/*package priv.cai.jobapply.hadoop;

import java.io.IOException;
import java.util.TreeMap;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class TopHundredWordsReducer extends
		Reducer<NullWritable, Text, NullWritable, Text> {

	private TreeMap<Integer, Text> repToRecordMap = new TreeMap<Integer, Text>();

	public void reduce(NullWritable key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {
		for (Text value : values) {
			repToRecordMap.put(
					Integer.parseInt(value.toString().split(",")[1].trim()), new Text(
							value));
			if (repToRecordMap.size() > 100) {
				repToRecordMap.remove(repToRecordMap.firstKey());
			}
		}
		for (Text t : repToRecordMap.descendingMap().values()) {
			context.write(NullWritable.get(), t);
		}
	}
}
*/
