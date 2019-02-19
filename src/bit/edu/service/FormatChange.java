package bit.edu.service;

import java.io.File;

/**
 * 读取getAudio生成的录音文件，转成pcm格式
 * @author asus
 *
 */
public class FormatChange extends AudioToText{
	
	public String Format(String path) {

		// 要改的文件夹路径
//		String path = "D:/sources/Audio/test1.mp3";
		File file = new File(path);
		
		// 得到文件夹下的所有文件和文件夹
		String[] list = file.list();

		if (list != null && list.length > 0) {
			for (String oldName : list) {
				File oldFile = new File(path, oldName);
				
				// 判断出文件和文件夹
				if (!oldFile.isDirectory()) {

					// 文件则判断是不是要修改的
					if (oldName.contains(".mp3")) {
						
						System.out.println(oldName);
						
						String newoldName = oldName.substring(0, oldName.lastIndexOf(".")) + ".pcm";
						
						System.out.println(newoldName);
						
						File newFile = new File(path, newoldName);
						boolean flag = oldFile.renameTo(newFile);
						
						System.out.println(flag);
					}

				}
				// else{
				//
				// //文件夹则迭代
				// String newpath=path+"/"+oldName;
				// Format(newpath);
				// }

			}
		}

		System.out.println("MP3转pcm程序调用结束");
		//return path = "D:/sources/Audio/test1.pcm";
		return path;

	}

}
