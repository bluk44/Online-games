package org.games.online.tcpservice.server;

import org.apache.log4j.Logger;
import org.games.online.events.PlayerConnectedEvent;
import org.games.online.events.PlayerDisconnectedEvent;
import org.games.online.message.Message;
import org.games.online.message.MessageProcessor;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

public class GameServerHandler extends SimpleChannelHandler implements
		ApplicationEventPublisherAware {

	private MessageProcessor messageProcessor;
	private Logger logger = Logger.getLogger(getClass());
	private ApplicationEventPublisher publisher;
	
	public void setMessageProcessor(MessageProcessor messageProcessor){
		this.messageProcessor = messageProcessor;
	}
	
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
		if (e.getMessage() != null) {
			Message message = (Message) e.getMessage();
			messageProcessor.processMessage(ctx.getChannel().getId(), message);
		}
	}

	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
		System.out.println("channel connected");

	//	publisher.publishEvent(new PlayerConnectedEvent(this, e.getChannel()));
		logger.info("PlayerConnectedEvent channelId: " + e.getChannel().getId());
	}

	@Override
	public void channelDisconnected(ChannelHandlerContext ctx,
			ChannelStateEvent e) {
//		publisher.publishEvent(new PlayerDisconnectedEvent(this, e.getChannel()));
		logger.info("PlayerDisconnectedEvent channelId: " + e.getChannel().getId());
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e)
			throws Exception {
		logger.error("server fuck up: " + e);
		super.exceptionCaught(ctx, e);
	}

	public void setApplicationEventPublisher(
			ApplicationEventPublisher applicationEventPublisher) {
		publisher = applicationEventPublisher;
	}
}
