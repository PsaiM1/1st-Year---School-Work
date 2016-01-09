import java.util.EmptyStackException;

/**
 * Complete the implementation of this class.
 * Use an array of objects as the "backing-store."
 * Note: carefully study the comments on each of the
 * interface methods and make sure that your implementation
 * throws all required exceptions, etc.
 * 
 * @author UMD CS Department.
 *
 */
public class MyStack implements IStack {
	// declare instance variables here
	private int arrayLength;
	private Object[] stackArray;
	private int counter = 0;
	// define constructor(s) here
	
	public MyStack( int capacity ) {
		arrayLength = capacity;
		stackArray = new Object[arrayLength];
	}

	@Override
	public boolean empty() {
		if (this.stackArray[0] == null) {
			return true;
		}
		return false;
	}

	@Override
	public Object peek() {
		if (counter == 0) {
			throw new EmptyStackException();
		} else {
			return this.stackArray[counter - 1];
		}
	}

	@Override
	public Object pop() {
		if (counter == 0) {
			throw new EmptyStackException();
		} else {
			Object obj = this.stackArray[counter-1];
			this.stackArray[counter-1] = null;
			counter--;
			return obj;
		}
	}

	@Override
	public void push(Object obj) {
		if (counter > this.stackArray.length - 1) {
			throw new StackOverflowError();
		} else {
			this.stackArray[counter] = obj;
			counter++;
		}
	}

	@Override
	public int search(Object obj) {
		int search = 0;
		for (int index = 0; index < this.stackArray.length; index++) {
			if (this.stackArray[index] == obj) {
				search = index;
				break;
			} else {
				search = -1;
			}
		}
		return search;
	}

}
