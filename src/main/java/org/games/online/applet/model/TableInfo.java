package org.games.online.applet.model;

import java.util.ArrayList;
import java.util.Collection;

public class TableInfo {
	int tableId;
	Collection<PlayerInfo> playersInRoom;
	
	public TableInfo(){
		tableId = 0;
		playersInRoom = new ArrayList<PlayerInfo>();
	}
	
	public TableInfo(Collection<PlayerInfo> playersInRoom, int tableId){
		this.playersInRoom = playersInRoom;
		this.tableId = tableId;
	}
	
	@Override
	public String toString() {
		String string = "" + tableId + ": ";
		for(PlayerInfo i : playersInRoom){
			string = string + i.toString() + " ";
		}
		return string;
	}
	
}
