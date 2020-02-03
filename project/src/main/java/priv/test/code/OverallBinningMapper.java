package priv.test.code;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

public class OverallBinningMapper extends
		Mapper<NullWritable, Text, Text, LongWritable> {

	public static final String MULTIPLE_OUTPUTS_OVERALL_1 = "overall1";
	public static final String MULTIPLE_OUTPUTS_OVERALL_2 = "overall2";
	public static final String MULTIPLE_OUTPUTS_OVERALL_3 = "overall3";
	public static final String MULTIPLE_OUTPUTS_OVERALL_4 = "overall4";
	public static final String MULTIPLE_OUTPUTS_OVERALL_5 = "overall5";

	private MultipleOutputs<Text, LongWritable> mos = null;

	public void setup(Context context) {
		mos = new MultipleOutputs<Text, LongWritable>(context);
	}

	public void map(NullWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		String[] arr = value.toString().split(",");
		String overrallValue = arr[0].trim();
		String summaryValue = arr[1].trim();
		String countValue = arr[2].trim();
		int overall = Float.valueOf(overrallValue).intValue();
		
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
}
