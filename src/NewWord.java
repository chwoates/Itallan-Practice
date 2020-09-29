
public class NewWord {
	
	private String word = "arrabiato";
	private String meaning = "angry";
	private String exampleUse = "sono molto arrabiato";
	private String exampleUse2 = "mi fai arrabiare";
	private String exampleUse3 = "non si arrabi";
	private int difficulty = 6;
	public boolean wordUsed = false;
	
	public NewWord(String inputWord, String wordMeaning, String example, String example2, String example3){
		word = inputWord;
		meaning = wordMeaning;
		exampleUse = example;
		exampleUse2 = example2;
		exampleUse3 = example3;
	}
	
	public String getWord(){
		return word;
	}

	public String getMeaning(){
		return meaning;
	}
	
	public String getExampleUse(){
		return exampleUse;
	}
	
	public String getExampleUse2(){
		return exampleUse2;
	}
	
	public String getExampleUse3(){
		return exampleUse3;
	}
	
	public int getDifficulty(){
		return difficulty;
	}
	
	public void setDifficulty(int newDifficulty){
		difficulty = newDifficulty;
	}
}
