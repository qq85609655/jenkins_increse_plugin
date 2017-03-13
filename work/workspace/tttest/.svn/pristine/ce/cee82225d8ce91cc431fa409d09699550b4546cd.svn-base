package com.unicompayment.fip.common.mina.codecfactory;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

import com.unicompayment.fip.common.mina.decoder.BasicDecoder;
import com.unicompayment.fip.common.mina.encoder.BasicEncoder;




public class BasicCodecFactory implements ProtocolCodecFactory{

	private BasicDecoder decoder = new BasicDecoder();
	
	private BasicEncoder encoder = new BasicEncoder();
	
	public void setDecoder(BasicDecoder dec){
		decoder = dec;
	}
	
	public void setEncoder(BasicEncoder enc){
		encoder  = enc;
	}

	public ProtocolDecoder getDecoder(IoSession arg0) throws Exception {
		// TODO Auto-generated method stub
		return decoder;
	}

	public ProtocolEncoder getEncoder(IoSession arg0) throws Exception {
		// TODO Auto-generated method stub
		return encoder;
	}
}

