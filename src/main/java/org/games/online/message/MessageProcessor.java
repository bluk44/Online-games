package org.games.online.message;

import org.games.onlie.player.PlayerManager;
/**
 * 
 * @author boodex
 * 
 * processes messages from clients, used by server`s channel handler
 */
public class MessageProcessor {
	protected PlayerManager palyerManager;
	
	public void processMessage(Message message){
		
	}

	public PlayerManager getPalyerManager() {
		return palyerManager;
	}

	public void setPalyerManager(PlayerManager palyerManager) {
		this.palyerManager = palyerManager;
	}
}
