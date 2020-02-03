package priv.test.code;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DataConvertTest {

	public static void main(String[] args) throws ParseException {
		
		 String input_date="11 30, 2016";
		 Date date = new SimpleDateFormat("MM dd, yyyy").parse(input_date);
		 
		 Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("America/Los_Angeles"));
			cal.setTime(date);
			int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
			System.out.println(dayOfWeek);
		 
		 
//		 String pattern = "MM/dd/yyyy";
//		    SimpleDateFormat format = new SimpleDateFormat(pattern);
//		    try {
//		      Date date = format.parse(input_date);
//		      System.out.println(date);
//		    } catch (ParseException e) {
//		      e.printStackTrace();
//		    }
//		    // formatting
//		    System.out.println(format.format(new Date()));
		 
//		 Calendar c = Calendar.getInstance(TimeZone.getTimeZone("America/Los_Angeles"));
//		 SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
//		 format.format(obj);
//		 c.setTime(yourDate);
//		 int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
//		 
//
//			Calendar cal = Calendar.getInstance();
//			cal.setTimeZone();
//			System.out.println(cal.get(Calendar.HOUR_OF_DAY));
//			System.out.println(cal.get(Calendar.WEEK_OF_MONTH));
//			System.out.println(cal.get(Calendar.WEEK_OF_YEAR));
//		 
//		  
//		  Date dt1=format1.parse(input_date);
//		  format1.setTimeZone();
//		  format1.DAY_OF_WEEK_FIELD
		// TODO Auto-generated method stub
				//11 25, 2010
				
				//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM DD, YYYY");
				//Date date = Date.parse("11 25, 2010", formatter); // LocalDate = 2010-02-23
				//DayOfWeek dow = date.getDayOfWeek();  // Extracts a `DayOfWeek` enum object.
				//String output = dow.getDisplayName(TextStyle.SHORT, Locale.US); // String = Tue
				
//				Calendar c = Calendar.getInstance();
//				c.set(2016, 10, 22);//sunday -- 1
		//
//				int day_of_week = c.get(Calendar.DAY_OF_WEEK);
//				System.out.println("day_of_week : "+day_of_week);
				
				/*Calendar now = Calendar.getInstance();
			    System.out.println("Current date : " + (now.get(Calendar.MONTH) + 1) + "-"
			        + now.get(Calendar.DATE) + "-" + now.get(Calendar.YEAR));

			    String[] strDays = new String[] { "Sunday", "Monday", "Tuesday", "Wednesday", "Thusday",
			        "Friday", "Saturday" };
			    // Day_OF_WEEK starts from 1 while array index starts from 0
			    System.out.println("Current day is : " + strDays[now.get(Calendar.DAY_OF_WEEK) - 1]);*/
				
			    
				//sunday -- 1
			   /* Calendar cal = Calendar.getInstance();
			    cal.set(Calendar.DAY_OF_MONTH, 22);
			    cal.set(Calendar.MONTH, Calendar.OCTOBER);
			    cal.set(Calendar.YEAR, 2016);

			    int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
			    
			    System.out.println(dayOfWeek);*/

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

				 

				 

	}

}
