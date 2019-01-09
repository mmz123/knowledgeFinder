package bit.edu.service;

import bit.edu.action.*;


/**
 * 交互操作在textBox2界面结束
 * 1.接收用户对textBox2内容的修改
 * 2.返回修改后的问题文本
 * @author me
 *
 */

public class ModelModify{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String fiText(){
		MainInterface mainInterface = new MainInterface();
		String fitext = mainInterface.textBox2.getText();
		
		System.out.println(fitext);
		
		return fitext;
	}
	
	

}
