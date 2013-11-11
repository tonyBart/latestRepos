package com.bartley.tenpinbowling.game;

import java.util.Arrays;
import java.util.List;

import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.junit.runner.RunWith;

import de.codecentric.jbehave.junit.monitoring.JUnitReportingRunner;

@RunWith(JUnitReportingRunner.class)
public class StrikeJbehave extends JUnitStories {

	public StrikeJbehave() {
		super();
	}

	@Override
	public InjectableStepsFactory stepsFactory() {
		return new InstanceStepsFactory(configuration(), new StrikeSteps());
	}

	@Override
	protected List<String> storyPaths() {
		return Arrays.asList("com/bartley/tenpinbowling/game/Strikes.story");
	}
}