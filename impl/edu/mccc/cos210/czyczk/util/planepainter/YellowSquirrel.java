package edu.mccc.cos210.czyczk.util.planepainter;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import edu.mccc.cos210.czyczk.util.IPlanePainter;

public class YellowSquirrel implements IPlanePainter {
	@Override
	public void paintPlane(Graphics2D g2d) {
		Path2D body = new Path2D.Double();
		body.moveTo(-128.0, -384.0);
		body.lineTo(0.0, 384.0);
		body.lineTo(128.0, -384.0);
		body.closePath();
		g2d.setPaint(new Color(255, 255, 0));
		g2d.fill(body);
		g2d.setPaint(Color.BLACK);
		g2d.setStroke(new BasicStroke(3.0f));
		g2d.draw(body);
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
