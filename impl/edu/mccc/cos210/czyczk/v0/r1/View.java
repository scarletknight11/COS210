package edu.mccc.cos210.czyczk.v0.r1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class View extends JPanel {
	private static final long serialVersionUID = 1L;
	private BufferedImage thePlane;
	private Model model;
	private Czyczk czyczk;
	private AffineTransform gat = null;
	private AffineTransform at = new AffineTransform();
	private AffineTransform stat;
	private Ellipse2D blob = new Ellipse2D.Double(-8.0, -8.0, 16.0, 16.0);
	private Ellipse2D collisionCircle = new Ellipse2D.Double(-25.0, -25.0, 50.0 ,50.0);
	public View(Model model) {
		this.model = model;
		setBackground(new Color(100, 255, 100));
		setPreferredSize(new Dimension(800, 800));
		init();
	}
	void init() {
		try {
			thePlane = loadThePlane();
		} catch (IOException ex) {
			model.getPlaneInfo().setPlaneName("RedSquirrel");
			try {
				thePlane = loadThePlane();
			} catch (Exception e) {
				System.err.println("bad things...");
				System.exit(-1);
			}
		}
		initStat();
	}
	private BufferedImage loadThePlane() throws IOException {
		BufferedImage bi = ImageIO.read(
			new File(
				"./images/planes/" +
				model.getPlaneInfo().getPlaneName() +
				"_0.25.png"
			)
		);
		return bi;
	}
	private void initStat() {
		stat = AffineTransform.getTranslateInstance(
			-thePlane.getWidth() / 2.0,
			thePlane.getHeight() / 2.0
		);
		stat.scale(1.0, -1.0);
	}
	private void initGat() {
		gat = AffineTransform.getTranslateInstance(
			getWidth() / 2.0,
			getHeight() - thePlane.getHeight() / 2.0 - 16.0
		);
		gat.scale(1.0, -1.0);
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g.create();
		if (gat == null) {
			initGat();
		}
		g2d.transform(gat);
		AffineTransform bat = new AffineTransform();
		g2d.setPaint(Color.YELLOW);
		for (Model.Blob b : Model.getBlobs()) {
			bat = AffineTransform.getTranslateInstance(b.x, b.y);
			Shape bs = bat.createTransformedShape(blob);
			g2d.fill(bs);
		}
		at.setToIdentity();
		at.translate(model.getX(), model.getY());
		at.concatenate(stat);
		g2d.drawRenderedImage(thePlane, at);
		model.collision(blob.getWidth()/2, collisionCircle.getWidth()/2);
		g2d.dispose();
	}
}
