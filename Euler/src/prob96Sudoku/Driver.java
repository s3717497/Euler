package prob96Sudoku;

import java.io.*;
import java.util.*;

public class Driver {

	public static void main(String[] args) throws FileNotFoundException 
	{
		long startTime= System.currentTimeMillis();
		
		Scanner sc = new Scanner(new File("src\\prob96Sudoku\\puzzle.txt"));
//		int[][] puzzle = new int[9][9];
//		K : while(sc.hasNext())
//		{
//			for (int[] row : puzzle)
//			{
//				String a = sc.nextLine();
//				if (a.indexOf("Grid") != -1)
//					continue K;
//				String[] b = a.split("");
//				for(int j=0; j<9; j++)
//					row[j] = Integer.parseInt(b[j]);
//			}
//			printSudoku((new Driver()).solve(puzzle));
//		}
		printSudoku((new Driver()).solve(hard));
		long endTime = System.currentTimeMillis();
		double timeInSeconds = (double)(endTime - startTime)/1000;
		System.out.printf("\nThat took %.3f seconds to complete.",timeInSeconds);
		
	}
	
	private static int[][] easy = 
		{
				{0,0,3,0,2,0,6,0,0},
				{9,0,0,3,0,5,0,0,1},
				{0,0,1,8,0,6,4,0,0},
				{0,0,8,1,0,2,9,0,0},
				{7,0,0,0,0,0,0,0,8},
				{0,0,6,7,0,8,2,0,0},
				{0,0,2,6,0,9,5,0,0},
				{8,0,0,2,0,3,0,0,9},
				{0,0,5,0,1,0,3,0,0}
		};
	
	private static int[][] hard = 
		{
			{3,0,0,2,0,0,0,0,0},
			{0,0,0,1,0,7,0,0,0},
			{7,0,6,0,3,0,5,0,0},
			{0,7,0,0,0,9,0,8,0},
			{9,0,0,0,2,0,0,0,4},
			{0,1,0,8,0,0,0,5,0},
			{0,0,9,0,4,0,3,0,1},
			{0,0,0,7,0,2,0,0,0},
			{0,0,0,0,0,8,0,0,6}
		};
	private int[][] solve(int[][] a)
	{
		int[][] unaltered = copy(a);
		int[][] prevA = copy(a);
		// similar to prob88, create an array tracking altered cells
		LinkedList<Cell> testCells = new LinkedList<>(Arrays.asList(getLowestCell(a)));
		while (contains0(a))
		{
			for (Cell cell : testCells)
				a[cell.i][cell.j] = cell.choices.get(cell.testIndex);	
			// store a copy to reset to in case of failure
			prevA = copy(a);
			
			
			// if a contradiction is found (no possible cell value)
			if (solveIterationPrint(a) == false) 
			{		
				//raise last elements index or remove element [cuz its combination is wrong]
				while (testCells.getLast().testIndex+1 >= testCells.getLast().choices.size())
					   testCells.removeLast();
				testCells.getLast().testIndex++;
				//reset cuz wrong values assigned to cell
				a = copy(unaltered);								
			}
			// if stuck (no cells changed)
			else if (equals(prevA, a))							
			{
				testCells.add(getLowestCell(a));
				//new guess, so reset to prev version
				a = copy(prevA);		
			}
		}
		return(a);
	}
	
	
	private boolean solveIterationPrint(int[][] a)
	{
		for (int i=0; i<a.length; i++)
		{
			for (int j=0; j<a.length; j++)
			{	
				LinkedList<Integer> choices = (new Cell(i,j,a)).choices;
				System.out.printf("%-20s", (a[i][j] == 0) ? choices : "");
				// assign a value to cell if...
				if (a[i][j]==0 && choices.size() == 1)
						a[i][j] = choices.getFirst();
				//cell has no possibilities aka other cells are wrong
				if (a[i][j]==0 && choices.isEmpty())
				{
					System.out.println("\n\n\n\nfailed\n\n\n");
					return false;
				}
			}
			System.out.println();
		}
		printSudoku(a);
		return true;
	}
	
	
	private boolean solveIteration(int[][] a)
	{
		for (int i=0; i<a.length; i++)
		for (int j=0; j<a.length; j++)
		{	
			LinkedList<Integer> choices = (new Cell(i,j,a)).choices;
			if (a[i][j]==0)
				if 		(choices.size()==1)	a[i][j] = choices.getFirst();
				else if (choices.isEmpty())	return false;					
				// cell has no possibilities aka other cells are wrong
		}
		return true;
	}
	
	
//	for efficiency: since each cell has 1 correct choice
//	choosing a cell with 2 choices, means you reach correct one quickest
//	this narrows down choices for other cells
	private Cell getLowestCell(int[][] puzzle)
	{
		for (int i=0; i<puzzle.length; i++)
		for (int j=0; j<puzzle.length; j++)
			// fails if no cell has 2 options, but unlikely
			if (puzzle[i][j] == 0 /*&& new Cell(i,j,puzzle).choices.size() == 2*/)
				return new Cell(i,j,puzzle);
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private boolean contains0(int[][] a)
	{
		for (int[] row : a)
		for (int num : row)
			if(num == 0)
				return true;
		return false;
	}
	
	private static int[][] copy(int[][] array)
	{
		int[][] a = new int[array.length][];
		for (int i=0; i<array.length; i++)
		{
			a[i] = new int[array[i].length];
			for (int j=0; j<array[i].length; j++)
				a[i][j] = array[i][j];
			
		}
		return a;
	}
	
	private static boolean equals(int[][] a1, int[][] a2)
	{
		for (int i=0; i<a1.length; i++)
		for (int j=0; j<a2.length; j++)
			if (a1[i][j] != a2[i][j])
				return false;
		return true;
	}
	
	private static void printSudoku(int[][] a)
	{
		for (int[] row : a)
		{
			for (int num : row)
				System.out.print(num + " ");
			System.out.println();
		}
		System.out.println("\n\n\n");
	}

}
