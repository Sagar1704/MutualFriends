package bigdata.sea.mutual;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MutualReducer extends Reducer<Text, IntWritable, Text, Text>{

	@Override
	protected void reduce(Text key, Iterable<IntWritable> values,
			Reducer<Text, IntWritable, Text, Text>.Context context)
					throws IOException, InterruptedException {
		
	}
}
