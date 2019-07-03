package prob54pokerCards;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import prob54pokerCards.Card.Suit;
import prob54pokerCards.Card.Value;

public class DirtyDriver {

	public static void main(String[] args) throws FileNotFoundException
	{
		char[] a = {'8','K','9','K','K','9'};
		System.out.println((new DirtyDriver()).noPairs(a));
		(new DirtyDriver()).quickAndDirtyRun();
	}
	
	private void quickAndDirtyRun() throws FileNotFoundException
	{
		Scanner sc = new Scanner(new File("src//pokerCards54//cards.txt"));
		
		while(sc.hasNext())
		{
			String[] cards = sc.nextLine().split(" ");
			char[] values = new char[cards.length];
			char[] suits = new char[cards.length];
			
			//Player numbered 0, 1
			char[][]  valuesOfPlayer = new   char[2][cards.length/2];
			char[][]  suitsOfPlayer  = new   char[2][cards.length/2];
			
			for (int i=0; i<cards.length; i++)
			{
				values[i] = cards[i].charAt(0);
				suits[i]  = cards[i].charAt(1);
				
				int lol = cards.length/2;
				int playerNo = i < lol ? 0:1;
				// give players half cards
				valuesOfPlayer[playerNo][i%lol] = values[i];
				suitsOfPlayer [playerNo][i%lol] =  suits[i];
			}
			
			int player0Rank = getRank(valuesOfPlayer[0], suitsOfPlayer[0], valuesOfPlayer[1]);
			int player1Rank = getRank(valuesOfPlayer[1], suitsOfPlayer[1], valuesOfPlayer[0]);
			
			if (player0Rank > 5 || player1Rank > 5)
			{
				for (char[] playerValues : valuesOfPlayer)
				{
					System.out.print("Player Values: ");
					for (char v : playerValues)
						System.out.print(v);
					System.out.println();
				}
				
				for (char[] playerSuits : suitsOfPlayer)
				{
					System.out.print("Player Suits: ");
					for (char v : playerSuits)
						System.out.print(v);
					System.out.println();
				}
				
				System.out.println("Player 0 Rank : " + player0Rank);
				System.out.println("Player 1 Rank : " + player1Rank);
				
				int winnerIndex = (player0Rank >= player1Rank) ? 0:1;
				System.out.println("Winner of hand: Player " + winnerIndex);
			}
					
			
		}
	}
	
	
	
	
	
	
	

	
	
	
	// higher values have a higher index
	// ordering like this for speed
	private char[] orderedValues = {'A','2','3','4','5','6','7','8','9','T','J','Q','K'};
	
	//returns player rank, last array needed to compare to other player
	private int getRank(char[] playerValues, char[] playerSuits, char[] player2Values)
	{
		int maxRank = 0;
		if (indexOf(getExtremeValue(playerValues, true)) > 
			indexOf(getExtremeValue(player2Values, true)))					maxRank = 1;
		if (noPairs(playerValues) >= 1)										maxRank = 2;
		if (noPairs(playerValues) >= 2) 									maxRank = 3;
		if (noSameKind(playerValues) >= 3)									maxRank = 4;
		if (isStraight(playerValues))										maxRank = 5;
		if (isFlush(playerSuits))		/* avoid counting repeated pair*/	maxRank = 6;
		if (noSameKind(playerValues) >= 3 && noPairs(playerValues) >= 2) 	maxRank = 7;
		if (noSameKind(playerValues) >= 4)									maxRank = 8;
		if (isStraight(playerValues) && isFlush(playerSuits))				maxRank = 9;
		if (isRoyalFlush(playerValues, playerSuits))						maxRank = 10;
		return maxRank;
	}
	
	private char getExtremeValue(char[] playerValues, boolean highestOrLowest)
	{
		
		char extremeValue = 0;
		
		//eg: {J,4,3,5,3}
		//is one of these A? NO
		//is one of these 2? No
		//is one of these 3? YES break;
		for (char orderedValue : orderedValues)
		{
			//since higher values have higher index
			for (char value : playerValues)
				if (value == orderedValue)
				{
					extremeValue = value;
					if (highestOrLowest == false)
						return extremeValue;
				}
			
		}
		return extremeValue;
	}
	
	
	private int noPairs(char[] playerValues)
	{	
		Map<Character, Integer> repArray = new HashMap<>();
		for (char value : playerValues)
		{
			int reps = 0;
			if (repArray.get(value) != null)
				reps = repArray.get(value);
		
			repArray.put(value, reps+1);
		}
		
		int noPairs = 0;
		for (int rep : repArray.values()) /*eg: [A,2,A,5,6] returns [2,1,2,1,1]*/
		{
			if (rep >= 2)
				noPairs++;
		}
		return noPairs;
	}
	
	
	
	private int noSameKind(char[] playerValues)
	{
		int maxReps = 0;
		for (char value : playerValues)
		{
			int reps = 0;
			// count reps of each value
			for (char v : playerValues)
				if (v  == value)
					reps++;
			
			if (maxReps < reps)
				maxReps = reps;
				
		}
		return maxReps;
	}
	
	
	
	
	
	
	
	
	
	
	
	private boolean isFullHouse(char[] playerValues)
	{
		return noSameKind(playerValues) >= 3 && noPairs(playerValues) >= 1;
	}
	
	
	
	
	
	
	
	
	
	
	private boolean isFlush(char[] playerSuits)
	{
		for (int i=1; i<playerSuits.length; i++)
			if (playerSuits[i] != playerSuits[i-1])
				return false;
		return true;
	}
	
	private boolean isStraight(char[] playerValues2)
	{
		ArrayList<Character> playerValues = toArrayList(playerValues2);
		ArrayList<Character> straightChars = new ArrayList<>();
	
		char lowestValue = getExtremeValue(playerValues2, false);		
		//each value after lowest should be +1
		int startIndex = indexOf(lowestValue);
		
		//create chars required for straight flush
		// based on lowest character
		// eg: if 8 is lowest, then 9, T, J, Q will also be added
		for (int i=startIndex; i<startIndex+playerValues.size(); i++)
			
			try {straightChars.add(orderedValues[i]);}
			// eg: lowest value is J, so only Q, K remaining
			//missing 5 consecutive values
			catch(ArrayIndexOutOfBoundsException e) {return false;};
		return playerValues.containsAll(straightChars);
		
		//		
		//		for (int i=0; i<playerValues2.length; i++)
		//		{
		//			for (char value : playerValues)
		//				if (value == orderedValues[startIndex])
		//				{
		//					startIndex++;
		//					break;
		//				}
		//		}
		//		
		//		return false;
		
	}
	
	
	private boolean isRoyalFlush(char[] playerValues2, char[] playerSuits) 
	{
	
		char[] royalCharss = {'K', 'Q', 'A', 'J', 'T'};
		
		for (char royalChar : royalCharss)
		{
			boolean containsThisChar = false;
			
			for (char v : playerValues2)
				if (v == royalChar)
					containsThisChar = true;
			
			if (!containsThisChar)
				return false;
		}
		//since other condition already checked
		return isFlush(playerSuits); 
		
	}
	
	
	
	
	private ArrayList<Character> toArrayList(char[] array)
	{
		ArrayList<Character> arrayList = new ArrayList<>();
		for (char c : array)
			arrayList.add(c);
		return arrayList;
	}
	
	
	private int indexOf(char a)
	{
		for (int i=0; i<orderedValues.length; i++)
			if (a == orderedValues[i])
				return i;
		return -1;
	}
	
	
}
