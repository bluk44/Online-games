package org.games.online.events;

import org.jboss.netty.channel.Channel;
import org.springframework.context.ApplicationEvent;
/**
 * 
 * @author boodex
 * This event occurs when server accepts connection 
 *
 */
public class PlayerConnectedEvent extends ApplicationEvent {

	private Channel channel;

	public PlayerConnectedEvent(Object source, Channel channel) {
		super(source);
		this.channel = channel;
	}

	public Channel getChannel() {
		return channel;
	}
}
