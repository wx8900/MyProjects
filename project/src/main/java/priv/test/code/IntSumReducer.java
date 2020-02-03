package priv.test.code;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class IntSumReducer extends Reducer<Text, Text, Text, Text> {
	private final static Integer ONE = new Integer(1);
	private Text outValue = new Text();
	Map<Double, Integer> map = new HashMap<Double, Integer>();

	public void reduce(Text key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {
		StringBuilder sb = new StringBuilder();
		for (Text text : values) {
			if (text == null)
				continue;
			String[] arr = text.toString().split("\\,");
			for (String s : arr) {
				if (s == null)
					continue;
				String[] oneRating = s.split("\\:");
				if (oneRating != null && oneRating.length > 0) {
					Double oneKey = Double.parseDouble(oneRating[0]);
					if (map.containsKey(oneKey)) {
						Integer value = map.get(oneKey);
						map.put(oneKey, (value + 1));
					} else {
						map.put(oneKey, ONE);
					}
				} else {
					continue;
				}
			}
		}

		for (Map.Entry<Double, Integer> entry : map.entrySet()) {
			sb.append(entry.getKey().toString()).append(":").append(entry.getValue().toString()).append(",");
		}

		outValue.set(sb.substring(0, (sb.length() - 1)));
		context.write(key, outValue);
	}
}
