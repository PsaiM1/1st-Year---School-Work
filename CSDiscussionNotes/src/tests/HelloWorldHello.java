package tests;

public class HelloWorldHello {
	
	String str1 = "hello";
	String str2 = "world";
	
	
	Thread T1 = new Thread (new Thread() {
		public void run() {
			while(true) {
				synchronized(str1) {
					synchronized(str2) {
						System.out.println(str1 + str2);
					}
				}
			}
		}
	});
	
	Thread T2 = new Thread (new Thread() {
		public void run() {
			while(true) {
				synchronized(str2) {
					synchronized(str1) {
						System.out.println(str2 + str1);
					}
				}
			}
		}
	});
	
	public static void main(String[] args) {
		HelloWorldHello derp = new HelloWorldHello();
		derp.T1.start();
		derp.T2.start();
	}
}
