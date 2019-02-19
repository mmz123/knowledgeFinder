package bit.edu.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.Enumeration;
import java.util.Hashtable;

import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

/**
 * 1.对AudioToText输出的问题文本或输入的问题文本分词处理 2.返回分词处理后的文本normalText（输入到文本框2内）
 * 
 * @author me
 *
 */

public class TextProcess {
	public static String normalText;
	
	// 对AudioToText输出的有效文本true.txt分词
	public static String Segment() {
		//String tager;
		String text = "";// 分词前的字符串（现在只读一行）
		String pathname1 = "D:/sources/Audio/true.txt";// 读入提取的有效文本文件路径
		String text2 = "";
		try (FileReader reader = new FileReader(pathname1);
				// 建立一个对象，它把文件内容转成计算机能读懂的语言
				BufferedReader br = new BufferedReader(reader)) {
			String line1;

			while ((line1 = br.readLine()) != null) {
				text = line1; // 只能读入文档的一行内容
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		StringReader re = new StringReader(text);
		IKSegmenter ik = new IKSegmenter(re, true);
		Lexeme lex = null;

		try {
			normalText = "";
			while ((lex = ik.next()) != null) {

				System.out.print(lex.getLexemeText() + "/ ");
				
				normalText += lex.getLexemeText() +"/ ";
			}

			FileWriter fw = null;
			try {
				// 将分词结果写入文件segmentationResults.txt
				fw = new FileWriter("D:/sources/Audio/segmentationResults.txt", false);

				// 这里向文件中输入结果
				fw.write(normalText);
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
		} catch (Exception e) {

		}

		try (
				// 建立一个对象，它把文件内容转成计算机能读懂的语言
				FileReader reader = new FileReader(pathname1); BufferedReader br = new BufferedReader(reader)) {
			String line3;
			while ((line3 = br.readLine()) != null) {
				text2 = line3; // 只能读入文档的一行内容
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		//PosTag();
		
		
		return normalText;
	}

	// 对Segment方法分词后的结果进行标注
//	public static String PosTag() {
		
//		// 统计出训练样本中词性种类及其频率
//		String content = "";
//		BufferedReader reader = null;
//		try { 
//			// 读取train.txt（训练集）文本中的内容，并保存在content的字符流中
//			reader = new BufferedReader(new FileReader("E:/Programme/Project/KG/model/train.txt"));
//			String line2;
//			while ((line2 = reader.readLine()) != null)
//				content += line2;
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			if (reader != null) {
//				try {
//					reader.close();
//				} catch (IOException e) {
//					
//				}
//			}
//		}
//
//		String[] text1; // text[]用于存储训练样本中的词语
//		text1 = content.split("(/[a-z]*\\s{0,})|(][a-z]*\\s{1,})"); // 去除词性标注
//		// for(String wd:text)
//		// System.out.println(wd);
//
//		String[] temp; // temp[]数组用于存储单个词的词性标注符号
//		temp = content.split("[0-9|-]*/|\\s{1,}[^a-z]*|][a-z]"); // 仅保留词性标注符号。
//		String[] temp1;
//		temp1 = new String[temp.length - 1];// 去除temp[0]为空的情况
//		for (int i = 0; i < temp.length - 1; i++)
//			temp1[i] = temp[i + 1];
//		// for(String wd:temp1)
//		// System.out.print(wd+" ");
//
//		String[] temp2; // temp2[]数组用于存储每两个词的词性标注符号
//		temp2 = new String[temp1.length - 1];
//		for (int i = 0; i < temp1.length - 1; i++)
//			temp2[i] = temp1[i] + ',' + temp1[i + 1];
//		// for(String wd:temp2)
//		// System.out.println(wd);
//
//		String[] word_pos;
//		word_pos = new String[text1.length];
//		for (int i = 0; i < text1.length; i++)
//			word_pos[i] = text1[i] + ',' + temp1[i];
//		// for(String wd:word_pos)
//		// System.out.println(wd);
//
//		Hashtable hash1 = new Hashtable(); // 创建hash1，存储单个词的词性及其频率
//		for (String wd : temp1) {
//			if (hash1.containsKey(wd))
//				hash1.put(wd, hash1.get(wd).hashCode() + 1);
//			else
//				hash1.put(wd, 1);
//		}
//		int sp = hash1.size(); // 统计词性个数
//		// System.out.println(hash1);
//
//		Hashtable hash2 = new Hashtable(); // 创建hash2，存储每两个词的词性及其频率
//		for (String wd : temp2) {
//			if (hash2.containsKey(wd))
//				hash2.put(wd, hash2.get(wd).hashCode() + 1);
//			else
//				hash2.put(wd, 1);
//		}
//		// System.out.println(hash2);
//
//		Hashtable hash3 = new Hashtable(); // 创建hash3,存储词语、词性和词频
//		for (String wd : word_pos) {
//			if (hash3.containsKey(wd))
//				hash3.put(wd, hash3.get(wd).hashCode() + 1);
//			else
//				hash3.put(wd, 1);
//		}
//		// System.out.println(hash3);
//
//		String[] table_pos; // table_pos[]用于存储所有不同的词性符号
//		table_pos = new String[sp];
//		Enumeration key = hash1.keys();
//		for (int i = 0; i < sp; i++) {
//			String str2 = (String) key.nextElement();
//			table_pos[i] = str2;
//		}
//		// for(String wd:table_pos)
//		// System.out.println(wd);
//
//		// --------------------------------------------------------------------------------------
//		// 计算状态转移概率
//		double[][] status; // status[i][j]用于存储转移概率,表示由状态j转移到状态i的概率。
//		status = new double[sp][sp];
//		for (int i = 0; i < sp; i++) // 初始化
//		{
//			for (int j = 0; j < sp; j++)
//				status[i][j] = 0;
//		}
//
//		for (int i = 0; i < sp; i++) {
//			for (int j = 0; j < sp; j++) {
//				String wd = table_pos[j];
//				String str2 = wd + ',' + table_pos[i];
//				if (hash2.containsKey(str2))
//					status[i][j] = Math
//							.log(((double) hash2.get(str2).hashCode() / (double) hash1.get(wd).hashCode()) * 100000000);
//				else
//					status[i][j] = Math.log((1 / ((double) hash1.get(wd).hashCode() * 1000)) * 100000000);
//			}
//		}
//		/*
//		 * for(int i=0;i<sp;i++) { System.out.println('\n'); for(int
//		 * j=0;j<sp;j++) System.out.print(status[j][0]+"  "); }
//		 */
//
//		// -----------------------------------------------------------------------------------------
//		// 计算发射概率
//		String sentence = "";
//		try { // 读取test.txt文本中的内容，并保存在sentence的字符流中。
//			BufferedReader str2 = new BufferedReader(new FileReader("D:/sources/Audio/segmentationResults.txt"));// 读入需要进行词性标注的文本
//			String line;
//			while ((line = str2.readLine()) != null)
//				sentence += line;
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		String[] test;
//		test = sentence.split("(/[a-z]*\\s{0,})|(][a-z]*\\s{1,})");
//		int sw = 0; // 记录test.txt中词语的总数
//		sw = test.length;
//		// for(String wd:test)
//		// System.out.println(wd);
//		// System.out.print(sw);
//
//		double[][] observe; // observe[i][j]表示在词性状态Sj下，输出词语Oi的概率。
//		observe = new double[sw][sp];
//		for (int i = 0; i < sw; i++) // 初始化
//		{
//			for (int j = 0; j < sp; j++)
//				observe[i][j] = 0;
//		}
//
//		for (int i = 0; i < sw; i++) {
//			for (int j = 0; j < sp; j++) {
//				String wd = test[i];
//				String ws = table_pos[j];
//				String str2 = wd + ',' + ws;
//				if (hash3.containsKey(str2))
//					observe[i][j] = Math
//							.log(((double) hash3.get(str2).hashCode() / (double) hash1.get(ws).hashCode()) * 100000000);
//				else
//					observe[i][j] = Math.log((1 / ((double) hash1.get(ws).hashCode() * 1000)) * 100000000);
//			}
//		}
//		/*
//		 * for(int i=0;i<sw;i++) { for(int j=0;j<sp;j++)
//		 * System.out.println(observe[j][0]); }
//		 */
//
//		// -----------------------------------------------------------------------------------------------
//		// Viterbi算法，进行词性标注。找出the best path
//		double[][] path; // path[][]存储单个词语的最大概率
//		path = new double[sw][sp];
//		for (int i = 0; i < sw; i++) {
//			for (int j = 0; j < sp; j++)
//				path[i][j] = 0.0;
//		}
//
//		int[][] backpointer; // backpointer[][]记录单个词中每个词性取得最大概率时所对应的前一个词性的位置
//		backpointer = new int[sw][sp];
//		for (int i = 0; i < sw; i++) {
//			for (int j = 0; j < sp; j++)
//				backpointer[i][j] = 0;
//		}
//
//		for (int j = 0; j < sp; j++) // 对test[]中的第一个词，初始化在每个词性下产生该词的概率。
//		{
//			path[0][j] = Math.log(((double) hash1.get(table_pos[j]).hashCode() / (double) temp1.length) * 100000000)
//					+ observe[0][j];
//		}
//		// for(int s=0;s<sp;s++)
//		// System.out.println(path[0][s]);
//		for (int i = 1; i < sw; i++) // 对test[]中剩下的词，依次计算单个词性对应的最大概率并记录其位置
//		{
//			for (int j = 0; j < sp; j++) {
//				double maxp = path[i - 1][0] + status[j][0] + observe[i][j];
//				int index = 0;
//				for (int k = 1; k < sp; k++) {
//					path[i][j] = path[i - 1][k] + status[j][k] + observe[i][j];
//					if (path[i][j] > maxp) {
//						index = k;
//						maxp = path[i][j];
//					}
//				}
//				backpointer[i][j] = index;
//				path[i][j] = maxp;
//			}
//		}
//		/*
//		 * for(int i=0;i<sw;i++) for(int j=0;j<sp;j++)
//		 * System.out.println(backpointer[sw-2][j]);
//		 */
//
//		// 回溯遍历，找出概率最大的路径,输出结果
//		int maxindex = 0; // 记录测试文本中最后一个词取得最大概率的位置。
//		double max = path[sw - 1][0];
//		for (int i = 1; i < sp; i++) {
//			if (path[sw - 1][i] > max) {
//				maxindex = i;
//				max = path[sw - 1][maxindex];
//			}
//		}
//		// System.out.println(max);
//
//		String[] result; // 存储词性标注结果
//		String[] object; // 存储结果集中的所有词性，用于计算精确度
//		result = new String[sw];
//		object = new String[sw];
//		result[sw - 1] = test[sw - 1] + '/' + table_pos[maxindex];
//		object[sw - 1] = table_pos[maxindex];
//		int t = 0;
//		int front = maxindex;
//		for (int i = sw - 2; i >= 0; i--) {
//			t = backpointer[i + 1][front];
//			result[i] = test[i] + '/' + table_pos[t] + "  ";
//			object[i] = table_pos[t];
//			front = t;
//		}
//
//		try {
//			FileWriter f = new FileWriter("D:/sources/Audio/PosTag.txt");// 完成词性标注后的文本的存放路径
//			for (int i = 0; i < result.length; i++){
//				f.write(result[i] + "");
//				normalText = normalText + result[i] + "";
//			}
//			f.flush();
//			f.close();
//		} catch (IOException e) {
//			System.out.println("错误");
//		}
//		
////		//文本框2的内容有词性标注的结果和匹配的结果
////		Match match = new Match();
////		Type = match.Matching();
////		normalText = normalText + Type;
//		return normalText;
//	}

}
