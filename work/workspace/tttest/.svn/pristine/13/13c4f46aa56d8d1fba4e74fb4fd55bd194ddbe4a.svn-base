package com.unicompayment.fip.common.utils.network;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * 功能：http工具类 
 * 说明： 该代码是示例代码，供研究和开发联通支付接口使用，商户可以按接口自己编写。
 * 该代码只是提供一个参考，并非一定要使用。
 * 
 * 作者 联通支付平台接口开发团队
 * 版本：1.0 
 * 日期：2011-10-25 
 * 
 */
public class HttpUtils {

	/**
	 * send http by post method
	 * 
	 * @param url
	 * @param map
	 * @return List
	 * @throws Exception
	 */
	public static List URLPost(String url, Map map,String charSet) throws Exception {
		URL sendurl = new URL(url);

		HttpURLConnection httpURLConnection = (HttpURLConnection) sendurl
				.openConnection();
		httpURLConnection.setRequestMethod("POST");
		httpURLConnection.setDoInput(true);
		httpURLConnection.setDoOutput(true);
		httpURLConnection.setUseCaches(false);
		httpURLConnection.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded;charset="+ charSet);

		StringBuffer sb = new StringBuffer();

		Iterator it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pairs = (Map.Entry) it.next();
			sb.append(pairs.getKey()).append("=").append(
					URLEncoder.encode(pairs.getValue().toString(),
							charSet)).append("&");
		}
		if (sb.length() > 0)// delete last & char
		{
			sb.setLength(sb.length() - 1);
		}

		// send data
		String sendData = sb.toString();

		OutputStream out = httpURLConnection.getOutputStream();
		out.write(sendData.getBytes("iso8859-1"));
		out.flush();
		out.close();

		InputStream in = httpURLConnection.getInputStream();

		BufferedReader reader = new BufferedReader(new InputStreamReader(in));

		List<String> result = new ArrayList<String>();
		while (true) {
			String line = reader.readLine();
			if (line == null) {
				break;
			} else {
				result.add(line);
			}
		}

		reader.close();
		in.close();

		return result;
	}
	
	public static Map<String,String> rspListToMap(List rspList,String rspCharset){
		Map<String,String> map = new HashMap<String, String>();
		for (int i = 0; i < rspList.size(); i++) {
			String currentResult = (String) rspList.get(i);
			if (currentResult == null || currentResult.equals("")) {
				continue;
			}
			int j = currentResult.indexOf("=");
			// int k = currentResult.length();
			if (j >= 0) {
				String sKey = currentResult.substring(0, j);
				String sValue = currentResult.substring(j + 1);
				try {
					sValue = URLDecoder.decode(sValue, rspCharset);
				} catch (UnsupportedEncodingException e) {
					throw new RuntimeException(e.getMessage());
				}
				map.put(sKey, sValue);
			}
		}
		return map;
	}
	
}
