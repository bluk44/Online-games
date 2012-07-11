package org.games.online.message;

import org.games.online.model.player.Player;
import org.games.online.model.player.PlayerManager;
import org.games.online.model.room.Room;
import org.games.online.model.room.RoomManager;
import org.games.online.model.table.Table;
import org.games.online.tcpservice.server.GameServer;

public class MessageProcessor {
	private PlayerManager playerManager;
	private RoomManager roomManager;

	private GameServer gameServer;

	public PlayerManager getPlayerManager() {
		return playerManager;
	}

	public void setPlayerManager(PlayerManager playerManager) {
		this.playerManager = playerManager;
	}
	
	public RoomManager getRoomManager(){
		return roomManager;
	}
	
	public void setRoomManager(RoomManager roomManager){
		this.roomManager = roomManager;
	}
		
	public GameServer getGameServer() {
		return gameServer;
	}

	public void setGameServer(GameServer gameServer) {
		this.gameServer = gameServer;
	}
	

	
	public void processMessage(int channelId, Message message) {
		if (message instanceof PlayerAuthenticationData) {
			onAuthenticationMessage(channelId,
					(PlayerAuthenticationData) message);
		} else if (message instanceof ChangeRoomMessage) {
			onChangeRoomMessage(channelId, (ChangeRoomMessage) message);
		} else if(message instanceof ChangeTableMessage){
			onChangeTableMessage(channelId, (ChangeTableMessage)message);
		}
	}

	private void onAuthenticationMessage(int channelId,
			PlayerAuthenticationData authData) {
		// code for authentication
		System.out.println("received PlayerAuthenticationData /n");
		boolean authSuccessfull = true;
		if (authSuccessfull) {

			gameServer.putAuhtenticated(channelId);
			gameServer.sendToChannel(channelId, new HelloMessage());
			Player newPlayer = new Player();
			newPlayer.setChannel(gameServer.getAuthenticatedChannel(channelId));
			newPlayer.setName(authData.playerName);
			playerManager.putPlayer(newPlayer);
		} else {
			// player authentication failed TODO
		}
	}

	private void onChangeRoomMessage(int channelId, ChangeRoomMessage message) {
		System.out.println("received ChangeRoomMessage \n");
		Player player = playerManager.getPlayerByChannelId(channelId);
		
		Room oldRoom = player.getRoom();
		if (oldRoom != null) {
			synchronized (oldRoom) {
				oldRoom.removePlayer(player);
				// send message TODO
			}
		}
		Room newRoom = roomManager.getRoom(message.getRoomId());
		if(newRoom != null){
			synchronized(newRoom){
				newRoom.addPlayer(player);
				// send message TODO
				// send info on new room to player
				player.getChannel().write(newRoom.generateRoomInfo());
				System.out.println("wrote room info to Player's channel");
			}
		}
	}
	
	private void onChangeTableMessage(int channelId, ChangeTableMessage message){
		System.out.println("received ChangeTableMessage \n");
		Player player = playerManager.getPlayerByChannelId(channelId);
		Room room = player.getRoom();
		Table oldTable = player.getTable();
		
		synchronized(room){
			if(oldTable != null){
				oldTable.removePlayer(player);
				// TODO send message
			}
			Table newTable = room.getTable(message.getTableId());
			if(newTable != null){
				newTable.addPlayer(player);
				// TODO send message
			}
		}
	}
}
