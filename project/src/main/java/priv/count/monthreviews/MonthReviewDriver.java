package priv.count.monthreviews;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
        
public class MonthReviewDriver {
 
 public static void main(String[] args) throws Exception {
        
    Job job = Job.getInstance(new Configuration(), "review count based on month and year");
    
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(IntWritable.class);
    
    job.setJarByClass(MonthReviewDriver.class);
    job.setMapperClass(MonthReviewMapper.class);
    job.setReducerClass(MonthReviewReducer.class);
    job.setCombinerClass(MonthReviewReducer.class);
    
    job.setNumReduceTasks(1);
                
    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));
        
    job.waitForCompletion(true);
 }
        
}
