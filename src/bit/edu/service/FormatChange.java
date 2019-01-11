package bit.edu.service;

import java.io.File;

public class FormatChange extends AudioToText{
	
	public String Format() {

		// 要改的文件夹路径

		String path = "E:/sources/Audio/test1.mp3";

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

		return path = "E:/sources/Audio/test1.pcm";

	}

}
