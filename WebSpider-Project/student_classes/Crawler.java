package student_classes;
import java.net.*;
import java.io.*;

public class Crawler {
	
    public static void main(String[] args) {
		
	MyQueue<URL> linkQueue = new MyQueue<URL>();
	MyQueue<URL> picQueue = new MyQueue<URL>();
	MySet<URL> beenThere = new MySet<URL>();
	MySet<URL> doneThat = new MySet<URL>();
		
	final int MAX_NUM_EXTRACTORS = 5;  // Change this to whatever you want
		
	ExtractorThread[] extractors = new ExtractorThread[MAX_NUM_EXTRACTORS];
		
	new SlideShowGUI(picQueue);
	new CrawlerGUI(linkQueue, picQueue, beenThere, doneThat, extractors);
		
	URL url;
		
	while(true) {
	    // YOU FILL THIS IN!!!  SEE THE PROJECT SPEC.
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			//its interrupted
			return;
		}
		
		for (int i = 0; i < MAX_NUM_EXTRACTORS; i++) {
			if (extractors[i] == null || !extractors[i].isAlive()) { //null or dead thread
				synchronized(linkQueue) { //lock the queue
					if (linkQueue.size() == 0) {
						try {
							linkQueue.wait();
						} catch (InterruptedException e) {
							//its interrupted
							return;
						}
					}
				} //release lock
				
				synchronized(extractors) { //lock the extractor array
					URL url2 = (URL) linkQueue.dequeue();
						try {
							while(url2.openConnection().getContentType() == null || !url2.openConnection().getContentType().startsWith("text/html")) {
								url2 = (URL) linkQueue.dequeue();
							}
							extractors[i] = new ExtractorThread(url2, linkQueue, picQueue, beenThere, doneThat);
							extractors[i].start();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							//e.printStackTrace();
						}
				}
			}
		}
	}
    }
}
