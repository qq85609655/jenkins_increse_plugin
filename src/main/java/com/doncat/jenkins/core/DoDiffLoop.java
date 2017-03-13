package com.doncat.jenkins.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.internal.wc.DefaultSVNOptions;
import org.tmatesoft.svn.core.wc.ISVNOptions;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc.SVNRevision;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

import com.doncat.jenkins.svnapi.SimpleISVNDiffStatusHandler;
import com.doncat.jenkins.utils.CreateTxtFile;
import com.doncat.jenkins.svnapi.Diff;

public class DoDiffLoop {
	private static SVNClientManager ourClientManager; //svn �ͻ��˹�����
	
	public void DoDiffLoopImpl(String svnRevisionStr,String svnUrl,String filePath){
		File filename = new File(filePath);
		PrintStream psold = System.out;
		FileOutputStream bos;
		new CreateTxtFile(filename);
		
		try {
			bos = new FileOutputStream(filename);
			System.setOut(new PrintStream(bos));
			
			DAVRepositoryFactory.setup();//��ʼ��֧��httpЭ��Ŀ⡣������ִ�д˲�����
			ISVNOptions options = SVNWCUtil.createDefaultOptions(true);//����ѡ��
			String svnUserName = "user";
			String svnPassword = "123qwe!@#";
			ourClientManager = SVNClientManager.newInstance((DefaultSVNOptions) options, svnUserName, svnPassword);
			SimpleISVNDiffStatusHandler diffhandler = new SimpleISVNDiffStatusHandler();
			
			SVNURL repositoryURL = null;
			try{
			      repositoryURL = SVNURL.parseURIEncoded(svnUrl);
			} catch(SVNException e){
			      e.printStackTrace();
			}
			
			ArrayList<Integer> SvnRevisionNumList = getSvnRevisionNumList(svnRevisionStr);
			for(int i=0;i<SvnRevisionNumList.size();i++){
				SVNRevision previousRevision = SVNRevision.create(SvnRevisionNumList.get(i)-1);
				SVNRevision Revision = SVNRevision.create(SvnRevisionNumList.get(i));
				try {
					ourClientManager.getDiffClient().doDiffStatus(repositoryURL, previousRevision, repositoryURL, Revision, true, true, diffhandler);
				} catch (SVNException e) {
					e.printStackTrace();
				}
			}
			System.setOut(psold);
			bos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private ArrayList<Integer> getSvnRevisionNumList(String svnRevisionStr){
		String[] SvnRevisionNumListStr = svnRevisionStr.split(",");
		ArrayList<Integer> SvnRevisionNumList = new ArrayList<Integer>();
		for(int i=0;i<SvnRevisionNumListStr.length;i++){
			SvnRevisionNumList.add(Integer.parseInt(SvnRevisionNumListStr[i]));
		}
		return SvnRevisionNumList;
	}
}
