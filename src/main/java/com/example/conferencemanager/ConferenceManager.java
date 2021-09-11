package com.example.conferencemanager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.HashMap;

@SpringBootApplication
public class ConferenceManager implements CommandLineRunner {

	private static Logger LOG = LoggerFactory.getLogger(ConferenceManager.class);

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
}
