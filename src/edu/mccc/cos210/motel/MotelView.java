package edu.mccc.cos210.motel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JPanel;
public class MotelView extends JPanel {
	private static final long serialVersionUID = 1L;
	private RoachMotel motel;
	private MotelModel model;
	public MotelView(RoachMotel motel, MotelModel model) {
		this.motel = motel;
		this.model = model;
		setLayout(null);
		setBackground(
			new Color(255, 210, 210)
		);
		setPreferredSize(new Dimension(800, 600));
		addMouseListener(
			new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					int score = model.doHits(e.getX(), e.getY());
					getMotel().bumpScore(score);
				}
			}
		);
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g.create();
		synchronized (model) {
			List<Roach> roaches = model.getRoaches();
			for (Roach roach : roaches) {
				roach.getView().paint(g2d);
			}
		}
		g2d.dispose();
	}
	public RoachMotel getMotel() {
		return motel;
	}
	public void setMotel(RoachMotel motel) {
		this.motel = motel;
	}
}
