import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.*;


public class Test {

	public static void main(String[] args) throws FileNotFoundException 
	{
		long startTime= System.currentTimeMillis();
		long endTime = System.currentTimeMillis();
		double timeInSeconds = (double)(endTime - startTime)/1000;
		System.out.printf("\nThat took %.3f seconds to complete.",timeInSeconds);
	} 
	
	
/* rando exercises*/	
	
	private static int fact(int n)			{	int a=1;	for (int i=1; i<=n; i++)	a *= i;		return a;	}
	private static int pow(int n, int pow)	{ 	int a=1;	for (int i=0; i<pow;i++)	a *= n;		return a;	}
	private static int hcf(int a, int b) 
    { 
      if (b == 0) 
        return a; 
      return hcf(b, a % b);  
    } 
	
	private static int sum(int[] array)
	{
		int sum=0;
		for (int num : array)
			sum+=num;
		return sum;
	}
	private static int product(int[] array)
	{
		int product=1;
		for (int num : array)
			product*=num;
		return product;
	}
	
	//Biginteger methods for readability
	private static BigInteger big(long a)			{	return BigInteger.valueOf(a);}
	private static BigInteger mult(long a, long b)	{	return BigInteger.valueOf(a).multiply(BigInteger.valueOf(b)); };
	
	private static int[] reverseArray(int[] array)
	{
		int[] reversedArray = new int[array.length];
		for (int i=0; i<array.length; i++)
			reversedArray[i] = array[array.length-1-i]; 
		return reversedArray;
	}
	private static int[] toArray(List<Integer> list)
	{
		int[] array = new int[list.size()];
		
		for (int i=0; i<array.length; i++)
			array[i] = list.get(i);
		return array;
	}
	
	
//	Gets a string arithmetic expession and solves it
	private static int readExpression(String expression)
	{
		List<String> ops= operationNumArray(expression, true);
		List<String> numsString = operationNumArray(expression, false);
		List<Integer> nums = new LinkedList<>();
		for (String str : numsString)
			nums.add(Integer.parseInt(str));
		
		int function;
		// list order determines which operation evaluated first
		for (String op : Arrays.asList("^","*","/","-","+"))
		while (ops.contains(op))
		{
			int opPos = ops.indexOf(op);
			ops.remove(opPos);
			
			int prev = nums.get(opPos);
			int post = nums.remove(opPos+1);
			
			switch (op)
			{
				case "^" : function = pow(prev,post); 	break;
				case "*" : function = prev * post; 		break;
				case "/" : function = prev / post;		break;
				case "-" : function = prev - post; 		break; // to avoid eg: 1-2+1000 = -1001
				case "+" : function = prev + post; 		break;
				default	 : throw new ArithmeticException("Unsupported operation");
			}
			nums.set(opPos, function);
		}
		return nums.get(0);
	}
	
	private static List<String> operationNumArray(String expression, boolean operationOrNum)
	{
		String regex = operationOrNum ? "[0-9]+" : "[^0-9]";
		List<String> clean = new LinkedList<>(Arrays.asList(expression.split(regex)));
		clean.removeAll(Arrays.asList("", null));
		return clean;
	}
	
	private static void nestedLoop(int numberOfNests, int rangeMax, int loopNo)
	{
		
		if (numberOfNests == 0)
		{
			System.out.println("we done here boi" + loopNo);
			return;
		}
		for (int i=0; i<rangeMax; i++)
			nestedLoop(numberOfNests-1, rangeMax, loopNo+1);
	}
	
	
	
	private static void triangle(int triangleSize)
	{
		int starPos1 = triangleSize - 1;
		int starPos2 = triangleSize - 1;
		triangle(triangleSize, starPos1, starPos2);
	}
	
	private static void triangle(int triangleSize, int starPos1, int starPos2)
	{
		
		if (starPos1 == 0)
			for (int i=0; i<triangleSize; 	i++)
				System.out.print("* ");
		else
		{
			StringBuilder lineToEdit = new StringBuilder("");
			for (int i=0; i<triangleSize*2; i++)
				lineToEdit.append(" ");
			
			lineToEdit.setCharAt(starPos1, '*');
			lineToEdit.setCharAt(starPos2, '*');
			
			System.out.println(lineToEdit);
			triangle( triangleSize, starPos1-1, starPos2+1 );
		}
		
	}
	
	private static void circle(int radius)
	{
		//radius^2 = x^2 + y^2
//		Draws in 2 parts: Upper half then lower half	
		circleHalf(radius, 0, radius, "UPPER");
		circleHalf(radius, radius, 0, "LOWER");
	}
	private static void circleHalf(int radius, int x, int y, String circleHalf)
	{
		StringBuilder lineToEdit = new StringBuilder("");
		for (int i=0; i<=radius*10; i++)
			lineToEdit.append(" ");
		
//					if   y  == 			  +-sqrt r2 + x2
		while ( Math.abs(y) == (int) Math.sqrt(radius*radius - x*x) )
		{
			lineToEdit.setCharAt(2*( x+radius)+2, '*');
			lineToEdit.setCharAt(2*(-x+radius)+2, '*');
			
//			Since we only want the last star, erase prev. stars on sae line			
			lineToEdit.setCharAt( 2*( x+radius), ' ');	lineToEdit.setCharAt(2*( x+radius)+4, ' ');
			lineToEdit.setCharAt( 2*(-x+radius), ' ');	lineToEdit.setCharAt(2*(-x+radius)+4, ' ');

			switch (circleHalf)
			{
				case "UPPER": 	x++;	if (x>radius)	return;	break;
				case "LOWER": 	x--;	if (x<0)		return;	break;
			}
		}
		y--;
		System.out.println(lineToEdit);
		circleHalf(radius, x, y, circleHalf);
	}
	
	public static void toBinary(int n)
	{
		if (n != 0)
		{	//divide by 2, then track its reaninder
			int remainder = n%2;
			System.out.println(remainder + " ");
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	| == sqrt	
// 	to find primality of random number, you could:
//	...check divisibility... 
// 	a) 		< n/2 [largest possible factor]
// 	b) 		> n/2
// 	c)		< |n
// 	d)	 |n - n/2 [This gap is bigger than c) ]
//	e) thru array storing prev. primes
	
// 	Why does c) work?x
// 	if 	p > |n, it has to multiply with some 
//		p < |n, which has already been checked.
//	there is no point checking p's > |n,
//	as the loop would have already broken.
//	[aka n is prie]
	private static ArrayList<Integer> primes(long cap)
	{
		ArrayList<Integer> primes = new ArrayList<Integer>(); 
		primes.add(2);
		
//		for odd nos. only
		k : for (int i=3; i<cap; i+=2) 
		{
			for (int q=0; q<primes.size(); q++)
				if (i % primes.get(q) == 0) 	//if i divisible by a prime, its not prime!!
					continue k; 
			primes.add(i);
		}
		return primes;
	}
	
	private static ArrayList<Integer> primes(int min, int cap)
	{
		ArrayList<Integer> primes = new ArrayList<Integer>(); 
		if (min<=2)
			primes.add(2);
		
		if (min%2 == 0)	
			min++;
//		search all odd nos. only
		k : for (int i=min; i<cap; i+=2) 
		{
			for (int f=3; 	 i>=f*f;  f+=2)	
				if (i%f == 0) 	
					continue k; 
			primes.add(i);
		}
		
//		System.out.print("The primes btwn "+ min + " and " + cap + " are: ");
//		for (int prime : primes)
//			System.out.print(prime + ", ");
//		System.out.println();
		
		return primes;
	}
	
	private static List<Integer> primeSeive(int cap)
	{
		//eg: isPrime[8] checks if 8 is prime
		boolean[] isComposite = new boolean[cap+1];
		
		for (int n=2; n*n<cap; n++)
		{
			if (isComposite[n] == true)
				continue;
			// mark all multiples of n as composite
			for (int i=n*n; i<cap; i+=n)
				isComposite[i] = true;
		}
		List<Integer> primes = new LinkedList<>();
		for (int i=2; i<cap; i++)
			if (!isComposite[i])
				primes.add(i);
		return primes;
	}
	
	private static boolean isPrime(long diagonal)
	{	
		if 		(diagonal<2) 	return false;
		else if (diagonal==2) 	return true;
		else if (diagonal%2==0)return false;
		for (int f=3; 	 diagonal>=f*f;  f+=2)	
			if (diagonal%f == 0)
				return false;
		return true;
	}
//	if sqrt S^2 = S  
	private static boolean isPerfectSquare(int n)
	{
		int sqrt   = (int) Math.sqrt(n);
		int square = sqrt*sqrt;
		return n == square;
	}
	
	
	
	
	
	
	
	
	private static int[] digits(long n)
	{
		int div = 10;
		int noDigits;
		for (noDigits=1; n/div>=1; noDigits++)
		{
			div*=10;
		}
		div/=10;
		int[] digits = new int[noDigits];

		//Put digits in array
		for (int d=0; d<digits.length; d++ )
		{
			digits[d] = ((int)n/div)%10;
			div/=10;
		}
		return digits;
	}
	
	private static int[] digits2(int n)
	{
		char[] num = (Integer.toString(n)).toCharArray();
		int[] digits = new int[num.length];
		
		for (int d=0; d<digits.length; d++ )	
			digits[d] = num[d]-48;
		
		return digits;
	}
	
	// eg: if 2 digit num, search all products below 99*99
	private static void nDigitPalindrome(int noDigits)
	{
//		10^noDigits -1
//		10^2        -1 = 99	
		int cap=1;
		for (int i=0;i<noDigits;i++)
			cap *= 10;
		cap--;

		int greatestPal = 1;
//		so that even with the highest j value, i can't reach this palindrome		
		for (int i=cap; i*cap>=greatestPal; i--)
		for (int j=cap; j>=i; j--)
		{
			int product = i*j;
//				Finding the LARGEST PAL
//				eg: 4*2 = 8
//				vs  3*3 = 9
			if (isPalindrome(product))
			{
				if (greatestPal<product)
					greatestPal=product;
				System.out.println(i);
				System.out.println(j);
				System.out.println(product);
			}
		}
			
	}
	
	private static boolean isPalindrome(int n)
	{
		int[] digits = digits2(n);
		
		//check if palindrome
		boolean isPalindrome= true;
		int 	lastDigit   = digits.length -1;
		
		for (int d=0; d<digits.length/2; d++)
			if (digits[d] != digits[lastDigit-d]) 
				isPalindrome=false;
		
		return isPalindrome;
	}
	

	private static int palindrome(int input, int base) 
	{
	    int n = input;
	    int pal = input;
	    if (n%2 == 1)
	        n /= base;
	 
	    while (n > 0) 
	    {
	        pal *= base;
	        pal += n%base;
	        n /= base;
	    }
	 
	    return pal;
	}
	
	
//	divisible by the highest power of each p
//	eg: p below 20 : 2^4, 3^2, 5, 7, 11, 13, 17, 19
	public static void evenlyDivisible(int upperLimit)
	{
		ArrayList<Integer> primes = primes(upperLimit);
		int[] factors= new int[primes.size()];
		int mindiv = 1;
		
		for (int i=0; i<primes.size(); i++)
		{
			factors[i] = 1;
			while ( factors[i] <= upperLimit)	factors[i] *= primes.get(i);
			if 	  ( factors[i]  > upperLimit)	factors[i] /= primes.get(i);
			
			mindiv *= factors[i];
		}
		
		
		System.out.println();
		for (int factor: factors)
			System.out.println(factor);
		
		System.out.printf("The smallest number divisible by all values from 1 to %d is: %d",upperLimit,mindiv);
//		
		
	}
	
	
/*prob12*/
//	divisors eg: 100 = 2^2 * 5^2 = 2^0 * 5^0/1/2, 2^1 * 5^0/1/2, 2^2 * 5^0/1/2
//	all prime factors of 100: a,b,c... and highest power of each, a^p,b^q,c^r
//	combinations of highest powers = p*q*r
	public static void prob12(int minNumDiv)
	{
		int x, numDiv = 1;
		long n=0;
		ArrayList<Integer> primes = new ArrayList<Integer>();
		for (x=3; numDiv <= minNumDiv; x++) 
		{
//			find pries btwn n[x-1]/2 - n[x]/2
//			3, 6, 10, 15, 21
//			1, 3, 5,   7, 10
			int min = x*(x-1)/4;	//n[x-1]/2
			int cap = x*(x+1)/4;	//n[x]	/2
			primes.addAll(primes(min,cap));
			
			numDiv = 1;
			//Finding factors to check divisibility
			for (int p : primes)
			{
				//Reset n each loop
				n = x*(x+1)/2;
				//Exclude non-divisibles from maxPowers[i]
				if (n%p == 0)
				{
					//extra power to calc divisors
					int maxPower = 1;	
					//find the largest power of each prime
					while (n %p == 0) {
						   n/=p;
						   maxPower++;
					}
					//maxPower = possible combinations of that power
					numDiv *= maxPower;
				}
			}
		}
		n = x*(x+1)/2;
		System.out.println("Smallest triangular number with over " + minNumDiv + " divisors is " + n);
				
	}
	
//	num = product of prime factors  	= a^p * b^q * c^r ....
//	eg: 3500						 	= 2^2 * 5^3 * 7^1
//	numDiv = all combinations of factors= p	  * q	* r ....
	private static int numDiv(int num)
	{
		int numDiv=1;
		int maxPower;
		for (int prime : primeList)
		{
			for (maxPower=1; num%prime == 0; maxPower++)
				num/=prime;
			numDiv *= maxPower;
			// number has been exhausted of its factors, so doesn't check all primes
			if (num == 1) break;
		}
		return numDiv;
	}
	
	
//	keep dividing num by lowest prime until it hits a prime
//	eg: 30/2 = 15/3 = 5, store 2,3,5				// input primeSeive(num)
	
	private static List<Integer> primeList;
	private static List<Integer> divisors(int num)
	{
		// divisors and max power
		List<Integer> divisors = new LinkedList<>();
		
		for(int prime : primeSeive(num))
			if (num%prime == 0)
				divisors.add(prime);
		System.out.println(divisors);
		return divisors;
	}
	
	private static List<Integer> maxPowers(int num)
	{
		List<Integer> maxPows  = new LinkedList<>();
		
		for (int prime : divisors(num))
		{
			int maxPower;
			for (maxPower=0; num%prime == 0; maxPower++)
				num/=prime;
			maxPows.add(maxPower);
		}
		System.out.println(maxPows);
		return maxPows;
	}
	
	
	public static void nDigitSum(String str)
	{
		Scanner sc = new Scanner(str);
		
		while (sc.hasNext())
		{
			String num = sc.nextLine();
			int lastIndex = num.length()-1;
			
			int reqNo = Integer.parseInt( num.substring(lastIndex - 10) );
			System.out.println("the required no. is " + reqNo);
		}
	}
	
//	 _____
//	|_|_|_|
//	|_|_|_|
//	|_|_|_|	
/*prob15*/
//	I sat on this prob for an entire day to try to figure this pattern
	public static int gridRoutes(int gridSize)
	{

		int s = gridSize;
		
		if (s == 1)
			return 2;
		int l = gridRoutes(s-1);
			return 2*l + 2*l*(s-1)/s;
			
	}
	
/*prob14*/	
	public static void longestCollatzChain(int upperLimit)
	{
		int maxChainSize = 0;
		int start = 0;
		for (int i=2;i<upperLimit;i++)
		{
			long n =i;
			short chainSize = 0;
			while (n != 1)
			{
		
				if (n%2 == 0)
					n /=2;
				else
					n  =3*n+1;
				chainSize++;
			}
			
			if (maxChainSize < chainSize) 
			{
				maxChainSize = chainSize;
				start = i;
			}
		}
			
		System.out.printf("The largest Collatz chain starting below %d is: %d, beginning at %d",upperLimit,maxChainSize,start);
	}
	
	
/*prob17*/
	public static void lettersBelow1000()
	{
		int letters;
		int total = 0;
		for (int i=1; i<=1000; i++)
		{
			letters = 0;
			
//			one+thousand
			if (i == 1000) 
			{		
				letters = 3+8;
				total  += letters;
				break;
			}
			if (i>=100)
			{
				switch (i/100)
				{
					case 1 : case 2 : case 6 : 			letters += 3; break; 
					case 4 : case 5 : case 9 : 			letters += 4; break;
					case 3 : case 7 : case 8 : 			letters += 5; break;
				}
//													... 	    + hundred +and
														letters += 7+3;
				if (i%100 == 0)							
					letters -= 3;//	  -and
			}
//				610 < x < 620
			if (i%100 < 20  && 	i%100 > 10)
				switch (i)
				{
					case 11: case 12:					letters += 6; break;
					case 15: case 16: 					letters += 7; break;
					case 13: case 14: case 18: case 19: letters += 8; break;
					case 17: 							letters += 9; break;
				}
			else 
			{
//				625	=> 	25    - 5	
				switch (i%100 - i%10)
				{
					case 10:							letters += 3; break;
					case 40: case 50: case 60: 			letters += 5; break;
					case 20: case 30: case 80: case 90:	letters += 6; break;
					case 70: 							letters += 7; break;
				}
//				625	=> 	5
				int a = i%10;
				switch (a)
				{
					case 1 : case 2 : case 6 : 			letters += 3; break; 
					case 4 : case 5 : case 9 : 			letters += 4; break;
					case 3 : case 7 : case 8 : 			letters += 5; break;
				}
			}
		
			total += letters;
		}
		System.out.println("The total no. of letters 1-1000 inclusive are: " + total);
		
	}
	
	
	
	private static int[][] trianglePath = 
		{ 	
			{75},
			{95, 64},		
			{17, 47, 82},	
			{18, 35, 87, 10},
			{20, 04, 82, 47, 65},
			{19, 01, 23, 75, 03, 34},
			{88, 02, 77, 73, 07, 63, 67},
			{99, 65, 04, 28, 06, 16, 70, 92},
			{41, 41, 26, 56, 83, 40, 80, 70, 33},
			{41, 48, 72, 33, 47, 32, 37, 16, 94, 29},
			{53, 71, 44, 65, 25, 43, 91, 52, 97, 51, 14},
			{70, 11, 33, 28, 77, 73, 17, 78, 39, 68, 17, 57},
			{91, 71, 52, 38, 17, 14, 91, 43, 58, 50, 27, 29, 48},
			{63, 66, 04, 68, 89, 53, 67, 30, 73, 16, 69, 87, 40, 31},
			{04, 62, 98, 27, 23,  9, 70, 98, 73, 93, 38, 53, 60, 04, 23}
		};
/*prob18*/
// 	Purpose: find optimal path in a triangle array
//	Conditions: Clear starting point, no ending point	going 2 directions: down/diagonal down
// 	starting from bottom, work left-then-upwards
//	current cell sum += Max(down,diagonal down)
	
	public static void trianglePathSum(int[][] trianglePath)
	{
		int[][] pathIndex = deepCopy(trianglePath);
		
		int lastRow = trianglePath.length-1;
		for (int i=lastRow-1; i>=0; i--)
		{
// 			no need lastCol -1 cuz the prev row already has -1 cols.
			for (int j=0; j<trianglePath[i].length; j++)
			{	
				trianglePath[i][j] += Math.max(	trianglePath[i+1][j], 
												trianglePath[i+1][j+1] );
				// store the index of the chosen path for later
				pathIndex[i][j] = 	trianglePath[i+1][j] > trianglePath[i+1][j+1] ? j:j+1;
			}
		}
		
		// navigating top-down, each index points to the next index
		// forming a single path
		int[] bestPath = new int[pathIndex.length-1];
		int nextIndex = 0;
		for (int i=0; i<pathIndex.length-1; i++)
		{
			bestPath[i] = nextIndex;
			nextIndex = pathIndex[i][nextIndex];
		}
		for (int a : bestPath)
		{
			for (int i=0; i<a; i++)
				System.out.print("  ");
			System.out.println(a);
		}
		System.out.println("THe largest sum of all paths are: " + trianglePath[0][0]);
	}
	
	private static int[][] deepCopy(int[][] array)
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
	
	
	
	
	
	
	
	
	// alternative to BigInteger
	public static ArrayList<Integer> largeNumStorage(long numToMultiply, ArrayList<Integer> digitStore9)
	{
//		eg: 28236283 stored like
//			83 62 23 28
//		But the nos. start little and grow in size
//		digitStore10 = new ArrayList<Integer>();
//		if (digitStore10.isEmpty())
//			digitStore10.add(1);
		
		int carry = 0;
		for (int i=0; i<digitStore9.size(); i++)
		{
			int d = digitStore9.get(i);
			d *= numToMultiply;
			d += carry; //add prev. carry
			
			digitStore9.set(i,d%1000000000); 
			carry = d/1000000000;
//							 Next element not exist, 		create next element
			if (carry>0  &&  digitStore9.size() == i+1)		digitStore9.add(0);
		}
		return digitStore9;
	}
	
/*prob20*/
	public static ArrayList<Integer> largePowers(int n, int power)
	{
		ArrayList<Integer> digitStore = new ArrayList<Integer>();
		digitStore.add(1);
		
		for (int i=0; i<power; i++)
			digitStore = largeNumStorage(n,digitStore);
		
		return digitStore;
	}
	
	
	
	
	
	
/*prob22*/
//	Enter values like this	
	String[] s = {	"ZIGZAG","MARY","PATRICIA","LINDA","BARBARA","ELIZABETH","JENNIFER",
					"MARIA","SUSAN","MARGARET","DOROTHY","LISA","NANCY","KAREN",
					"BETTY","HELEN","SANDRA","DONNA","CAROL","RUTH","SHARON"	};
//	sortNames(s);
	public static void sortNames(String[] names)
	{
		for (int i=1; i< names.length; i++)
		{
			int   newPos = i;
			while (newPos>0 && names[i].compareTo(names[newPos-1])<0)
			      newPos--;
			
			//Swap and reorder array
			String tmp = names[i];
			for (int j=i; j>newPos; j--)
				names[j] = names[j-1];
			names[newPos] = tmp;
			
		}
		
		System.out.println("The sorted order of names are: ");
		for (String n : names)
			System.out.println(n);
	}

/*prob23*/
//	abs
//	if divSum > i
//	{12,18,20...}
//	abSums
//	{24,30,36,32...}
//	totalNonAbSum = n(n+1)/2 - totalAbSum

	
	
	
	
	
	
	
	
	
	
	
/*prob24*/
//	Lesson: / then % to cycle thru options
	
//	The 1st position = pos 0
//	Hence 1,000,000th position = pos 999,999
	
//	eg:										Array dig							   ArrayPos			
//	01234						start with {0,1,2,3,4}	a changes every 24		a|4!	a = x/(5-1)! = 7/24 = 0							dig[0] = 0	YES
//	01243						ammend to  {1,2,3,4}	b changes every 6		b|3!	b = x/(5-2)! = 7/6	= 1							dig[1] = 2 	YES
//	01324						ammend to  {1,3,4}		c changes every 2		c|2!	c = x/(5-3)! = 7/2  = 3							dig[3] 		NO		.
//	01342													.								
//	01423																										cycle thru dig[0,1,2]
//	01432																										c = c%dig.length =3%3=0	dig[0] = 1	YES
//								
//	02134 					 	ammend to  {3,4}		d changes every 1 		d|1!	d = x/(5-4)! = 7/1  = 7	d = d%dig.length =7%2=1	dig[1] = 4	YEA
//	02143 8th = pos7 =permPosx	ammend to  {3}			e changes every 1 		d|0!
//	02314
//	02341						[only 24 possibilities, req. 120][slots = digits = 5]
//	02413
//	02431						
	
//	03124		012				HOWEVER if [slots = 3][digits = 5]
//	03142		013										a changes every 4*3		a|4!/2!	a = x(5-3)!/(5-1)!
//	03214		014										b changes every 3		b|3!/2!
//	03241												
//	03412		021				.... rest is same
//	03421		023
//				024
//	04123						
//	04132		031
//	04213		032
//	04231		034
//	04312		
//	04321.... 	041...					

//	^^^^^
//	|||||
//	abcde				
	
	private static String posNo;
	private static int[] reorderedArray;
//														eg: for 1-8 pandigital use (8,7,6,5,4,3,2,1)
	public static void lexPerm(int permPos, int slots, List<Integer> dig2)
	{
		ArrayList<Integer> dig = new ArrayList<Integer>(dig2);
		int size = dig.size();
		
		if (permPos > fact(size)){
			System.out.printf("Error : the requested position \"%d\" exceeds the "
			+ "maximum no. of permutations possible, which is %d.\n",permPos,fact(size));		return;
		}
		if (slots	> size){
			System.out.printf("Error : more slots than unique digits cause repeating numbers");	return;
		}
		
		int d;
		for (int i=1; i<=slots; i++)	
		{
//			d  = permPos/fact(size - i);				//x/rate of digit change = x/(5-3)! = 7/2  = 3   
			d  = permPos*fact(size - slots);
			d /=		 fact(size - i);
			d %= dig.size();							//cycle thru dig[0,1,2] since dig[3] does not exist
			
//			posNo += pow(10,size - i) * dig.get(d);		//eg: 02143  = 10^5*0 + 10^4*2....
			posNo += dig.get(d);
			reorderedArray[i-1] = dig.get(d);
			dig.remove(d);								//ammend array
		}
//		int nthPos = permPos+1;
//		System.out.println("The lexicographic permutation of these digits "
//		+ "at the " + nthPos + "th position is: " + posNo);
	}
	private static String lexPermString(int permPos, int slots, List<Integer> dig)
	{
		posNo = "";
		lexPerm(permPos, slots, dig);
		return posNo;
	}
	private static int[] lexPermArray(int permPos, int slots, List<Integer> dig)
	{
		reorderedArray = new int[slots];
		lexPerm(permPos, slots, dig);
		return reorderedArray;
	}
	
	
	
	
	private static int[][] allPermutations(int slots, int[] array)
	{
		int[][] permutations = new int[fact(array.length)/fact(array.length -slots)][];
		for (int i=0; i<permutations.length; i++)
		{
			List<Integer> list = new LinkedList<>();
			for (int n : array)
				list.add(n);
			permutations[i] = lexPermArray(i,slots, list);
		}
		return permutations;
	}
	private static int[][] allPermutations(int[] array)
	{
		return allPermutations(array.length, array);
	}
	
	
	
	
	
	
//	6 size:	2 slots: ||****	|*|***	|**|** 	|***|*	|****|			*||***	*|*|**	*|**|* 	*|***|		...			
//			3 slots: |||***	||*|**	||**|*	||***|					|*||**	|*|*|*	|*|**|				|**||*	|**|*|			|***||	...
//	Implies n nested for loops, n = slots, 1st nest for 1st |, 2nd nest for 2nd |
//		for (int i=0; 	i<size-slots; 	i++)	pos of 1st |
//		for (int j=i+1; j<size-slots+1; j++)
//		for (int k=j+1; k<size-slots+2; k++)
//		{
//			listPos[0] = i;
//			listPos[1] = j;
//			listPos[2] = k;
//			//combinations.add(list
//		}
//
//	Need array of elements indicating chosen list positions
//	eg: list a,b,c,d,e,f: {0,4,5} means pick a,b,f
	private static List<int[]> combinations(int slots, int[] array)
	{
		combinations = new LinkedList<>();
		int[] currComb= new int[slots];
		int[] listPos = new int[slots];
		
		int last = slots-1;
		int k = last;
		
		// initial array {0,1,2}
		for (int i=0; i<slots; i++)
			listPos[i] = i;
		listPos[last]--;
		
		do {
			listPos[k]++;
			// reset nextBars
			for (int i=k+1; i<slots; i++)
				listPos[i] = listPos[i-1]+1;
			
			while (listPos[last] < array.length)
			{
				//get elements from positions
//				for (int i=0; i<slots; i++)
//					currComb[i] = array[listPos[i]];
//				boolean[] test = new boolean[array.length];
//				for (int i=0; i<listPos.length; i++)
//					test[listPos[i]] = true;
//				for (boolean aa : test)
//					System.out.print(aa ? "|" : "*");
//				System.out.println();
				combinations.add(currComb.clone());
				k=last;
				listPos[k]++;
			}
		} while (--k >= 0);
		return combinations;
	}
	
	private static List<int[]> combinationPos(int slots, int size)
	{
		combinations = new LinkedList<>();
		combNestedLoop(0, size-slots+1, 0, new int[slots]);
		return combinations;
		
	}
	private static List<int[]> combinations;
	private static void combNestedLoop(int rangeMin, int rangeMax, int bar, int[] listPos)
	{
		if (bar==listPos.length)
		{
			combinations.add(listPos.clone());
			return;
		}
		for (int i=rangeMin; i<rangeMax; i++)
		{
			listPos[bar] = i;
			combNestedLoop(i+1, rangeMax+1, bar+1, listPos);
		}
	}
	
	

	
	private static List<int[]> allCombinations(int[] array)
	{
		List<int[]> allCombos = new LinkedList<>();
		for (int i=1; i<=array.length; i++)
			allCombos.addAll(combinations(i,array));
		return allCombos;
	}
	
	private static List<int[]> allCombinationPos(int size)
	{
		List<int[]> allCombosPos = new LinkedList<>();
		for (int i=1; i<=size; i++)
			allCombosPos.addAll(combinationPos(i,size));
		return allCombosPos;
	}

//	eg: 0125 as |||**|
	private static void printCombos(int[] listPos, int size)
	{
		boolean[] test = new boolean[size];
		for (int pos : listPos)
			test[pos] = true;
		for (boolean aa : test)
			System.out.print(aa ? "|" : "*");
		System.out.print(" ");
	}
	
	
	
	
	
	
	
	
	
	

/*prob27*/
//	Purpose: in Quadratic q(i) = i^2 + ai + b. find a,b for max i, so 0=>i is prime	
//	A == any
//	O == odd
//	E == even
//	P == prime
	
//	Properties I figured: 
//	f(n) is P										f(n)	= O
//	distance of P's, n<=>n+1,		d = 2n+1+a 		2n+1+a 	= E [not used, but req. for efficiency]
//	f(0) is P						f(0) = b 		b		= P								

//	if a is E
//		f(O) = O^2 + E*O + P		f(O) = E WRONG
//	since n alternates between E/O					a 		= O
	
//	Efficiency:
//	-1000 < a,b < 1000			
//	a = 			2000 options			b = 		   2000 options		Searching all a,b		total Options = a*b = 400,000 options			INEFFICIENT
//	a = O's only = 	1000 options			b = P's only = 200 options		Searching specific a,b  total Options = O*P =  20,000 options			EFFICIENT

//	Searching q(i) for max i which is P
// 	Largest q(i) n^2 + 1000n + 1000
//	preset P-list rather than dynamic P-list in loop

	private static void consPrimesInQuadratic(int coeffLimit) //1000 for this prob
	{
		int maxA=0;
		int maxB=0;
		int maxStreak = 0;
//		a can only be O [we know E values WILL fail]
		int a = -coeffLimit;
		if (a%2 == 0)	
			a++;	
		
		ArrayList<Integer> primes = primes(coeffLimit);
		
		for (a=a; a<coeffLimit; a+=2)		//eg: a = -1000 < O's < 1000
		for (int b : primes)	//	  b = all 	  P's <	1000
		{
			//Searching q(i) for max i, so 0=>i is prime
			int primeStreak = 0;	
			for (int n=0; isPrime(n*n + a*n + b); n++)
				primeStreak++;
			
			if (maxStreak < primeStreak) {
				maxStreak = primeStreak;
				maxA 	  = a;
				maxB   	  = b;
			}
		}
		
		System.out.println(maxA +" "+ maxB + " with a streak of " + maxStreak);
		
	}

/*prob28*/
//	Purpose: sum of diagonal corners of each "box"
//											43 44 45 46 47 48 49*		note how last nums[*] of each box is a SQUARE
//					21 22 23 24 25*			42 21 22 23 24 25*26		bc each square box is n*n units large [n is an Odd length]
//		7  8  9*	20  7  8  9*10			41 20  7  8  9*10 27		
//	1	6  1* 2		19  6  1* 2 11			40 19  6  1* 2 11 28		all corners to add for a 7 unit box:
//		5  4  3		18  5  4  3 12			39 18  5  4  3 12 29		1, 3^2, 5^2, 7^2
//					17 16 15 14 13			38 17 16 15 14 13 30		   7    21   43
//											37 36 35 34 33 32 31		   5    17   37
// 		<--3-->		<------5----->			<---------7--------> = n	   3    13   31
//																		  [+2] [+4] [+6]
//	Algorithm:
//	numToAdd = 1;
//	numToAdd += 2*1 until it hits 3^2, then
//  numToAdd += 2*2 until it hits 5^2, then
// 	numToAdd += 2*3 until it hits 7^2, then repeat until hits 1001^2
//	totalSum = 0;
//	totalSum += numToAdd everytie numToAdd changes
	
	

/*prob29*/
//	Purpose: all combinations of a^b, 2<=a,b<=100 WITHOUT REPS
//	Teaches: skill of using nested loops
	
//	Total combinations = a options* b options - REPS 	= (100-1)*(100-1)-REPS 		= 9801-REPS				REP occurrences 	powers of:
//																											4,8,16,32,64		2
// 	reps occur at powers of a, 	eg: for 2^b, reps occur at 4^b,8^b... [NOT multiples like 6^b]				9, 27				3
// 	reps occur uptil		 	eg: 4^b <= 2^100		2^2b <= 2^    1*100		
//								eg: 8^b <= 4^100		2^3b <= 2^    2*100		
//													For	  jb <=   (j-1)*100	 = largest power for rep
	
//	So all reps at eg: 16 = 2^4
//	are at powers   of 16 = 2^4b						  
//	Reps at 16 occur at: [reps++]						  4b <= 300			so when 4b == any 3n, 2<3n<300	OR 	4b | 3	[any power of 8]	4b<=300	[since 8^b <= 2^300]
//																								  				4b | 2	[any power of 4]	...
//																												4b | 1	[any power of 2]	...
//																												4b | i	0<i<4				4b<=[i-1]*100
//																												
//	Reps of 32 occur at:																						5b | i	0<i<5
//	Reps of 2^j ccur at:																						jb | i	0<i<=j-1	2<=j<7 	[since 128>100]


//	Repeat for a^j, a<=sqrt100 [since at a=11, 11^j>100 beyond range]
	
//	Nested loop order:
//		a nests j nests b nests i			a = bases of 2->10 [whose powers contain reps]
//											j = subdivision of a, checking special cases of a [powers]
	
	
	private static void uniquePows(long max) // in this case, 100
	{
		int reps = 0;
		ArrayList<Integer> powersToSkip = new ArrayList<Integer>();
//		since a^2 <= 100
		for (int a=2; a*a<=max;	   a++)
		{
//			find largest j
			int maxPower=0;
			int k=a;
			while (k <= max) 
			{
				k*=a;
				maxPower++;
				powersToSkip.add(k);
			}
//			SKIP a if its power is done
			if (powersToSkip.contains(a)) 	continue;
			
			for (int j=2; j<=maxPower; j++)
			for (int b=2; b<=max; 	   b++)
//				for eg: 32   32^b <= 16^100		16->8->4->2
				for (int i=j-1; j*b <= i*max; i--) // alsp accounts that 0<i<j
//					if 	32^b == some power below it
					if ((j*b)%i == 0) 
					{	
						reps++;
						break;
					}
		}
		long possibilities = (max-1)*(max-1) - reps;
		System.out.println("The number of unique powers are: " + possibilities);
			
	}
/*Oggggggggggggggggggggggg THIS CODE IS FRIGGIN PERFECT
 * WITHOUT USE OF BIGPOW TO SOLVE, CORRECT ANSWER AT 0.001S
 * logic was self-derived
 */
	
	
	
	
	
	
/*prob31*/ 		/*List mode*/
//	Purpose: find all ways to add to a, given certain values
//	eg: using {1,2,5,10} to add to 10
//
//	reqSum    = 10
//	values[i] = {10,5,2,1}		Start with values[0], keep adding until == reqSum
	
//								if adding values[i] > reqSum
//									try again with i++
//									until sum<=reqSum
//	
//	Problem: no way to rmbr prev. combos
//	Solution: Create List to track integers added, and the sum till that point
//			  extend the list after reducing/removing the last term
//			  eg: to get 10 : {5,5} {5,2..} {5,2,2,1}
//	
	private static void sumCombinations(int reqSum, int[] v)
	{
		int totalCombos = 0;
		
		LinkedList<Integer> addTerms = new LinkedList<Integer>();
		addTerms.add(v[v.length-1]);
		
			
		
		while (addTerms.size()-1>=0)
		{ 
			int last = addTerms.removeLast();
			int i=0;
			while (v[i] < last)
				i++;
			
			// reduce last addTerm eg: 10,5,5 -> 10,5,2
			if (last != 1)	
			{
				addTerms.add(v[i-1]);
				// extend addTerms with values <= prev. eg: 10,5,2 -> 10,5,2,2,1
				// break if sum == reqSum 	for effic.
				for (i=i-1; i>=0; i--)
					while (sumOfTerms(addTerms)+v[i] <= reqSum)
						   addTerms.add(v[i]);
					
				System.out.println(addTerms);
				totalCombos++;
			}
		}
		if (reqSum <100)	System.out.println("There are " +totalCombos+ " ways to split "   +reqSum+ " cents.");		
		else				System.out.println("There are " +totalCombos+ " ways to split a " +reqSum+ " dolla bill.");
	}
	
	//https://www.mathblog.dk
	private static int totalSumCombinationsv2(int reqSum, int[] v)
	{
		//eg: totalCombos[x] = partitions of x with values v
		int[] totalCombos = new int[reqSum+1];
		totalCombos[0] = 1;
		 
		for (int value : v) 
		for (int j = value; j <= reqSum; j++) 
		    totalCombos[j] += totalCombos[j - value];
		
		return totalCombos[reqSum];
	}
	//improved version, 1/2 process time
	private static int totalSumCombinationsv3(int reqSum, int[] v)
	{
		//eg: totalCombos[x] = partitions of x with values v
		int[] totalCombos = new int[reqSum+1];
		totalCombos[0] = 1;
		 
		//totalCombos[reqSum] += totalCombos[reqSum - 10] 1st iteration
		//					  +  totalCombos[reqSum - 20] 2nd iteration
		//					  +  totalCombos[reqSum - 50] 3rd iteration	
		for (int value : v)
		{
			for (int j = value; j <= reqSum-value; j++) // eg: totalCombos[reqSum-20] at 2nd iteration
				   totalCombos[j] += totalCombos[j - value];
			
			totalCombos[reqSum] += totalCombos[reqSum-value];
		}
		// what if you store each iteration in a 2D array to add modularity
		return totalCombos[reqSum];
	}
//	private static int[] bb()
//	{
//		LinkedList<Integer> startArray = new LinkedList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10));
//		LinkedList<Integer> totalCombos= new LinkedList<>();
//		for (int c : totalSumCombinationsv2(10,toArray(startArray)))
//			totalCombos.add(c);
//		
////		for (int reqSum=11; reqSum<16; reqSum++)
////		{
//		int reqSum = 11;
//			//totalCombos.set(reqSum, 0)
//			totalCombos.add(0);
//			//previous vals are done
//			startArray.add(reqSum);
//			// since totalCombos[j] only goes till old reqSum
//			// prepare totalCombos[] with the new reqSum before introducing new value
//			
//			// totalCombos[reqSum] eg:
//			// totalCombos[12] 			  += totalCombos[11] (1st iteration) + totalCombos[10] (2nd iteration) + ....
//			
//			for (int value:startArray)
//				totalCombos.set(reqSum, totalCombos.get(reqSum) + totalCombos.get(reqSum-value)); /*WRONG*/
//		return toArray(totalCombos);
//		
//	}
	
	
	private static int[] coinValues(int reqSum)
	{
		List<Integer> values = new LinkedList<>();
		int[] coinVals = {1,2,5};
		
			// i=4,i%3=1		2	*	10^1	=20
		for (int i=0;  coinVals[i%3]*pow(10,i/3)<=reqSum; i++)
			values.add(coinVals[i%3]*pow(10,i/3));
		
		return toArray(values);
	}


	
	private static int sumOfTerms(List<Integer> addTerms)
	{
		int sum=0;
		for(int term : addTerms)
			sum += term;
		return sum;
	}
	
	
	
/*prob32*/
//	Find all ab*cde = fghi, pandigital 1-9
//	ab*cde <= 9876

	private static void pandigital()
	{
		final int LAST_PERM_POS = 9*8*7*6*5;	//9 digits, 5 slots = 9!/(9-5)!
		for (int i=0; i<8000; i++)
		{
//			split result abcde to ab and cde
			ArrayList<Integer> dig = new ArrayList<Integer>(Arrays.asList(9,8,7,6,5,4,3,2,1));
			String abcde= lexPermString(i,5,dig);
			int abc 	= Integer.parseInt(abcde.substring(0,3));
			int de  	= Integer.parseInt(abcde.substring(3,5));
			String fghi	= Integer.toString(abc*de);
			
			if (abc*de > 9786) continue;

//			does not double check for pre-existing pans
			if (isPandigital(abcde + fghi))	
				System.out.println(abcde + fghi);
		}
	}
	
	private static boolean isPandigital(int n)
	{
		boolean isPan = true;
		int[] dig     = digits(n);
		
		for (int d=1;  d<digits(n).length; d++)
		for (int d2=0; d2<d; d2++)
				if (dig[d] == dig[d2])
				{
					isPan = false;
					break;
				}
		return isPan;
	}
	private static boolean isPandigital(String n)
	{
		boolean isPan = true;
		char[] dig 	  = n.toCharArray();
		for (int d=1;  d<dig.length; d++)
		for (int d2=0; d2<d; 		d2++)
				if (dig[d] == dig[d2])
				{
					isPan = false;
					break;
				}
		return isPan;
	}
	
	
	
// Search range is from 10 -> 99....999. The question is: how many 9's?
//	x digits, so fact sum of digits is 9! +...x times...+ 9!
//			  		which should be < 99999...x times...9999		9!x < sigma(9*10^x)		8!x < sigma(10^x)	x<6.3 digits, or range <2291000
	
//	Pointless to search all i 0<100000, because of repetitions such as 1135,3151,5113 etc.
//	Search in a way that repeated nos. not included
//	0003    0123
//	0013    1123
//	0113    0223
//	1113    1223 
//	0023    2223....
	private static void orderlessNos()
	{
		for (int h=0; h<10; h++)
		for (int i=0; i<=h; i++)
		for (int j=0; j<=i; j++)
		for (int k=0; k<=j; k++)
		for (int l=0; l<=k; l++)
		for (int m=0; m<=l; m++)
		for (int n=0; n<=m; n++)
		{
			int num =     h*1000000+i*100000 + j*10000 + k*1000 + l*100 + m*10 + n;
			int factSum = fact(n)+fact(m)+fact(l)+fact(k)+fact(j)+fact(i)+fact(h);
			
			
//			Integer[] fact = (Integer[])digits(factSum);
//			Arrays.sort(fact, Collections.reverseOrder()); //descending order
//			int orderedFact = 0;
//			int div = 1000000;
//			
//			for (int q=0; q<fact.length; q++)
//			{
//				orderedFact += fact[q]*div;
//				div/=10;
//			}
//			if(orderedFact - num < 10 && orderedFact - num > -10)
//			{
//				System.out.println("orderedFact is " + orderedFact);
//				System.out.println("num is " + num);
//				System.out.println("resulting in a value of" + factSum);
//			}
			//is 5331 == 3153
			//if (/*paow == soe order of nlkji*/);
		}
		/*DOESN't WORK*/
		
	}

/*prob39*/
// 	Purpose : find  max pythagorean triplets so a+b+c = P
//	c = sqrt a2 + b2						a+b+ sqrt(a2+b2) = P		a = P(P-2b) / 2(P-b)
	
//	Search values : 1 <=b <= a
//	then P++
	
//	To check integer solutions, check if a is integer 			OR		P(P-2b) % 2(P-b) == 0
	
	private static void pythPeriTriplets (int perimeter)
	{
		int maxTriples = 0;
		int maxP = 0;
		
		for (int p=12; p <= perimeter; p++) 	
		{
			int triples = pythTriples(p);
			if (triples>0)
				System.out.print("are Pythagorean triples adding to " + p + "\n\n");
			
			if (maxTriples < triples) {
				maxTriples = triples;
				
				maxP = p;
			}
		}
		
		System.out.println("\n\nThe perimeter with max solutions of Pythagorean Triples is "+ maxP+
						   "\nThe solutions are :\n" + pythTriples(maxP));
	}
//	returns the no. of triples adding to a perieter
	private static int pythTriples (int P)
	{
		int triples = 0;
//					   b <= a
		for (int b=1;  b <= P/2 * (P-2*b)/(P-b); b++)
		{		//	a is an integer
				if (P*(P-2*b) % (2*(P-b)) == 0)
				{
					int a = P/2 * (P-2*b)/(P-b);
					int c = P -b-a;
					System.out.println(b+","+a+","+c);
					triples++;
				}
				
		}
		return triples;
	}
	
	
	
	
/*prob41*/	
	private static void maxPanPrime ()
	{
//		all combos of 987654321 onwards
//		all combos of 87654321 onwards... and so on, taking out a slot with each iteration
		ArrayList<Integer> dig = new ArrayList<Integer>(Arrays.asList(9,8,7,6,5,4,3,2,1));
		while (dig.size()>1)
		{
			for (int i=0; i<fact(dig.size()); i++)
			{
				int pan = Integer.parseInt(lexPermString(i,dig.size(),dig));
				if (isPrime(pan))
				{
					System.out.println(pan);
					return;
				}
			}
			dig.remove(0);
		}
	}
	
	
/*prob43*/
	private static void subStrdiv()
	{
		
		int[] primes = {2,3,5,7,11,13,17};
		ArrayList<Integer> pans = new ArrayList<Integer>(Arrays.asList(9,8,7,6,5,4,3,2,1,0));
		for (int i=0; i<fact(10); i++)
		{
			String pan = lexPermString(i,10,pans);
			
			for (int j=0; j<primes.length; j++)
			{
				int substring = Integer.parseInt(pan.substring(j+1, j+3+1));
				if (substring%primes[j] != 0)	break;
				
				if (j == primes.length-1)	System.out.println(pan);
			}
			
		}
	}
	
/*prob44*/
// 	Purpose : Find smallest Pi - Pj [penta nos.] where Pi + Pj is also penta
//	Lesson : Unlike calculus, finding min(x) is easier, use that as a terminating condition

//	check P10 - P9, P10 - P8.... if penta
//	the first Pi - Pj found is smallest

//	THEN check if Pi + Pj is penta
	private static void p()
	{
		for (int i=1; i<10000000; i++)
		{
			int Pi = i*(3*i-1)/2;
			
			// iterate thru Pi - Pj, from i-1=>0
			for (int j=i-1; j>0; j--)
			{
				int Pj = j*(3*j-1)/2;
				if (isPenta(Pi - Pj) && isPenta(Pi + Pj))
				{
					System.out.println(Pi + ", " + Pj);
					break;
				}
				
			}
		}
	}
	
//	Penta nos. P = n[3n-1]/2				OR 	n = -1 + sqrt 1+24P /6
// 	if n is whole, P is penta
	private static boolean isPenta(int p)
	{
		double penTest = (Math.sqrt(1 + 24*p) + 1.0) / 6.0;
	    return penTest == ((int)penTest);
	}
	
	

	
/*prob46*/
// 	Purpose : Find no. which breaks goldbach's other conjecture OC = 2*S + P
	
// 	Efficiency : P's {11,13,17,19} are expensive 
//			vs 	 S's {25,36,49,64}
//	So for each OC, go down P's 	and check if OC - P = 2S	S = OC - P/2	
//	check if perfect square, if so, move on
	
//	Track any P's that OC avoids as it goes up, and add to list
	
	private static void goldbachDisproof()
	{
		ArrayList<Integer> primes = new ArrayList<Integer>();
		primes.add(2);
		OC : for (int i=3; i<888880; i+=2)
		{
			if (isPrime(i))		{primes.add(i);		continue OC;}
			
			for (int prime : primes) 	
				if (isPerfectSquare((i - prime)/2)) continue OC;
		
			System.out.println(i);
			
		}
	}
	
/*prob47*/	
	private static void distinctFactors()
	{
		
		int[] primes = primes(2,1000000).stream().mapToInt(i -> i).toArray();
		
		int 	consecutiveNos   = 0;
		boolean has4PrimeFactors = false;
		
		for (int i=10; i<100000; i++)
		{
			if (has4PrimeFactors(i,primes))	consecutiveNos++;
			else 							consecutiveNos=0;
			
			if (consecutiveNos == 2)
			{
				System.out.println(i);
				break;
			}
		}
	}
	private static boolean has4PrimeFactors(int n, int[] primeList)
	{
		int factors = 0;
		for (int prime : primeList)
		{
			if (n%prime == 0) 	factors++;
			if (factors >=  4)	return true;
		}
		return false;
		
	}
	
/*prob50*/
	//Purpose: find consecutive sum of primes < 1000000
	// f(i) = sigma(primes<i)
	// so find largest f(j) - f(i) < 1000000
	
	// i++ if sum < 1000000 and reset j=0
	// j++ if sum > 1000000
	
	// Efficiency: store all values of f(largest prime < 1000000) in array, so avoid recalculating sum\

/*prob54: solved in different class*/
	
/*prob57*/
//	Purpose: express sqrt 2 as continued fraction to analyze its numerator/denominator
//	x = 2+1/2		1/(2+1/(2+1/(2+1/x)))
//	y = 2+1/x		1/(2+1/(2+1/y))
//	z = 2+1/y		1/(2+1/z)				Notice recurring theme? x = 2+1/x

//	// x=(2+1/2)	= 5/2
//	n = 1;		d = 2;		//1/2
//	n = n + d*2;			//1/2 + 2
//	
//	// x=2+1/x		2 + 2/5		= (10 + 2)/5
//	int nStore = n;
//	n = d;		d = nStore; //2/5				// swap n and d
//	n = n + d*2;			//2/5 + 2
//
//  Rinse and repeat, until the last step, where you add 1 instead of 2
	
	private static void infiniteFrac()
	{
		// x = n/d
		// x = 1/2
		BigInteger n = BigInteger.valueOf(1);	BigInteger d = BigInteger.valueOf(2);
		int digitDifference = 0;
		
		for (int i=0; i<1000; i++)
		{
			// add 2
			// 	n+d*2
			n = n.add(d.multiply(BigInteger.valueOf(2)));		
			// 1/x
			BigInteger nStore = n;
			n = d;		
			d = nStore;
			
			// x = 1 + n/d
			BigInteger sqrtNumerator = n.add(nStore);
			BigInteger sqrtDenominator = d;
			
			// if n has more digits than d
			if (sqrtNumerator.toString().length() > sqrtDenominator.toString().length())
				digitDifference++;
			
			System.out.println(sqrtNumerator + "/" + sqrtDenominator);
		}
		
		System.out.println("There are " + digitDifference + " instances where the numerator"
		+ " has more digits than the denominator");
	}

/*prob 58*/
// 	Purpose: (see prob28) check spiral diagonals for primes, and find the ratio of primes
//	Summary: Start at 1, +2 4 times, +4 4 times, +6 4 times....
//	Track if each number prime
//	Create ratio: primes/all nums, stop if <10%
	private static void spiralPrimes()
	{
		long diagonal = 1;
		int  numToAdd = 2;
		int primeDiagonals = 0, totalDiagonals = 0;
		
		while(true)
		{
			for (int i=0; i<4; i++)
			{
				if (isPrime(diagonal))
					primeDiagonals++;
				totalDiagonals++;
				
				// to avoid closing before a good sample size reached
				if (totalDiagonals > 10)
					if ((double)primeDiagonals/totalDiagonals < 0.10)
						return;
				
				
				System.out.print(diagonal + " ");
				if (i == 0)
					System.out.println();
				diagonal+= numToAdd;
			}
			numToAdd += 2;
		}
	}
	
	
	
/*prob59*/
//	Purpose: decrypt the message from the ascii numbers, given looping key 3 characters long
//	ASCII ciphertext character XOR key character = plaintext character

// 	the 3 characters of key are {a,b,c...z} lowercase
// 	so XOR plaintext with any 3 combination of these, in a loop
//	for (int char1=97; char1<123; char1++)
//		for (int char2=97; char2<123; char2++)
//			for (int char3=97; char3<123; char3++)
// 	combinations = 26^3
// 	
//	EFFICIENCY: use individual loops for char1,2,3 to avoid all 26 combos
	private static void keypadDecryption() throws FileNotFoundException
	{
		Scanner sc = new Scanner(new File("src//prob59.txt"));
		
		// int array of ASCII cipherText nums
		ArrayList<Integer> cipherNums = new ArrayList<Integer>();
		for (String num : sc.nextLine().split(","))
			cipherNums.add(Integer.parseInt(num));
		
		// choose 3 chars from {a...z} as key eg: {c,b,x}
		// since each char checks special positions eg: char 1 checks pos 1,4,7,10...
		// narrow positions to check down first
		// and now recheck for these selected positions
		// since they are GUARANTEED to not print useless characters
		for (int char1 : getSafeValues(cipherNums,0))
		for (int char2 : getSafeValues(cipherNums,1))
		for (int char3 : getSafeValues(cipherNums,2))
		{
			int[] key = {char1, char2, char3};
			String plainText = "";
			// cycle thru key chars and xor with plaintext char
			for (int i=0; i<cipherNums.size(); i++)
			{
				int plainNum = key[i%3]^cipherNums.get(i);
				plainText += (char) plainNum;
			}
			
			// (The Gos....
			if (plainText.toCharArray()[0] == '(')
				System.out.println(plainText);
		}
	}
	// returns key char positions that don't yield wierd plaintexts
	private static List<Integer> getSafeValues(ArrayList<Integer> cipherNums, int pos)
	{
		List<Integer> safeValues = new ArrayList<Integer>();
		
			// cycle thru key chars and xor with plaintext char
			for (int keyNum=97; keyNum<123; keyNum++)
			for (int i=pos; i<cipherNums.size(); i+=3)	
			{
				
				int plainNum = keyNum^cipherNums.get(i);
				//if ascii number refers to a character
				if (plainNum < 126 && plainNum > 32)
					safeValues.add(keyNum);
			}
		
		return safeValues;
	}
	
/*prob61 - done in separate class*/
	
	
	
	
	

	
/*prob64*/	
	
//STEP 1		STEP 2			STEP 3								STEP 4				STEP 5
//a0 = 4,	 	1/(sqrt23—4)	= (sqrt23+4)/7	(after *conjugate)						= 1 + (sqrt23—3)/7 	(1 = (int) 1/(sqrt23—4))
//a1 = 1,	 	7/(sqrt23—3)	= 7(sqrt23+3)/14					= (sqrt23+3)/2		= 3 + (sqrt23—3)/2
//a2 = 3,	 	2/(sqrt23—3)	= 2(sqrt23+3)/14					= (sqrt23+3)/7		= 3 + (sqrt23—3)/2
	private static List<Integer> infFracListOld(int square)
	{
		if (isPerfectSquare(square))
			throw new ArithmeticException("Only non-squares allowed");
		ArrayList<Integer> periodList = new ArrayList<>();
		
		double x= Math.sqrt(square);
		double num=x; 			double den= 1;
		
		// eg: sqrt 23=4, so = 4+sqrt23-4
		int a = (int) x;		periodList.add(a);
		
		// used to check if future fracs are repetitions
		double periodCheck = 1/(x-a);
		
		do
		{
			//STEP 5
			//eg: (sqrt23-3)/7 = 1 + (sqrt23+4)/7 
			//separate num from a
			num-=den*a;
				
			
			//STEP 2
			double numStore = num;
			num=den;			den=numStore; //sqrt23 - 3
			
			
			//STEP 3
			//conjugate to simplify denominator eg: (sqrt23-4) -> 7
			double conj1=x; 	double conj2=den-x; 
								den=Math.round(den*(conj1-conj2));
			
			//STEP 4
			// eg: 7(sqrt23+3)/14, factor out 7/14
			int hcf = hcf((int)num,(int)den);				
			num /= hcf;			den /= hcf;
			num*=conj1-conj2;
			
			//STEP 1
			a = (int) (num/den);	periodList.add(a);
		}
		// check if a is going to repeat in the future
		// how? thru its repeated fraction expression
		while ((int) (den/(num-den*a)*100000000) != (int)(periodCheck*100000000));
		return periodList;

	}
	
	
//	used a faster algorithm, from https://www.mathblog.dk/files/euler/Problem64.cs	
	private static LinkedList<Integer> infiniteFracPeriodList(int square)
	{
		
		if (isPerfectSquare(square))
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
	
	private static BigInteger[] getSquareConvergent(int square, long pos)
	{	
		LinkedList<Integer> periodList = infiniteFracPeriodList(square);
		
		// store current/previous numerators/denominators, num0 is current
		BigInteger[] num = new BigInteger[3];			
		BigInteger[] den = new BigInteger[3];
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
			if (isPerfectSquare(D))
				continue;
			// https://math.stackexchange.com/questions/1794936/estimation-on-the-accuracy-of-the-convergents-of-sqrtn
			BigInteger num = getSquareConvergent(D,11)[0];
			BigInteger den = getSquareConvergent(D,11)[1];
			
			for (BigInteger y=big(1); 	true; 	y=y.add(big(1)))
			{
				//(num*y+den)/den 							= sqrtD*y+1
				BigInteger x = num.multiply(y).add(den).divide(den);
				//	x2		 	 	- 		Dy2								==  1
				if (x.pow(2)	.subtract	(y.pow(2).multiply(big(D)))	.equals	(big(1)))
				{
					System.out.println(x + "^2 - " + D + "*" + y + "^2 = 1" );
					continue k;
				}
			}
			
//				for (int y=1; 			true; 		 y++)
//				for (int x=sqrtD*y; x<(sqrtD+1)*y; x++)	// old
//					if (x*x - D*y*y == 1)
//					{
//						System.out.println(x + "^2 - " + D + "*" + y + "^2 = 1, " + (int)(Math.sqrt(D)*y));
//						continue k;
//					}
				
					
					//  x2 	 						== Dy2 + 1
					//num/den						== Dy2 + 1
					//num	 						==(Dy2 + 1)den
					//num = sqrtNum*y				den = sqrtDen 
//					if (pow(n*y,2) 	== (D*y*y + 1)*pow(d,2))
				
		}
	}

	
/*prob68*/
//	Purpose: pentagon ring with each straight link add to common sum [a+b+e = f+d+b = j+g+d = i+h+g = c+e+h]
//		a   	
//	        b	  j
//		e	    c
//	d	  g   i   h	
//			f
//	
//	EFFICIENCY
//	Notice max 2 links have 1 term eg: only a,b,e/f,d,b have b
//	minimum sum? 1+2+10
//	iterate thru permutations reverse order
//	so the sum formed has largest string
	private static void pentagonRing()
	{
		// each node in ring contains a different value 1-10
		// reverse order so the first common sum array found is largest string
		int[] nodeVals = {10,9,8,7,6,5,4,3,2,1};
		
		k : for (int[] vals : allPermutations(nodeVals))
		{	
			int[] sums = 
			{
				vals[0] + vals[1] + vals[4],
				vals[5] + vals[3] + vals[1],
				vals[9] + vals[6] + vals[3],
				vals[8] + vals[7] + vals[6],
				vals[2] + vals[4] + vals[7]
			};
			
			// check if the common sum condition is met
			for (int i=1; i<sums.length; i++)
				if (sums[i] != sums[i-1])
					continue k;
			
			for (int value : vals)
				System.out.print(value + " ");
				System.out.print("    "+ sums[0]);
			System.out.println();
			// since the first set of vals found is the largest string
			// eg: 10 5 8 9 1 2 3 7 6 4 is the first found
			break;
		}
		
		SortedSet<Integer> a = new TreeSet<>();
		a.add(2);
		
		
	}
	
	
	
/*prob73 - done in separate class*/					

/*prob78 - see prob31*/
//Purpose: p(n) = number of ways to split n beads = prob31 with array of values 1=>n
//			Find p(n) | 1000000
// This looks like prob 31 with a layer on top
// to solve with larger numbers, iterate through math equation
	


/*prob79 - done in separate class*/
//	Purpose: Guess shortest password from list of 3 character guesses, condition: each character in order
// 	Setup a list of characters, move characters behind other characters depending on the guess order
//	if the password is 123456, and guesses {1,3,5} {2,4,6} {2,3,5}
//	list = {1,3,5,2,4,6} using guess {2,3,5}
//							we get	 {1,2,3,5,4,6}
	

	
//	131	673 234 103 18			
//	201 96  342 965 150
//	630 803 746 422 111
//	537 699 497 121 956
//	805 732 524 37  331
/*prob81*/
//	Purpose: Find optimal path in rectangle array
//	Conditions: Clear starting/ending point		2 directions: down/right
//   *   *   *   *   *   * 		 *   *   *   *   *   * 
//   
//   *   *   *   *   *   *		 *   *   *   *   *   * 
//   
//   *   *   *   *   *   *		 *   *   *   *   *   * 
//   
//   *   *   *   *   *   *		 *   *   *   *   *   * 
//   				 _						 _				 _
//   *   *   *   *  |*|  O		 *   *   *  |*|  O   * 		| | += min(O,O), repeat going left 
//   
//   *   *   *   *   O   * 		 *   *   *   O   *   * 		once row complete move up
	
	private static int[][] rectanglePath = {	{131, 673, 234, 103, 18 },
												{201, 96,  342, 965, 150},
												{630, 803, 746, 422, 111},
												{537, 699, 497, 121, 956},
												{805, 732, 524, 37,  331}};
	private static void twoWayPathSum(int[][] path)
	{
		int[][] pathCopy = deepCopy(path);
		boolean[][] downOrRight = new boolean[path.length][path[0].length];
		
		//special case, when current cell is at bottom/right
		int lastRow = path.length-1;
		int lastColumn = path[0].length-1;
		for (int i=lastRow-1; i>=0; i--) 
		{
			// right most cells
			path[lastRow][i]+= path[lastRow][i+1];
			downOrRight[lastRow][i] = false;
			
			// bottom most cells
		    path[i][lastColumn]+= path[i+1][lastColumn];
		    downOrRight[i][lastColumn] = true;
		}
		//main case
		for (int i=path.length-2; 	i>=0; i--) 
		for (int j=path[i].length-2;j>=0; j--)
		{	// 						down cell		right cell	
		    path[i][j] += Math.min(	path[i+1][j], path[i][j+1]);
		    // store direction towards chosen path for later
			downOrRight[i][j] = 	path[i+1][j] < path[i][j+1];
		}

		//print the path of numbers
		int spaces=0;
		int x = 0;
		int y = 0;
		int totalMoves = path[0].length + path.length-1;
		for (int a=0; a<totalMoves; a++)
		{
			int[] prevCo = {x, y};
			System.out.print(pathCopy[prevCo[0]][prevCo[1]]);
			
			boolean direction = downOrRight[x][y];
			// if direction == down
			if (direction == true)	x++;
			else					y++;
			int[] co = {x, y};
			
			// if co is down/right wards of prev co
			if 		(co[0] > prevCo[0]){
				spaces=0;
				System.out.println("\n\n");
			}
			for (int s=spaces; s<co[1]; s++){
				System.out.print("\t");
				spaces++;
			}	
		}
	}
	
/*prob83 - Print mode*/
//	Purpose: Find optimal path in rectangle array
// 	Condition: Clear starting/ending point		4 directions
	
//	Approach 1: use Kruskal's algorithm, keep building/connected lowest vertices
//	Unless it forms a cycle
// 	FAIL: why?
//	*    10   10		*    10   10		*	 *    *
//	11   99   10		11   99   10   		11   99   *   
//	 0   99   10    	*    99   10		*    99   *
//	11    0   *     	11   *    *         11   *	  *		Solution found, not optimal
	
//	Approach 2: A star: tip: H-cost: overestimate for speed, underestimate for precision
//	https://cs.stackexchange.com/questions/30778, so in this case, underestimate
//	https://www.youtube.com/watch?v=-L-WgKMFuhE

//	Approach 3: Dijkstra's: because H-cost heuristic too inaccurate
//	Since it assumes each nodes' H-cost = 0, it is accurate af
	

/*prob84 - relies on too much probability/not enough conceptual knowledge*/
	

/*prob85*/
// Purpose: find no. rectangles fittable in a larger box

//	 _ _ _  . .  ^	rectangle width/height = w/h
//	|	  | . .  |	box width/height = W/H
//	|_ _ _| . .  4
//	. . . . . .  |	
//	. . . . . .  v
//	
//	<----5---->
//
//	Fittable rectangles with fixed w/h 		= (W-w+1)*(H-h+1)
//	Fittable rectangles with changing w		= (W-1+1)*(H-h+1) + (W-2+1)*(H-h+1).... + (0+1)*(H-h+1)
//											= (sum(W-1)+W)*(H-h+1)
//	Fittable rectangles with changing h 	= (W-w+1)*(sum(H-1)+H)
//  Fittable rectangles with changing w/h	= (W-1+1)*(sum(H-1)+H) + .....
//											= (sum(W-1)+W)*(sum(H-1)+H)
//											= ((W-1)W/2+W)*((H-1)H/2+H)
//											= (W^2 + W)/2 *(H^2 + H)/2
	
	
/*prob86*/
//	Purpose: find no. integer solutions for all cuboids till dimension M, 
//	solution = shortest route traversing surface btwn 2 diagonally opposite corners
//	Easy if you know calculus
//
//	route length= w^2 + (l-x)^2 + x^2 + h^2, l,w,h = length, width, height
//	y 			= w^2 + l^2 + h^2 + 2x^2 - 2lx		draws out a parabola, so find the lowest point of y (at gradient==0)
//	gradient dy	= 4x - 2l
//	lowest point at x = l/2
//
//	shortest route = w^2 + l^2/2 + h^2 
//	Is integer depends if l^2/2 is integer
//	if l is even, shortest route is integer

	
/*prob88*/
//	Purpose: find smallest no. with n terms product = sum	eg: 5 terms = 1*1*2*2*2 = 1+1+2+2+2
//	Lesson: Alternative to nested loop: use an array with a dynamic index, which iterates and resets
//	Source: http://www-users.mat.umk.pl/~anow/ps-dvi/si-krl-a.pdf
//
//	store in array terms >1 to multiply
//	term1++
// 	IF product >= sum, reset term1, 
//	term2++, repeat				
//
//	eg: 5 terms = 2*2 < 2+2 + 3*1 = 7		= 3*3 == 3+3 + 3*1 = 9	max sum == 9	product>=sum on 1st iteration, term3++
//				= 2*4 < 8+1		  	
//				= 2*5 ==9+1 CONDITION2, sum == 10
//
//				= 2*2*2 == 2+2+2 + 2*1												product>=sum on 1st iteration/all lowest term, break;
//
//	Max >1 terms = 1+[log2(n)]
//	if terms >= 2, product > sum growth rate 
// 	perfect array size = x, where 2^x <= noTerms - 1*x
//	eg: noTerms=40, array is [2,2,2,2,2]
//
//	Nested loop? for each array element
//	for (i=prevI; sum<=product && sum<=min; i++) ... x times	
//	make sure future elements altered in loop start 
//	eg: [2,2,2,8,9] -> [2,2,3,3,3] NOT [2,2,3,2,2] for proper sum calculation
//
//	OR increment different indexes when needed
	private static int[] minProductSumOld(int noTerms)
	{
		double log2OfN = Math.log10(noTerms)/Math.log10(2);
		int noNon1Terms= 1+((int)Math.floor(log2OfN));
		int[] terms = new int[noNon1Terms];
		// minimum terms [1,1,1,1,1,2,2] 
		Arrays.fill(terms, 1);
		
		// tracks the lowest index incremented
		int last=terms.length-1;
		int i = last;
		int min=1000000000;
		int[] minTerms = new int[terms.length];
		// all indexes incremented to the point product>=sum
		// further increments impossible
		while(--i != -1)
		{
			// increment next term and set future values == to it
			// eg: [2,2,2,5,6]  -> [2,2,3,3,3]
			terms[i]++;
			for (int k=i; k<terms.length; k++)
				terms[k] = terms[i];
			
			// since all the 1's not included in "terms"
			int sum = noTerms-terms.length + sum(terms); 
			while (product(terms)<=sum && sum<=min)	
			{
				// since now NOT on lowest terms
				// 2nd/3rd/4th.... element can go higher
				i = last;
				if(product(terms)==sum)
				{
					min = sum;
					minTerms = terms.clone();
				}
				terms[last]++;
				sum++;
			}
		}
		return minTerms;
	}
//	to print the values out
// 	for (int a : terms)
//		System.out.print(a + ",");
//	System.out.println(x);
//
//	change the while loop into an if statement to reduce complexity
//	How? 
//												eg: [1,1,1,1,1,1,2,x] 	[1,1,1,1,1,3,3,x]			[1,1,1,1,1,1,3,x]	
//	2*2*3*x		 	 <= 1+..+1+2+2+3+x			equivalent				overestimate				underestimate
// 		ax			 <= b+x						2x <= 8+x				9x <= 11+x					3x <= 9+x
//	3<=	x			 <= b/(a-1)					x  <= 8, or x=8			x  <= 11/8, but x >= 3		x  <= 9/2, x=4.5 (not int)
//	if x = 3.6, means   b>a-1 or product<sum	incrementIndex=last		incrementIndex unchanged	incrementIndex=last
//
//	if x >= prevTerm
//		if x is whole
//			min = sum
//		incrementIndex = last
	private static int minProductSum(int noTerms)
	{
		double log2OfN = Math.log10(noTerms)/Math.log10(2);
		int noNon1Terms= 1+((int)Math.floor(log2OfN));
		//exclude the last term, since it is to be calculated
		// eg: [1,1,1,1,1,1,2,x]
		int[] terms = new int[noNon1Terms-1];
		// minimum terms [1,1,1,1,1,2,x] 
		Arrays.fill(terms, 1);
		
		// tracks the lowest index incremented
		int last=terms.length-1;
		int i = last+1;
		int min=0;
		// all indexes incremented to the point product>=sum
		// further increments impossible
		while(--i != -1)
		{
			// increment next term and set future values == to it
			// eg: [2,2,2,5,x]  -> [2,2,3,3,x]
			terms[i]++;
			for (int k=i; k<terms.length; k++)
				terms[k] = terms[i];
	
			// since all the 1's not included in "terms", last term excluded eg: [1,1,1,1,1,1,2,x]
			int sum = noTerms-1-terms.length + sum(terms);
			int product = product(terms);
			int x = sum/(product-1);
			
			if (x >= terms[last])
			{
				i = last;
				if (sum%(product-1) == 0)
					min = sum+x;
			}
		}
		return min;
	}

	
	private static int sumOfMinProductSums(int cap)
	{
		Set<Integer> a = new HashSet<>();
		for (int i=2; i<=cap; i++)
		{
			int[] minTerms = minProductSumOld(i);
//			for (int t : minTerms)
//				System.out.print(t + ",");
//			System.out.print("\t");
			
			int sum = i-minTerms.length+ sum(minTerms);
//			System.out.println(sum);
			a.add(sum);
		}
		int sumSum = 0;
		for (int num : a)
			sumSum+=num;
		return sumSum;
	}
	
	
/*prob91 - done in separate class*/
//	Purpose: find all right triangles in a grid, with 1 point on origin
//	* *	* *
//	* *	* *																						*		  	  *				*		  	  *
//	* *	* *									*		  	  *				*		  	  *
//	O *	* *		O * *		O *   *			O *			O *			O *			O *				O *			O *			O *			O *			etc
//
//				
//																								*			  *				*			  *
//											*			  *				*			  *			
//							O   * *			O	*		O	*		O	*		O	*			O	*		O	*		O	*		O	*		etc		 
//	
//	To get all possible triangles
//	a) move 1st* to the end, then up
//	b) Once 1st* end, move 2nd* right, repeat a)
//
//	
//	To get right triangles: OPTION 1
//	Calculate 3 angles: O-1st*-2nd*
//	------*------		Notice 3 right triangles formed between points
//	|	  b		|		bottom-right, top-right, top-left
//	| 	  		|		Use this to calc angles a,b,c		note:  x(a) = x coordinate of a, take the abs of x(b-a)
//	|		   a*													if tan-1 = infinity, angle = 180
//	| c			|		angle a = tan-1(y(a) / x(a)) + tan-1(y(b-a) / x(b-a)) 
//	O___________|		
//	
//	
//	To get right triangles: OPTION 2
//	No need to calculate angles, notice patterns in grid right triangles
//																*
//	  *			2 up, 1 right, 										*	2 up, 1 right		ratio
//		  		4 right, 2 down		simplify to lowest terms			2 left, 1 up		-1/ratio		gradients m1*m2 = -1
//	O		  *													O	
//	
//	All Possibilities = sum(gridX*gridY - 2);
	
	
/*prob93*/
// 	Purpose: Given +,-,*,/ and () over 2/3 terms, form all possible expressions with numbers{a,b,c,d}
//	Create an expressionBuilder?
//
//	() - choose starting point, auto assign ending bracket 
//	() for 2 terms - behind number
//	() for 3 terms - behind number/bracket 	
//	How to find which number to put bracket behind?
//	OR manually input all options
//	No need for bodmas, cuz covered by bracket combos
//	a*b*c*d														-NOT counted.
//	(a*b)*c*d		a*(b*c)*d		a*b*(c*d)					-NOT counted.
//	(a*b)*(c*d)
//	(a*b*c)*d						a*(b*c*d)					-NOT counted.
//	((a*b)*c)*d		(a*(b*c))*d		a*((b*c)*d)		a*(b*(c*d))
//
//	Total possibilities = 3024*64*5
	private static void arithmeticExpressions()
	{
		int[] dig 	= {0,1,2,3,4,5,6,7,8,9};
		char[] ops	= {'*','/','+','-'};
		int maxRangeSize = 0;
		int[] maxDigits = new int[4];
		
		
		// 9! / (9-4)! = 3024 possibilities
		for (int[] digits : allPermutations(4,dig))
		{	// stores all solutions a=>bum
			Set<Integer> range = new TreeSet<>();
			
			// 4*4*4 = 64 possibilities
			for(char op1 : ops)				
			for(char op2 : ops)
			for(char op3 : ops)					
			{
				int a = digits[0];
				int b = digits[1];
				int c = digits[2];
				int d = digits[3];
														/*are these wrong?*/
				// 5 possibilities			
				try {int number  = solve(solve(a,op1,b),	op2,	solve(c,op3,d));range.add(number);} 	catch(ArithmeticException e) {}	// (a*b)*(c*d)
				try {int number2 = solve(solve(solve(a,op1,b),op2,c),	op3,	d);	range.add(number2);}	catch(ArithmeticException e) {}	// ((a*b)*c)*d	
				try {int number3 = solve(solve(a,op1,solve(b,op2,c)),	op3,	d);	range.add(number3);}	catch(ArithmeticException e) {}	// (a*(b*c))*d		
				try {int number4 = solve(a,	op1,	solve(solve(b,op2,c),op3,d)); 	range.add(number4);} 	catch(ArithmeticException e) {}	// a*((b*c)*d)	
				try {int number5 = solve(a,	op1,	solve(b,op2,solve(c,op3,d)));	range.add(number5);} 	catch(ArithmeticException e) {}	// a*(b*(c*d))
			}
			
			// since -ve nos./0 not required
			range.removeIf(i -> {return i<=0;});
			// 1=>n consecutive only
			List<Integer> range2 = new LinkedList<>(range);
			for (int i=1; i<=range2.size(); i++) 
				if (range2.get(i-1) != i)
			    {
			    	int removeIndex = i;
			    	range2.removeIf(j -> {return j>removeIndex;});
			    	break;
			    }
			// {a,b,c,d} that obtains largest range
			if (range2.size() > maxRangeSize)
			{
				maxRangeSize = range2.size();
				maxDigits = digits;
			}
		}
	}
	private static int solve(int num1, char operation, int num2)
	{
		if (operation == '/')
			if (num2 == 0 || num1%num2 != 0)			/*is this wrong?*/
				throw new ArithmeticException();
		switch(operation)
		{
			case '*' : return num1*num2;
			case '/' : return num1/num2;
			case '+' : return num1+num2;
			case '-' : return num1-num2;
		}
		throw new ArithmeticException();
	}
	
	private static int bodmasSolve(int num1, char op1, int num2, char op2, int num3, char op3, int num4)
	{
		if ((op1== '*' || op1=='/') && (op3== '*' || op3=='/'))
		{
			int a = solve(num1, op1, num2); 
			int b = solve(num3, op3, num4);
			return solve(a,op2,b);
		}
		else
		{
			int a = (op1== '*' || op1=='/') ? bodmasSolve(num1, op1, num2, op2, num3) 
											: bodmasSolve(num2, op2, num3, op3, num4);
			return (op1== '*' || op1=='/') 	? solve(a, op3, num4)
											: solve(num1, op1, a);
		}
	}
	
	private static int bodmasSolve(int num1, char op1, int num2, char op2, int num3)
	{
		int a = (op1== '*' || op1=='/') ? solve(num1, op1, num2) 
										: solve(num2, op2, num3);
		return (op1== '*' || op1=='/') 	? solve(a, op2, num3)
										: solve(num1, op1, a);
	}
	
	
/*prob96 - done in separate class*/
//	Purpose - solve a hard case of "guess and test" sudoku
//	What makes this difficult is multiple layers of testing required
//	To find the right solution
//	
//	Detect "contradictions" in guess-and-test when a cell has no possibilities
//	Detect "stuck" - when puzzle has no change
//
//	In guess-and-test, prefer cell w/ smallest possibilities eg: [2,5] vs [1,2,6,8,9]
//	Why? lockdown cell's value faster 
//
//	OPTION 1: Nested Loop
// 	A = cell with lowest possibility
// 	test A's 1st value 
// 		break if contradiction found
// 		if stuck
// 			test next A's 1st value
//				.
//				.
//				.
//	OPTION 2: Array tracking altered cells
//	Choose the cell, and try solving with this value
//	if stuck, choose a 2nd cell and try again
//	if contradiction, un/rechoose 2nd cell and try again
//
// 	A = cell with lowest possibility
// 		test A's 1st value 
// 		if contradiction found
// 			test 2nd value
// 		if stuck
// 			test next A's 1st value
//		if array corrupted
//			reset array (original unaltered NOT prev)
/*Fast solution < 0.4 secs (faster? less methods/single class but kept for readability)*/
	

	

/*prob101 - not done but concept learned*/
//	Purpose: find approximating functions to n-1 degrees given n points/terms 
//	Lesson: you can create polynomial functions given terms using Lagrange interpolating functions
//	eg: 1,5,10,20 gives a cubic function
//	https://math.stackexchange.com/questions/523907/explanation-of-lagrange-interpolating-polynomial
	
/*prob102*/
//	Purpose: check if a plotted triangle houses the origin
//	
//	Triangle contains origin if lines pass thru 4 quadrants
//	Need function to predict which quadrants a line is in: y = mx+c			(-5,1) (1,-5) passes thru Quads 2,3,4	m = -1, y-1 = -1(x--5)		y = -x-4
//	at y=0/ x=0, line crosses quadrants (if it isn't vertical/horizontal)							y=0, x=-4 so Quads 2/3		x=0, y=-4 so Quads 3/4
//	if that point inside endpoint range, it crosses the quadrant			-5<-x->1	1<-y->-5 	x&y in range				x&y in range
	
	private static boolean containsOrigin(Point p1, Point p2, Point p3)
	{
		// if line crosses quad  	   1	  2	     3      4
		boolean[] quadrants = {true, false, false, false, false};
		
		Point[] points = {p1,p2,p3};
		for (int i=0; i<points.length; i++)
		{
			Point a = points[i];
			Point b = points[(i+1)%points.length];
			
			double mAB = (a.y - b.y*1.0)/(a.x - b.x*1.0);
			double yIntAB = a.y - mAB*a.x;	// at  y=0, x=?	this is c	(x,0)
			double xIntAB = -yIntAB/mAB;	//	at x=0, y=?				(0,y)
			
			// find the smaller point first
			int point1y = (a.y<b.y) ? a.y : b.y;	int point1x = (a.x<b.x) ? a.x : b.x;
			int	point2y = (a.y<b.y) ? b.y : a.y;	int	point2x = (a.x<b.x) ? b.x : a.x;
			// determine x-int in range
			if (point1y<0 && 0<point2y)
			{
				// determine quadrants if (x,0) is -ve
				quadrants[xIntAB > 0 ? 1:2] = true;
				quadrants[xIntAB > 0 ? 4:3] = true;
			}
			// determine y-int in range
			if (point1x<0 && 0<point2x)
			{
				// determine quadrants if (0,y) is -ve
				quadrants[yIntAB > 0 ? 2:3] = true;
				quadrants[yIntAB > 0 ? 1:4] = true;
			}
		}
		// check if all quadrants are passed, 
		for (boolean passed : quadrants)
			if (!passed)
				return false;
		return true;
	}
	
/*prob103*/
//	Purpose: Find set with n elements so all combinations of elements inside have different sum 
//	and all elements add to smallest sum
//	
//	To find all combinations in a 6 set = all combinations of 2/3/4/5/6 elements, nested loops
// 	Then produce all combinations of array from {1,2,3,4,5,6} => {n,n+1,n+2,n+3,n+4,n+5}, nested loops
//	k= first index that can be raised
//	k=0 -> ->
//	{1,1,1,1,1,9}	k=5
//	{1,1,1,1,2,9}	k=4 
//	{1,1,1,1,3,9}	k=4
//	{1,1,1,2,3,9}	k=3 
//	{1,1,1,1,4,9}	k=4 
//	{1,1,1,2,4,9}	k=3
//	{1,1,1,3,4,9}	k=3
//	{1,1,2,3,4,9}	k=2
//	{1,1,1,1,5,9}	k=4
	private static void uniqueSumsSet(int setSize)
	{
		int[] a = {1,2,3,4,5};
		int[] optimal = uniqueSumsSet(a, true);
		while (optimal.length < setSize)
		{
			// formula for suboptimal set
			int b = optimal[optimal.length/2];
			
			int[] subOptimal = new int[optimal.length+1];
			for (int i=0; i<subOptimal.length; i++)
				subOptimal[i] = b+ (i>0 ? optimal[i-1]:0);
			
			optimal = uniqueSumsSet(subOptimal,false);
			for (int aa : optimal)
				System.out.print(aa + " ");
			System.out.println();
		}
	}

	
//	Given an initial array, finds first special sum set eg: {20, 31, 37, 40, 42, 43, 44};
//	Time depends on how far off from unique set			// keep going until first optimal found?
	private static int[] uniqueSumsSet(int[] initialArray, boolean bruteSearch)
	{	
		// Create the combo positions only once
		List<int[]> allCombinationPos = allCombinationPos(initialArray.length);
		
		int[] subOptimal = initialArray.clone();
		
		// check for lower sum versions with elements +-n range
		// only check combos if it's sum < suboptimal sum
		int[] finalArray = bruteSearch ? null		  : addToArray(initialArray.clone(), 3);
		initialArray 	 = bruteSearch ? initialArray : addToArray(initialArray,-3);
		int[] array 	 = initialArray.clone();
		
		//nested loop alternative
		nextArray: while (!Arrays.equals(array, finalArray))
		{
//			System.out.println(array[array.length-1]);
//			int[] aa = {10,17,20,22,23,24};
//			if (Arrays.equals(array, aa))
//				System.out.println("hahaahah");
			for (int k=0; k<array.length; k++)
				// if element can be raised
				if (k==array.length-1 || array[k]+1 < array[k+1])
				{
					array[k]++;
					// reset prev elements eg: {1,6,8,9,11} -> {1,1,1,10,11}
					for (int i=0; i<k; i++)
						array[i] = initialArray[i];
					// otherwise once array sum crosses a point, it will never be true
					if ((bruteSearch ? true : sum(array)<sum(subOptimal)) 
					&& smallerSubsethasSmallerSum(array) 
					&& noCommonSum(array,allCombinationPos)) 
							 return array;
						else continue nextArray;
				}
		}
		// cuz no other optimal found in range
		return initialArray;
	}
	private static boolean noCommonSum(int[] array, List<int[]> allCombinationPos)
	{
		List<Integer> sums = new LinkedList<>();
		for (int[] listPos : allCombinationPos)
		{
			int[] comb = new int[listPos.length];
			for (int i=0; i<listPos.length; i++)
				comb[i] = array[listPos[i]];
			if (!sums.contains(sum(comb)))
				 sums.add(sum(comb));
			else return false;
		}
		return true;
	}
	
	private static int[] addToArray(int[] array, int addValue)
	{
		for (int i=0; i<array.length; i++)
			array[i] += addValue;
		return array;
	}
	
	private static boolean smallerSubsethasSmallerSum(int[] array)
	{
		int prevMaxSum, minSum, maxSum=0;
		for (int i=1; i<=array.length; i++)
		{
			prevMaxSum = maxSum;
			maxSum = 0;
			minSum = sum(combinations(i, array).get(0));
			for (int[] comb : combinations(i, array))
			{
				if (maxSum < sum(comb))
					maxSum = sum(comb);
				if (minSum > sum(comb))
					minSum = sum(comb);
			}
			if (minSum < prevMaxSum)
				return false;
		}
		return true;
	}
	
	private static int testUniqueSumsSet() throws FileNotFoundException
	{
		Scanner sc = new Scanner(new File("src//prob105.txt"));
		
		
		int totalSum =0;
		while (sc.hasNext())
		{
			List<Integer> setList = new LinkedList<>();
			for (String s : sc.nextLine().split(","))
				setList.add(Integer.parseInt(s));
			
			int[] set = toArray(setList);
			Arrays.sort(set);
			if (smallerSubsethasSmallerSum(set) && noCommonSum(set, allCombinationPos(set.length)))
				totalSum += sum(set);
		}
		return totalSum;
	}
	
/*prob106*/
//	Lesson: equivalent to catalan number method (useful for arithmetic parenthesis expressions etc.)
//	Count pairs possible for each combo (only compared with ones below)
//	*****		|****	4 	||***9	|||**9	||||*3		|||||
//				*|***	3	|*|**8	||*|*8 	|||*|2
//	Ignore a)	**|**	2	|**|*7	||**|7	||*||1		Ignore f)
//				***|*	1	|***|6	|*||*6	|*|||
//				****|		*||**5	|*|*|5	
//							*|*|*4	|**||4	Ignore e)
//				Ignore b)	*|**|3	*|||*3
//							**||*2	*||*|2
//							**|*|1	*|*||1
//							***||	**|||
//							c)		Ignore d)
//
// 	b)bc |**** != *|*** always		e) bc ||||* and |||*| have ||| common and |* != *| always
//	c)bc ||*** != **||* always, ignore these		
//	   	 |*|** != *|*|* always, ignore these				
//
//	c) 	keep:	|**|*	with *||**				 	OR [[[[*				max 2 unequal
//				|***|	with *||**, *|*|*, **||*	OR [[[*[ [[*[[	[*[[[	max 2 unequal
//				*|**|	with **||*					OR *[[[[				max 2 unequal
//	
//	d)	bc		||**|	with |*||*, *|||*			OR *[[[[ [*[[[	 		max 2 unequal (for repeat |, it's basically not there so *)
//				|**||	with *|||*	*||*|			OR [[[[* *[[[[			max 2 unequal	IGNORE ALL (repeats from prev)
//	
//	EFFICIENCY
//	Inefficient: 	for (allCombos)
//						check with (allCombos)
//	Ignore a) and e) and d)
//	Only need to check combos with same bars

//	of the uncommon   |s, This can't happen: 1st | < 1st |, 2nd | < 2nd |....  |*|*| != *|*||
//	large case example: |***|**|*|			|******|||||				|***|**|*|
//						*|*|**||**	valid	*||||||*****	valid		*|***|**||	invalid
//
//	Which combos to pick? With holes eg: |***| |**||
//	Generate holes: eg: ********	||**|**...	|*|**|*...	|**|**|		|***|*|	
//	generating holes directly is more complicated than just checking for holes in regular combos
	private static int noCommonSumvalidPairsOld(int size)
	{
		int validPairs = 0;
		// ignore a) and d)
		for (int slots=2; slots<=size-2; slots++)
			// check combos with same no. bars
			for (int[] comb : combinationPosWithHoles(slots, size))
			{
				printCombos(comb, size);
				for (int[] comb2 : combinationPos(slots, size))
					// compare with combos below only
					if (compareCombos(comb, comb2) && barsWithinHole(comb, comb2))
					{
						printCombos(comb2, size);
						validPairs++;
					}
				System.out.println();
			}
		return validPairs;			
	}
	
	private static List<int[]> combinationPosWithHoles(int slots, int size)
	{
		// cuz of possible overlap eg: |**|**| will appear twice, with ||**|**... and |**||**
		combinations = new LinkedList<>();
		combNestedLoopWithHoles(0, size, 1, new int[slots]);
		return combinations;
		
	}
	private static void combNestedLoopWithHoles(int rangeMin, int size, int bar, int[] listPos)
	{
		int slots = listPos.length;
		if (bar==slots+1)
		{
			if (hasHoles(listPos))
				combinations.add(listPos.clone());
			return;
		}
		//generate a hole combo eg: |||**|***... OR	||**||**... OR |**|||**
		for (int i=rangeMin; i<size-slots+bar; i++)
		{
			listPos[bar-1] = i;
			combNestedLoopWithHoles(i+1, size, bar+1, listPos);
		}
	}
	
	private static boolean hasHoles(int[] listPos)
	{
		for (int i=0; i<listPos.length; i++)
			if (listPos[i] - (i==0 ? 0:listPos[i-1]) > 2)
				return true;
		return false;
	}
//	To avoid repeats  	*|**|	with **||* notice that compare 	2-2 slots, max 2 unequal for no overlap, min size 4	eg: [[[[ [[*[[ 	[[[**[
//	eg: *[[[[			||**|	with |*||*						3-3 slots, max 3 unequal,				min size 6	eg:				[[[[[[ [*[[[[[
//
//	Generate pairings eg: {2,4,6,7} 2 slots or {1,2,6,7,11,12} 3 slots NO OVERLAP
//	How to generate all {a,b,c,d}? like a regular 4 slot combo
//	for pairings length n remove n/2 elements eg: {2,3,4,5} remove {2,3} to get {2,3} {4,5} 
	private static int noCommonSumvalidPairs(int size)
	{
		int validPairs = 0;
		// ignore a) and d)
		for (int slots=2; slots*2<=size; slots++)
			
			for (int[] pairing : combinationPos(slots*2, size))
				// choose n/2 elements					
				for (int[] combPos : combinationPos(slots, pairing.length))
				{
					int[] comb = getElements(pairing, combPos);
					int[] comb2= getElements(pairing, complementIndexes(pairing, combPos));
					// compare with combos below only
					if (compareCombos(comb, comb2) && barsWithinHole(comb, comb2))
					{
						printCombos(comb, size);
						printCombos(comb2, size);
						validPairs++;
						System.out.println();
					}
				}
				System.out.println();
		return validPairs;			
	}
//	eg: *|***| with **||**	NOT *|***| with |***|*
	private static boolean barsWithinHole(int[] comb, int[] comb2)
	{
		boolean smaller = false;
		boolean greater = false;
		for (int j=0; j<comb.length; j++)
			if 		(comb[j] < comb2[j])	smaller = true;
			else if (comb[j] > comb2[j])	greater = true;
		if (smaller == true && greater == true)
			return true;
		return false;
	}
	
	private static int[] complementIndexes(int[] pairing, int[] notListPos)
	{
		boolean test[] = new boolean[pairing.length];
		for (int pos : notListPos)
			test[pos] = true;
		
		int[] listPos = new int[notListPos.length];
		int i=0;
		for(int j=0; j<test.length; j++)
			if (test[j] == false)
				listPos[i++]=j;
		return listPos;
	}
	
	private static int[] getElements(int[] pairing, int[] indexes)
	{
		int[] comb = new int[indexes.length];
		
		int a=0;
		for (int i:indexes)
			comb[a++] = pairing[i];
		return comb;
	}
	
	private static boolean compareCombos(int[] listPos, int[] listPos2)
	{
		for (int i=0; i<listPos.length; i++)
			if (listPos[i] < listPos2[i])
				return true;
			else if (listPos[i] > listPos2[i])
				return false;
		return false;
	}
	
	

/*prob107 - INCOMPLETE*/	
	
	
	
/*prob108*/
//	Purpose: 1/x + 1/y = 1/n, first n with 1000+ solutions
//	Lesson: sometimes an efficient algorithm is generating the results rather than input
//	eg: n=4,   5 +	 20 =  4
//			   6 +   12 =  4
//			   8 + 	 8  =  4 only 3 solutions
//	note that  x 	 y	converge, look for solutions till then
//	otherwise  20+   5  =  4 this happens, x and y swap
//	most extreme value for x close to 0
//	So what's the converge point? when x>y
//	
//	rewrite formula as y = nx/x-n so we can iterate thru x
//	solution when nx%x-n == 0
//
//	note that  x,y always > n
//	1/(n+a) + 1/(n+b) = 1/n
//	n^2 = a*b
//	find all a,b = find all x,y
//	find all divisors of n^2 and use it to find all ways to multiply to n^2
//	eg: n = 15, n^2 = 225 = 3^2 * 5^2	factors = 1,3,5,9,15,25,45,75,225
//	ways to multiply = 1,225  3,75  3,45  9,25  15^2	5 ways = divisors/2
//	this is a,b		eg:1/(15+1) + 1/(15+225) = 1/15
//
//	numDiv of n   = p+1 * q+1 * r+1.... (p,q,r maxPowers of primes)
//	numDiv of n^2 = 2p+1* 2q+1* 2r+1.... 						 
	private static int diophantineReciprocalsOld()
	{
		int solutions = 0;
		primeList = primeSeive(10000000);
		for (int n=4; solutions<=1000; n++)
		{
			// all ways to multiply to n^2, to find all combinations a*b = n^2
			// all solutions of 1/(n+a) + 1/(n+b) = 1/n
			solutions = numDivOfSquare(n)/2;
			System.out.println(n + "," + solutions);
		}
		
//		for (n++; solutions<1000)
//		for (x=n+1; x++ <= (n*x)/(x-n)) till converge point
//			if ((n*x)%(x-n) == 0)
//				solutions++; (reset for new x)	
//				print("1/%d + 1/%d = 1/%-10d",x,(n*x)/(x-n),n);
		return solutions;
	}
	
	private static int numDivOfSquare(int num)
	{
		int numDivOfN2=1;
		int maxPower;
		for (int prime : primeList)
		{
			// number has been exhausted of its factors, so doesn't check all primes
			if (num == 1) break;
			
			for (maxPower=0; num%prime == 0; maxPower++)
				num/=prime;
			numDivOfN2 *= 2*maxPower+1;
		}
		return numDivOfN2;
	}
	
//	numDiv of n = p+1* q+1* r+1.... = a*b*c			
//	to find n so 1/x+1/y = 1/n has max solutions, find n which has max ways for a*b
//	solutions ~ p+1* q+1* r+1.... 
//
//	so WHAT IF YOU GENERATE MAX   a *  b *  c.... 		maxPows= 	 2,2,1,1,1,1 	numDiv = 3*3*2*2*2*2/2>1000
//	smallest n for this?	MIN 2^	*3^	 *5^			eg: 180180 = 2^2 * 3^2 * 5*7*11*13
//	why not increase a only? bc then it's eg: 10000*1*1... vs 9998*2*2...
//	private static void a()
//	{
//		int[] exponentList = new int[14];
//		int solutions = 1;
//		int index=0;
//		
//		// 2111
//		// 2211
//		// 2221
//		// 2222
//		// 3111
//		// 3211
//		// 3221
//		// 3222
//		
//		while (true)
//		{
//			while  (index < exponentList.size())
//			{
//				exponentList.subList(index, toIndex)
//				exponentList.set(index, exponentList.get(index)+1);
//				index++;
//			}
//			if exponentsList[index]
//		}
//		while (true)
//		{
//			exponentList.add(1);
//			
//			solutions = numDiv(exponentList)/2;
//			if (solutions > 1000) break;
//		}
//	}
	
	private static int numDiv(List<Integer> exponents)
	{
		int numDiv=1;
		for (int exp : exponents)
			numDiv *= exp+1;
		return numDiv;
	}
	
	
}
