package zhengli;

import java.io.IOException;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;


public class BasicExam extends Configured implements Tool{

	/**
	 * @param args
         * test
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {

		int res = ToolRunner.run(new Configuration(), new BasicExam(), args);
				
		System.exit(res);
	}
	
	public int run(String[] args) throws Exception {
		
		if (args.length != 2) {
			
			System.out.println("Usage: <in> <out>");
			
			System.err.printf("Usage: %s [generic options] <input> <output> \n", getClass().getSimpleName());
			
			ToolRunner.printGenericCommandUsage(System.err);
			
			System.exit(2);
		}
		
		Configuration conf = new Configuration();
		
		Job job = new Job(conf, " Basic Exam");
		
		job.setJarByClass(BasicExam.class);
		
		job.setMapperClass(FileMapper1.class);
		
		job.setReducerClass(FileReducer1.class);
		
		job.setOutputKeyClass(Text.class);
		
		job.setOutputValueClass(Text.class);
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		job.waitForCompletion(true);
		
		return 0;
	}
	
	// FileMapper1: split the line into words and map the word to count
	public static class FileMapper1 extends Mapper<LongWritable, Text, Text, Text> {
		
		Pattern pattern = Pattern.compile("\\w*");
		
		public void map(LongWritable key, Text values, Context context)
			throws IOException,InterruptedException {
			
			StringTokenizer stringTokenizer = new StringTokenizer(values.toString()," \t\n\r\f\"");
			
			while (stringTokenizer.hasMoreTokens()) {
				
				Matcher matcher = pattern.matcher(stringTokenizer.nextToken());
				
				if (matcher.matches()) {
					
					context.write(new Text(matcher.group(0)), new Text("1"));
				}
				
			}
		}
	}

	public static class FileReducer1 extends Reducer<Text, Text, Text, Text> {
		
		public void reduce(Text key, Iterable<Text> values, Context context)
			throws IOException,InterruptedException {
			
			int wordcont = 0;
			
			for (Text text : values) {
			
				wordcont++;
			}
			
			if (wordcont > 10) {
				
				context.write(key, new Text(wordcont+""));
			}
			
		}
	}
}
