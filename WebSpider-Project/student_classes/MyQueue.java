package student_classes;

import java.util.LinkedList;
import java.util.Queue;

public class MyQueue<T> {

	/* YOU WRITE THIS CLASS */
	private Queue<T> currQueue;
	public MyQueue() {
		currQueue = new LinkedList<T>(); //wrapped queue with linked list
	}

	/**
	 * returns the size of this queue
	 * @return
	 */
	public int size() {
		synchronized(this) {
			return currQueue.size();
		}
	}
	
	/**
	 * clears this queue
	 */
	public void clear() {
		synchronized(this) {
			currQueue.clear();
		}
	}
	
	/**
	 * adds the specified object to the end of the queue
	 * @param e
	 */
	public void enqueue(Object e) {
		synchronized(this) {
			currQueue.offer((T) e);
			this.notifyAll();
		}
	}
	
	/**
	 * removes the first object from the queue (following FIFO behavior)
	 * and returns it
	 * @return
	 */
	public Object dequeue() {
		synchronized(this) {
			Object temp = currQueue.poll();
			while (temp == null) { //queue is empty
				try {
					this.wait();
					temp = currQueue.poll();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return temp;
		}
	}
}
