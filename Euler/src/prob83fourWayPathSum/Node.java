package prob83fourWayPathSum;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import prob73sortingFractions.Fraction;

public class Node implements Comparable<Node> {


	
	
	public int value;
	private char name;
	public int i;
	public int j;
	
	public Node link;
	public int minScore;
	public Node[] reachableNodes;
	public Node(int score, char name)
	{
		this.value = score;
		this.name = name;
	}
	public Node(int score, int idI, int idJ)
	{
		value = score;
		i = idI;
		j = idJ;
		// to ensure it gets updated the first time
		// since all paths would have a score > 0
		minScore = Integer.MAX_VALUE;
		
	}
	
	public void setReachableNodes(Node...nodes)
	{
		reachableNodes = nodes;
	}
	
	public void calcMinScore(Node currentNode)
	{
		if (minScore >= currentNode.minScore + this.value)
		{
			// so when goal node reached, a chain of prev nodes formed
			link = currentNode;
			minScore = currentNode.minScore + this.value;
		}
	}
	
	
	
	@Override
	public int hashCode() 					/*supports array sizes over 100?*/
	{
		return 100*i + j;
	}
	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		return 	i == other.i && 
				j == other.j;
	}
	
	public int compareTo(Node node) 
	{
		if 		(minScore >  node.minScore)	return 1;
		else if (minScore == node.minScore)	return 0;
		else 								return -1;
	} 
	
	public String toString()
	{
		return "\nNode " + i+" "+j + "\tValue: " + value + "\tMinimal score: " + minScore + "";
		
	}

}
