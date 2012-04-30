package org.games.online.applet.client;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.games.online.applet.events.ChangedRoomEvent;
import org.games.online.applet.events.ChangedRoomEventListener;
import org.games.online.applet.events.ChannelExceptionEvent;
import org.games.online.applet.events.ChannelExceptionEventListener;
import org.games.online.applet.events.ConnectedEvent;
import org.games.online.applet.events.ConnectedEventListener;
import org.games.online.applet.events.DisconnectedEvent;
import org.games.online.applet.events.DisconnectedEventListener;
import org.games.online.applet.events.Event;
import org.games.online.applet.events.EventListener;
import org.games.online.applet.model.RoomInfo;
import org.games.online.message.ChangeRoomMessage;
import org.games.online.message.PlayerAuthenticationData;
import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

public class Client {

	protected String name;
	protected int id;
	protected static int clientCounter = 0;

	protected ChannelFactory channelFactory;
	protected ClientBootstrap bootstrap;
	protected ChannelFuture channelFuture;
	protected Channel channel;
	protected ChannelBuffer writeBuffer;

	protected List<Object> listenerList = new ArrayList<Object>();

	protected static String DEF_HOSTNAME = "localhost";
	protected static int DEF_SERVER_PORT = 10000;
	private Logger logger = Logger.getLogger(getClass());

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
		logger.info(name + ": connecting to " + hostname + ":" + port + " ...");
		channelFactory = new NioClientSocketChannelFactory(
				Executors.newCachedThreadPool(),
				Executors.newCachedThreadPool());

		bootstrap = new ClientBootstrap(channelFactory);
		ClientPipelineFactory factory = new ClientPipelineFactory(
				new ClientChannelHandler());
		bootstrap.setPipelineFactory(factory);
		bootstrap.setOption("tcpNoDelay", true);
		bootstrap.setOption("keepAlive", true);

		channelFuture = bootstrap
				.connect(new InetSocketAddress(hostname, port));
		channelFuture.awaitUninterruptibly();
		if (!channelFuture.isSuccess()) {
			channelFuture.getCause().printStackTrace();
			// logger.error(name + ": connection failed");
			System.out.println("connection failed");
		}
		channel = channelFuture.getChannel();
		eventOccurred(ConnectedEventListener.class, new ConnectedEvent());
		sendAuthenticationData();
		sendChangeRoomData();
	}

	/**
	 * Tries to connect to default server
	 */
	public void initConnection() {
		initConnection(DEF_HOSTNAME, DEF_SERVER_PORT);
	}

	/**
	 * Disconnects from server
	 */
	public void closeConnection() {
		ChannelFuture closeFuture = channel.close();

		closeFuture.awaitUninterruptibly();
		channelFactory.releaseExternalResources();
		// logger.info(name + ": dziekuje dobranoc");
		eventOccurred(DisconnectedEventListener.class, new DisconnectedEvent());
	}

	class ClientChannelHandler extends SimpleChannelHandler {

		@Override
		public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e)
				throws Exception {
			eventOccurred(ChannelExceptionEventListener.class,
					new ChannelExceptionEvent());
			super.exceptionCaught(ctx, e);
		}
		
		@Override
		public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
				throws Exception {
				if(e.getMessage() instanceof RoomInfo){
					ChangedRoomEvent event = new ChangedRoomEvent();
					event.setRoomInfo((RoomInfo)e.getMessage());
					eventOccurred(ChangedRoomEventListener.class, event);
				}
		}
	}
	
	void sendAuthenticationData(){
		PlayerAuthenticationData message = new PlayerAuthenticationData();
		message.setPlayerName(name);
		message.setSessionId("0");
		channel.write(message);
	}
	
	void sendChangeRoomData(){
		ChangeRoomMessage message = new ChangeRoomMessage();
		message.setRoomId(0);
		channel.write(message);
	}
	
}
