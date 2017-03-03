package edu.mccc.cos210.czyczk.v0;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputListener;

public class Controller extends KeyAdapter implements MouseInputListener {
	private static double strafeRate = 8.0;
	private Model model;
	public Controller(Model model) {
		this.model = model;
	}
	@Override
	public void keyPressed(KeyEvent ke) {
		switch (ke.getKeyCode()) {
			case KeyEvent.VK_LEFT:
			case KeyEvent.VK_A:
				model.setDeltaX(-strafeRate);
				break;
			case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_D:
				model.setDeltaX(strafeRate);
				break;
			case KeyEvent.VK_UP:
			case KeyEvent.VK_W:
				model.setDeltaY(-strafeRate);
				break;
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_S:
				model.setDeltaY(strafeRate);
				break;
			default:
				break;
		}
	}
	@Override
	public void keyReleased(KeyEvent ke) {
		switch (ke.getKeyCode()) {
			case KeyEvent.VK_LEFT:
			case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_A:
			case KeyEvent.VK_D:
				model.setDeltaX(0.0);
				break;
			case KeyEvent.VK_UP:
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_W:
			case KeyEvent.VK_S:
				model.setDeltaY(0.0);
				break;
			default:
				break;
		}
	}
	@Override
	public void mouseClicked(MouseEvent me) {
	}
	@Override
	public void mouseEntered(MouseEvent me) {
	}
	@Override
	public void mouseExited(MouseEvent me) {
	}
	@Override
	public void mousePressed(MouseEvent me) {
	}
	@Override
	public void mouseReleased(MouseEvent me) {
	}
	@Override
	public void mouseDragged(MouseEvent me) {
	}
	@Override
	public void mouseMoved(MouseEvent me) {
	}
}
