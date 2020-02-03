package priv.analysis.overallrating.average;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import priv.entity.Review;

import com.alibaba.fastjson.JSON;

public class FilterRatingMapper extends
		Mapper<Object, Text, Text, Text> {
	private Text outKey = new Text();

	public void map(Object key, Text value, Context context) throws IOException,
			InterruptedException {

		Review review = JSON.parseObject(value.toString(), Review.class);
		String asin = review.getAsin();

		outKey.set(asin);
		context.write(outKey, value);
	}
}