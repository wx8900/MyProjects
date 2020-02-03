package priv.count.summary;

import java.io.IOException;
import java.util.TreeMap;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class TopTenWordsMapper extends
		Mapper<LongWritable, Text, NullWritable, Text> {

	private TreeMap<Integer, Text> mapperMap = new TreeMap<Integer, Text>();

	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		String[] arr = value.toString().split(",");
		Integer val = Integer.parseInt(arr[1]);
		mapperMap.put(val, new Text(arr[0] + "," + val));
		if (mapperMap.size() > 10) {
			mapperMap.remove(mapperMap.firstKey());
		}
	}

	protected void cleanup(Context context) throws IOException,
			InterruptedException {
		for (Text t : mapperMap.descendingMap().values()) {
			context.write(NullWritable.get(), t);
		}
	}
}
