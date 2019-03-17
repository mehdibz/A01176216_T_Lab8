package a00123456;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Project: A00123456Lab8
 * File: Lab8.java
 * Date: Oct 30, 2016
 * Time: 6:46:50 PM
 */

/**
 * Recreates the 2016 Olympic 100m sprint final, but with random running times
 * 2 3054 USA BROMELL Trayvon
 * 3 2909 RSA SIMBINE Akani
 * 4 3069 USA GATLIN Justin
 * 5 2434 FRA VICAUT Jimmy
 * 6 2612 JAM BOLT Usain
 * 7 2196 CAN DE GRASSE Andre
 * 8 2245 CIV MEITE Ben Youssef
 * 9 2611 JAM BLAKE Yohan
 * 
 * @author Sam Cirka, A00123456
 *
 */
public class Lab8 {

	private static final Instant startTime = Instant.now();

	private static final int LANE = 0;
	private static final int BIB_NUMBER = 1;
	private static final int COUNTRY = 2;
	private static final int LASTNAME = 3;
	private static final int FIRSTNAME = 4;
	private static final int REACTION_TIME = 5;
	private static String[][] runnerData = new String[][] { //
			// lane, bib#, country, lastname, firstname, reationTime (ms)
			{ "2", "3054", "USA", "BROMELL", "Trayvon", "135" }, //
			{ "3", "2909", "RSA", "SIMBINE", "Akani", "128" }, //
			{ "4", "3069", "USA", "GATLIN", "Justin", "152" }, //
			{ "5", "2434", "FRA", "VICAUT", "Jimmy", "140" }, //
			{ "6", "2612", "JAM", "BOLT", "Usain", "155" }, //
			{ "7", "2196", "CAN", "DE GRASSE", "Andre", "141" }, //
			{ "8", "2245", "CIV", "MEITE", "Ben Youssef", "156" }, //
			{ "9", "2611", "JAM", "BLAKE", "Yohan", "145" } };

	private static List<Runner> runners;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(startTime);

		new Lab8().run();
	}

	/**
	 * 
	 */
	private void run() {
		runners = new ArrayList<>();
		for (String[] data : runnerData) {
			Runner runner = new Runner(Integer.parseInt(data[LANE]), Integer.parseInt(data[BIB_NUMBER]), data[COUNTRY], data[LASTNAME], data[FIRSTNAME],
					Integer.parseInt(data[REACTION_TIME]));
			runners.add(runner);
			runner.start();
			System.out.println(Instant.now() + " - " + runner.toString());
		}

		System.out.println("join");
		for (Runner runner : runners) {
			try {
				System.out.println(Instant.now() + " - " + runner.toString());
				runner.join();
			} catch (InterruptedException e) {
				// don't care;
			}
		}

		printReport(runners);

		Instant endTime = Instant.now();
		System.out.println(endTime);
		System.out.println(String.format("Duration: %d ms", ChronoUnit.MILLIS.between(startTime, endTime)));
	}

	private void printReport(List<Runner> runners) {
		System.out.println("============================================================");
		System.out.format("%4s %4s %4s %-7s %-9s %-11s %8s %6s%n", //
				"Rank", "Lane", "Bib#", "Country", "Last Name", "First Name", "Reaction", "Result");
		Collections.sort(runners);
		int place = 1;
		for (Runner runner : runners) {
			System.out.format("%4d %4d %4d %-7s %-9s %-11s %8.3f %6.3f%n", //
					place++, runner.getLane(), runner.getBibNumber(), runner.getCountry(), runner.getLastName(), runner.getFirstName(),
					runner.getReationTime() / 1000.0F, runner.getTimeInMs() / 1000.0F);
		}

		System.out.println("============================================================");
	}

	public static synchronized int getRunnerCount() {
		return runners.size();
	}

}
