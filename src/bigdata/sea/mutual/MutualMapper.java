package bigdata.sea.mutual;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MutualMapper extends Mapper<Object, Text, Text, IntWritable> {
	private IntWritable one = new IntWritable(1);
	private String userA;
	private String userB;

	@Override
	protected void map(Object key, Text value,
			Mapper<Object, Text, Text, IntWritable>.Context context)
					throws IOException, InterruptedException {
		Configuration conf = context.getConfiguration();
		userA = conf.get(MutualFriendsFinder.USER_A);
		userB = conf.get(MutualFriendsFinder.USER_B);

		String profile[] = value.toString().split("\t");
		String user = profile[0];

		if (profile.length == 2) {
			if (user.equalsIgnoreCase(userA) || user.equalsIgnoreCase(userB)) {
				for (String friend : profile[1].split(",")) {
					context.write(new Text(friend), one);
				}
			}
		}

	}
}
