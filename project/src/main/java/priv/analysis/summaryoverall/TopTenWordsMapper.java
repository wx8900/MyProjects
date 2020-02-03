package priv.analysis.summaryoverall;

import java.io.IOException;
import java.util.TreeMap;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class TopTenWordsMapper extends Mapper<Object, Text, NullWritable, Text> {
	TreeMap<Integer, Text> mapperMap = new TreeMap<Integer, Text>();

	public void map(Object key, Text value, Context context)
			throws IOException, InterruptedException {
		
		String out = value.toString().split(",")[2];
		Integer outKey = Integer.parseInt(out.trim());
		
		mapperMap.put(outKey, new Text(value.toString()));
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
