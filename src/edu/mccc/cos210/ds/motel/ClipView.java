package edu.mccc.cos210.ds.motel;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Line;
public class ClipView implements Observer {
	private static final Clip[] clip = new Clip[3];
	public ClipView() {
		try {
			clip[0] = (Clip) AudioSystem.getLine(
				new Line.Info(Clip.class)
			);
			clip[0].open(
				AudioSystem.getAudioInputStream(
					new URL(
						"file:clips/vend.wav"
					)
				)
			);
			clip[1] = (Clip) AudioSystem.getLine(
				new Line.Info(Clip.class)
			);
			clip[1].open(
				AudioSystem.getAudioInputStream(
					new URL(
						"file:clips/miss.wav"
					)
				)
						);
			clip[2] = (Clip) AudioSystem.getLine(
				new Line.Info(Clip.class)
			);
			clip[2].open(
				AudioSystem.getAudioInputStream(
					new URL(
						"file:clips/splat.wav"
					)
				)
			);
		} catch (Exception ex) {
		}
	}
	public static void play(final int n) {
		new Thread(
			() -> {
				try {
					clip[n].stop();
					clip[n].setFramePosition(0);
					clip[n].loop(0);
					Thread.sleep(250);
				} catch (Exception ex) {
				}
			}
		).start();
	}
	@Override
	public void update(Observable o, Object arg) {
		if (arg != null) {
			play((Integer) arg);
		}
	}
}
