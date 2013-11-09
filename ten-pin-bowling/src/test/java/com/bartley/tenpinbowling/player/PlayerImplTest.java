package com.betgenius.tenpinbowling.player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.betgenius.tenpinbowling.frame.GameFrame;
import com.betgenius.tenpinbowling.frame.GameFrameImpl;
import com.betgenius.tenpinbowling.scorecard.ScoreCard;
import com.betgenius.tenpinbowling.util.DefinedConstants;

@RunWith(MockitoJUnitRunner.class)
public class PlayerImplTest {
	Player player;

	@Mock
	ScoreCard scoreCard = Mockito.mock(ScoreCard.class);



	@Before
	public void setUp() throws Exception {
		player = new PlayerImpl("Tony",10);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetGameFrames() {
		String message = "The scoreCard.setFrameRollResult stub should have been called";
		List<GameFrame> frames = new ArrayList<GameFrame>();
		for(int i=1;i<=10;i++){
			frames.add(new GameFrameImpl(i + 1));
		}
		when(scoreCard.getGameFrames()).thenReturn(frames);
		List<GameFrame> returnFrames = null;
		returnFrames = scoreCard.getGameFrames();
		int i = 0;
		for(GameFrame nextFrame :returnFrames){
			assertEquals("The two arrys of GamesFrame's should be equal",nextFrame,frames.get(i++));
		}
	}


	@Test
	public void testSetFrameRollResult() {
		String message = "The scoreCard.setFrameRollResult stub should have been called";
		doThrow(new NullPointerException(message)).when(scoreCard).setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 10);
		boolean nullThrown = false;
		try{
			scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 10);
		}catch(NullPointerException nullEx){
			assertTrue("Not correct NullPointerException",nullEx.getMessage().equals(message));
			nullThrown = true;
		}
		// Check for case NullPointerException not thrown
		if(!nullThrown){
			assertTrue("An NullPointerException should have been thrown", false);
		}
	}


}
