package com.example.conferencemanager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.HashMap;
import static org.junit.Assert.assertEquals;

@SpringBootTest
class ConferenceManagerTest {
	private static Logger LOG = LoggerFactory
			.getLogger(ConferenceManagerTest.class);
	private ConferenceManager conferenceManager;
	String[] inputListOfEvents;

	@BeforeEach
	public void initConferenceManagerTest() {
		LOG.info("Initialized test environment");
		conferenceManager = new ConferenceManager();
		inputListOfEvents = new String[]{"Writing Fast Tests Against Enterprise Rails 60min",
				"Overdoing it in Python 45min",
				"Lua for the Masses 30min",
				"Ruby Errors from Mismatched Gem Versions 45min",
				"Common Ruby Errors 45min",
				"Rails for Python Developers lightning",
				"Communicating Over Distance 60min",
				"Accounting-Driven Development 45min",
				"Woah 30min",
				"Sit Down and Write 30min",
				"Pair Programming vs Noise 45min",
				"Rails Magic 60min",
				"Ruby on Rails: Why We Should Move On 60min",
				"Clojure Ate Scala (on my project) 45min",
				"Programming in the Boondocks of Seattle 30min",
				"Ruby vs. Clojure for Back-End Development 30min",
				"Ruby on Rails Legacy App Maintenance 60min",
				"A World Without HackerNews 30min",
				"User Interface CSS in Rails Apps 30min"};
	}

	@AfterEach
	public void tearDown(){
		LOG.info("Test completed");
	}

	@Test
	public void testInputToApplication() {
		LOG.info("Starting the test for input to Application");
		ConferenceManager.main(inputListOfEvents);
	}

	@Test
	public void shouldReturnParsedEvents() {
		HashMap<String, Integer> parsedEvents = conferenceManager.parseEvents(
				new String[]{inputListOfEvents[0],inputListOfEvents[1]}
		);
		assertEquals(60, parsedEvents.values().toArray()[1]);
		assertEquals("Writing Fast Tests Against Enterprise Rails 60min", parsedEvents.keySet().toArray()[1]);
	}
}
