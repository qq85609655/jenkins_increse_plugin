package com.doncat.jenkins.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;
/**
 * 
 * @author liujingran
 * @category ��svn diff�������ļ��嵥�е��ļ�·���������������жϣ��ı�ɹ��̱�����ȫ�������ļ����ļ�ϵͳ�е�·���������һ�������б��ȡ�ļ���
 *
 */
public class SCMPath2LocalPathUtil {
	private final static Logger logger = Logger.getLogger("SCMPath2LocalPath");
	
	ArrayList<String> RecursionPath = new ArrayList<String>();
	static ArrayList<String> CorrectList = new ArrayList<String>();
	/**
	 * svn�����ļ�list
	 */
	static ArrayList<String> svnIncresementList = new ArrayList<String>();
	ArrayList<String> CutPath = new ArrayList<String>();
	
	/**
	 * �ж�һ��ָ�����ļ�����·�����Ƿ���������·����������ָ����·����
	 * @param localPathTemp
	 * @param svnPathTemp
	 * @return
	 */
	public boolean judgeFileInPath(String localPathTemp,String scmPathTemp){
		String pathCombine = (localPathTemp+"/"+scmPathTemp);
		if(new File(pathCombine).exists()&&(new File(pathCombine).isFile())==true){
			CorrectList.add(pathCombine);
			return true;
		}else
			return false;
	}
	
	/**
	 * ����һ��Ŀ¼�µ����еݹ�Ŀ¼
	 * @param localPathTemp
	 * @return
	 */
	public ArrayList<String> getRecursionPath(String localPathTemp){
		String[] CurrentPathList = new File(localPathTemp).list();
		for(String CurrentPathListTemp:CurrentPathList){
			String combinePath = localPathTemp +"/"+ CurrentPathListTemp;
			if((new File(combinePath).isFile())==false&&new File(combinePath).exists()==true){
				RecursionPath.add(combinePath);
				getRecursionPath(combinePath);
			}
		}
		return RecursionPath;
	}
	
	/**
	 * �ص�scmPath�ĵ�һ��
	 * @param scmPathTemp
	 * @return
	 */
//	public ArrayList<String> cutPath(String scmPathTemp){
//		String[] cutPath = scmPathTemp.replaceAll("\\\\", "/").replace(".java", ".class").trim().split("/");
//		CutPath.clear();
//		for(int i=0;i<cutPath.length;i++){
//			CutPath.add(cutPath[i].toString());
//		}
//		return CutPath;
//	}
	
	/**
	 * ���ļ����ݶ���List��listFileContext
	 * @param filePath
	 */
	public void readFile(String filePath){
		String brContext = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(filePath)));
			while((brContext = br.readLine())!=null){
				svnIncresementList.add(brContext);
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
	}

	/**
	 * �ж��ⲿ·���ڱ����ļ�ϵͳ���Ƿ����
	 * 
	 */
//	public void findPathInLocalFileSystem(){
//		for(int i=0;i<listFileContext.size();i++){
//			String cutbrContext = listFileContext.get(i);
//			Out:
//			for (int j=0;j<RecursionPath.size();j++){
//				cutPath(cutbrContext);
//				for(int k=0;k<CutPath.size();k++){
//					if(judgeFileInPath(RecursionPath.get(j),CutPath.get(k))==true){
//						listFileContext.remove(i);
//						i--;
//						break Out;
//					}
//				}
//			}
//		}
//	}
	
	/**
	 * ����svn����list�����Ѿ������õ�·����Ѱ�Ҷ�Ӧ�ļ�
	 */
	public void findPathInLocalFileSystem(){
		for(int i=0;i<svnIncresementList.size();i++){
			findFile(i);
		}
	}

	/**
	 * Ѱ���ļ�
	 * @param i
	 */
	private void findFile(int i) {
		String cutbrContext = svnIncresementList.get(i).replaceAll("\\\\", "/").replace(".java", ".class").trim();//java�滻��class
		for(int j=0;j<RecursionPath.size();j++){//��ԭʼ·�������ж� ���û�У���ִ���±ߵ�whileѭ���ж� ��ֹ���һ�ξͽ�ȡ����ʧ�������·���ı���
			if(judgeFileInPath(RecursionPath.get(j),cutbrContext)){
//					listFileContext.remove(i);
//					i--;
//				System.out.println("�����ļ�:"+RecursionPath.get(j)+cutbrContext);
				return;
			}
		}
		while (cutbrContext.contains("/")){//�������/���������ѭ��
			cutbrContext = cutbrContext.substring(cutbrContext.indexOf("/")+1);
			for(int j=0;j<RecursionPath.size();j++){
				if(judgeFileInPath(RecursionPath.get(j),cutbrContext)==true){
//						listFileContext.remove(i);
//						i--;
//					System.out.println("�����ļ�"+RecursionPath.get(j)+"/"+cutbrContext);
					return;
				}
			}
		}
	}
	
	/**
	 * ��list������ļ�
	 * @param list
	 * @param fileName
	 * @return
	 */
	public File list2File(ArrayList<String> list,String fileName){
		try {
			FileWriter bw = new FileWriter(fileName);
			for(int i=0;i<list.size();i++){
				bw.write(list.get(i));
				bw.write("\r\n");
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		File afterTransFile = new File(fileName);
		return afterTransFile;
	}
	
	public static File scmPath2LocalPath(String localPath,String scmFileList,String localPathList,String notFoundList) {
		SCMPath2LocalPathUtil scmPath2LocalPathUtil = new SCMPath2LocalPathUtil();	
		scmPath2LocalPathUtil.getRecursionPath(localPath);
		scmPath2LocalPathUtil.readFile(scmFileList);
		scmPath2LocalPathUtil.findPathInLocalFileSystem();
		return scmPath2LocalPathUtil.list2File(CorrectList,localPathList);
	}
	
	public static void main(String[] args) {
		SCMPath2LocalPathUtil b = new SCMPath2LocalPathUtil();
//		b.ScmPath2LocalPath("D:\\Draw\\unzip", "D:\\Draw\\list.txt", "D:\\Draw\\CorrectList","D:\\Draw\\notFoundList");
		b.getRecursionPath("D:\\checkout\\ȫ����\\trunk\\02.src\\HRBAMDS");
	}
}
