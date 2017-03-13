package com.doncat.jenkins.core;

import java.io.IOException;
import java.util.logging.Logger;

import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.internal.wc.DefaultSVNOptions;
import org.tmatesoft.svn.core.wc.ISVNOptions;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc.SVNRevision;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

import com.doncat.jenkins.utils.WarUtil;
import com.doncat.jenkins.svnapi.Diff;

public class GetFileViaSVNList {
	private final static Logger logger = Logger.getLogger("GetFileViaSVNList");
	public static SVNClientManager ourClientManager; //svn �ͻ��˹�����
	
//	public static void main(String[] args) {
//		String trunkPath = args[0];//����url����diffʱ��ԭ��֧
//		String branchPath = args[1];//��֧url����diffʱ��Ŀ���֧
//		String localWorkspace = args[2];//���ش���ù��̵ĸ�Ŀ¼
//		String packageName = args[3];//Ҫ�����ȫ��������
//		
////		String trunkPath = "http://130.1.11.180/svn/creditloan/qlcx/trunk/02.src/HRBCMS";//Ҫ�����svnĿ¼
////		String branchPath = "http://130.1.11.180/svn/creditloan/qlcx/branches/batch-yz/HRBCMS";
////		String localWorkspace = "D:\\checkout\\ȫ����\\branches\\batch-yz\\HRBCMS\\target";//���ش���ù��̵ĸ�Ŀ¼
////		String packageName = "HRBCMS.war";//Ҫ�����ȫ��������
//		
//		Diff svndiffHeadResult = new Diff();
//		try {
//			DAVRepositoryFactory.setup();
//			ISVNOptions options = SVNWCUtil.createDefaultOptions(true);
//			String name = "liujingran";
//			String password = "3759662Ljr";
//			ourClientManager = SVNClientManager.newInstance((DefaultSVNOptions)options, name, password);
//
//			svndiffHeadResult.getDiffResult(ourClientManager, trunkPath, SVNRevision.HEAD, branchPath, SVNRevision.HEAD, localWorkspace+"\\svndiffresult.txt");
//		} catch (SVNException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		logger.info("�����б��ȡ��ϣ�׼����ѹwar��");
//		WarUtil unzipWar = new WarUtil();
//		String unzipPath = unzipWar.getFileNameWithNoSux(packageName);
//		unzipWar.unzipWar(localWorkspace+"/"+packageName,localWorkspace+"/"+unzipPath);
//		logger.info("war����ѹ��ϣ�׼��ƥ���ļ�");
//		SCMPath2LocalPathUtil.scmPath2LocalPath(localWorkspace+"\\"+unzipPath, localWorkspace+"\\svndiffresult.txt", localWorkspace+"\\CorrectList.txt",localWorkspace+"\\notFoundList.txt");
//		logger.info("�ļ�ƥ����ϣ�׼����ȡ�ļ�");
//		DrawFileUtil.draw(localWorkspace+"\\CorrectList.txt",localWorkspace+"\\increaseFile",localWorkspace+"\\"+packageName,localWorkspace+"\\unzipPath",localWorkspace);
//		logger.info("�����ļ���ȡ��ϣ�λ�ã�"+localWorkspace+"\\increaseFile");
//	}
}
