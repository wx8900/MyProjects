package priv.analysis.overallrating.average;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class AverageRatingDriver {

	public static void main(String[] args) throws Exception {

		Configuration conf = new Configuration();
		conf.set("mapred.textoutputformat.ignoreseparator", "true");
		conf.set("mapred.textoutputformat.separator", " , ");

		String[] otherArgs = new GenericOptionsParser(conf, args)
				.getRemainingArgs();
		if (otherArgs.length != 4) {
			System.out
					.println("Usage: Driver, error in priv.analysis.overallrating.average.AverageRatingDriver <in> <out> !!!");
			System.exit(2);
		}
		
		FileSystem hdfs = FileSystem.get(conf);
		Path path = new Path(otherArgs[1]);
		hdfs.delete(path, true);
		path = new Path(otherArgs[2]);
		hdfs.delete(path, true);
		path = new Path(otherArgs[3]);
		hdfs.delete(path, true);

		Job job = new Job(conf, "rating count");
		job.setJarByClass(AverageRatingDriver.class);
		job.setMapperClass(FilterRatingMapper.class);
		// job.setCombinerClass(IntSumCombiner.class);
		job.setReducerClass(IntSumReducer.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);

		job.setNumReduceTasks(1);

		FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
		FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));

		job.waitForCompletion(true);

		Job job2 = new Job(conf, "average rating of overall");
		job2.setJarByClass(AverageRatingDriver.class);
		job2.setMapperClass(AverageRatingMapper.class);
		job2.setReducerClass(AverageSumReducer.class);
		job2.setMapOutputKeyClass(Text.class);
		job2.setMapOutputValueClass(Text.class);
		job2.setOutputKeyClass(Text.class);
		job2.setOutputValueClass(Text.class);

		job2.setNumReduceTasks(1);

		FileInputFormat.addInputPath(job2, new Path(otherArgs[1]));
		FileOutputFormat.setOutputPath(job2, new Path(otherArgs[2]));

		job2.waitForCompletion(true);
		
		Job job3 = new Job(conf, "distribution count of rating");
		job3.setJarByClass(AverageRatingDriver.class);
		job3.setMapperClass(DistributionCountMapper.class);
		job3.setReducerClass(DistributionCountReducer.class);
		job3.setMapOutputKeyClass(Text.class);
		job3.setMapOutputValueClass(IntWritable.class);
		job3.setOutputKeyClass(Text.class);
		job3.setOutputValueClass(IntWritable.class);

		job3.setNumReduceTasks(1);

		FileInputFormat.addInputPath(job3, new Path(otherArgs[2]));
		FileOutputFormat.setOutputPath(job3, new Path(otherArgs[3]));

		job3.waitForCompletion(true);
	}

}
