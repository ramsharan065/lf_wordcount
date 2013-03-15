package wordcount;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * It gives top 5 words.
 * @author ramsharan
 *
 */
public class Counter {
	private File readFile_;
	private File outputFile_;
	private final int NUMBEROFOUTPUT = 5;//top 5
	private ArrayList<WordPlusCounter> wordPlusCounterList_;
	
	/**
	 * 
	 * @param readFile
	 * @param outputFile
	 */
	public Counter(File readFile, File outputFile){
		readFile_=readFile;
		outputFile_=outputFile;
		wordPlusCounterList_ = new ArrayList<>();
	}
	/**
	 * 
	 * @throws IOException
	 */
	public void operate() throws IOException{
		BufferedReader fileReader = new BufferedReader(new FileReader(readFile_));
		String line;
		Pattern pattern = Pattern.compile("\\w+");
		Matcher matcher;
		String word;
		while((line=fileReader.readLine())!=null){
			matcher = pattern.matcher(line);
			while(matcher.find()){
				word = matcher.group();
				insertWord(word);
			}
		}
		
		Collections.sort(wordPlusCounterList_, new Comparator<WordPlusCounter>() {			@Override
			public int compare(WordPlusCounter o1, WordPlusCounter o2) {
				return (o2.counter-o1.counter);//for descending order
			}
		});
		
		fileReader.close();
		BufferedWriter fileWriter = new BufferedWriter(new FileWriter(outputFile_));
		
		for(int i=0;i<wordPlusCounterList_.size()&&i<NUMBEROFOUTPUT;i++){
			fileWriter.write(wordPlusCounterList_.get(i).word+" - "+wordPlusCounterList_.get(i).counter+"\n");
		}
		fileWriter.close();
	}
	/**
	 * 
	 * @param word
	 */
	protected void insertWord(String word){
		WordPlusCounter wordPlusCounter;
		boolean isFound=false;
		word=word.toLowerCase();
		for(int i=0;i<wordPlusCounterList_.size();i++){
			wordPlusCounter = wordPlusCounterList_.get(i);
			if(word.equals(wordPlusCounter.word)){
				wordPlusCounter.counter++;
				isFound=true;
				break;
			}
		}
		if(!isFound){
			wordPlusCounterList_.add(new WordPlusCounter(word));
		}
	}
	
}
/**
 * 
 * @author ramsharan
 *
 */
class WordPlusCounter {
	public String word;
	public int counter;
	public WordPlusCounter(String word){
		this.word = word;
		counter = 1;
	}
}
