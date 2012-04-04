package org.games.online.player;

import java.net.SocketAddress;

import org.games.onlie.player.AbstractPlayerFactory;
import org.games.onlie.player.Player;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelConfig;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipeline;

public class TestPlayerFactory extends AbstractPlayerFactory{

	public Player createPlayer(int playerId, String playerName, int channelId) {
		Player player = new Player();
		player.setPlayerId(playerId);
		player.setName(playerName);
			Channel chan = new Channel(){
				private int channelId;
				public int compareTo(Channel o) {
					// TODO Auto-generated method stub
					return 0;
				}

				public Integer getId() {
					// TODO Auto-generated method stub
					return channelId;
				}

				public ChannelFactory getFactory() {
					// TODO Auto-generated method stub
					return null;
				}

				public Channel getParent() {
					// TODO Auto-generated method stub
					return null;
				}

				public ChannelConfig getConfig() {
					// TODO Auto-generated method stub
					return null;
				}

				public ChannelPipeline getPipeline() {
					// TODO Auto-generated method stub
					return null;
				}

				public boolean isOpen() {
					// TODO Auto-generated method stub
					return false;
				}

				public boolean isBound() {
					// TODO Auto-generated method stub
					return false;
				}

				public boolean isConnected() {
					// TODO Auto-generated method stub
					return false;
				}

				public SocketAddress getLocalAddress() {
					// TODO Auto-generated method stub
					return null;
				}

				public SocketAddress getRemoteAddress() {
					// TODO Auto-generated method stub
					return null;
				}

				public ChannelFuture write(Object message) {
					// TODO Auto-generated method stub
					return null;
				}

				public ChannelFuture write(Object message,
						SocketAddress remoteAddress) {
					// TODO Auto-generated method stub
					return null;
				}

				public ChannelFuture bind(SocketAddress localAddress) {
					// TODO Auto-generated method stub
					return null;
				}

				public ChannelFuture connect(SocketAddress remoteAddress) {
					// TODO Auto-generated method stub
					return null;
				}

				public ChannelFuture disconnect() {
					// TODO Auto-generated method stub
					return null;
				}

				public ChannelFuture unbind() {
					// TODO Auto-generated method stub
					return null;
				}

				public ChannelFuture close() {
					// TODO Auto-generated method stub
					return null;
				}

				public ChannelFuture getCloseFuture() {
					// TODO Auto-generated method stub
					return null;
				}

				public int getInterestOps() {
					// TODO Auto-generated method stub
					return 0;
				}

				public boolean isReadable() {
					// TODO Auto-generated method stub
					return false;
				}

				public boolean isWritable() {
					// TODO Auto-generated method stub
					return false;
				}

				public ChannelFuture setInterestOps(int interestOps) {
					// TODO Auto-generated method stub
					return null;
				}

				public ChannelFuture setReadable(boolean readable) {
					// TODO Auto-generated method stub
					return null;
				}
				public void setChannelId(int channelId){
					channelId = channelId;
				}
			};
			
		player.setChannel(chan);
		return player;
	}

	@Override
	public Player createPlayer(int playerId, String playerName, Channel channel) {
		// TODO Auto-generated method stub
		return null;
	}


}
