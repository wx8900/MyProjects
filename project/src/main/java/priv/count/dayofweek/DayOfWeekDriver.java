package priv.count.dayofweek;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
        
public class DayOfWeekDriver {
 
 public static void main(String[] args) throws Exception {
        
    Job job = Job.getInstance(new Configuration(), "review publish day of week");
    
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(IntWritable.class);
    
    job.setJarByClass(DayOfWeekDriver.class);
    job.setMapperClass(DayOfWeekMapper.class);
    job.setReducerClass(DayOfWeekReducer.class);
    job.setCombinerClass(DayOfWeekReducer.class);
    
    job.setNumReduceTasks(1);
                
    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));
        
    job.waitForCompletion(true);
 }
        
}
