package com.test.spring.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import priv.cai.jobapply.constant.Constants;

//@Component
public class DataConversionTask {
	
	private static final Logger LOG = LoggerFactory.getLogger(DataConversionTask.class);
	
	SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATAFORMAT_YMD_HMS);
	
	public DataConversionTask(){
		System.out.println("Quartzjob create success 12345 !!!");
	}

  //@Scheduled(cron = "0/1 * * * * ? ")
  public void run() {

      if (LOG.isInfoEnabled()) {
    	  LOG.info("This is schedule class，the  current time is ===== " + sdf.format(new Date()));
      }
	  //System.out.println("This is schedule class，the  current time is  " + sdf.format(new Date()));
  }
}