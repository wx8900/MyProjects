/*package priv.cai.jobapply.hadoop;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import priv.cai.jobapply.constant.StopWords;
import priv.cai.jobapply.constant.Constants;

public class FilterStopWordsMapper extends Mapper<LongWritable, Text, Text, LongWritable> {

	private static final LongWritable ONE = new LongWritable(1);
	private Text word = new Text();
	Pattern pattern = Pattern.compile(Constants.BIG_DATA_PATTERN);

	public void map(LongWritable key, Text value,Context context)
					throws IOException, InterruptedException {
		
		//Review review = JSON.parseObject(value.toString(), Review.class);
		Matcher matcher = pattern.matcher(value.toString());
		String result = matcher.replaceAll(" ").trim();
		String[] array = result.split(" ");
				
		for (String str : array) {
	        if (str.length() >= 2 && StringUtils.isAlpha(str)
					&& !StopWords.is(str)) {
	        	word.set(str);
				context.write(word, ONE);
	        }
		}
	}
}
*/
