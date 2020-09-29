import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class WordLearnDisplay {
	
	private JFrame frame;
	private JButton button1, button2, button3, button4, button5;
	private boolean wordUsed = false;
	Object wordUsedLock = new Object();
	Color wordColor = Color.blue;
	int xLocation = 100;
	int yLocation = 120;
	Font bigFont = new Font("serif", Font.BOLD, 40);
	Font biggerFont = new Font("serif", Font.BOLD, 50);
	BufferedImage image;

	String italianWord = "insomma";
	String meaning = "well", wordMeaning="";
	String example = "insomma, non e' facile", wordExample="", example2 = "dog", wordExample2="";
	String example3 = "cat", wordExample3 = "";

	//NewWord blank = new NewWord("","","","","");
	private NewWord currentWord = new NewWord("","","","","");
	
	public WordLearnDisplay(){
		//Set Frame
		frame = new JFrame("Time to learn some cool idioms");

		//Set Top Banner ("North" in the layout)
		button1 = new JButton("Nuove Parole");
		button1.setFont(bigFont);
		button1.setForeground(Color.red);

		//Set Left Panel ("West" in the layout)
		try {
			image = ImageIO.read(new File("san_gimignano.jpg"));
		} catch (IOException ex) {
			// handle exception...
		}

		JPanel picturePanel = new JPanel(){
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setFont(new Font("serif", Font.PLAIN, 48));
				g.drawImage(image, 25,50, 500,400,null); // see javadoc for more info on the parameters
			}
		};
		picturePanel.setPreferredSize(new Dimension(500,300));

		//Set Bottom Buttons ("South" in the layout)
		JPanel meaningExamplesPanel = new JPanel();
		button2 = new JButton("Esempi");
		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				wordExample = example;
				wordExample2 = example2;
				wordExample3 = example3;
				frame.repaint();
			}
		});
		button2.setFont(bigFont);
		button3 = new JButton("Significato");
		button3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				wordMeaning = meaning;
				frame.repaint();
			}
		});
		button3.setFont(bigFont);
		meaningExamplesPanel.add(button2);
		meaningExamplesPanel.add(button3);

		//Set Punto Button ("East" in the layout)
		JPanel puntoPanel = new JPanel();
		JPanel puntoPanel2 = new JPanel();
		button4 = new JButton("Punto");
		button5 = new JButton("Punto2");
		puntoPanel.add(button4);
		puntoPanel2.add(button5);
		button4.setForeground(Color.blue);
		button4.setFont(bigFont);
		button5.setForeground(Color.blue);
		button5.setFont(bigFont);
		button4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setWordUsed(true);
				//frame.getContentPane().remove(puntoPanel);
				//frame.getContentPane().add(BorderLayout.EAST, puntoPanel2);
				frame.getContentPane().revalidate();
				frame.repaint();
			}
		});



		//  Set central word area
		JPanel drawPanel = new JPanel() {
			public void paintComponent(Graphics g) {

				g.setColor(wordColor);
				g.setFont(new Font("serif", Font.PLAIN, 48));
				g.drawString(italianWord, xLocation + 12, yLocation);

				g.setFont(new Font("serif", Font.PLAIN, 44));
				g.setColor(Color.red);
				g.drawString(wordMeaning, xLocation, yLocation + 100);

				g.setFont(new Font("serif", Font.PLAIN, 30));
				g.drawString(wordExample, xLocation, yLocation + 200);
				g.drawString(wordExample2, xLocation, yLocation + 250);
				g.drawString(wordExample3, xLocation, yLocation + 300);
			}
		};

		//Fill the frame
		frame.setSize(1800,1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(BorderLayout.NORTH,button1);
		frame.getContentPane().add(BorderLayout.WEST,picturePanel);
		frame.getContentPane().add(BorderLayout.SOUTH,meaningExamplesPanel);
		frame.getContentPane().add(BorderLayout.EAST,puntoPanel);
		frame.getContentPane().add(BorderLayout.CENTER,drawPanel);

		frame.setVisible(true);
	}

	synchronized public void updateWord(NewWord newestWord){
		
		currentWord = newestWord;
		italianWord = currentWord.getWord();
		meaning = currentWord.getMeaning();
		example = currentWord.getExampleUse();
		example2 = currentWord.getExampleUse2();
		example3 = currentWord.getExampleUse3();
		
		wordMeaning = "";
		wordExample = "";
		wordExample2 = "";
		wordExample3 = "";
		
		frame.repaint();	
	}

	public boolean getWordUsed(){
		synchronized (wordUsedLock) {
			return wordUsed;
		}
	}
	public void setWordUsed(boolean newValue){
		//wordUsed = new AtomicBoolean(newValue);
		synchronized (wordUsedLock) {
			wordUsed = newValue;
		}
	}
	public void setWordColor(Color newColor){
		wordColor = newColor;
	}
}