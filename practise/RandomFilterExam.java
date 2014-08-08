package zhengli;

import java.io.IOException;
import java.util.Random;

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

public class RandomFilterExam extends Configured implements Tool {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		int res = ToolRunner.run(new Configuration(), new RandomFilterExam(), args);
		
		System.exit(res);
	}
	
	public int run(String[] args) throws Exception {
		
		// command input file output-file
		if (args.length != 2) {
			
			System.out.println("Usage: <in> <out>");
			
			System.err.printf("Usage: %s [generic options] <input> <output> \n", getClass().getSimpleName());
			
			ToolRunner.printGenericCommandUsage(System.err);
			
			System.exit(2);
			
			return -1;
		}
		

		Configuration configuration = new Configuration();
		// set the result percent of the whole file in hdfs
		configuration.set("filter_percentage", "30");
		
		Job job = new Job(configuration, " random filter example");
		
		job.setJarByClass(RandomFilterExam.class);
		
		job.setMapperClass(RandomFilterMapper.class);
		
		job.setOutputKeyClass(NullWritable.class);
		
		job.setOutputValueClass(Text.class);
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		System.exit(job.waitForCompletion(true)? 0:1);
		
		return 0;
	}
	
	public static class RandomFilterMapper extends Mapper<Object, Text, NullWritable, Text> {
		
		private Random rands = new Random();
		
		private Double percentage;
		
		protected void setup(Context context) throws IOException,InterruptedException {
			
			String strPercentage = context.getConfiguration().get("filter_percentage");
			
			percentage = Double.parseDouble(strPercentage);
		}
		
		public void map(Object key, Text values, Context context)
			throws IOException,InterruptedException {
			
			if (rands.nextDouble() < percentage) {
				
				context.write(NullWritable.get(), values);
			}
		}
	}

}
