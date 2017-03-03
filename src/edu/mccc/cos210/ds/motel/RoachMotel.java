package edu.mccc.cos210.ds.motel;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
public class RoachMotel {
	private JFrame jf;
	private int score = 0;
	private int total = 0;
	private static final String TITLE = "Roach Motel - %d out of %d [Score = %.1f%%]";
	public RoachMotel() {
		jf = new JFrame(String.format(TITLE, 0, 0, 0.0));
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Motel motel = new Motel(this);
		jf.add(
			motel.getView(),
			BorderLayout.CENTER
		);
		jf.pack();
		jf.setResizable(false);
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
	}
	public static void main(String[] sa) {
		EventQueue.invokeLater(RoachMotel::new);
	}
	public void bumpTotal() {
		total++;
		setTitle();
	}
	public void bumpScore(int n) {
		score += n;
		setTitle();
	}
	private void setTitle() {
		this.jf.setTitle(
			String.format(
				TITLE,
				score,
				total,
				(double) score / total * 100.0
			)
		);
	}
}
