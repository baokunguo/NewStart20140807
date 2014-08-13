package zLearningClass;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class LearningClassReadFileExam {

	/**
	 * @param args
	 *            The difference between the two methods is what to use to
	 *            construct a BufferedReader. Method 1 uses InputStreamReader
	 *            and Method 2 uses FileReader. What¡¯s the difference between
	 *            the two classes? From Java Doc, "An InputStreamReader is a
	 *            bridge from byte streams to character streams: It reads bytes
	 *            and decodes them into characters using a specified charset."
	 *            InputStreamReader can handle other input streams than files,
	 *            such as network connections, classpath resources, ZIP files,
	 *            etc. FileReader is "Convenience class for reading character
	 *            files. The constructors of this class assume that the default
	 *            character encoding and the default byte-buffer size are
	 *            appropriate." FileReader does not allow you to specify an
	 *            encoding other than the platform default encoding. Therefore,
	 *            it is not a good idea to use it if the program will run on
	 *            systems with different platform encoding.
	 * 
	 *            Both will work, but what is the difference between
	 *            FileOutputStream and FileWriter? There are a lot of discussion
	 *            on each of those classes, they both are good implements of
	 *            file i/o concept that can be found in a general operating
	 *            systems. However, we don¡¯t care how it is designed, but only
	 *            how to pick one of them and why pick it that way.
	 * 
	 *            If you are familiar with design patterns, FileWriter is a
	 *            typical usage of Decorator pattern actually. I have use a
	 *            simple tutorial to demonstrate the Decorator pattern, since it
	 *            is very important and very useful for many designs.
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		// String filepath =
		// "/media/Document/programm/GitHub/NewStart20140807/githelp/README.md";
		// File file = new File(filepath);
		File filepath = new File(
				"/media/Document/programm/GitHub/NewStart20140807/githelp");
		File file = new File(filepath.getCanonicalPath() + File.separator
				+ "README.md");
		File filewrite = new File(filepath.getCanonicalFile() + File.separator
				+ "LearningFileWriter.txt");

		System.out.println("File name " + file.getName() + "\nFile path "
				+ file.getPath());

		try {
			readFile1(file);
			System.out.println("readFile1 Finished , then readFile2 \t.....\n");
			readFile2(file);
			System.out.println("readFile2 Finished , then file write \t ...\n");
			writeFile1(filewrite);
			System.out
					.println("file write Finished ,then read filewrite \t ...\n");
			readFile1(filewrite);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	// method 1
	private static void readFile1(File in) throws IOException {

		FileInputStream fileInputStream = new FileInputStream(in);
		// construct bufferedreader from INputStreamReader
		// and inputstreamreader can set the decode type
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(fileInputStream, "UTF-8"));

		String line = null;
		int i = 0;
		while ((line = bufferedReader.readLine()) != null) {
			System.out.println(line);
			i++;
			if (i > 5) {
				break;
			}
		}

		bufferedReader.close();
	}

	private static void readFile2(File in) throws IOException {

		// construct bufferedReader from FileReader
		BufferedReader bufferedReader = new BufferedReader(new FileReader(in));

		String line = null;
		int i = 0;
		while ((line = bufferedReader.readLine()) != null) {
			System.out.println(line);
			i = i + 1;
			if (i > 10) {
				break;
			}
		}
		bufferedReader.close();
	}

	public static void writeFile1(File outFile) throws IOException {
		FileOutputStream fileOutputStream = new FileOutputStream(outFile);
		BufferedWriter bufferedWriter = new BufferedWriter(
				new OutputStreamWriter(fileOutputStream));
		for (int i = 0; i < 10; i++) {
			bufferedWriter.write(" here is writing");
			bufferedWriter.append("\t ... this is appending");
			bufferedWriter.newLine();
		}

		bufferedWriter.close();
	}

	public static void writeFile2(File outFile) throws IOException {
		FileWriter fWriter = new FileWriter(outFile);
		for (int i = 0; i < 10; i++) {
			fWriter.write("fwriter is writing \t ....");
			fWriter.append("as");
		}

		fWriter.close();
	}
}
