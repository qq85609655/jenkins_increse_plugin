package com.unicompayment.fip.common.utils.text;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileOperation {

	/**
	 * 写文件
	 * 
	 * @param path
	 *            文件的路径
	 * @param content
	 *            写入文件的内容
	 * @param fileName
	 * 			  文件的名字
	 */
	public static boolean writerText(String path, String content, String fileName) {

		File dirFile = new File(path);

		if (!dirFile.exists()) {
			dirFile.mkdir();
		}

		try {
			//new FileWriter(path + fileName, true);  这里加入true 可以不覆盖原有TXT文件内容 续写
			BufferedWriter bw1 = new BufferedWriter(new FileWriter(path
					+ fileName, true));
			bw1.write(content);
			bw1.flush();
			bw1.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}
