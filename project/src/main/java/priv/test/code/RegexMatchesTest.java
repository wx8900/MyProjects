package priv.test.code;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import priv.entity.Rating;

public class RegexMatchesTest {
	
	public static void main(String args[]) {
		String str = "[0,0]";
		String pattern = "\\[[0-9]\\d*,[0-9]\\d*\\]";

		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(str);
		System.out.println(m.matches());
		
		String sb = "1:3,2:57,3:0,4:296,5:16888,";
		System.out.println(sb.substring(0, (sb.length()-1)));
		String[] arr = sb.split("\\,");
		for (String s : arr) {
			System.out.println(s);
		}
		
		System.out.println("=============");
		System.out.println(Rating.Rating1.getIndex()); 
		System.out.println(Integer.parseInt("4.0, aa,   18".toString().split(",")[2].trim()));
		String number = "10.0";
		Float result = Float.parseFloat(number);
		float m1 = 2.8f;
	    int n = (int)m1;
	    System.out.println(n);
	     
	     DecimalFormat df = new DecimalFormat("##.##%");
		double a =2.0;
		int b =0;
		System.out.println(a/b);
		System.out.println(df.format((a / b)));
		
		//System.out.println("[97, 115]".substring(1, ("[97, 115]".length()-1)));
		
		System.out.println(df.format((a / b)));
		
		System.out.println("&infin;");
	}

}
