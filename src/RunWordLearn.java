import java.awt.*;

public class RunWordLearn  {

    int x = 0;
	
	private WordLists wordList = new WordLists("Internazionale200415");
	NewWord blank = new NewWord("","","","","");
	volatile NewWord  displayWord = new NewWord("","","","","");
	Object displayLock = new Object();
	public RunWordLearn(){}
	
	public static void main(String[] args){
		
		RunWordLearn runGame = new RunWordLearn();
		
		runGame.execGame();
	}
	
	public void execGame(){
		
		int wordTime = wordList.getWordDelayTime()*8;		// sets the minimum word on screen time in ms
		int blankTime = wordList.getBlankDelayTime()*8;		// sets the minimum blank screen time in ms
		long startTime;
		long currentTime;
		WordLearnDisplay display = new WordLearnDisplay(); 	// instantiates the display

		int numbeeps = 10;

		for(int x=0;x<numbeeps;x++)
		{
			//java.awt.Toolkit.getDefaultToolkit().beep();
		}
		
		while(true){

			displayWord =  wordList.getNextWord();
			display.updateWord(displayWord);		// update the screen with the next (randomly chosen) word from list

			startTime = System.currentTimeMillis();

			while(!display.getWordUsed() && (System.currentTimeMillis()-startTime)<10000){
			}
			display.updateWord(blank);
			while(!display.getWordUsed() && (System.currentTimeMillis()-startTime)<10500){
			}
			display.updateWord(displayWord);
			while(!display.getWordUsed() && (System.currentTimeMillis()-startTime)<20000){
			}
			display.updateWord(blank);
			while(!display.getWordUsed() && (System.currentTimeMillis()-startTime)<20500){
			}
			display.updateWord(displayWord);
			while(!display.getWordUsed() && (System.currentTimeMillis()-startTime)<30000){
			}
			display.setWordColor(Color.red);
			display.updateWord(displayWord);
			while(!display.getWordUsed() && (System.currentTimeMillis()-startTime)<40000){
			}
			if((System.currentTimeMillis()-startTime)>40000) {
				for (int x = 0; x < numbeeps; x++) {
					java.awt.Toolkit.getDefaultToolkit().beep();
				}
			}

			while(!display.getWordUsed()){
			}
			display.setWordUsed(false);
			display.setWordColor(Color.blue);
		}
	}
	
	public void delay(int min_time){
		try {Thread.sleep(min_time);} 
		catch (InterruptedException e) {e.printStackTrace();}
	}
}
