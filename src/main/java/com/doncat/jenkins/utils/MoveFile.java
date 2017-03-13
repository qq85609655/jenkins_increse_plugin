package com.doncat.jenkins.utils;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;

public class MoveFile {
	private final static Logger logger = Logger.getLogger("MoveFile");
	public static void moveFile(String sourceFileStr,String localWorkspace,String toPath){
//		sourceFileStr = sourceFileStr.replaceAll("\\\\", "/").trim().replaceAll(".java", ".class");
//		String desPath = pathDes + qualifiedPath;
//		String sourcePath = pathSource + qualifiedPath;
//		logger.info("sourceFileStr"+sourceFileStr);
//		logger.info("localWorkspace"+localWorkspace);
		String targetFilePath = sourceFileStr.replace(localWorkspace, "");
		//D:\checkout\ȫ����\branches\qlcx-scwt\HRBCMS\target\increaseFile/incre/D:/checkout/ȫ����/branches/qlcx-scwt/HRBCMS/target/HRBCMS/CreditManage/CreditPutOut/EntDealContractList.jsp
		targetFilePath = toPath + "/incre/" + targetFilePath;
//		logger.info("׼���ƶ��ļ�"+sourceFileStr+"��"+targetFilePath);
//		toPath=toPath.replaceAll("\\\\", "/").trim().replaceAll(".java", ".class");
//		String desPath = sourceFileStr.replace(toPath,toPath+"/incre/");
//		logger.info(qualifiedPath+"���滻��"+desPath);
//		String sourcePath =sourceFileStr;
		try {
			String desPath = targetFilePath.substring(0,targetFilePath.lastIndexOf("/"));
			File desFolder = new File(desPath);
			if(!desFolder.exists()){
				desFolder.mkdirs();
			}
			FileUtils.copyFile(new File(sourceFileStr), new File(targetFilePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		int a = 0;
//		if(desPath.lastIndexOf("/")!=-1){
//			a = desPath.lastIndexOf("/");
//		}
//		
//		String initPath = desPath.substring(0, a);
//		
//		File sourceFile = new File(sourcePath);
//		File desFile = new File(initPath);
//		desFile.mkdirs();
//		if(sourceFile.exists()==true){
////			System.out.println(sourceFile.getName());
////			sourceFile.renameTo(new File(desPath));
//			try {
//				FileUtils.copyFile(sourceFile, new File(desPath));
////				FileUtils.
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		else{
//			System.out.println(qualifiedPath+"-------do not exists!");
//		}
	}
}
