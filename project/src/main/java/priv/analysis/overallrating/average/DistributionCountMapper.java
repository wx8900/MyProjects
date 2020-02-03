package priv.analysis.overallrating.average;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class DistributionCountMapper extends
		Mapper<LongWritable, Text, Text, IntWritable> {
	private final static IntWritable ONE = new IntWritable(1);
	private Text outKey = new Text();

	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		String[] arr = value.toString().split(",");
		outKey.set(arr[1]);
		context.write(outKey, ONE);
	}
}
