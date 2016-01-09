package listClasses;

import java.util.*;

public class BasicLinkedList<T> implements Iterable<T> {
	// private node class
	protected class Node {
		
		Node next;
		Object data;
		
		public Node (Object objData) {
			next = null;
			data = objData;
		}
		
		public Node (Object objData, Node inNext) {
			next = inNext;
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
		
		public void setNext(Node inNext) {
			next = inNext;
		}
	}
	/**
	 * Basic Linked List constructor: constructs a default Node that becomes the head
	 */
	protected Node head;
	protected int cursor;
	public BasicLinkedList() {
		head = new Node(null);
		cursor = 0;
	}
	/**
	 * Add to End method: Adds a Node containing the data to the end of the List
	 * @param data
	 * @return
	 */
	public BasicLinkedList<T> addToEnd(T data) {
		Node tempNode = new Node (data);
		if (cursor == 0) {
			head = tempNode;
			cursor++;
		} else {
			Node currNode = this.head;
			while (currNode.getNext() != null) {
				currNode = currNode.getNext();
			}
			currNode.setNext(tempNode);
			cursor++;
		}
		return this;
	}
	/**
	 * Add to Front method: Adds a Node containing the data to the front of the List
	 * making the Node the new head and making the previous head the next of the new
	 * head
	 * @param data
	 * @return
	 */
	public BasicLinkedList<T> addToFront(T data) {
		Node tempNode = new Node (data);
		Node holderNode = this.head;
		this.head = tempNode;
		this.head.setNext(holderNode);
		cursor++;
		return this;
	}
	/**
	 * GetFirst method: getter for the head of the list;
	 * @return
	 */
	public T getFirst() {
		return (T) this.head.getData();
	}
	/**
	 * GetLast method: getter for the tail of the list;
	 * @return
	 */
	public T getLast() {
		Node currNode = this.head;
		while (currNode.getNext() != null) {
			currNode = currNode.getNext();
		}
		return (T) currNode.getData();
	}
	/**
	 * getSize method: returns the size of the List;
	 * @return
	 */
	public int getSize() {
		return cursor;
	}
	/**
	 * Iterator method: Allows this list to become the target of a for each loop
	 */
	public Iterator<T> iterator() {
		return new listIterator();
	}
	/**
	 * Inner Iterator class that defines Iterator according to this List structure
	 * if there is another node that is next to the current one it will return true
	 * if not it will return false and this method can iterate to the next node;
	 */
	public class listIterator implements Iterator<T> {
		Node currNode = BasicLinkedList.this.head;
		public boolean hasNext() {
			if (currNode != null) {
				return true;
			} else {
				return false;
			}
		}
		
		public T next() {
			Object data = currNode.getData(); // obtain current data
			currNode = currNode.getNext(); // then increment
			return (T) data;
		}
		
	}
	/**
	 * Remove method: removes all nodes that contain targetData from the list by comparing the
	 * data in the current node with the targetData, and if there is a match then that Node
	 * is removed;
	 * @param targetData
	 * @param comparator
	 * @return
	 */
	public BasicLinkedList<T> remove(T targetData, java.util.Comparator<T> comparator) {
		Node currNode = this.head;
		
		do {
			//check if the head contains the data and if so remove it
			if (comparator.compare((T) currNode.getData(), targetData) == 0 && (currNode == this.head)) {
				this.head = currNode.getNext();
				currNode = currNode.getNext();
				this.cursor--;
			//check every other node that contains the data and iterate down it once more
			//removing each node that does contain the target data
			} else if (comparator.compare((T) currNode.getData(), targetData) == 0) {
				Node tempNode = this.head;
				while(tempNode != null) {
					if (tempNode.getNext().getData().equals(targetData)) {
						tempNode.setNext(tempNode.getNext().getNext());
						this.cursor--;
					}
					tempNode = tempNode.getNext();
					currNode = currNode.getNext();
				}
			} else {
				currNode = currNode.getNext();
			}
		} while (currNode != null);
		
		return this;
	}
	
	
	/**
	 * retrieveFirstElement method: removes and returns the head of the linked list
	 * @return
	 */
	public T retrieveFirstElement() {
		//call a holding node to hold the head's data
		Node holdingNode = new Node(this.head.getData());
		Node currNode = this.head;
		//make the next node the new head to remove the previous head
		this.head = currNode.getNext();
		return (T) holdingNode.getData();
	}
	/**
	 * retrieveLastElement method: removes and returns the last element, the tail, of 
	 * the linked list
	 * @return
	 */
	public T retrieveLastElement() {
		//call a holding node and iterate to the end of the list
		Node holdingNode = new Node(this.getLast());
		Node currNode = this.head;
		while (currNode.getNext() != null) {
			currNode = currNode.getNext();
		}
		//set a node tail node to the last node of the list
		Node tailNode = currNode;
		Node tempNode = this.head;
		//iterate up to the last node but do not become that node
		while (tempNode.getNext() != tailNode) {
			tempNode = tempNode.getNext();
		}
		//remove it
		tempNode.setNext(null);
		return (T) holdingNode.getData();
	}
	
}
