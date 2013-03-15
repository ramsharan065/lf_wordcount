package starting;

import java.io.File;
import java.io.IOException;

import wordcount.Counter;

public class Main {
	public static void main(String[] args) throws IOException{
		File file = new File("inputFile.txt");
		File file2 = new File("outputFile.txt");
		Counter counter = new Counter(file, file2);
		counter.operate();
	}
}
