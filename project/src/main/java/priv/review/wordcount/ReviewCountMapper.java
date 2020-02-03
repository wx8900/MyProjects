package priv.review.wordcount;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import priv.entity.Review;
import priv.entity.StopWords;

import com.alibaba.fastjson.JSON;

public class ReviewCountMapper extends Mapper<Object, Text, Text, IntWritable> {
	private final static IntWritable one = new IntWritable(1);
	Pattern pattern = Pattern.compile("[\\p{Punct}\\d]");
	private Text word = new Text();

	public void map(Object key, Text value, Context context)
			throws IOException, InterruptedException {
		Review review = JSON.parseObject(value.toString(), Review.class);
		Matcher matcher = pattern.matcher(review.getReviewText());
		String result = matcher.replaceAll(" ").trim();
		String[] array = result.split(" ");
				
		for (String str : array) {
	        if (str.length() >= 2 && StringUtils.isAlpha(str)
					&& !StopWords.is(str)) {
	        	word.set(str);
				context.write(word, one);
	        }
		}
	}
}