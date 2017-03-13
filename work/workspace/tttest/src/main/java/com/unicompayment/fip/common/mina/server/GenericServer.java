package com.unicompayment.fip.common.mina.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.filterchain.IoFilter;
import org.apache.mina.core.filterchain.IoFilterChainBuilder;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.service.IoServiceListener;
import org.apache.mina.core.service.IoServiceStatistics;
import org.apache.mina.core.service.TransportMetadata;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.session.IoSessionDataStructureFactory;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.SocketSessionConfig;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import com.unicompayment.fip.common.mina.decoder.BasicDecoder;
import com.unicompayment.fip.common.mina.encoder.BasicEncoder;


public class GenericServer implements SocketAcceptor {
	
	private NioSocketAcceptor nsa;
	
	public GenericServer(String localPort,IoHandler handler){
		nsa = new NioSocketAcceptor();
		nsa.setDefaultLocalAddress(new InetSocketAddress(Integer.parseInt(localPort)));
		DefaultIoFilterChainBuilder dicb = new DefaultIoFilterChainBuilder();
		Map<String, IoFilter> filters = new LinkedHashMap<String, IoFilter>();
		filters.put("codecFilter", new ProtocolCodecFilter(new BasicEncoder(), new BasicDecoder()));
		filters.put("executorFilter", new ExecutorFilter());
		dicb.setFilters(filters);
		nsa.setFilterChainBuilder(dicb);
		nsa.setReuseAddress(true);
		nsa.setHandler(handler);
	}

	@Override
	public int getBacklog() {
		return nsa.getBacklog();
	}

	@Override
	public InetSocketAddress getDefaultLocalAddress() {
		return nsa.getDefaultLocalAddress();
	}

	@Override
	public InetSocketAddress getLocalAddress() {
		return nsa.getLocalAddress();
	}

	@Override
	public SocketSessionConfig getSessionConfig() {
		return nsa.getSessionConfig();
	}

	@Override
	public boolean isReuseAddress() {
		return nsa.isReuseAddress();
	}

	@Override
	public void setBacklog(int arg0) {
		nsa.setBacklog(arg0);
	}

	@Override
	public void setDefaultLocalAddress(InetSocketAddress arg0) {
		nsa.setDefaultLocalAddress(arg0);
	}

	@Override
	public void setReuseAddress(boolean arg0) {
		nsa.setReuseAddress(arg0);
	}

	@Override
	public void bind() throws IOException {
		nsa.bind();
	}

	@Override
	public void bind(SocketAddress arg0) throws IOException {
		nsa.bind(arg0);
	}

	@Override
	public void bind(Iterable<? extends SocketAddress> arg0) throws IOException {
		nsa.bind(arg0);
	}

	@Override
	public void bind(SocketAddress arg0, SocketAddress... arg1)
			throws IOException {
		nsa.bind(arg0, arg1);
	}

	@Override
	public List<SocketAddress> getDefaultLocalAddresses() {
		return nsa.getDefaultLocalAddresses();
	}

	@Override
	public Set<SocketAddress> getLocalAddresses() {
		return nsa.getLocalAddresses();
	}

	@Override
	public boolean isCloseOnDeactivation() {
		return nsa.isCloseOnDeactivation();
	}

	@Override
	public IoSession newSession(SocketAddress arg0, SocketAddress arg1) {
		return nsa.newSession(arg0, arg1);
	}

	@Override
	public void setCloseOnDeactivation(boolean arg0) {
		nsa.setCloseOnDeactivation(arg0);
	}

	@Override
	public void setDefaultLocalAddress(SocketAddress arg0) {
		nsa.setDefaultLocalAddress(arg0);
	}

	@Override
	public void setDefaultLocalAddresses(Iterable<? extends SocketAddress> arg0) {
		nsa.setDefaultLocalAddresses(arg0);
	}

	@Override
	public void setDefaultLocalAddresses(List<? extends SocketAddress> arg0) {
		nsa.setDefaultLocalAddresses(arg0);
	}

	@Override
	public void setDefaultLocalAddresses(SocketAddress arg0,
			SocketAddress... arg1) {
		nsa.setDefaultLocalAddresses(arg0, arg1);
	}

	@Override
	public void unbind() {
		nsa.unbind();
	}

	@Override
	public void unbind(SocketAddress arg0) {
		nsa.unbind(arg0);
	}

	@Override
	public void unbind(Iterable<? extends SocketAddress> arg0) {
		nsa.unbind(arg0);
	}

	@Override
	public void unbind(SocketAddress arg0, SocketAddress... arg1) {
		nsa.unbind(arg0,arg1);
	}

	@Override
	public void addListener(IoServiceListener arg0) {
		nsa.addListener(arg0);
	}

	@Override
	public Set<WriteFuture> broadcast(Object arg0) {
		return nsa.broadcast(arg0);
	}

	@Override
	public void dispose() {
		nsa.dispose();
	}

	@Override
	public void dispose(boolean arg0) {
		nsa.dispose(arg0);
	}

	@Override
	public long getActivationTime() {
		return nsa.getActivationTime();
	}

	@Override
	public DefaultIoFilterChainBuilder getFilterChain() {
		return nsa.getFilterChain();
	}

	@Override
	public IoFilterChainBuilder getFilterChainBuilder() {
		return nsa.getFilterChainBuilder();
	}

	@Override
	public IoHandler getHandler() {
		return nsa.getHandler();
	}

	@Override
	public int getManagedSessionCount() {
		return nsa.getManagedSessionCount();
	}

	@Override
	public Map<Long, IoSession> getManagedSessions() {
		return nsa.getManagedSessions();
	}

	@Override
	public int getScheduledWriteBytes() {
		return nsa.getScheduledWriteBytes();
	}

	@Override
	public int getScheduledWriteMessages() {
		return nsa.getScheduledWriteMessages();
	}

	@Override
	public IoSessionDataStructureFactory getSessionDataStructureFactory() {
		return nsa.getSessionDataStructureFactory();
	}

	@Override
	public IoServiceStatistics getStatistics() {
		return nsa.getStatistics();
	}

	@Override
	public TransportMetadata getTransportMetadata() {
		return nsa.getTransportMetadata();
	}

	@Override
	public boolean isActive() {
		return nsa.isActive();
	}

	@Override
	public boolean isDisposed() {
		return nsa.isDisposed();
	}

	@Override
	public boolean isDisposing() {
		return nsa.isDisposing();
	}

	@Override
	public void removeListener(IoServiceListener arg0) {
		nsa.removeListener(arg0);
	}

	@Override
	public void setFilterChainBuilder(IoFilterChainBuilder arg0) {
		nsa.setFilterChainBuilder(arg0);
	}

	@Override
	public void setHandler(IoHandler arg0) {
		nsa.setHandler(arg0);
	}

	@Override
	public void setSessionDataStructureFactory(
			IoSessionDataStructureFactory arg0) {
		nsa.setSessionDataStructureFactory(arg0);
	}

	@Override
	public void bind(SocketAddress... arg0) throws IOException {
		nsa.bind(arg0);
	}

}
