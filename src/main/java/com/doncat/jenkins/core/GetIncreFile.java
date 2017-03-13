package com.doncat.jenkins.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Logger;

import com.doncat.jenkins.utils.MoveFile;

public class GetIncreFile {
	private final static Logger logger = Logger.getLogger("GetIncreFile");
	/**
	 * ��ȡ�����ļ�
	 * @param increFile ���������ļ�list���ļ�
	 * @param localWorkspace list�ļ���Ŀ��·��
	 * @param increFilePath �����ļ��洢λ��
	 */
	public GetIncreFile(File increFile,String localWorkspace,String increFilePath){
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(increFile));
			String fileinfo;
			try {
				while((fileinfo = br.readLine())!=null){
					MoveFile.moveFile(fileinfo,localWorkspace,increFilePath);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("û������ļ�"+increFile.getName());
		}
	}
}
