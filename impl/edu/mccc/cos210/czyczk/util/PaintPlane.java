package edu.mccc.cos210.czyczk.util;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class PaintPlane {
	private static final String PLANE_PAINTER_PACKAGE = "edu.mccc.cos210.czyczk.util.planepainter";
	public PaintPlane() {
		JFrame jf = new JFrame("PaintPlane");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.add(new PaintPlaneView(getPlanePainter("Intimidator")));
		jf.pack();
		jf.setResizable(false);
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
	}
	public static void main(String[] sa) {
		EventQueue.invokeLater(PaintPlane::new);
	}
	private IPlanePainter getPlanePainter(String planeName) {
		IPlanePainter planePainter = null;
		try {
			planePainter = (IPlanePainter) Class.forName(
				PLANE_PAINTER_PACKAGE + "." + planeName
			).newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		return planePainter;
	}
}
