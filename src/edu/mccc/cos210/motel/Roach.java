package edu.mccc.cos210.motel;
import java.awt.geom.Point2D;
import java.util.Observable;
import java.util.Observer;
public class Roach extends Observable implements Observer {
	private RoachModel model;
	private RoachView view;
	public Roach(Point2D.Double position, double angle, double velocity) {
		model = new RoachModel(position, angle, velocity);
		view = new RoachView(model);
		model.addObserver(this);
	}
	public RoachView getView() {
		return view;
	}
	public Point2D.Double getPosition() {
		return model.getPosition();
	}
	public RoachModel getModel() {
		return model;
	}
	public void updatePosition() {
		model.updatePosition();
	}
	@Override
	public void update(Observable o, Object arg) {
		setChanged();
		notifyObservers();
	}
}
