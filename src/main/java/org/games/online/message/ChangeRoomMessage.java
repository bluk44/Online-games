package org.games.online.message;

public class ChangeRoomMessage implements Message {
	
	int roomId;
	public int getRoomId(){
		return roomId;
	}
	public void setRoomId(int roomId){
		this.roomId = roomId;
	}
}
