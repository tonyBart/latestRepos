package com.betgenius.tenpinbowling.scorecard;

import static org.junit.Assert.assertArrayEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.betgenius.tenpinbowling.frame.GameFrame;
import com.betgenius.tenpinbowling.frame.GameFrameImpl;
import com.betgenius.tenpinbowling.util.DefinedConstants;
import com.betgenius.tenpinbowling.util.DefinedConstants.FRAME_CONTEXT;

@RunWith(MockitoJUnitRunner.class)
public class ScoreCardImplTest {

	@SuppressWarnings("unchecked")
	@Spy
	private List<GameFrame> gameFrame = Mockito.spy(new ArrayList<GameFrame>());

	private ScoreCard scoreCard= new ScoreCardImpl(10);


	private List<GameFrame> frames;

	@Test
	public void processFrame102ndRollSpareTest(){

		List<Integer[]> expectedRollvalues = new ArrayList<Integer[]>();
		Integer[] temp = {10,0,0};
		expectedRollvalues.add(temp);
		temp[0] = 3;
		temp[1] = 4;
		temp[2] = 0;
		expectedRollvalues.add(temp);
		temp[0] = 0;
		temp[1] = 0;
		temp[2] = 0;
		expectedRollvalues.add(temp);
		expectedRollvalues.add(temp);
		expectedRollvalues.add(temp);
		expectedRollvalues.add(temp);
		expectedRollvalues.add(temp);
		expectedRollvalues.add(temp);
		expectedRollvalues.add(temp);
		temp[0] = 7;
		temp[1] = 3;
		temp[2] = 10;
		expectedRollvalues.add(temp);

		List<Integer> bonusArray = new ArrayList<Integer>();
		bonusArray.add(17);
		bonusArray.add(7);
		bonusArray.add(0);
		bonusArray.add(0);
		bonusArray.add(0);
		bonusArray.add(0);
		bonusArray.add(0);
		bonusArray.add(0);
		bonusArray.add(0);
		bonusArray.add(20);

		List<String[]> expectedNotes = new ArrayList<String[]>();
		String[] tempString = {DefinedConstants.STRIKE,DefinedConstants.SPACE,DefinedConstants.SPACE};
		expectedNotes.add(tempString);
		tempString[0] = DefinedConstants.SPACE;
		tempString[1] = DefinedConstants.SPACE;
		tempString[2] = DefinedConstants.SPACE;
		expectedNotes.add(tempString);
		expectedNotes.add(tempString);
		expectedNotes.add(tempString);
		expectedNotes.add(tempString);
		expectedNotes.add(tempString);
		expectedNotes.add(tempString);
		expectedNotes.add(tempString);
		expectedNotes.add(tempString);
		tempString[0] = DefinedConstants.SPACE;
		tempString[1] = DefinedConstants.SPARE;
		tempString[2] = DefinedConstants.STRIKE;
		expectedNotes.add(tempString);

		/* Stub generic return values */
		// General Cases
		when(gameFrame.get(anyInt()).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 10)).thenReturn(FRAME_CONTEXT.FRAME_ENDED);

		// Cater for cases where first & 2nd rolls will be 0 : index 2-8
		for(int i=2; i <= 8;i++){
			when(gameFrame.get(i).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0)).thenReturn(FRAME_CONTEXT.SECOND_ROLL);
			when(gameFrame.get(i).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL,0)).thenReturn(FRAME_CONTEXT.FRAME_ENDED);
		}

		// Specific Cases
		when(gameFrame.get(1).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 3)).thenReturn(FRAME_CONTEXT.SECOND_ROLL);
		when(gameFrame.get(1).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL,4)).thenReturn(FRAME_CONTEXT.FRAME_ENDED);
		when(gameFrame.get(9).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 7)).thenReturn(FRAME_CONTEXT.SECOND_ROLL);
		when(gameFrame.get(9).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 3)).thenReturn(FRAME_CONTEXT.SPARE);
		when(gameFrame.get(9).setRollResult(DefinedConstants.TYPES_OF_ROLLS.THIRD_ROLL, 10)).thenReturn(FRAME_CONTEXT.FRAME_ENDED);

		/**
		 * Stub out the arrays to return the expected values
		 */
		for(int i=0; i < gameFrame.size(); i++){
			when(gameFrame.get(i).getRollsResults()).thenReturn(expectedRollvalues.get(i));
			when(gameFrame.get(i).getNotes()).thenReturn(expectedNotes.get(i));
		}

		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL,10);
		frames.get(0).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 10);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL,3);
		frames.get(1).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 3);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL,4);
		frames.get(1).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 4);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL,0);
		frames.get(2).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL,0);
		frames.get(2).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL,0);
		frames.get(3).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL,0);
		frames.get(3).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL,0);
		frames.get(4).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL,0);
		frames.get(4).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL,0);
		frames.get(5).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL,0);
		frames.get(5).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL,0);
		frames.get(6).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL,0);
		frames.get(6).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL,0);
		frames.get(7).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL,0);
		frames.get(7).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL,0);
		frames.get(8).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL,0);
		frames.get(8).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL,7);
		frames.get(9).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 7);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL,3);
		frames.get(9).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 3);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.THIRD_ROLL,10);
		frames.get(9).setRollResult(DefinedConstants.TYPES_OF_ROLLS.THIRD_ROLL, 10);


		List<GameFrame> results = scoreCard.getGameFrames();
		int position = 0;
		for(GameFrame frame : results){
			assertArrayEquals("Testing roll values for frame: " + frame.getName(),
					expectedRollvalues.get(position),frame.getRollsResults());

			assertArrayEquals("Testing note values for frame: " + frame.getName(),
					expectedNotes.get(position),frame.getNotes());
		}

	}

	@Test
	public void processMaxScoreTest(){

		List<Integer[]> expectedRollvalues = new ArrayList<Integer[]>();
		Integer[] temp = {10,0,0};
		expectedRollvalues.add(temp);
		temp[0] = 3;
		temp[1] = 4;
		temp[2] = 0;
		expectedRollvalues.add(temp);
		temp[0] = 0;
		temp[1] = 0;
		temp[2] = 0;
		expectedRollvalues.add(temp);
		expectedRollvalues.add(temp);
		expectedRollvalues.add(temp);
		expectedRollvalues.add(temp);
		expectedRollvalues.add(temp);
		expectedRollvalues.add(temp);
		expectedRollvalues.add(temp);
		expectedRollvalues.add(temp);

		List<Integer> bonusArray = new ArrayList<Integer>();
		bonusArray.add(17);
		bonusArray.add(7);
		bonusArray.add(0);
		bonusArray.add(0);
		bonusArray.add(0);
		bonusArray.add(0);
		bonusArray.add(0);
		bonusArray.add(0);
		bonusArray.add(0);
		bonusArray.add(0);

		List<String[]> expectedNotes = new ArrayList<String[]>();
		String[] tempString = {DefinedConstants.STRIKE,DefinedConstants.SPACE,DefinedConstants.SPACE};
		expectedNotes.add(tempString);
		tempString[0] = DefinedConstants.SPACE;
		tempString[1] = DefinedConstants.SPACE;
		tempString[2] = DefinedConstants.SPACE;
		expectedNotes.add(tempString);
		expectedNotes.add(tempString);
		expectedNotes.add(tempString);
		expectedNotes.add(tempString);
		expectedNotes.add(tempString);
		expectedNotes.add(tempString);
		expectedNotes.add(tempString);
		expectedNotes.add(tempString);
		expectedNotes.add(tempString);

		/* Stub generic return values */
		// General Cases
		when(gameFrame.get(anyInt()).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 10)).thenReturn(FRAME_CONTEXT.FRAME_ENDED);

		// Cater for cases where first & 2nd rolls will be 0 : index 2-9
		for(int i=2; i <= 9;i++){
			when(gameFrame.get(i).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0)).thenReturn(FRAME_CONTEXT.SECOND_ROLL);
			when(gameFrame.get(i).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL,0)).thenReturn(FRAME_CONTEXT.FRAME_ENDED);
		}

		// Specific Cases
		when(gameFrame.get(1).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 3)).thenReturn(FRAME_CONTEXT.SECOND_ROLL);
		when(gameFrame.get(1).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL,4)).thenReturn(FRAME_CONTEXT.FRAME_ENDED);

		/**
		 * Stub out the arrays to return the expected values
		 */
		for(int i=0; i < gameFrame.size(); i++){
			when(gameFrame.get(i).getRollsResults()).thenReturn(expectedRollvalues.get(i));
			when(gameFrame.get(i).getNotes()).thenReturn(expectedNotes.get(i));
		}


		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL,10);
		frames.get(0).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 10);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL,3);
		frames.get(1).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 3);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL,4);
		frames.get(1).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 4);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL,0);
		frames.get(2).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL,0);
		frames.get(2).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL,0);
		frames.get(3).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL,0);
		frames.get(3).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL,0);
		frames.get(4).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL,0);
		frames.get(4).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL,0);
		frames.get(5).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL,0);
		frames.get(5).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL,0);
		frames.get(6).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL,0);
		frames.get(6).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL,0);
		frames.get(7).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL,0);
		frames.get(7).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL,0);
		frames.get(8).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL,0);
		frames.get(8).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL,0);
		frames.get(9).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL,0);
		frames.get(9).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);

		int position = 0;
		List<GameFrame> results = scoreCard.getGameFrames();
		for(GameFrame frame : results){
			assertArrayEquals("Testing roll values for frame: " + frame.getName(),
					expectedRollvalues.get(position),frame.getRollsResults());

			assertArrayEquals("Testing note values for frame: " + frame.getName(),
					expectedNotes.get(position),frame.getNotes());

		}

	}

	@Test
	public void processMaxScoreTest10thFrame(){

		List<Integer[]> expectedRollvalues = new ArrayList<Integer[]>();
		Integer[] temp = {10,0,0};
		expectedRollvalues.add(temp);
		temp[0] = 3;
		temp[1] = 4;
		temp[2] = 0;
		expectedRollvalues.add(temp);
		temp[0] = 0;
		temp[1] = 0;
		temp[2] = 0;
		expectedRollvalues.add(temp);
		expectedRollvalues.add(temp);
		expectedRollvalues.add(temp);
		expectedRollvalues.add(temp);
		expectedRollvalues.add(temp);
		expectedRollvalues.add(temp);
		expectedRollvalues.add(temp);
		temp[0] = 10;
		temp[1] = 0;
		temp[2] = 0;
		expectedRollvalues.add(temp);

		List<String[]> expectedNotes = new ArrayList<String[]>();
		String[] tempString = {DefinedConstants.STRIKE,DefinedConstants.SPACE,DefinedConstants.SPACE};
		expectedNotes.add(tempString);
		tempString[0] = DefinedConstants.SPACE;
		tempString[1] = DefinedConstants.SPACE;
		tempString[2] = DefinedConstants.SPACE;
		expectedNotes.add(tempString);
		expectedNotes.add(tempString);
		expectedNotes.add(tempString);
		expectedNotes.add(tempString);
		expectedNotes.add(tempString);
		expectedNotes.add(tempString);
		expectedNotes.add(tempString);
		expectedNotes.add(tempString);
		tempString[0] = DefinedConstants.STRIKE;
		tempString[1] = DefinedConstants.SPACE;
		tempString[2] = DefinedConstants.SPACE;
		expectedNotes.add(tempString);

		/* Stub generic return values */
		// General Cases
		when(gameFrame.get(anyInt()).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 10)).thenReturn(FRAME_CONTEXT.FRAME_ENDED);

		// Cater for cases where first & 2nd rolls will be 0 : index 2-8
		for(int i=2; i <= 8;i++){
			when(gameFrame.get(i).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0)).thenReturn(FRAME_CONTEXT.SECOND_ROLL);
			when(gameFrame.get(i).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL,0)).thenReturn(FRAME_CONTEXT.FRAME_ENDED);
		}

		// Specific Cases
		when(gameFrame.get(1).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 3)).thenReturn(FRAME_CONTEXT.SECOND_ROLL);
		when(gameFrame.get(1).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL,4)).thenReturn(FRAME_CONTEXT.FRAME_ENDED);
		when(gameFrame.get(9).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 10)).thenReturn(FRAME_CONTEXT.FRAME_ENDED);


		/**
		 * Stub out the arrays to return the expected values
		 */
		for(int i=0; i < gameFrame.size(); i++){
			when(gameFrame.get(i).getRollsResults()).thenReturn(expectedRollvalues.get(i));
			when(gameFrame.get(i).getNotes()).thenReturn(expectedNotes.get(i));
		}


		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL,10);
		frames.get(0).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 10);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL,3);
		frames.get(1).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 3);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL,4);
		frames.get(1).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 4);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL,0);
		frames.get(2).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL,0);
		frames.get(2).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL,0);
		frames.get(3).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL,0);
		frames.get(3).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL,0);
		frames.get(4).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL,0);
		frames.get(4).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL,0);
		frames.get(5).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL,0);
		frames.get(5).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL,0);
		frames.get(6).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL,0);
		frames.get(6).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL,0);
		frames.get(7).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL,0);
		frames.get(7).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL,0);
		frames.get(8).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL,0);
		frames.get(8).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL,10);
		frames.get(9).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL,10);

		int position = 0;
		List<GameFrame> results = scoreCard.getGameFrames();
		for(GameFrame frame : results){
			assertArrayEquals("Testing roll values for frame: " + frame.getName(),
					expectedRollvalues.get(position),frame.getRollsResults());

			assertArrayEquals("Testing note values for frame: " + frame.getName(),
					expectedNotes.get(position),frame.getNotes());

		}

	}

	@Test
	public void processNonFrame102ndRollSpareTest(){

		List<Integer[]> expectedRollvalues = new ArrayList<Integer[]>();
		Integer[] temp = {10,0,0};
		expectedRollvalues.add(temp);
		temp[0] = 3;
		temp[1] = 4;
		temp[2] = 0;
		expectedRollvalues.add(temp);
		temp[0] = 4;
		temp[1] = 6;
		temp[2] = 0;
		expectedRollvalues.add(temp);
		temp[0] = 3;
		temp[1] = 0;
		temp[2] = 0;
		expectedRollvalues.add(temp);
		temp[0] = 0;
		temp[1] = 0;
		temp[2] = 0;
		expectedRollvalues.add(temp);
		expectedRollvalues.add(temp);
		expectedRollvalues.add(temp);
		expectedRollvalues.add(temp);
		expectedRollvalues.add(temp);
		temp[0] = 7;
		temp[1] = 3;
		temp[2] = 10;
		expectedRollvalues.add(temp);

		List<String[]> expectedNotes = new ArrayList<String[]>();
		String[] tempString = {DefinedConstants.STRIKE,DefinedConstants.SPACE,DefinedConstants.SPACE};
		expectedNotes.add(tempString);
		tempString[0] = DefinedConstants.SPACE;
		tempString[1] = DefinedConstants.SPACE;
		tempString[0] = DefinedConstants.SPACE;
		expectedNotes.add(tempString);
		expectedNotes.add(tempString);
		expectedNotes.add(tempString);
		expectedNotes.add(tempString);
		expectedNotes.add(tempString);
		tempString[0] = DefinedConstants.SPACE;
		tempString[1] = DefinedConstants.SPARE;
		tempString[0] = DefinedConstants.SPACE;
		expectedNotes.add(tempString);
		tempString[0] = DefinedConstants.SPACE;
		tempString[1] = DefinedConstants.SPACE;
		tempString[0] = DefinedConstants.SPACE;
		expectedNotes.add(tempString);
		expectedNotes.add(tempString);
		tempString[0] = DefinedConstants.SPACE;
		tempString[1] = DefinedConstants.SPARE;
		tempString[0] = DefinedConstants.SPACE;
		expectedNotes.add(tempString);

		/* Stub generic return values */
		// General Cases
		when(gameFrame.get(anyInt()).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 10)).thenReturn(FRAME_CONTEXT.FRAME_ENDED);

		// Cater for cases where first & 2nd rolls will be 0 : index 2-8
		for(int i=4; i <= 8;i++){
			when(gameFrame.get(i).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0)).thenReturn(FRAME_CONTEXT.SECOND_ROLL);
			when(gameFrame.get(i).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL,0)).thenReturn(FRAME_CONTEXT.FRAME_ENDED);
		}

		// Specific Cases
		when(gameFrame.get(1).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 3)).thenReturn(FRAME_CONTEXT.SECOND_ROLL);
		when(gameFrame.get(1).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL,4)).thenReturn(FRAME_CONTEXT.FRAME_ENDED);
		when(gameFrame.get(2).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 4)).thenReturn(FRAME_CONTEXT.SECOND_ROLL);
		when(gameFrame.get(2).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL,6)).thenReturn(FRAME_CONTEXT.SPARE);
		when(gameFrame.get(2).setRollResult(DefinedConstants.TYPES_OF_ROLLS.THIRD_ROLL,0)).thenReturn(FRAME_CONTEXT.FRAME_ENDED);
		when(gameFrame.get(3).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 3)).thenReturn(FRAME_CONTEXT.SECOND_ROLL);
		when(gameFrame.get(3).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL,0)).thenReturn(FRAME_CONTEXT.FRAME_ENDED);
		when(gameFrame.get(9).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 7)).thenReturn(FRAME_CONTEXT.SECOND_ROLL);
		when(gameFrame.get(9).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 3)).thenReturn(FRAME_CONTEXT.SPARE);
		when(gameFrame.get(9).setRollResult(DefinedConstants.TYPES_OF_ROLLS.THIRD_ROLL, 10)).thenReturn(FRAME_CONTEXT.FRAME_ENDED);

		/**
		 * Stub out the arrays to return the expected values
		 */
		for(int i=0; i < gameFrame.size(); i++){
			when(gameFrame.get(i).getRollsResults()).thenReturn(expectedRollvalues.get(i));
			when(gameFrame.get(i).getNotes()).thenReturn(expectedNotes.get(i));
		}

		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL,10);
		frames.get(0).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 10);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL,3);
		frames.get(1).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 3);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL,4);
		frames.get(1).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 4);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL,4);
		frames.get(2).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 4);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL,6);
		frames.get(2).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 6);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.THIRD_ROLL,0);
		frames.get(2).setRollResult(DefinedConstants.TYPES_OF_ROLLS.THIRD_ROLL, 6);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL,3);
		frames.get(3).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 3);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL,0);
		frames.get(3).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL,0);
		frames.get(4).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL,0);
		frames.get(4).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL,0);
		frames.get(5).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL,0);
		frames.get(5).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL,0);
		frames.get(6).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL,0);
		frames.get(6).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL,0);
		frames.get(7).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL,0);
		frames.get(7).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL,0);
		frames.get(8).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL,0);
		frames.get(8).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL,7);
		frames.get(9).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 7);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL,3);
		frames.get(9).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 3);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.THIRD_ROLL,10);
		frames.get(9).setRollResult(DefinedConstants.TYPES_OF_ROLLS.THIRD_ROLL, 10);

		int position = 0;
		List<GameFrame> results = scoreCard.getGameFrames();
		for(GameFrame frame : results){
			assertArrayEquals("Testing roll values for frame: " + frame.getName(),
					expectedRollvalues.get(position),frame.getRollsResults());

			assertArrayEquals("Testing note values for frame: " + frame.getName(),
					expectedNotes.get(position),frame.getNotes());

		}

	}

	@Before
	public void setUp() throws Exception {
		frames  = new ArrayList<GameFrame>();
		for(int i=0; i< 10;i++){
			gameFrame.add(Mockito.mock(GameFrame.class));
			frames.add(new GameFrameImpl(i+1));
		}

		((ScoreCardImpl)scoreCard).setGameFrames(gameFrame);


	}

	@After
	public void tearDown() throws Exception {
	}


	@Test
	public void testStrikeWithStrike1framesLater() {
		List<Integer[]> expectedRollvalues = new ArrayList<Integer[]>();
		Integer[] temp = {10,0,0};
		expectedRollvalues.add(temp);
		expectedRollvalues.add(temp);
		temp[0] = 4;
		temp[1] = 6;
		temp[2] = 0;
		expectedRollvalues.add(temp);
		temp[0] = 3;
		temp[1] = 0;
		temp[2] = 0;
		expectedRollvalues.add(temp);
		temp[0] = 0;
		temp[1] = 0;
		temp[2] = 0;
		expectedRollvalues.add(temp);
		expectedRollvalues.add(temp);

		expectedRollvalues.add(temp);
		expectedRollvalues.add(temp);
		expectedRollvalues.add(temp);
		temp[0] = 7;
		temp[1] = 3;
		temp[2] = 10;
		expectedRollvalues.add(temp);

		List<String[]> expectedNotes = new ArrayList<String[]>();
		String[] tempString = {DefinedConstants.STRIKE,DefinedConstants.SPACE,DefinedConstants.SPACE};
		expectedNotes.add(tempString);
		expectedNotes.add(tempString);
		tempString[0] = DefinedConstants.SPACE;
		tempString[1] = DefinedConstants.SPARE;
		tempString[0] = DefinedConstants.SPACE;;
		expectedNotes.add(tempString);
		tempString[0] = DefinedConstants.SPACE;
		tempString[1] = DefinedConstants.SPACE;
		tempString[0] = DefinedConstants.SPACE;
		expectedNotes.add(tempString);
		expectedNotes.add(tempString);
		expectedNotes.add(tempString);
		expectedNotes.add(tempString);
		expectedNotes.add(tempString);
		expectedNotes.add(tempString);
		tempString[0] = DefinedConstants.SPACE;
		tempString[1] = DefinedConstants.SPARE;
		tempString[0] = DefinedConstants.STRIKE;
		expectedNotes.add(tempString);

		/* Stub generic return values */
		// General Cases
		when(gameFrame.get(anyInt()).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 10)).thenReturn(FRAME_CONTEXT.FRAME_ENDED);

		// Cater for cases where first & 2nd rolls will be 0 : index 4-8
		for(int i=4; i <= 8;i++){
			when(gameFrame.get(i).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0)).thenReturn(FRAME_CONTEXT.SECOND_ROLL);
			when(gameFrame.get(i).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL,0)).thenReturn(FRAME_CONTEXT.FRAME_ENDED);
		}

		// Specific Cases
		when(gameFrame.get(1).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 10)).thenReturn(FRAME_CONTEXT.FRAME_ENDED);
		when(gameFrame.get(2).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 4)).thenReturn(FRAME_CONTEXT.SECOND_ROLL);
		when(gameFrame.get(2).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL,6)).thenReturn(FRAME_CONTEXT.SPARE);
		when(gameFrame.get(2).setRollResult(DefinedConstants.TYPES_OF_ROLLS.THIRD_ROLL,0)).thenReturn(FRAME_CONTEXT.FRAME_ENDED);
		when(gameFrame.get(3).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 3)).thenReturn(FRAME_CONTEXT.SECOND_ROLL);
		when(gameFrame.get(3).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL,0)).thenReturn(FRAME_CONTEXT.FRAME_ENDED);
		when(gameFrame.get(9).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 7)).thenReturn(FRAME_CONTEXT.SECOND_ROLL);
		when(gameFrame.get(9).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 3)).thenReturn(FRAME_CONTEXT.SPARE);
		when(gameFrame.get(9).setRollResult(DefinedConstants.TYPES_OF_ROLLS.THIRD_ROLL, 10)).thenReturn(FRAME_CONTEXT.FRAME_ENDED);

		/**
		 * Stub out the arrays to return the expected values
		 */
		for(int i=0; i < gameFrame.size(); i++){
			when(gameFrame.get(i).getRollsResults()).thenReturn(expectedRollvalues.get(i));
			when(gameFrame.get(i).getNotes()).thenReturn(expectedNotes.get(i));
		}

		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL,10);
		frames.get(0).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 10);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL,10);
		frames.get(1).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 10);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL,4);
		frames.get(2).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 4);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL,6);
		frames.get(2).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 6);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.THIRD_ROLL,0);
		frames.get(2).setRollResult(DefinedConstants.TYPES_OF_ROLLS.THIRD_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL,3);
		frames.get(3).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 3);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL,0);
		frames.get(3).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL,0);
		frames.get(4).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL,0);
		frames.get(4).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL,0);
		frames.get(5).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL,0);
		frames.get(5).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL,0);
		frames.get(6).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL,0);
		frames.get(6).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL,0);
		frames.get(7).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL,0);
		frames.get(7).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL,0);
		frames.get(8).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL,0);
		frames.get(8).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL,7);
		frames.get(9).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 7);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL,3);
		frames.get(9).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 3);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.THIRD_ROLL,10);
		frames.get(9).setRollResult(DefinedConstants.TYPES_OF_ROLLS.THIRD_ROLL, 10);

		int position = 0;
		List<GameFrame> results = scoreCard.getGameFrames();
		for(GameFrame frame : results){
			assertArrayEquals("Testing roll values for frame: " + frame.getName(),
					expectedRollvalues.get(position),frame.getRollsResults());

			assertArrayEquals("Testing note values for frame: " + frame.getName(),
					expectedNotes.get(position),frame.getNotes());
		}

	}

	@Test
	public void testStrikeWithStrike2framesLater() {

		List<Integer[]> expectedRollvalues = new ArrayList<Integer[]>();
		Integer[] temp = {10,0,0};
		expectedRollvalues.add(temp);
		expectedRollvalues.add(temp);
		temp[0] = 4;
		temp[1] = 6;
		temp[2] = 0;
		expectedRollvalues.add(temp);
		temp[0] = 10;
		temp[1] = 0;
		temp[2] = 0;
		expectedRollvalues.add(temp);
		temp[0] = 0;
		temp[1] = 0;
		temp[2] = 0;
		expectedRollvalues.add(temp);
		temp[0] = 10;
		temp[1] = 0;
		temp[2] = 0;
		expectedRollvalues.add(temp);
		temp[0] = 0;
		temp[1] = 0;
		temp[2] = 0;
		expectedRollvalues.add(temp);
		expectedRollvalues.add(temp);
		expectedRollvalues.add(temp);
		temp[0] = 7;
		temp[1] = 3;
		temp[2] = 10;
		expectedRollvalues.add(temp);

		List<String[]> expectedNotes = new ArrayList<String[]>();
		String[] tempString = {DefinedConstants.STRIKE,DefinedConstants.SPACE,DefinedConstants.SPACE};
		expectedNotes.add(tempString);
		expectedNotes.add(tempString);
		tempString[0] = DefinedConstants.SPACE;
		tempString[1] = DefinedConstants.SPARE;
		tempString[0] = DefinedConstants.SPACE;
		expectedNotes.add(tempString);
		tempString[0] = DefinedConstants.STRIKE;
		tempString[1] = DefinedConstants.SPACE;
		tempString[0] = DefinedConstants.SPACE;
		expectedNotes.add(tempString);
		expectedNotes.add(tempString);
		tempString[0] = DefinedConstants.SPACE;
		tempString[1] = DefinedConstants.SPARE;
		tempString[0] = DefinedConstants.SPACE;
		expectedNotes.add(tempString);
		tempString[0] = DefinedConstants.STRIKE;
		tempString[1] = DefinedConstants.SPACE;
		tempString[0] = DefinedConstants.SPACE;
		expectedNotes.add(tempString);
		expectedNotes.add(tempString);
		expectedNotes.add(tempString);
		tempString[0] = DefinedConstants.SPACE;
		tempString[1] = DefinedConstants.SPARE;
		tempString[0] = DefinedConstants.STRIKE;
		expectedNotes.add(tempString);

		/* Stub generic return values */
		// General Cases
		when(gameFrame.get(anyInt()).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 10)).thenReturn(FRAME_CONTEXT.FRAME_ENDED);

		// Cater for cases where first & 2nd rolls will be 0 : index 4-8
		for(int i=2; i <= 8;i++){
			when(gameFrame.get(i).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0)).thenReturn(FRAME_CONTEXT.SECOND_ROLL);
			when(gameFrame.get(i).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL,0)).thenReturn(FRAME_CONTEXT.FRAME_ENDED);
		}

		// Specific Cases
		when(gameFrame.get(1).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 10)).thenReturn(FRAME_CONTEXT.FRAME_ENDED);
		when(gameFrame.get(9).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 7)).thenReturn(FRAME_CONTEXT.SECOND_ROLL);
		when(gameFrame.get(9).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 3)).thenReturn(FRAME_CONTEXT.SPARE);
		when(gameFrame.get(9).setRollResult(DefinedConstants.TYPES_OF_ROLLS.THIRD_ROLL, 10)).thenReturn(FRAME_CONTEXT.FRAME_ENDED);

		/**
		 * Stub out the arrays to return the expected values
		 */
		for(int i=0; i < gameFrame.size(); i++){
			when(gameFrame.get(i).getRollsResults()).thenReturn(expectedRollvalues.get(i));
			when(gameFrame.get(i).getNotes()).thenReturn(expectedNotes.get(i));
		}

		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL,10);
		frames.get(0).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 10);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL,10);
		frames.get(1).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 10);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL,0);
		frames.get(2).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL,0);
		frames.get(2).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL,0);
		frames.get(3).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL,0);
		frames.get(3).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL,0);
		frames.get(4).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL,0);
		frames.get(4).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL,0);
		frames.get(5).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL,0);
		frames.get(5).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL,0);
		frames.get(6).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL,0);
		frames.get(6).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL,0);
		frames.get(7).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL,0);
		frames.get(7).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL,0);
		frames.get(8).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL,0);
		frames.get(8).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 0);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL,7);
		frames.get(9).setRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, 7);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL,3);
		frames.get(9).setRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL, 3);
		scoreCard.setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.THIRD_ROLL,10);
		frames.get(9).setRollResult(DefinedConstants.TYPES_OF_ROLLS.THIRD_ROLL, 10);

		int position = 0;
		List<GameFrame> results = scoreCard.getGameFrames();
		for(GameFrame frame : results){
			assertArrayEquals("Testing roll values for frame: " + frame.getName(),
					expectedRollvalues.get(position),frame.getRollsResults());

			assertArrayEquals("Testing note values for frame: " + frame.getName(),
					expectedNotes.get(position),frame.getNotes());

		}

	}

}
