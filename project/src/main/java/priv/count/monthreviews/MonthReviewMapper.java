package priv.count.monthreviews;

import java.io.IOException;
import java.util.Calendar;
import java.util.TimeZone;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import priv.entity.Review;

import com.alibaba.fastjson.JSON;

public class MonthReviewMapper extends Mapper<Object, Text, Text, IntWritable> {
	private final static IntWritable ONE = new IntWritable(1);
	private Text word = new Text();
	Calendar cal = Calendar.getInstance(TimeZone
			.getTimeZone("America/Los_Angeles"));

	public void map(Object key, Text value, Context context)
			throws IOException, InterruptedException {
		
		Review review = JSON.parseObject(value.toString(), Review.class);
		word.set(getMonthAndYear(review.getReviewTime()));
		
		context.write(word, ONE);
	}

	private String getMonthAndYear(String input) {
		if (input == null || "".equals(input)) return "";
		String[] str = input.split(",");
		String[] arr = str[0].split(" ");
		return str[1]+","+arr[0];
	}
}