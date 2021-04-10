package edu.cuny.csi.csc330.lab5;

import java.util.Scanner;

public class DrunkWalkTester {

	private Scanner input;

	public DrunkWalkTester() {
		init();
	}

	private void init() {
		input = new Scanner(System.in);
	}

	public void runTest(int steps) {

		System.out.print("Enter the starting avenue value: ");
		int avenue = input.nextInt();
		System.out.print("Enter the starting street value: ");
		int street = input.nextInt();

		//////////////////////// start test
		// make the Drunkard with initial position
		DrunkWalker billy = new DrunkWalker(avenue, street);
		DrunkWalker harvey = new DrunkWalker(avenue, street);

		// have him move/step 200 time
		billy.fastForward(steps);
		harvey.fastForward(steps);

		// get his current location
		String locationBilly = billy.getLocation();
		String locationHarvey = harvey.getLocation();
		// get distance from start
		int distance = billy.howFar();
		int distance2 = harvey.howFar();

		System.out.println("Billy's " + locationBilly);
		System.out.println("That's " + distance + " blocks from start.");

		System.out.println("Harvey's " + locationBilly);
		System.out.println("That's " + distance2 + " blocks from start.");

		if (distance > distance2) {
			System.out.println("Billy walked a greater distance with " + distance + " blocks from the start.");
		} else
			System.out.println("Harvey walked a greater distance with " + distance2 + " blocks from the start.");

		billy.displayWalkDetails();
		harvey.displayWalkDetails();

		//////////////////// end test
		/**
		 * Expand the test above to include the following ... Create a 2nd instances of
		 * DrunkWalker - Harvey Have then race each - which instance (billy or harvey)
		 * manages to walk a greater distance with 200 steps?
		 * 
		 * Also invoke the displayWalkDetails() on both instances.
		 */

	}

	/**
	 * @param args
	 * 
	 */
	public static void main(String[] args) {
		DrunkWalkTester tester = new DrunkWalkTester();

		int steps = 200;
		if (args.length == 1) {
			steps = Integer.parseInt(args[0]);
		}

		tester.runTest(steps);

		System.exit(0);

	}

}
