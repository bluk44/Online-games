package org.games.online.applet.events;

public interface ChangedRoomEventListener extends EventListener<ChangedRoomEvent> {

	public void eventOccurred(Object source, ChangedRoomEvent event);

}
