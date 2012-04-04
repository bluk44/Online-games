package org.games.online.events;

import org.jboss.netty.channel.Channel;
import org.springframework.context.ApplicationEvent;

public class PlayerDisconnectedEvent extends ApplicationEvent {
	private Channel channel;

	public PlayerDisconnectedEvent(Object source, Channel channel) {
		super(source);
		this.channel = channel;
	}

}
