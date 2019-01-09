package bit.edu.action;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

public class JFramtest extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFramtest frame = new JFramtest();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JFramtest() {
		setBounds(100, 100, 450, 300);

	}

}
