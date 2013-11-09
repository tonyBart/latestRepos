package com.betgenius.tenpinbowling.frame;



import com.betgenius.tenpinbowling.util.DefinedConstants;
import com.betgenius.tenpinbowling.util.DefinedConstants.FRAME_CONTEXT;

public interface GameFrame {
	public void addToBonus(int bonus);
	public int getBonus();
	public int getName();
	public String[] getNotes();
	public Integer[] getRollsResults();
	public boolean isStrikeBonus();
	public FRAME_CONTEXT setRollResult(DefinedConstants.TYPES_OF_ROLLS roll, int result);
}
