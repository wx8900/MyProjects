package priv.helpfulness.weight;

import java.io.IOException;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import priv.entity.Review;
import priv.entity.StopWords;

import com.alibaba.fastjson.JSON;

public class HelpfulWeightMapper extends Mapper<Object, Text, Text, Text> {
	Pattern pattern = Pattern.compile("[\\p{Punct}\\d]");
	private Text word = new Text();
	private Text tweetId = new Text();

	public void map(Object key, Text value,
			Mapper<Object, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		
		Review review = JSON.parseObject(value.toString(), Review.class);
		String review_id = review.getReviewerID();
		String txt = review.getReviewText();
		if (txt == null) {
			return;
		}
		
//		Matcher matcher = pattern.matcher(txt);
//		String result = matcher.replaceAll(" ").trim();
//		String[] words = result.split(" ");		
//		for (String str : array) {
//	        if (str.length() >= 2 && StringUtils.isAlpha(str)
//					&& !StopWords.is(str)) {
//	        	word.set(str);
//				context.write(word, ONE);
//	        }
//		}

		txt = StringEscapeUtils.unescapeHtml(txt.toLowerCase());

		String[] words = txt.split(" ");
		int len = words.length;
		for (int i = 0; i < len; i++) {
			if ((words[i].length() > 2) && (StringUtils.isAlpha(words[i]))
					&& (!StopWords.is(words[i]))) {
				word.set(words[i]);
				tweetId.set(review_id);
				context.write(word, tweetId);
			}
		}
	}
}