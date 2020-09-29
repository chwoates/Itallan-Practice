import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class WordLists {
	
	private ArrayList <NewWord> newWords = new ArrayList<NewWord>();
	private NewWord currentWord = new NewWord("","","","","");
	private int wordDelayTime = 1000;
	private int blankDelayTime = 2000;
	private int lastWordNum = 4;
	private int wordCount = 0;
	private NewWord finito = new NewWord("Finito", "", "", "", "");
	
	public WordLists(String filename){
		
		try{
			File myFile = new File(filename);
			FileReader fileReader = new FileReader(myFile);
			BufferedReader reader = new BufferedReader(fileReader);
			String line = null;
			
			wordDelayTime = Integer.parseInt(reader.readLine().trim());	//read in the delay times for the game
			blankDelayTime = Integer.parseInt(reader.readLine().trim());
			
			while((line = reader.readLine()) != null){					//read in the words, meaning, 3 synonyms
				
				String delims = ",";
				String[] tokens = line.split(delims);
				newWords.add(new NewWord(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4]));
			}
			reader.close();
			
		} 
		catch (IOException e) { e.printStackTrace();}	
	}
	
	public NewWord getNextWord() {//  Assign the next word weighted by the difficulty

		Random rand = new Random();
		int nextWordNum = 0;
		boolean nextWordDone = false;

		if (wordCount == (newWords.size())) {
			return finito;
		}
		else {

			while (!nextWordDone) {
				nextWordNum = rand.nextInt(newWords.size());

				if (!newWords.get(nextWordNum).wordUsed) {
					nextWordDone = true;
				}
			}
			nextWordDone = false;
			newWords.get(nextWordNum).wordUsed = true;
			++wordCount;
			currentWord = newWords.get(nextWordNum);
			return newWords.get(nextWordNum);
		}
	}
	
	public int getWordDelayTime(){
		return wordDelayTime;
	}
	
	public int getBlankDelayTime(){
		return blankDelayTime;
	}
	
	public int getWordListSize(){
		return newWords.size();
	}

	public NewWord getCurrentWord(){
		return currentWord;
	}
}
