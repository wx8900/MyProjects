package priv.cai.jobapply.dataload.main;

import org.apache.log4j.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import priv.cai.jobapply.springmvc.controller.LoginController;

public class App {
	
	final static Logger logger = Logger.getLogger(App.class);

	private static ApplicationContext context;

	//[org.springframework.batch.item.ItemReader] for property 'itemReader': no matching editors or conversion strategy found
    public static void main(String[] args) {

	String[] springConfig  =
		{	"batch/config/database.xml",
			"batch/config/context.xml",
			"batch/jobs/job-report.xml"
		};

	context = new ClassPathXmlApplicationContext(springConfig);

	JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
	Job job = (Job) context.getBean("positionJob");

	try {

		JobExecution execution = jobLauncher.run(job, new JobParameters());
		System.out.println("Exit Status : " + execution.getStatus());

	} catch (Exception e) {
		logger.error("This is error", e);
	}

	System.out.println("Done");

  }
}