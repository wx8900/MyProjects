package priv.analysis.overallrating.average;

import java.io.IOException;
import java.text.DecimalFormat;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class AverageSumReducer extends Reducer<Text, Text, Text, Text> {
	private DecimalFormat df = new DecimalFormat("0.0");

	public void reduce(Text key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {
		int totalReviews = 0;
		double points = 0.0d;
		for (Text text : values) {
			String[] arr = text.toString().split("\\,");
			for (String s : arr) {
				if (s == null)
					continue;
				String[] oneRating = s.split("\\:");
				if (oneRating != null && oneRating.length > 0) {
					if (oneRating.length != 2)
						continue;
					int reviews = Integer.parseInt(oneRating[1]);
					points += Double.parseDouble(oneRating[0]) * reviews;
					totalReviews += reviews;
				} else {
					continue;
				}
			}
		}

		String avg = df.format((points / totalReviews));
		context.write(key, new Text(avg));
	}
}
