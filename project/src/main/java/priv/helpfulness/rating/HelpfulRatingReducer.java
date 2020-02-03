package priv.helpfulness.rating;

import java.io.IOException;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class HelpfulRatingReducer extends Reducer<Text, Text, NullWritable, Text> {

	public void reduce(Text key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {
		for (Text val : values) {
			// sum += Double.parseDouble(val.toString().replaceAll("%", ""));
			context.write(NullWritable.get(), new Text(val.toString()));
		}
	}
}
