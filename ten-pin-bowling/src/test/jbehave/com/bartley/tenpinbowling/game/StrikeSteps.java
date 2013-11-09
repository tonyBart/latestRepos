package com.betgenius.tenpinbowling;

import java.util.List;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import com.betgenius.tenpinbowling.frame.GameFrame;
import com.betgenius.tenpinbowling.game.TenPinGame;



public class StrikeScenarios {
	TenPinGame game;

	@Given("A game has started with 1 player ")
	public void givenGameHasStarted(){
		String[] names = {"Tony"};
		game = new TenPinGame(names,2);

	}

	@Then("His score for the first roll should be 19"  )
	public  void thenXshouldBe() {
		List<GameFrame> frame = game.getPlayers()[0].getGameFrames();
		int bonus = frame.get(0).getBonus();

	}


	@When("Player bowls again he gets a 3")
	public void whenBowlsAgainGets3(){
		game.processRoll(3);
	}

	@When("Player bowls again he gets a 6")
	public void whenBowlsAgainGets6(){
		game.processRoll(6);
	}


	@When("With the first a roll, he gets a strike ")
	public  void whenPlayer1GetsStrike() {
		game.processRoll(10);
	}

	@When("Player rolls he gets a 10")
	public void whenTakes1stBowlsGets10(){
		game.processRoll(10);
	}
}