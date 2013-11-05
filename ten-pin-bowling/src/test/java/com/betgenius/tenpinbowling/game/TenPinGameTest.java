/**
 * 
 */
package com.betgenius.tenpinbowling.game;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.Spy;

import com.betgenius.tenpinbowling.frame.GameFrame;
import com.betgenius.tenpinbowling.frame.GameFrameImpl;
import com.betgenius.tenpinbowling.player.Player;
import com.betgenius.tenpinbowling.player.PlayerImpl;
import com.betgenius.tenpinbowling.util.DefinedConstants;

/**
 * @author Tony
 *
 */
public class TenPinGameTest {

	@Spy
	Player tonyPlayer = Mockito.spy(new PlayerImpl());

	@Spy
	Player frankPlayer = Mockito.spy(new PlayerImpl());
	Player[] players ={tonyPlayer,frankPlayer};
	private TenPinGame tenPinGame;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		Mockito.reset(players);

	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.betgenius.tenpinbowling.game.TenPinGame#getPlayers()}.
	 */
	@Test
	public void testGetPlayers() {
		((PlayerImpl)tonyPlayer).setPlayerName("Tony");
		((PlayerImpl)frankPlayer).setPlayerName("Frank");
		((PlayerImpl)tonyPlayer).setNumOfFrames(10);
		((PlayerImpl)frankPlayer).setNumOfFrames(10);
		tenPinGame = new TenPinGame(players,10);

		List<GameFrame> tonyFrames = new ArrayList<GameFrame>();
		for(int i=1; i <= 10; i++){
			tonyFrames.add(new GameFrameImpl(i));
		};
		tonyFrames.get(0).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 10);
		tonyFrames.get(1).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 3);
		tonyFrames.get(1).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 4);
		tonyFrames.get(2).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		tonyFrames.get(2).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		tonyFrames.get(3).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		tonyFrames.get(3).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		tonyFrames.get(4).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		tonyFrames.get(4).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		tonyFrames.get(5).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		tonyFrames.get(5).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		tonyFrames.get(6).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		tonyFrames.get(6).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		tonyFrames.get(7).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		tonyFrames.get(7).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		tonyFrames.get(8).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		tonyFrames.get(8).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		tonyFrames.get(9).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 7);
		tonyFrames.get(9).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 3);
		tonyFrames.get(9).setRollResult(DefinedConstants.TYPES_OF_ROLLS.THIRD_ROLL, 10);

		List<GameFrame> frankFrames = new ArrayList<GameFrame>();
		for(int i=1; i <= 10; i++){
			frankFrames.add(new GameFrameImpl(i));
		};

		frankFrames.get(0).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 10);
		frankFrames.get(1).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 3);
		frankFrames.get(1).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 4);
		frankFrames.get(2).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 4);
		frankFrames.get(2).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 6);
		frankFrames.get(2).setRollResult(DefinedConstants.TYPES_OF_ROLLS.THIRD_ROLL, 6);
		frankFrames.get(3).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 3);
		frankFrames.get(3).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		frankFrames.get(4).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		frankFrames.get(4).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		frankFrames.get(5).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		frankFrames.get(5).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		frankFrames.get(6).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		frankFrames.get(6).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		frankFrames.get(7).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		frankFrames.get(7).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		frankFrames.get(8).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		frankFrames.get(8).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		frankFrames.get(9).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 7);
		frankFrames.get(9).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 3);
		frankFrames.get(9).setRollResult(DefinedConstants.TYPES_OF_ROLLS.THIRD_ROLL, 10);

		when(tonyPlayer.getGameFrames()).thenReturn(tonyFrames);
		when(frankPlayer.getGameFrames()).thenReturn(frankFrames);

		HashMap<String, List<GameFrame>> resultsMap = tenPinGame.getPlayersResults();
		List<GameFrame> tonyReturnedFrames = resultsMap.get("Tony");
		List<GameFrame> frankReturnedFrames = resultsMap.get("Frank");
		assertTrue("It should be the Tony array",tonyReturnedFrames.hashCode() == tonyFrames.hashCode());
		assertTrue("It should be the Frank array",frankReturnedFrames.hashCode() == frankFrames.hashCode());
	}

	/**
	 * Test method for {@link com.betgenius.tenpinbowling.game.TenPinGame#getPlayersResults()}.
	 */
	@Test
	public void testGetPlayersResults() {
		((PlayerImpl)tonyPlayer).setPlayerName("Tony");
		((PlayerImpl)frankPlayer).setPlayerName("Frank");
		((PlayerImpl)tonyPlayer).setNumOfFrames(10);
		((PlayerImpl)frankPlayer).setNumOfFrames(10);
		tenPinGame = new TenPinGame(players,10);

		List<GameFrame> tonyFrames = new ArrayList<GameFrame>();
		for(int i=1; i <= 10; i++){
			tonyFrames.add(new GameFrameImpl(i));
		};

		tonyFrames.get(0).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 10);
		tonyFrames.get(1).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 3);
		tonyFrames.get(1).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 4);
		tonyFrames.get(2).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		tonyFrames.get(2).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		tonyFrames.get(3).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		tonyFrames.get(3).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		tonyFrames.get(4).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		tonyFrames.get(4).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		tonyFrames.get(5).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		tonyFrames.get(5).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		tonyFrames.get(6).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		tonyFrames.get(6).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		tonyFrames.get(7).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		tonyFrames.get(7).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		tonyFrames.get(8).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		tonyFrames.get(8).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		tonyFrames.get(9).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 7);
		tonyFrames.get(9).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 3);
		tonyFrames.get(9).setRollResult(DefinedConstants.TYPES_OF_ROLLS.THIRD_ROLL, 10);

		List<GameFrame> frankFrames = new ArrayList<GameFrame>();
		for(int i=1; i <= 10; i++){
			frankFrames.add(new GameFrameImpl(i));
		};

		frankFrames.get(0).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 10);
		frankFrames.get(1).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 3);
		frankFrames.get(1).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 4);
		frankFrames.get(2).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 4);
		frankFrames.get(2).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 6);
		frankFrames.get(2).setRollResult(DefinedConstants.TYPES_OF_ROLLS.THIRD_ROLL, 6);
		frankFrames.get(3).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 3);
		frankFrames.get(3).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		frankFrames.get(4).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		frankFrames.get(4).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		frankFrames.get(5).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		frankFrames.get(5).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		frankFrames.get(6).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		frankFrames.get(6).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		frankFrames.get(7).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		frankFrames.get(7).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		frankFrames.get(8).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		frankFrames.get(8).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		frankFrames.get(9).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 7);
		frankFrames.get(9).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 3);
		frankFrames.get(9).setRollResult(DefinedConstants.TYPES_OF_ROLLS.THIRD_ROLL, 10);

		when(tonyPlayer.getGameFrames()).thenReturn(tonyFrames);
		when(frankPlayer.getGameFrames()).thenReturn(frankFrames);

		HashMap<String, List<GameFrame>> resultsMap = tenPinGame.getPlayersResults();
		List<GameFrame> tonyReturnedFrames = resultsMap.get("Tony");
		List<GameFrame> frankReturnedFrames = resultsMap.get("Frank");
		assertTrue("It should be the Tony array",tonyReturnedFrames.hashCode() == tonyFrames.hashCode());
		assertTrue("It should be the Frank array",frankReturnedFrames.hashCode() == frankFrames.hashCode());
	}

	/**
	 * Test method for {@link com.betgenius.tenpinbowling.game.TenPinGame#processRoll(int)}.
	 */
	@Test
	public void testProcessRoll() {
		((PlayerImpl)tonyPlayer).setPlayerName("Tony");
		((PlayerImpl)frankPlayer).setPlayerName("Frank");
		((PlayerImpl)tonyPlayer).setNumOfFrames(10);
		((PlayerImpl)frankPlayer).setNumOfFrames(10);
		tenPinGame = new TenPinGame(players,10);

		List<GameFrame> tonyFrames = new ArrayList<GameFrame>();
		for(int i=1; i <= 10; i++){
			tonyFrames.add(new GameFrameImpl(i));
		}

		tonyFrames.get(0).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 10);
		tonyFrames.get(1).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 3);
		tonyFrames.get(1).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 4);
		tonyFrames.get(2).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		tonyFrames.get(2).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		tonyFrames.get(3).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		tonyFrames.get(3).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		tonyFrames.get(4).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		tonyFrames.get(4).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		tonyFrames.get(5).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		tonyFrames.get(5).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		tonyFrames.get(6).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		tonyFrames.get(6).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		tonyFrames.get(7).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		tonyFrames.get(7).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		tonyFrames.get(8).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		tonyFrames.get(8).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		tonyFrames.get(9).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 7);
		tonyFrames.get(9).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 3);
		tonyFrames.get(9).setRollResult(DefinedConstants.TYPES_OF_ROLLS.THIRD_ROLL, 10);

		List<GameFrame> frankFrames = new ArrayList<GameFrame>();
		for(int i=1; i <= 10; i++){
			frankFrames.add(new GameFrameImpl(i));
		};

		frankFrames.get(0).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 10);
		frankFrames.get(1).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 3);
		frankFrames.get(1).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 4);
		frankFrames.get(2).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 4);
		frankFrames.get(2).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 6);
		frankFrames.get(2).setRollResult(DefinedConstants.TYPES_OF_ROLLS.THIRD_ROLL, 6);
		frankFrames.get(3).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 3);
		frankFrames.get(3).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		frankFrames.get(4).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		frankFrames.get(4).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		frankFrames.get(5).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		frankFrames.get(5).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		frankFrames.get(6).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		frankFrames.get(6).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		frankFrames.get(7).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		frankFrames.get(7).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		frankFrames.get(8).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		frankFrames.get(8).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		frankFrames.get(9).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 7);
		frankFrames.get(9).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 3);
		frankFrames.get(9).setRollResult(DefinedConstants.TYPES_OF_ROLLS.THIRD_ROLL, 10);

		when(tonyPlayer.getGameFrames()).thenReturn(tonyFrames);
		when(frankPlayer.getGameFrames()).thenReturn(frankFrames);

		HashMap<String, List<GameFrame>> resultsMap = tenPinGame.getPlayersResults();
		List<GameFrame> tonyReturnedFrames = resultsMap.get("Tony");
		List<GameFrame> frankReturnedFrames = resultsMap.get("Frank");
		assertTrue("It should be the Tony array",tonyReturnedFrames.hashCode() == tonyFrames.hashCode());
		assertTrue("It should be the Frank array",frankReturnedFrames.hashCode() == frankFrames.hashCode());
	}

	/**
	 * Test method for {@link com.betgenius.tenpinbowling.game.TenPinGame#TenPinGame(java.lang.String[], int)}.
	 */
	@Test
	public void testTenPinGame() {
		((PlayerImpl)tonyPlayer).setPlayerName("Tony");
		((PlayerImpl)frankPlayer).setPlayerName("Frank");
		((PlayerImpl)tonyPlayer).setNumOfFrames(10);
		((PlayerImpl)frankPlayer).setNumOfFrames(10);
		tenPinGame = new TenPinGame(players,10);

		List<GameFrame> tonyFrames = new ArrayList<GameFrame>();
		for(int i=1; i <= 10; i++){
			tonyFrames.add(new GameFrameImpl(i));
		};

		tonyFrames.get(0).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 10);
		tonyFrames.get(1).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 3);
		tonyFrames.get(1).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 4);
		tonyFrames.get(2).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		tonyFrames.get(2).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		tonyFrames.get(3).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		tonyFrames.get(3).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		tonyFrames.get(4).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		tonyFrames.get(4).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		tonyFrames.get(5).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		tonyFrames.get(5).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		tonyFrames.get(6).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		tonyFrames.get(6).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		tonyFrames.get(7).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		tonyFrames.get(7).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		tonyFrames.get(8).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		tonyFrames.get(8).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		tonyFrames.get(9).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 7);
		tonyFrames.get(9).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 3);
		tonyFrames.get(9).setRollResult(DefinedConstants.TYPES_OF_ROLLS.THIRD_ROLL, 10);

		List<GameFrame> frankFrames = new ArrayList<GameFrame>();
		for(int i=1; i <= 10; i++){
			frankFrames.add(new GameFrameImpl(i));
		};

		frankFrames.get(0).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 10);
		frankFrames.get(1).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 3);
		frankFrames.get(1).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 4);
		frankFrames.get(2).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 4);
		frankFrames.get(2).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 6);
		frankFrames.get(2).setRollResult(DefinedConstants.TYPES_OF_ROLLS.THIRD_ROLL, 6);
		frankFrames.get(3).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 3);
		frankFrames.get(3).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		frankFrames.get(4).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		frankFrames.get(4).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		frankFrames.get(5).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		frankFrames.get(5).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		frankFrames.get(6).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		frankFrames.get(6).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		frankFrames.get(7).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		frankFrames.get(7).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		frankFrames.get(8).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		frankFrames.get(8).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		frankFrames.get(9).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 7);
		frankFrames.get(9).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 3);
		frankFrames.get(9).setRollResult(DefinedConstants.TYPES_OF_ROLLS.THIRD_ROLL, 10);

		when(tonyPlayer.getGameFrames()).thenReturn(tonyFrames);
		when(frankPlayer.getGameFrames()).thenReturn(frankFrames);

		HashMap<String, List<GameFrame>> resultsMap = tenPinGame.getPlayersResults();
		List<GameFrame> tonyReturnedFrames = resultsMap.get("Tony");
		List<GameFrame> frankReturnedFrames = resultsMap.get("Frank");
		assertTrue("It should be the Tony array",tonyReturnedFrames.hashCode() == tonyFrames.hashCode());
		assertTrue("It should be the Frank array",frankReturnedFrames.hashCode() == frankFrames.hashCode());
	}

}
