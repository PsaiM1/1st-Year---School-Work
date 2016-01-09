package utilities;

/**
 * <P>A \code>FunctionalList</code> object supports linked-list operations but, unlike the
 * "standard" implementation that performs \emph{in situ} modifications, e.g., destructively modifies
 * the original list when adding/removing elements, a "functional" list create copies of the original 
 * object with the desired modifications. This has several ramifications---some good, some not so good.
 * <P>
 * <BR>
 * <P>In this assignment, you will implement a functional linked list that provides a subset of useful
 * operations.
 * @author UMD CS Department
 *
 *
 * @param <T>
 */
public class FunctionalList<T> {
	/**
	 * Internal (hidden) class ... define a Node to keep track of values and links.
	 * @author UMD CS Department
	 *
	 * @param <T>
	 */
	public class Node  {
		Node next;
		Object data;
		
		public Node(Object objData) {
			next = null;
			data = objData;
		}
		
		public Node(Object objData, Node nextLink) {
			next = nextLink;
			data = objData;
		}
		
		public Object getData() {
			return data;
		}
		
		public void setData(Object objData) {
			data = objData;
		}
		
		public Node getNext() {
			return next;
		}
		
		public void setNext(Node nextLink) {
			next = nextLink;
		}
	}
	
	// define additional properties here.
	public Node head;
	private int cursor;
	
	// define ctor(s) here:
	public FunctionalList() {
		head = new Node(null);
		cursor = 0;
	}
	
	// private copy constructor: deep copies the list
	private FunctionalList(FunctionalList<T> list) {
		FunctionalList<T> temp = new FunctionalList<T>();
		if(temp.cursor == 0) {
			Node tempNode = new Node(list.head.getData());
			temp.head = tempNode;
			if (temp.head.getData() != null) {
				temp.cursor++;
			}
		} 
		Node listCurrNode = list.head;
		Node tempCurrNode = temp.head;
		while(listCurrNode.getNext() != null) {
			listCurrNode = listCurrNode.getNext();
			Node copyNode = new Node(listCurrNode.getData(), null);
			tempCurrNode.setNext(copyNode);
			tempCurrNode = tempCurrNode.getNext();
			temp.cursor++;
		}
		this.cursor = temp.cursor;
		this.head = temp.head;
	}
	
	// public interface: *note to self use while loop to avoid using indexes
	//Add method: adds to the end of the linked List
	public FunctionalList<T> add( T element ) {
		FunctionalList<T> tempList = new FunctionalList<T>(this);
		Node currNode = tempList.head;
		Node tempNode = new Node(element);
		if (tempList.cursor == 0) {
			tempList.head = tempNode;
			cursor++;
		} else {
			while (currNode.getNext() != null) {
				currNode = currNode.getNext();
			}
			currNode.setNext(tempNode);
			cursor++;
		}
		this.head = tempList.head;
		return this;
		
	}
	
	//Append Method: attaches one linked list to the end of this linked list
	public FunctionalList<T> append( FunctionalList<T> other ) {
		FunctionalList<T> orgTempList = new FunctionalList<T>(this);
		FunctionalList<T> otherTempList = new FunctionalList<T>(other);
		Node currOrgNode = orgTempList.head;
		Node otherListNode = otherTempList.head;
		while (currOrgNode.getNext() != null) {
			currOrgNode = currOrgNode.getNext();
		}
		do {
			if (otherListNode == null) {
				break;
			} else {
				orgTempList.add((T) otherListNode.getData());
				otherListNode = otherListNode.getNext();
			}
		} while (otherListNode != null);
		
		this.cursor = orgTempList.cursor;
		this.head = orgTempList.head;
		return this;
	}
	
	//Remove method: removes all instances of the element from the linked list
	public FunctionalList<T> remove( T element ) {
		FunctionalList<T> tempList = new FunctionalList<T>(this);
		Node currNode = tempList.head;
		
		do {
			if (!currNode.getData().equals(element)) {
				currNode = currNode.getNext();
			} else if (currNode.getData().equals(element) && (currNode == tempList.head)){
				tempList.head = currNode.getNext();
				currNode = currNode.getNext();
				tempList.cursor--;
			} else {
				Node tempNode = tempList.head;
				while (tempNode != null) {
					if (tempNode.getNext().getData().equals(element)) {
						tempNode.setNext(tempNode.getNext().getNext());
						tempList.cursor--;
					}
					tempNode = tempNode.getNext();
					currNode = currNode.getNext();
				}
			}
		} while (currNode != null);
		this.cursor = tempList.cursor;
		this.head = tempList.head;
		return this;
		
	}
	
	public FunctionalList<T> reverse() {
		FunctionalList<T> tempList = new FunctionalList<T>(this);
		if (tempList.head == null) {
			return this;
		}
		
		Node pNode = tempList.head.getNext();
		Node qNode = tempList.head;
		Node rNode = null;
		
		while (pNode != null) {
			rNode = qNode;
			qNode = pNode;
			pNode = pNode.getNext();
			qNode.setNext(rNode);
		}
		
		tempList.head.setNext(null);
		tempList.head = qNode;
		this.head = tempList.head;
		return this;
	}
	
	public int size() {
		return cursor;
	}
	
	public int positionOf( T element ) {
		Node currNode = this.head;
		int counter = 0;
		while (currNode != null) {
			if (currNode.getData().equals(element)) {
				return counter;
			} else {
				counter++;
				currNode = currNode.getNext();
			}
		}
		if (currNode == null) {
			counter = -1;
		}
		return counter;
	}
	
	public T nth( int index) {
		Node currNode = this.head;
		int counter = 0;
		if (index < 0 || index > this.size() - 1) {
			throw new ArrayIndexOutOfBoundsException("out of bounds");
		} else {
			while (currNode != null) {
				if (counter == index) {
					break;
				} else {
					currNode = currNode.getNext();
					counter++;
				}
			}
		}
		return (T) currNode.getData();
	}
	// override(s):
	public String toString() {
		String listString = "[";
		Node currNode = this.head;
		while(currNode != null) {
			if (this.positionOf((T) currNode.getData()) == this.size() - 1) {
				String temp = currNode.getData().toString();
				listString = listString.concat(temp);
				currNode = currNode.getNext();
			} else {
				String temp = currNode.getData().toString() + ", ";
				listString = listString.concat(temp);
				currNode = currNode.getNext();
			}
		}
		listString = listString.concat("]");
		return listString;
	}

	public Object[] toArray() {
		Object[] listArray = new Object[this.size()];
		Node currNode = this.head;
		int counter = 0;
		while (currNode != null) {
			if (counter == this.size()) {
				break;
			} else {
				listArray[counter] = currNode.getData();
				counter++;
				currNode = currNode.getNext();
			}
		}
		return listArray;
	}

}
