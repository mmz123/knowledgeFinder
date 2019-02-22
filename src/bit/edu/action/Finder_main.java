package bit.edu.action;

import java.awt.EventQueue;

import bit.edu.service.Match;
import bit.edu.service.Tag;

/**
 * 
 * @author lenovo
 *
 */

public class Finder_main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		test();

		// //主方法中调用CreatInterface()方法
		//// new mainInterface().CreatInterface("问答系统");
		//
		// EventQueue.invokeLater(new Runnable() {
		// public void run() {
		// try {
		// new MainInterface().CreatInterface("问答系统");
		//
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// }
		// });

	}

	public static void test() {
		// String s = "梅卡瓦主战坦克 和 印度阿琼主战坦克 的 宽度 是多少";
		String s = "印度阿琼主战坦克 的 长度 是多少";
		Match.model(s);
		Tag.Tagging(s);
	}

}
