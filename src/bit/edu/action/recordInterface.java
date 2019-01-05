package bit.edu.action;

import java.awt.Container;

import javax.swing.JDialog;
import javax.swing.JLabel;

/**
 * 在主界面点击录音按钮之后，弹出录音窗口
 * @author lenovo
 *
 */
public class recordInterface extends JDialog{
	
	public recordInterface(mainInterface mainInterface) {
		// TODO Auto-generated constructor stub
		//实例化一个JDialog类对象，指定对话框的父窗体、窗体标题和类型
		super(mainInterface, "录音窗口", true);
		Container container = getContentPane();
		container.add(new JLabel("录音"));
		setBounds(400, 300, 300, 200);
		
	}

//	public void record(mainInterface Frame){
//		//实例化一个JDialog类对象，指定对话框的父窗体、窗体标题和类型
//		super(frame, "录音窗口", true);
//		Container container = getContentPane();
//		container.add(new JLabel("录音"));
//		setBounds(200, 200, 180, 180);
//	
//	}

}
