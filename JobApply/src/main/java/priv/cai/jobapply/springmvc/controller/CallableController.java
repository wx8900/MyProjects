package priv.cai.jobapply.springmvc.controller;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import priv.cai.jobapply.constant.Constants;
import priv.cai.jobapply.dataload.main.LoadProperties;
import priv.cai.jobapply.dataload.parser.ElementsParser;
import priv.cai.jobapply.springmvc.model.Positions;
import priv.cai.jobapply.springmvc.service.PositionsService;
 
@Controller
public class CallableController {
	
	@Autowired
	PositionsService positionsService;
	
	public static void main(String[] args) throws FileNotFoundException {
		String queryConditions = "/jobs?q=PHP&l=Thousand+Oaks%2C+CA";
		List<Positions> list = new CallableController().getCrawlData(queryConditions);
		for (int i = 0; i < list.size(); i++) {
			System.out.println("==== main test ===="+list.get(i));
		}
	}
	
	@RequestMapping(value = { "/savedata" }, method = RequestMethod.POST)
	public String listPositions(@RequestParam("q") String keywords,
								@RequestParam("l") String location, Model model) {
		
		String queryConditions = "";
		List<Positions> positions = new ArrayList<Positions>(15);// the size of Crawl Data in one time
		
		if(location == null || ("").equals(location)) {
			location = "Thousand Oaks, CA";
		}
		if(keywords != null || !("").equals(keywords)) {
			String convertLocation = location.replaceAll(Constants.SPACE, Constants.SPACE_TO_PLUS).replaceAll(Constants.COMMA, Constants.COMMA_TO_HEX);
			queryConditions = "/jobs?q=" + keywords + "&l=" + convertLocation;
			try {
				positions = getCrawlData(queryConditions);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("keywords ====== "+keywords+" ======location ====== "+location);
		System.out.println("queryConditions ====== "+queryConditions);
		System.out.println("positions.size ============ "+positions.size());
		
		if(positions != null && positions.size() > 0) {
			DateTimeFormatter dtf = DateTimeFormat.forPattern(Constants.DATAFORMAT_YMD_HMS);
			DateTime currentDate = new DateTime();
			int leng = positions.size();
			for(int i = 0;  i < leng; i++) {
				Positions po = (Positions) positions.get(i);
			    String date = dtf.print(currentDate);
				po.setJobinsertdate(date);
				positionsService.savePositions(po);
			}
		}

		model.addAttribute("savestatus", "save successful!");
		return "redirect:/listAll";
	}
	
	public List<Positions> getCrawlData(String queryConditions) throws FileNotFoundException {
		System.out.println("---- Crawl Data program is running !!! ----");
		Date date1 = new Date();

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

		System.out.println("----program start running 111----");
		//ExtractElements.parseSearchResults(host, sb);
		System.out.println("----sb----"+sb.toString());
		List<Positions> li = ElementsParser.parseSearchResults(host, sb);
		System.out.println("----program start ending 222----");

		exs.shutdown();

		for (Future<String> f : list) {
			// 从Future对象上获取任务的返回值，并输出到控制台
			System.out.println(">>>" + f.isDone());
		}

		Date date2 = new Date();
		System.out.println("----program close running----，running period : 【" + (date2.getTime() - date1.getTime()) + " ms 】");
		
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

}