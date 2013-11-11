package com.bartley.tenpinbowling.player;

import java.util.List;

import com.bartley.tenpinbowling.frame.GameFrame;
import com.bartley.tenpinbowling.scorecard.ScoreCard;
import com.bartley.tenpinbowling.scorecard.ScoreCardImpl;
import com.bartley.tenpinbowling.util.DefinedConstants;

public class PlayerImpl implements Player {
	private ScoreCard scoreCard;

	private String playerName;

	public PlayerImpl(){

	}

	public PlayerImpl(String name, int numOfFrames){
		playerName = name;
		scoreCard = new ScoreCardImpl(numOfFrames);
	}

	public List<GameFrame> getGameFrames(){
		return scoreCard.getGameFrames();
	}

	public String getPlayerName() {
		return playerName;
	}

	public ScoreCard getScoreCard() {
		return scoreCard;
	}

	public DefinedConstants.FRAME_CONTEXT setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS roll, int frame, int score) {
		return scoreCard.setFrameRollResult(roll, score);


	}

	public void setNumOfFrames(int numOfFrames){
		scoreCard = new ScoreCardImpl(numOfFrames);
	}

	public void setPlayerName(String playerName){
		this.playerName = playerName;
	}

}
