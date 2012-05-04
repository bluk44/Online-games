package org.games.online.model.player;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author boodex
 * 
 *         manages players and they channels holds mapping between playerIds and
 *         players holds mapping between channelIds and players
 * 
 */
public class PlayerManager {
	Map<Integer, Player> playerIdPlayerMap;
	Map<Integer, Integer> channelIdPlayerIdMap;

	public PlayerManager(){
		playerIdPlayerMap = new HashMap<Integer, Player>();
		channelIdPlayerIdMap = new HashMap<Integer, Integer>();
	}
	
	public PlayerManager(Map<Integer, Player> playerIdPlayerMap,
			Map<Integer, Integer> channelIdPlayerIdMap) {
		super();
		this.playerIdPlayerMap = playerIdPlayerMap;
		this.channelIdPlayerIdMap = channelIdPlayerIdMap;
	}

	public synchronized Player getPlayerByPlayerId(int playerId) {

		return playerIdPlayerMap.get(playerId);
	}

	public synchronized Player getPlayerByChannelId(int channelId) {
		Integer playerId = channelIdPlayerIdMap.get(channelId);
		if (playerId != null) {
			return playerIdPlayerMap.get(playerId);
		}
		return null;
	}

	public synchronized int getPlayerId(Integer channelId) {
		return channelIdPlayerIdMap.get(channelId);
	}

	public synchronized void putPlayer(Player player) {
		int playerId = player.getPlayerId();
		int channelId = player.getChannel().getId();

		playerIdPlayerMap.put(playerId, player);

		channelIdPlayerIdMap.put(channelId, playerId);

	}

	public synchronized void deletePlayerByPlayerId(int playerId) {
		int channelId = playerIdPlayerMap.get(playerId).getChannel().getId();
		playerIdPlayerMap.remove(playerId);
		channelIdPlayerIdMap.remove(channelId);

	}
	
	public synchronized void deletePlayerByChannelId(int channelId) {
		int playerId = channelIdPlayerIdMap.get(channelId);
		playerIdPlayerMap.remove(playerId);
		channelIdPlayerIdMap.remove(channelId);

	}
	
	public synchronized void deleteAllPlayers(){
		playerIdPlayerMap.clear();
		channelIdPlayerIdMap.clear();
	}
}
