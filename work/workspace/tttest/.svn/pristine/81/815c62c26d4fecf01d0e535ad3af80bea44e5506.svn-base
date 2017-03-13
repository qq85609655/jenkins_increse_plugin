package com.unicompayment.fip.common.mina.iohandler;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public abstract class BasicIoHandler extends IoHandlerAdapter{
	
	private static Logger logger = LoggerFactory.getLogger(BasicIoHandler.class);
	
	//解码器，将原生的字节数组转化成内部业务流程需要的数据类型
	protected Object doDecode(byte[] message) throws Exception{
		return message;
	}

	protected abstract Object doBusiness(Object obj) throws Exception;
	
	protected byte[] doEncode(Object data) throws Exception{
		return (byte[]) data;
	}
	
	@Override
	public void messageReceived(IoSession session,Object message) throws Exception{
		try {
			//解码处理
			Object inObj = doDecode((byte[]) message);
			//业务处理
			Object outObj= doBusiness(inObj);
			//编码处理
			byte[] resultBytes = doEncode(outObj);
			//回应客户端
			session.write(resultBytes);
		} catch (Exception e) {
			logger.error("BasicIoHandler.messageReceived：=：", e);
			throw e;
		} finally{
			try {
				session.close(true);
			} catch (Throwable e) {
				logger.error("sessionCloseException：=：", e);
			}
		}	
	}
	@Override
	public void exceptionCaught(IoSession session,Throwable cause) throws Exception{
		try {
			session.close(true);
		} catch (Throwable e) {
			logger.error("sessionCloseException：=：", e);
		}
	}
	@Override
	public void messageSent(IoSession session,Object message) throws Exception{
	}
	@Override
	public void sessionClosed(IoSession session) throws Exception{		
		logger.debug("=======================通信完毕，断开连接===================");		
	}
	@Override
	public void sessionCreated(IoSession session) throws Exception{
		session.getConfig().setUseReadOperation(true);
	}
	@Override
	public void sessionIdle(IoSession session,IdleStatus status) throws Exception{
		super.sessionIdle(session, status);
	}
	@Override
	public void sessionOpened(IoSession session) throws Exception{
		String client = (String) session.getAttribute("client");
		if (client == null) {
			logger.debug("===================IP=" + session.getRemoteAddress() + "的客户端接入本系统==================");
		} else {
			logger.debug("===================连接到服务器=" + session.getRemoteAddress() + "==================");
		}
	}
}
