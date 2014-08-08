package zhengli;

import java.io.IOException;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class TopKCountWords extends Configured implements Tool{
	
	public static void main(String[] args) throws Exception {

		int res = ToolRunner.run(new Configuration(), new TopKCountWords(), args);
				
		System.exit(res);
	}
	
	public int run(String[] args) throws Exception {
		
		if (args.length != 2) {
			
			System.out.println("Usage: <in> <out>");
	
			System.err.printf("Usage: %s [generic options] <input> <output> \n", getClass().getSimpleName());
			
			ToolRunner.printGenericCommandUsage(System.err);
			
			System.exit(2);
		}
		
		String temp1 = args[1] + "tmp1";
		
		String temp2 = args[1] + "tmp2";
		
		// config 1
		Configuration conf = new Configuration();
		
		Job job = new Job(conf, " word count");
		
		job.setJarByClass(TopKCountWords.class);
		
		job.setMapperClass(FileMapper1.class);
		
		job.setReducerClass(FileReducer1.class);
		
		job.setOutputKeyClass(Text.class);
		
		job.setOutputValueClass(Text.class);
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		
		FileOutputFormat.setOutputPath(job, new Path(temp1));
		
		if(job.waitForCompletion(true)){		
		
		// config 2
		Configuration conf2 = new Configuration();
		
		Job job2 = new Job(conf2," num words ");
		
		job2.setJarByClass(TopKCountWords.class);
		
		job2.setMapperClass(FileMapper2.class);
		
		job2.setReducerClass(FileReducer2.class);
		
		job2.setOutputKeyClass(Text.class);
		
		job2.setOutputValueClass(Text.class);
		
		FileInputFormat.addInputPath(job2, new Path(temp1));
		
		FileOutputFormat.setOutputPath(job2, new Path(temp2));
		
		job2.setInputFormatClass(KeyValueTextInputFormat.class);
		
		if (job2.waitForCompletion(true)) {
			
			Configuration conf3 = new Configuration();
			
			conf3.set("topK", "400");
			
			Job job3 = new Job(conf3, "top k word set");
			
			job3.setJarByClass(TopKCountWords.class);
			// THE KeyValueTextInputFormat can split the class
			job3.setInputFormatClass(KeyValueTextInputFormat.class);
			
			job3.setMapperClass(FileMapper3.class);
			
			job3.setReducerClass(FileReducer3.class);
			
			job3.setOutputKeyClass(NullWritable.class);
			
			job3.setOutputValueClass(Text.class);
			
			FileInputFormat.addInputPath(job3, new Path(temp2));
			
			FileOutputFormat.setOutputPath(job3, new Path(args[1]));		

			System.exit(job3.waitForCompletion(true) ? 0 : 1);
			}
		}
		
		return 0;
		
	}
	
	// FileMapper1: split the line into words and map the word to count
	public static class FileMapper1 extends Mapper<LongWritable, Text, Text, Text> {
		
		Pattern pattern = Pattern.compile("\\w*");
		
		public void map(LongWritable key, Text values, Context context)
			throws IOException,InterruptedException {
			
			StringTokenizer stringTokenizer = new StringTokenizer(values.toString()
					.toLowerCase(Locale.ENGLISH)," \t\n\r\f\"");
			
			while (stringTokenizer.hasMoreTokens()) {
				
				Matcher matcher = pattern.matcher(stringTokenizer.nextToken());
				
				if (matcher.matches()) {
					
					context.write(new Text(matcher.group(0)), new Text("1"));
				}
				
			}
		}
	}
	
	// FileReduce1: word cont in all
	public static class FileReducer1 extends Reducer<Text, Text, Text, Text> {
		
		public void reduce(Text key, Iterable<Text> values, Context context)
			throws IOException,InterruptedException {
			
			int wordcont = 0;
			
			for (Text text : values) {
				
				if(Integer.parseInt(text.toString()) == 1){

					wordcont++;
					
				}else {
					
					wordcont = wordcont + Integer.parseInt(text.toString());
				}
			}
			
			if (wordcont > 5) {
				
				context.write(key, new Text(wordcont+""));
			}
			
		}
	}
	
	// FileMapper2: count word in map
	public static class FileMapper2 extends Mapper<Text, Text, Text, Text> {
		
		public void map(Text key, Text values, Context context) 
			throws IOException, InterruptedException {
			
			context.write(values, key);
		}
	}
	
	// FileReduce2:count word++ in reduce
	public static class FileReducer2 extends Reducer<Text, Text, Text, Text> {
		
		// Top count word
		/*int K = 10;
		private TreeMap<Integer, String> topMap = new TreeMap<Integer, String>();
		*/
		public void reduce(Text key, Iterable<Text> values, Context context)
			throws IOException,InterruptedException {
			
			StringBuffer numWList = new StringBuffer("");
			
			for (Text text : values) {
				
				numWList.append(text.toString().trim());
				
				numWList.append(" # ");
			}
			
			/*topMap.put(Integer.parseInt(key.toString()), numWList.toString());
			
			if (topMap.size() > K) {
			
				topMap.remove(topMap.firstKey());
				
			}
			
			for (String string : topMap.values()) {
				
				context.write(new Text("result "), new Text(string));
				
			}*/
			context.write(key, new Text(numWList.toString()));
			//context.write(new Text("result : "), new Text(topMap.values().toString()));
			
		}
	}

	public static class FileMapper3 extends Mapper<Text, Text, NullWritable, Text>{
		
		private TreeMap<Integer, String> topKW = new TreeMap<Integer, String>();
		
		//int topK = 100;
		
		public void map(Text key, Text valText, Context context)
			throws IOException,InterruptedException {
			
			int topK = Integer.parseInt(context.getConfiguration().get("topK"));
			
			topKW.put(Integer.parseInt(key.toString()), key.toString()+"\t"+valText.toString());
			
			while (topKW.size() > topK) {
				
				topKW.remove(topKW.firstKey());
			}
		}
		
		@Override
		protected void cleanup(Context context)
			throws IOException, InterruptedException {
			
			for(String string:topKW.values()){
				
				context.write(NullWritable.get(), new Text(string));
			}
		}
	}

	public static class FileReducer3 extends Reducer<NullWritable, Text, NullWritable, Text>{
		
		//public static final int topK = 90;
		
		private TreeMap<Integer, String> topKW = new TreeMap<Integer, String>();
		
		public void reduce(NullWritable key, Iterable<Text> values, Context context)
			throws IOException,InterruptedException {
			
			int topK = Integer.parseInt(context.getConfiguration().get("topK"));
			
			for (Text valText:values){
				
				String[] vString = valText.toString().split("\t");
				
				Integer coun = Integer.parseInt(vString[0]);
				
				topKW.put(coun, valText.toString());
				
				while(topKW.size() > topK){
					
					topKW.remove(topKW.firstKey());
				}
			}
			
			for(String rString:topKW.values()){
				
				context.write(NullWritable.get(), new Text(rString));
			}
		}
	}
}
