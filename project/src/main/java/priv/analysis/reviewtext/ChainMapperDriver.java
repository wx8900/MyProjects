package priv.analysis.reviewtext;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.filecache.DistributedCache;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class ChainMapperDriver {
	
	public static void main(String[] args) throws Exception {

		Configuration conf = new Configuration();
		conf.set("mapred.textoutputformat.ignoreseparator", "true");
		conf.set("mapred.textoutputformat.separator", " , ");

		String[] otherArgs = new GenericOptionsParser(conf, args)
				.getRemainingArgs();
		if (otherArgs.length != 4) {
			System.out
					.println("Usage: priv.analysis.reviewtext.ChainMapperDriver <in1> <in2> <out1> <out2>");
			System.exit(2);
		}
		
		FileSystem hdfs = FileSystem.get(conf);
		Path path = new Path(otherArgs[2]);
		hdfs.delete(path, true);
		path = new Path(otherArgs[3]);
		hdfs.delete(path, true);

		Job job = new Job(conf, "positive or negative word count");
		job.setJarByClass(ChainMapperDriver.class);
		job.setMapperClass(FilterStopWordsMapper.class);
		job.setReducerClass(WordSumReducer.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(LongWritable.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(LongWritable.class);

		job.setNumReduceTasks(1);

		FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
		FileOutputFormat.setOutputPath(job, new Path(otherArgs[2]));
		
		// Add the user files to the DistributedCache
		FileStatus[] userFiles = FileSystem.get(conf).listStatus(new Path(otherArgs[1]));
		for (FileStatus status : userFiles) {
			//job.addCacheFile(status.getPath().toUri());
			DistributedCache.addCacheFile(status.getPath().toUri(),job.getConfiguration());
		}

		job.waitForCompletion(true);

		Job job2 = new Job(conf, "top 100 positive or negative reviews words");
		job2.setJarByClass(ChainMapperDriver.class);
		job2.setMapperClass(TopHundredWordsMapper.class);
		job2.setReducerClass(TopHundredWordsReducer.class);
		job2.setMapOutputKeyClass(NullWritable.class);
		job2.setMapOutputValueClass(Text.class);
		job2.setOutputKeyClass(NullWritable.class);
		job2.setOutputValueClass(Text.class);

		job2.setNumReduceTasks(1);

		FileInputFormat.addInputPath(job2, new Path(otherArgs[2]));
		FileOutputFormat.setOutputPath(job2, new Path(otherArgs[3]));

		job2.waitForCompletion(true);
	}


}
