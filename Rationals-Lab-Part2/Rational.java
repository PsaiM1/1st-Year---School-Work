/*
 * You must implement this class.
 */
public class Rational {

	private int numerator, denominator;
	//DATA GOES UP HERE
	
	/**
	 * Note: this method must throw an IllegalArgumentException if the <code>denomIn</code> is
	 * 0. Otherwise, it creates a Rational Number.
	 */
	/*
	 * calls a new Rational number with parameters numerator and denominator and checks if denominator = 0
	 */
	public Rational(int numerIn, int denomIn) {
	    if (denomIn == 0) {
	    	throw new IllegalArgumentException("denomIn cannot equal 0");
	    } else {
	    	numerator = numerIn;
	    	denominator = denomIn;
	    }
	}
	
	/**
	 * The following is the copy-constructor.
	 * @return
	 */
	public Rational( Rational other ) {
		this.numerator = other.numerator;
		this.denominator = other.denominator;
	}
	// gets Numerator
	public int getNumer() {
	    return numerator;
	}
	// gets Denominator
	public int getDenom() {
	    return denominator;
	}
	// prints the rational number as a String
	public String toString() {
	    return numerator + "/" + denominator;
	}
	/*
	 * calls a new rational except with parameters that are reversed so that it 
	 * follows the qualities of a reciprocal
	 */
	public Rational reciprocal() {
	    Rational RationalNumber = new Rational(denominator, numerator);
	    return RationalNumber;
	}
	// multiplies Rational a and Rational b together 
	public static Rational multiply(Rational a, Rational b) {
	    Rational RationalNumber = new Rational((a.getNumer() * b.getNumer()), (a.getDenom() * b.getDenom()));
	    return RationalNumber;
	}
	// divides the current object "this" with rational a
	public Rational divide(Rational a) {
	    Rational RationalNumber = new Rational(this.getNumer() * a.getDenom(), this.getDenom() * a.getNumer());
	    return RationalNumber;
	}
	// adds the current object "this" with rational a using the addition formula
	public Rational add(Rational a) {
	    Rational RationalNumber = new Rational(this.getNumer() * a.getDenom() + this.getDenom() * a.getNumer(), this.getDenom() * a.getDenom());
	    return RationalNumber;
	}
	/*
	 * private method that uses Euclid's GCD reduction algorithm to find the
	 * greatest common divisor
	 */
	private static int GCD(int a, int b) {
		int r = a % b;
		while (r!= 0) {
			a = b;
			b = r;
			r = a % b;
		}
		return b; // greatest common divisor
	}
	/*
	 * calls a new rational number but instead divides the numerator and denominator by their 
	 * GCD and returns the reduced rational number.
	 */
	public Rational reduce() {
		Rational RationalNumber = new Rational(numerator/GCD(numerator, denominator), denominator/GCD(numerator, denominator));
		return RationalNumber;
	}
	
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		} else if (other == null) {
			return false;
		} else if (other instanceof Rational) {
			Rational x = ((Rational) other).divide(this);
			if (x.getNumer() == x.getDenom()) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
}
