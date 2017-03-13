package com.unicompayment.fip.common.utils.text;

public class XmlTagUtil {

	public static String getTagContent(String xml,String tag){
		if(tag==null || tag.trim().length() == 0)
			return null;
		String start = "<"+tag+">";
		String end= "</"+tag+">";
		if(xml == null || xml.indexOf(start)==-1
				|| xml.indexOf(start) > xml.indexOf(end))
			return null;
		return xml.substring(xml.indexOf(start)+start.length(), xml.indexOf(end)).trim();
	}
	
	public static String makeXmlNode(String content,String tag){
		if(tag==null || tag.trim().length() == 0)
			return null;
		String start = "<"+tag+">";
		String end= "</"+tag+">";
		if(content == null)
			content = "";
		return start + content.trim() + end;
	}
	
}
