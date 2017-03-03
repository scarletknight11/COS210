package edu.mccc.cos210.czyczk.v0;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class Czyczk {
	private JFrame jf;
	private static final int FPS = 30;
	private int score = 0;
	private int total = 0;
	private static final String TITLE = "Czyczk.v0 - %d out of %d [Score = %.1f%%]";
	public Czyczk() {
		jf = new JFrame(String.format(TITLE, 0, 0, 0.0));
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Model model = new Model();
		model.lookupPlane(getPassword());
		Controller control = new Controller(model);
		View view = new View(model);
		jf.add(view, BorderLayout.CENTER);
		jf.pack();
		jf.setResizable(false);
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
		view.addMouseListener(control);
		view.addMouseMotionListener(control);
		view.addKeyListener(control);
		view.requestFocusInWindow();
		new javax.swing.Timer(
			1000 / FPS,
			(ae) -> {
				model.update();
				view.repaint();
			}
		).start();
	}
	public static void main(String[] sa) {
		EventQueue.invokeLater(Czyczk::new);
	}
   public static String getPassword() {
        JPanel jp = new JPanel();
        final JPasswordField jpf = new JPasswordField(10);
        jp.add(new JLabel("Password"));
        jp.add(jpf);
        JOptionPane jop = new JOptionPane(jp, JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION) {
            private static final long serialVersionUID = 1L;
			@Override
            public void selectInitialValue() {
                jpf.requestFocusInWindow();
            }
        };
        jop.createDialog(null, "Czyczk").setVisible(true);
        if (!jop.getValue().equals(JOptionPane.OK_OPTION)) {
        	System.exit(-1);
        }
        return jpf.getPassword().length == 0 ? "" : new String(jpf.getPassword());
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
