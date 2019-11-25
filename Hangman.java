import java.util.Scanner;
/**
 * Hangman Game
 * @author Lucas Balangero
 * Hangman game with:
 * - stick figure drawings
 * - incorrect letters 
 * - and blanks of missing letters
 * 11/25/2019
 */
public class Hangman {

	public static void main(String[] args) {
		//Variables
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter Word for Friend to Guess: ");
		String secretWord = scan.nextLine() ;
		String incorrectLetters="";
		boolean notFinished=true;
		String guess;
		char [] guessBlanks=startWord(secretWord);
		int incorrectGuesses=0;
		
		//Clears the board
		for(int i=0;i<100;i++)
			System.out.println("");
		
		
		
		//instructions
		System.out.println("~~~~~HANGMAN~~~~~"
				+"\nOk friend, type in a letter to guess or\ntry typing entire word if you think you know it ");		
		
		//Loop until lose or win
		do {
			System.out.println("Bad Guesses: "+incorrectLetters);
			blanks(guessBlanks);
			
			System.out.println("Guess a letter: ");
			guess = scan.nextLine();
			//If one letter was guessed
			if (guess.length()==1) {
				//If guess matches with at least 1 letter in secretWord
				if (secretWord.contains(guess)) {
					for(int i=0; i<secretWord.length(); i++) {
						if(secretWord.charAt(i)==(guess.charAt(0))) {
							//letter guess was correct
							guessBlanks[i]=guess.charAt(0);		
						}
					}
				}
				//No letters matched with guess in secretWord
				else {
					System.out.println("Incorrect Guess!");
					incorrectGuesses++;
					incorrectLetters +=guess.charAt(0)+" ";
				}
			}
			//User inserted a string bigger than one (Tried guessing word)
			else {
				if (guess.equals(secretWord)) {
					notFinished=false;
				}
				else {
					System.out.println("Incorrect!");
					incorrectGuesses++;
				}
				
			}
			
			drawFigure(incorrectGuesses);
			if (checkWin(secretWord, guessBlanks))
				break;
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n");
			
		}while(notFinished&&incorrectGuesses!=6);
		//Congrats message or death message
		scan.close();
		
		if(incorrectGuesses>5) {
			System.out.println("The Correct Word Was "+secretWord);
			System.out.println("~~~~You LOSE~~~~");
		}
		else
			System.out.println("Congrats!");
	}
	
	/**
	 * Prints number of letter not guessed yet
	 * @param lettersGuessed
	 */
	public static void blanks(char[] lettersGuessed) {
		String blanks=("( ");
		for(int i=0; i<lettersGuessed.length; i++) {
			if (i==lettersGuessed.length-1)
				blanks=blanks+(lettersGuessed[i]);
			else
				blanks=blanks+(lettersGuessed[i])+" , ";
		}
		blanks=blanks+(" )");
		System.out.println(blanks);
			
	}
	/**
	 * Prints out "_" for the length of the secretword
	 * @param word
	 * @return
	 */
	public static char[] startWord(String word) {
		char[] wordX= new char[word.length()];
		for(int i=0;i<word.length();i++) {
			wordX[i]='_';
		}
		return wordX;
	}
	
	/**
	 * Prints stick figure
	 * @param guesses
	 */
	public static void drawFigure(int guesses) {
		System.out.println("");
		switch(guesses) {
			case(0):
				System.out.println("");
				System.out.println("");
				System.out.println("");
				break;
			case(1):
				System.out.println(" 0");
				System.out.println("");
				System.out.println("");
				break;
			case(2):
				System.out.println(" 0");
				System.out.println(" |");
				System.out.println("");
				break;
			case(3):
				System.out.println(" 0");
				System.out.println("/|");
				System.out.println("");
				break;
			case(4):
				System.out.println(" 0");
				System.out.println("/|\\");
				System.out.println(" ");
				break;
			case(5):
				System.out.println(" 0");
				System.out.println("/|\\");
				System.out.println(" /");
				break;
			case(6):
				System.out.println(" 0");
				System.out.println("/|\\");
				System.out.println(" /\\");
				break;
		}
		
	}
	/**
	 * Checks if user won
	 * returns true if all the letters have been guessed correctly
	 * @param secretWord
	 * @param guessBlanks
	 * @return 
	 */
	public static boolean checkWin(String secretWord,char[] guessBlanks) {
		for (int i=0;i<guessBlanks.length;i++) {
			if(secretWord.charAt(i)!=guessBlanks[i])
				return false;
		}
		return true;
	}
	
	
}
