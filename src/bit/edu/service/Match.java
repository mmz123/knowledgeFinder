package bit.edu.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 1.接收textProcess的输出，判断问题所属模板，用整数型变量a的值区分
 * 2.对于第一类模板和第二类模板，能够确定提问的装备类型和属性
 * 
 * @author me
 *
 */

public class Match {
		
//		//以下六条指令均与SQL有关
//		static Connection conn;
//		static Statement stmt;
//		static ResultSet rs;
//		//定义连接字符
//		static String dbURL = "jdbc:sqlserver://10.101.15.216:1433;DatabaseName=property";//DatebaseName写SQL数据库名
//		static String userName = "sa";//SQL用户名
//		static String userPwd = "sqlserver";//SQL本地密码
//				
//		public static void connDB() {   //连接数据库方法
//			try {
//				//连接数据库
//				//conn=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=教务系统","sa","123");
//				conn=DriverManager.getConnection(dbURL,userName,userPwd);
//				stmt=conn.createStatement();
//			}catch(Exception e) {
//				e.printStackTrace();
//				System.out.println("连接失败！");
//			}
//		}	
//		public void closeDB() {  //关闭数据库方法
//			try {
//				rs.close();
//				stmt.close();
//				conn.close();
//			}catch(Exception e) {
//				e.printStackTrace();
//				//System.out.println("关闭失败！");
//			}
//		}
//		
//		//判断第一、二类模板的装备类型
//		public static String Matching(String str ){
//		int a = 0;
//		String m = "";
//		String c = "";
//		String t = "";
//		
//		//String str = "AK47步枪 的 装  是多少";
//		String[] strs=str.split(" ");
//			
//		String regex1 = "[^| |]{0,30}(导弹|枪|机床|火箭|超级计算机|坦克).*(的|能).*(是|是多少|多).*";
//		String regex11 = "[^| |]{0,30}(导弹).*";	
//		String regex12 = "[^| |]{0,30}(枪).*";
//		String regex13 = "[^| |]{0,30}(机床).*";
//		String regex14 = "[^| |]{0,30}(火箭).*";
//		String regex15 = "[^| |]{0,30}(超级计算机).*";
//		String regex16 = "[^| |]{0,30}(坦克).*";
//		String regex2x1 = ".*(和|与|跟).*(比|相比|相比较).*";
//		String regex2x2 = "[^| |]{0,10}.*(在|.).{0,100}(更).*";
//		
//		boolean flag1 = str.matches(regex1);
//	    boolean flag2 = str.matches(regex2x1)||str.matches(regex2x2);
//	    
//	    if(flag1)
//	    {
//	    m="1 ";
//	    boolean flag11 = str.matches(regex11);
//	    boolean flag12 = str.matches(regex12);
//	    boolean flag13 = str.matches(regex13);
//	    boolean flag14 = str.matches(regex14);
//	    boolean flag15 = str.matches(regex15);
//	    boolean flag16 = str.matches(regex16);
//	    	if(flag11) 
//	    		a=1;
//	    	else if(flag12)
//	    		a=2;
//	    	else if(flag13)
//	    		a=3;
//	    	else if(flag14)
//	    		a=4;
//	    	else if(flag15)
//	    		a=5;	
//	    	else if(flag16)
//	    		a=6;	
//	    	for(String s : strs){
//				 c=search(s,a);
//				 t=t+c;//t中存放标准属性
//			}
//	    }
//	    		    		
//	    if(flag2)
//	    { m="2 ";
//	    boolean flag11 = str.matches(regex11);
//	    boolean flag12 = str.matches(regex12);
//	    boolean flag13 = str.matches(regex13);
//	    boolean flag14 = str.matches(regex14);
//	    boolean flag15 = str.matches(regex15);
//	    boolean flag16 = str.matches(regex16);
//	    	if(flag11) 
//	    		a=1;
//	    	else if(flag12)
//	    		a=2;
//	    	else if(flag13)
//	    		a=3;
//	    	else if(flag14)
//	    		a=4;
//	    	else if(flag15)
//	    		a=5;	
//	    	else if(flag16)
//	    		a=6;	
//	    	for(String s : strs){
//				 c=search(s,a);
//				 t=t+c;//t中存放标准属性
//			}
//	    
//	    
//	    }
//	    
//	    
//	return t;
//	}
//
//	    public static String search(String str,int a) {   //查询属性方法
//		   
//		 String m = "";
//		 String xhj=null;
//		 String ppt="";
//		 switch(a){
//		 case 1 :xhj="导弹属性集"; break;
//		 case 2 :xhj="枪属性集"; break;
//		 case 3 :xhj="机床属性集"; break;
//		 case 4 :xhj="火箭属性集"; break;
//		 case 5 :xhj="超级计算机属性集"; break;
//		 case 6 :xhj="坦克属性集"; break;
//		 }
//		 
//		connDB();//连接数据库
//
//		try {
//
//			rs=stmt.executeQuery("select 属性  from "+ xhj +" where 属性 ="+"'"+str+"'"+
//			" or 替换词1 ="+"'"+str+"'"+" or 替换词2="+"'"+str+"'"+" or 替换词3="+"'"+str+"'"+
//			" or 替换词4="+"'"+str+"'" +" or 替换词5="+"'"+str+"'"+" or 替换词6="+"'"+str+"'");//数据库查询，属性，替换词为列名
//
//			if(rs.next()) {
//			ppt = rs.getString("属性");  
//	    	//System.out.println(ppt);
//			m=m+ppt;
//			}
//		//else m=m+str;
//			//System.out.print(m);
//		}catch(Exception e){
//
//			e.printStackTrace();
//
//		}
//	 return m;				
//	}				
//
//	    public static int model(String str){
//	    int a=0;
//	    String regex1 = "[^| |]{0,30}(导弹|枪|机床|火箭|超级计算机|坦克).*(的|能).*(是|是多少|多).*";
//	    String regex2x1 = ".*(和|与|跟).*(比|相比|相比较).*";
//		String regex2x2 = "[^| |]{0,10}.*(在|.).{0,100}(更).*";
//		String regex3 = "[^| |]{0,10}(设立|设立|建立|设|建|成立)[| |]";
//		String regex3x1 = "[^| |]{0,10}(为研发|.).*(设立|而设立|建立|设|建|成立).*";
//		//String regex3x2 = "[^| |](为研发|为).*(设立).*";
//		String regex3x3 = "[^| |]{0,20}[| |].*(有哪几个|哪些|哪几个).*(是研究|研究|研发).*";
//		String regex3x4 = "[^| |].*[| |](是哪个).*[^| |].*[| |]";
//		//String regex4 = "[^| |]{0,10}.*(研发|研制|开发|设计|发了).*";
//		String regex4x1 = "[^| |]{0,20}[| |](是哪个).*(研发|研制|开发|设计).*";
//		String regex4x2 = "[^| |]{0,20}.*[| |](研发的|研发|发了).{0,100}(有哪些|哪些).*(型号).*";
//		String regex5 = "[^| |]{0,10}(合作|协作)[^| |]{0,10}.*";
//		String regex5x1 = "(和|与).*[^| |]{0,20}[| |].*(合作).*(哪些|哪个).*";
//		String regex5x2 = "[^| |]{0,20}[| |].*(与|和).{0,100}(是否).*(合作).*";//5类模板关键词的正则化语句，用于确定问题属于5类模板中的哪一类
//		//String regex6 = "[^| |]{0,10}(n|v)";//用于筛选问题中的有效词（手工定义词性训练集后，可以筛选出国家、机构、具体型号以及关系等信息）		
//		boolean flag1 = str.matches(regex1);
//	    boolean flag2 = str.matches(regex2x1)||str.matches(regex2x2);
//	    boolean flag3 = str.matches(regex3)|| str.matches(regex3x1)||str.matches(regex3x3)||str.matches(regex3x4);
//	    //boolean flag3 = str.matches(regex3)|| str.matches(regex3x1)||str.matches(regex3x3)||str.matches(regex3x4)||str.matches(regex3x2);
//	    boolean flag4 = str.matches(regex4x1)||str.matches(regex4x2);
//	   // boolean flag4 = str.matches(regex4)|| str.matches(regex4x1)||str.matches(regex4x2);
//	    boolean flag5 = str.matches(regex5)|| str.matches(regex5x1)||str.matches(regex5x2);
//	    if(flag1)
//	        a=1;  
//		if(flag2)
//	        a=2;  
//		if(flag3)
//	        a=3;    
//	    if(flag4)
//	        a=4;  
//		if(flag5)
//	        a=5;  
//		//if(flag6)
//	       // a=6;  
//	    return a;
//	    }
	    
		  


	
	
	
	
	
	
	
	
	
	

//	public String normalText() {
//		String finalText;
//
//		finalText = "88";
//		return finalText;
//	}

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
