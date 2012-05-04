package org.games.online.model.player;

import org.games.online.model.room.Room;
import org.games.online.model.table.Table;
import org.jboss.netty.channel.Channel;

public class Player {
	Channel channel;
	String name;
	int playerId;
	
	Room room;
	Table table;
	
	public Channel getChannel(){
		return channel;
	}
	public void setChannel(Channel channel){
		this.channel = channel;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPlayerId() {
		return playerId;
	}
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + playerId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (playerId != other.playerId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Player [name=" + name + ", playerId=" + playerId + "]";
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public Table getTable() {
		return table;
	}
	public void setTable(Table table) {
		this.table = table;
	}
	

}
