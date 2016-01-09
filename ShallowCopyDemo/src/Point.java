
public class Point {
	public int x;
	public int y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public static void main(String[] args) {
		Point p1 = new Point(3, 4);
		System.out.println("p1:" + p1.x + "," + p1.y);
	}

}
