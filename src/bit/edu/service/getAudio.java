package bit.edu.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;

/**
 * 1.界面端发出录音请求，即点击麦克风，程序开始运行，采集问题音频 2.点击录音界面的提交按钮，结束程序，即录音结束，输出问题音频文件
 * 
 * @author me
 *
 */

public class getAudio {

	// 定义录音格式
	AudioFormat af = null;

	// 定义目标数据行,可以从中读取音频数据,该 TargetDataLine 接口提供从目标数据行的缓冲区读取所捕获数据的方法。
	TargetDataLine td = null;

	// 定义源数据行,源数据行是可以写入数据的数据行。它充当其混频器的源。应用程序将音频字节写入源数据行，这样可处理字节缓冲并将它们传递给混频器。
	SourceDataLine sd = null;

	// 定义字节数组输入输出流
	ByteArrayInputStream bais = null;
	ByteArrayOutputStream baos = null;

	// 定义音频输入流
	AudioInputStream ais = null;

	// 定义停止录音的标志，来控制录音线程的运行
	Boolean stopflag = false;
	
	private byte[] audioData;

	/**
	 * 点击麦克风按钮，开始录音
	 * 
	 * @return
	 */
	public void Record() {
		// TODO Auto-generated method stub

		try {
			// af为AudioFormat也就是音频格式
			af = getAudioFormat();
			DataLine.Info info = new DataLine.Info(TargetDataLine.class, af);
			td = (TargetDataLine) (AudioSystem.getLine(info));

			// 打开具有指定格式的行，这样可使行获得所有所需的系统资源并变得可操作。
			td.open(af);

			// 允许某一数据行执行数据 I/O
			td.start();

			// 创建播放录音的线程

			Record record = new Record();

			Thread t1 = new Thread(record);

			t1.start();

		} catch (LineUnavailableException ex) {

			ex.printStackTrace();

		}
		System.out.println("录音程序调用结束");

	}

	/**
	 * 设置AudioFormat音频参数
	 * 
	 * @return
	 */
	public AudioFormat getAudioFormat()

	{

		// 下面注释部分是另外一种音频格式，两者都可以
		AudioFormat.Encoding encoding = AudioFormat.Encoding.

				PCM_SIGNED;

		float rate = 8000f;
		int sampleSize = 16;
		String signedString = "signed";
		boolean bigEndian = true;
		int channels = 1;

		System.out.println("record和save程序运行前音频参数的设置");

		return new AudioFormat(encoding, rate, sampleSize, channels, (sampleSize / 8) * channels, rate, bigEndian);

	}

	/**
	 * 点击录音界面的提交按钮，StopRecord结束录音程序，Save保存录音文件，返回文件的物理地址fliePath
	 */

	public void StopRecord() {

		// 停止录音
		stopflag = true;
		System.out.println("录音停止程序调用结束");

	}

	// 保存录音
	public void Save() {
		
		// 取得录音输入流
		af = getAudioFormat();
		bais = new ByteArrayInputStream(audioData);

		ais = new AudioInputStream(bais, af, audioData.length / af.getFrameSize());

		// 定义最终保存的文件名
		File file = null;

		// 写入文件
		try {

			// 以当前的时间命名录音的名字
			// 将录音的文件存放到F盘下语音文件夹下
			File filePath = new File("E:/sources/Audio");

			if (!filePath.exists()) {

				// 如果文件不存在，则创建该目录
				filePath.mkdir();
			}

			// System.out.println("222222");
			file = new File(filePath.getPath() + "/" + "test1" + ".mp3");
			AudioSystem.write(ais, AudioFileFormat.Type.WAVE, file);

		} catch (Exception e) {
			e.printStackTrace();

		} finally {

			// 关闭流
			try {
				if (bais != null) {

					bais.close();
				}

				if (ais != null) {

					ais.close();
				}

			} catch (Exception e) {

				e.printStackTrace();

			}
		}
		System.out.println("录音文件保存程序调用结束");
//		String filePath = "E:/sources/Audio/test1.mp3";
//		return filePath;

	}

	// 录音类，因为要用到MyRecord类中的变量，所以将其做成内部类
	class Record implements Runnable {

		// 定义存放录音的字节数组,作为缓冲区
		byte bts[] = new byte[10000];

		// 将字节数组包装到流里，最终存入到baos中
		// 重写run函数
		public void run() {

			baos = new ByteArrayOutputStream();
			try {

				stopflag = false;
				while (stopflag != true) {

					// 当停止录音没按下时，该线程一直执行
					// 从数据行的输入缓冲区读取音频数据。
					// 要读取bts.length长度的字节,cnt 是实际读取的字节数
					int cnt = td.read(bts, 0, bts.length);
					if (cnt > 0) {
						baos.write(bts, 0, cnt);
					}
					getAudio.this.audioData = baos.toByteArray();
				}
				

			} catch (Exception e) {

				e.printStackTrace();

			} finally {

				try {
					// 关闭打开的字节数组流
					if (baos != null) {

						baos.close();

					}

				} catch (IOException e) {

					e.printStackTrace();

				} finally {

					td.drain();
					td.close();
				}
			}

			System.out.println("录音程序用到的内部类");
		}

	}

//	// 播放录音
//	public void play(){
//
//		// 将baos中的数据转换为字节数据
//		audioData = baos.toByteArray();
//
//		// 转换为输入流
//		bais = new ByteArrayInputStream(audioData);
//		af = getAudioFormat();
//		ais = new AudioInputStream(bais, af, audioData.length / af.getFrameSize());
//
//		try {
//
//			DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, af);
//
//			sd = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
//			sd.open(af);
//			sd.start();
//
//			// 创建播放进程
//
//			Play py = new Play();
//
//			Thread t2 = new Thread(py);
//
//			t2.start();
//
//		} catch (Exception e) {
//
//			e.printStackTrace();
//
//		} finally {
//
//			try {
//
//				// 关闭流
//
//				if (ais != null){
//
//					ais.close();
//				}
//
//				if (bais != null){
//
//					bais.close();
//
//				}
//
//				if (baos != null){
//
//					baos.close();
//
//				}
//
//			} catch (Exception e) {
//
//				e.printStackTrace();
//			}
//		}
//	}
//	
//	//播放类,同样也做成内部类
//
//		class Play implements Runnable{
//
//			//播放baos中的数据即可
//
//			public void run() {
//
//				byte bts[] = new byte[10000];
//
//				try {
//
//					int cnt;
//
//		            //读取数据到缓存数据
//
//		            while ((cnt = ais.read(bts, 0, bts.length)) != -1) 
//
//		            {
//
//		                if (cnt > 0){
//		                	
//		                    //写入缓存数据
//		                    //将音频数据写入到混频器
//		                    sd.write(bts, 0, cnt);
//
//		                }
//
//		            }
//
//		           
//
//				} catch (Exception e) {
//
//					e.printStackTrace();
//
//				}finally{
//
//					 sd.drain();
//			         sd.close();
//
//				}
//			}		
//		}
	

}
