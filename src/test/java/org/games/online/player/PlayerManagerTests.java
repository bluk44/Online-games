package org.games.online.player;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.games.onlie.player.AbstractPlayerFactory;
import org.games.onlie.player.Player;
import org.games.onlie.player.PlayerFactory;
import org.games.onlie.player.PlayerManager;
import org.junit.Before;
import org.junit.Test;

import foo.FakeChannel;



public class PlayerManagerTests{
	AbstractPlayerFactory factory;
	PlayerManager manager;
	Player p1, p2, p3;
	
	@Before
	public void setUp() throws Exception {
		factory = new PlayerFactory();
		manager = new PlayerManager(new HashMap<Integer, Player>(), new HashMap<Integer, Integer>());
		FakeChannel chan1 = new FakeChannel(), chan2 = new FakeChannel(), chan3 = new FakeChannel();
		chan1.setChannelId(1);
		chan2.setChannelId(2);
		chan3.setChannelId(1);
		p1 = factory.createPlayer(1, "aaa", chan1);
		p2 = factory.createPlayer(1, "bbb", chan2);
		p3 = factory.createPlayer(1, "ccc", chan3);
	}
	
	@Test
	public void test(){
//		manager.putPlayer(p1);
//		manager.putPlayer(p2);
//		assertEquals("aaa", manager.getPlayer(1).getName());
//		assertEquals("bbb", manager.getPlayer(2).getName());
//		manager.removePlayer(1);
//		manager.removePlayer(manager.getPlayerId(2));
	}
	

	protected void tearDown() throws Exception {

	}
}
