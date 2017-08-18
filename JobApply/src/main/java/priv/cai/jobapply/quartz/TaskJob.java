package priv.cai.jobapply.quartz;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.hibernate5.SessionFactoryUtils;
import org.springframework.orm.hibernate5.SessionHolder;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import priv.cai.jobapply.constant.Constants;
import priv.cai.jobapply.dataload.main.LoadProperties;
import priv.cai.jobapply.dataload.parser.ElementsParser;
import priv.cai.jobapply.dataload.spider.SaveTXTFiles;
import priv.cai.jobapply.dataload.spider.TextFilter;
import priv.cai.jobapply.springmvc.controller.CallableThread;
import priv.cai.jobapply.springmvc.model.Positions;

public class TaskJob extends QuartzJobBean {

	private static final Logger log = LoggerFactory.getLogger(TaskJob.class);
	private SessionFactory sessionFactory;
	SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATAFORMAT_YMD_HMS);
	List<Positions> list = null;

	@Override
	protected void executeInternal(JobExecutionContext ctx) throws JobExecutionException {
		list = new ArrayList<Positions>(30);
		list = run();
		
		SessionHolder sessionHolder = (SessionHolder) TransactionSynchronizationManager
				.getResource(getSessionFactory());
		boolean existingTransaction = sessionHolder != null;
		Session session;
		if (existingTransaction) {
			log.error("Found thread-bound Session for HibernateInterceptor");
			session = sessionHolder.getSession();
		} else {
			session = openSession();
			TransactionSynchronizationManager.bindResource(getSessionFactory(), new SessionHolder(session));
		}
		try {
			/*
			 * try { session = sessionFactory.getCurrentSession(); } catch
			 * (HibernateException e) { session = sessionFactory.openSession();
			 * }
			 */
			executeTransactional(ctx);
		} catch (HibernateException ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (existingTransaction) {
				log.debug("Not closing pre-bound Hibernate Session after TransactionalQuartzTask");
			} else {
				SessionFactoryUtils.closeSession(session);
				TransactionSynchronizationManager.unbindResource(getSessionFactory());
			}
		}
	}

	public void executeTransactional(JobExecutionContext ctx) throws JobExecutionException {
		DateTimeFormatter dtf = DateTimeFormat.forPattern(Constants.DATAFORMAT_YMD_HMS);
		DateTime currentDate = new DateTime();

		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		int leng = list.size();
		System.out.println("======= session =======" + session);
		System.out.println("======= leng =======" + leng);
		for (int i = 0; i < leng; i++) {
			Positions po = (Positions) list.get(i);
			String date = dtf.print(currentDate);
			po.setJobinsertdate(date);
			session.save(po);
			// session.persist(po);
			System.out.println("======= i =======" + i);
		}
		session.getTransaction().commit();
		session.close();
	}

	public List<Positions> run() {
		String location = null;
		String keywords = null;
		String convertLocation = "";
		String queryConditions = "";
		String loca = "";
		String key = "";
		List<Positions> positions = new ArrayList<Positions>(30);

		if (location != null || (location != null && !("").equals(location.trim()))) {
			loca = location;
		} else {
			loca = "Los Angeles, CA";
		}

		if (keywords != null || (keywords != null && !("").equals(keywords.trim()))) {
			key = keywords;
		} else {
			key = "Java";
		}
		convertLocation = loca.replaceAll(Constants.SPACE, Constants.SPACE_TO_PLUS).replaceAll(Constants.COMMA,
				Constants.COMMA_TO_HEX);
		queryConditions = "/jobs?q=" + key + "&l=" + convertLocation;

		try {
			positions = getCrawlData(queryConditions);
		} catch (FileNotFoundException e) {
			System.out.println("getCrawlData has FileNotFound Exception !!!" + e.getMessage());
		}

		return positions;
	}

	public List<Positions> getCrawlData(String queryConditions) throws FileNotFoundException {
		System.out.println("---- Crawl Data program is running !!! ----");
		String host = "";
		String url = LoadProperties.initProperties();
		String[] strUrl = url.split(",");
		int len = strUrl.length;

		ArrayList<Future<String>> list = new ArrayList<Future<String>>();
		ExecutorService exs = Executors.newFixedThreadPool(len);
		for (int i = 0; i < len; i++) {
			System.out.println(">>>>>>>>>>>>>>> Task " + i + " Start !!!");
			host = strUrl[i];
			list.add(exs.submit(new CallableThread(host + queryConditions)));
			System.out.println(">>>>>>>>>>>>>>> Task " + i + " End !!!");
		}

		StringBuffer sb = getOutputString(list);
		System.out.println("---- Program start running  ----");
		List<Positions> li = ElementsParser.parseSearchResults(host, sb);
		System.out.println("---- Program start ending  ----");
		
		// 20170513 add logic: save return HTML code into txt files start
		StringBuffer sBuffer = TextFilter.getUsefulElementsFromResults(sb);
		System.out.println("The result of saveTextFiles() [1: Success; 0: Failure]: " + SaveTXTFiles.saveAsTextFiles(sBuffer));
		
		// 20170513 add logic: end

		exs.shutdown();

		for (Future<String> f : list) { // 从Future对象上获取任务的返回值，并输出到控制台
			System.out.println(">>>" + f.isDone());
		}

		return li;
	}

	/**
	 * @param list
	 * @return
	 */
	private static StringBuffer getOutputString(ArrayList<Future<String>> list) {
		StringBuffer sb = new StringBuffer();
		for (Future<String> fs : list) {
			try {
				sb.append(fs.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		return sb;
	}

	protected Session openSession() throws DataAccessResourceFailureException {
		try {
			Session session = getSessionFactory().openSession();
			session.setFlushMode(FlushMode.MANUAL);
			return session;
		} catch (HibernateException ex) {
			throw new DataAccessResourceFailureException("Could not open Hibernate Session", ex);
		}
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
