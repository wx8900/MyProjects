package priv.analysis.summaryoverall;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class SummaryChainJobDriver {
 
 public static void main(String[] args) throws Exception {
	 
	 Configuration conf = new Configuration();
		conf.set("mapred.textoutputformat.ignoreseparator", "true");
		conf.set("mapred.textoutputformat.separator", " , ");

		String[] otherArgs = new GenericOptionsParser(conf, args)
				.getRemainingArgs();
		if (otherArgs.length != 3) {
			System.out
					.println("Usage: Driver, error in priv.analysis.summaryoverall.ChainMapperDriver <in> <out> <out> !!!");
			System.exit(2);
		}
		
		FileSystem hdfs = FileSystem.get(conf);
		Path path = new Path(otherArgs[1]);
		hdfs.delete(path, true);
		path = new Path(otherArgs[2]);
		hdfs.delete(path, true);

		Job job = new Job(conf, "overall count");
		job.setJarByClass(SummaryChainJobDriver.class);
		job.setMapperClass(SummaryWordMapper.class);
		//job.setCombinerClass(SummaryWordReducer.class);
		//job.setPartitionerClass(OverallPartitioner.class);
		job.setReducerClass(SummaryWordReducer.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);

		job.setNumReduceTasks(1);

		FileInputFormat.addInputPath(job, new Path(otherArgs[0]));//"/home/cloudera/Elect_Data_15.txt"
		FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));//"/home/cloudera/out1"

		job.waitForCompletion(true);

		Job job2 = new Job(conf, "average rating of overall");
		job2.setJarByClass(SummaryChainJobDriver.class);
		job2.setMapperClass(TopTenWordsMapper.class);
		job2.setReducerClass(TopTenWordsReducer.class);
		job2.setMapOutputKeyClass(NullWritable.class);
		job2.setMapOutputValueClass(Text.class);
		job2.setOutputKeyClass(NullWritable.class);
		job2.setOutputValueClass(Text.class);

		job2.setNumReduceTasks(1);
		
		FileInputFormat.addInputPath(job2, new Path(otherArgs[1]));
		FileOutputFormat.setOutputPath(job2, new Path(otherArgs[2]));
		
		MultipleOutputs.addNamedOutput(job2, TopTenWordsReducer.MULTIPLE_OUTPUTS_OVERALL_1, 
				TextOutputFormat.class, Text.class, LongWritable.class);

		MultipleOutputs.addNamedOutput(job2, TopTenWordsReducer.MULTIPLE_OUTPUTS_OVERALL_2, 
				TextOutputFormat.class, Text.class, LongWritable.class);
		
		MultipleOutputs.addNamedOutput(job2, TopTenWordsReducer.MULTIPLE_OUTPUTS_OVERALL_3 , 
				TextOutputFormat.class, Text.class, LongWritable.class);

		MultipleOutputs.addNamedOutput(job2, TopTenWordsReducer.MULTIPLE_OUTPUTS_OVERALL_4, 
				TextOutputFormat.class, Text.class, LongWritable.class);
		
		MultipleOutputs.addNamedOutput(job2, TopTenWordsReducer.MULTIPLE_OUTPUTS_OVERALL_5 , 
				TextOutputFormat.class, Text.class, LongWritable.class);
		
		MultipleOutputs.setCountersEnabled(job2, true);

		job2.waitForCompletion(true);
 }
        
}