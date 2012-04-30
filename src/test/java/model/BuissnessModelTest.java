package model;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.games.onlie.player.Player;
import org.games.online.model.game.BuissnessModelFacade;
import org.games.online.model.room.Room;
import org.jboss.netty.channel.Channel;

public class BuissnessModelTest extends TestCase {
	Player player1, player2, player3;
	Room room;
	BuissnessModelFacade model;
	Logger logger = Logger.getLogger(getClass());

	@Override
	protected void setUp() throws Exception {
		player1 = new Player();
		player2 = new Player();
		player3 = new Player();
		player1.setName("player1");
		player2.setName("player2");
		player3.setName("player3");
		player1.setPlayerId(1);
		player2.setPlayerId(2);
		player3.setPlayerId(3);
		Channel chan1 = new ChannelMock(1), chan2 = new ChannelMock(2), chan3 = new ChannelMock(
				3);
		player1.setChannel(chan1);
		player2.setChannel(chan2);
		player3.setChannel(chan3);

		room = new Room("aaa");
		model = new BuissnessModelFacade();
		model.putRoom(room);
		model.putPlayer(player1);
		model.putPlayer(player2);
		model.putPlayer(player3);

		logger.info("players set up");

	}

	public void test() {
		Room r = model.getRoom(room.getRoomId());
		assertEquals(true, (r == room));
		logger.info(r.generateRoomInfo());
		model.changeRoom(player1, 1);
		model.changeRoom(player2, 1);
		model.changeRoom(player3, 1);
		logger.info(r.generateRoomInfo());
		logger.info(player1.getRoom());
		model.changeRoom(player1, 0);
		logger.info(r.generateRoomInfo());
		logger.info(player1.getRoom());		
	}
}
