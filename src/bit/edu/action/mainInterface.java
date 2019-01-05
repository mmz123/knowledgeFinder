package bit.edu.action;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;



/**
 * 主界面设计，包含文本框、按钮的尺寸、位置设置
 * 弹出的录音窗口的设置
 * @author lenovo
 *
 */

public class mainInterface extends JFrame{
	
	
	public void CreatInterface(String title){
		JFrame jf = new JFrame(title);
		Container container = jf.getContentPane();
		container.setLayout(null);
		
		JButton bl = new JButton();
		bl.setIcon(new ImageIcon("microphone.jpg"));
		bl.setBounds(600, 20, 50, 80);
		//为按钮添加鼠标单击事件
		bl.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//使recordInterface窗体可见
				new recordInterface(mainInterface.this).setVisible(true);
				
			}
		});
		container.add(bl);
		
		container.setBackground(Color.LIGHT_GRAY);
		jf.setVisible(true);
		jf.setSize(1000, 800);
		//窗口关闭方式
		jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		
	}
		
//	public void CreatInterface(String title){
//		JFrame jf = new JFrame(title);
//		Container container = jf.getContentPane();
////		JLabel jl = new JLabel("主界面");
////		//使标签文字居于上方中间
////		jl.setHorizontalAlignment(SwingConstants.CENTER);
////		container.add(jl);
//		container.setBackground(Color.WHITE);
//		jf.setVisible(true);
//		jf.setSize(1000, 800);
//		//窗口关闭方式
//		jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//
//		
//	}
	
	

}
