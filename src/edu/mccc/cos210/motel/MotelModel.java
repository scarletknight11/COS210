package edu.mccc.cos210.motel;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
public class MotelModel extends Observable {
	private ArrayList<Roach> roaches = new ArrayList<Roach>();
	public synchronized List<Roach> getRoaches() {
		return roaches;
	}
	public synchronized void addRoach(Roach roach) {
		roaches.add(roach);
		setChanged();
		notifyObservers(new Integer(0));
	}
	public synchronized void removeRoach(Roach roach) {
		roaches.remove(roach);
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
