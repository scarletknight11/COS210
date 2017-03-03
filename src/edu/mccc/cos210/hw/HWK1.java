package edu.mccc.cos210.hw;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class HWK1 {
	private final int FPS = 20;
	public HWK1() {
		JFrame jf = new JFrame("HWK1");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel jp = new JPanel() {
			private static final long serialVersionUID = 1L;
			@Override
			public Color getBackground() {
				return Color.RED;
			}
		};
		MyCanvas canvas = new MyCanvas();
		jp.add(canvas);
		jf.add(jp, BorderLayout.CENTER);
		jf.pack();
		jf.setResizable(false);
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
		canvas.createBufferStrategy(2);
		canvas.setIgnoreRepaint(true);
		new Timer(1000 / FPS, ae -> canvas.draw()).start();
	}
	public static void main(String[] sa) {
		EventQueue.invokeLater(HWK1::new);
	}
	class MyCanvas extends Canvas {
		private static final long serialVersionUID = 1L;
		private static final int FRAME_WIDTH = 370;
		private static final int FRAME_HEIGHT = 240;
		private static final int WINDOW_WIDTH = FRAME_WIDTH * 2;
		private static final int WINDOW_HEIGHT = FRAME_HEIGHT * 2;
		private BufferedImage bi;
		private int index = 0;
		public MyCanvas() {
			try {
				bi = ImageIO.read(new File("./images/Muybridge_The_Horse_in_Motion.jpg"));
			} catch (Exception ex) {
				System.exit(-1);
			}
		}
		public void draw() {
			BufferStrategy bs = getBufferStrategy();
			Graphics2D g2d = (Graphics2D) bs.getDrawGraphics();
			g2d.clearRect(0, 0, getWidth(), getHeight());
			g2d.drawImage(
				getFrame(),
				getWidth() / 2 - FRAME_WIDTH,
				getHeight() / 2 - FRAME_HEIGHT,
				getWidth() / 2 + FRAME_WIDTH,
				getHeight() / 2 + FRAME_HEIGHT,
				0,
				0,
				FRAME_WIDTH,
				FRAME_HEIGHT,
				this
			);
			g2d.dispose();
			bs.show();
			Toolkit.getDefaultToolkit().sync();
		}
		public void paint(Graphics g) {
		}
		private Image getFrame() {
			BufferedImage biFrame = new BufferedImage(
				FRAME_WIDTH,
				FRAME_HEIGHT,
				BufferedImage.TYPE_INT_BGR
			);
			Graphics2D g2d = biFrame.createGraphics();
			int sx1 = 20 + index % 4 * FRAME_WIDTH + index % 4 * 4;
			int sy1 = 30 + index / 4 * FRAME_HEIGHT + index / 4 * 4;
			int sx2 = sx1 + FRAME_WIDTH;
			int sy2 = sy1 + FRAME_HEIGHT;
			g2d.drawImage(
				bi,
				0,
				0,
				FRAME_WIDTH,
				FRAME_HEIGHT,
				sx1,
				sy1,
				sx2,
				sy2,
				this
			);
			//index = index < 17 ? index + 1 : 0;
			if (index < 11) {
				index = index + 1;
			} else {
				index = 0;
			}
			g2d.dispose();
			return biFrame;
		}
		@Override
		public Dimension getPreferredSize() {
			return new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT);
		}
	};
}