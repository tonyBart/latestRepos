package com.betgenius.tenpinbowling.scorecard;

import java.util.ArrayList;
import java.util.List;

import com.betgenius.tenpinbowling.frame.GameFrame;
import com.betgenius.tenpinbowling.util.DefinedConstants;
import com.betgenius.tenpinbowling.util.DefinedConstants.FRAME_CONTEXT;

public class ScoreCardImpl implements ScoreCard{

	private  List<GameFrame> frames;
	private int amountOfFrames;
	private int currentFrame = 1;
	private DefinedConstants.SCORECARD_CONTEXT currentState = DefinedConstants.SCORECARD_CONTEXT.EMPTY;

	public ScoreCardImpl(){

	}

	public ScoreCardImpl(int numOfFrames){
		amountOfFrames = numOfFrames;
		if(numOfFrames > DefinedConstants.MAX_NUM_FRAMES ||
				numOfFrames < 1){
			amountOfFrames = DefinedConstants.MAX_NUM_FRAMES;
		}
		frames =new ArrayList<GameFrame>();
	}



	public DefinedConstants.SCORECARD_CONTEXT getCurrentState() {
		return currentState;
	}

	public List<GameFrame> getGameFrames() {
		return frames;
	}

	protected FRAME_CONTEXT processFrameResponse(FRAME_CONTEXT rollResult) {
		if(currentFrame != amountOfFrames && rollResult.equals(DefinedConstants.FRAME_CONTEXT.FRAME_ENDED)){
			currentFrame++;

		}else{
			// Check for game ended
			if(DefinedConstants.FRAME_CONTEXT.FRAME_ENDED.toString().equals(rollResult)){
				currentState  = DefinedConstants.SCORECARD_CONTEXT.GAME_ENDED;
			}
		}

		return rollResult;
	}


	protected DefinedConstants.FRAME_CONTEXT processScore(DefinedConstants.TYPES_OF_ROLLS roll, int score) {
		// Check for Strike Bonus in previous frame if not start of game and don't get a Strike
		processStrikeBonus(score);
		FRAME_CONTEXT result = setRollResults(roll, score);
		result = processFrameResponse(result);
		return result;
	}

	protected void processStrikeBonus(int score) {
		if(currentFrame > 1 && score != DefinedConstants.MAX_SCORE){
			if(frames.get(currentFrame - 2).isStrikeBonus()){

				frames.get(currentFrame - 2).addToBonus(score);
			}
			// Check for Strike Bonus in previous 2 previous frame
			if(currentFrame > 2 && frames.get(currentFrame - 3).isStrikeBonus()){
				frames.get(currentFrame - 3).addToBonus(score);
			}
		}
	}

	public FRAME_CONTEXT setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS roll, int score) {
		//
		if(currentState == DefinedConstants.SCORECARD_CONTEXT.EMPTY){
			currentState = DefinedConstants.SCORECARD_CONTEXT.IN_GAME;
		}

		if(currentFrame >= 1 && currentFrame <= amountOfFrames){
			return processScore(roll, score);
		}

		throw new IndexOutOfBoundsException();

	}

	public void setGameFrames(List<GameFrame> frames){
		this.frames = frames;
	}

	public void setNumOfFrames(int numOfFrames){
		amountOfFrames = numOfFrames;
		if(numOfFrames > DefinedConstants.MAX_NUM_FRAMES ||
				numOfFrames < 1){
			amountOfFrames = DefinedConstants.MAX_NUM_FRAMES;
		}
	}

	FRAME_CONTEXT setRollResults(
			DefinedConstants.TYPES_OF_ROLLS roll, int score) {
		return frames.get(currentFrame - 1).setRollResult(roll, score);
	}

}
