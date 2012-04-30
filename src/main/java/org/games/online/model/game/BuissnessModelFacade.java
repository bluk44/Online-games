package org.games.online.model.game;

import java.util.HashMap;
import java.util.Map;

import org.games.onlie.player.Player;
import org.games.online.model.room.Room;
import org.games.online.model.table.Table;

public class BuissnessModelFacade {
	Map<Integer, Player> playerIdPlayerMap = new HashMap<Integer, Player>();
	Map<Integer, Integer> channelIdPlayerIdMap = new HashMap<Integer, Integer>();
	Object playerMapLock = new Object();

	Map<Integer, Table> tableMap = new HashMap<Integer, Table>();

	Map<Integer, Room> roomMap = new HashMap<Integer, Room>();

	public void putPlayer(Player player) {
		int playerId = player.getPlayerId();
		int channelId = player.getChannel().getId();

		synchronized (playerMapLock) {
			playerIdPlayerMap.put(playerId, player);
			channelIdPlayerIdMap.put(channelId, playerId);
		}
	}

	public Player getPlayerById(int playerId) {
		synchronized (playerMapLock) {
			return playerIdPlayerMap.get(playerId);
		}
	}

	public Player getPlayerByChannelId(int channelId) {
		int playerId;
		synchronized (playerMapLock) {
			playerId = channelIdPlayerIdMap.get(channelId);
		}
		return getPlayerById(playerId);
	}

	public void deletePlayerByPlayerId(int playerId) {
		// delete from player map
		Player player;
		synchronized (playerMapLock) {
			int channelId = playerIdPlayerMap.get(playerId).getChannel()
					.getId();
			player = playerIdPlayerMap.remove(playerId);
			channelIdPlayerIdMap.remove(channelId);
		}

		// delete from room
		if (player.getRoom() != null) {
			Room room = player.getRoom();
			synchronized (room) {
				room.removePlayer(player);
				// send message
			}
		}
	}

	public void deletePlayerByChannelId(int channelId) {
		int playerId;
		synchronized (playerMapLock) {
			playerId = channelIdPlayerIdMap.get(channelId);
		}
		deletePlayerByPlayerId(playerId);
	}

	public void changeRoom(Player player, int newRoomId) {
		// delete from old room
		if (player.getRoom() != null) {
			Room old = player.getRoom();
			synchronized (old) {
				old.removePlayer(player);
				// send message TODO
			}
		}
		// join new room
		Room newRoom = roomMap.get(newRoomId);
		if (newRoom != null) {
			synchronized (newRoom) {
				newRoom.addPlayer(player);
				System.out.println(player.getName() + "joins room: "
						+ newRoom.getRoomName());
				// send message TODO
				// acquire room data send message to player
				player.getChannel().write(newRoom.generateRoomInfo());
				System.out.println("wrote room info to Player's channel");
			}
		}
	}

	public void changeTable(Player player, int tableId){
		
		synchronized(player.getRoom()){
			if(player.getTable() != null){
				player.getTable().removePlayer(player);
				// TODO send message "player leaves room"
			}
		}
		Table newTable;
		synchronized(tableMap){
			
		}
	}
	
	public void putNewTable(int roomId, Table table) {
		Room room;
		synchronized (roomMap) {
			room = roomMap.get(roomId);
		}
		if (room != null) {
			synchronized (room) {
				if (table != null) {
					room.addTable(table);
					tableMap.put(table.getId(), table);
					// send message
				}
			}
		}
	}

	public void deleteTable(Table table){
		// TODO
		// remove from table map
		// remove from room
		// remove from players in table;
	}
	
	public Room getRoom(int roomId) {
		synchronized (roomMap) {
			return roomMap.get(roomId);
		}
	}

	public void putRoom(Room newRoom) {
		synchronized (roomMap) {
			roomMap.put(newRoom.getRoomId(), newRoom);
		}
	}

}