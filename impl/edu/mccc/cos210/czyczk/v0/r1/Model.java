package edu.mccc.cos210.czyczk.v0.r1;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Model {
	private static final double MIN_X = -400;
	private static final double MAX_X = 400;
	private static final double MIN_Y = 0.0;
	private static final double MAX_Y = 600;
	private static final Map<String, PlaneInfo> pilotMap = new HashMap<>();
	private PlaneInfo planeInfo;
	private static final List<Blob> blobs = new LinkedList<>();
	private double x;
	private double y;
	private double tan;
	private double distance;
	private double deltaX;
	private double deltaY;
	private int count = 0;
	private int total = 0;
	public Model() {
		loadBlobs();
		init();
	}
	void loadBlobs() {
		for (int i = 0; i < 100; i++) {
			blobs.add(new Blob());
		}
	}
	void init() {
		x = 0.0;
		y = 0.0;
		deltaX = 0.0;
		deltaY = 0.0;
	}
	public PlaneInfo getPlaneInfo() {
		return planeInfo;
	}
	void setPlaneInfo(PlaneInfo planeInfo) {
		this.planeInfo = planeInfo;
	}
	public static List<Blob> getBlobs() {
		return blobs;
	}
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	void setDeltaX(double x) {
		this.deltaX = x;
	}
	void setDeltaY(double deltaY) {
		this.deltaY = deltaY;
	}
	void collision(double rad1, double rad2) {
		tan = rad1 + rad2;
		for (Blob b : blobs) {
			distance = Math.pow((x - b.x), 2) + Math.pow((y - b.y), 2);
			if((tan * tan) >= distance){
				b.x = 1000;
				count++;
				System.out.println("Count " + count);
		}
	}
}
	void update() {
		for (Blob b : blobs) {
			b.update();
//	    	if (distance(b.x, b.y, x, y) < 8) {
//	    		
//	    		}
	    	}
		x += deltaX;
		if (x < MIN_X) {
			x = MIN_X;
		} else {
			if (x > MAX_X) {
				x = MAX_X;
			}
		}
		y -= deltaY;
		if (y < MIN_Y) {
			y = MIN_Y;
		} else {
			if (y > MAX_Y) {
				y = MAX_Y;
			}
		}
	}
	private double distance(double x1, double y1, double x2, double y2) {
		double a = x2 - x1;
		double b = y2 - y1;
		double c = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
		return c;
	}
	static class Blob {
		public double x;
		public double y;
		public Blob() {
			x = Math.random() * 800.0 - 400.0;
			y = Math.random() * 8000.0 + 800.0;
		}
		public void update() {
			y -= 4.0;
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
	String lookupPlane(String pilot) {
		PlaneInfo pi = pilotMap.get(pilot);
		if (pi == null) {
			pi = pilotMap.get("");
		}
		setPlaneInfo(pi);
		return getPlaneInfo().getPlaneName();
	}
	static {
		pilotMap.put("", new PlaneInfo("RedSquirrel", "RedSquirrel", "RedSquirrel"));
		pilotMap.put("WatsonJ", new PlaneInfo("WatsonJ", "YellowSquirrel", "RedSquirrel"));
		pilotMap.put("TuttleT", new PlaneInfo("TuttleT", "BlueSquirrel", "RedSquirrel"));
		pilotMap.put("AlbarracinJ", new PlaneInfo("AlbarracinJ", "ViralInfection", "Alpha"));
		pilotMap.put("DavilaM", new PlaneInfo("DavilaM", "UnluckyBandit", "Alpha"));
		pilotMap.put("GarciaL", new PlaneInfo("GarciaL", "Misery", "Alpha"));
		pilotMap.put("DawesM", new PlaneInfo("DawesM", "Thunderstorm", "Beta"));
		pilotMap.put("GiragosianM", new PlaneInfo("GiragosianM", "GrimReaper", "Beta"));
		pilotMap.put("WalkerE", new PlaneInfo("WalkerE", "MetalGrinder", "Beta"));
		pilotMap.put("GuzmanC", new PlaneInfo("GuzmanC", "Undertaker", "Gamma"));
		pilotMap.put("ParkmanK", new PlaneInfo("ParkmanK", "Beelzebub", "Gamma"));
		pilotMap.put("PaulB", new PlaneInfo("PaulB", "MommasBadBoy", "Gamma"));
		pilotMap.put("CiemnieckiZ", new PlaneInfo("CiemnieckiZ", "Armageddon", "Delta"));
		pilotMap.put("KonaduJ", new PlaneInfo("KonaduJ", "ScreamingBanshee", "Delta"));
		pilotMap.put("PatelD", new PlaneInfo("PatelD", "WidowMaker", "Delta"));
		pilotMap.put("JoshiK", new PlaneInfo("JoshiK", "Apocalypse", "Epsilon"));
		pilotMap.put("LeeK", new PlaneInfo("LeeK", "BlindingLight", "Epsilon"));
		pilotMap.put("ShammaJ", new PlaneInfo("ShammaJ", "LethalInjection", "Epsilon"));
		pilotMap.put("BryantMatthewsJ", new PlaneInfo("BryantMatthewsJ", "BladesOfSteel", "Theta"));
		pilotMap.put("BurtonT", new PlaneInfo("BurtonT", "Zubenelgenubi", "Theta"));
		pilotMap.put("SharmaS", new PlaneInfo("SharmaS", "RustySpoon", "Theta"));
		pilotMap.put("JeonH", new PlaneInfo("JeonH", "Rasputin", "Kappa"));
		pilotMap.put("PatelJ", new PlaneInfo("PatelJ", "MisterLucifer", "Kappa"));
		pilotMap.put("YuA", new PlaneInfo("YuA", "FireDancer", "Kappa"));
		pilotMap.put("DecletP", new PlaneInfo("DecletP", "GlassCannon", "Lambda"));
	    pilotMap.put("FernandezK", new PlaneInfo("FernandezK", "BoneCrusher", "Lambda"));
	    pilotMap.put("MyslowskiR", new PlaneInfo("MyslowskiR", "GatesOfHades", "Lambda"));
	    pilotMap.put("CheffA", new PlaneInfo("CheffA", "DarkOfNight", "Sigma"));
	    pilotMap.put("JohnsonT", new PlaneInfo("JohnsonT", "SilentNinja", "Sigma"));
	    pilotMap.put("SokolL", new PlaneInfo("SokolL", "FlamingBurrito", "Sigma"));
	    pilotMap.put("AlonsoN", new PlaneInfo("AlonsoN", "AngryPorkChop", "Omega"));
	    pilotMap.put("DimemmoJ", new PlaneInfo("DimemmoJ", "SmokeyPitMaster", "Omega"));
	    pilotMap.put("SinghS", new PlaneInfo("SinghS", "Intimidator", "Omega"));
	    pilotMap.put("MurphyJ", new PlaneInfo("MurphyJ", "ShockAndAwe", "USA"));
	    pilotMap.put("JJM2", new PlaneInfo("MurphyJ", "ShockAndAwe", "USA"));
	    pilotMap.put("JJM3", new PlaneInfo("MurphyJ", "ShockAndAwe", "USA"));
	}
}
