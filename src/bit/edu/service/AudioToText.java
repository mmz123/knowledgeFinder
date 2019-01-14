package bit.edu.service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import javax.xml.bind.DatatypeConverter;

import org.json.JSONObject;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 1.读取getAudio生成的录音文件，转成pcm格式 2.调用百度语音识别接口 3.输出音频识别文本
 * 
 * @author me
 *
 */
public class AudioToText {

	private static String serverURL = "http://vop.baidu.com/server_api";
	private static String token = "";
	private static String testFileName = null; // 百度语音提供技术支持
	private static String RecognitionResult = new String();

	// put your own params here
	// 下面3个值要填写自己申请的app对应的值
	String apiKey = "QQEePejDr9zsRztnM9QhK1L4";
	String secretKey = "SYV73NWGY5dEjhuWz7msooPdL5204qx5";
	static String cuid = "70-4D-7B-3F-61-02";

	// 读取转换格式后的录音文件,调用百度接口识别，识别音频文件，生成的是一个txt文件，txt文件中提取问题文本

	public String questionText() throws Exception {

		getToken();
		// method1();
		FormatChange formatChange = new FormatChange();

		method(formatChange.Format("E:/sources/Audio/test1.mp3"));
		System.out.println("语音识别的主要程序");
		
		RecognitionResult = Extract("E:/sources/Audio/test1.txt");
		
		return RecognitionResult;
	}

	// 链接百度语音识别接口
	public void getToken() throws Exception {
		String getTokenURL = "https://openapi.baidu.com/oauth/2.0/token?grant_type=client_credentials" + "&client_id="
				+ apiKey + "&client_secret=" + secretKey;

		HttpURLConnection conn = (HttpURLConnection) new URL(getTokenURL).openConnection();
		token = new JSONObject(printResponse(conn)).getString("access_token");
		System.out.println("语音识别程序——连接百度接口");
	}

	// 语音识别的方法
	private static String method(String path) throws Exception {

		String s = null;
		File pcmFile = new File(path);
		HttpURLConnection conn = (HttpURLConnection) new URL(serverURL + "?cuid=" + cuid + "&token=" + token)
				.openConnection();

		// add request header
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "audio/pcm; rate=8000");

		conn.setDoInput(true);
		conn.setDoOutput(true);

		// send request
		DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
		wr.write(loadFile(pcmFile));
		wr.close();
		wr.flush();

		// 注释掉下面那句话减少很多报错，但是文本框1会出现别的东西
		//System.out.println(getUtf8String(printResponse(conn)));

		s = getUtf8String(printResponse(conn));

		System.out.println("语音识别方法调用结束");
		return s;

	}

	public static String printResponse(HttpURLConnection conn) throws Exception {

		if (conn.getResponseCode() != 200) {

			// request error不输出错误时注释调下面那句话
			// System.out.println("conn.getResponseCode() = " +
			// conn.getResponseCode());
			return "";

		}

		InputStream is = conn.getInputStream();
		BufferedReader rd = new BufferedReader(new InputStreamReader(is));
		String line;
		StringBuffer response = new StringBuffer();
		while ((line = rd.readLine()) != null) {

			response.append(line);
			response.append('\r');

		}

		rd.close();
		System.out.println(new JSONObject(response.toString()).toString(4));

		FileWriter fw = null;
		try {
			fw = new FileWriter("E:/sources/Audio/test1.txt", true);
			fw.write(new JSONObject(response.toString()).toString(1));// 这里向文件中输入结果123
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

		return response.toString();

	}

	private static byte[] loadFile(File file) throws IOException {

		InputStream is = new FileInputStream(file);
		long length = file.length();
		byte[] bytes = new byte[(int) length];
		int offset = 0;
		int numRead = 0;
		while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
			offset += numRead;

		}
		if (offset < bytes.length) {
			is.close();
			throw new IOException("Could not completely read file " + file.getName());
		}
		is.close();
		System.out.println("loadFile程序调用结束");
		return bytes;

	}

	// GBK编码转为UTF-8

	private static String getUtf8String(String s) throws UnsupportedEncodingException {

		StringBuffer sb = new StringBuffer();
		sb.append(s);
		String xmlString = "";
		String xmlUtf8 = "";
		xmlString = new String(sb.toString().getBytes("GBK"));
		xmlUtf8 = URLEncoder.encode(xmlString, "GBK");

		System.out.println("GBK转UTF-8调用结束");
		return URLDecoder.decode(xmlUtf8, "UTF-8");

	}

	// 以下为提取百度返回的有效信息
	public static String Extract(String RecognitionFilePath) {
		// ------------------------------------------------------------------------------------------------------------------
		// 以下为提取百度返回结果中的有效信息
		String str = "";// 存放从百度语音api返回的一行信息
		String str1 = "";// 存放从百度语音api返回的全部信息
		String questionText = "";// 存放从str1中提取的有效信息，即完成语音-文字转换后的问题
		//String filePath = "c:\\zhongzhuan\\test1.txt";// 提取百度api返回结果中有效信息的程序读文件路径

		try (FileReader reader = new FileReader(RecognitionFilePath); BufferedReader br = new BufferedReader(reader) // 建立一个对象，它把文件内容转成计算机能读懂的语言
		) {
			String line;

			while ((line = br.readLine()) != null) {
				str = line; // 只能读入文档的一行内容
				str1 = str1 + str;// 这样就能识别多行了
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		String regex = "[\\[].{0,100}[\\]]";// 从百度返回的一大串信息中找到"[....]"的内容，得到的结果就是语音识别后的问题
		Matcher m = Pattern.compile(regex).matcher(str1);
		while (m.find()) {
			System.out.println(m.group());
			questionText = m.group();

			FileWriter fw = null;
			try {
				//true.txt为提取出的有效文本
				fw = new FileWriter("E:/sources/Audio/true.txt", true);
				fw.write(questionText);// 将
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
		}

		System.out.println("从百度语音识别结果中提取有效信息");
		System.out.println("questionText = " + questionText);
		return questionText;
	}

}
