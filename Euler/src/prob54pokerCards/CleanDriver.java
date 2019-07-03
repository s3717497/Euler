package prob54pokerCards;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import prob54pokerCards.Card.Suit;
import prob54pokerCards.Card.Value;

public class CleanDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	private void run() throws FileNotFoundException
	{
		Scanner sc = new Scanner(new File("src//pokerCards54//cards.txt"));
		
		while(sc.hasNext())
		{
			String[] cardStrings = sc.nextLine().split(" ");
			ArrayList<Card> cards = new ArrayList<>();
			
			for (String cardString : cardStrings)
				cards.add(toCard(cardString));
			
			// give half the cards to each player
			Player player1 = new Player(cards.subList(0, cards.size()/2));
			Player player2 = new Player(cards.subList(cards.size()/2, cards.size()));
			
			if (player1.getRank() > 5 || player2.getRank() > 5)
			{
//				for (char[] playerValues : valuesOfPlayer)
//				{
//					System.out.print("Player Values: ");
//					for (char v : playerValues)
//						System.out.print(v);
//					System.out.println();
//				}
//				
//				for (char[] playerSuits : suitsOfPlayer)
//				{
//					System.out.print("Player Suits: ");
//					for (char v : playerSuits)
//						System.out.print(v);
//					System.out.println();
//				}
				
				System.out.println("Player 0 Rank : " + player1.getRank());
				System.out.println("Player 1 Rank : " + player2.getRank());
				
				int winnerIndex = (player1.getRank()>player2.getRank()) ? 1:2;
				System.out.println("Winner of hand: Player " + winnerIndex);
			}
		}
		
	}


	
//			if (player0Rank > 5 || player1Rank > 5)
//			{
//				for (char[] playerValues : valuesOfPlayer)
//				{
//					System.out.print("Player Values: ");
//					for (char v : playerValues)
//						System.out.print(v);
//					System.out.println();
//				}
//				
//				for (char[] playerSuits : suitsOfPlayer)
//				{
//					System.out.print("Player Suits: ");
//					for (char v : playerSuits)
//						System.out.print(v);
//					System.out.println();
//				}
//				
//				System.out.println("Player 0 Rank : " + player0Rank);
//				System.out.println("Player 1 Rank : " + player1Rank);
//				
//				int winnerIndex = (player0Rank >= player1Rank) ? 0:1;
//				System.out.println("Winner of hand: Player " + winnerIndex);
//			}
//					
//			
//		}
	
	// given "2H" converts to card 2 of Hearts
	private Card toCard(String cardString)
	{
		char valueChar = cardString.charAt(0);
		char suitChar = cardString.charAt(1);
		
		return new Card(toSuit(suitChar), toValue(valueChar));
	}
	
	private Value toValue(char c)
	{
		switch (c)
		{
			case 'K' : 	return Value.KING;
			case 'Q' : 	return Value.QUEEN;
			case 'J' : 	return Value.JACK;
			case 'T' : 	return Value.TEN;
			case '9' :	return Value.NINE;
			case '8' : 	return Value.EIGHT;
			case '7' : 	return Value.SEVEN;
			case '6' : 	return Value.SIX;
			case '5' : 	return Value.FIVE;
			case '4' : 	return Value.FOUR;
			case '3' : 	return Value.THREE;
			case '2' : 	return Value.TWO;
			case 'A' : 	return Value.ACE;
			default : 	return null;
		}
	}
	
	private Suit toSuit(char c)
	{
		switch (c)
		{
			case 'H' : 	return Suit.HEARTS;
			case 'S' : 	return Suit.SPADES;
			case 'C' : 	return Suit.CLUBS;
			case 'D' : 	return Suit.DIAMONDS;
			default : 	return null;
		}
	}
	

	
	
}
