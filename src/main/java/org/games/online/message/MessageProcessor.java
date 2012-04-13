package org.games.online.message;

import org.games.onlie.player.Player;
import org.games.onlie.player.PlayerManager;
import org.games.online.model.room.RoomManager;
import org.games.online.tcpservice.server.GameServer;

public class MessageProcessor {
	private PlayerManager playerManager;
	private GameServer gameServer;
	private RoomManager roomManager;

	public PlayerManager getPlayerManager() {
		return playerManager;
	}

	public void setPlayerManager(PlayerManager playerManager) {
		this.playerManager = playerManager;
	}

	public GameServer getGameServer() {
		return gameServer;
	}

	public void setGameServer(GameServer gameServer) {
		this.gameServer = gameServer;
	}

	public RoomManager getRoomManager() {
		return roomManager;
	}

	public void setRoomManager(RoomManager roomManager) {
		this.roomManager = roomManager;
	}
	
	public void processMessage(int channelId, Message message){
		if(message instanceof PlayerAuthenticationData){
			onAuthenticationMessage(channelId, (PlayerAuthenticationData)message);
		} else if(message instanceof ChangeRoomMessage){
			
		}
	}
	
	private void onAuthenticationMessage(int channelId, PlayerAuthenticationData authData){
		// code for authentication
		boolean authSuccessfull = true;
		if(authSuccessfull){
			gameServer.putAuhtenticated(channelId);
			Player newPlayer = new Player();
			newPlayer.setChannel(gameServer.getAuthenticatedChannel(channelId));
			newPlayer.setName(authData.playerName);
			playerManager.putPlayer(newPlayer);
		} else {
		// player authentication failed
		}
	}
	
	private void onChangeRoomMessage(int channelId, ChangeRoomMessage message){
		Player player = playerManager.getPlayerByChannelId(channelId);
		message.getRoomId();
	}
}
