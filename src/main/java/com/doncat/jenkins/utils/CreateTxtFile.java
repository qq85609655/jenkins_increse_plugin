package com.doncat.jenkins.utils;

import java.io.File;
import java.io.IOException;

public class CreateTxtFile {
	public CreateTxtFile(File filename){
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
