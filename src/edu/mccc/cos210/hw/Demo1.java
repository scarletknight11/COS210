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

public class Demo1 {
	private final int FPS = 20;
	public Demo1() {
		JFrame jf = new JFrame("Demo1");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel jp = new JPanel() {
			private static final long serialVersionUID = 1L;
			@Override
			public Color getBackground() {
				return Color.WHITE;
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
		EventQueue.invokeLater(Demo1::new);
	}
	class MyCanvas extends Canvas {
		private static final long serialVersionUID = 1L;
		private static final int FRAME_WIDTH = 215;
		private static final int FRAME_HEIGHT = 100;
		private static final int WINDOW_WIDTH = FRAME_WIDTH * 2;
		private static final int WINDOW_HEIGHT = FRAME_HEIGHT * 2;
		private BufferedImage bi;
		private int index = 0;
		public MyCanvas() {
			try {
				bi = ImageIO.read(new File("./images/Tiger-run-Poster-640x800.jpg"));
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
			g2d.drawImage(
				bi,
				0,
				0,
				FRAME_WIDTH,
				FRAME_HEIGHT,
				index % 3 * 207,
				index < 3 ? 99 : (index / 3 - 1) * 116 + 199,
				index % 3 * 207 + FRAME_WIDTH,
				index < 3 ? 199 : (index / 3 - 1) * 116 + 199 + FRAME_HEIGHT,
				this
			);
			index = index < 17 ? index + 1 : 0;
			g2d.dispose();
			return biFrame;
		}
		@Override
		public Dimension getPreferredSize() {
			return new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT);
		}
	};
}