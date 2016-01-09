
public class Point {
	
	double x, y;
	
	public Point(double xVal, double yVal){
		x = xVal;
		y = yVal;
	}
	public void set(double xVal, double yVal){
		x = xVal;
		y = yVal;
		
	}
	
	public void print () {
		System.out.println("("+x+","+y+")");
	}

}
