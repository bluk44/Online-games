package org.games.online.tcpservice.server;

import java.net.InetSocketAddress;

import org.apache.log4j.Logger;
import org.games.online.events.PlayerConnectedEvent;
import org.games.online.message.Message;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.group.ChannelGroup;
import org.jboss.netty.channel.group.ChannelGroupFuture;
import org.springframework.context.ApplicationListener;

public class GameServer implements ApplicationListener<PlayerConnectedEvent> {
	protected int port = 10000;

	/**
	 * a channel which accepts connections from the clients
	 */
	protected Channel serverChannel;
	/**
	 * a factory which creates and manages channels and it's resources processes
	 * all I/O requests and performs I/O to generate ChannelEvents
	 */
	protected ChannelFactory channelFactory;
	/**
	 * Whenever a new connection is accepted by the server, a new
	 * ChannelPipeline will be created by the specified ChannelPipelineFactory.
	 */
	protected ChannelPipelineFactory channelPipelineFactory;
	/**
	 * channel group of logged players
	 */
	protected ChannelGroup authenticatedChannels;
	/**
	 * channel group of newly connected players
	 */
	protected ChannelGroup anonymousChannels;
	/**
	 * a helper class which sets up the server
	 */
	protected ServerBootstrap serverBootstrap;

	protected Logger logger = Logger.getLogger(this.getClass());

	public GameServer(ChannelFactory channelFactory,
			ChannelPipelineFactory channelPipelineFactory,
			ChannelGroup authenticatedChannels, ChannelGroup anonymousChannels,
			ServerBootstrap serverBootstrap) {
		this.channelFactory = channelFactory;
		this.channelPipelineFactory = channelPipelineFactory;
		this.authenticatedChannels = authenticatedChannels;
		this.anonymousChannels = anonymousChannels;
		this.serverBootstrap = serverBootstrap;
		
	}

	public void start() {

		serverBootstrap.setPipelineFactory(channelPipelineFactory);
		serverBootstrap.setOption("child.tcpNoDelay", true);
		serverBootstrap.setOption("child.keepAlive", true);

		serverChannel = serverBootstrap.bind(new InetSocketAddress(port));
		logger.info("starting tcp server on port " + port);
		System.out.println("starting tcp server on port " + port);
	}

	public void stop() {
		logger.info("stopping server");
		ChannelGroupFuture closeFuture = authenticatedChannels.close();
		closeFuture.awaitUninterruptibly();
		channelFactory.releaseExternalResources();
		ChannelGroupFuture close = anonymousChannels.close();
		close.awaitUninterruptibly();

		logger.info("tcp server stopped");
	}

	public void putWaiting(Channel channel) {
		anonymousChannels.add(channel);
	}
	
	public void putAuhtenticated(int channelId){
		Channel chan = anonymousChannels.find(channelId);
		anonymousChannels.remove(chan);
		authenticatedChannels.add(chan);
	}
	
	public Channel getAuthenticatedChannel(int channelId){
		return authenticatedChannels.find(channelId);
	}
	
	public void sendToChannel(int channelId, Message message) {
		Channel channel = authenticatedChannels.find(channelId);
		if (channel != null) {
			channel.write(message);
		}
	}

	public void sendToAll(Message message) {
		Object[] chanArray = authenticatedChannels.toArray();

		for (Object o : chanArray) {
			Channel chan = (Channel) o;
			chan.write(message);
		}
	}

	public void sendToSome(Message message, int[] channelIds) {
		for (int i : channelIds) {
			Channel c = null;
			if ((c = authenticatedChannels.find(c.getId())) != null) {
				c.write(message);
			}
		}
	}

	public void onApplicationEvent(PlayerConnectedEvent event) {
		anonymousChannels.add(event.getChannel());
		logger.info("anonumous player added");
	}
}
