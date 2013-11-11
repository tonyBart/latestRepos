package com.bartley.tenpinbowling.player;

import java.util.List;

import com.bartley.tenpinbowling.frame.GameFrame;
import com.bartley.tenpinbowling.scorecard.ScoreCard;
import com.bartley.tenpinbowling.util.DefinedConstants;

public interface Player {
	public List<GameFrame> getGameFrames();
	public String getPlayerName();
	public ScoreCard getScoreCard();
	public DefinedConstants.FRAME_CONTEXT setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS roll, int frame, int score);
}
