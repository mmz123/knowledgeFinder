package bit.edu.action;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * 1.接收ModelToQuery输出的Neo4j查询语句并输出
 * @author me
 *
 */

public class textBox3 extends JFrame{
	public JTextField textBox_v3(){

		//文本框2
		JTextField textBox = new JTextField();
		textBox.setHorizontalAlignment(SwingConstants.LEFT);
		textBox.setFont(new Font("宋体", Font.PLAIN, 12));
		textBox.setBounds(25, 155, 600, 36);

		textBox.setColumns(20);
		return textBox;
		
	}

	public textBox3() {

		
	}
	

}
