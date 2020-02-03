package priv.test.code;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import priv.entity.Review;

import com.alibaba.fastjson.JSON;

public class OverallPartitionMapper extends Mapper<LongWritable, Text, Text, Text> {
	private Text word = new Text();

	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		
		Review review = JSON.parseObject(value.toString(), Review.class);
		word.set(review.getOverall());
		context.write(word , value);
	}
}