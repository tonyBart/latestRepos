package com.betgenius.tenpinbowling.player;

import java.util.List;

import com.betgenius.tenpinbowling.frame.GameFrame;
import com.betgenius.tenpinbowling.scorecard.ScoreCard;
import com.betgenius.tenpinbowling.util.DefinedConstants;

public interface Player {
	public List<GameFrame> getGameFrames();
	public String getPlayerName();
	public ScoreCard getScoreCard();
	public DefinedConstants.FRAME_CONTEXT setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS roll, int frame, int score);
}
