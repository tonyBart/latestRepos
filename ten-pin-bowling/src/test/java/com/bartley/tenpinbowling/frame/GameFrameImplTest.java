package com.betgenius.tenpinbowling.frame;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.betgenius.tenpinbowling.util.DefinedConstants;


public class GameFrameImplTest {
	private GameFrame gameFrame;
	@Before
	public void setUp() throws Exception {
		gameFrame = new GameFrameImpl(1);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBonusNoStrike() {

		gameFrame.setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL,10);
		gameFrame.addToBonus(4);
		gameFrame.addToBonus(4);
		int bonus = gameFrame.getBonus();
		assertEquals("The bonus added with roll scores; should be 18",bonus,18);
	}

	@Test
	public void testBonusWithStrike() {
		gameFrame.setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL,10);
		gameFrame.addToBonus(4);
		gameFrame.addToBonus(4);
		int bonus = gameFrame.getBonus();
		assertEquals("The bonus added with roll scores; should be 18",bonus,18);
	}

	@Test
	public void testExtraRollBonus() {
		gameFrame.setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL,4);
		gameFrame.setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL,6);
		gameFrame.setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 10);
		int bonus = gameFrame.getBonus();
		assertEquals("The isStrikeBonus should be set to true",gameFrame.isStrikeBonus(), true);
		assertEquals("The bonus added with roll scores; should be 20",bonus,20);
	}

	@Test
	public void testGetName() {
		assertEquals("The frame number should be 1 ", gameFrame.getName(),1);
	}

	@Test
	public void testRollsResults() {
		gameFrame.setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL,1);
		gameFrame.setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL,6);
		Integer[] rollResults = gameFrame.getRollsResults();
		Integer[] expResults = {1,6,null};
		assertArrayEquals("The roll results shoud be 1 and 6",expResults,rollResults);
	}

	@Test
	public void testSetSpare() {
		boolean isRoll = gameFrame.isStrikeBonus();
		assertEquals("The isStrikeBonus should be intialised to false",isRoll, false);
		gameFrame.setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 10);
		int bonus = gameFrame.getBonus();
		assertEquals("The bonus added with roll scores; should be 10, the Strike on the Spare should not be counted",bonus,10);
	}

}
