package org.games.online.applet.events;

public interface EventListener<T extends Event> {
	void eventOccurred(Object source, T event);
}
