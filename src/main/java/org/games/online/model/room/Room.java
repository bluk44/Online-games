package org.games.online.model.room;

import java.util.ArrayList;
import java.util.Collection;

import org.games.onlie.player.Player;
import org.games.online.applet.model.PlayerInfo;
import org.games.online.applet.model.RoomInfo;
import org.games.online.applet.model.TableInfo;
import org.games.online.model.game.Game;
import org.games.online.model.table.Table;

public class Room {
	static int roomCounter;
	int roomId;
	String roomName;
	Game game;
	
	public Room(String roomName){
		synchronized(this){
		++roomCounter;
		roomId = roomCounter;
		}
		this.roomName = roomName;
		players = new ArrayList<Player>();
		tables = new ArrayList<Table>();
	}
	
	Collection<Player> players;
	Collection<Table> tables;
	
	public void removePlayer(Player player){
		players.remove(player);
		player.setRoom(null);
		
		if(player.getTable()!=null){
			player.getTable().removePlayer(player);
		}
		// send message TODO
	}
	
	public void addPlayer(Player player){
		players.add(player);
		player.setRoom(this);
	}
	
	public RoomInfo generateRoomInfo(){
		Collection<PlayerInfo> playersInfo = new ArrayList<PlayerInfo>();
		for (Player p : players) {
			PlayerInfo pInfo = new PlayerInfo(p.getPlayerId(), p.getName());
			playersInfo.add(pInfo);
		}
		
		Collection<TableInfo> tablesInfo = new ArrayList<TableInfo>();
		for (Table t : tables) {
			Collection<Integer> playerIds = new ArrayList<Integer>();
			Collection<Player> playersInTable = t.getPlayers();
			for(Player p : playersInTable){
				playerIds.add(p.getPlayerId());
			}
			tablesInfo.add(new TableInfo(t.getId(), playerIds));
		}
		RoomInfo roomInfo = new RoomInfo(roomName, roomId);
		roomInfo.setPlayers(playersInfo);
		roomInfo.setTables(tablesInfo);
		return roomInfo;
	}
	
	public void addTable(Table table){
		tables.add(table);
	}
	
	public void removeTable(Table table){
		tables.remove(table);
	}
	
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

	@Override
	public String toString() {
		return "Room [roomId=" + roomId + ", roomName=" + roomName + "]";
	}
	

}
