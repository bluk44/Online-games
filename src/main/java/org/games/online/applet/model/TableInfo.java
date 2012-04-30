package org.games.online.applet.model;

import java.io.Serializable;
import java.util.Collection;

import org.games.onlie.player.Player;
import org.games.online.model.table.Table;

public class TableInfo implements Serializable{
	int tableId;
	Collection<Integer> playerIds;
	RoomInfo roomRef;
	
	public TableInfo(int tableId, Collection<Integer> playerIds){
		this.tableId = tableId;
		this.playerIds = playerIds;
	}

	public void setRoomRef(RoomInfo roomRef) {
		this.roomRef = roomRef;
	}

	public RoomInfo getRoomRef(){
		return roomRef;
	}
	
	@Override
	public String toString() {
		return "" + tableId;
	}

}
