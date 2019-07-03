package assignmentAPT;

public class Particle {

	public Particle(int xCoordinate, int yCoordinate, int orient)
	{
		x = xCoordinate;
		y = yCoordinate;
		orientation = orient;
	}
	
	private int x;
	private int y;
	private int orientation;
	
	int getX()
	{
		return x;
	}
	int getY()
	{
		return y;
	}
	int getOrient()
	{
		return orientation;
	}
	
	public String toString()
	{
		String orient = "";
		switch (orientation)
		{
		case ParticleFilter.ORIEN_LEFT : orient="left"; break;
		case ParticleFilter.ORIEN_RIGHT : orient="right"; break;
		case ParticleFilter.ORIEN_UP : orient="up"; break;
		case ParticleFilter.ORIEN_DOWN : orient="down"; break;
		}
		return "x=" + x + " y=" + y + " orientation=" + orient;
	}
}
