package edu.mccc.cos210.g2d.paint;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;

public class DrawDemo {
	public DrawDemo() {
		JFrame jf = new JFrame("DrawDemo");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.add(
			new MyJPanel(),
			BorderLayout.CENTER
		);
		jf.pack();
		jf.setResizable(false);
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
	}
	public static void main(String[] sa) {
		EventQueue.invokeLater(DrawDemo::new);
	}
}
