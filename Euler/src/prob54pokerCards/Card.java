package prob54pokerCards;



public class Card {
	
	 public enum Suit
	 {
	      HEARTS, SPADES, CLUBS, DIAMONDS;
	      
		 public static Suit getEnumEquivalent(char c)
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

	   public enum Value
	   {
	      ACE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), 
	      NINE(9), TEN(10), JACK(11), QUEEN(12), KING(13);
		   
		   private int score;
		   
		   private Value(int score) 
		   {
			   this.score = score;
		   }
		   
		   
		   public int getScore() 
		   {
				return score;
		   }
		   
		   public static Value getValue(int score) 
		   {
			   switch (score)
				{
					case 13 : 	return Value.KING;
					case 12 : 	return Value.QUEEN;
					case 11 : 	return Value.JACK;
					case 10 : 	return Value.TEN;
					case 9 :	return Value.NINE;
					case 8 : 	return Value.EIGHT;
					case 7 : 	return Value.SEVEN;
					case 6 : 	return Value.SIX;
					case 5 : 	return Value.FIVE;
					case 4 : 	return Value.FOUR;
					case 3 : 	return Value.THREE;
					case 2 : 	return Value.TWO;
					case 1 : 	return Value.ACE;
					default : 	return null;
				}
		}
		   
		   public static Value getEnumEquivalent(char c)
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
	   }
	   
	private Suit suit;
	private Value value;
		
	public Card(Suit suit, Value value)
	{
		this.suit = suit;
		this.value = value;
	}
	
	public Suit getSuit() {
		return suit;
	}

	public Value getValue() {
		return value;
	}

}
