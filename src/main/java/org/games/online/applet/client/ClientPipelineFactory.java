package org.games.online.applet.client;

import static org.jboss.netty.channel.Channels.pipeline;

import org.games.online.message.FrameDelimiter;
import org.games.online.message.MessageDecoder;
import org.games.online.message.MessageEncoder;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.handler.codec.frame.DelimiterBasedFrameDecoder;


public class ClientPipelineFactory implements ChannelPipelineFactory {

	public ClientPipelineFactory(SimpleChannelHandler clientChannelHandler) {
		this.clientChannelHandler = clientChannelHandler;
	}

	public ChannelPipeline getPipeline() throws Exception {
		ChannelPipeline pipeline = pipeline();
		pipeline.addLast("frame", new DelimiterBasedFrameDecoder(8192,
				FrameDelimiter.get()));
		pipeline.addLast("encoder", new MessageEncoder());
		pipeline.addLast("decoder", new MessageDecoder());
		pipeline.addLast("handler", clientChannelHandler);
		return pipeline;
	}

	SimpleChannelHandler clientChannelHandler;
}
