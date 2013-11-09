package com.betgenius.tenpinbowling.util;

import java.util.HashMap;
import java.util.Map;

public class DefinedConstants {
	public static enum FRAME_CONTEXT{WAITING,SECOND_ROLL,SPARE,FRAME_ENDED};
	public static enum ROLL_RESULT{NOTHING,SPARE,STRIKE,END};
	public static enum SCORECARD_CONTEXT{EMPTY,IN_GAME,GAME_ENDED};
	public static enum TYPES_OF_ROLLS{FIRST_ROLL, SECOND_ROLL,THIRD_ROLL};
	private static final Map<TYPES_OF_ROLLS,Integer> ROLL_POSITION = new HashMap<TYPES_OF_ROLLS,Integer>();
	public static final int MAX_SCORE = 10;
	public static final int MAX_NUM_FRAMES = 12;

	public static final int MAX_NUM_ROLLS = 3;
	public static final String STRIKE = "STRIKE - MASTER STROKE";
	public static final String SPARE = "SPARE - BOWL AGAIN";
	public static final String SPACE = " ";
	public static final String GUTTER = "UNLUCKY - NEED PRACTICE ";
	public static final int MIN_SCORE = 0;
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
