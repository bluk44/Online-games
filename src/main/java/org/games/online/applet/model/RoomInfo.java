package org.games.online.applet.model;

import java.util.Collection;

import org.games.online.message.Message;

public class RoomInfo implements Message {
	String roomName;
	int roomId;

	Collection<PlayerInfo> players;
	Collection<TableInfo> tables;

	public RoomInfo(String roomName, int roomId) {
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

	@Override
	public String toString() {
		String roomInfo = "room name: " + roomName + " id: " + roomId + "\n";
		roomInfo += "players in room: \n";
		for (PlayerInfo pi : players) {
			roomInfo = roomInfo + pi + "\n";
		}
		roomInfo += "tables in room: \n";
		for (TableInfo ti : tables) {
			roomInfo = roomInfo + ti + "\n";
		}
		return roomInfo;
	}
}
