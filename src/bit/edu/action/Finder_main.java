package bit.edu.action;

import java.awt.EventQueue;

/**
 * 
 * @author lenovo
 *
 */

public class Finder_main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//主方法中调用CreatInterface()方法
//		new mainInterface().CreatInterface("问答系统");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new mainInterface().CreatInterface("问答系统");
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
