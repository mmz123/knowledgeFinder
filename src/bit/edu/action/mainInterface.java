package bit.edu.action;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import bit.edu.service.AudioToText;
import bit.edu.service.getAudio;




/**
 * 主界面设计，包含文本框、按钮的尺寸、位置设置
 * 弹出的录音窗口的设置
 * @author lenovo
 *
 */

public class mainInterface extends JFrame{
//	private JPanel contentPane;
//	private JTextField textBox1;
//	private JTextField textBox2;
//	private JTextField textBox3;

	
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
//		textBox1 = new JTextField();
//		textBox1.setHorizontalAlignment(SwingConstants.LEFT);
//		textBox1.setFont(new Font("宋体", Font.PLAIN, 12));
//		textBox1.setBounds(25, 25, 600, 36);
//		contentPane.add(textBox1);
//		textBox1.setColumns(20);	
		textBox1 textBox1 = new textBox1();
		contentPane.add(textBox1.textBox_v1());

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
				new recordInterface(mainInterface.this).setVisible(true);
				//获取数据
				getAudio getAudio = new getAudio();
				getAudio.diaoyong();
				//保存缓存中
				
				
				
				
			
			}
		});
		
		//文本框2
		textBox2 textBox2 = new textBox2();
		contentPane.add(textBox2.textBox_v2());



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



//public void CreatInterface(String title){
//JFrame jf = new JFrame(title);
//Container container = jf.getContentPane();
////JLabel jl = new JLabel("主界面");
//////使标签文字居于上方中间
////jl.setHorizontalAlignment(SwingConstants.CENTER);
////container.add(jl);
//container.setBackground(Color.WHITE);
//jf.setVisible(true);
//jf.setSize(1000, 800);
////窗口关闭方式
//jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//
//
//}


