package edu.mccc.cos210.ds;

public class SinglyLinkedList<T> implements ISinglyLinkedList<T> {
	protected SNode head = null;
	protected SNode tail = null;
	private int size = 0;
	@Override
	public void addFirst(T data) {
		SNode temp = new SNode(data);
		if (size == 0) {
			head = temp;
			tail = temp;
			size += 1;
		} else {
			temp.nextNode = head;
			head = temp;
			size += 1;
		}
	}
	@Override
	public T getFirst() {
		if (size > 0) {
			return head.getData();
		} else {
			throw new java.util.NoSuchElementException();
		}
	}
	@Override
	public T removeFirst() {
		if (size == 0) {
			return null;
		} else {
			T data = head.data;
			head = head.nextNode;
			size -= 1;
			return data;
		}
		
	}
	@Override
	public void addLast(T data) {
		SNode temp = new SNode(data);
		if (size == 0) {
			head = temp;
			tail = temp;
			size += 1;
		} else {
			tail.nextNode = temp;
			tail = temp;
			size += 1;
		}
	}
	@Override
	public T getLast() {
		return tail.getData();
	}
	@Override
	public int getSize() {
		return size;
	}
	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		SNode node = head;
		sb.append("[ ");
		while (node != null) {
			sb.append(node.getData().toString() + " ");
			node = node.getNextNode();
		}
		
		if(sb.length() ==3){
            sb.delete(sb.length() - 3, sb.length());
		
		}
		
		sb.append("]");
		return sb.toString();
	}
	@Override
	public java.util.Iterator<T> iterator() {
		return new SIterator();
	}
	private class SIterator implements java.util.Iterator<T> {
		private SNode next = head;
		@Override
		public boolean hasNext() {
			if (next == null) {
				return false;
			}
			return true;
		}
		@Override
		public T next() {
			if (next == null) {
				throw new java.util.NoSuchElementException();
			}
			T data = next.getData();
			next = next.getNextNode();
			return data;
		}
	}
	protected class SNode {
		private SNode nextNode;
		private T data;
		
		public SNode(T data) {
			setData(data);
		}
		public SNode getNextNode() {
			return nextNode;
		}
		public void setNextNode(SNode nextNode) {
			this.nextNode = nextNode;
		}
		public T getData() {
			return data;
		}
		public void setData(T data) {
			this.data = data;
		}
	}
}