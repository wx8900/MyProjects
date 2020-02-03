package priv.analysis.summaryoverall;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class OverallPartitioner extends Partitioner<Text, IntWritable> {

	@Override
	public int getPartition(Text key, IntWritable value, int arg2) {
		String arr = key.toString().split(",")[0].trim();
		
		return (int) (Float.valueOf(arr).intValue()-1);
		
	}
}
