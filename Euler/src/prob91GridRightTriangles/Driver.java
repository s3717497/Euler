package prob91GridRightTriangles;

import java.awt.Point;

public class Driver {

	public static void main(String[] args) 
	{
		long startTime= System.currentTimeMillis();
		(new Driver()).run(51, 51);
		long endTime  = System.currentTimeMillis();
		
		double timeInSeconds = (double)(endTime - startTime)/1000;
		System.out.printf("\nThat took %.3f seconds to complete.",timeInSeconds);
	}
	
	private void run(int gridX, int gridY)
	{
			//	* * * *
			//	* * * *
			//	* * * *
			//	* * * *
			Point O = new Point(0,0);
			int occurrences =0;
			// coordinates x,y -> number equivalent
			// this is so we can skip all points covered by the previous points
			int Onum = 0;
			for (int p1Num=Onum+1; 	p1Num<gridX*gridY; p1Num++)	// O = 0,0 	so p1 from 0,1 (1) => 3,3 (15)
			for (int p2Num=p1Num+1;	p2Num<gridX*gridY; p2Num++)	// p1 = 3,2 so p2 from 3,3 => 4,4
			{
				Point p1 = new Point(p1Num%gridX,p1Num/gridX);
				Point p2 = new Point(p2Num%gridX,p2Num/gridX);
				
				if (isRightTriangle(O,p1,p2))
					occurrences++;
			}
			System.out.println(occurrences);
	}
	
	private boolean isRightTriangle(Point a, Point b, Point c)
	{
		Distance[] dists = {new Distance(a,b), new Distance(b,c), new Distance(c,a)};
		for (int i=0; i<dists.length; i++)
		{
			// 	for right triangles, ratio of gradients m1*m2 = -1
			//	dist1.y*dist2.y = -dist1.x*dist2.x					
			int j=(i+1)%dists.length;
			if (dists[i].yDist * dists[j].yDist == 
				dists[i].xDist * dists[j].xDist * -1)
				return true;
		}
		return false;
	}

}
