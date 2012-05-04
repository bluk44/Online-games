package org.games.online.model.room;

import java.util.HashMap;
import java.util.Map;

public class RoomManager {
	Map<Integer, Room> roomIdRoomMap = new HashMap<Integer, Room>();

	public void createNewRoom(String roomName) {
		Room room = new Room(roomName);
		synchronized (roomIdRoomMap) {
			roomIdRoomMap.put(room.getRoomId(), room);
		}
	}
	
	public Room getRoom(int roomId){
		return roomIdRoomMap.get(roomId);
	}
	
	@Override
	public String toString() {
		return "roomManager";
	}
}
