package bit.edu.action;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JTextField;

public class JFramtest extends JInternalFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

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
		
		textField = new JTextField();
		textField.setToolTipText("问题文本");
		getContentPane().add(textField, BorderLayout.WEST);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		getContentPane().add(textField_1, BorderLayout.CENTER);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		getContentPane().add(textField_2, BorderLayout.EAST);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		getContentPane().add(textField_3, BorderLayout.SOUTH);
		textField_3.setColumns(10);

	}

}
