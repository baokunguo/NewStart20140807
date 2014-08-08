package zhengli;

import java.io.IOException;
import java.util.HashSet;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileSplit;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.StringUtils;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;


public class WordDocIdExam extends Configured implements Tool{

	/**
	 * @param 
	 * input 
	 * output word,documentsList
	 */
	
	public int run(String[] args) throws Exception {
		
		if (args.length < 2) {
			
			System.out.println("Word DocId usage: WordDocId <input> <output>");
			
			ToolRunner.printGenericCommandUsage(System.out);
			
			System.out.println("");
			
			return -1;
		}
		
		Configuration conf = new Configuration();
		
		Job job = new Job(conf, "WordDoc id ");
		
		job.setJarByClass(WordDocIdExam.class);
		
		job.setMapperClass(WordMap.class);
		
		job.setReducerClass(WordReduce.class);
		
		job.setOutputKeyClass(Text.class);
		
		job.setOutputValueClass(Text.class);
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		job.waitForCompletion(true);
		
		return 0;
	}
	
	public static void main(String[] args) throws Exception {
		
		int res = ToolRunner.run(new Configuration(), new WordDocIdExam(), args);
		
		System.exit(res);
	}

	
	public static class WordMap extends Mapper<LongWritable, Text, Text, Text> {
		
		private Text documentId;
		
		private Text word = new Text();
		
		protected void setup(Context context) {
		
			String filename = ((FileSplit) context.getInputSplit()).getPath().getName();
			
			documentId = new Text(filename);
		}
		
		protected void map(LongWritable key, Text value, Context context)
			throws IOException,InterruptedException	{
			
			for(String token:StringUtils.split(value.toString())) {
			
				word.set(token);
				
				context.write(word, documentId);
			}
		}
	}
	
	public static class WordReduce extends Reducer<Text, Text, Text, Text> {
		
		private Text docIdText = new Text();
		
		public void reduce(Text key, Iterable<Text> values, Context context)
			throws IOException,InterruptedException {
			
			HashSet<Text> uniqueDoc = new HashSet<Text>();
			
			for (Text doc : values) {
				
				uniqueDoc.add(new Text(docIdText));
			}
			
			docIdText.set(new Text(StringUtils.join(", ", uniqueDoc)));
			
			context.write(key, docIdText);
		}
	}
}
