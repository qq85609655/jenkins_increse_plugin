package com.unicompayment.fip.common.utils.uuid;

import java.util.Random;

/**
 * 
 * 随机数公共类
 * @author donglei
 *
 */
public class RandomUtil {
	
	/**
	 * 
	 * 生成指定位数的随机数
	 * @param pwd_len
	 * @return
	 */
	public static String genRandomNum(int pwd_len){
	    //35是因为数组是从0开始的，26个字母+10个数字
	    final int maxNum = 36;
	    int i; //生成的随机数
	    int count = 0; //生成的密码的长度
	    char[] str = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
	    StringBuffer pwd = new StringBuffer("");
	    Random r = new Random();
	    while(count < pwd_len){
	     //生成随机数，取绝对值，防止生成负数，
	   
	     i = Math.abs(r.nextInt(maxNum)); //生成的数最大为36-1
	   
	     if (i >= 0 && i < str.length) {
	      pwd.append(str[i]);
	      count ++;
	     }
	    }
	    return pwd.toString();
	 }

}
