package priv.analysis.overallrating.average;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class AverageRatingMapper extends Mapper<Object, Text, Text, Text> {
	private Text outKey = new Text();

	public void map(Object key, Text value, Context context)
			throws IOException, InterruptedException {
		String[] str = value.toString().split(",");
		outKey.set(str[0]);
		context.write(outKey, value);
	}
}