package edu.mccc.cos210.motel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import javax.imageio.ImageIO;
public class RoachView {
	private RoachModel model;
	private static BufferedImage[] images = new BufferedImage[] { null, null, null };
	private int toggle = 0;
	public RoachView(RoachModel model) {
		this.model = model;
		loadImages();
	}
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		AffineTransform at = (AffineTransform) model.getTransform().clone();
		if (model.isDead()) {
			at.translate(-65.0, -25.0);
			g2d.drawRenderedImage(
				RoachView.images[2],
				at
			);
			model.dying--;
		} else {
			at.translate(-65.0, -100.0);
			g2d.drawRenderedImage(
				RoachView.images[toggle],
				at
			);
			toggle = toggle == 1 ? 0 : 1;
		}
		g2d.dispose();
	}
	private void loadImages() {
		try {
			if (RoachView.images[0] == null) {
				for (int i = 2; i < 5; i++) {
					BufferedImage bi = ImageIO.read(
						new FileInputStream(
							"images/r" + i + ".gif"
						)
					);
					RoachView.images[i - 2] = bi;
				}
			}
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
	}
}
