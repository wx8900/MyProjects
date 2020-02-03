package priv.helpfulness.rating;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
        
public class HelpfulRatingDriver {
 
 public static void main(String[] args) throws Exception {
    
    Job job = Job.getInstance(new Configuration(), "helpful rating");
    
    job.setMapOutputKeyClass(Text.class);
    job.setMapOutputValueClass(Text.class);
    job.setOutputKeyClass(NullWritable.class);
    job.setOutputValueClass(Text.class);
    
    job.setJarByClass(HelpfulRatingDriver.class);
    job.setMapperClass(HelpfulRatingMapper.class);
    job.setReducerClass(HelpfulRatingReducer.class);
    //job.setCombinerClass(HelpfulRatingReducer.class);
    
    job.setNumReduceTasks(1);
                
    FileInputFormat.addInputPath(job, new Path(args[0]));//"/home/cloudera/Elect_Data_12.txt"
    FileOutputFormat.setOutputPath(job, new Path(args[1]));
        
    job.waitForCompletion(true);
 }
        
}