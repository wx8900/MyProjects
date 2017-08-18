package priv.cai.jobapply.hadoop;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.zip.GZIPInputStream;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.filecache.DistributedCache;

public class WordSumReducer extends
		Reducer<Text, LongWritable, Text, LongWritable> {

	private LongWritable outvalue = new LongWritable();
	private HashMap<String, String> wordCache = new HashMap<String, String>();

	public void setup(Context context) {
		try {
			wordCache.clear();

			Path[] files = DistributedCache.getLocalCacheFiles(context.getConfiguration());
			if (files == null || files.length == 0) {
				throw new RuntimeException("Words information is not set in DistributedCache !!!");
			}

			for (Path p : files) {
				BufferedReader rdr = new BufferedReader(new InputStreamReader(
						new GZIPInputStream(new FileInputStream(new File(
								p.toString())))));

				String line;
				while ((line = rdr.readLine()) != null) {
					wordCache.put(line, line);
				}
			}

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void reduce(Text key, Iterable<LongWritable> values, Context context)
			throws IOException, InterruptedException {
		int sum = 0;
		if (wordCache.containsKey(key.toString())) {
			for (LongWritable value : values) {
				sum += value.get();
			}
		}
		outvalue.set(sum);

		context.write(key, outvalue);
	}
}
