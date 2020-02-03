package priv.analysis.reviewtext;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import priv.entity.Review;
import priv.entity.StopWords;

import com.alibaba.fastjson.JSON;

public class FilterStopWordsMapper extends Mapper<LongWritable, Text, Text, LongWritable> {

	private static final LongWritable ONE = new LongWritable(1);
	private Text word = new Text();
	Pattern pattern = Pattern.compile("[\\p{Punct}\\d]");

	public void map(LongWritable key, Text value,Context context)
					throws IOException, InterruptedException {
		
		Review review = JSON.parseObject(value.toString(), Review.class);
		Matcher matcher = pattern.matcher(review.getReviewText());
		String result = matcher.replaceAll(" ").trim();
		String[] array = result.split(" ");
				
		for (String str : array) {
	        if (str.length() >= 2 && StringUtils.isAlpha(str)
					&& !StopWords.is(str)) {
	        	word.set(str);
				context.write(word, ONE);
	        }
		}
	}
}

