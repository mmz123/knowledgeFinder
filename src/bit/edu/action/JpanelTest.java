package bit.edu.action;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import javax.swing.Box;
import java.awt.Component;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.JFormattedTextField;
import java.awt.Canvas;
import java.awt.SystemColor;
import java.awt.FlowLayout;
import java.awt.Window.Type;
import java.awt.GridLayout;

public class JpanelTest extends JFrame {

	private JPanel contentPane;
	private JTextField textBox1;
	private JTextField textBox2;
	private JTextField textBox3;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JpanelTest frame = new JpanelTest();
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
	public JpanelTest() {
		setBackground(SystemColor.inactiveCaption);
		setTitle("测试");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setBounds(5, 6, 622, 90);
		btnNewButton.setBackground(SystemColor.window);
		btnNewButton.setIcon(new ImageIcon("microphone.jpg"));
//		btnNewButton.setBorder(null);//去掉边框线
		contentPane.add(btnNewButton);
		
		label = new JLabel("");
		label.setBounds(391, 109, 386, 90);
		contentPane.add(label);
		
		label_1 = new JLabel("");
		label_1.setBounds(15, 109, 386, 90);
		contentPane.add(label_1);
		
		textBox1 = new JTextField();
		textBox1.setBounds(662, 6, 106, 90);
		textBox1.setHorizontalAlignment(SwingConstants.LEFT);
		textBox1.setFont(new Font("宋体", Font.PLAIN, 12));
		contentPane.add(textBox1);
		textBox1.setColumns(20);
		
		label_2 = new JLabel("");
		label_2.setBounds(5, 186, 386, 90);
		contentPane.add(label_2);
		
		Canvas canvas = new Canvas();
		canvas.setBounds(391, 186, 386, 90);
		canvas.setBackground(SystemColor.inactiveCaptionBorder);
		contentPane.add(canvas);
		
		label_3 = new JLabel("");
		label_3.setBounds(5, 276, 386, 90);
		contentPane.add(label_3);
		
		label_4 = new JLabel("");
		label_4.setBounds(391, 276, 386, 90);
		contentPane.add(label_4);
		
		
		textBox2 = new JTextField();
		textBox2.setBounds(5, 366, 386, 90);
		textBox2.setFont(new Font("宋体", Font.PLAIN, 12));
		contentPane.add(textBox2);
		textBox2.setColumns(10);
		
		JButton button = new JButton("确认");
		button.setBounds(391, 366, 386, 90);
		button.setToolTipText("问题无误确认");
		button.setBackground(SystemColor.control);
		button.setFont(new Font("宋体", Font.PLAIN, 12));
		contentPane.add(button);
		
		textBox3 = new JTextField();
		textBox3.setBounds(5, 456, 386, 90);
		textBox3.setFont(new Font("宋体", Font.PLAIN, 12));
		contentPane.add(textBox3);
		textBox3.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(391, 456, 386, 90);
		contentPane.add(textArea);
	}
}
