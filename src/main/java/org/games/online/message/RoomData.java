package org.games.online.message;

import java.util.Collection;

import org.games.online.applet.model.PlayerInfo;
import org.games.online.applet.model.RoomInfo;
import org.games.online.applet.model.TableInfo;

public class RoomData implements Message {
	String roomName;
	int roomId;
	
	Collection<PlayerInfo> players;
	Collection<TableInfo> tables;
	
	public RoomData(String roomName, int roomId){
		this.roomName = roomName;
		this.roomId = roomId;
	}
	
	public String getRoomName() {
		return roomName;
	}
	public int getRoomId() {
		return roomId;
	}
	public Collection<PlayerInfo> getPlayers() {
		return players;
	}
	public void setPlayers(Collection<PlayerInfo> players) {
		this.players = players;
	}
	public Collection<TableInfo> getTables() {
		return tables;
	}
	public void setTables(Collection<TableInfo> tables) {
		this.tables = tables;
	}
}
