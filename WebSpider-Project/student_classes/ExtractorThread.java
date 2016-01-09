package student_classes;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;

public class ExtractorThread extends Thread {

    private URL url;
    private MyQueue<URL> linkQueue, picQueue;
    private MySet<URL> beenThere, doneThat;

    public ExtractorThread(URL url, MyQueue<URL> linkQueue, MyQueue<URL> picQueue, MySet<URL> beenThere, MySet<URL> doneThat) {
	this.url = url;
	this.linkQueue = linkQueue;
	this.picQueue = picQueue;
	this.beenThere = beenThere;
	this.doneThat = doneThat;
    }

    public String getCurrentURL() {
	return url.toString();
    }

    private static Pattern LINK_PATTERN = Pattern.compile("href *= *\"([^\"]*)\"", Pattern.CASE_INSENSITIVE);
    private static Pattern IMAGE_PATTERN = Pattern.compile("<( )*(img|IMG)( )+([^<>])*(src|SRC)( )*=( )*\"([^\"]+)\"[^>]*>");

    private static Set<URL> extractLinks(Pattern toMatch, String s, URL currentURL, int group) {
	Matcher m = toMatch.matcher(s);
	Set<URL> links = new HashSet<URL>();
	while ( m != null && s!= null && m.find()) {
	    String found = m.group(group);
	    try {
		links.add(new URL(currentURL, found));
	    } catch (MalformedURLException e) {
		// just ignore
	    }
	}
	return links;
    }

    private static Set<URL> getLinks(String s, URL currentURL) {
	return extractLinks(LINK_PATTERN, s, currentURL, 1);
    }

    private static Set<URL> getPicURLs(String s, URL currentURL) {
	return extractLinks(IMAGE_PATTERN, s, currentURL, 8);
    }

    public void run() {
	// 	YOU MUST WRITE THIS METHOD!  SEE THE PROJECT SPEC.
    	try {
			BufferedReader urlIn = new BufferedReader(new InputStreamReader(this.url.openStream()));
			String input;
	    	while ((input = urlIn.readLine()) != null) {
	    		Set<URL> linkSet = ExtractorThread.getLinks(input, this.url);
	    		for (URL urlRef : linkSet) {
	    			if (urlRef != null && (url.getProtocol().equals("http") || url.getProtocol().equals("file"))) { //if something fails check this line and change the && to || between http and file
	    				synchronized(beenThere) {
	    					if (!beenThere.contains(urlRef)) {
	    						linkQueue.enqueue(urlRef);
	    						beenThere.add(urlRef);
	    					}
	    				}
	    			}
	    		}
	    	}
	    	urlIn.close();
	    	BufferedReader result = new BufferedReader(new InputStreamReader(this.url.openStream()));
	    	String inputRes;
	    	while ((inputRes = result.readLine()) != null) {
	    		Set<URL> pictures = ExtractorThread.getPicURLs(inputRes, this.url);
	    		for (URL pic : pictures) {
	    			synchronized(doneThat) {
	    				if (!doneThat.contains(pic)) {
	    					picQueue.enqueue(pic);
	    					doneThat.add(pic);
	    				}
	    			}
	    		}
	    	}
	    	result.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
    }

}
