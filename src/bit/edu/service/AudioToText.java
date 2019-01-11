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
	private static String questionText = new String();

	// put your own params here
	// 下面3个值要填写自己申请的app对应的值
	String apiKey = "QQEePejDr9zsRztnM9QhK1L4";
	String secretKey = "SYV73NWGY5dEjhuWz7msooPdL5204qx5";
	static String cuid = "70-4D-7B-3F-61-02";

	// 读取录音文件,调用百度接口识别，识别音频文件，返回问题文本
	
	public String questionText() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String questionText(String path) {

		testFileName = path;

		questionText = "pp";

		return questionText;
	}

	// 链接百度语音识别接口
	public void getToken() throws Exception {
		String getTokenURL = "https://openapi.baidu.com/oauth/2.0/token?grant_type=client_credentials" + "&client_id="
				+ apiKey + "&client_secret=" + secretKey;

		HttpURLConnection conn = (HttpURLConnection) new URL(getTokenURL).openConnection();
		token = new JSONObject(printResponse(conn)).getString("access_token");
	}

	// 语音识别的方法
	private static void method() throws Exception {

		File pcmFile = new File(testFileName);
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
		wr.flush();
		wr.close();
		System.out.println(getUtf8String(printResponse(conn)));
	}

	public static String printResponse(HttpURLConnection conn) throws Exception {

		if (conn.getResponseCode() != 200) {

			// request error
			System.out.println("conn.getResponseCode() = " + conn.getResponseCode());
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
			fw = new FileWriter("C:\\zhongzhuan\\识别结果.txt", true);
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
		while (offset < bytes.length
				&& (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
			offset += numRead;

		}

		if (offset < bytes.length) {
			is.close();
			throw new IOException("Could not completely read file " + file.getName());

		}
		is.close();
		return bytes;

	}

	// GBK编码转为UTF-8

	private static String getUtf8String(String s) throws UnsupportedEncodingException{

		StringBuffer sb = new StringBuffer();
		sb.append(s);
		String xmlString = "";
		String xmlUtf8 = "";
		xmlString = new String(sb.toString().getBytes("GBK"));
		xmlUtf8 = URLEncoder.encode(xmlString, "GBK");
		
		return URLDecoder.decode(xmlUtf8, "UTF-8");

	}



}
