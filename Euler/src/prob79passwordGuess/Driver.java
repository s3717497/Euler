package prob79passwordGuess;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Driver {

//	Purpose: Guess shortest password from list of 3 character guesses, condition: each character in order
	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner sc = new Scanner(new File("src\\passwordGuess79\\prob79.txt"));
		CustomList<Character> possibleChars = new CustomList<>();
		
		while (sc.hasNext())
			for (char c : sc.nextLine().toCharArray())
				if (!possibleChars.contains(c))
					possibleChars.add(c);
		
		sc = new Scanner(new File("src\\passwordGuess79\\prob79.txt"));
		while (sc.hasNext())
		{
			char[] guesses = sc.nextLine().toCharArray();
			for (int i=1; i<guesses.length; i++)
				// move prev char before current char on the list
				// since guesses are ordered
				possibleChars.moveBefore(guesses[i], guesses[i-1]);
		}
		System.out.println(possibleChars);

	}

}
