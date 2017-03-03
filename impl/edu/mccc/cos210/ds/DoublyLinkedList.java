package edu.mccc.cos210.ds;

import java.util.NoSuchElementException;

import edu.mccc.cos210.ds.SinglyLinkedList.SNode;



public class DoublyLinkedList<T> implements IDoublyLinkedList<T> {
	protected DNode head = null;
	protected DNode tail = null;
	private int size = 0;
	@Override
	public void addFirst(T data) {
		DNode temp = new DNode(data);
		if (size == 0) {
			head = temp;
			tail = temp;
			size += 1;
		} else {
			temp.nextNode = head;
			head.prevNode = temp;
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
		if (head == null) {
			throw new java.util.NoSuchElementException();
		} else {
			T data = head.data;
			head = head.nextNode;
			if (head != null) {
				head.prevNode = null;
			} else {
				tail = null;
			}
			size -= 1;
			return data;
		}
		
	}
	@Override
	public void addLast(T data) {
		DNode temp = new DNode(data);
		if (size == 0) {
			head = temp;
			tail = temp;
			size += 1;
		} else {
			tail.nextNode = temp;
			temp.prevNode = tail;
			tail = temp;
			size += 1;
		}
	}
	@Override
	public T removeLast() {
		if (tail == null) { 
			throw new NoSuchElementException(); 
		}
		T data = tail.data;
		tail = tail.prevNode;
		if (tail == null) {
			head = null;
		} else {
			tail.nextNode = null;
		}
		size--;
		return data;
	}
	@Override
	public T getLast() {
		if (size > 0) {
			return tail.getData();
		} else {
			throw new java.util.NoSuchElementException();
		}
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
		DNode node = head;
		sb.append("[ ");
		while (node != null) {
			sb.append(node.getData().toString() + " ");
			node = node.getNextNode();
		}
		sb.append("]");
		return sb.toString();
	}
	@Override
	public java.util.Iterator<T> iterator() {
		return new ForwardIterator();
	}
	@Override
	public java.util.Iterator<T> reverseIterator() {
		return new ReverseIterator();
	}
	private class ForwardIterator implements java.util.Iterator<T> {
		private DNode node = null;
		private DNode nextNode = head;
		@Override
		public boolean hasNext() {
			if (nextNode == null) {
				return false;
			}
			return true;
		}
		@Override
		public T next() {
			if (nextNode == null) {
				throw new java.util.NoSuchElementException();
			}
			node = nextNode;
			T data = nextNode.getData();
			nextNode = nextNode.getNextNode();
			return data;
		}
		@Override
		public void remove() {
			if (node == null) {
				throw new java.util.NoSuchElementException();
			}
			if (node.getNextNode() == null && node.getPrevNode() == null) {
				head = null;
				tail = null;
			} else {
				if (node.getNextNode() == null) {
					tail = node.prevNode;
					tail.nextNode = null;
				} else {
					if (node.getPrevNode() == null) {
						head = node.nextNode;
						head.prevNode = null;
					} else {
						node.prevNode.nextNode = node.nextNode;
						node.nextNode.prevNode = node.prevNode;
					}
				}
			}
			node = null;
			size--;
		}
	}
	private class ReverseIterator implements java.util.Iterator<T> {
		private DNode nextNode = tail;
		@Override
		public boolean hasNext() {
			if (nextNode == null) {
				return false;
			}
			return true;
		}
		@Override
		public T next() {
			if (nextNode == null) {
				throw new java.util.NoSuchElementException();
			}
			T data = nextNode.getData();
			nextNode = nextNode.getPrevNode();
			return data;
		}
	}
	protected class DNode {
		private DNode nextNode;
		private DNode prevNode;
		private T data;
		public DNode(T data) {
			setData(data);
		}
		public DNode getNextNode() {
			return nextNode;
		}
		public void setNextNode(DNode nextNode) {
			this.nextNode = nextNode;
		}
		public DNode getPrevNode() {
			return prevNode;
		}
		public void setPrevNode(DNode prevNode) {
			this.prevNode = prevNode;
		}
		public T getData() {
			return data;
		}
		public void setData(T data) {
			this.data = data;
		}
	}
}
