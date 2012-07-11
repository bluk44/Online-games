package org.games.online.message;

public class PlayerLeavesRoom implements Message{
	int playerId;

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	
}