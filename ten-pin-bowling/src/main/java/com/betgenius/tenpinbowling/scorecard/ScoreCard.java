package com.betgenius.tenpinbowling.scorecard;

import java.util.List;

import com.betgenius.tenpinbowling.frame.GameFrame;
import com.betgenius.tenpinbowling.util.DefinedConstants;
import com.betgenius.tenpinbowling.util.DefinedConstants.FRAME_CONTEXT;


public interface ScoreCard {
	public List<GameFrame> getGameFrames();
	public FRAME_CONTEXT setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS roll, int score);
}
