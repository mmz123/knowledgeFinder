package bit.edu.action;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import bit.edu.service.getAudio;




/**
 * 主界面设计，包含文本框、按钮的尺寸、位置设置
 * 弹出的录音窗口的设置
 * 
 * 文本框1功能：
 * 功能1：接收AudioToText输出的问题文本并输出
 * 功能2：接收用户直接输入的问题文本并输出
 * 
 * @author lenovo
 *
 */

public class mainInterface extends JFrame{
//	private JPanel contentPane;
	private JTextField textBox1;
	private JTextField textBox2;
//	private JTextField textBox3;

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5290416764892459818L;

	public void CreatInterface(String title){
		setTitle(title);//窗体标题
		setBackground(SystemColor.inactiveCaption);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);	//窗口关闭方式
		setBounds(100, 100, 800, 600);
		
		JPanel contentPane  = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		setVisible(true);

		//文本框1
		textBox1 = new JTextField();
		textBox1.setHorizontalAlignment(SwingConstants.LEFT);
		textBox1.setFont(new Font("宋体", Font.PLAIN, 20));
		textBox1.setBounds(25, 25, 600, 36);
		textBox1.setColumns(20);
		
		contentPane.add(textBox1);
		
		

		//录音按钮
		JButton record = new JButton("");
		record.setBackground(SystemColor.window);
		record.setIcon(new ImageIcon("microphone.jpg"));
		record.setBounds(680, 25, 20, 36);
		record.setBorder(null);
		
		contentPane.add(record);
		//为录音按钮添加鼠标单击事件
		record.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//使recordInterface窗体可见
				recordInterface recordinterface  = new recordInterface(mainInterface.this);
				recordinterface.setVisible(true);
				
				
				//调用音频采集
				getAudio getAudio = new getAudio();
				getAudio.Record();
				
				//将音频转换的文本显示到文本框1中
				textBox1.setText(recordinterface.getAtext());
				
				
				
				
			}
		});
		
		
		
		//文本框2
		
		textBox2 = new JTextField();
		textBox2.setHorizontalAlignment(SwingConstants.LEFT);
		textBox2.setFont(new Font("宋体", Font.PLAIN, 20));
		textBox2.setBounds(25, 90, 600, 36);
		textBox2.setColumns(20);
		textBox2.setVisible(true);
		
		contentPane.add(textBox2);
		
		textBox2.setText("model answer");

//在textBox2中定义格式之后的添加方式		
//		textBox2 textBox2 = new textBox2();
//		contentPane.add(textBox2.textBox_v2());



		//确认按钮
		JButton button = new JButton("确认");
		button.setToolTipText("问题无误确认");
		button.setBackground(SystemColor.control);
		button.setFont(new Font("宋体", Font.PLAIN, 12));
		button.setBounds(650, 90, 80, 36);
		button.setFocusPainted(false);
		contentPane.add(button);
		
		//文本框3
		textBox3 textBox3 = new textBox3();
		contentPane.add(textBox3.textBox_v3());
		
		//答案框
		JTextArea textArea = new JTextArea();
		textArea.setBounds(25, 220, 360, 320);
		contentPane.add(textArea);
		
		//图谱展示
		Canvas canvas = new Canvas();
		canvas.setBackground(SystemColor.inactiveCaptionBorder);
		canvas.setBounds(411, 220, 363, 320);
		contentPane.add(canvas);

		
	}

}


