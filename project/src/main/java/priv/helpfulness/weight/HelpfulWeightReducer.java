package priv.helpfulness.weight;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class HelpfulWeightReducer extends Reducer<Text, Text, Text, Text> {
	private static final Double N = 1689188.0D;
	private Text value = new Text();

	public void reduce(Text key, Iterable<Text> values,
			Reducer<Text, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		DecimalFormat df = new DecimalFormat("00.00");
		StringBuffer sb = new StringBuffer();
		boolean first = true;
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for (Text tweet_id : values) {
			if (map.containsKey(tweet_id)) {
				map.put(tweet_id.toString(), Integer.valueOf(((Integer) map
						.get(tweet_id)).intValue() + 1));
			} else {
				map.put(tweet_id.toString(), Integer.valueOf(1));
			}
		}
		int mapSize = map.size();
		for (String tweet_id : map.keySet()) {
			if (first) {
				first = false;
			} else {
				sb.append(",");
			}
			sb.append(tweet_id
					+ ":"
					+ df.format((1.0D + Math.log10(((Integer) map.get(tweet_id))
							.doubleValue()))
							* Math.log10(N / mapSize)));
		}
		this.value.set(sb.toString());
		context.write(key, this.value);
	}
}