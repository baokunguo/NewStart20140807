package zhengli;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class GrepExam extends Configured implements Tool {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		int res = ToolRunner.run(new Configuration(), new GrepExam(), args);

		System.exit(res);

	}

	public int run(String args[]) throws Exception {

		if (args.length != 2) {

			System.out.println("Usage: <in> <out>");

			System.err.printf(
					"Usage: %s [generic options] <input> <output> \n",
					getClass().getSimpleName());

			ToolRunner.printGenericCommandUsage(System.err);

			System.exit(2);
		}
		
		Configuration conf = new Configuration();
		
		conf.set("mapregex", "\\w*");
		
		Job job = new Job(conf, "Grep function");
		
		job.setJarByClass(GrepExam.class);
		
		job.setMapperClass(GrepMapper.class);
		
		job.setOutputKeyClass(NullWritable.class);
		
		job.setOutputValueClass(Text.class);
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		job.waitForCompletion(true);

		return 0;
	}

	public static class GrepMapper extends
			Mapper<Object, Text, NullWritable, Text> {

		private String mapRegex = null;

		public void setup(Context context) throws IOException,
				InterruptedException {

			mapRegex = context.getConfiguration().get("mapregex");

		}

		public void map(Object key, Text valueText, Context context)
				throws IOException, InterruptedException {

			if (valueText.toString().matches(mapRegex)) {

				context.write(NullWritable.get(), valueText);
			}
		}
	}

}
