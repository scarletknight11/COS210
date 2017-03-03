package edu.mccc.cos210.czyczk.util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ShowPlanes {
	private List<BufferedImage> thePlanes = new ArrayList<>();
	private List<String> thePlaneNames = new ArrayList<>();
	private static Map<String, PlaneInfo> planeMap = new HashMap<>();
	private int index = 0;
	private boolean timerOn = true;
	public ShowPlanes() {
		JFrame jf = new JFrame("Czyczk Show Planes");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loadPlanes();
		JPanel jp = new MyJPanel();
		jf.add(jp, BorderLayout.CENTER);
		jf.pack();
		jf.setResizable(false);
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
		javax.swing.Timer t = new javax.swing.Timer(3000, ae -> jp.repaint());
		t.start();
		jp.addMouseListener(
				new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent me) {
						if (timerOn) {
							t.stop();
							jf.setTitle("Czyczk Show Planes (Paused)");
						} else {
							t.start();
							jf.setTitle("Czyczk Show Planes");
							jp.repaint();
						}
						timerOn = !timerOn;
					}
				}
			);
	}
	public static void main(String[] sa) {
		EventQueue.invokeLater(ShowPlanes::new);
	}
	private void loadPlanes() {
		try {
			String[] planes = new File(
				"./images/planes/"
			).list(
				(dir, name) -> name.endsWith("_1.0.png")
			);
			for (String p : planes) {
				System.out.println(p);
				thePlaneNames.add(p.substring(0, p.indexOf("_")));
				BufferedImage bi = ImageIO.read(new File("./images/planes/" + p));
				thePlanes.add(bi);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	class MyJPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		public MyJPanel() {
			setBackground(Color.WHITE);
			setPreferredSize(new Dimension(768, 768));
		}
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g.create();
			g2d.setFont(g2d.getFont().deriveFont(32.0f));
			g2d.drawRenderedImage(
				thePlanes.get(index),
				AffineTransform.getTranslateInstance(
					getWidth() / 2.0 - thePlanes.get(index).getWidth() / 2.0,
					getHeight() / 2.0 - thePlanes.get(index).getHeight() / 2.0
				)
			);
			g2d.drawString(thePlaneNames.get(index), 4, 28);
			PlaneInfo pi = planeMap.get(thePlaneNames.get(index));
			g2d.drawString(pi.getPilotName(), 4, 64);
			g2d.drawString(pi.getSquadronName(), 4, 100);
			index = index == (thePlanes.size() - 1) ? 0 : index + 1;
			g2d.dispose();
		}
	}
	static class PlaneInfo {
		String planeName;
		String pilotName;
		String squadronName;
		public PlaneInfo(String pilotName, String planeName, String squadronName) {
			setPilotName(pilotName);
			setPlaneName(planeName);
			setSquadronName(squadronName);
		}
		public String getPilotName() {
			return pilotName;
		}
		public void setPilotName(String pilotName) {
			this.pilotName = pilotName;
		}
		public String getPlaneName() {
			return planeName;
		}
		public void setPlaneName(String planeName) {
			this.planeName = planeName;
		}
		public String getSquadronName() {
			return squadronName;
		}
		public void setSquadronName(String squadronName) {
			this.squadronName = squadronName;
		}
	}
	static {
		planeMap.put("RedSquirrel", new PlaneInfo("", "RedSquirrel", ""));
		planeMap.put("BlueSquirrel", new PlaneInfo("", "BlueSquirrel", ""));
		planeMap.put("YellowSquirrel", new PlaneInfo("", "YellowSquirrel", ""));
		planeMap.put("ViralInfection", new PlaneInfo("AlbarracinJ", "ViralInfection", "Alpha"));
		planeMap.put("UnluckyBandit", new PlaneInfo("DavilaM", "UnluckyBandit", "Alpha"));
		planeMap.put("Misery", new PlaneInfo("GarciaL", "Misery", "Alpha"));
		planeMap.put("Thunderstorm", new PlaneInfo("DawesM", "Thunderstorm", "Beta"));
		planeMap.put("GrimReaper", new PlaneInfo("GiragosianM", "GrimReaper", "Beta"));
		planeMap.put("MetalGrinder", new PlaneInfo("WalkerE", "MetalGrinder", "Beta"));
		planeMap.put("Undertaker", new PlaneInfo("GuzmanC", "Undertaker", "Gamma"));
		planeMap.put("Beelzebub", new PlaneInfo("ParkmanK", "Beelzebub", "Gamma"));
		planeMap.put("MommasBadBoy", new PlaneInfo("PaulB", "MommasBadBoy", "Gamma"));
		planeMap.put("Armageddon", new PlaneInfo("CiemnieckiZ", "Armageddon", "Delta"));
		planeMap.put("ScreamingBanshee", new PlaneInfo("KonaduJ", "ScreamingBanshee", "Delta"));
		planeMap.put("WidowMaker", new PlaneInfo("PatelD", "WidowMaker", "Delta"));
		planeMap.put("Apocalypse", new PlaneInfo("JoshiK", "Apocalypse", "Epsilon"));
		planeMap.put("BlindingLight", new PlaneInfo("LeeK", "BlindingLight", "Epsilon"));
		planeMap.put("LethalInjection", new PlaneInfo("ShammaJ", "LethalInjection", "Epsilon"));
		planeMap.put("BladesOfSteel", new PlaneInfo("BryantMatthewsJ", "BladesOfSteel", "Theta"));
		planeMap.put("Zubenelgenubi", new PlaneInfo("BurtonT", "Zubenelgenubi", "Theta"));
		planeMap.put("RustySpoon", new PlaneInfo("SharmaS", "RustySpoon", "Theta"));
		planeMap.put("Rasputin", new PlaneInfo("JeonH", "Rasputin", "Kappa"));
		planeMap.put("MisterLucifer", new PlaneInfo("PatelJ", "MisterLucifer", "Kappa"));
		planeMap.put("FireDancer", new PlaneInfo("YuA", "FireDancer", "Kappa"));
		planeMap.put("GlassCannon", new PlaneInfo("DecletP", "GlassCannon", "Lambda"));
	    planeMap.put("BoneCrusher", new PlaneInfo("FernandezK", "BoneCrusher", "Lambda"));
	    planeMap.put("GatesOfHades", new PlaneInfo("MyslowskiR", "GatesOfHades", "Lambda"));
	    planeMap.put("DarkOfNight", new PlaneInfo("CheffA", "DarkOfNight", "Sigma"));
	    planeMap.put("SilentNinja", new PlaneInfo("JohnsonT", "SilentNinja", "Sigma"));
	    planeMap.put("FlamingBurrito", new PlaneInfo("SokolL", "FlamingBurrito", "Sigma"));
	    planeMap.put("AngryPorkChop", new PlaneInfo("AlonsoN", "AngryPorkChop", "Omega"));
	    planeMap.put("SmokeyPitMaster", new PlaneInfo("DimemmoJ", "SmokeyPitMaster", "Omega"));
	    planeMap.put("Intimidator", new PlaneInfo("SinghS", "Intimidator", "Omega"));
	    planeMap.put("ShockAndAwe", new PlaneInfo("MurphyJ", "ShockAndAwe", "USA"));
	}
}