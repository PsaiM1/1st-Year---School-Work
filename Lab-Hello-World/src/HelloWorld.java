/**
 * All processing happens inside of containers called "classes." The name of this
 * class describes what it does: it will print the String "Hello World" on the 
 * user's console. In order for this to happen, you will need to change the message,
 * i.e., the parameter to the <code>System.out.println()</code> method.
 * 
 * Naturally, this exercise isn't intended to test your programming prowess as much
 * as it is designed to ensure that everything works and you have a chance to see
 * the individual pieces.
 * 
 * @author CS Department.
 *
 */
public class HelloWorld {
	/** 
	 * Main is the "entry point," which is where the JVM looks when told
	 * to "execute" a .class file.
	 * @param args (an array of Strings; ignored by this program).
	 */
	public static void main(String[] args) {
		System.out.println("Hello World!");
	}

}
