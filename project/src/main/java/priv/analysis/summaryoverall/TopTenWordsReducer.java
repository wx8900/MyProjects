package priv.analysis.summaryoverall;

import java.io.IOException;
import java.util.TreeMap;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

public class TopTenWordsReducer extends
		Reducer<NullWritable, Text, NullWritable, Text> {
	
	public static final String MULTIPLE_OUTPUTS_OVERALL_1 = "overall1";
	public static final String MULTIPLE_OUTPUTS_OVERALL_2 = "overall2";
	public static final String MULTIPLE_OUTPUTS_OVERALL_3 = "overall3";
	public static final String MULTIPLE_OUTPUTS_OVERALL_4 = "overall4";
	public static final String MULTIPLE_OUTPUTS_OVERALL_5 = "overall5";

	private MultipleOutputs<NullWritable, Text> mos = null;

	private TreeMap<Integer, Text> repToRecordMap = new TreeMap<Integer, Text>();
	
	public void setup(Context context) {
		mos = new MultipleOutputs<NullWritable, Text>(context);
	}
	
	public void getOutPut (NullWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		String[] arr = value.toString().split(",");
		String overrallValue = arr[0].trim();
		String summaryValue = arr[1].trim();
		String countValue = arr[2].trim();
		Integer overall = 0;
		if(overrallValue.contains(".")) {
			String a = overrallValue.split("\\.")[0];
			overall = Integer.parseInt(a);
		} else {
			overall = Integer.parseInt(overrallValue);
		}
		
		switch (overall) {
		case 1:
			mos.write(MULTIPLE_OUTPUTS_OVERALL_1, summaryValue + ","
					+ overrallValue, countValue, MULTIPLE_OUTPUTS_OVERALL_1
					+ "/part");
			break;
		case 2:
			mos.write(MULTIPLE_OUTPUTS_OVERALL_2, summaryValue + ","
					+ overrallValue, countValue, MULTIPLE_OUTPUTS_OVERALL_2
					+ "/part");
			break;
		case 3:
			mos.write(MULTIPLE_OUTPUTS_OVERALL_3, summaryValue + ","
					+ overrallValue, countValue, MULTIPLE_OUTPUTS_OVERALL_3
					+ "/part");
			break;
		case 4:
			mos.write(MULTIPLE_OUTPUTS_OVERALL_4, summaryValue + ","
					+ overrallValue, countValue, MULTIPLE_OUTPUTS_OVERALL_4
					+ "/part");
			break;
		case 5:
			mos.write(MULTIPLE_OUTPUTS_OVERALL_5, summaryValue + ","
					+ overrallValue, countValue, MULTIPLE_OUTPUTS_OVERALL_5
					+ "/part");
			break;
		}
	}

	public void cleanup(Context context) {
		try {
			mos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void reduce(NullWritable key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {
		for (Text value : values) {
			String out = value.toString().split(",")[2];
			Integer outKey = Integer.parseInt(out.trim());
			repToRecordMap.put(outKey, new Text(value));
			if (repToRecordMap.size() > 10) {
				repToRecordMap.remove(repToRecordMap.firstKey());
			}
		}
		for (Text t : repToRecordMap.descendingMap().values()) {
			getOutPut(NullWritable.get(), t, context);
		}
	}
}
