package com.unicompayment.fip.common.mina.decoder;

import java.util.HashMap;
import java.util.Map;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BasicDecoder implements ProtocolDecoder {
	
	private static Logger logger = LoggerFactory.getLogger(BasicDecoder.class);

	private String headLength;

	public String getHeadLength() {
		return headLength;
	}

	public void setHeadLength(String headLength) {
		this.headLength = headLength;
	}
	
	public void dispose(IoSession arg0) throws Exception {
		// Do nothing
	}

	public void finishDecode(IoSession arg0, ProtocolDecoderOutput arg1)
			throws Exception {
		// Do nothing
	}

	@Override
	public void decode(IoSession session, IoBuffer in, ProtocolDecoderOutput out)
			throws Exception {
		byte[] arr = in.array();
		in.flip();
		out.write(arr);
		
		int headlength = 0;
		try{
			headlength = Integer.parseInt(headLength);
		}catch(NumberFormatException nfe){			
		}
		byte[] headbytes = new byte[headlength];		
		System.arraycopy(arr, 0, headbytes, 0, headlength);
		String headStr = new String(headbytes);
		int msglength = 0;
		try{
			msglength=Integer.parseInt(headStr);
		}catch(NumberFormatException nfe){			
		}
		byte[] msgbytes = new byte[msglength];
		System.arraycopy(arr, headlength, msgbytes, 0, msgbytes.length);		
//		if(msglength != msgbytes.length){
//			logger.error("报文长度校验未通过！");
//			return;
//		}
		
		String inStr = new String(msgbytes);
		Map<String,Object> inMap = null;
		if(inStr != null && !inStr.trim().equals("")){
			String [] inStrArr = inStr.split("[`]",-1);
			if(inStrArr != null && inStrArr.length>0){
				inMap = new HashMap<String,Object>();
				for(String strTemp : inStrArr)
				try{
					String[] strTempArr = strTemp.split("[|]",-1);
					inMap.put(strTempArr[0], strTempArr[1]);
				}catch(Exception e){					
				}
			}
			
		}
		
	}

}
