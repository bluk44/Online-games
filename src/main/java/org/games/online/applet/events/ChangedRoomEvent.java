package org.games.online.applet.events;

import org.games.online.applet.model.RoomInfo;

public class ChangedRoomEvent extends Event {
	RoomInfo room;
	public void setRoomInfo(RoomInfo room){
		this.room = room;
	}
	public RoomInfo getRoomInfo(){
		return room;
	}
}
