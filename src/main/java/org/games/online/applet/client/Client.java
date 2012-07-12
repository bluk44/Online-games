package org.games.online.applet.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import org.games.online.applet.events.*;
import org.games.online.tcpservice.server.TestNettyConnection.ClientChannelHandler;
import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

public class Client {

	protected String name;
	protected int id;
	protected static int clientCounter = 0;

	protected List<Object> listenerList = new ArrayList<Object>();

	public boolean isConnected = false;
	private ClientBootstrap bootstrap = null;
	private Channel channel = null;
	
	public Client() {
		this("bezimienny");
	}
	
	public Client(String name) {
		this.name = name;
		synchronized (this) {
			id = clientCounter++;
		}
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void addListener(Class<? extends EventListener> listenerType,
			EventListener listener) {
		listenerList.add(listenerType);
		listenerList.add(listener);
	}

	public void eventOccurred(Class<? extends EventListener> eventListenerType,
			Event event) {
		for (int i = 0; i < listenerList.size(); i += 2) {
			if (listenerList.get(i).equals(eventListenerType)) {
				EventListener el = (EventListener) listenerList.get(i + 1);
				el.eventOccurred(this, event);
			}
		}
	}

	/**
	 * Tries to connect to specified server
	 * 
	 * @param hostname
	 *            Server ip address
	 * @param port
	 *            Server port
	 */
	public void initConnection(String hostname, int port) {
		bootstrap = new ClientBootstrap(
				new NioClientSocketChannelFactory(
						Executors.newCachedThreadPool(),
						Executors.newCachedThreadPool()));
		ClientPipelineFactory factory = new ClientPipelineFactory(
				new ClientChannelHandler());
		
		ChannelFuture future = bootstrap.connect(new InetSocketAddress(
				hostname, 10000));

		// Wait until the connection attempt succeeds or fails.
		channel = future.awaitUninterruptibly().getChannel();
		if (!future.isSuccess()) {
			isConnected = false;
			eventOccurred(ConnectionFailedEventListener.class, new ConnectionFailedEvent());
		} else {
			isConnected = true;
			eventOccurred(ConnectedEventListener.class, new ConnectedEvent());
		}
	}

	/**
	 * Disconnects from server
	 * 
	 * @throws IOException
	 */
	public void closeConnection(){
		if (isConnected) {
			eventOccurred(DisconnectedEventListener.class, new DisconnectedEvent());
			ChannelFuture channelFuture = channel.close();
			channelFuture.awaitUninterruptibly();
			bootstrap.releaseExternalResources();
			eventOccurred(DisconnectedEventListener.class, new DisconnectedEvent());
		}
	}

	@Override
	public String toString() {
		return "client " + id + "\n";
	}
}
