package com.bartley.tenpinbowling.game;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import com.bartley.tenpinbowling.frame.GameFrame;
import com.bartley.tenpinbowling.game.TenPinGame;



public class StrikeSteps {
	TenPinGame game;

	@Given("A game has started with with players named $names for $numbOfFrames Frames ")
	public void givenGameHasStarted(List<String> names, int numbOfFrames){
		game = new TenPinGame((String[])names.toArray(),numbOfFrames);

	}

	@When("When player takes his 2nd roll, he gets a $rollScore ")
	public void whenBowlsAgainGets3(int rollScore){
		game.processRoll(rollScore);
	}

	@When("When player takes his 3rd roll, he gets a $rollScore ")
	public void whenBowlsAgainGets6(int rollScore){
		game.processRoll(rollScore);
	}

	@Then("His score for that frame should be $frameScore"  )
	public  void thenXshouldBe(int frameScore) {
		List<GameFrame> frame = game.getPlayers()[0].getGameFrames();
		int bonus = frame.get(0).getFrameTotal();
		assertEquals("The score should be " + frameScore,bonus,frameScore);
	}


}