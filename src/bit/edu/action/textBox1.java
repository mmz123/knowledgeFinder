package bit.edu.action;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * 功能1：接收AudioToText输出的问题文本并输出
 * 
 * 功能2：接收用户直接输入的问题文本并输出
 * @author me
 *
 */
public class textBox1 extends JFrame{
//	private JTextField textBox1;
	//用户输入的问题文本
	public JTextField textBox_v1(){

		//文本框1
		JTextField textBox = new JTextField();
		textBox.setHorizontalAlignment(SwingConstants.LEFT);
		textBox.setFont(new Font("宋体", Font.PLAIN, 12));
		textBox.setBounds(25, 25, 600, 36);
		textBox.setColumns(20);
		return textBox;
		
		
		
	}

	
	public textBox1() {
		
		
		
		
	}
	
	
}
