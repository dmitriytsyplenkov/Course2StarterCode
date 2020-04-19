package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		head = new LLNode<E>(null);
		tail = new LLNode<E>(null);
		head.setNext(tail);
		tail.setPrev(head);
		size = 0;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		// TODO: Implement this method
		if (element == null) {
			throw new NullPointerException("nulls are not allowed");
		}
		LLNode newNode = new LLNode(element, tail.getPrev(), tail);
		tail.getPrev().setNext(newNode);
		tail.setPrev(newNode);
		size++;

		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		// TODO: Implement this method.
		return getNode(index).getData();

	}

	public LLNode<E> getNode(int index) {
		if (index > size - 1 || index < 0) {
			throw new IndexOutOfBoundsException("the index is out of bounds");
		}
		if (index >= size / 2) {
			LLNode<E> curNode = tail;
			for (int i = size - 1; i >= index; i--) {
				curNode = curNode.getPrev();
			}
			return curNode;
		} else {
			LLNode<E> curNode = head;
			for (int i = 0; i <= index; i++) {
				curNode = curNode.getNext();
			}
			return curNode;
		}
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		// TODO: Implement this method
		if (element == null) {
			throw new NullPointerException();
		}
		if (size == 0) {
			if (index > 0) {
				throw new IndexOutOfBoundsException();
			}

			LLNode<E> newNode = new LLNode<E>(element, head, tail);
			head.setNext(newNode);
			tail.setPrev(newNode);
		} else {
			LLNode<E> nodeAtIndex = getNode(index);
			LLNode<E> newNode = new LLNode<E>(element, nodeAtIndex.getPrev(), nodeAtIndex);
			nodeAtIndex.getPrev().setNext(newNode);
			nodeAtIndex.setPrev(newNode);
		}
		size++;
	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		// TODO: Implement this method
		LLNode<E> curNode = getNode(index);
		curNode.getPrev().setNext(curNode.getNext());
		curNode.getNext().setPrev(curNode.getPrev());
		size--;
		return curNode.getData();
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		// TODO: Implement this method
		if (element == null) {
			throw new NullPointerException();
		}
		LLNode<E> curNode = getNode(index);
		E curNodePrevData = curNode.getData();
		curNode.setData(element);

		return curNodePrevData;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor


	public LLNode<E> getPrev() {
		return prev;
	}

	public LLNode<E> getNext() {
		return next;
	}

	public void setPrev(LLNode<E> prev) {
		this.prev = prev;
	}

	public void setNext(LLNode<E> next) {
		this.next = next;
	}

	public E getData() {
		return data;
	}

	public void setData(E data) {
		this.data = data;
	}

	public LLNode(E e)
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

	public LLNode(E e, LLNode prev, LLNode next) {
		this.data = e;
		this.prev = prev;
		this.next = next;
	}

}
