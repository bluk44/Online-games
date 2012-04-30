package org.games.online.message;

import org.games.onlie.player.Player;
import org.games.online.model.game.BuissnessModelFacade;
import org.games.online.tcpservice.server.GameServer;

public class MessageProcessor {
	private BuissnessModelFacade facade;
	private GameServer gameServer;
	public BuissnessModelFacade getFacade() {
		return facade;
	}

	public void setFacade(BuissnessModelFacade facade) {
		this.facade = facade;
	}
	
	public GameServer getGameServer() {
		return gameServer;
	}

	public void setGameServer(GameServer gameServer) {
		this.gameServer = gameServer;
	}
	
	public void processMessage(int channelId, Message message){
		if(message instanceof PlayerAuthenticationData){
			onAuthenticationMessage(channelId, (PlayerAuthenticationData)message);
		} else if(message instanceof ChangeRoomMessage){
			onChangeRoomMessage(channelId, (ChangeRoomMessage) message);
		} 
	}
	
	private void onAuthenticationMessage(int channelId, PlayerAuthenticationData authData){
		// code for authentication
		System.out.println("received PlayerAuthenticationData /n");
		boolean authSuccessfull = true;
		if(authSuccessfull){
			
			gameServer.putAuhtenticated(channelId);
			Player newPlayer = new Player();
			newPlayer.setChannel(gameServer.getAuthenticatedChannel(channelId));
			newPlayer.setName(authData.playerName);
			facade.putPlayer(newPlayer);
		} else {
		// player authentication failed TODO
		}
	}
	
	private void onChangeRoomMessage(int channelId, ChangeRoomMessage message){
		System.out.println("received ChangeRoomMessage \n");
		Player player = facade.getPlayerById(channelId);
		facade.changeRoom(player, message.getRoomId());
	}	
}
