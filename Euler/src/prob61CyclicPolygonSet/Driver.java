package prob61CyclicPolygonSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Driver {

	private static Type[] types = polygonTypesReordered(0);
	private static ArrayList<Type> typeOrder = new ArrayList<>();
	private static  LinkedList<Integer> orderedCyclicSet = new LinkedList<>();
	
	
//	Purpose: find 6-ordered-set (of different polyons) 4 digit cyclic numbers
//	Lesson: Learn to use n nested loops
	
// 	when nest iterating thru polygon numbers, problem:
//	nest iterate triangular(square(pentagonal...)))
//	BUT ALSO	 square(triangular(pentagonal...)))
	
//	because finding an ordered set, nest iteration ORDER MATTERS
//	Solution: create outer for loop that chooses equation for each nest
	
//	EFFICIENCY
//	Cyclic efficiency: given 	eg: 8128, only search thru 2800=>2899 for cyclic
//	2nd cyclic num				eg: 2882, only search thru 8200=>8299 for cyclic
//	3rd cyclic num is 8281
//	create a polygon array for storage (not implemented) 
	public static void main(String[] args) 
	{
		// choose different equation for each nest
		for (int permPos=0; permPos<=fact(types.length); permPos++)
		{
			types = polygonTypesReordered(permPos);
			
			int rangeMin = 1000;
			int rangeMax = 10000;
			nestedLoop(rangeMin, rangeMax, 0);
		}
	}
	
	
	
	
	private static void nestedLoop(int rangeMin, int rangeMax, int typeIndex)
	{
		List<Integer> polygonList = types[typeIndex].getPolygonList(rangeMin, rangeMax);
		if (typeIndex == 5)
		{
			// in the final loop, the last polygon number has:
			// previous polygon number last 2 digits 
			// first polygon number first 2 digits
			// eg: if 8281 followed by 8110, last number = 10 + 82 = 1082
			int num = rangeMin + orderedCyclicSet.get(0)/100;
			// if num is a polygon
			if (polygonList.indexOf(num)>-1)
			{
				orderedCyclicSet.add(num);
				typeOrder.add(types[typeIndex]);
				System.out.print("Set of cyclic polygonal numbers: " + orderedCyclicSet);
				System.out.println("\t\tPolygonal type of each number: " + typeOrder);
				 // gets [8256, 3, 5625, 4, 2512, 7, 1281, 8, 8128, 6, 2882, 5]
			}
			return;
		}
		
		for (int num : polygonList)
		{
			//if 3rd digit 0, eg: 2201, means eg: 0100 is a 3-digit cyclic number
			if (num%100 < 10)	
				continue; 
			
			// if num = 2283, range = 8300 => 8400
			rangeMin = (num%100)*100;
			rangeMax = rangeMin +100;
			orderedCyclicSet.add(num);
			typeOrder.add(types[typeIndex]);
			nestedLoop(rangeMin, rangeMax, typeIndex+1);
		}
		orderedCyclicSet.clear();
		typeOrder.clear();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private static int fact(int n)			
	{	
		int a=1;	
		for (int i=1; i<=n; i++)	
			a *= i;		
		return a;	
	}

	public static Type[] polygonTypesReordered(int permPos)
	{
		ArrayList<Type> types = new ArrayList<Type>(Arrays.asList(Type.values()));
		int size = types.size();
		
		if (permPos > fact(size)){
			System.out.printf("Error : the requested position \"%d\" exceeds the "
			+ "maximum no. of permutations possible, which is %d.\n",permPos,fact(size));		return null;
		}
		
		int d;
		Type[] typesReordered = new Type[size];
		for (int i=1; i<=size; i++)	
		{
			d  = permPos/fact(size - i);				//x/rate of digit change = x/(5-3)! = 7/2  = 3   
			d %= types.size();							//cycle thru dig[0,1,2] since dig[3] does not exist

			typesReordered[i-1] = types.get(d);
			types.remove(d);								//ammend array
		}
		return typesReordered;
	}
	
	
	// the polygon "type" storing list of those numbers from 1000-10000
	// eg: a Triangle Type contains list of triangle numbers
	private enum Type
	{
		TRIANGLE(3), SQUARE(4), PENTAGON(5), HEXAGON(6), SEPTAGON(7), OCTAGON(8);
		
		private int value;
		private Type(int i)
		{
			this.value = i;
		}
		
		private int getValue()
		{
			return value;
		}
		
		private int getEquation(int i) throws ArithmeticException
		{
			switch (this)
			{
				case TRIANGLE : return i*(i+1)/2;
				case SQUARE : return i*i;
				case PENTAGON : return i*(3*i-1)/2;
				case HEXAGON : return i*(2*i-1);
				case SEPTAGON : return i*(5*i-3)/2;
				case OCTAGON : return i*(3*i-2);
			}
			throw new ArithmeticException();
		}
		private List<Integer> getPolygonList(int rangeMin, int rangeMax)
		{
			List<Integer> polygonList = new ArrayList<>();
			for (int i=1; getEquation(i)<rangeMax; i++)
				if (getEquation(i) > rangeMin)	
					polygonList.add(getEquation(i));
			
			return polygonList;
			
		}
		
	}
}
