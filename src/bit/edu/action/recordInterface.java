package bit.edu.action;

import java.awt.Container;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import bit.edu.service.AudioToText;

/**
 * 在主界面点击录音按钮之后，弹出录音窗口
 * 录音结束点击提交按钮，调用
 * @author lenovo
 *
 */
public class recordInterface extends JDialog{
	
	private String atext;
	
	public recordInterface(mainInterface mainInterface) {
		// TODO Auto-generated constructor stub
		//实例化一个JDialog类对象，指定对话框的父窗体、窗体标题和类型
		super(mainInterface, "录音窗口", true);
		Container container = getContentPane();
		container.setLayout(null);
//		container.add(new JLabel(""));
		setBounds(400, 300, 300, 200);
		setBackground(SystemColor.inactiveCaption);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//录音结束按钮
		JButton button = new JButton("提交");
		button.setBackground(SystemColor.window);
		button.setBounds(180, 50, 60, 36);
		button.setFocusPainted(false);
		container.add(button);
		//为录音按钮添加鼠标单击事件
		button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//关闭当前窗体，不关闭父窗口
				dispose();
				
				//运行语音识别程序，读取识别转换的文字
				AudioToText audioToText = new AudioToText();
				//textBox1要接收的转换后的文本
				String AnswerText = audioToText.AnswerText();
				System.out.println(AnswerText);
				recordInterface.this.atext = AnswerText;
			}
		});
		
	}

	public String getAtext() {
		return atext;
	}

	public void setAtext(String atext) {
		this.atext = atext;
	}


}
