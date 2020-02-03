package priv.count.dayofweek;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import priv.entity.Review;

import com.alibaba.fastjson.JSON;

public class DayOfWeekMapper extends Mapper<Object, Text, Text, IntWritable> {
	private final static IntWritable ONE = new IntWritable(1);
	private Text word = new Text();
	Calendar cal = Calendar.getInstance(TimeZone
			.getTimeZone("America/Los_Angeles"));

	public void map(Object key, Text value, Context context)
			throws IOException, InterruptedException {

		try {
			Review review = JSON.parseObject(value.toString(), Review.class);
			word.set(String.valueOf(getDayOfWeek(review.getReviewTime())));
		} catch (Exception e) {
			e.printStackTrace();
		}
		context.write(word, ONE);
	}

	private int getDayOfWeek(String input) throws ParseException {
		if (input == null || "".equals(input)) return 0;
		Date date = new SimpleDateFormat("MM dd, yyyy").parse(input);
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_WEEK);
	}
}