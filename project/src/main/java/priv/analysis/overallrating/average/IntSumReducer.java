package priv.analysis.overallrating.average;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

//import priv.entity.Rating;
import priv.entity.Review;

import com.alibaba.fastjson.JSON;

public class IntSumReducer extends Reducer<Text, Text, Text, Text> {
	private final static Integer ONE = new Integer(1);
	private Text outValue = new Text();

	public void reduce(Text key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {
		Map<Double, Integer> map = new HashMap<Double, Integer>(5);
//		map.put(Rating.Rating1.getIndex(), 0);
//		map.put(Rating.Rating2.getIndex(), 0);
//		map.put(Rating.Rating3.getIndex(), 0);    
//		map.put(Rating.Rating4.getIndex(), 0);
//		map.put(Rating.Rating5.getIndex(), 0);
		
		StringBuilder sb = new StringBuilder();
		for (Text text : values) {
			if (text == null)
				continue;
			Review rev = JSON.parseObject(text.toString(), Review.class);
			String overall = rev.getOverall();
			if (overall != null && overall.length() > 0) {
				Double overallDou = Double.parseDouble(overall);
				if (map.containsKey(overallDou)) {
					Integer value = map.get(overallDou);
					map.put(overallDou, (value + 1));
				} else {
					map.put(overallDou, ONE);
				}
			} else {
				continue;
			}
		}

		for (Map.Entry<Double, Integer> entry : map.entrySet()) {
			sb.append(entry.getKey().toString()).append(":")
					.append(entry.getValue().toString()).append(",");
		}

		outValue.set(sb.substring(0, (sb.length() - 1)));
		context.write(key, outValue);
	}
}
