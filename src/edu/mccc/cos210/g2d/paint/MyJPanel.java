package edu.mccc.cos210.g2d.paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import javax.swing.JPanel;

public class MyJPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	public MyJPanel() {
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(1024, 768));
	}
//	private BufferedImage loadTex() {
//		BufferedImage bi = null;
//		try {
//			bi = ImageIO.read(new File("./images/tex.png"));
//		} catch (Exception ex) {
//			System.exit(-1);
//		}
//		return bi;
//	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g.create();
		AffineTransform gat = new AffineTransform();
		gat.translate(getWidth() / 2.0, getHeight() / 2);
		gat.scale(1.0, -1.0);
		g2d.transform(gat);
		
		g2d.setColor(Color.BLACK);
		
		g2d.draw(
			new Line2D.Double(
				0.0,
				getHeight() / 2.0,
				0.0,
				-getHeight() / 2.0
			)
		);
		
		g2d.draw(
			new Line2D.Double(
				-getWidth() / 2.0,
				0.0,
				getWidth() / 2.0,
				0.0
			)
		);

		Ellipse2D e2d = new Ellipse2D.Double(-256.0, -256.0, 512.0, 512.0);
		
		g2d.setPaint(Color.PINK);
//		g2d.setPaint(new Color(255, 0, 128));
//		g2d.setPaint(new Color(0.0f, 1.0f, 0.2f));
//		g2d.setPaint(new Color(0, 128, 128, 64));
//		g2d.setPaint(new Color(0.5f, 0.7f, 0.1f, 0.8f));
//		g2d.setPaint(
//			new GradientPaint(
//				-getWidth() / 4,
//				getHeight() / 4,
//				Color.RED,
//				getWidth() / 4,
//				-getHeight() / 4,
//				Color.BLUE,
//				false
//			)
//		);
//		g2d.setPaint(
//			new LinearGradientPaint(
//				-getWidth() / 4,
//				getHeight() / 4,
//				getWidth() / 4,
//				-getHeight() / 4,
//				new float[] {
//					0.0f,
//					0.33f,
//					0.66f,
//					1.0f
//				},
//				new Color[] {
//					Color.RED,
//					Color.GREEN,
//					Color.BLUE,
//					Color.WHITE
//				}
//			)
//		);
//		g2d.setPaint(
//			new RadialGradientPaint(
//				0.0f,
//				0.0f,
//				256.0f,
//				new float[] {
//					0.0f,
//					0.33f,
//					0.66f,
//					1.0f
//				},
//				new Color[] {
//					Color.RED,
//					Color.GREEN,
//					Color.BLUE,
//					Color.WHITE
//				}
//			)
//		);
//		g2d.setPaint(
//			new TexturePaint(
//				new MyBufferedImage(64, 64, BufferedImage.TYPE_3BYTE_BGR),
//				new Rectangle2D.Double(-32.0, -32.0, 64.0, 64.0)
//			)
//		);
//		g2d.setPaint(
//			new TexturePaint(
//				loadTex(),
//				new Rectangle2D.Double(-50.0, -50.0, 100.0, 100.0)
//			)
//		);
		g2d.fill(e2d);
		
		g2d.setStroke(new BasicStroke(0.0f));
		g2d.setPaint(Color.BLACK);
		g2d.draw(e2d);
		
		g2d.dispose();
	}
//	private class MyBufferedImage extends BufferedImage {
//		public MyBufferedImage(int width, int height, int type) {
//			super(width, height, type);
//			paintSelf(createGraphics());
//		}
//		private void paintSelf(Graphics2D g2d) {
//			g2d.setPaint(Color.BLUE);
//			g2d.fillRect(0, 0, getWidth(), getHeight());
//			g2d.setStroke(new BasicStroke(0.0f));
//			g2d.setPaint(Color.ORANGE);
//			g2d.draw(
//				new Line2D.Double(0.0, 0.0, 64.0, 64.0)
//			);
//			g2d.draw(
//				new Line2D.Double(64.0, 0.0, 0.0, 64.0)
//			);
//			AffineTransform fat = new AffineTransform();
//			fat.setToScale(4.0, -4.0);
//			g2d.setFont(getFont().deriveFont(fat));
//			fat.scale(1.0, -1.0);
//			String s = "Q";
//			g2d.setPaint(Color.RED);
//			g2d.drawString(
//				s,
//				(int) (
//					getWidth() / 2.0 -
//					SwingUtilities.computeStringWidth(
//						g2d.getFontMetrics(),
//						s
//					) / 2.0
//				),
//				g2d.getFontMetrics().getHeight() / -2
//			);
//			g2d.dispose();
//		}
//	}
}
