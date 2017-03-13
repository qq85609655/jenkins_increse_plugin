package com.doncat.jenkins.svnapi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.logging.Logger;

import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc.SVNRevision;


public class Diff {
	private final static Logger logger = Logger.getLogger("Diff");
	
    SimpleISVNDiffStatusHandler diffhandler = new SimpleISVNDiffStatusHandler();

    public void getDiffResult(SVNClientManager svnCM,String oldurl, SVNRevision oldRev, String newurl, SVNRevision newRev, String filePath) throws IOException, SVNException{
    	System.out.println("��ȡ�ļ��б�");
    	File filename = new File(filePath);
    	createTxtFile(filename);
    	PrintStream psold = System.out;
    	FileOutputStream bos;
    	
    	//����ת��
//    	SVNRevision oldRev = SVNRevision.create(oldRevision);
//	    SVNRevision newRev = SVNRevision.create(newRevision);
	    SVNURL oldURL=SVNURL.parseURIEncoded(oldurl);
	    SVNURL newURL=SVNURL.parseURIEncoded(newurl);
    	
        try {
    		bos = new FileOutputStream(filename);
    		System.setOut(new PrintStream(bos));
//    		logger.info(""+oldURL+"\n"+ oldRev+"\n"+  newURL+ "\n"+ newRev+ "\n"+ diffhandler);
    		svnCM.getDiffClient().doDiffStatus(oldURL, oldRev, newURL, newRev, true, false, diffhandler);
//    		svnCM.getDiffClient().doDiff(oldURL, oldRev, newURL, newRev, true, false, bos);
    		System.setOut(psold);
    		bos.close();
		} catch (FileNotFoundException e1) {
    		e1.printStackTrace();
    	}
    }
	
    public void createTxtFile(File filename){
		if (!filename.exists()){
	  		   try {
				filename.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
	  		   System.err.println(filename+"�Ѵ���");
	  	   }
	  	else{
	  		   System.out.println(filename+"�Ѵ���");
	  	   }
	}   
}
