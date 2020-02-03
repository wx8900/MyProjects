package priv.helpfulness.rating;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import priv.entity.Review;

import com.alibaba.fastjson.JSON;

public class HelpfulRatingMapper extends Mapper<Object, Text, Text, Text> {
	private Text outValue = new Text();
	private DecimalFormat df = new DecimalFormat("00.00%");
	private Pattern pattern = Pattern.compile("\\[[0-9]\\d*,[0-9]\\d*\\]");

	public void map(Object key, Text value, Context context)
			throws IOException, InterruptedException {

		Review review = JSON.parseObject(value.toString(), Review.class);
		String helpful = review.getHelpful();
		Matcher matcher = pattern.matcher(helpful);
		if (matcher.matches()) {
			String asin = review.getAsin();
			String reviewerID = review.getReviewerID();

			String number = helpful.substring(1, (helpful.length() - 1));
			String[] helpfulArr = number.split(",");
			double totalYes = Double.parseDouble(helpfulArr[0]);
			int total = Integer.parseInt(helpfulArr[1]);
			String percentage = "";
			if (helpfulArr.length == 2) {
				try{
					if (total == 0.0d) {
						//percentage = "Infinity";//∞   &infin;
						return;
					} else if (total > 0.0d) {
						percentage = df.format(totalYes/total);
					}
				} catch (NumberFormatException e) {
					return;
				}
				
				outValue.set(asin + "\t" + reviewerID + "\t" + percentage);
				
				context.write(new Text(asin), outValue);
			}
		}

	}
}