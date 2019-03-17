package a00123456;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;

/**
 * Project: A00123456Lab8
 * File: Runner.java
 * Date: Oct 30, 2016
 * Time: 6:47:13 PM
 */

/**
 * @author Sam Cirka, A00123456
 *
 */
public class Runner extends Thread implements Comparable<Runner> {
	private int lane;
	private int bibNumber;
	private String country;
	private String firstName;
	private String lastName;
	private int reationTime;
	private Random randomDelay;
	private int distance = 1;
	private long timeInMs;

	/**
	 * @param lane
	 * @param bibNumber
	 * @param firstName
	 * @param lastName
	 */
	public Runner(int lane, int bibNumber, String country, String lastName, String firstName, int reationTime) {
		this.setName(lastName);
		this.lane = lane;
		this.bibNumber = bibNumber;
		this.country = country;
		this.firstName = firstName;
		this.lastName = lastName;
		this.reationTime = reationTime;

		randomDelay = new Random();
	}

	/**
	 * @return the lane
	 */
	public int getLane() {
		return lane;
	}

	/**
	 * @return the bibNumber
	 */
	public int getBibNumber() {
		return bibNumber;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @return the reationTime
	 */
	public int getReationTime() {
		return reationTime;
	}

	/**
	 * @return the distance
	 */
	public int getDistance() {
		return distance;
	}

	/**
	 * @return the time
	 */
	public long getTimeInMs() {
		return timeInMs;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		Instant start = Instant.now();

		// add the reaction time
		try {
			Thread.sleep(reationTime);
		} catch (InterruptedException e1) {
			// I don't care;
		}

		while (distance < 100) {
			long delay = 0;
			try {
				delay = 90L + randomDelay.nextInt(16);
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				// I don't care;
			}

			distance++;
		}

		Instant end = Instant.now();
		timeInMs = Duration.between(start, end).toMillis();
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Runner runner) {
		return (int) (timeInMs - runner.timeInMs);
	}

}
