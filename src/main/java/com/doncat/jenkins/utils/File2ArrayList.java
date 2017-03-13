package com.doncat.jenkins.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class File2ArrayList {
	public ArrayList<String> File2ArrayList(String fileName){
		System.out.println(fileName+"������Ϊ��");
		ArrayList<String> listArr = new ArrayList<String>();
		File file = new File(fileName);
		String read = null;
		
		try {
			BufferedReader readFile = new BufferedReader(new FileReader(file));
			while((read = readFile.readLine())!=null){
				listArr.add(read);
				System.out.println(read);
			}
		} catch (FileNotFoundException e) {
			System.out.println("û���ҵ�"+fileName+"����ļ�");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return listArr;
	}
}
