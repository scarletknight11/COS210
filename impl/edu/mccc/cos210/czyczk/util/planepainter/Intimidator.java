package edu.mccc.cos210.czyczk.util.planepainter;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

import edu.mccc.cos210.czyczk.util.IPlanePainter;

public class Intimidator implements IPlanePainter {
	@Override
	public void paintPlane(Graphics2D g2d) {
		Path2D body = new Path2D.Double();
		body.moveTo(16.0, 64.0);
		body.lineTo(16.0, -64.0);
		body.lineTo(-16.0, -64.0);
		body.lineTo(-16.0, 64.0);
		body.closePath();
		g2d.setPaint(new Color(128, 0, 0));
		g2d.fill(body);
		g2d.setPaint(Color.BLACK);
		g2d.setStroke(new BasicStroke(3.0f));
		g2d.draw(body);
		Path2D leftwing = new Path2D.Double();
		leftwing.moveTo(-16, 0);
		leftwing.lineTo(-96,-32);
		leftwing.lineTo(-96,-48);
		leftwing.lineTo(-16,-48);
		leftwing.closePath();
		g2d.setPaint(new Color(117, 117, 117));
		g2d.fill(leftwing);
		g2d.setPaint(Color.BLACK);
		g2d.setStroke(new BasicStroke(3.0f));
		g2d.draw(leftwing);
		Path2D rightwing = new Path2D.Double();
		rightwing.moveTo(16, 0);
		rightwing.lineTo(96,-32);
		rightwing.lineTo(96,-48);
		rightwing.lineTo(16,-48);
		rightwing.closePath();
		g2d.setPaint(new Color(117, 117, 117));
		g2d.fill(rightwing);
		g2d.setPaint(Color.BLACK);
		g2d.setStroke(new BasicStroke(3.0f));
		g2d.draw(rightwing);
		Path2D head = new Path2D.Double();
		head.moveTo(-16, 64);
		head.lineTo(16, 64);
		head.lineTo(0, 96);
		head.closePath();
		g2d.setPaint(new Color(117, 117, 117));
		g2d.fill(head);
		g2d.setPaint(Color.BLACK);
		g2d.setStroke(new BasicStroke(3.0f));
		g2d.draw(head);
	}
	@Override
	public Rectangle2D getBounds2D() {
		return new Rectangle2D.Double(
			-128.0 - 2.0,
			-384.0 - 2.0,
			256.0 + 4.0,
			768.0 + 10.0
		);
	}
}



