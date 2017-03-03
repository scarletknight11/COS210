package edu.mccc.cos210.motel;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.Observable;
public class RoachModel extends Observable {
	private AffineTransform transform = new AffineTransform();
	private Point2D.Double position;
	private double angle;
	private double velocity;
	private boolean dead = false;
	int dying = 3;
	public RoachModel(Point2D.Double position, double angle, double velocity) {
		this.position = position;
		this.angle = angle;
		this.velocity = velocity;
		transform = AffineTransform.getTranslateInstance(
			position.getX(),
			position.getY()
		);
		transform.rotate(
			angle
		);
	}
	public boolean isHit(double x, double y) {
		if (position.distance(new Point2D.Double(x, y)) < 24.0) {
			dead = true;
			return true;
		} else {
			return false;
		}
	}
	public AffineTransform getTransform() {
		return transform;
	}
	public Point2D.Double getPosition() {
		return position;
	}
	public void setPosition(Point2D.Double position) {
		this.position = position;
		transform = AffineTransform.getTranslateInstance(
			position.getX(),
			position.getY()
		);
		transform.rotate(angle);
		setChanged();
		notifyObservers();
	}
	public void setVelocity(double velocity) {
		this.velocity = velocity;
		setChanged();
		notifyObservers();
	}
	public void updatePosition() {
		if (!isDead()) {
			double x = transform.getTranslateX();
			double y = transform.getTranslateY();
			double dx = velocity * Math.sin(angle);
			double dy = velocity * Math.cos(angle);
			setPosition(
				new Point2D.Double(
					x + dx,
					y - dy
				)
			);
		}
	}
	public void setDead(boolean b) {
		dead = b;
	}
	public boolean isDead() {
		return dead;
	}
}
