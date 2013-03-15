package starting;

import java.io.File;
import java.io.IOException;

import wordcount.Counter;
/**
 * 
 * @author ramsharan
 *
 */
public class Main {
	/**
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException{
		File file = new File("inputFile.txt");
		File file2 = new File("outputFile.txt");
		Counter counter = new Counter(file, file2);
		counter.operate();
	}
}
