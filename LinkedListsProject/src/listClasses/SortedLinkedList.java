package listClasses;

import java.util.*;
public class SortedLinkedList<T> extends BasicLinkedList<T> {
	private Comparator<T> comparator;
	/**
	 * Constructor for a sorted list: defines the private comparator field
	 * and creates a list using the super() constructor;
	 * @param inComparator
	 */
	public SortedLinkedList(java.util.Comparator<T> inComparator) {
		super();
		comparator = inComparator;
	}
	/**
	 * add method: adds an element in the appropriate location in the list
	 * @param element
	 * @return
	 */
	public SortedLinkedList<T> add(T element) {
		Node currNode = this.head;
		Node holdingNode = new Node(element);
		if (this.cursor == 0) {
			this.head = holdingNode;
			this.cursor++;
		} else {
			do {
				int result = this.comparator.compare(element, (T) currNode.getData());
				//if the data in the current node is less than the element
				//continue to the next node
				if (result < 0) {
					if (currNode == this.head) {
						holdingNode.setNext(currNode);
						this.head = holdingNode;
						this.cursor++;
					} else {
						currNode = currNode.getNext();
					}
				//if the data in the current node is greater than the element
				//iterate to the previous node and set its next to a node containing the
				//element and make that node's next the current node
				} else if (result > 0) {
					Node tempNode = this.head;
					if (currNode == this.head) {
						holdingNode.setNext(currNode);
						this.head = holdingNode;
						this.cursor++;
					} else {
						while (tempNode.getNext() != currNode) {
							tempNode = tempNode.getNext();
						}
						tempNode.setNext(holdingNode);
						holdingNode.setNext(currNode);
						this.cursor++;
						break;
					}
				//if the data in the current node is equal to the element then
				//do the same thing as the greater that condition.
				} else {
					Node tempNode = this.head;
					if (currNode == this.head) {
						holdingNode.setNext(currNode);
						this.head = holdingNode;
						this.cursor++;
					} else {
						while (tempNode.getNext() != currNode) {
							tempNode = tempNode.getNext();
						}
						tempNode.setNext(holdingNode);
						holdingNode.setNext(currNode);
						this.cursor++;
						break;
					}
				}
			} while (currNode.getNext() != null);
		}
		return this;
	}
	/**
	 * remove method: call to the super class's remove method using this sorted linked
	 * list's comparator field
	 * @param targetData
	 * @return
	 */
	public BasicLinkedList<T> remove(T targetData) {
		super.remove(targetData, comparator);
		return this;
	}
	
	public BasicLinkedList<T> addToEnd(T data) {
		throw new UnsupportedOperationException("invalid operation for sorted list");
	}
	
	public BasicLinkedList<T> addToFront(T data) {
		throw new UnsupportedOperationException("invalid operation for sorted list");
	}
}
