package com.doncat.jenkins.core;

import java.io.File;
import java.util.logging.Logger;

import com.doncat.jenkins.utils.WarUtil;

public class DrawFileUtil {
	private final static Logger logger = Logger.getLogger("SCMPath2LocalPath");
	public static void draw(String correctListFilePath, String increasePathStr,String warPath, String unzipPath, String localWorkspace) {
		WarUtil warDeal = new WarUtil();
		warDeal.unzipWar(warPath, unzipPath);
		File increasePath = new File(increasePathStr);
		if(!increasePath.exists()){
			increasePath.mkdirs();
		}
		GetIncreFile gifile = new GetIncreFile(new File(correctListFilePath),localWorkspace, increasePathStr);
	}
}
