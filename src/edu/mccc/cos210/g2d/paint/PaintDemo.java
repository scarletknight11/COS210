package edu.mccc.cos210.g2d.paint;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class PaintDemo {
	public PaintDemo() {
		JFrame jf = new JFrame("PaintDemo");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.add(new MyJPanel());
		jf.pack();
		jf.setResizable(false);
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
	}
	public static void main(String[] sa) {
		EventQueue.invokeLater(PaintDemo::new);
	}
}
