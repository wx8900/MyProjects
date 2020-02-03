package priv.analysis.overallrating.count;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class OverallRatingDriver {

	public static void main(String[] args) throws Exception {

		Job job = Job.getInstance(new Configuration(), "overall rating count");

		String[] otherArgs = new GenericOptionsParser(args)
				.getRemainingArgs();

		if (otherArgs.length != 2) {
			System.err
					.println("Usage: priv.analysis.overallrating.OverallRatingDriver <in>  <out>");
			System.exit(2);
		}

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);

		job.setJarByClass(OverallRatingDriver.class);
		job.setMapperClass(OverallRatingMapper.class);
		job.setReducerClass(OverallRatingReducer.class);
		job.setCombinerClass(OverallRatingReducer.class);

		job.setNumReduceTasks(1);

		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		job.waitForCompletion(true);
	}

}
