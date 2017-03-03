package edu.mccc.cos210.czyczk.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class MakePNGs {
	private static final String PlANE_PAINTER_PACKAGE =
		"edu.mccc.cos210.czyczk.util.planepainter";
	public static void main(String[] sa) {
		for (int scale = 100; scale > 0; scale -= 5) {
			MakePNGs.doIt(scale / 100.0);
		}
	}
	private static void doIt(double scale) {
		String[] planes = new File(
			"./impl/" + PlANE_PAINTER_PACKAGE.replaceAll("\\.", "/")
		).list(
			(dir, name) -> name.endsWith(".java")
		);
		for (String plane : planes) {
			String planeName = plane.substring(0, plane.lastIndexOf("."));
			System.out.println(planeName);
			IPlanePainter planePainter = null;
			try {
				planePainter = (IPlanePainter) Class.forName(
					PlANE_PAINTER_PACKAGE + "." + planeName
				).newInstance();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			Rectangle2D bounds = planePainter.getBounds2D();
			BufferedImage bi = new BufferedImage(
				(int) (bounds.getWidth() * scale + 0.5),
				(int) (bounds.getHeight() * scale + 0.5),
				BufferedImage.TYPE_4BYTE_ABGR
			);
			Graphics2D g2d = (Graphics2D) bi.getGraphics();
			g2d.setPaint(new Color(0, 0, 0, 0));
			g2d.fillRect(
				0,
				0,
				(int) (bi.getWidth()),
				(int) (bi.getHeight())
			);
			AffineTransform gat = new AffineTransform();
			gat.translate(bi.getWidth() / 2.0, bi.getHeight() / 2.0);
			gat.scale(scale, -scale);
			g2d.transform(gat);
			planePainter.paintPlane(g2d);
			try {
				ImageIO.write(
					bi,
					"png",
					new File(
						"./images/planes/" + planeName + "_" + scale + ".png"
					)
				);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			g2d.dispose();
		}
	}
}
