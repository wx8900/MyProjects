package priv.helpfulness.weight;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class HelpfulWeightDriver {
	public static void main(String[] args) throws Exception {
		Job job = Job.getInstance(new Configuration(), "calculate helpful weight");

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);

		job.setJarByClass(HelpfulWeightDriver.class);
		job.setMapperClass(HelpfulWeightMapper.class);
		job.setReducerClass(HelpfulWeightReducer.class);
		job.setCombinerClass(HelpfulWeightReducer.class);

		job.setNumReduceTasks(1);

		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		job.waitForCompletion(true);
	}
}
