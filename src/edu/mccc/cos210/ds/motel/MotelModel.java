package edu.mccc.cos210.ds.motel;
import java.util.ArrayList;
import java.util.Observable;

import edu.mccc.cos210.ds.DoublyLinkedList;
import edu.mccc.cos210.ds.IDoublyLinkedList;

public class MotelModel extends Observable {
	private IDoublyLinkedList<Roach> roaches = new DoublyLinkedList<Roach>();
	public synchronized IDoublyLinkedList<Roach> getRoaches() {
		return roaches;
	}
	public synchronized void addRoach(Roach roach) {
		roaches.addFirst(roach);
		setChanged();
		notifyObservers(new Integer(0));
	}
	public synchronized void removeRoach(Roach roach) {
		roaches.removeFirst();
	}
	public synchronized int doHits(double x, double y) {
		int score = 0;
		for (Roach roach : roaches) {
			if (roach.getModel().isHit(x, y)) {
				score++;
				roach.getModel().setDead(true);
				setChanged();
				notifyObservers(new Integer(2));
			} else {
				setChanged();
				notifyObservers(new Integer(1));
			}
		}
		return score;
	}
}
