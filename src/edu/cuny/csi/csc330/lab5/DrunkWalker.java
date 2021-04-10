package edu.cuny.csi.csc330.lab5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DrunkWalker {

	private Intersection startIntersection;
	private Intersection currentIntersection;
	List<Intersection> journey;
	Map<Intersection, Integer> intersectionCount;
	private Integer stepCounter;

	// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	// add other data members here including Collection instances that you will use
	// to
	// to track history and statistics ...

	private DrunkWalker() {
		init();
	}

	/**
	 * 
	 * @param avenue
	 * @param street
	 */
	public DrunkWalker(int avenue, int street) {
		init();
		startIntersection = new Intersection(avenue, street);
		currentIntersection = new Intersection(avenue, street);
	}

	private void init() {
		// What should we do here to initialize an instance??
		// Create the Collection that tracks the journey 1 to N Intersections
		journey = new ArrayList<Intersection>();
		// Create the Collection allows me to map Intersections to counts - number of
		// times we land there
		intersectionCount = new TreeMap<Intersection, Integer>();
		// Create other Collections instances that would facilitate easier management of
		// what needs to be tracked
		stepCounter = 1;

	}

	/**
	 * step in a random direction
	 */
	public void step() {

		takeAStep();
		journey.add(currentIntersection);

		/*
		 * If intersection count containsKey of currentIntersection that means we were
		 * at this intersection before. So we increment the value of the key. else, its
		 * the first time we are at the currentIntersection, so we .put the K,V pair of
		 * the currentIntersection and value = 0
		 */
		if (intersectionCount.containsKey(currentIntersection)) {
			intersectionCount.put(currentIntersection, stepCounter + 1);
		} else {
			intersectionCount.put(currentIntersection, stepCounter);
		}

		/**
		 * !!!!!!!!!!!!!!!!!!!!!!!!!!! Now, update the Collections that manage the
		 * following:
		 * 
		 * 1) add this next step/intersection to a "history" List that will contain the
		 * sequence of all steps taken by this DrunkWalker instance
		 * 
		 * 2) add this next step to a Intersection -> Counter Map ... The Map Collection
		 * can and should be of Generics "Type" <Intersection, Integer> key =
		 * Intersection value = Count Value Need to do something like this: if
		 * intersection is not saved in Map yet as a key yet, add a key->value pair of
		 * Intersection->1 else if intersection value is there, the existing key->value
		 * pair need to be replaced as Intersection->existing_count+1
		 * 
		 */
	}

	private void takeAStep() {
		Direction dir = Direction.NONE.getNextRandom();

		Intersection nextIntersection = new Intersection(currentIntersection.getAvenue(),
				currentIntersection.getStreet());

		if (dir == Direction.SOUTH) { // south
			nextIntersection.setStreet(nextIntersection.getStreet() - 1);
		} else if (dir == Direction.WEST) { // west
			nextIntersection.setAvenue(nextIntersection.getAvenue() + 1);
		} else if (dir == Direction.NORTH) { // north
			nextIntersection.setStreet(nextIntersection.getStreet() + 1);
		} else if (dir == Direction.EAST) { // east
			nextIntersection.setAvenue(nextIntersection.getAvenue() - 1);
		} else if (dir == Direction.NORTHEAST) { // northeast
			nextIntersection.setAvenue(nextIntersection.getAvenue() - 1);
			nextIntersection.setStreet(nextIntersection.getStreet() + 1);
		} else if (dir == Direction.NORTHWEST) { // northwest
			nextIntersection.setAvenue(nextIntersection.getAvenue() + 1);
			nextIntersection.setStreet(nextIntersection.getStreet() + 1);
		} else if (dir == Direction.SOUTHWEST) { // south-west
			nextIntersection.setAvenue(nextIntersection.getAvenue() + 1);
			nextIntersection.setStreet(nextIntersection.getStreet() - 1);
		} else { // south-east
			nextIntersection.setAvenue(nextIntersection.getAvenue() - 1);
			nextIntersection.setStreet(nextIntersection.getStreet() - 1);
		}

		currentIntersection = nextIntersection;

		/**
		 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!! now what do we do based on the random Direction
		 * created? How do we go about updating the "currentIntersection" instance to
		 * reflect the direction/step that was just selected?
		 */

	}

	/**
	 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! toString()
	 * 
	 * @return
	 */
	@Override
	public String toString() {
		return "DrunkWalker [startIntersection=" + startIntersection + ", currentIntersection=" + currentIntersection
				+ ", journey=" + journey + ", intersectionCount=" + intersectionCount + "]";
	}

	/**
	 * generate string that contains current intersection/location info
	 */
	public String getLocation() {
		return String.format("Current location: DrunkWalker [avenue=%d, street=%d]", currentIntersection.getAvenue(),
				currentIntersection.getStreet());
	}

	/**
	 * Take N number of steps
	 * 
	 * @param steps
	 */
	public void fastForward(int steps) {
		// Considering that we already have a step() method, how would we
		// implement this method? think reuse!
		for (int i = 0; i < steps; i++) {
			step();
		}
	}

	/**
	 * Display information about this current walker instance
	 */
	public void displayWalkDetails() {
		/**
		 * This method needs to display the following information in a neat, readable
		 * format using calls to System.out.println() or System.out.printf()
		 * 
		 * 1 - starting location 2 - current/ending location 3 - distance (value
		 * returned by howFar() ) 4 - number of steps taken - which collection would be
		 * able to provide that information, and how? 5 - number of unique intersections
		 * visited - which collection would be able to provide that information, and
		 * how? 6 - Intersections visited more than once
		 * 
		 */

		System.out.printf("%nStarting Location: Intersection [avenue=%d, street=%d]%n", startIntersection.getAvenue(),
				startIntersection.getStreet());

		System.out.printf("Current/Ending Location: Intersection [avenue=%d, street=%d]%n",
				currentIntersection.getAvenue(), currentIntersection.getStreet());

		System.out.printf("Distance (blocks): %d %n", howFar());
		System.out.printf("Number of steps taken: %d %n", journey.size());
		System.out.printf("Number of unique intersections visited: %d %n%n", intersectionCount.size());

		intersectionCount.forEach((k, v) -> System.out.printf("Visited Intersection [avenue=%d, street=%d] %d times!%n",
				k.getAvenue(), k.getStreet(), v));

	}

	/**
	 * X Y Coordinate distance formula |x1 - x2| + |y1 - y2|
	 * 
	 * @return
	 */
	public int howFar() {
		// |x1 - x2| + |y1 - y2|.
		int startAvenue = startIntersection.getAvenue();
		int avenue = currentIntersection.getAvenue();
		int startStreet = startIntersection.getStreet();
		int street = currentIntersection.getStreet();

		return (Math.abs(startAvenue - avenue)) + (Math.abs(startStreet - street));
	}

	public static void main(String[] args) {

		// create Drunkard with initial position (ave,str)
		DrunkWalker billy = new DrunkWalker(6, 23);

		for (int i = 1; i <= 3; ++i) {
			billy.step();
			System.out.printf("billy's location after %d steps: %s\n", i, billy.getLocation());
		}

		// get his current location
		String location = billy.getLocation();
		// get distance from start
		int distance = billy.howFar();

		System.out.println("Current location after fastForward(): " + location);
		System.out.println("That's " + distance + " blocks from start.");

		// have him move 25 random intersections
		billy.fastForward(25);
		billy.displayWalkDetails();

	}

}
