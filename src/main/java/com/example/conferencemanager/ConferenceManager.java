package com.example.conferencemanager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
}
