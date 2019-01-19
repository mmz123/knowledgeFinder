package bit.edu.action;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import bit.edu.service.*;

/**
 * 主界面设计，包含文本框、按钮的尺寸、位置设置 弹出的录音窗口的设置
 * 
 * 文本框1功能： 功能1：接收AudioToText输出的问题文本 功能2：接收用户直接输入的问题文本
 * 
 * 文本框2功能： 功能1：接收按模板转换后的标准问题 功能2：支持用户对问题的部分文本做修改 功能3：返回用户修改后的内容
 * 
 * 文本框3功能： 功能1：接收ModelToQuery返回的查询语句
 * 
 * 
 * 
 * @author lenovo
 *
 */

public class MainInterface extends JFrame {
	// private JPanel contentPane;
	public JTextField textBox1;
	public JTextField textBox2;
	private JTextField textBox3;
	private getAudio getAudio = new getAudio();

	/**
	 * 
	 */
	private static final long serialVersionUID = 5290416764892459818L;

	public void CreatInterface(String title) {
		setTitle(title);// 窗体标题
		setBackground(SystemColor.inactiveCaption);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // 窗口关闭方式
		setBounds(100, 100, 1000, 700);

		JPanel contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		setVisible(true);

		// 文本框1
		textBox1 = new JTextField();
		JLabel label1 = new JLabel("问题文本");
		label1.setBounds(25, 25, 75, 36);
		
		textBox1.setHorizontalAlignment(SwingConstants.LEFT);
		textBox1.setFont(new Font("宋体", Font.PLAIN, 20));
		textBox1.setBounds(100, 25, 700, 36);
		textBox1.setColumns(20);

		contentPane.add(textBox1);
		contentPane.add(label1, textBox1);

		// 文本框2
		textBox2 = new JTextField();
		JLabel label2 = new JLabel("词性标注");
		label2.setBounds(25, 90, 75, 36);
		
		textBox2.setHorizontalAlignment(SwingConstants.LEFT);
		textBox2.setFont(new Font("宋体", Font.PLAIN, 20));
		textBox2.setBounds(100, 90, 700, 36);
		textBox2.setColumns(20);
		textBox2.setVisible(true);

		contentPane.add(textBox2);
		contentPane.add(label2, textBox2);
		// textBox2.setText("model question");

		// 在textBox2中定义其属性格式之后在这里添加的方式
		// textBox2 textBox2 = new textBox2();
		// contentPane.add(textBox2.textBox_v2());

		// 文本框3
		textBox3 = new JTextField();
		JLabel label3 = new JLabel("查询语句");
		label3.setBounds(25, 155, 75, 36);

		textBox3.setHorizontalAlignment(SwingConstants.LEFT);
		textBox3.setFont(new Font("宋体", Font.PLAIN, 20));
		textBox3.setBounds(100, 155, 700, 36);
		textBox3.setEditable(false);
		
		textBox3.setColumns(20);
		textBox3.setVisible(true);

		contentPane.add(textBox3);
		contentPane.add(label3, textBox3);

		// 答案框
		JTextArea AnswerBox = new JTextArea();
		AnswerBox.setFont(new Font("宋体", Font.PLAIN, 20));
		AnswerBox.setBounds(25, 220, 460, 320);
		AnswerBox.setColumns(20);
		AnswerBox.setLineWrap(true);// 自动换行

		JScrollPane jsp = new JScrollPane(AnswerBox); // 添加滚动条
		AnswerBox.setBounds(25, 220, 460, 700); // 设置 JTextArea 宽100,高500
		jsp.setBounds(25, 220, 460, 420); // 设置 JScrollPane 宽100,高200

		contentPane.add(AnswerBox);

		// 录音按钮
		JButton record = new JButton("");
		record.setBackground(SystemColor.window);
		record.setIcon(new ImageIcon("microphone.jpg"));
		record.setBounds(860, 25, 20, 36);
		record.setBorder(null);

		contentPane.add(record);
		// 为录音按钮添加鼠标单击事件
		record.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				
				// 调用Record录音程序
				getAudio.Record();
				
				
				// 使recordInterface窗体可见
				recordInterface recordinterface = new recordInterface(MainInterface.this);
				recordinterface.setVisible(true);
				

				// 将音频转换的文本显示到文本框1中
				textBox1.setText(recordinterface.getQuetiontext());
				

				// 将匹配模板之后的标准问题文本显示到文本框2中
				textBox2.setText(recordinterface.getFinaltext());

			}
		});

		// 确认按钮
		JButton confirm = new JButton("确认");
		confirm.setToolTipText("问题无误确认");
		confirm.setBackground(SystemColor.control);
		confirm.setFont(new Font("宋体", Font.PLAIN, 16));
		confirm.setBounds(830, 90, 80, 36);
		confirm.setFocusPainted(false);
		contentPane.add(confirm);
		// 为确认按钮添加鼠标单击事件
		confirm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				// 点击确认按钮之后，运行ModelModify获取文本框2里面的内容最终问题文本
				// ModelModify modelModify = new ModelModify();
				// modelModify.fiText();
				String finaltext = textBox2.getText();
				ModelModify modelModify = new ModelModify();
				modelModify.getfiText("测试" + finaltext);

				// 文本框3的内容，运行ModelToQuery，返回Neo4j查询语句，不可编辑
				ModelToQuery modelToQuery = new ModelToQuery();
				textBox3.setText(modelToQuery.QueryText());
				

				// AnswerBox的内容，运行Neo4jQuery，以文本形式返回Neo4j的查询结果
				Neo4jQuery neo4jQuery = new Neo4jQuery();
				AnswerBox.setText(neo4jQuery.Answer());

			}
		});

		// 图谱展示
		Canvas canvas = new Canvas();
		canvas.setBackground(SystemColor.inactiveCaptionBorder);
		canvas.setBounds(511, 220, 463, 420);
		contentPane.add(canvas);

	}

	public getAudio getGetAudio() {
		return getAudio;
	}

	public void setGetAudio(getAudio getAudio) {
		this.getAudio = getAudio;
	}
	
	

}
