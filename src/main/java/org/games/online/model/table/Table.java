package org.games.online.model.table;

import java.util.ArrayList;
import java.util.Collection;

import org.games.onlie.player.Player;
import org.games.online.model.room.Room;

public class Table {
	
	static int tableCounter;
    int tableId;
    Room room;
	Collection<Player> players;
	public Table(){
		synchronized(this){
			++tableCounter;
			tableId = tableCounter;
		}
		players = new ArrayList<Player>();
	}
	public int getId() {
		return tableId;
	}

	public Collection<Player> getPlayers() {
		return players;
	}
		
	public void removePlayer(Player player){
		players.remove(player);
		player.setRoom(null);
	}
}
