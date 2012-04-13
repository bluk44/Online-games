package org.games.online.tcpservice.server;

import static org.jboss.netty.channel.Channels.pipeline;

import org.games.online.message.FrameDelimiter;
import org.games.online.message.MessageDecoder;
import org.games.online.message.MessageEncoder;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.handler.codec.frame.DelimiterBasedFrameDecoder;

public class GameServerPipelineFactory implements ChannelPipelineFactory {
	
	private SimpleChannelHandler gameServerHandler;
	
	public void setGameServerHandler(SimpleChannelHandler gameServerHandler){
		this.gameServerHandler = gameServerHandler;
	}
	
	public ChannelPipeline getPipeline() throws Exception {
		
		ChannelPipeline pipeline = pipeline();
		pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192, FrameDelimiter.get()));
		pipeline.addLast("message decoder", new MessageDecoder());
		pipeline.addLast("message encoder", new MessageEncoder());
		pipeline.addLast("handler", gameServerHandler);
		
		return pipeline;
	}

}
