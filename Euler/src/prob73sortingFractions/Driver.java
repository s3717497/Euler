package prob73sortingFractions;

import java.util.SortedSet;
import java.util.TreeSet;

public class Driver {

	public static void main(String[] args) 
	{
		
		long startTime= System.currentTimeMillis();
		System.out.println(sortingFractions(12000, 3, 2));
		long endTime  = System.currentTimeMillis();
		
		double timeInSeconds = (double)(endTime - startTime)/1000;
		System.out.printf("\nThat took %.3f seconds to complete.",timeInSeconds);
	}
	
	// 	eg: 12000, 	eg: 3 for 1/3	
	private static SortedSet<Fraction> sortingFractions(int cap, Fraction min, Fraction max)
	{
		SortedSet<Fraction> fracs = new TreeSet<>();
		
		for (int d=3; d<=cap; d++)
		{
			// min=1/3, max=1/2
			// min=3.6 / 11 => 3/8
			// min=5.5 / 11 => 6/11
			//eg: 1/3 = 4000/12000
			int numMin = d/min.getDenominator() + 1;
			int numMax = d/max.getDenominator() + 1;
			
			for (int num=numMin; num<numMax; num++)
				fracs.add(new Fraction(num,d));
		}
		//special case
		fracs.remove(new Fraction(1,2));
		
		
		return fracs;
	}
	
// 	eg: 12000, 	eg: 3 for 1/3	
	private static int sortingFractions(int cap, int minDen, int maxDen)
	{
		int total = 0;
		
		for (int d=3; d<=cap; d++)
		{
			// min=1/3, max=1/2
			// min=3.6 / 11 => 3/8
			// min=5.5 / 11 => 6/11
			//eg: 1/3 = 4000/12000
			int numMin = d/minDen + 1;
			int numMax = d/maxDen + 1;
			
			for (int num=numMin; num<numMax; num++)
				if (hcf(num,d) == 1)
					total++;
			
		}
		return total;
	}
	
	private static int hcf(int a, int b) 
    { 
      if (b == 0) 
        return a; 
      return hcf(b, a % b);  
    }
	
}
