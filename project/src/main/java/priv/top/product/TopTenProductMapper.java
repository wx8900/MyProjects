package priv.top.product;

import java.io.IOException;
import java.util.TreeMap;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class TopTenProductMapper extends Mapper<Object, Text, NullWritable, Text> {
	private TreeMap<Integer, Text> mapperMap = new TreeMap<Integer, Text>();

	public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
		String[] arr = value.toString().split(",");

		mapperMap.put(Integer.parseInt(arr[1]), 
				new Text(arr[0] + "," + arr[1]));
		if (mapperMap.size() > 10) {
			mapperMap.remove(mapperMap.firstKey());
		}
	}

	protected void cleanup(Context context) throws IOException, InterruptedException {
		for (Text t : mapperMap.values()) {
			context.write(NullWritable.get(), t);
		}
	}
}
