package com.bartley.tenpinbowling.game;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import com.bartley.tenpinbowling.frame.GameFrame;
import com.bartley.tenpinbowling.player.Player;
import com.bartley.tenpinbowling.player.PlayerImpl;
import com.bartley.tenpinbowling.util.DefinedConstants;
import com.bartley.tenpinbowling.util.DefinedConstants.FRAME_CONTEXT;

public class TenPinGame {
	public static void main1(String[] args) {
		// Check for minimum number of players == 1 + number of games  = 2
		int numberOfFrames = 0;
		TenPinGame game = null;
		int numOfPlayers = 0;
		if(args.length < 2 ){
			try{
				// Check to ensure that the last argument is an integer between 1 & maximum number of frames
				numberOfFrames = Integer.parseInt(args[args.length - 1]);
				if(numberOfFrames < 1 || numberOfFrames > DefinedConstants.MAX_NUM_FRAMES){
					System.out.println("The number of games should be an integer between 1 and " + DefinedConstants.MAX_NUM_FRAMES);
					System.exit(0);
				}
				numOfPlayers = args.length - 1;
				String[] names = new String[numOfPlayers];
				System.arraycopy(args,0,names,0,names.length);
				game = new TenPinGame(names,numberOfFrames);
			}catch(NumberFormatException numb){
				System.out.println("The number of games should be an integer between 1 and " + DefinedConstants.MAX_NUM_FRAMES);
				System.exit(0);
			}
		}

		Random gen = new Random();
		for(int i = 1; i <= numberOfFrames; i++){
			for(int j=0; j<numOfPlayers; j++ ){
				int firstRoll = 0;
				boolean isFirstRoll = true;
				boolean isSecRoll = false;
				boolean isThirdRoll = false;
				FRAME_CONTEXT rollResult = null;
				while(rollResult != DefinedConstants.FRAME_CONTEXT.FRAME_ENDED ){
					if(isFirstRoll || rollResult == DefinedConstants.FRAME_CONTEXT.EXTRA){

						firstRoll = gen.nextInt(10);
						rollResult = game.processRoll(firstRoll);
						isFirstRoll = false;
						isSecRoll = true;
					}else{
						if(isSecRoll || rollResult == DefinedConstants.FRAME_CONTEXT.EXTRA_SPARE){
							// Take into account what is scored in the first roll
							int secRoll = gen.nextInt(10 - firstRoll );
							//
							isSecRoll = false;
							rollResult =  game.processRoll(secRoll);
							if(rollResult == DefinedConstants.FRAME_CONTEXT.SPARE){
								isThirdRoll = true;
							}
						}else{
							if(isThirdRoll || rollResult == DefinedConstants.FRAME_CONTEXT.EXTRA_ROLL){
								int thirdRoll = gen.nextInt(10);
								rollResult =  game.processRoll(thirdRoll);
								isThirdRoll = false;
							}
						}
					}
				}
			}
			HashMap<String, List<GameFrame>> resultsMap = game.getPlayersResults();
			// To Do display results
		}
	}

	/**
	 * @param args
	 */
	Player[] players;

	private Random gen;

	private final int numberOfFrames;

	private int currentFrame;

	private int whichPlayer;

	int firstRoll = 0;
	boolean isFirstRoll = true;
	boolean isSecRoll = false;
	boolean isThirdRoll = false;


	public TenPinGame(Player[] players,int numOfFrames){
		numberOfFrames = numOfFrames;
		this.players = players;
	}

	public TenPinGame(String[] playerNames,int numOfFrames){
		numberOfFrames = numOfFrames;
		players = new Player[playerNames.length];
		int i = 0;
		for(String name: playerNames){
			players[i++] = new PlayerImpl(name,numOfFrames);
		}
	}

	public Player[] getPlayers(){
		return players;
	}

	public HashMap<String, List<GameFrame>> getPlayersResults(){
		HashMap<String, List<GameFrame>> resultsMap = new HashMap<String,List<GameFrame>>();
		for(Player player :players){
			resultsMap.put(player.getPlayerName(),player.getGameFrames());
		}

		return resultsMap;
	}

	/**
	 * 
	 * @param roll: value of  the roll
	 * @return: boolean to indicate whether end of game
	 */
	public FRAME_CONTEXT processRoll(int roll){
		DefinedConstants.FRAME_CONTEXT rollResult = null;

		while(rollResult != DefinedConstants.FRAME_CONTEXT.FRAME_ENDED){
			if(isFirstRoll || rollResult == DefinedConstants.FRAME_CONTEXT.EXTRA){

				rollResult = players[whichPlayer].setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.FIRST_ROLL, currentFrame, roll);
				isFirstRoll = false;
				isSecRoll = true;
			}else{
				if(isSecRoll || rollResult == DefinedConstants.FRAME_CONTEXT.EXTRA_SPARE){
					//
					isSecRoll = false;
					rollResult = players[whichPlayer].setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.SECOND_ROLL,currentFrame, roll);
					if(rollResult == DefinedConstants.FRAME_CONTEXT.SPARE){
						isThirdRoll = true;
					}
				}else{
					if(isThirdRoll || rollResult == DefinedConstants.FRAME_CONTEXT.EXTRA_ROLL){
						rollResult = players[whichPlayer].setFrameRollResult(DefinedConstants.TYPES_OF_ROLLS.THIRD_ROLL,currentFrame, roll);
						isThirdRoll = false;
					}
				}
			}
		}
		// If end of frame reset isFirstRoll
		if(rollResult == DefinedConstants.FRAME_CONTEXT.FRAME_ENDED){
			isFirstRoll = true;
			// Point to next player if not at the last player, if so, point to first player
			if(whichPlayer != players.length - 1){
				whichPlayer++;
			}else{
				whichPlayer = 0;
			}
		}

		// Check for increasing the frame
		if(rollResult == DefinedConstants.FRAME_CONTEXT.FRAME_ENDED && whichPlayer == players.length - 1 &&
				currentFrame != numberOfFrames){
			currentFrame++;

		}else{
			if(
					rollResult ==  DefinedConstants.FRAME_CONTEXT.FRAME_ENDED  && whichPlayer == players.length - 1 &&
					currentFrame == numberOfFrames){
				currentFrame = 1;
			}
		}
		return rollResult;
	}
}
