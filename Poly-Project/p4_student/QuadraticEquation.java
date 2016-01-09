/**
 * A general representation of a quadratic equation of the form:
 * (a*x^2 + b*x + c)
 * 
 * @author Evan Golub
 * @date March 2013
 */

package p4_student;

/*
 * Name: PrasannaSai Meruga
 * Section Number: 0402
 * TA: Ahmed Elgohary
 * Date: 11/6/14
 */


public class QuadraticEquation {
	private final MyDouble a;
	private final MyDouble b;
	private final MyDouble c;
	
	//constructor that creates an empty QuadraticEquation with all MyDouble values set to zero
	public QuadraticEquation() {
		a = MyDouble.zero;
		b = MyDouble.zero;
		c = MyDouble.zero;
	}

	//constructor that creates a QuadraticEquation with only c being a set value
	public QuadraticEquation(MyDouble cIn) {
		a = MyDouble.zero;
		b = MyDouble.zero;
		c = cIn;
	}

	//constructor that creates a QuadraticEquation with b and c being the set values
	public QuadraticEquation(MyDouble bIn, MyDouble cIn) {
		a = MyDouble.zero;
		b = bIn;
		c = cIn;
	}

	//constructor that creates a QuadraticEquation with all three MyDOuble values being set
	public QuadraticEquation(MyDouble aIn, MyDouble bIn, MyDouble cIn) {
		a = aIn;
		b = bIn;
		c = cIn;
	}
	
	//copy constructor, takes all values of QuadraticEquation other and sets it to the current object values
	public QuadraticEquation(QuadraticEquation other) {
		this.a = other.a;
		this.b = other.b;
		this.c = other.c;
	}
	
	//getter for value a
	public MyDouble getA() {
		return a;
	}
	
	//getter for value b
	public MyDouble getB() {
		return b;
	}
	
	//getter for value c
	public MyDouble getC() {
		return c;
	}
	
	/*
	 * This method takes a value x and plugs it into the QuadraticEquation and returns the solved
	 * value.
	 */
	public MyDouble evaluate(MyDouble x) {
		MyDouble resultA = (a.multiply(x.multiply(x)));
		MyDouble resultB = (b.multiply(x));
		MyDouble FullEval = resultA.add(resultB.add(c));
		return FullEval;
	}
	
	/*
	 * This method takes the current object and all its values and adds it to the inputted QuadraticEquation's values
	 * and returns a new QuadraticEquation
	 */
	public QuadraticEquation add(QuadraticEquation quadIn) {
		MyDouble resultA = (this.a.add(quadIn.a));
		MyDouble resultB = (this.b.add(quadIn.b));
		MyDouble resultC = (this.c.add(quadIn.c));
		QuadraticEquation QuadAdd = new QuadraticEquation(resultA, resultB, resultC);
		return QuadAdd;
	}
	
	/*
	 * This method takes the current object and all its values and subtracts the inputted QuadraticEquation's values
	 * and returns a new QuadraticEquation
	 */
	public QuadraticEquation subtract(QuadraticEquation quadIn) {
		MyDouble resultA = (this.a.subtract(quadIn.a));
		MyDouble resultB = (this.b.subtract(quadIn.b));
		MyDouble resultC = (this.c.subtract(quadIn.c));
		QuadraticEquation QuadSubtract = new QuadraticEquation(resultA, resultB, resultC);
		return QuadSubtract;
	}

	/*
	 * This method takes the values of the current object and performs FOIL multiplication with the inputted QuadraticEquation.
	 * However since multiplication between certain quadratic equations will yield cubic or quartic terms, if those terms 
	 * are greater than zero then the returned QuadraticEquation is null, if not then it will return a new QuadraticEquation
	 * whose values have been multiplied.
	 */
	public QuadraticEquation limitedMultiply(QuadraticEquation quadIn) {
		MyDouble FourTerm = (this.a.multiply(quadIn.a));
		MyDouble ThreeTerm = ((this.a.multiply(quadIn.b).add(this.b.multiply(quadIn.a))));
		MyDouble TwoTerm = ((this.c.multiply(quadIn.a)).add(this.b.multiply(quadIn.b)).add(this.a.multiply(quadIn.c)));
		MyDouble OneTerm = ((this.c.multiply(quadIn.b)).add(this.b.multiply(quadIn.c)));
		MyDouble Constant = (this.c.multiply(quadIn.c));
		if (FourTerm.isZero() && ThreeTerm.isZero()) {
			QuadraticEquation MultiQuad = new QuadraticEquation(TwoTerm, OneTerm, Constant);
			return MultiQuad;
		} else {
			return null;
		}
	}

	/*
	 * This method takes the current object and differentiates it by using the Power Rule from Calculus.
	 * This will take the exponents and multiply it against the current values to yield QuadraticEquation
	 * in a linear form "bx + c"
	 */
	public QuadraticEquation derivative() {
		MyDouble expOne = new MyDouble(2.0);
		MyDouble resultB = (this.a.multiply(expOne));
		MyDouble expTwo = new MyDouble(1.0);
		MyDouble resultC = (this.b.multiply(expTwo));
		QuadraticEquation QuadDiff = new QuadraticEquation(resultB, resultC);
		return QuadDiff;
	}

	/*
	 * This method takes the values of the current object and returns a normalized MyDouble value of the
	 * QuadraticEquation.
	 */
	public MyDouble normalize() {
		MyDouble resultA = new MyDouble(this.a);
		MyDouble resultB = new MyDouble(this.b);
		MyDouble resultC = new MyDouble(this.c);
		MyDouble NormQuad = new MyDouble(resultA.square().add(resultB.square().add(resultC.square())));
		return NormQuad.sqrt();
	}

	/*
	 * This method uses the normalize values of the current object and the inputted QuadraticEquation and
	 * does a boolean comparison on them and yields an int compare = 0 if the values are equal, compare = -1 if 
	 * the current object value is less than the inputted object and, compare = 1 if the current object value is
	 * greater than the inputted object.
	 */
	public int compareTo(QuadraticEquation quadIn) {
		int compare = 0;
		if (this.normalize() == quadIn.normalize()) {
			compare = 0;
		} else if (this.normalize().compareTo(quadIn.normalize()) == -1) {
			compare = -1;
		} else if (this.normalize().compareTo(quadIn.normalize()) == 1) {
			compare = 1;
		}
		return compare;
	}
	
	/*
	 * This method takes the current object and returns it in String format
	 */
	public String toString() {
		String a = this.a.toString();
		String b = this.b.toString();
		String c = this.c.toString();
		StringBuilder printEquation = new StringBuilder();
		if (!(this.a.isZero())) {
				printEquation.append(a+"x^2");
		}
		
		if ((!(this.b.isZero()) && !(this.a.isZero()))) {
			if (this.b.compareTo(this.b.abs()) == -1) {
				printEquation.append(b+"x");
			} else {
				printEquation.append("+" + b+"x");
			}
		} else if ((!(this.b.isZero()) && (this.a.isZero()))) {
			printEquation.append(b + "x");
		}
		
		if (((!(this.a.isZero())) || (!(this.b.isZero()))) && (!(this.c.isZero()))) {
			if (this.c.compareTo(this.c.abs()) == -1) {
				printEquation.append(c);
			} else {
				printEquation.append("+" + c);
			}
		} else if (((this.a.isZero()) && (this.b.isZero())) && !(this.c.isZero())) {
			printEquation.append(c);
		} else if (((this.a.isZero()) && (this.b.isZero())) && (this.c.isZero())){
			printEquation.append("0");
		}
		
		return printEquation.toString();
		
		
	}
	/*
	 * this method parses a inputted string into a quadratic equation
	 */
	public static QuadraticEquation parseQuadratic(String str) {
		str = str.replaceAll("\\s+", "");
		int Index = str.indexOf("x^2");
		MyDouble aVal = new MyDouble(0);
		MyDouble bVal = new MyDouble(0);
		if (Index != -1) {
			str = str.substring(0, Index);
			aVal = new MyDouble(Double.parseDouble(str));
			str = str.substring(Index + 3);
		}
		
		int IndexTwo = str.indexOf("x");
		if (IndexTwo != -1) {
			str.substring(0, IndexTwo);
			bVal = new MyDouble(Double.parseDouble(str));
			str = str.replaceAll("+", "");
			str = str.substring(IndexTwo + 1);
		}
		
		str.substring(0);
		str.replaceAll("+","");
		MyDouble cVal = new MyDouble(Double.parseDouble(str));
		QuadraticEquation parseQuad = new QuadraticEquation(aVal, bVal, cVal);
		return parseQuad;
	}
	
	
	
	
	
	
	//NOTE: THIS IS WRITTEN FOR YOU - DO NOT CHANGE!!!!
	public boolean equals (Object other) {
		if (other == null) {
			return false;
		}
		else if (this.getClass()!=other.getClass()) {
			return false;
		}
		else {
			QuadraticEquation casted = (QuadraticEquation)other;
			return (
					a.equals(casted.a) && 
					b.equals(casted.b) && 
					c.equals(casted.c)
			);
		}
	}
	
	
}