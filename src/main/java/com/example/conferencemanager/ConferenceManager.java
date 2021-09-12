package com.example.conferencemanager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class ConferenceManager implements CommandLineRunner {
	private static Logger LOG = LoggerFactory.getLogger(ConferenceManager.class);
	private int firstTrackHours, secondTrackHours;

	public int getFirstTrackHours() {
		return firstTrackHours;
	}

	public int getSecondTrackHours() {
		return secondTrackHours;
	}

	public static void main(String[] args) {
		LOG.info("STARTING THE CONFERENCE MANAGER");
		SpringApplication.run(ConferenceManager.class, args);
		LOG.info("APPLICATION FINISHED");
	}

	/**
	 * @param args The command line input
	 * @throws Exception
	 */
	@Override
	public void run(String... args) {
		LOG.info("EXECUTING : command line runner");
		for (int i = 0; i < args.length; ++i) {
			LOG.info(args[i]);
		}
	}

	/**
	 * Below method parses the input events, meaning it will
	 * populate the hashmap with the duration of event and
	 * the event itself from the input list
	 * @param listOfEvents List containing the events,
	 *                     to be precise the description of each event
	 * @return	Returns the HashMap with the duration and event description
	 */
	public HashMap<String,Integer> parseEvents(String[] listOfEvents){
		HashMap<String,Integer> speeches=new HashMap<>();
		for(String event:listOfEvents){
			if(event.endsWith("lightning")) {
				speeches.put(event,5);
			}
			else {
				int speechLength = Integer.parseInt(
						event.split(("(?=(\\s[0-9]))"))[1].
								replace("min", "").
								trim()
				);
				speeches.put(event,speechLength);
			}
		}
		return speeches;
	}

	/**
	 * This method divides 'speeches'(in other words 'events')
	 * into our pre-defined 2 tracks
	 * @param speeches The HashMap of each speech description and its duration
	 * @return	Returns the List of HashMap that holds the description of events and its duration
	 */
	public List<HashMap<String,Double>> divideIntoTracks(HashMap<String,Integer> speeches){
		HashMap<String,Double> firstTrack = new HashMap<>(), secondTrack = new HashMap<>();

		for(Map.Entry<String,Integer> speech : speeches.entrySet()){
			String key = speech.getKey();
			Integer value = speech.getValue();
			if(secondTrackHours < firstTrackHours){
				secondTrackHours = addSpeechesToTracks(secondTrackHours, secondTrack, key, value);
			}
			else{
				firstTrackHours = addSpeechesToTracks(firstTrackHours, firstTrack, key, value);
			}
		}
		return new ArrayList<>(Arrays.asList(firstTrack, secondTrack));
	}

	/**
	 * In this method, the speeches are added into tracks
	 * and corresponding track hours are updated
	 * @param trackHours The accumulated amount of time for each track
	 * @param track	HashMap of event description and the amount of time for the respective event
	 * @param newSpeech The new speech to be added to the corresponding track
	 * @param newSpeechDuration The new speech duration, which will be added to the corresponding track
	 * @return	Returns the updated duration of the corresponding track
	 */
	public Integer addSpeechesToTracks(Integer trackHours, HashMap<String,Double> track,
									   String newSpeech, Integer newSpeechDuration){
		if(trackHours == 180){
			track.put("Lunch", 12.0);
			trackHours += 60;
		}
		track.put(newSpeech, 9.00 + (trackHours / 60) + (trackHours % 60) * 0.01);
		return trackHours + newSpeechDuration;
	}

	/**
	 *	This method adds Networking event to the corresponding track
	 * @param tracks	List of HashMap containing event description and its duration
	 * @return	Returns updated list of HashMap, which will contain Networking event
	 */
	public List<HashMap<String,Double>> addNetworkingEvents(List<HashMap<String,Double>> tracks){
		int tracksCounter=0;
		for(HashMap<String,Double> track:tracks) {
			int trackHours=(tracksCounter>0) ? getSecondTrackHours() : getFirstTrackHours();
			if (420 <= trackHours && trackHours <= 560)
				track.put("Networking Event", 9.00 + (trackHours / 60) + (trackHours % 60) * 0.01);
			tracksCounter++;
		}
		return tracks;
	}
}
