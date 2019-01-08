package bit.edu.action;

import java.awt.Container;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import bit.edu.service.AudioToText;

/**
 * 在主界面点击录音按钮之后，弹出录音窗口
 * 录音结束点击提交按钮，调用
 * @author lenovo
 *
 */
public class recordInterface extends JDialog{
	
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
				//关闭窗体
				dispose();
				//
				//读取识别转换为文字
				AudioToText audioToText = new AudioToText();
				audioToText.AnswerText();
				//TODO存储数据到数据库
				//新创建一个查询类QueryTxt(查询语音转化过来的问文字（未校验的结果）)
				
				
				
			}
		});
		
	}

}
