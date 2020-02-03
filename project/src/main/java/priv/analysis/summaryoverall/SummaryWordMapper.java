package priv.analysis.summaryoverall;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import priv.entity.Review;
import priv.entity.StopWords;

import com.alibaba.fastjson.JSON;

public class SummaryWordMapper extends Mapper<Object, Text, Text, IntWritable> {
	private final static IntWritable ONE = new IntWritable(1);
	Pattern pattern = Pattern.compile("[\\p{Punct}\\d]");
	private Text word = new Text();

	public void map(Object key, Text value, Context context)
			throws IOException, InterruptedException {

		Review review = JSON.parseObject(value.toString(), Review.class);
		Matcher matcher = pattern.matcher(review.getSummary());
		String summary = matcher.replaceAll(" ").trim();
		String[] summaryArray = summary.split(" ");
		String overall = review.getOverall().trim();

		for (String summaryWord : summaryArray) {
			if (summaryWord != null) {
				String summ = summaryWord.trim();
				if (summ.length() >= 2 && StringUtils.isAlpha(summ)
						&& !StopWords.is(summ)) {
					word.set(overall +","+ summ.toLowerCase());
					context.write(word, ONE);
				}
			}
		}
	}
}