package edu.mccc.cos210.czyczk.util;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public interface IPlanePainter {
	public void paintPlane(Graphics2D g2d);
	public Rectangle2D getBounds2D();
}
