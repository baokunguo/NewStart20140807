package zhengli;

import TFIDFExample.MRDPUtils;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.zip.GZIPInputStream;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.util.Hash;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.util.bloom.BloomFilter;
import org.apache.hadoop.util.bloom.Key;

public class BloomExam {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {

		Path inputFilePath = new Path(args[0]);
		
		int numMembers = Integer.parseInt(args[1]);
		
		float falsePosRate = Float.parseFloat(args[2]);
		
		Path bfFilePat = new Path(args[3]);
		
		// Calculate our vector size and optimal K value		
		int vectorSize = TrainingBloom.getOptimalBloomFilterSize(numMembers,falsePosRate);
		
		int nbHash = TrainingBloom.getOptimalk(numMembers,vectorSize);
		
		// Create new Bloom filter
		BloomFilter filter = new BloomFilter(vectorSize, nbHash, Hash.MURMUR_HASH);
		
		System.out.println("Training Bloom filter of size" + vectorSize
				+ "with" +nbHash + "hash function" + numMembers 
				+ "approximate number of records, and " + falsePosRate
				+ "false positive rate");
		
		// open file for read
		String line = null;
		
		int numElements = 0;
		
		FileSystem fSystem = FileSystem.get(new Configuration());
		
		for(FileStatus status:fSystem.listStatus(inputFilePath)) {
			
			BufferedReader rdrBufferedReader = 
					new BufferedReader(
							new InputStreamReader(new GZIPInputStream(fSystem.open(status.getPath()))));
			
			System.out.println("Reading" + status.getPath());
			
			while ((line = rdrBufferedReader.readLine()) != null) {
				
				filter.add(new Key(line.getBytes()));
				
				++numElements;
			}
			
			rdrBufferedReader.close();
		}
		
		System.out.println("Trained Bloom filter with" + numElements + "entries");
		
		System.out.println("Serializing Bloom filter to HDFS at" + bfFilePat);
		
		FSDataOutputStream strm = fSystem.create(bfFilePat);
		
		filter.write(strm);
		
		strm.flush();
		
		strm.close();
		
		System.exit(0);
	}
	
	public static class BloomFilteringMapper extends Mapper<Object, Text, Text, NullWritable> {
		
		private BloomFilter filter = new BloomFilter();
		
		protected void setup(Context context) throws IOException,InterruptedException {
			
			// Get file from the DistributedCache
			URI[] files = DistributedCache.getCacheFiles(context.getConfiguration());
			
			System.out.println("Reading bloom filter from: " + files[0].getPath());
			
			// open local file for read
			DataInputStream strm = new DataInputStream(new FileInputStream(files[0].getPath()));
			
			filter.readFields(strm);
			
			strm.close();
			
		}
		
		public void map(Object key, Text value, Context context)
			throws IOException, InterruptedException {
			
			Map<String, String> parsed = MRDPUtils.transformXmlToMap(value.toString());
			
			// Get the value for the comment
			String comment = parsed.get("Text");
			
			StringTokenizer tokenizer = new StringTokenizer(comment);
			
			while (tokenizer.hasMoreTokens()) {
				
				String word = tokenizer.nextToken();
				
				if (filter.membershipTest(new Key(word.getBytes()))) {
					
					context.write(value, NullWritable.get());
					
					break;
				}
				
			}
			
		}
	}
}
