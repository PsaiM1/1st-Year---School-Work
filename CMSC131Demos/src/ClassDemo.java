import java.util.Scanner;
public class ClassDemo {

	public static void main(String[] args) {
		Point p1 = new Point(0, 0); //here because the constructer in the other class it was allowed to set at zero
		Point p2 = new Point(0, 0);
		p1.set(3, 4);
		p1.print();
		p2.set(5, 7);
		p2.print();
		Scanner sc = new Scanner(System.in);
		String s1 = sc.next();
		System.out.println("read: " + s1);
	}

}
