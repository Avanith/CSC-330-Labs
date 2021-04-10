/**
 * LAB 3 -  Lotto QuickPicker Game 
 */
package edu.cuny.csi.csc330.lab3;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Date;

import javax.swing.JOptionPane;

import edu.cuny.csi.csc330.util.Randomizer;

public class LottoQuickPicker {

	// constants specific to current game - BUT NOT ALL GAMES
	public final static int DEFAULT_GAME_COUNT = 1;
	public static int GAME_COUNT = 1;
	private final static String GAME_NAME = "LOTTO";
	private final static int SELECTION_POOL_SIZE = 59;
	private final static int SELECTION_COUNT = 6;
	private final static String STORE_NAME = "Dan's Deli";
	private int lottoArray[][] = new int[GAME_COUNT][SELECTION_COUNT];

	/**
	 * Default Constructor that makes a single game.
	 */
	public LottoQuickPicker() {
		init(DEFAULT_GAME_COUNT);
	} // END LOTTOQUICKPICKER

	/**
	 * Constructor that creates "games" number of games.
	 */
	public LottoQuickPicker(int games) {
		GAME_COUNT = games;
		init(games);
	} // end LOTTOQUICKPICKER

	/**
	 * This array will call on two helper methods to generate a 2-D array with each
	 * row having unique numbers.
	 * 
	 * @return A 2-D array that has unique numbers for each of its rows.
	 */
	private int[][] uniqueNumArray() {
		int[][] uniqueArray = new int[GAME_COUNT][SELECTION_COUNT];

		for (int i = 0; i < GAME_COUNT; i++)
			uniqueArray[i] = generateUniqueArray();

		return uniqueArray;
	} // END UNIQUENUMARRAY

	/**
	 * Helper Method Generates a 1-D array with unique numbers from 1 - selection
	 * pool size.
	 * 
	 * @return A 1-D array with unique numbers.
	 */
	private static int[] generateUniqueArray() {
		int[] array = new int[SELECTION_COUNT];
		int temp;

		for (int i = 0; i < array.length; i++) {
			temp = Randomizer.generateInt(1, SELECTION_POOL_SIZE);
			// if the array doesn't have the number, then add the number to the array.
			// can write separate method for this
			while (arrayHasNumber(temp, array)) {
				temp = Randomizer.generateInt(1, SELECTION_POOL_SIZE);
			} // END while statement

			array[i] = temp;
		} // end for-loop

		Arrays.sort(array);

		return array;
	} // END generateUniqueArray

	/**
	 * Helper Method Array will check if passed number is inside of passed array
	 * 
	 * @param numToCheckFor The int to check the array for
	 * @param arrayToCheck  The array to check inside for the passed int.
	 * @return a boolean value that is true if the array has the passed integer and
	 *         false if it does not
	 */
	private static boolean arrayHasNumber(int numToCheckFor, int[] arrayToCheck) {
		for (int i = 0; i < arrayToCheck.length; i++) {
			if (numToCheckFor == arrayToCheck[i])
				return true;
		}
		return false;
	} // END arrayHasNumber

	/**
	 * Initializes a private class 2D array variable with the proper number of
	 * "quick-pick" lotto games.
	 * 
	 * @param games An integer that is the number of games that will be generated.
	 */
	private void init(int games) {
		lottoArray = Arrays.copyOf(uniqueNumArray(), lottoArray.length);
	} // END init

	/**
	 * This method will call displayHeading() and displayFooter() helper methods and
	 * in-between those two it will display the private member lottoArray which
	 * contains the quick pick games that have been previously generated
	 */
	public void displayTicket() {

		/**
		 * display heading Game ticket should display a game specific heading including
		 * date/time.
		 * 
		 * for i in gameCount generate selectionCount number of unique random selections
		 * in ascending order * Each game will be numbered/indexed - 1,2,3 ... n game
		 * numbers will be evenly spaced/formatted and single digit nums will be padded
		 * with a leading 0.
		 * 
		 * display footer
		 */

		displayHeading();

		/**
		 * Display selected numbers
		 */
		// Iterate through each row, which is a single lotto QP game
		// Display each game number as "i+1".
		// Display each element of the rows, zero padded
		for (int i = 0; i < lottoArray.length; i++) {
			System.out.printf("(%-2d)   ", i + 1);
			for (int j = 0; j < lottoArray[i].length; j++) {
				System.out.printf(" %02d ", lottoArray[i][j]);
			} // END inner for-loop
			System.out.println();
		} // END outer for-loop

		displayFooter();

		return;
	} // END displayTicket

	/**
	 * Displays the heading of the Lotto ticket.
	 */
	protected void displayHeading() {
		System.out.println("-----------------------------------");
		System.out.println("-------------- " + GAME_NAME + " --------------");
		Date today = new Date();
		System.out.printf("    %s", today);
		System.out.println();
		System.out.println();
	} // END displayHeading

	/**
	 * Displays the footer of the lottery ticket which includes the odds of winning.
	 */
	protected void displayFooter() {
		BigInteger odds = new BigInteger("1");
		odds = calculateOdds();

		System.out.println();
		System.out.printf("Odds of Winning: 1 in %,d\n", odds);
		System.out.println("--------- (c) " + STORE_NAME + " ----------");
		System.out.println("-----------------------------------");
	} // END displayFooter

	/**
	 * This method executes the following algorithm to get the odds of winning a
	 * quick pick lottery game: SELECTION_POOL_SIZE! / SELECTION_COUNT! *
	 * (SELECTION_POOL_SIZE - SELECTION_COUNT)!
	 * 
	 * We use factorial for the selection pool size because you have a 1 in
	 * selection_pool_size chance of matching a number. We have to divide by the
	 * order of the SELECTION_COUNT! because we must take into account that it
	 * doesn't matter in which order the numbers are drawn to win. there are, in
	 * this case, 6! = 720 different ways they could be drawn. Then you multiply by
	 * the number of alternatives - the number of choices. This is called the
	 * combination function.
	 * 
	 * @return A BigInteger that is the result of calculating the odds of winning a
	 *         quick pick lottery ticket.
	 */
	private BigInteger calculateOdds() {
		BigInteger odds = new BigInteger("1");
		odds = factorial(SELECTION_POOL_SIZE).divide( // start of denominator
				factorial(SELECTION_COUNT).multiply(factorial(SELECTION_POOL_SIZE - SELECTION_COUNT)) // end denominator
		); // end division of top and bottom
		return odds;
	} // END calculateOdds

	/**
	 * Calculates a factorial of a given number with BigInteger
	 * 
	 * @param num The number we want the factorial of.
	 * @return The result of the factorial in BigInteger form.
	 */
	private BigInteger factorial(int num) {
		BigInteger sum = new BigInteger("1");
		for (int i = num; i > 1; i--) {
			sum = sum.multiply(BigInteger.valueOf(i));
		}
		return sum;
	} // END factorial

	/**
	 * @param args String passed from the command line
	 */
	public static void main(String[] args) {
		// takes an optional command line parameter specifying number of QP games to be
		// generated
		// By default, generate 1
		int numberOfGames = DEFAULT_GAME_COUNT;

		if (args.length > 0) { // if user provided an arg, assume it to be a game count
			numberOfGames = Integer.parseInt(args[0]); // [0] is the 1st element!
			GAME_COUNT = numberOfGames;
		} // END if
//			 String input = JOptionPane.showInputDialog("How many games would you like to play?");
//			 try {
//				 GAME_COUNT = Integer.parseInt(input);
//			 } catch (NumberFormatException err) {
//				 System.out.println("You did not enter an integer.\n");
//				 System.exit(1);
//			 }

		LottoQuickPicker lotto = new LottoQuickPicker(numberOfGames);

		lotto.displayTicket();
	} // END main

} // END LottoQuickPicker class
