package prob96Sudoku;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class Cell {
	
	// index of choice test-assigned to cell
	// can only increase until it runs out
	int testIndex = 0;
	LinkedList<Integer> choices;
	int i;
	int j;
	public Cell(int i, int j, int[][] puzzle)
	{
		this.i = i;
		this.j = j;
		
		List<Integer> row = new LinkedList<>();	
		List<Integer> col = new LinkedList<>();	
		List<Integer> grid = new LinkedList<>();	// the 3x3 cell
		
		int startI = i/3*3; 	// eg: i=4 startPos = 4/3*3 = 3
		int startJ = j/3*3; 	// eg: j=8 startPos = 8/3*3 = 6
		for (int p=startI; p<startI+3; p++)
		for (int b=startJ; b<startJ+3; b++)
			grid.add(puzzle[p][b]);
		
		for (int p=0; p<9; p++) {
			row.add(puzzle[i][p]);
			col.add(puzzle[p][j]);
		}
		
		HashSet<Integer> taken = new HashSet<>();
		taken.addAll(row);
		taken.addAll(col);
		taken.addAll(grid);
		
		// values not found in row, column, or grid
		choices = new LinkedList<>();
		for (int num=1; num<=9; num++)
			if (!taken.contains(num))
				choices.add(num);
	}
	
	public String toString()
	{
		return "\n"+choices.toString() + "\t" + testIndex + "\t" + i + "," +j;
	}
	
}
