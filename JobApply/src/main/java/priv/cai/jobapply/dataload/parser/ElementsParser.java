package priv.cai.jobapply.dataload.parser;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import priv.cai.jobapply.constant.Constants;
import priv.cai.jobapply.springmvc.model.Positions;

/**
 * Different results come from different web sites must write different template to analyze the return HTML codes.
 * 
 * @author Jack Cai
 *
 */

public class ElementsParser {
	
	final static Logger logger = Logger.getLogger(ElementsParser.class);
	
	/**
	 * Analyze the HTML
	 * 
	 * @param host
	 * @param sb
	 * @return
	 */
	public static List<Positions> parseSearchResults(String host, StringBuffer sb) {
		if (sb == null || (sb != null && sb.toString().length() <= 0)) return new ArrayList<Positions>(1);
		Document doc = Jsoup.parse(sb.toString());
		Elements result = null;
		if (doc == null) {
			return new ArrayList<Positions>(1);
		} else {
			result = doc.select("#resultsCol");
		}
		if (result == null) return new ArrayList<Positions>(1);
		
		//total Count
		/*Elements totalCount = result.select("#searchCount");
		String totalCountText = totalCount.text();
		String[] number = totalCountText.split(" "); // totalcount - backup
		System.out.println("totalCountText : " + number[5]);*/
		
		Elements rows = new Elements();
		Positions po = null;
		ArrayList<Positions> list = new ArrayList<Positions>();
		try {
			rows = doc.getElementsByAttributeValueContaining("class", "  row  result");
			Calendar cal = Calendar.getInstance();       
	        TimeZone tz = TimeZone.getTimeZone(Constants.TIMEZONE_US_LA);
			if (rows != null) {
				for (Element row : rows) {
					po = new Positions();
					
					Element jobtitle = row.select("a[href]").first();// jobtitle
					po.setTitle(jobtitle.text());
					if(jobtitle.attr("href") != null) {
						int length = jobtitle.attr("href").length();
						if (length > 0 && length < 500) {
							po.setTitlehref(jobtitle.attr("href"));
						} else if (length >= 500) {
							po.setTitlehref(jobtitle.attr("href").substring(0, 498));
						}
					}
					
					Element table = row.select("tr > td").first();
					if (table != null) {
						if (table.getElementsByTag("nobr").first() != null) { // salary
							Element money = table.getElementsByTag("nobr").first();
							po.setSalary((money != null) ? money.text() : "");
						} else {
							// add 20170513 start
							Element sjclStyle = row.select("div[class=sjcl]").first();
							if(sjclStyle != null && sjclStyle.hasText()) {
								if (sjclStyle.select("span[class=company]").first() != null) {
									po.setCompany(sjclStyle.select("span[class=company]").first().text());
								}
								if (sjclStyle.select("span[class=location]").first() != null) {
									po.setLocation(sjclStyle.select("span[class=location]").first().text());
								}
							}
							// add 20170513 end
							
							Element moneySecondStyle = row.select("div[class=sjcl] > div").first();
							po.setSalary((moneySecondStyle != null) ? moneySecondStyle.text() : "");
						}
						
						if (table.select("span[class=summary]").first() != null) {// description
							String describeText = table.select("span[class=summary]").first().text();
							if (describeText != null && !StringUtils.isEmpty(describeText)) {
								int leng = describeText.length();
								if (leng > 0 && leng < 300) {
									po.setDescription(describeText);
								} else if (leng >= 300) {{
									po.setDescription(describeText.substring(0, 298));
								}
							}
						}
					}
					
					String cName = "";
					String cHref = "";
					Element company = row.select("span[class=company]").first();
					if(company != null ) {
						if(company.hasText()) {
							cName = company.text();
						}
						if(null != company.select("span") && company.select("span").first().hasText()) {
							cName = company.select("span").first().text();
						}
						if(null != company.select("span > a") && null != company.select("span > a").first()) {
							Element companyN = company.select("span > a").first();
							cName = companyN.text();
							cHref = host + companyN.attr("href");
						}
						// modify 20170513 start
						if(StringUtils.isEmpty(po.getCompany())) {
							po.setCompany(cName);
							po.setCompanyhref(cHref);
						}
						// modify 20170513 end
					}
					
					Element reviewsCount = row.select("span[class=slNoUnderline]").first();
					if (reviewsCount != null) {
						po.setReviews(reviewsCount.text());
					}
					Element reviewLink = row.select("a[class=turnstileLink slNoUnderline ]").first();
					if(reviewLink != null) {
						po.setComments(reviewLink.attr("href"));
					}
					Element ratings = row.select("span[class=ratings]").first();
					if (ratings != null) {
						String[] str = ratings.attr("background-image").split("_");
						if(str != null && str.length > 2) {
							po.setStars(str[1]);
						}
					}
					
					Element location = row.select("span.location").first();// location
					if(location != null && !StringUtils.isEmpty(location.toString())){
						String locationText = location.text();
						po.setLocation(locationText);
						int size = locationText.length();
						Pattern pat = Pattern.compile(Constants.REGEX_NUMERIC);
				        Matcher mat = pat.matcher(locationText);
				        if (mat.find()) {
				        	po.setZipcode(locationText.substring(size - 5, size));
				        }
					}
					
					String offset = "";
					Element jobdate = row.select("div[class=result-link-bar] > span[class=date]").first();
					if(jobdate != null) {
						String offsetDays = jobdate.text();
						if (!offsetDays.contains("+") && offsetDays.endsWith(" days ago")) {
							offset = getPostDateofPosition(offsetDays.replace(" days ago",""), cal, tz);
						} else if (offsetDays.endsWith(" hours ago")) {
							offset = getPostDateofPosition("1", cal, tz);
						} 
					}
					po.setJobcreated(offset);
					
					Element Sponsored = row.select("span[class=sdn]").first();
					if (Sponsored != null) {
						po.setSponsored("1");
					}
					
					list.add(po);
				}
			}
		   }
		} catch (Exception e) {
			logger.error("This is error", e);
			System.exit(0);
		}
		
		return list;
	}
	
	/**
	 * 
	 */
	private static String getPostDateofPosition(String offset, Calendar cal, TimeZone tz) {
		int off = Integer.valueOf(offset).intValue();
        cal.setTimeZone(tz);
		cal.add(Calendar.DATE, -off);
		SimpleDateFormat df = new SimpleDateFormat(Constants.DATAFORMAT_YMD); 
		return df.format(cal.getTime());
	}
	
	/*private static boolean isNumeric(String str) {
		if (str == null) return false;
		if (str != null && str.length() <= 0) return false;
        String regEx = "^-?[0-9]+$";
        Pattern pat = Pattern.compile(regEx);
        Matcher mat = pat.matcher(str);
        if (mat.find()) {
            return true;
        }
        else {
            return false;
        }
    }*/
	private static void isNumber() {
		String locationText = "CA 91362";
		if(locationText != null){
			int size = locationText.length();
			Pattern pat = Pattern.compile(Constants.REGEX_NUMERIC);
	        Matcher mat = pat.matcher(locationText);
	        if (mat.find()) {
	        	System.out.println(locationText.substring(size - 5, size));
	        }
	        else {
	        	System.out.println("======");
	        }
		}
	}
	
	private void getPicURL(Element row) {
		Element ratings = row.select("span[class=ratings]").first();
		if (ratings != null) {
			String[] str = ratings.attr("background-image").split("_");
			if(str != null && str.length > 2) {
				System.out.println(str[1] + "Stars");
			}
		}
	}

	public static void main(String[] args) {
		/*String html = "<p>An <a href='http://example.com/'><b>example</b></a> link.</p>";
		Document doc = Jsoup.parse(html);
		Element link = doc.select("a").first();

		String text = doc.body().text(); // "An example link"
		String linkHref = link.attr("href"); // "http://example.com/"
		String linkText = link.text(); // "example""

		String linkOuterH = link.outerHtml(); 
		    // "<a href="http://example.com"><b>example</b></a>"
		String linkInnerH = link.html(); // "<b>example</b>"
		System.out.println(text);
		System.out.println(linkHref);
		System.out.println(linkText);
		System.out.println(linkOuterH);
		System.out.println(linkInnerH);*/
		
		/*String str = "91360"; //true
        System.out.println(isNumeric(str));
             
        str = "91360-1623";   //true
        System.out.println(isNumeric(str));
             
        str = "CA";    //false
        System.out.println(isNumeric(str));*/
		
		//System.out.println(getPostDateofPosition("8"));  
		//isNumber();
//		Element row = "<span class='ratings' style='height: 11px; line-height: 11px; background-image: url(/images/cmp/star_2_sm@2x.png); background-size: 12px 22px;width: 60px;'></span>";
//		getPicURL(row);
	}

}
