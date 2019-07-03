package assignmentAPT;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Driver {

	// simple java implementation of COSC1285 C++ assignment
	public static void main(String[] args) throws IOException 
	{
		(new Driver()).run();
		
//		int[][] mat = {
//				{1,2,3},
//				{4,5,6},
//				{7,8,9}
//		};
//		displayMatrix(mat);
//		rotateMatrix(mat);
		
	}
	
	void run() throws IOException
	{
		
		ParticleFilter pf = new ParticleFilter(readMaze(new File("C:\\Users\\Reuben\\COSC2391\\Euler\\src\\assignmentAPT\\maze.txt")), readObs(new File("C:\\Users\\Reuben\\COSC2391\\Euler\\src\\assignmentAPT\\obs.txt")));
		System.out.println(pf.filter());
	}
	
    // by 90 degrees in anti-clockwise direction 
//	*    *	then	  *
//					*	*
//	*    *			  *
	
	
	
	
    static void rotateMatrix(int mat[][]) 
    { 
    		//
    	
            for (int y=0; y<2; y++) 
            { 
                // store current cell in temp variable 
                int temp = mat[0][y]; 
                mat[0][y] = mat[y][2]; // move values from right to top
                mat[y][2] = mat[2][2-y];  // move values from bottom to right
                mat[2][2-y] = mat[2-y][0]; // move values from left to bottom
                mat[2-y][0] = temp;  // assign temp to left 
                displayMatrix(mat);
            } 
            
        
    } 
  
    // Function to print the matrix 
    static void displayMatrix(int mat[][]) 
    { 
        for (int i = 0; i < 3; i++) 
        { 
            for (int j = 0; j < 3; j++) 
                System.out.print(" " + mat[i][j]); 
       
            System.out.print("\n"); 
        } 
        System.out.print("\n"); 
    } 
	
	
	char[][] readMaze(File mazeFile) throws IOException
	{
		Scanner sc = new Scanner(mazeFile);
		
		int rows;
		int cols = 0;
		for (rows=0; sc.hasNext(); rows++)
			cols = sc.nextLine().length();
		
		sc = new Scanner(mazeFile);
		char[][] maze = new char[rows][cols];
		
		for (int i=0; sc.hasNext(); i++)
			maze[i] = sc.nextLine().toCharArray();
		
		return maze;
		
	}
	
	List<char[][]> readObs(File obsFile) throws FileNotFoundException
	{
		int length = ParticleFilter.OBSERVATION_DIMENSION;
		List<char[][]> obsList = new LinkedList<>();
		Scanner sc = new Scanner(obsFile);
		
		while (true)
		{
			char[][] obs = new char[length][length];
			for (int i=0; i<3; i++)
				obs[i] = sc.nextLine().toCharArray();
			obsList.add(obs);
			
			if (sc.hasNextLine())	sc.nextLine();
			else return obsList;
		}
	}

}
