package priv.cai.jobapply.dataload.spider;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TextFilter {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static StringBuffer getUsefulElementsFromResults(StringBuffer sb) {
		if (sb == null || (sb != null && sb.toString().length() <= 0)) return new StringBuffer(0);
		
		Document doc = Jsoup.parse(sb.toString());
		Elements result = null;
		if (doc == null) {
			return new StringBuffer(0);
		} else {
			result = doc.select("#resultsCol");
		}
		if (result == null) return new StringBuffer(0);
		
		Elements rows = new Elements();
		StringBuffer sbOut = new StringBuffer();
		try {
			rows = doc.getElementsByAttributeValueContaining("class", "  row  result");
			if (rows != null) {
				for (Element row : rows) {
					//sbOut.append(delHTMLTag(row.toString()));//test1
					//sbOut.append(stripHtml(delHTMLTag(row.toString())));//test2
					sbOut.append(stripHtml(stripHtml(delHTMLTag(row.toString()))));//test3
				}
			}
		} catch (Exception e) {
				e.printStackTrace();
				sbOut = null;
		}
		return sbOut;
	}

	public static String delHTMLTag(String htmlStr){  
        String regEx_script="<script[^>]*?>[\\s\\S]*?<\\/script>"; //定义script的正则表达式  
        String regEx_style="<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式  
        String regEx_html="<[^>]+>"; //定义HTML标签的正则表达式  
         
        Pattern p_script=Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE);  
        Matcher m_script=p_script.matcher(htmlStr);  
        htmlStr=m_script.replaceAll(""); //过滤script标签  
         
        Pattern p_style=Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE);  
        Matcher m_style=p_style.matcher(htmlStr);  
        htmlStr=m_style.replaceAll(""); //过滤style标签  
         
        Pattern p_html=Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE);  
        Matcher m_html=p_html.matcher(htmlStr);  
        htmlStr=m_html.replaceAll(""); //过滤html标签  
  
       return htmlStr.trim(); //返回文本字符串  
    }   
      
    /** 
    *去掉字符串里面的html代码。<br> 
    *要求数据要规范，比如大于小于号要配套,否则会被集体误杀。 
    *　 
    *@paramcontent 
    *　　　　　内容 
    *@return去掉后的内容 
    */  
public static String stripHtml(String content){  
    //<p>段落替换为换行  
    content=content.replaceAll("<p.*?>","");  
    //<br><br/>替换为换行 　  
    content=content.replaceAll("<brs*/?>","");  
    //去掉其它的<>之间的东西   
    content=content.replaceAll("<.*?>","");  
    //还原HTML//content=HTMLDecoder.decode(content);   
    return content;  
    }  
  
public static String replaceHtml(String html){   
    String regEx="<.+?>"; //表示标签   
    Pattern p=Pattern.compile(regEx);   
    Matcher m=p.matcher(html);   
    String s=m.replaceAll("");   
    return s;   
} 

}
