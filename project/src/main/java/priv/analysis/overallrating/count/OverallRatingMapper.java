package priv.analysis.overallrating.count;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import priv.entity.Review;

import com.alibaba.fastjson.JSON;

public class OverallRatingMapper extends
		Mapper<LongWritable, Text, Text, IntWritable> {
	private final static IntWritable ONE = new IntWritable(1);
	private Text word = new Text();

	public void map(LongWritable key, Text value, Context context) throws IOException,
			InterruptedException {

		Review review = JSON.parseObject(value.toString(), Review.class);
		String overall = review.getOverall();

		word.set(overall);
		context.write(word, ONE);
	}
}