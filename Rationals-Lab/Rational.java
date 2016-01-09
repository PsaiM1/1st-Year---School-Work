/*
 * You must implement this class.
 */
import java.lang.IllegalArgumentException;
public class Rational {

	private int numerator, denominator;
	//DATA GOES UP HERE
	
	/**
	 * Note: this method must throw an IllegalArgumentException if the <code>denomIn</code> is
	 * 0. Otherwise, it creates a Rational Number.
	 */
	public Rational(int numerIn, int denomIn) {
	   if (denomIn == 0) {
		   throw new IllegalArgumentException("Denom cannot be zero"); // throws an exception because if denom = zero then the number doesnt exist
	   } else {
		   numerator = numerIn;
		   denominator = denomIn;
	   }
	}
	
	public int getNumer() {
	    return numerator;
	}
	
	public int getDenom() {
	    return denominator;
	}
	
	public String toString() { //concats the numer and denom with a slash
	    return numerator + "/" + denominator;
	}
	
	public Rational reciprocal() { //calls on Rational but with switched parameters that match the qualities of a reciprocal
		Rational RationalNumber = new Rational(denominator, numerator);
		return RationalNumber;
	    
	}
	/*
	 * multiply calls a new rational using the parameters 
	 * that is calculated by getting the numer and denom
	 * from rational a and b and multiplying them to make the new rational
	 * 
	 */
	public static Rational multiply(Rational a, Rational b) { 
	    Rational multiNum = new Rational(a.getNumer() * b.getNumer(), a.getDenom() * b.getDenom());
		return multiNum;
	}
	/*
	 * divide calls a new rational that uses the numer and denom of the current object called by "this"
	 * and divides with rational a's numer and denom
	 */
	public Rational divide(Rational a) {
	    Rational diviNum = new Rational(this.getNumer() * a.getDenom(), this.getDenom() * a.getNumer());
	    return diviNum;
	}
	/*
	 * add calls a new rational that uses the numer and denom of the current object called by "this"
	 * and uses the formula (ad + bc)/bd to add them and return rational
	 */
	public Rational add(Rational a) {
	    Rational addNum = new Rational((this.getNumer() * a.getDenom() + a.getNumer() * this.getDenom()), this.getDenom() * a.getDenom());
		return addNum;
	}
	
}
