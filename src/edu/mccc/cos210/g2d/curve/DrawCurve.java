package edu.mccc.cos210.g2d.curve;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.event.ChangeListener;
import javax.swing.event.MouseInputAdapter;
import javax.swing.filechooser.FileNameExtensionFilter;


public class DrawCurve {
	private static final String S1 = "QuadCurve2D";
	private static final String S2 = "CubicCurve2D";
	private java.util.List<Point2D> al = new ArrayList<>();
	private boolean cubic = false;
	private JCheckBox jcb1 = new JCheckBox("Edit");
	private JCheckBox jcb2 = new JCheckBox("Show");
	private JPanel mjp = new MyJPanel();
	private BufferedImage bi;
	public DrawCurve(String s) {
		JFrame jf = new JFrame("DrawCurve");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.add(
			createToolBar(),
			BorderLayout.NORTH
		);
		jf.add(
			mjp,
			BorderLayout.CENTER
		);
		MouseInputAdapter mia = new MyMouseInputListener();
		mjp.addMouseListener(mia);
		mjp.addMouseMotionListener(mia);
		mjp.addKeyListener(new MyKeyListener());
		jf.pack();
		jf.setResizable(false);
		jf.setLocationRelativeTo(null);
		this.bi = loadImage(s);
		jf.setVisible(true);
		mjp.requestFocus();
	}
	public static void main(final String[] sa) {
		EventQueue.invokeLater(
			() -> new DrawCurve("images/1.jpg")
		);
	}
	private static BufferedImage loadImage(String s) {
		BufferedImage nbi = new BufferedImage(
			1024,
			768,
			BufferedImage.TYPE_3BYTE_BGR
		);
		if (s != null) {
			try {
				BufferedImage bix = ImageIO.read(
					new FileInputStream(s)
				);
				Graphics2D g2d = nbi.createGraphics();
				g2d.drawImage(
					bix,
					0,
					0,
					nbi.getWidth(),
					nbi.getHeight(),
					null
				);
				g2d.dispose();
				bix = null;
			} catch (Exception ex) {
				nbi = null;
			}
		}
		return nbi;
	}
	private ActionListener alsnr = ae -> {
		if (ae.getSource() instanceof JButton) {
			JButton jb = (JButton) ae.getSource();
			clearPoints();
			setCubic(S2.equals(jb.getText()));
			jcb1.setSelected(false);
			mjp.repaint();
		}
	};
	private JToolBar createToolBar() {
		JToolBar jtb = new JToolBar();
		JButton jb = new JButton(S1);
		jtb.add(jb);
		jb.addActionListener(alsnr);
		jb = new JButton(S2);
		jtb.add(jb);
		jb.addActionListener(alsnr);
		jtb.add(this.jcb1);
		jtb.add(this.jcb2);
		jb = new JButton("Dump");
		jb.addActionListener(
			ae -> {
				System.out.println("---");
				for (Point2D p2d : al) {
					System.out.println(p2d);
				}
				mjp.repaint();
			}
		);
		jtb.add(jb);
		jtb.addSeparator();
		jb = new JButton("Open");
		jb.addActionListener(
			ae -> {
				JFileChooser jfc = new JFileChooser("images");
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
					"PNG, JPG & GIF Images", "png", "jpg", "gif"
				);
				jfc.setFileFilter(filter);
				if (jfc.showOpenDialog(jtb) == JFileChooser.APPROVE_OPTION) {
					try {
						bi = loadImage(jfc.getSelectedFile().getPath());
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
				mjp.repaint();
			}
		);
		jtb.add(jb);
		ChangeListener cl = ce -> mjp.repaint();
		jcb1.addChangeListener(cl);
		jcb2.addChangeListener(cl);
		return jtb;
	}
	private void setCubic(boolean b) {
		this.cubic = b;
	}
	private boolean isCubic() {
		return this.cubic;
	}
	private void clearPoints() {
		al.clear();
	}
	private class MyJPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		private Stroke stroke = new BasicStroke(3.0f);
		private AffineTransform at = new AffineTransform();
		public MyJPanel() {
			setLayout(null);
			setBackground(Color.WHITE);
		}
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g.create();
			if (bi != null && jcb2.isSelected()) {
				g2d.drawRenderedImage(bi, at);
			}
			g2d.setPaint(Color.RED);
			for (int i = 0; i < al.size(); i++) {
				Ellipse2D.Double ed = new Ellipse2D.Double(
					al.get(i).getX() - 4.0,
					al.get(i).getY() - 4.0,
					8.0,
					8.0
				);
				g2d.fill(ed);
			}
			g2d.setPaint(Color.BLACK);
			g2d.setStroke(stroke);
			int n = isCubic() ? 4 : 3;
			for (int i = 0; i < al.size() / n; i++) {
				Shape s = getThingy(i * n);
				g2d.draw(s);
			}
			g2d.dispose();
			requestFocus();
		}
		@Override
		public Dimension getPreferredSize() {
			return new Dimension(1024, 768);
		}
		private Shape getThingy(int i) {
			Shape s;
			if (isCubic()) {
				s = new CubicCurve2D.Double(
					al.get(i).getX(),
					al.get(i).getY(),
					al.get(i + 1).getX(),
					al.get(i + 1).getY(),
					al.get(i + 2).getX(),
					al.get(i + 2).getY(),
					al.get(i + 3).getX(),
					al.get(i + 3).getY()
				);
			} else {
				s = new QuadCurve2D.Double(
					al.get(i).getX(),
					al.get(i).getY(),
					al.get(i + 1).getX(),
					al.get(i + 1).getY(),
					al.get(i + 2).getX(),
					al.get(i + 2).getY()
				);
			}
			return s;
		}
	}
	private class MyMouseInputListener extends MouseInputAdapter {
		private int index = -1;
		@Override
		public void mouseReleased(MouseEvent me) {
			double x = (double) me.getX();
			double y = (double) me.getY();
			if (!jcb1.isSelected()) {
				al.add(new Point2D.Double(x, y));
			}
			mjp.repaint();
		}
		@Override
		public void mousePressed(MouseEvent me) {
			if (jcb1.isSelected()) {
				double x = (double) me.getX();
				double y = (double) me.getY();
				this.index = findNearest(x, y);
				mjp.repaint();
			}
		}
		@Override
		public void mouseDragged(MouseEvent me) {
			if (jcb1.isSelected()) {
				double x = (double) me.getX();
				double y = (double) me.getY();
				if (index >= 0) {
					al.set(
						this.index,
						new Point2D.Double(x, y)
					);
				}
				mjp.repaint();
			}
		}
		private int findNearest(double x, double y) {
			int index = -1;
			int i = 0;
			for (i = 0; i < al.size(); i++) {
				if (al.get(i).distance(x, y) < 5) {
					index = i;
					break;
				}
			}
			return index;
		}
	}
	private class MyKeyListener extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent ke) {
			if (
				ke.getKeyCode() == KeyEvent.VK_Z &&
				ke.getModifiersEx() == KeyEvent.CTRL_DOWN_MASK
			) {
				if (al.size() != 0) {
					al.remove(al.size() - 1);
					mjp.repaint();
				}
			}
		}
	}
}
