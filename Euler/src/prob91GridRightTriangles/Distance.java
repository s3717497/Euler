package prob91GridRightTriangles;

import java.awt.Point;

public class Distance {

	int xDist;
	int yDist;
	public Distance(Point point1, Point point2)
	{
		// -ve indicates left/down
		xDist = point2.x - point1.x;
		yDist = point2.y - point1.y;
	}
	
	public String toString()
	{
		return "\nX distance: " + xDist + "\tY Distance: " + yDist;
	}

}
