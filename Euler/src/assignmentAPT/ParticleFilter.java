package assignmentAPT;

import java.util.LinkedList;
import java.util.List;

public class ParticleFilter {
	
	private LinkedList<Particle> particleList;
	private char[][] maze;
	private List<char[][]> obsList;
	
	
	static final int OBSERVATION_DIMENSION = 3;
	static final int ROTATIONS = 4;
	static final int ORIEN_LEFT = 0;
	static final int ORIEN_UP = 1;
	static final int ORIEN_RIGHT = 2;
	static final int ORIEN_DOWN = 3;
	
	ParticleFilter(char[][] maze, List<char[][]> obsList) 
	{
		particleList = new LinkedList<>();
		this.obsList = obsList;
		this.maze = maze; 
	}
	
	List<Particle> filter()
	{
		for (char[][] obs : obsList)
			alterObservation(obs);
		return particleList;
	}


//	// scan the maze in 3x3 slots comparing with the observation
//	void filter(char[][] observation) 
//	{
////		// set current orientation using robot's character
//		int orientation = setOrientation(observation[1][1]);		/*directional*/
//		
//		// no need to compare to previous list
//		boolean emptyList = particleList.size() == 0;
//		//Temporary list copy to compare with at end
//		LinkedList<Particle> oldList = (LinkedList<Particle>) particleList.clone();	
//		particleList.clear();
////		// so it matches the maze (robot NOT at = or ~)
//		observation[1][1] = '.';
//
////		// scan all possible areas
//		for (int i=0; i+OBSERVATION_DIMENSION<maze.length; i++)
//		for (int j=0; j+OBSERVATION_DIMENSION<maze[i].length; j++)
//		{	
//			// check if ALL particles from 3x3 and maze MATCH
//			int totalMatches = 0;
//			
//			for (int p=0; p<OBSERVATION_DIMENSION; p++)
//			for (int q=0; q<OBSERVATION_DIMENSION; q++)
//				if (observation[p][q] == maze[i+p][j+q])
//					totalMatches++;
//			
//			Particle newParticle = new Particle(j+1,i+1,orientation);
//			boolean isValidParticle = emptyList ? true : particleCheck(oldList, newParticle);
//			// is a valid particle
//			if (totalMatches == 9 && isValidParticle)
//			{
//				System.out.println("valid: " + newParticle.toString());
//				particleList.add(newParticle);		
//			}
//		}
//	}
	
	void alterObservation(char[][] observation) 
	{		
		char robotChar = observation[1][1];
		// determines if a directional or undirectional filter approach be applied
		boolean isDirectional = robotChar != '*';
		// no need to compare to previous list
		boolean emptyList = particleList.size() == 0;
		//Temporary list copy to compare with at end
		LinkedList<Particle> oldList = (LinkedList<Particle>) particleList.clone();	
		particleList.clear();
//		// so it matches the maze (robot NOT at = or ~)
		observation[1][1] = '.';
		System.out.println("new obs");
		//loop for each rotation, or just one if direction is known
		for (int rotation=0; rotation<(isDirectional?1:ROTATIONS); rotation++)
		{
			// starts at ORIEN_UP -> ORIEN_RIGHT (1,0,3,2) 
			int orientation = isDirectional ? setOrientation(robotChar) : (ROTATIONS+1-rotation)%ROTATIONS;
			// scan all possible areas
			filter(observation, orientation, emptyList, oldList);
			System.out.println();
			
			// rotate observation 90 degrees ACW
			for (int y=0; y<2; y++) 
	        { 
	            char temp 			= observation[0][y]; 
	            observation[0][y] 	= observation[y][2]; 
	            observation[y][2] 	= observation[2][2-y]; 
	            observation[2][2-y]	= observation[2-y][0]; 
	            observation[2-y][0]	= temp;  
	        } 
		}
		
	}

	int setOrientation(char robotChar) 
	{
		if	   (robotChar == '^' )	return ORIEN_UP;
		else if(robotChar == '<' )	return ORIEN_LEFT;
		else if(robotChar == 'v' )	return ORIEN_DOWN;
		else if(robotChar == '>' )	return ORIEN_RIGHT;
		return -1;
	}
	
	void filter(char[][] observation, int orientation, boolean emptyList, List<Particle> oldList)
	{
		// scan all possible areas
		for (int i=0; i+OBSERVATION_DIMENSION-1<maze.length; i++)
		for (int j=0; j+OBSERVATION_DIMENSION-1<maze[i].length; j++)
		{					
			// check if ALL particles from 3x3 and maze MATCH
			int totalMatches = 0;	
			for (int p=0; p<OBSERVATION_DIMENSION; p++)			
			for (int q=0; q<OBSERVATION_DIMENSION; q++)			
				if (observation[p][q] == maze[i+p][j+q])
					totalMatches++;
		
			if (i==3 && j==6)
			{
				System.out.println("Check");
				System.out.println("");
			}
			Particle newParticle = new Particle(j+1,i+1,orientation);
			boolean isValidParticle = emptyList ? true : particleCheck(oldList, newParticle);
			// add the centre point
			if (totalMatches == 9 && isValidParticle)
			{
				System.out.println("valid: " + newParticle.toString());
				particleList.add(newParticle);		
			}
		}
	}

//	boolean particleCheck(List<Particle> oldList, Particle newParticle) {		/*turn these into pointers?*/
//		
//		// search thru old particles to see if potential follows rules
//		for (Particle oldParticle : oldList) 
//		{
//			
//			int oldX = oldParticle.getX();
//			int newX = newParticle.getX();
//			int oldY = oldParticle.getY();
//			int newY = newParticle.getY();
//			int oldOrientation = oldParticle.getOrient();
//			int newOrientation = newParticle.getOrient();
//			
//			// if robot hasn't rotated, it has moved
//			if (oldOrientation == newOrientation) {
//				
//				// robot moves only 1 step in direction of orientation
//				boolean isVertical = newOrientation==ORIEN_UP || newOrientation==ORIEN_DOWN;
//				boolean isUpOrLeft = newOrientation==ORIEN_UP || newOrientation==ORIEN_LEFT;
//				int oldVertical 			= isVertical ? oldY : oldX;
//				int newVertical 			= isVertical ? newY : newX;
//				int oldHorizontal			= !isVertical? oldY : oldX;
//				int newHorizontal			= !isVertical? newY : newX;
//		
//				// eg: ORIEN_UP from (3,2) to (3,1) y REDUCES
//				if (oldVertical+(isUpOrLeft?-1:1)==newVertical && oldHorizontal==newHorizontal)
//				 	return true;
//			}
//			// robot only rotated
//			else if (oldOrientation%2 != newOrientation%2)
//				if (oldX==newX && oldY==newY)
//					return true;	
//		}
//		return false;
//	}
	
	boolean particleCheck(List<Particle> oldList, Particle newParticle) {		/*turn these into pointers?*/
		
		// search thru old particles to see if potential follows rules
		for (Particle oldParticle : oldList) 
		{
			int oldOrVerticality = oldParticle.getOrient()%2;
			int newOrVerticality = newParticle.getOrient()%2;
			
			int[] a = {oldParticle.getX(), oldParticle.getY()};
			int[] b = {newParticle.getX(), newParticle.getY()};
			
			int moves = 0;
			for (int i=0; i<a.length; i++)
				moves += Math.abs(a[i] - b[i]);
			
			if 		(moves == 0 && oldOrVerticality != newOrVerticality)				return true;
			else if (moves == 1 && oldParticle.getOrient() == newParticle.getOrient())	return true;
		}
		return false;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
