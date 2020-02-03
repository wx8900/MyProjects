package priv.top.reviewer;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class ReviewerChainDriver {
	
	private static Job getReviewerCount(Configuration conf, String input, String output) throws IOException {
		Job job = new Job(conf, "users count");

		job.setJarByClass(ReviewerChainDriver.class);
		job.setMapperClass(ReviewerCountMapper.class);
		job.setReducerClass(ReviewerCountReducer.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);

		job.setNumReduceTasks(1);

		FileInputFormat.addInputPath(job, new Path(input));
		FileOutputFormat.setOutputPath(job, new Path(output));

		return job;
	}
	
	private static Job getTopTenReviewer(Configuration conf, String input, String output) throws IOException {
			Job job = new Job(conf, "top 10 users who publish most reviews");

			job.setJarByClass(ReviewerChainDriver.class);
			job.setMapperClass(TopTenReviewerMapper.class);
			job.setReducerClass(TopTenReviewerReducer.class);
			job.setMapOutputKeyClass(NullWritable.class);
			job.setMapOutputValueClass(Text.class);
			job.setOutputKeyClass(NullWritable.class);
			job.setOutputValueClass(Text.class);

			job.setNumReduceTasks(1);

			FileInputFormat.addInputPath(job, new Path(input));
			FileOutputFormat.setOutputPath(job, new Path(output));

			return job;
		}
	
	public static void deleteOutPath(Configuration conf, String output1,
			String output2) throws IOException {
		FileSystem hdfs = FileSystem.get(conf);
		Path path = new Path(output1);
		hdfs.delete(path, true);
		path = new Path(output2);
		hdfs.delete(path, true);
	}

	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		conf.set("mapred.textoutputformat.ignoreseparator", "true");
		conf.set("mapred.textoutputformat.separator", ",");
		
		String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
		if (otherArgs.length != 3) {
			System.out.println("Usage: Driver, error in <in> <out> !!!");
			System.exit(2);
		}
		
		deleteOutPath(conf, otherArgs[1], otherArgs[2]);
		
		Job job = getReviewerCount(conf, otherArgs[0], otherArgs[1]);
		job.waitForCompletion(true);
		
		job = getTopTenReviewer(conf, otherArgs[1], otherArgs[2]);
		job.waitForCompletion(true);
	}

}
