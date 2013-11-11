package com.bartley.tenpinbowling.scorecard;

import java.util.List;

import com.bartley.tenpinbowling.frame.GameFrame;
import com.bartley.tenpinbowling.util.DefinedConstants;
import com.bartley.tenpinbowling.util.DefinedConstants.FRAME_CONTEXT;


public interface ScoreCard {
	public List<GameFrame> getGameFrames();
	public FRAME_CONTEXT setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS roll, int score);
}
