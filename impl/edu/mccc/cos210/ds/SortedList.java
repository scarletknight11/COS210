package edu.mccc.cos210.ds;

public class SortedList<T> extends OrderedList<T> implements ISortedList<T> {
	private java.util.Comparator<? super T> comparator = null;
	public SortedList() {
		this(null);
	}
	public SortedList(java.util.Comparator<? super T> comparator) {
		super();
		this.comparator = comparator;
	}
	@SuppressWarnings("unchecked")
	@Override
	public void add(T data) {
		if (comparator == null && !(data instanceof java.lang.Comparable)) {
			throw new java.lang.IllegalArgumentException();
		}
		boolean added = false;
		DNode newNode = new DNode(data);
		if (head == null) {
			addFirst(data);
			return;
		}
		DNode node = head;
		while (node != null) {
			if (comparator != null && comparator.compare(data, node.getData()) < 0 ||
				comparator == null && ((Comparable<? super T>) data).compareTo(node.getData()) < 0) {
				newNode.setPrevNode(node.getPrevNode());
				newNode.setNextNode(node);
				node.setPrevNode(newNode);
				if (newNode.getPrevNode() != null) {
					newNode.getPrevNode().setNextNode(newNode);
				} else {
					head = newNode;
				}
				added = true;
				break;
			}
			node = node.getNextNode();
		}
		if (!added) {
			addLast(data);
		}
	}
	@Override
	public void setComparator(java.util.Comparator<? super T> comparator) {
		this.comparator = comparator;
	}
	@Override
	public java.util.Comparator<? super T> getComparator() {
		return this.comparator;
	}
}
