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

public class JpanelTest extends JFrame {

	private JPanel contentPane;
	private JTextField textBox1;
	private JTextField textBox2;
	private JTextField textBox3;

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
		
		textBox1 = new JTextField();
		textBox1.setHorizontalAlignment(SwingConstants.LEFT);
		textBox1.setFont(new Font("宋体", Font.PLAIN, 12));
		textBox1.setBounds(25, 25, 600, 36);
		contentPane.add(textBox1);
		textBox1.setColumns(20);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setBackground(SystemColor.window);
		btnNewButton.setIcon(new ImageIcon("microphone.jpg"));
		btnNewButton.setBounds(680, 25, 20, 36);
//		btnNewButton.setBorder(null);//去掉边框线
		contentPane.add(btnNewButton);
		
		
		textBox2 = new JTextField();
		textBox2.setFont(new Font("宋体", Font.PLAIN, 12));
		textBox2.setBounds(25, 90, 600, 36);
		contentPane.add(textBox2);
		textBox2.setColumns(10);
		
		JButton button = new JButton("确认");
		button.setToolTipText("问题无误确认");
		button.setBackground(SystemColor.control);
		button.setFont(new Font("宋体", Font.PLAIN, 12));
		button.setBounds(650, 90, 80, 36);
		contentPane.add(button);
		
		textBox3 = new JTextField();
		textBox3.setFont(new Font("宋体", Font.PLAIN, 12));
		textBox3.setBounds(25, 155, 600, 36);
		contentPane.add(textBox3);
		textBox3.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(25, 220, 360, 320);
		contentPane.add(textArea);
		
		Canvas canvas = new Canvas();
		canvas.setBackground(SystemColor.inactiveCaptionBorder);
		canvas.setBounds(411, 220, 363, 320);
		contentPane.add(canvas);
	}
}
