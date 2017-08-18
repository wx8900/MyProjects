package com.test.spring.quartz;

import java.util.Calendar;

public class MyTask {

	public void printCurrentTime() {
		// printing current system time
		System.out.println("Current Time : " + Calendar.getInstance().getTime());
	}
}
