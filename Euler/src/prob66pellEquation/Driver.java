package prob66pellEquation;

import java.math.BigInteger;
import java.util.LinkedList;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	
//	used a faster algorithm, from https://www.mathblog.dk/files/euler/Problem64.cs	
	private static LinkedList<Integer> infiniteFracPeriodList(int square)
	{
		
		if (square == ((int) Math.sqrt(square))*((int) Math.sqrt(square)))
        	throw new ArithmeticException("Only non-squares allowed");
		LinkedList<Integer> periodList = new LinkedList<>();
        int sqrt = (int) Math.sqrt(square);                                
        
        int d = 1;
        int m = 0;
        int a = sqrt;				periodList.add(a);

        do
        {                
            m = d*a - m;                    
            d = (square - m*m)/d;
            a = (sqrt + m)/d;		periodList.add(a);
        }
        while(a != 2*sqrt);
        
        return periodList;
	}
	
	// convergent numerator, num[0] is current, rest are previous
	private static BigInteger[] num = new BigInteger[3];
	private static BigInteger[] den = new BigInteger[3];
	
	private static BigInteger[] getSquareConvergent(int square, long pos)
	{	
		LinkedList<Integer> periodList = infiniteFracPeriodList(square);
		
		//i==0
		// remove the non-repeating part eg: sqrt13 [3,(1,1,1,1,6)] remove 3
		int a0 = periodList.removeFirst();
		int a1 = periodList.getFirst();
		num[0] = big(a0);						den[0] = big(1);			
//		System.out.println(num[0] + "/" + den[0]);
		
		//i==1
		num[1] = num[0];						den[1] = den[0];
		num[0] = big(a0*a1 + 1);				den[0] = big(a1);	
//		System.out.println(num[0] + "/" + den[0]);
		
		//keep cycling thru period elements eg: sqrt 13,  pos 7, 3,1,1,1,1,6,1
		for (long i=2; i<pos; i++)
		{
			int index = (int) ((i-1)%periodList.size());
			int a  = periodList.get(index);
			num[2] = num[1];					den[2] = den[1];
			num[1] = num[0];					den[1] = den[0];
			
			//		 numPrev*a+numPrevPrev
			num[0] = num[1].multiply(big(a)).add(num[2]);
			den[0] = den[1].multiply(big(a)).add(den[2]);				
//			System.out.print(num[0] + "/" + den[0] + "\t");
//			
//			double aa = num[0].longValueExact()/(double)den[0].longValueExact();
//			System.out.print(aa);
			
//			System.out.println();
		}
		
		
		BigInteger[] frac = {num[0], den[0]};
		return frac;
//		num = 1;	den=allAs[0];
//		for (int a : allAs)
//			num += a*den;
//		
//			int numStore=num;
//			num = den;
//			den = numStore;
//		
//
//			int sqrtNumerator = a0*den + numStore;
//			int sqrtDenominator = den;
//			System.out.println(sqrtNumerator + "/ " + sqrtDenominator);
	}
	
	private static void a()
	{
		
	}
	
	
/*prob66*/
//Purpose: given x2 - Dy2 = 1, 2=>D=>1000, find largest x
//Lesson: need efficiency given unpredictable processing times

// x = smallest square > D*y2	OR 	d2*y2	OR	(dy)2		// d=sqrt(D)
// x > dy
// eg:  	 				5*4^2, 	sqrt5=2,	
//			(2*4 + 1)^2  - 	5*4^2
//			(2*4 + a)^2	 -  5*4^2

//	EFFICIENCY:
//	the sharper d is, the sharper x is
//	the higher y is, the  sharper x needs to be	
	
//	SPECIAL NOTE: convergents underestimate x, by how much?
	
//	Console sample taken D = 978->995	
//	  x									(int) (sqrtD*y)
//	118337^2 - 978*3784^2 = 1, 			118336
//	Too big!
//	51841^2 - 980*1656^2 = 1, 			51840
//	Too big!
//	8837^2 - 982*282^2 = 1, 			8836
//	284088^2 - 983*9061^2 = 1, 			284087
//	88805^2 - 984*2831^2 = 1, 			88804
//	Too big!
//	49299^2 - 986*1570^2 = 1, 			49298
//	377^2 - 987*12^2 = 1, 				376
//	Too big!
//	Too big!
//	881^2 - 990*28^2 = 1, 				880
//	Too big!
//	63^2 - 992*2^2 = 1, 				62
//	2647^2 - 993*84^2 = 1,			 	2646
//	1135^2 - 994*36^2 = 1, 				1134
	
// 	x is very close to sqrtD*y, just +1 
//	as long as sqrtD is sharp enough, x = floor(sqrtD*y)+1
// 	to get a sharp sqrt, you need infinite fractions
//	represent sqrt as numerator / denominator, and rearrange equation
	
//	x = floor(sqrtD*y)+1
//	x = floor(sqrtD*y+1)
//	x = floor(num/den*y+1)				num/den must be accurate af
//	x = floor((num*y+num)/den)			
//	x = newNum*y+newNum | den			newNum < num (basically performing an (int))

//	how to ensure num/den is accurate?
//	https://math.stackexchange.com/questions/1794936/estimation-on-the-accuracy-of-the-convergents-of-sqrtn
// 	digit accuracy ~ digits(num) + digits(den)

	
	
	
	private static void quadraticDiophantine()
	{
		k: for (int D=2; D<=1000;D++)
		{
			BigInteger a = BigInteger.valueOf(2);
			int sqrtD = (int) Math.sqrt(D);
			
//			int sqrtNumD = getSquareConvergent(D,10)[0];
//			int sqrtDenD = getSquareConvergent(D,10)[1];
			if (!isPerfectSquare(D))
//				for (int y=1; 			true; 		 y++)
//				for (int x=sqrtD*y; x<(sqrtD+1)*y; x++)	// old
//					if (x*x - D*y*y == 1)
//					{
//						System.out.println(x + "^2 - " + D + "*" + y + "^2 = 1, " + (int)(Math.sqrt(D)*y));
//						continue k;
//					}
			
				for (BigInteger y=big(1); 			true; 		 y=y.add(big(1)))
				{
					// https://math.stackexchange.com/questions/1794936/estimation-on-the-accuracy-of-the-convergents-of-sqrtn
					int digits = y.toString().length();
					BigInteger num = getSquareConvergent(D,digits+20)[0];
					BigInteger den = getSquareConvergent(D,digits+20)[1];
					
					//(num*y+den)/den 							= sqrtD*y+1
					BigInteger x = num.multiply(y).add(den).divide(den);
					
					//	x2		 	 	- 		Dy2								==  1
					if (x.pow(2)	.subtract	(y.pow(2).multiply(big(D)))	.equals	(big(1)))
					{
						System.out.println(x + "^2 - " + D + "*" + y + "^2 = 1" );
						continue k;
					}
				}
		}	
					
					//  x2 	 						== Dy2 + 1
					//num/den						== Dy2 + 1
					//num	 						==(Dy2 + 1)den
					//num = sqrtNum*y				den = sqrtDen 
//					if (pow(n*y,2) 	== (D*y*y + 1)*pow(d,2))
	}
	
	private static boolean isPerfectSquare(int n)
	{
		int sqrt   = (int) Math.sqrt(n);
		int square = sqrt*sqrt;
		return n == square;
	}
	
	private static BigInteger big(long a)			
	{	
		return BigInteger.valueOf(a);
	}
	

}
