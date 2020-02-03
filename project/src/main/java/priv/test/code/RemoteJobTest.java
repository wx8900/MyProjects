package priv.test.code;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.map.TokenCounterMapper;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.reduce.IntSumReducer;
 
/**
 * @author Krisztian_Horvath 
 */ 
public class RemoteJobTest { 
 
    public static void main(String[] args) throws Exception { 
 
        System.getProperties().put("hadoop.home.dir", "d:\\Projects\\hadoop-test\\"); 
 
        Configuration conf = new Configuration(); 
        conf.set("fs.defaultFS", "hdfs://localhost:8020"); 
        conf.set("mapred.job.tracker", "localhost:8021"); 
        conf.set("yarn.resourcemanager.address", "localhost:8032"); 
 
        Job job = new Job(conf, "word count1"); 
        job.setJarByClass(RemoteJobTest.class); 
        job.setMapperClass(TokenCounterMapper.class); 
        job.setReducerClass(IntSumReducer.class); 
        job.setOutputKeyClass(Text.class); 
        job.setOutputValueClass(IntWritable.class); 
 
        FileInputFormat.addInputPath(job, new Path("wc")); 
        FileOutputFormat.setOutputPath(job, new Path("wc-out")); 
 
        job.waitForCompletion(true); 
    } 
}


