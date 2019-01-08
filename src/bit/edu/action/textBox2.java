package bit.edu.action;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * 1.接收TextToModel输出的文本
 * 2.支持对文本的修改
 * 
 * 3.若修改，接收ModelModify的输出结果，并输出（交互操作）
 * 4.点击查询按钮，确认问题（结束修改操作），输出问题文本
 * @author me
 *
 */

public class textBox2 extends JFrame{
	public JTextField textBox_v2(){

		//文本框2
		JTextField textBox = new JTextField();
		textBox.setHorizontalAlignment(SwingConstants.LEFT);
		textBox.setFont(new Font("宋体", Font.PLAIN, 20));
		textBox.setBounds(25, 90, 600, 36);

		textBox.setColumns(20);
		return textBox;
		
			
	}

	public textBox2() {

		
	}

}
