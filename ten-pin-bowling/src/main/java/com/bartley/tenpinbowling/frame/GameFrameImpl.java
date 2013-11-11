package com.bartley.tenpinbowling.frame;

import static com.bartley.tenpinbowling.util.DefinedConstants.EXTRA;
import static com.bartley.tenpinbowling.util.DefinedConstants.MAX_NUM_FRAMES;
import static com.bartley.tenpinbowling.util.DefinedConstants.MAX_SCORE;
import static com.bartley.tenpinbowling.util.DefinedConstants.SPACE;
import static com.bartley.tenpinbowling.util.DefinedConstants.SPARE;
import static com.bartley.tenpinbowling.util.DefinedConstants.SPARE1;
import static com.bartley.tenpinbowling.util.DefinedConstants.SPARE_AND_STRIKE1;
import static com.bartley.tenpinbowling.util.DefinedConstants.SPARE_AND_STRIKE2;
import static com.bartley.tenpinbowling.util.DefinedConstants.STRIKE;
import static com.bartley.tenpinbowling.util.DefinedConstants.STRIKE1;
import static com.bartley.tenpinbowling.util.DefinedConstants.STRIKE2;
import static com.bartley.tenpinbowling.util.DefinedConstants.getRollpos;

import java.util.ArrayList;
import java.util.List;

import com.bartley.tenpinbowling.util.DefinedConstants.FRAME_CONTEXT;
import com.bartley.tenpinbowling.util.DefinedConstants.TYPES_OF_ROLLS;

public class GameFrameImpl implements GameFrame{
	private int name;
	private List<String> notes = new ArrayList<String>();
	private List<Integer> rollResults = new ArrayList<Integer>();
	private boolean[] strikeChecker;
	private boolean strikeBonus;
	private boolean spareBonus;
	private int[] bonusArr;

	public GameFrameImpl(){

	}

	public GameFrameImpl(int name){
		this.name = name;
	}

	public void addToBonus(int bonus) {
		if(strikeBonus && strikeChecker != null ){
			if(!strikeChecker[0]){
				strikeChecker[0] = true;
				bonusArr[0] = bonus;
				/**
				if(name == MAX_NUM_FRAMES && strikeChecker.length == 2){
					// Comment as a Spare
					notes.add(SPARE);
				}
				 **/
			}else{
				if(!strikeChecker[1]){
					strikeChecker[1] = true;
					bonusArr[1] = bonus;
					strikeBonus = false;
					// Add in the notes
					if(name == MAX_NUM_FRAMES){
						notes.add(STRIKE + SPARE_AND_STRIKE1 + bonusArr[0] + " & " + bonusArr[1] + STRIKE2
								+ name + SPARE_AND_STRIKE2);
					}else{
						notes.add(STRIKE + SPARE_AND_STRIKE1 + bonusArr[0] + " & " + bonusArr[1] + STRIKE1
								+ name + SPARE_AND_STRIKE2);
					}
				}
			}
		}else{
			if(spareBonus){
				bonusArr[0] = bonus;
				spareBonus = false;
				// Add in the notes
				notes.add(SPARE + SPARE_AND_STRIKE1 + bonusArr[0]  + SPARE1 +
						+ name + SPARE_AND_STRIKE2);
			}
		}

	}

	public int getFrameTotal() {
		int total = 0;
		if(bonusArr != null){
			for(Integer num: bonusArr){
				if(num != null){
					total +=  num;
				}
			}
		}

		for(Integer rollValue: rollResults){
			total += rollValue;
		}
		return total;
	}

	public int getName() {
		return name;
	}

	public List<String> getNotes() {
		return notes;
	}

	public List<Integer> getRollsResults() {
		return rollResults;
	}

	public boolean isStrikeBonus() {
		return strikeBonus;
	}

	public void setName(int name){
		this.name = name;
	}

	public FRAME_CONTEXT setRollResult(TYPES_OF_ROLLS roll, int result){
		int rollPos = getRollpos(roll);
		FRAME_CONTEXT response = null;
		if(roll == TYPES_OF_ROLLS.FIRST_ROLL){
			response = processFirstRoll(result);
		}else{
			if(roll == TYPES_OF_ROLLS.SECOND_ROLL ){
				response =  processSecondRoll(result,rollPos);

			}else{
				// Add the bonus
				addToBonus(result);
				response =  FRAME_CONTEXT.FRAME_ENDED;
			}
		}
		return response;
	}

	/**
	 * @param result
	 * @return
	 */
	protected FRAME_CONTEXT processFirstRoll(int result) {
		FRAME_CONTEXT response = null;
		rollResults.add(result);
		if(result == MAX_SCORE){
			setStrikeBonus();
			if(name == MAX_NUM_FRAMES){
				notes.add(SPACE);
				response = FRAME_CONTEXT.EXTRA_ROLL;
			}else{
				notes.add(STRIKE);
				response = FRAME_CONTEXT.FRAME_ENDED;
			}
		}else{
			notes.add(SPACE);
			response = FRAME_CONTEXT.SECOND_ROLL;
		}
		return response;
	}


	protected FRAME_CONTEXT processSecondRoll(int result, int rollPos) {
		if(strikeBonus){
			// Must be the last frame, only time get second roll after strike
			notes.add(SPARE);
			bonusArr[0] = result;
			return FRAME_CONTEXT.EXTRA_SPARE;
		}else{
			if(result < MAX_SCORE){
				if(rollResults.get(rollPos - 1) + result == MAX_SCORE){
					setSpareBonus();
					rollResults.add(result);
					if(name  == MAX_NUM_FRAMES){
						return FRAME_CONTEXT.EXTRA_ROLL;
					}

				}else{
					rollResults.add(result);

				}
			}else{
				rollResults.add(result);
				if(name  == MAX_NUM_FRAMES){
					setSpareBonus();
					return FRAME_CONTEXT.EXTRA_ROLL;
				}
				setStrikeBonus();
			}
		}
		return FRAME_CONTEXT.FRAME_ENDED;
	}


	/**
	 * @param result
	 */
	protected FRAME_CONTEXT processThirdRoll(int result) {
		if(spareBonus){
			notes.add(EXTRA);
			bonusArr[0] = result;
			spareBonus = false;
		}else{
			// Got to be StrikeBonus
			// Check the size of the bonusArr container
			if(bonusArr.length == 1){
				// Spare or Strike in last 2nd roll in the Last Frame
				bonusArr[0] = result;
				strikeChecker[0] = true;
				strikeBonus = false;
			}else{
				// Strike in first roll in the Last Frame
				bonusArr[1] = result;
				strikeChecker[1] = true;
				strikeBonus = false;
			}
		}
		return FRAME_CONTEXT.FRAME_ENDED;
	}

	/**
	 * 
	 */
	protected void setStrikeBonus() {
		strikeBonus = true;
		strikeChecker = new boolean[2];
		bonusArr = new int[2];
	}

	/**
	 * 
	 */
	protected void setSpareBonus() {
		notes.add(SPARE);
		spareBonus = true;
		bonusArr = new int[1];

	}

	public boolean isSpareBonus() {
		return spareBonus;
	}

	public void addToNotes(String note) {
		notes.add(note);
	}

	public int[] getBonusArr() {
		int[] transferer = new int[bonusArr.length];
		for(int i=0; i<bonusArr.length;i++){
			transferer[i] = bonusArr[i];
		}
		return transferer;
	}

}
