/*
 * @(#)MathUtil.java     V1.0.0      @2014-6-18
 *
 * Project:unpcommon
 *
 * Modify Information:
 *    Author        Date        Description
 *    ============  ==========  =======================================
 *    chenyong       2014-6-18     Create this file
 * 
 * Copyright Notice:
 *     Copyright (c) 2009-2014 Unicompay Co., Ltd. 
 *     1002 Room, No. 133 North Street, Xi Dan, 
 *     Xicheng District, Beijing ,100032, China 
 *     All rights reserved.
 *
 *     This software is the confidential and proprietary information of
 *     Unicompay Co., Ltd. ("Confidential Information").
 *     You shall not disclose such Confidential Information and shall use 
 *     it only in accordance with the terms of the license agreement you 
 *     entered into with Unicompay.
 */
package com.unicompayment.fip.common.utils.text;

import java.text.NumberFormat;
import java.util.Random;
import java.util.StringTokenizer;
/**
 * 数字文本Util类
 *
 * @author chenyong
 */
public class MathUtil {
	private static final Random r = new Random(); 
    
    private static final Random r1 = new Random(System.currentTimeMillis() + 1); // for msg file
    private static final Random r2 = new Random(System.currentTimeMillis() + 2); // for B2BTrnxNO
    private static final Random r3 = new Random(System.currentTimeMillis() + 3); // for MsgID

    public MathUtil() {

    }

    /**
     * 去掉金额逗号
     * @param amt
     * @return
     */
    public static String delAmtComma(String amt) {
        if(amt != null && !amt.equals("")) {
            int idx = 0;
            while( (idx = amt.indexOf(",")) != -1) {
                amt = amt.substring(0, idx) + amt.substring(idx + 1);
            }
        }
        return amt;
    }

    /**
     * 判断是否数字
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        try {
            Double.parseDouble(str);
            return true;
        }
        catch(Exception e) {
            return false;
        }
    }

    /**
     * 判断是否含有小数的数字
     * @param str
     * @return
     */
    public static boolean isDecimal(String str) {
        try {
            double dstr = Double.parseDouble( (str));
            if(str.indexOf(".") != -1 && dstr % 1 != 0) {
                return true;
            } else {
                return false;
            }
        }
        catch(Exception e) {
            return false;
        }
    }

    /**
     * 判断数字格式
     * @param the String need to be determine
     * @param integerDigits Integer digitals
     * @param decimalNum Decimal digitals
     */
    public static boolean isRightNumberFormat(String str, int integerDigitals,
        int decimalDigitals) {
        try {
            double dstr = Double.parseDouble( (str));

            if(str.indexOf(".") != -1) {
                StringTokenizer tk = new StringTokenizer(str, ".");
                String ID = tk.nextToken();
                String DD = tk.nextToken().trim();

                if(ID.length() < integerDigitals + 1 && DD.length() < decimalDigitals + 1) {
                    return true;
                } else {
                    return false;
                }
            } else {
                if(str.length() < integerDigitals + 1) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        catch(Exception e) {
            return false;
        }
    }

    /**
     * 将数字转化为百分数
     * @param amt
     * @return
     */
    public static String toPercent(double amt) {
        if(amt > 1) {
            throw new java.lang.NumberFormatException();
        }
        amt = amt*100D;
        NumberFormat amtFormat = NumberFormat.getInstance();
        amtFormat.setGroupingUsed(false);
        amtFormat.setMaximumFractionDigits(6);
        String rtn_amt = "0";
        if(amt != 0) {
            rtn_amt = amtFormat.format(amt);
        }
        return rtn_amt;
    }
    
    /**
     * 请求报文文件名产生随机数(scale为位数)
     * @param scale
     * @return
     */
    public static String randomNumberForMsgFileName(int scale) {
        return randomNumber(scale, r1);
    }
    
    /**
     * B2BTrnxNo产生随机数(scale为位数)
     * @param scale
     * @return
     */
    public static String randomNumberForB2BTrnxNo(int scale) {
        return randomNumber(scale, r1);
    }
    
    /**
     * 后台报文MsgId产生随机数(scale为位数)
     * @param scale
     * @return
     */
    public static String randomNumberForMsgId(int scale) {
        return randomNumber(scale, r1);
    }
    
    /**
     * 产生随机数(scale为位数)
     * @param scale
     * @return
     */
    private static String randomNumber(int scale, Random r) {
        int result;
        int max = new Double(Math.pow(10, scale)).intValue();
        int min = new Double(Math.pow(10, scale-1)).intValue();
        while (true) {
            result = r.nextInt(max) % max;
            if (result > min) {
                return String.valueOf(result);
            }
        }
    }
    
    /**
     * 产生随机数(scale为位数)
     * @param scale
     * @return
     */
    public static String randomNumber(int scale) {
        int result;
        int max = new Double(Math.pow(10, scale)).intValue();
        int min = new Double(Math.pow(10, scale-1)).intValue();
        while (true) {
            result = r.nextInt(max) % max;
            if (result > min) {
                return String.valueOf(result);
            }
        }
    }
}
