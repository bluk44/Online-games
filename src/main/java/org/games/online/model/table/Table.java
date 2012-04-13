package org.games.online.model.table;

import java.util.Collection;

import org.games.onlie.player.Player;

public class Table {

	int tableId;
	Collection<Player> players;
	public int getTableId() {
		return tableId;
	}
	public void setTableId(int tableId) {
		this.tableId = tableId;
	}
	public Collection<Player> getPlayers() {
		return players;
	}
	public void setPlayers(Collection<Player> players) {
		this.players = players;
	}
	
}
