package com.betgenius.tenpinbowling;

import java.util.Arrays;
import java.util.List;

import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.junit.runner.RunWith;

import de.codecentric.jbehave.junit.monitoring.JUnitReportingRunner;

@RunWith(JUnitReportingRunner.class)
public class StrikeJbehaveTest extends JUnitStories {

	public StrikeJbehaveTest() {
		super();
	}

	@Override
	public InjectableStepsFactory stepsFactory() {
		return new InstanceStepsFactory(configuration(), new StrikeScenarios());
	}

	@Override
	protected List<String> storyPaths() {
		return Arrays.asList("com/betgenius/tenpinbowling/StrikeScenarios.java");
	}
}