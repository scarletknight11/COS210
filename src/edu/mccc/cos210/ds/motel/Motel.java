package edu.mccc.cos210.ds.motel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import edu.mccc.cos210.ds.IDoublyLinkedList;

public class Motel implements Observer, ActionListener {
	private ClipView clipView = new ClipView();
	private RoachMotel motel;
	private MotelModel model;
	private MotelView view;
	public Motel(RoachMotel motel) {
		this.motel = motel;
		model = new MotelModel();
		view = new MotelView(motel, model);
		Roach roach = new Roach(
			new Point2D.Double(
				400,
				300
			),
			Math.PI,
			7.0
		);
		model.addRoach(roach);
		roach.addObserver(this);
		model.addObserver(clipView);
		new javax.swing.Timer(133, this).start();
	}
	public MotelView getView() {
		return this.view;
	}
	public static Roach vendRoach() {
		Roach roach = new Roach(
			new Point2D.Double(
				(Math.random() * (800 - 148.0)) + 74.0,
				(Math.random() * (600 - 110.0)) + 55.0
			),
			Math.random() * 2.0 * Math.PI,
			Math.random() * 10.0 + 1.0
		);
		return Math.random() < 0.33 ? roach : null;
	}
	@Override
	public void update(Observable o, Object arg) {
		view.repaint();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		synchronized (model) {
			IDoublyLinkedList<Roach> roaches = model.getRoaches();
			for (Roach roach : roaches) {
				roach.updatePosition();
			}
			Roach newRoach = Motel.vendRoach();
			if (newRoach != null) {
				model.addRoach(newRoach);
				newRoach.addObserver(this);
				motel.bumpTotal();
			}
			Iterator<Roach> it = roaches.iterator();
			while (it.hasNext()) {
				Roach roach = it.next();
				double x = roach.getPosition().getX();
				double y = roach.getPosition().getY();
				if (
					roach.getModel().isDead() && roach.getModel().dying <= 0 ||
					x < -75 || x > 875 || y < -75 || y > 675
				) {
					roach.deleteObservers();
					it.remove();
				}
			}
		}
	}
}
