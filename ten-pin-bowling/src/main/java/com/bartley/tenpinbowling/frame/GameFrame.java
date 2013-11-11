package com.bartley.tenpinbowling.frame;



import java.util.List;

import com.bartley.tenpinbowling.util.DefinedConstants;
import com.bartley.tenpinbowling.util.DefinedConstants.FRAME_CONTEXT;

public interface GameFrame {
	public void addToBonus(int bonus);
	public int getFrameTotal();
	public int getName();
	public void addToNotes(String note);
	public List<String> getNotes();
	public List<Integer> getRollsResults();
	public boolean isStrikeBonus();
	public boolean isSpareBonus();
	public FRAME_CONTEXT setRollResult(DefinedConstants.TYPES_OF_ROLLS roll, int result);
	public int[] getBonusArr();
}
