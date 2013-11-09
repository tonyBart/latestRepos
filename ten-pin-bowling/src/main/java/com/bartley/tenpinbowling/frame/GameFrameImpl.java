package com.betgenius.tenpinbowling.frame;

import com.betgenius.tenpinbowling.util.DefinedConstants;
import com.betgenius.tenpinbowling.util.DefinedConstants.FRAME_CONTEXT;

public class GameFrameImpl implements GameFrame{
	private int name;
	private String[] notes = new String[3];
	private Integer[] rollResults = new Integer[3];
	private boolean[] strikeCounter;
	private boolean strikeBonus = false;
	private boolean spareBonus = false;
	private int bonus;

	public GameFrameImpl(){

	}

	public GameFrameImpl(int name){
		this.name = name;
	}

	public void addToBonus(int bonus) {
		if(strikeBonus && strikeCounter != null ){
			if(!strikeCounter[0]){
				strikeCounter[0] = true;
				this.bonus += bonus;
			}else{
				if(!strikeCounter[1]){
					strikeCounter[1] = true;
					this.bonus += bonus;
					strikeBonus = false;
				}
			}
		}

	}

	public int getBonus() {
		return bonus;
	}

	public int getName() {
		return name;
	}

	public String[] getNotes() {
		return notes;
	}

	public Integer[] getRollsResults() {
		return rollResults;
	}

	public boolean isStrikeBonus() {
		return strikeBonus;
	}

	public void setName(int name){
		this.name = name;
	}

	public FRAME_CONTEXT setRollResult(DefinedConstants.TYPES_OF_ROLLS roll, int result) throws IndexOutOfBoundsException{
		if(roll != DefinedConstants.TYPES_OF_ROLLS.THIRD_ROLL  || roll == DefinedConstants.TYPES_OF_ROLLS.THIRD_ROLL && spareBonus){
			int rollPos = DefinedConstants.getRollpos(roll);
			String note = " ";
			rollResults[rollPos] = result;
			bonus += result;
			if(result == 10){
				// strike
				strikeBonus = true;
				strikeCounter = new boolean[2];
				note = DefinedConstants.STRIKE;
				notes[rollPos] = note;
				return DefinedConstants.FRAME_CONTEXT.FRAME_ENDED;
			}else{
				if(roll == DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL && rollResults[rollPos - 1] + result == DefinedConstants.MAX_SCORE){
					// Spare
					spareBonus = true;
					note = DefinedConstants.SPARE;
					notes[rollPos] = note;
					return DefinedConstants.FRAME_CONTEXT.SPARE;
				}else{
					// End Frame
					if(roll == DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL || roll == DefinedConstants.TYPES_OF_ROLLS.THIRD_ROLL){
						// Check for two minimum rolls
						if(roll == DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL && bonus == DefinedConstants.MIN_SCORE){
							note = DefinedConstants.GUTTER;
						}
						notes[rollPos] = note;
						return DefinedConstants.FRAME_CONTEXT.FRAME_ENDED;
					}
					// First roll
					notes[rollPos] = note;
					return DefinedConstants.FRAME_CONTEXT.SECOND_ROLL;
				}
			}

		}else{
			throw new IndexOutOfBoundsException("You are not allowed this roll number: " + roll+1);
		}

	}

}
