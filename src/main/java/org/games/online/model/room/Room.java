package org.games.online.model.room;

import java.util.Collection;

import org.games.onlie.player.Player;
import org.games.online.model.game.Game;
import org.games.online.model.table.Table;

public class Room {
	int roomId;
	String roomName;
	Game game;
	
	public Room(int roomId, String roomName){
		this.roomId = roomId;
		this.roomName = roomName;
	}
	
	Collection<Player> players;
	Collection<Table> tables;
	
	public Collection<Player> getPlayers(){
		return players;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Collection<Table> getTables() {
		return tables;
	}

	public void setTables(Collection<Table> tables) {
		this.tables = tables;
	}
}
