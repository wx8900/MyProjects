package priv.preprocess.code;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import priv.entity.Review;

import com.alibaba.fastjson.JSON;

/*import java.time.DayOfWeek;
 import java.time.LocalDate;
 import java.time.format.DateTimeFormatter;
 import java.time.format.TextStyle;
 import java.util.Calendar;
 import java.util.Date;
 import java.util.Locale;
 import java.util.TimeZone;
 */
public class MyRunnableTest implements Runnable {// :\ .\ -\ \,
	private String threadName;
	private static final String desktop = "/home/cloudera/test/Elect_Data_13.txt";
	static File input = new File(desktop);
	static BufferedReader br = null;

	static {
		try {
			br = new BufferedReader(new FileReader(input));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	MyRunnableTest(String name) {
		threadName = name;
		System.out.println("Creating " + threadName);
	}

	public void run() {
		for (int i = 0; i < 50000; i++) {
			Review review = null;
			try {
				review = JSON.parseObject(br.readLine(), Review.class);
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("EXCEPTION : " + e.getMessage());
			}
			if (review == null)
				break;
			System.out.println(this.threadName + " --- " + review);
		}
		throw new RuntimeException();
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		try {
			for (int i = 0; i < 1000; i++) {
				new Thread(new MyRunnableTest("Thread" + "-" + i)).start();
			}
		} catch (RuntimeException rx) {
			System.out.println("Caught EXCEPTION ============ " + rx);
			Thread.sleep(90000000);
		} catch (Exception x) {
			System.out.println("Caught EXCEPTION ============ " + x);
			Thread.sleep(90000000);
		}

		/*
		 * String desktop = "/home/cloudera/test/Elect_Data_11.txt"; File input
		 * = new File(desktop); BufferedReader br = new BufferedReader(new
		 * FileReader(input));
		 * 
		 * for (int i =0; i < 500000; i++) { Review review =
		 * JSON.parseObject(br.readLine(), Review.class); if (review == null)
		 * break; System.out.println("review : "+review); }
		 */

		// TODO Auto-generated method stub
		// 11 25, 2010

		// DateTimeFormatter formatter =
		// DateTimeFormatter.ofPattern("MM DD, YYYY");
		// Date date = Date.parse("11 25, 2010", formatter); // LocalDate =
		// 2010-02-23
		// DayOfWeek dow = date.getDayOfWeek(); // Extracts a `DayOfWeek` enum
		// object.
		// String output = dow.getDisplayName(TextStyle.SHORT, Locale.US); //
		// String = Tue

		// Calendar c = Calendar.getInstance();
		// c.set(2016, 10, 22);//sunday -- 1
		//
		// int day_of_week = c.get(Calendar.DAY_OF_WEEK);
		// System.out.println("day_of_week : "+day_of_week);

		/*
		 * Calendar now = Calendar.getInstance();
		 * System.out.println("Current date : " + (now.get(Calendar.MONTH) + 1)
		 * + "-" + now.get(Calendar.DATE) + "-" + now.get(Calendar.YEAR));
		 * 
		 * String[] strDays = new String[] { "Sunday", "Monday", "Tuesday",
		 * "Wednesday", "Thusday", "Friday", "Saturday" }; // Day_OF_WEEK starts
		 * from 1 while array index starts from 0
		 * System.out.println("Current day is : " +
		 * strDays[now.get(Calendar.DAY_OF_WEEK) - 1]);
		 */

		/*
		 * Calendar cal = Calendar.getInstance(TimeZone.getDefault()); //change
		 * the timezone: Provide the TimeZone Id as parameter
		 * cal.setTimeZone(TimeZone.getTimeZone("Europe/Athens")); //get the
		 * details in new time zone
		 * System.out.println(cal.get(Calendar.HOUR_OF_DAY));
		 * System.out.println(cal.get(Calendar.WEEK_OF_MONTH));
		 * System.out.println(cal.get(Calendar.WEEK_OF_YEAR));
		 */

		// sunday -- 1
		/*
		 * Calendar cal = Calendar.getInstance(); cal.set(Calendar.DAY_OF_MONTH,
		 * 22); cal.set(Calendar.MONTH, Calendar.OCTOBER);
		 * cal.set(Calendar.YEAR, 2016);
		 * 
		 * int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		 * 
		 * System.out.println(dayOfWeek);
		 */
	}
}
