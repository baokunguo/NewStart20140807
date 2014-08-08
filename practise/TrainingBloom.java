package zhengli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.util.Hash;
import org.apache.hadoop.util.bloom.BloomFilter;
import org.apache.hadoop.util.bloom.Key;

public class TrainingBloom {
	
	public static int getOptimalBloomFilterSize(int numRecords, float falsePostRate) {
		
		int size = (int)(-numRecords *(float) Math.log(falsePostRate) / Math.pow(Math.log(2), 2));
		
		return size;
	}
	
	public static int getOptimalk(float numMembers, float vectorSize) {
		
		return (int)Math.round(vectorSize / numMembers*Math.log(2));
	}

	public static void main(String[] args) throws IOException {
		
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
		
		
	}

