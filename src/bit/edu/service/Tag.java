package bit.edu.service;

import java.sql.*;

public class Tag {
	//创建Connection和Statement对象
	//连接数据库和调用数据库,//以下六条指令均与SQL有关
		static Connection conn;
		static Statement stmt;
		static ResultSet rs;
		//定义连接字符
		static String dbURL = "jdbc:sqlserver://localhost:1433;DatabaseName=property";//DatebaseName写SQL数据库名
		static String userName = "sa";//SQL用户名
		static String userPwd = "1111";//SQL本地密码
				
		public static void connDB() {   //连接数据库方法
			try {
				//连接数据库
				//conn=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=教务系统","sa","123");
				conn=DriverManager.getConnection(dbURL,userName,userPwd);
				stmt=conn.createStatement();
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("连接失败！");
			}
		}	
		public void closeDB() {  //关闭数据库方法
			try {
				rs.close();
				stmt.close();
				conn.close();
			}catch(Exception e) {
				e.printStackTrace();
				//System.out.println("关闭失败！");
			}
		}
		
	public static String Tagging(String str) {   
		 
		//String str = "梅卡瓦主战坦克 和 印度阿琼主战坦克 的 宽度 是多少";
		//String str = "梅卡瓦主战坦克  哈哈哈 澳大利亚 啦啦啦 法国电力公司 巴拉巴拉 X-101/289空射亚声速战略巡航导弹   Theta CZ-1D  ";
	    String c="";
		String[] strs=str.split(" ");	
		for(String s : strs){
	    //System.out.println(s);
		c =c+ Find(s);	
		}	
		return c;
	}	
		
		
		public static String Find(String str) { 	
		
		String str1 ="" ;
		 String m ="" ;
		 String ppt=null; 
		connDB();
		try {

			rs=stmt.executeQuery("select 标注  from 标注  where 实体 ="+"'"+ str +"'");//查询数据库，标注，实体为列名

			if(rs.next()) {
			    ppt = rs.getString("标注");  
	    	//System.out.println(ppt);
	  //str=str.replace(str," "+str+"|"+ppt+" ");  //标注核心
	    	m=m+str+" ";
	    	//System.out.println(str);
			}
			//else  m=m+" "+str;
			
		}catch(Exception e){

			e.printStackTrace();

		}
		//System.out.print(m);
	 return m ;				
	}	
	

}
