package edu.mccc.cos210.ds;

public class Set<T> implements ISet<T>, java.lang.Iterable<T> {
	IResizableArray<IOrderedList<T>> theBucketArray = new ResizableArray<>(8);
	private int size = 0;
	public Set() {
		for (int index = 0; index < theBucketArray.getSize(); index++) {
			theBucketArray.set(index, new OrderedList<T>());
		}
	}
	private void increaseCapacity() {
		IResizableArray<IOrderedList<T>> newBucketArray = new ResizableArray<>(theBucketArray.getSize() * 2);
		for (int i = 0; i < newBucketArray.getSize(); i++) {
			newBucketArray.set(i, new OrderedList<T>());
		}
		for (T data : this) {
			int index = compress(hash(data), newBucketArray.getSize());
			newBucketArray.get(index).addFirst(data);
		}
		theBucketArray = newBucketArray;
	}
	private int hash(T data) {
		return 0;
	}
	private int compress(int hash) {
		return 0;
	}
	private int compress(int hash, int mod) {
		return 0;
	}
	@Override
	public void add(T data) {
		if ((double) getSize() > theBucketArray.getSize() * 0.7) {
			increaseCapacity();
		}
		@SuppressWarnings("unused")
		int index = compress(hash(data));
	}
	@Override
	public void add(ISet<T> set) {
	}
	@Override
	public void remove(T data) {
		for (int i = 0; i < theBucketArray.getSize(); i++) {
			java.util.Iterator<T> listIterator = theBucketArray.get(i).iterator();
			while (listIterator.hasNext()) {
				if (listIterator.next().equals(data)) {
					listIterator.remove();
					size--;
					break;
				}
			}
		}
	}
	@Override
	public boolean contains(T data) {
		int index = compress(hash(data));
		return theBucketArray.get(index).contains(data);
	}
	@Override
	public boolean contains(ISet<T> set) {
		boolean b = false;
		for (T data : set) {
			b = contains(data);
			if (!b) {
				break;
			}
		}
		return b;
	}
	@Override
	public ISet<T> union(ISet<T> set) {
		return null;
	}
	@Override
	public ISet<T> intersection(ISet<T> set) {
		return null;
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
		return getSize() + " " + theBucketArray.toString();
	}
	@Override
	public java.util.Iterator<T> iterator() {
		ISinglyLinkedList<T> dataList = new SinglyLinkedList<>();
		for (int i = 0; i < theBucketArray.getSize(); i++) {
			for (T data : theBucketArray.get(i)) {
				dataList.addFirst(data);
			}
		}
		return dataList.iterator();
	}
}
