package bigdata.sea.mutual;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MutualReducer extends Reducer<Text, IntWritable, Text, Text> {
	private StringBuilder mutualFriends;

	public MutualReducer() {
		mutualFriends = new StringBuilder();
	}

	@Override
	protected void reduce(Text key, Iterable<IntWritable> values,
			Reducer<Text, IntWritable, Text, Text>.Context context)
					throws IOException, InterruptedException {
		int sum = 0;
		for (IntWritable val : values) {
			sum += val.get();
		}

		if (sum > 1) {
			mutualFriends.append(key + ",");
		}
	}

	@Override
	protected void cleanup(
			Reducer<Text, IntWritable, Text, Text>.Context context)
					throws IOException, InterruptedException {
		Configuration conf = context.getConfiguration();
		context.write(
				new Text(conf.get(MutualFriendsFinder.USER_A) + ", "
						+ conf.get(MutualFriendsFinder.USER_B)),
				new Text(mutualFriends.substring(0,
						mutualFriends.length() - 1)));
	}
}
