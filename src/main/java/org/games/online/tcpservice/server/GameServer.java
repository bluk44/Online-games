package org.games.online.tcpservice.server;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.group.ChannelGroup;
import org.jboss.netty.channel.group.ChannelGroupFuture;
import org.jboss.netty.channel.group.DefaultChannelGroup;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

import protocol.Message;
import protocol.PlayersInRoom;
import protocol.PlayerConnected;
import chat.PlayerObject;
import chat.Room;

public class GameServer {
	private static int DEFAULT_PORT = 10000;
	private ChannelFactory channelFactory;
	private ServerBootstrap serverBootstrap;
	private Room chatRoom = new Room("BDSM");

	public static final ChannelGroup allChannels = new DefaultChannelGroup("all clients");
	public static final ChannelGroup awaiting = new DefaultChannelGroup("awaiting");
	private Logger logger = Logger.getLogger(getClass());

	public void start() {
		channelFactory = new NioServerSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool());

		serverBootstrap = new ServerBootstrap(channelFactory);
		serverBootstrap.setPipelineFactory(new GameServerPipelineFactory(this));
		serverBootstrap.setOption("child.tcpNoDelay", true);
		serverBootstrap.setOption("child.keepAlive", true);

		Channel serverChannel = serverBootstrap.bind(new InetSocketAddress(DEFAULT_PORT));
		logger.info("server started");

		// allChannels.add(serverChannel);
	}

	public void stop() {
		logger.info("stopping server");
		ChannelGroupFuture closeFuture = allChannels.close();
		closeFuture.awaitUninterruptibly();
		channelFactory.releaseExternalResources();
		logger.info("server stopped");
	}

	public void putWaiting(Channel channel) {
		awaiting.add(channel);
	}

	public void authenticate(int channelId, String playerName) {
		Channel chan = awaiting.find(channelId);
		awaiting.remove(chan);
		allChannels.add(chan);
		// create new player
		PlayerObject player = new PlayerObject();
		player.setPlayerName(playerName);
		player.setChannelId(channelId);
		// add to chatroom
		chatRoom.addPlayer(channelId, player);
		sendTo(channelId, new PlayersInRoom(chatRoom.getPlayers()));
		sendSome(new PlayerConnected(player, channelId), new int[] { channelId });
	}

	public void sendTo(int playerId, Message message) {
		Channel channel = allChannels.find(playerId);
		if (channel != null) {
			channel.write(message);
		}
	}

	public void sendAll(Message message) {
		sendSome(message, new int[] {});
	}

	public void sendSome(Message message, int[] exclude) {
		Object[] chanArray = allChannels.toArray();
		for (Object o : chanArray) {
			Channel c = (Channel)o;
			boolean ok = true;
			for (int i : exclude) {
				if (c.getId() == i) {
					ok = false;
					break;
				}
				if (ok) {
					c.write(message);
				}
			}
		}
	}
	
	public void removePlayer(int playerId){
		chatRoom.removePlayer(playerId);
		
	}
}
