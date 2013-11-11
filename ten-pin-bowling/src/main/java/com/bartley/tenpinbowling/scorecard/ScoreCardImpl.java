package com.bartley.tenpinbowling.scorecard;

import static com.bartley.tenpinbowling.util.DefinedConstants.MAX_NUM_FRAMES;
import static com.bartley.tenpinbowling.util.DefinedConstants.MAX_SCORE;
import static com.bartley.tenpinbowling.util.DefinedConstants.MIN_NUM_OF_FRAMES;

import java.util.ArrayList;
import java.util.List;

import com.bartley.tenpinbowling.frame.GameFrame;
import com.bartley.tenpinbowling.util.DefinedConstants.FRAME_CONTEXT;
import com.bartley.tenpinbowling.util.DefinedConstants.SCORECARD_CONTEXT;
import com.bartley.tenpinbowling.util.DefinedConstants.TYPES_OF_ROLLS;

public class ScoreCardImpl implements ScoreCard{

	private  List<GameFrame> frames;
	private int amountOfFrames;
	private int currentFrame = MIN_NUM_OF_FRAMES;
	private SCORECARD_CONTEXT currentState = SCORECARD_CONTEXT.EMPTY;
	private int totalScore;

	public ScoreCardImpl(){

	}

	public ScoreCardImpl(int numOfFrames){
		amountOfFrames = numOfFrames;
		if(numOfFrames > MAX_NUM_FRAMES ||
				numOfFrames < 1){
			amountOfFrames = MAX_NUM_FRAMES;
		}
		frames =new ArrayList<GameFrame>();
	}



	public SCORECARD_CONTEXT getCurrentState() {
		return currentState;
	}

	public List<GameFrame> getGameFrames() {
		return frames;
	}

	protected FRAME_CONTEXT processFrameResponse(FRAME_CONTEXT rollResult) {
		if(currentFrame != amountOfFrames && rollResult.equals(FRAME_CONTEXT.FRAME_ENDED)){
			currentFrame++;

		}else{
			// Check for game ended
			if(FRAME_CONTEXT.FRAME_ENDED == rollResult  ){
				currentState  = SCORECARD_CONTEXT.GAME_ENDED;
			}
		}

		return rollResult;
	}


	protected FRAME_CONTEXT processScore(TYPES_OF_ROLLS roll, int score) {
		// Check for Strike Bonus in previous frame if not start of game and don't get a Strike
		processStrikeBonus(score, roll);
		FRAME_CONTEXT result = setRollResults(roll, score);
		result = processFrameResponse(result);
		return result;
	}

	protected void processStrikeBonus(int score, TYPES_OF_ROLLS roll) {
		//
		if(currentFrame <= MAX_NUM_FRAMES && score != MAX_SCORE){
			if(frames.get(currentFrame - 2).isStrikeBonus() ||  frames.get(currentFrame - 2).isSpareBonus() ){
				frames.get(currentFrame - 2).addToBonus(score);
			}
			// Check for Strike Bonus in previous 2 previous frame
			if(currentFrame > 2 && frames.get(currentFrame - 3).isStrikeBonus()){
				frames.get(currentFrame - 3).addToBonus(score);

			}
		}else{
			// Max_SCORE
			if(currentFrame == MAX_NUM_FRAMES && roll != TYPES_OF_ROLLS.FIRST_ROLL){
				if(frames.get(currentFrame - 2).isSpareBonus()){
					frames.get(currentFrame - 2).addToBonus(score);
				}
			}else{
				if(currentFrame == MAX_NUM_FRAMES && roll == TYPES_OF_ROLLS.SECOND_ROLL){
					if(frames.get(currentFrame - 1).isStrikeBonus() ){
						frames.get(currentFrame - 1).addToBonus(score);
					}
				}else{
					if(currentFrame == MAX_NUM_FRAMES && roll == TYPES_OF_ROLLS.THIRD_ROLL){
						frames.get(currentFrame - 1).addToBonus(score);
					}
				}

			}
		}
	}


	public FRAME_CONTEXT setFrameRollResult(TYPES_OF_ROLLS roll, int score) {
		//
		if(currentState == SCORECARD_CONTEXT.EMPTY){
			currentState = SCORECARD_CONTEXT.IN_GAME;
		}

		if(currentFrame >= MIN_NUM_OF_FRAMES && currentFrame <= amountOfFrames){
			FRAME_CONTEXT result = processScore(roll, score);
			if(result == FRAME_CONTEXT.FRAME_ENDED){
				totalScore += frames.get(currentFrame - 1).getFrameTotal();
			}
			return result;
		}

		throw new IndexOutOfBoundsException();

	}

	public void setGameFrames(List<GameFrame> frames){
		this.frames = frames;
	}

	public void setNumOfFrames(int numOfFrames){
		amountOfFrames = numOfFrames;
		if(numOfFrames > MAX_NUM_FRAMES ||
				numOfFrames < 1){
			amountOfFrames = MAX_NUM_FRAMES;
		}
	}

	FRAME_CONTEXT setRollResults(
			TYPES_OF_ROLLS roll, int score) {
		return frames.get(currentFrame - 1).setRollResult(roll, score);
	}

	public int getTotalScore() {
		return totalScore;
	}

}
