package priv.top.product;

import java.io.IOException;
import java.util.TreeMap;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class TopTenProductReducer extends Reducer<NullWritable, Text, NullWritable, Text> {

	private static final String comma = ",";
	private TreeMap<Integer, Text> reducerMap = new TreeMap<Integer, Text>();

	public void reduce(NullWritable key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {

		for (Text value : values) {
			String[] arr = value.toString().split(comma);

			if (1 == arr.length) {
				//arr = input.split("\t");
				continue;
			}

			reducerMap.put(Integer.parseInt(arr[1]), 
					new Text(arr[0].toString().concat(comma).concat(arr[1])));
			if (reducerMap.size() > 10) {
				reducerMap.remove(reducerMap.firstKey());
			}
		}
	}

	protected void cleanup(Context context) throws IOException,
			InterruptedException {
		for (Text t : reducerMap.descendingMap().values()) {
			context.write(NullWritable.get(), t);
		}
	}

}
