package prob83fourWayPathSum;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class Driver {

	private Node[][] nodes = new Node[80][80];					/*manually change this*/
	private LinkedList<Node> visited = new LinkedList<>();
	private SortedSet<Node> reachable= new TreeSet<>();
	
//	Altered so that any file with any size <100x100 can be inputted
	public static void main(String[] args) throws IOException
	{
		long startTime= System.currentTimeMillis();
		(new Driver()).run(new File("src\\prob83fourWayPathSum\\nodeValues.txt"));
		long endTime  = System.currentTimeMillis();
		
		double timeInSeconds = (double)(endTime - startTime)/1000;
		System.out.printf("\nThat took %.3f seconds to complete.",timeInSeconds);
		
//		LinkedList<Node> visited = new LinkedList<>();
//		Set<Node> reachable= new HashSet<>();
//														Node f = new Node(1,'F');
//		
//		Node b = new Node(4,'B');			Node d = new Node(1,'D');
//						Node c = new Node(100,'C');					Node g = new Node(4,'G');
//		Node a = new Node(1,'A');			Node e = new Node(1,'E');
//		
//		
//		a.setReachableNodes(b,c,e);
//		b.setReachableNodes(a,c,d);
//		c.setReachableNodes(a,b,d,e);
//		d.setReachableNodes(b,c,g,f);
//		e.setReachableNodes(a,c,g);
//		f.setReachableNodes(d);
//		g.setReachableNodes(e,d);
//		
//		a.minScore = a.value;
//		visited.add(a);
//		
//		while (!visited.contains(f))
//		{
//			// look thru all reachable nodes for a candidate
//			for (Node node : visited.getLast().reachableNodes)
//			{
//				node.calcMinScore(visited.getLast());
//				reachable.add(node);
//			}
//			reachable.removeAll(visited);
//			
//			//Once reachable nodes have a score, ready to move on
//			//choose the node with the lowest minimal score
//			Node lowestNode = f;
//			for (Node node : reachable)
//				if (node.minScore < lowestNode.minScore)
//					lowestNode = node;
//			visited.add(lowestNode);
//		}
//		
//		// from the goal node, connects all previous nodes together
//		// until start node reached
//		LinkedList<Node> minimalChain = new LinkedList<>();
//		minimalChain.add(f);
//		while(!minimalChain.contains(a))
//		{
//			minimalChain.add(minimalChain.getLast().link);		
//		}
//		System.out.println(minimalChain);
	}
	
	
	
	
	
	
	
	private void run(File file) throws IOException
	{
		printPath(file,40,40);
		readPath(file);
		
		Node startNode = nodes[0][0];
		Node goalNode = nodes[nodes.length-1][nodes[0].length-1];
		
		startNode.minScore = startNode.value;
		visited.add(startNode);
		while (!visited.contains(goalNode))
		{
			// look thru all reachable nodes for a candidate
			for (Node node : getReachableNodes(visited.getLast()))
			{
				node.calcMinScore(visited.getLast());
				reachable.add(node);
			}
			reachable.removeAll(visited);
			//Once reachable nodes have a score, ready to move on
			//choose the node with the lowest minimal score
			visited.add(reachable.first());
		}
		printMinimalRoute();
	}
	
	
	
// 	from the goal node, chains previous nodes together
// 	until start node reached
	private void printMinimalRoute()
	{
		Node startNode = nodes[0][0];
		Node goalNode = nodes[nodes.length-1][nodes[0].length-1];
		
		LinkedList<Node> minimalChain = new LinkedList<>();
		minimalChain.add(goalNode);
		while(!minimalChain.contains(startNode))
			   minimalChain.add(minimalChain.getLast().link);	
		
		for (int i=0; i<nodes.length; i++)
		{
			for (int j=0; j<nodes[0].length; j++)
				System.out.print(minimalChain.contains(new Node(0,i,j)) ? "* " : "  ");		
			System.out.println();
		}
	}
	
//	A -	B -	C		A reachableNodes = B,D
//	|	|	|		E reachableNodes = D,B,F,H
//	D -	E -	F
//	|	|	|
//	G -	H -	I
	private Node[] getReachableNodes(Node currentNode)
	{ 
		int i = currentNode.i;
		int j = currentNode.j;
		
		int h = nodes.length;
		int w = nodes[0].length;
		
		List<Node> reachableNodes = new LinkedList<>();
		if (i-1 >= 0)	reachableNodes.add(nodes[i-1][j]);	//up
		if (i+1 < h)	reachableNodes.add(nodes[i+1][j]);	//down
		if (j-1 >= 0)	reachableNodes.add(nodes[i][j-1]);	//left
		if (j+1 < w)	reachableNodes.add(nodes[i][j+1]);	//right
		return toArray(reachableNodes);
	}

	private static Node[] toArray(List<Node> list)
	{
		Node[] array = new Node[list.size()];
		
		for (int i=0; i<array.length; i++)
			array[i] = list.get(i);
		return array;
	}
	
	
	
	
	
	
	
	
	
	
	private void printPath(File file, int height, int width) throws IOException
	{
		nodes = new Node[height][width];
		FileWriter 	fw = new FileWriter(file);
		PrintWriter pw = new PrintWriter(fw);
		
		for (int i=0; i<height; i++)
		{
			for (int j=0; j<width; j++)
			{
				pw.print((new Random()).nextInt(1000));
				if (j<width-1)
					pw.print("\t");	
			}
			pw.println();
		}
		pw.close();
		fw.close();
	}
	
	private void readPath(File file) throws FileNotFoundException
	{
		//store all values as nodes
		Scanner sc = new Scanner(file);
		for (int i=0; sc.hasNext(); i++)
		{
			int j=0;
			for (String s : sc.nextLine().split("\t"))
			{
				int nodeValue = Integer.parseInt(s);
				nodes[i][j] = new Node(nodeValue, i, j);
				j++;
			}
		}
	}
}
