package org.games.online.applet.model;

import java.io.Serializable;

import org.games.online.model.player.Player;

public class PlayerInfo implements Serializable{
	
	int playerId;
	String playerName;
	
	RoomInfo refRoom;
	TableInfo refTable;
	
	public PlayerInfo(){}
	
	public PlayerInfo(int playerId, String playerName){
		this.playerId = playerId;
		this.playerName = playerName;
	}
	public int getPlayerId() {
		return playerId;
	}
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public RoomInfo getRefRoom() {
		return refRoom;
	}
	public void setRefRoom(RoomInfo refRoom) {
		this.refRoom = refRoom;
	}
	public TableInfo getRefTable() {
		return refTable;
	}
	public void setRefTable(TableInfo refTable) {
		this.refTable = refTable;
	}
	
	@Override
	public String toString() {
		
		return playerName;
	}
}
