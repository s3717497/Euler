package prob54pokerCards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import prob54pokerCards.Card.Suit;
import prob54pokerCards.Card.Value;

public class Player {

	private Player opponent;
	private List<Card> cards;
	private List<Suit> suits;
	private List<Value> values;
	
	public Player(List<Card> list)
	{
		this.cards = list;
		setupSuits();
		setupValues();
	}
	
	private List<Card> getCards()
	{
		return cards;
	}
	
	private void setupSuits()
	{
		suits = new ArrayList<>();
		for(Card card : cards)
			suits.add(card.getSuit());
	}
	
	private void setupValues()
	{
		values = new ArrayList<>();
		for(Card card : cards)
			values.add(card.getValue());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public int getRank()
	{
		int maxRank = 0;
		if (	this.getExtremeValue(true).getScore() > 
			opponent.getExtremeValue(true).getScore())	maxRank = 1;
		if (noPairs() >= 1)								maxRank = 2;
		if (noPairs() >= 2) 							maxRank = 3;
		if (noSameKind() >= 3)							maxRank = 4;
		if (isStraight())								maxRank = 5;
		if (isFlush())									maxRank = 6;
		if (noSameKind() >= 3 && noPairs() >= 1) 		maxRank = 7;
		if (noSameKind() >= 4)							maxRank = 8;
		if (isStraight() && isFlush())					maxRank = 9;
		if (isRoyalFlush())								maxRank = 10;
		return maxRank;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private Value getExtremeValue(boolean highestOrLowest)
	{
		int maxScore = 0;
		int minScore = 15;
		for (Value value : values)
		{
			if (value.getScore() > maxScore)	maxScore = value.getScore();
			if (value.getScore() < minScore)	minScore = value.getScore();
		}
		
		return Value.getValue(highestOrLowest ? maxScore : minScore);
	}
	
	
	private int noPairs()
	{	
		Map<Value, Integer> repArray = new HashMap<>();
		for (Value value : values)
		{
			int reps = 0;
			if (repArray.get(value) != null)
				reps = repArray.get(value);
		
			repArray.put(value, reps+1);
		}
		
		int noPairs = 0;
		for (int rep : repArray.values()) 
		{	// this is because another condition checks for 3/4 reps
			if (rep == 2)
				noPairs++;
		}
		return noPairs;
	}
	
	
	
	private int noSameKind()
	{
		int maxReps = 0;
		for (Value value : values)
		{
			int reps = 0;
			// count reps of each value
			for (Value v : values)
				if (v  == value)
					reps++;
			
			if (maxReps < reps)
				maxReps = reps;
		}
		return maxReps;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	private boolean isFlush()
	{
		for (Suit suit : suits)
			if (suits.get(0).equals(suit))
				return false;
		return true;
	}
	
	private boolean isStraight()
	{
		//eg: {J,K,2,10,J} becomes {11,13,2,10,11} becomes {2,10,11,11,13}
		ArrayList<Integer> scores = new ArrayList<>();
		for (Value value : values)
			scores.add(value.getScore());
		Collections.sort(scores);
		
		int reqScore = scores.get(0);
		for (int score : scores)
		{
			if (score != reqScore)
				return false;
			reqScore++;
		}
		return true;
		
	}
	
	
	private boolean isRoyalFlush()
	{
		List<Value> royalValues = new ArrayList<>();
		royalValues.add(Value.KING);
		royalValues.add(Value.QUEEN);
		royalValues.add(Value.JACK);
		royalValues.add(Value.TEN);
		royalValues.add(Value.ACE);
		
		return values.containsAll(royalValues) && isFlush();
	}
}
