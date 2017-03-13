package com.unicompayment.fip.common.utils.sign;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能：签名工具类 
 * 说明： 该代码是示例代码，供研究和开发联通支付接口使用，商户可以按接口自己编写。
 * 建议不做修改。
 * 
 * 作者 联通支付平台接口开发团队
 * 版本：1.0 
 * 日期：2011-10-25 
 * 
 */
public class UniPaySignUtils {
	
	/**签名方式*/
	private static final String ALG_MD5 = "MD5"; 
	private static final String ALG_SHA1 = "SHA-1"; 
	
	/** 
	 * 生成MD5方式签名的签名结果
	 * @param sArray 要签名的数组
	 * @param key 签名密码
	 * @param inputCharset 签名字符集,与请求接口中的字符集一致
	 * @return 签名结果字符串
	 */
	public static String getMd5SignMsg(Map<String, String> params,String key,String inputCharset) {
		String signSource = getSignSourMsg(params,key);
		String signMsg = HashEncrypt.doEncrypt(signSource, ALG_MD5, inputCharset);
		System.out.println("签名："+signMsg);
		return signMsg;
	}
	
	/** 
	 * 生成SHA-1方式签名的签名结果
	 * @param sArray 要签名的数组
	 * @param key 签名密码
	 * @param inputCharset 签名字符集,与请求接口中的字符集一致
	 * @return 签名结果字符串
	 */
	public static String getSha1SignMsg(Map<String, String> params,String key,String inputCharset) {
		String signSource = getSignSourMsg(params,key);
		String signMsg = HashEncrypt.doEncrypt(signSource,ALG_SHA1, inputCharset);
		return signMsg;
	}
	
	/** 
	 * 生成证书方式签名的签名结果
	 * @param sArray 要签名的数组
	 * @param inputCharset 签名字符集,与请求接口中的字符集一致
	 * @return 签名结果字符串
	 */
	public static String getCertSignMsg(Map<String, String> params,String inputCharset) {
		String signSource = getSignSourMsg(params,"");
		String signMsg = signSource;//实现待定
		return signMsg;
	}
	
	/** 
	 * 得到签名源数据
	 * @param sArray 要签名的参数数组
	 * @param key 签名密码
	 * @return 签名源字符串
	 */
	private static String getSignSourMsg(Map<String, String> params, String key) {
		params = doFilterParam(params);
		String signSource = createLinkString(params);
		if (key != null && key.length() > 0) {//证书签名时签名密码为空
			signSource = signSource + "|key=" + key;
		}
		System.out.println("签名原数据："+signSource);
		return signSource;

	}
	
	/** 
	 * 处理数组中的null值和除去非签名参数
	 * @param sArray 签名参数组
	 * @return 去掉空值与签名参数后的新签名参数组
	 */
	private static Map<String, String> doFilterParam(Map<String, String> params) {
		List<String> keys = new ArrayList<String>(params.keySet());
		Map<String, String> newParam = new HashMap<String, String>();

		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = params.get(key);
			
			if ("hmac".equalsIgnoreCase(key) || "signMsg".equalsIgnoreCase(key)
					|| "cert".equalsIgnoreCase(key)) {
				continue;
			}
			if (value == null||value.length()==0) {
				continue;
			}
			newParam.put(key, value);
		}

		return newParam;
	}
	
	/** 
	 * 把数组所有元素排序，并按照“参数=参数值”的模式用“|”字符拼接成字符串
	 * @param params 需要排序并参与字符拼接的参数组
	 * @return 拼接后字符串
	 */
	private static String createLinkString(Map<String, String> params) {
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);

		StringBuffer prestr = new StringBuffer("");

		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = params.get(key);

			if (i == keys.size() - 1) {// 拼接时，不包括最后一个|字符
				prestr.append(key).append("=").append(value);
			} else {
				prestr.append(key).append("=").append(value).append("|");
			}
		}
		return prestr.toString();
	}
}
