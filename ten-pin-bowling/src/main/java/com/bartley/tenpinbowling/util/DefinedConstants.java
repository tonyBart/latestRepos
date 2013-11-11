package com.bartley.tenpinbowling.util;

import java.util.HashMap;
import java.util.Map;

public class DefinedConstants {
	public static enum FRAME_CONTEXT{WAITING,SECOND_ROLL,SPARE, EXTRA_SPARE, EXTRA,EXTRA_ROLL, FRAME_ENDED};
	public static enum ROLL_RESULT{NOTHING,SPARE,STRIKE,END};
	public static enum SCORECARD_CONTEXT{EMPTY,IN_GAME,GAME_ENDED};
	public static enum TYPES_OF_ROLLS{FIRST_ROLL, SECOND_ROLL,THIRD_ROLL};
	private static final Map<TYPES_OF_ROLLS,Integer> ROLL_POSITION = new HashMap<TYPES_OF_ROLLS,Integer>();
	public static final int MAX_SCORE = 10;
	public static final int MAX_NUM_FRAMES = 10;

	public static final int MAX_NUM_ROLLS = 3;
	public static final String STRIKE = "Strike";
	public static final String STRIKE1 = " from next frame (rolls 1 and 2 from frame ";
	public static final String STRIKE2 = " from this frame (rolls 2 and 3 from frame ";
	public static final String SPARE = "Spare";
	public static final String SPARE1 = " from next roll (roll 1 frame";
	public static final String SPARE_AND_STRIKE1 = ": 10 pins plus bonus of  ";
	public static final String SPARE_AND_STRIKE2 = ")";
	public static final String SPACE = "";
	public static final String GUTTER = "UNLUCKY - NEED PRACTICE ";
	public static final String PERFECT = "perfect";
	public static final int MIN_SCORE = 0;
	public static final int MIN_NUM_OF_FRAMES = 1;
	public static final String EXTRA = "Extra roll due to spare in " + MAX_NUM_FRAMES + "th frame";
	static{
		setRollPos();
	}
	public static int getRollpos(TYPES_OF_ROLLS roll){
		return ROLL_POSITION.get(roll);
	};

	private static void setRollPos(){
		ROLL_POSITION.put(TYPES_OF_ROLLS.FIRST_ROLL, 0);
		ROLL_POSITION.put(TYPES_OF_ROLLS.SECOND_ROLL, 1);
		ROLL_POSITION.put(TYPES_OF_ROLLS.THIRD_ROLL, 2);
	}

}
