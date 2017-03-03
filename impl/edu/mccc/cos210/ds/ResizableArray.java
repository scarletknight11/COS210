package edu.mccc.cos210.ds;

public class ResizableArray<T> extends Array<T> implements IResizableArray<T> {
	public ResizableArray() {
		this(0);
	}
	public ResizableArray(int size) {
		super(size);
	}
	@Override
	public void resize(int size) {
		resize(size, null);
	}
	@SuppressWarnings("unused")
	@Override
	public void resize(int size, T init) {
		Object[] theOldArray = theArray;
		theArray = new Object[size];
	}
}
