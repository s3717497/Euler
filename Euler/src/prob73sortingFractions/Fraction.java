package prob73sortingFractions;

public class Fraction implements Comparable {

	private int num;
	private int den;
	public Fraction (int numerator, int denominator)
	{
		int hcf = hcf(numerator, denominator);
		num = numerator/hcf;
		den = denominator/hcf;
	}
	
	public int getNumerator()
	{
		return num;
	}
	public int getDenominator()
	{
		return den;
	}
	
	public boolean equals(Fraction frac)
	{
		//they should be simplified first to be compared directly
		if(	num == frac.getNumerator() &&
			den == frac.getDenominator())
			return true;
		return false;
	}
	
	public int compareTo(Fraction frac)
	{
		if 		(num*frac.getDenominator() >  frac.getNumerator()*den)	return 1;
		else if (num*frac.getDenominator() == frac.getNumerator()*den)	return 0;
		else 															return -1;
	}
	
	private int hcf(int a, int b) 
    { 
      if (b == 0) 
        return a; 
      return hcf(b, a % b);  
    }

	@Override
	public int compareTo(Object fracc) 
	{
		Fraction frac = ((Fraction)fracc);
		if 		(num*frac.getDenominator() >  frac.getNumerator()*den)	return 1;
		else if (num*frac.getDenominator() == frac.getNumerator()*den)	return 0;
		else 															return -1;
	} 
	
	public String toString()
	{
		return num + "/" + den;
	}
}
