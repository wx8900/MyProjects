package priv.test.code;

import java.util.TreeMap;

import org.apache.hadoop.io.Text;

public class StringTest {
	public static void main(String[] args) {
		String comma = ",";
		TreeMap<Integer, Text> reducerMap = new TreeMap<Integer, Text>();
		String input = "B00J6RVQJM	9";//"B00J6RVQJM	9"
		String[] arr = input.split(comma);

		if (1 == arr.length) {
			arr = input.split("\t");
		}
		System.out.println("=====" + Integer.parseInt(arr[1].toString()));
		reducerMap.put(Integer.parseInt(arr[1].toString()), 
				new Text(arr[0].toString().concat(comma).concat(arr[1].toString())));
		 System.out.println(reducerMap);
		
//		String s = "FOOBAR";
//        int i = Integer.parseInt(s);
//        // this line of code will never be reached
//        System.out.println("int value = " + i);
		 
		/*String r_name3 = "String 1359^8,!!!!!!!!!!!!!!!!!!!!!!!!!!!SAVE9&9.9[75{8?7;)6}4]:98!8*88 @#000%0*(0)0_+-=:/'.";
		//String r_name3 = "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!THANK";
        //Pattern pattern = Pattern.compile("[\\d]");
		Pattern pattern = Pattern.compile("[`~!@#$%^&*()+=|{}':;'.,\\[\\]._+-<>/?......|”“]");
		//Pattern pattern = Pattern.compile("[\\p{Punct}\\d]");
        Matcher matcher = pattern.matcher(r_name3);
        System.out.println(matcher.replaceAll("").trim());*/
	}

}
