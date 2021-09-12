package com.example.conferencemanager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ConferenceManagerTest {
	private static Logger LOG = LoggerFactory
			.getLogger(ConferenceManagerTest.class);
	private ConferenceManager conferenceManager;
	List<String> inputListOfEvents;

	@Before
	public void initConferenceManager() {
		LOG.info("Initialized test environment");
		conferenceManager = new ConferenceManager();
		inputListOfEvents = Arrays.asList("Writing Fast Tests Against Enterprise Rails 60min",
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
				"User Interface CSS in Rails Apps 30min"
		);
	}

	@After
	public void tearDown(){
		LOG.info("Test completed");
	}

	@Test
	public void testApplication() {
		LOG.info("STARTING THE APPLICATION");
		HashMap<String, Integer> parsedEvents = conferenceManager.parseEvents(inputListOfEvents);
		List<HashMap<String, Double>> dividedTracks = conferenceManager.divideIntoTracks(parsedEvents);
		List<HashMap<String, Double>> speechesWithNetworkingEvent = conferenceManager.addNetworkingEvents(dividedTracks);
		conferenceManager.printResults(speechesWithNetworkingEvent);
	}

	@Test
	public void shouldReturnSortedMapByValues() {
		HashMap<String, Double> hashMap = new HashMap();
		hashMap.put("3", 32.0);
		hashMap.put("2", 23.0);
		hashMap.put("4", 55.0);

		HashMap<String, Double> sortedMap = conferenceManager.sortHashMapByValuesAscending(hashMap);
		assertEquals("2", sortedMap.keySet().toArray()[0]);
		assertEquals(23.0, sortedMap.values().toArray()[0]);
	}

	@Test
	public void testCorrectNumberOfTrackHours() {
		int result1 = conferenceManager.addSpeechesToTracks(0, new HashMap<String, Double>(), "a", 1);
		int result2 = conferenceManager.addSpeechesToTracks(180, new HashMap<String, Double>(), "a", 1);
		assertEquals(1, result1);
		assertEquals(241, result2);
	}

	@Test
	public void testTracksWithNetworkingEvents() {
		List<String> input = Arrays.asList("Writing Fast Tests Against Enterprise Rails 60min",
				"Overdoing it in Python 45min"
		);

		HashMap<String, Integer> parsedEvents = conferenceManager.parseEvents(input);
		List<HashMap<String, Double>> dividedTracks = conferenceManager.divideIntoTracks(parsedEvents);
		List<HashMap<String, Double>> speechesWithNetworkingEvent = conferenceManager.addNetworkingEvents(dividedTracks);

		assertEquals(9.0, speechesWithNetworkingEvent.get(1).values().toArray()[0]);
		assertEquals("Writing Fast Tests Against Enterprise Rails 60min", dividedTracks.get(1).keySet().toArray()[0]);

	}

	@Test
	public void testDivideIntoTracks() {
		List<String> input = Arrays.asList("Writing Fast Tests Against Enterprise Rails 60min",
				"Overdoing it in Python 45min"
		);
		HashMap<String, Integer> parsedEvents = conferenceManager.parseEvents(input);

		List<HashMap<String, Double>> dividedTracks = conferenceManager.divideIntoTracks(parsedEvents);

		assertEquals(9.0, dividedTracks.get(0).values().toArray()[0]);
		assertEquals("Overdoing it in Python 45min", dividedTracks.get(0).keySet().toArray()[0]);

	}

	@Test
	public void testParseEvents() {
		List<String> input = Arrays.asList("Writing Fast Tests Against Enterprise Rails 60min",
				"Overdoing it in Python 45min"
		);
		HashMap<String, Integer> parsedEvents = conferenceManager.parseEvents(input);

		assertEquals(60, parsedEvents.values().toArray()[1]);
		assertEquals("Writing Fast Tests Against Enterprise Rails 60min", parsedEvents.keySet().toArray()[1]);
	}

}
