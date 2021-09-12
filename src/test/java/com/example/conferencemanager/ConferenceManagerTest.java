package com.example.conferencemanager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
class ConferenceManagerTest {
	private static Logger LOG = LoggerFactory
			.getLogger(ConferenceManagerTest.class);
	private ConferenceManager conferenceManager;
	String[] inputListOfEvents;
	HashMap<String, Integer> parsedEvents;
	List<HashMap<String, Double>> dividedTracks;

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
	public void testApplication(){
		HashMap<String, Integer> parsedEvents = conferenceManager.parseEvents(inputListOfEvents);
		List<HashMap<String, Double>> dividedTracks = conferenceManager.divideIntoTracks(parsedEvents);
		for(HashMap<String,Double> hashMap:dividedTracks){
			for(String key:hashMap.keySet()){
				LOG.info(hashMap.get(key)+" "+key);
			}
		}
	}

	@Test
	public void testParseEvents() {
		parseEventsForUnitTests();
		Assertions.assertEquals(60, parsedEvents.values().toArray()[1]);
		Assertions.assertEquals("Writing Fast Tests Against Enterprise Rails 60min", parsedEvents.keySet().toArray()[1]);
	}

	@Test
	public void testDivideIntoTracks() {
		parseEventsForUnitTests();
		dividedTracks = conferenceManager.divideIntoTracks(parsedEvents);

		Assertions.assertEquals(9.0, dividedTracks.get(0).values().toArray()[0]);
		Assertions.assertEquals("Overdoing it in Python 45min", dividedTracks.get(0).keySet().toArray()[0]);
	}

	@Test
	public void testCorrectNumberOfTrackHours() {
		int result1 = conferenceManager.addSpeechesToTracks(0, new HashMap<>(), "a", 1);
		int result2 = conferenceManager.addSpeechesToTracks(180, new HashMap<>(), "a", 1);
		Assertions.assertEquals(1, result1);
		Assertions.assertEquals(241, result2);
	}

	@Test
	public void testTracksWithNetworkingEvents() {
		parseEventsForUnitTests();
		List<HashMap<String, Double>> dividedTracks = conferenceManager.divideIntoTracks(parsedEvents);
		List<HashMap<String, Double>> speechesWithNetworkingEvent = conferenceManager.addNetworkingEvents(dividedTracks);

		assertEquals(9.0, speechesWithNetworkingEvent.get(1).values().toArray()[0]);
		assertEquals("Writing Fast Tests Against Enterprise Rails 60min", dividedTracks.get(1).keySet().toArray()[0]);
	}

	public void parseEventsForUnitTests(){
		parsedEvents = conferenceManager.parseEvents(
				new String[]{inputListOfEvents[0],inputListOfEvents[1]});
	}
}
