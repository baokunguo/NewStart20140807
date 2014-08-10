package zhengli;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

public class StackXMLPaserExam {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		
		// Get the sample string
		String filepath = "/media/Document/programm/mahout/data/stackexchange/092011DBAdminMeta/users.xml";
		
		System.out.println("Start Reading ...");
		
		File file = new File(filepath);
		
		BufferedReader bfReader = new BufferedReader(new FileReader(file));
		
		String tempstring = null;
		
		int line = 1;
		
		while (line < 5 && (tempstring = bfReader.readLine()) != null) {		
			
			System.out.println(tempstring);
			
			line++;
		}
		
		bfReader.close();
		
		// try parse xml 
		HashMap<String, String> reHashMap = new HashMap<String,String>();
		
		String sampleString = tempstring.trim().substring(5, tempstring.length()-4);
		
		String [] result = sampleString.split("\"");
		
		for(int i = 0; i < result.length-2; i = i+2){
			
			reHashMap.put(result[i].trim(), result[i+1]);
			
			System.out.print(result[i].trim()+"  " +result[i+1]+"\n");
			
			if (result[i].trim().equals("Body=")) {
				
				System.out.println(result[i+1]);
			}
			
		}
		
	}

}
