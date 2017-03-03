package edu.mccc.cos210.ds;

public class Queue<T> implements IQueue<T> {
	private ISinglyLinkedList<T> theList = new SinglyLinkedList<>();
	@Override
	public void enqueue(T data) {
		theList.addLast(data);
	}
	@Override
	public T dequeue() {
		return theList.removeFirst();
	}
	@Override
	public T peek() {
		return theList.getFirst();
	}
	@Override
	public boolean isEmpty() {
		return theList.isEmpty();
	}
	@Override
	public String toString() {
		return theList.toString();
	}
}
