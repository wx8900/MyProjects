package priv.review.wordcount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
        
public class ReviewCountDriver {
 
 public static void main(String[] args) throws Exception {
        
    Job job = Job.getInstance(new Configuration(), "review count");
    
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(IntWritable.class);
    
    job.setJarByClass(ReviewCountDriver.class);
    job.setMapperClass(ReviewCountMapper.class);
    job.setReducerClass(ReviewCountReducer.class);
    job.setCombinerClass(ReviewCountReducer.class);
    
    job.setNumReduceTasks(1);
                
    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));
        
    job.waitForCompletion(true);
 }
        
}