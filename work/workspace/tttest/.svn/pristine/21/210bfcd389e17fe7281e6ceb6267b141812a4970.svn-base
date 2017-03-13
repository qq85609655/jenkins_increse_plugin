package com.unicompayment.fip.common.mina.encoder;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BasicEncoder implements ProtocolEncoder {
	
	private static Logger logger = LoggerFactory.getLogger(BasicEncoder.class);
	
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

	public void encode(IoSession session, Object obj, ProtocolEncoderOutput out)
			throws Exception {
		try{						
			byte[] resbytes = (byte[])obj;
			IoBuffer buf = IoBuffer.allocate(resbytes.length);
			buf.setAutoExpand(true);
			if (resbytes != null)
				buf.put(resbytes);
			buf.flip();
			out.write(buf);
		} catch (Exception e) {
			logger.error("encoding error:=:", e);
			session.close(true);
			throw e;
		}
	}

}
