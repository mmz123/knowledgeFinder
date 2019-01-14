package bit.edu.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 1.接收textProcess的输出，将处理过的文本与问题模板融合 
 * 
 * @author me
 *
 */

public class Match {

	public String normalText() {
		String finalText;

		finalText = "88";
		return finalText;
	}

	// 匹配模板
	public static String Matching() {
		String pathname2 = "E:/sources/Audio/PosTag.txt"; // 匹配模板程序读入文件路径
		String str3 = null;// 存放完成词性标注后的问题
		String s1 = null;// 存放问题中的实体和关系
		int a = 0;
		String m = null;
		try (FileReader reader1 = new FileReader(pathname2); BufferedReader br = new BufferedReader(reader1) // 建立一个对象，它把文件内容转成计算机能读懂的语言
		) {
			String line2;

			while ((line2 = br.readLine()) != null) {
				// 一次读入一行数据
				// System.out.println(line);
				str3 = line2; // 只能读入文档的一行内容
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// String str3 = "红旗导弹/n 射程/n 是多少/";
		// String str3 = "红旗导弹/n 和/ 东风导弹/n 比/ 哪个/ 射程/n 远/ ？/";
		// String str3 = "和/ 中国/n 有/ 合作关系/n 的/ 国家/n 有哪些/ ？/";
		// String str3 = "中国/n 设立/v 有/ 哪些/ 研究所/n ？/";
		// String str3 = "北理工/n 研发/n 了/ 哪些/ 型号/n 的/ 导弹/n ？";

		String regex1 = "[^| |]{0,10}[射程|长度|宽度][^| |]{0,10}[/][n||v]";// 后续需要增加属性
		String regex2 = "[^| |]{0,10}[和].{0,100}[比]";
		String regex3 = "[^| |]{0,10}[设立][^| |]{0,10}[/][n||v]";
		String regex4 = "[^| |]{0,10}[研发][^| |]{0,10}[/][n||v]";
		String regex5 = "[^| |]{0,10}[合作][^| |]{0,10}[/][n||v]";// 5类模板关键词的正则化语句，用于确定问题属于5类模板中的哪一类
		String regex6 = "[^| |]{0,10}[/][n||v]";// 用于筛选问题中的名词和动词（手工定义词性训练集后，可以筛选出国家、机构、具体型号以及关系等信息）

		Matcher m1 = Pattern.compile(regex1).matcher(str3);
		Matcher m2 = Pattern.compile(regex2).matcher(str3);
		Matcher m3 = Pattern.compile(regex3).matcher(str3);
		Matcher m4 = Pattern.compile(regex4).matcher(str3);
		Matcher m5 = Pattern.compile(regex5).matcher(str3);// 将问题与5类模板的正则化语句匹配，匹配成功则m.x=1
		Matcher m6 = Pattern.compile(regex6).matcher(str3);// 若问题中有n或v，m6=1

		while (m6.find()) {
			System.out.print(m6.group());
			s1 = s1 + m6.group(); // s1中存放问题中的实体和关系
			// FileWriter fw = null;
			// try {
			// fw = new FileWriter("C:\\zhongzhuan\\12345.txt",
			// true);//实体和关系的输出路径
			// fw.write(s1);//这里将提取到的实体、关系等关键词输出到上述路径的文本文档
			// fw.flush();
			// } catch (FileNotFoundException e) {
			// e.printStackTrace();
			// } catch (IOException e) {
			// e.printStackTrace();
			// } finally {
			// if (fw != null) {
			// try {
			// fw.close();
			// } catch (IOException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
		}
		FileWriter fw = null;
		try {
			fw = new FileWriter("E:/sources/Audio/Relation.txt", true);// 实体和关系的输出路径
			fw.write(s1);// 这里将提取到的实体、关系等关键词输出到上述路径的文本文档
			fw.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		while (m1.find()) {
			a = 1;
		}
		while (m2.find()) {
			a = 2;
		}
		while (m3.find()) {
			a = 3;
		}
		while (m4.find()) {
			a = 4;
		}
		while (m5.find()) {
			a = 5;
		} // 问题与第几类模板匹配，a就等于几

		switch (a) { // 根据a的值判断属于哪一类模板
		case 1:
			System.out.println("对应第一种模板");
			m = "对应第一种模板对应第一种模板";
			break;
		case 2:
			System.out.println("对应第二种模板");
			m = "对应第一种模板对应第二种模板";
			break;
		case 3:
			System.out.println("对应第三种模板");
			m = "对应第一种模板对应第三种模板";
			break;
		case 4:
			System.out.println("对应第四种模板");
			m = "对应第一种模板对应第四种模板";
			break;
		case 5:
			System.out.println("对应第五种模板");
			m = "对应第一种模板对应第五种模板";
			break;
		}
		System.out.println(m);
		// return s1;
		return m;

	}

}
