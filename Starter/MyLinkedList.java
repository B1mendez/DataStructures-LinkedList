/**
 * Name: Brian Mendez
 * Email: b1mendez@ucsd.edu
 * Sources used: Zybooks, and LLIterator.java file in lecture.
 * 
 * This programs simulates a linked list without using imports
 * and collections. This link list will also have two dummy nodes
 * one in the beginning of the list, the head which next points to
 * the first node, and the tail which next points to a null. The 
 * instance variables are size, head node, and tail node. 
 */

import java.util.AbstractList;

/** 
 * A public class using generics that extendes an AbstactList using
 * generics
 */

public class MyLinkedList<E> extends AbstractList<E> {

	int size;
	Node head;
	Node tail;

	/**
	 * A Node class that holds data and references to 
	 * previous and next Nodes.
	 */
	protected class Node {
		E data;
		Node next;
		Node prev;

		/** 
		 * Constructor to create singleton Node 
		 * @param element Element to add, can be null	
		 */
		public Node(E element) {
			// Initialize the instance variables
			this.data = element;
			this.next = null;
			this.prev = null;
		}

		/** 
		 * Set the parameter prev as the previous node
		 * @param prev - new previous node
		 */
		public void setPrev(Node prev) {
			this.prev = prev;		
		}

		/** 
		 * Set the parameter next as the next node
		 * @param next - new next node
		 */
		public void setNext(Node next) {
			this.next = next;
		}

		/** 
		 * Set the parameter element as the node's data
		 * @param element - new element 
		 */
		public void setElement(E element) {
			this.data = element;
		}

		/** 
		 * Accessor to get the next Node in the list 
		 * @return the next node
		 */
		public Node getNext() {
			return this.next;
		}

		/** 
		 * Accessor to get the prev Node in the list
		 * @return the previous node  
		 */
		public Node getPrev() {
			return this.prev;
		}

		/** 
		 * Accessor to get the Nodes Element 
		 * @return this node's data
		 */
		public E getElement() {
			return this.data;
		}
	}

	//  Implementation of the MyLinkedList Class
	/** Only 0-argument constructor is defined */
	public MyLinkedList() {
		head = new Node(null);
		tail = new Node(null); 
		head.next = tail; 
		tail.prev = head; 
	}
	/** 
	 * Method that checks the size
	 * @return the number nodes in the list
	 */
	@Override
	public int size() {
		// need to implement the size method
		return this.size; 
	}
	/** 
	 * Assesor to get the element/data in a node
	 * @param index - the location of the node
	 * @return the data stored in the node
	 */
	@Override
	public E get(int index) {
		if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }

		Node curr = head.next; 
		for (int i = 0; i < index + 1; i++){
			if (index != i){
				curr = curr.next; 
			}
		} 
		return (E)curr.data;  
	}

	/** 
	 * Add the parameter into the list in the index 
	 * @param index - the location of the node
	 * @param data - element being added
	 */
	@Override
	public void add(int index, E data) {
		/* Add your implementation here */
		if (index < 0 || index > size){
            throw new IndexOutOfBoundsException(); 
        }

        if (data == null){
            throw new NullPointerException();
        }

        //loop through
		Node newNode = new Node(data);
		Node nextCurr; 
		Node prevCurr; 
		if (size == 0){
			prevCurr = head;
			nextCurr = tail; 
		}
		else if (index == 0){
			prevCurr = head;
			nextCurr = prevCurr.getNext(); 
		}
		else {
			prevCurr = getNth(index - 1);
			nextCurr = prevCurr.getNext(); 
		}

		prevCurr.setNext(newNode); 
		newNode.setPrev(prevCurr); 
		newNode.setNext(nextCurr); 

		if(nextCurr != null){
			nextCurr.setPrev(newNode);
		}

		size++; 
	}
	/** 
	 * Add the parameter at the end of list
	 * @param data - element being added
	 * @return true
	 */
	public boolean add(E data) {
		if (data == null){
			throw new NullPointerException(); 
		}

		Node curr = head; 
		Node newNode = new Node(data);
		for (int i = 0; i < size; i++){
			curr = curr.next;
		}

		newNode.next = tail;
		tail.prev = newNode; 
		newNode.prev = tail.prev.prev;  
		curr.next = newNode; 
	

		size++; 
		return true;
	}
	/** 
	 * Set the data in the node where the index appoints
	 * @param index - the location of the node
	 * @param data - element being added
	 * @return previous data in the node 
	 */
	public E set(int index, E data) {
		if (data == null){
			throw new NullPointerException();
		}
		if (index < 0 || index >= size){
			throw new IndexOutOfBoundsException(); 
		}

		Node curr = head.next; 
		while (curr != null && index != 0){
			curr = curr.next;
			index--; 
		}
		curr.data = data;
		return (E)curr.data; // TODO
	}

	/** 
	 * Remove the node located at the index.
	 * Adjust the size accordingly. 
	 * @param index - the location of the node
	 * @return the data removed in the node
	 */
	public E remove(int index) {
		if (index < 0 || index >= size){
			throw new IndexOutOfBoundsException(); 
		}

		Node curr = head;

		int temp = 0; 
		while (curr != null && temp < index - 1){
			curr = curr.next; 
		}

		//if curr is null then it is at end of list
		if (curr == null){
			Node newNode = tail.prev.prev;
			tail.prev = newNode; 
			newNode.next = tail;
			size--;
			return (E) tail.prev.data;
		}

		Node currData = curr.next;
		Node newNode = curr.next.next;
		curr.next = newNode;
		newNode.prev = curr; 
		size--;
		return (E) currData.data;
	}
	/** 
	 * Clear the node and keep the dummy nodes
	 * (head and tail). 
	 */
	public void clear() {
		head = new Node(null);
		tail = new Node(null);
		size = 0; 

		head.next = tail;
		tail.prev = head; 

		head.prev = null;
		tail.next = null;
	}

	/** 
	 * If the list is empty (size equals zero)
	 * @return true
	 */
	public boolean isEmpty() {	
		return size == 0;  
	}

	/** 
	 * Accessor to get the node at the index 
	 * @param index - the location of the node
	 * @return the node in the index 
	 */
	protected Node getNth(int index) {
		if (index < 0 || index >= size){
			throw new IndexOutOfBoundsException(); 
		}

		Node curr = head.next; 
		for (int i = 0; i < index + 1; i++){
			if (index != i){
				curr = curr.next; 
			}
		} 
		return (Node) curr;  
	}

	public void reverseRegion(int fromIndex, int toIndex){
        //TODO: Add your solution here
        if (toIndex > size || fromIndex > size){
            throw new IndexOutOfBoundsException(); 
        }
 
        if (fromIndex < toIndex){

			Node beforeCurr;
			if(fromIndex == 0){
				beforeCurr = head;
			}
			else {
				beforeCurr = getNth(fromIndex - 1); 

			}

            Node curr = getNth(fromIndex);
            Node temp = null; 

            while (fromIndex < toIndex){
                temp = curr.next; 
                curr.next = temp.next; 

                temp.next = beforeCurr.next;
                beforeCurr.next = temp;

				
				//temp.setPrev(beforeCurr);
                //curr.setPrev(getNth(fromIndex)); 
				
                fromIndex++; 
            }
			
			//set previous nodes
			Node prevNode = head;
			Node newNode = head.next;
			while (newNode != null){
				newNode.setPrev(prevNode);
				prevNode = prevNode.next; 
				newNode = newNode.next;  
			}
            
        } 
    }
}