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
import bit.edu.service.Match;

/**
 * 1.在主界面点击录音按钮之后，弹出录音窗口，调用getAudio程序
 * 2.录音结束点击提交按钮，停止getAudio中record程序，关闭录音窗口
 * 3.调用AudioToText程序，接收音频转换的文本，放入文本框1
 * 4.调用Match问题与模型匹配的程序，文本框2接收匹配之后的标准问题
 * @author lenovo
 *
 */
public class recordInterface extends JDialog{
	
	private String content;
	private String content1;
	
	public recordInterface(MainInterface mainInterface) {
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
		button.setToolTipText("录音结束");
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
				
			/**
			* 停止录音，getAudio程序运行结束
			*/
			
				
				
				//运行语音识别程序，读取识别转换的文字
				AudioToText audioToText = new AudioToText();
				//textBox1要接收的转换后的文本
				String questionText = audioToText.questionText();
				//测试
				System.out.println("测试  "+questionText);
				recordInterface.this.content = questionText;
							
				//运行Match匹配程序
				Match match = new Match();
				//文本框2要接收的与模板匹配之后的标准问题文本
				String finalText = match.finalText();
				//测试
				System.out.println("测试  "+finalText);
				recordInterface.this.content1 = finalText;
			
//				//文本框2要接收的与模板匹配之后的最终的问题文本
//				String finalText = match.finalText();
//				System.out.println(finalText);
//				recordInterface.this.content = finalText;
				
				
				
			}
		});
		
	}

	public String getQuetiontext() {
		return content;
	}

//	public void setQuetiontext(String text) {
//		this.content = text;
//	}
	
	public String getFinaltext(){
		return content1;
	}

//	public void setFinaltext(String text) {
//		this.content1 = text;
//	}


}
