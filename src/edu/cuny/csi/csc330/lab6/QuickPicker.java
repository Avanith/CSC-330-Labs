/*Lotto Quick Picking Game
 * Takes in a mandatory command line argument of a .properties file that should have
 * lotto game specifications and a second optional argument of the number of games to be played.
 */

package edu.cuny.csi.csc330.lab6;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Date;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import edu.cuny.csi.csc330.util.Randomizer;

public class QuickPicker {

	// constants specific to current game - BUT NOT ALL GAMES
	public final static int DEFAULT_GAME_COUNT = 1;
	private static int GAME_COUNT = 1;
	private static String GAME_NAME = "LOTTO";
	private static int SELECTION_POOL_SIZE = 59;
	private static int SELECTION_POOL_SIZE2 = 0;
	private static int SELECTION_COUNT = 6;
	private static int SELECTION_COUNT2 = 0;
	private static String VENDOR = "Dan's Deli";
	private int lottoArray[][] = new int[GAME_COUNT][SELECTION_COUNT];
	private int lottoArray2[][] = new int[GAME_COUNT][SELECTION_COUNT2];
	private static String propFileName;

	/**
	 * Parses through a .properties file to get lotto game specifications.
	 * 
	 * @throws QuickPickerException     Various reasons i.e. property specification missing
	 * @throws MissingResourceException If the .property file does not exist.
	 * @throws NullPointerException     If no filename is provided.
	 * @throws NumberFormatException    If the parseint hits a letter.
	 */
	private static void initFromPropBundle()
			throws QuickPickerException, MissingResourceException, NullPointerException, NumberFormatException {

		try {
			ResourceBundle bundle = ResourceBundle.getBundle(propFileName);

			if (bundle.containsKey("GameName")) {
				GAME_NAME = bundle.getString("GameName");
			} else
				throw new QuickPickerException("Error: ", 4);
			if (bundle.containsKey("Pool1UniqueCount")) {
				SELECTION_COUNT = Integer.parseInt(bundle.getString("Pool1UniqueCount"));
			} else
				throw new QuickPickerException("Error: ", 5);
			if (bundle.containsKey("Pool1TotalSize")) {
				SELECTION_POOL_SIZE = Integer.parseInt(bundle.getString("Pool1TotalSize"));
			} else
				throw new QuickPickerException("Error: ", 6);
			if (bundle.containsKey("Pool2UniqueCount")) {
				SELECTION_COUNT2 = Integer.parseInt(bundle.getString("Pool2UniqueCount"));
			} else
				throw new QuickPickerException("Error: ", 7);
			if (bundle.containsKey("Pool2TotalSize")) {
				SELECTION_POOL_SIZE2 = Integer.parseInt(bundle.getString("Pool2TotalSize"));
			} else
				throw new QuickPickerException("Error: ", 8);
			if (bundle.containsKey("Vendor")) {
				VENDOR = bundle.getString("Vendor");
			} else
				throw new QuickPickerException("Error: ", 9);

		} catch (QuickPickerException e) {
			//System.out.println(e);
			e.printStackTrace();
			System.exit(e.getCode());
		} catch (NullPointerException | NumberFormatException e) {
			//System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
			System.exit(-1);
		} catch (MissingResourceException e) {
			throw new QuickPickerException("Error...", 2);
		}

	}

	/**
	 * Default Constructor that makes a single game.
	 */
	public QuickPicker() {
		init();
	} // END LOTTOQUICKPICKER

	/**
	 * Constructor that creates "games" number of games.
	 */
	public QuickPicker(int games) {
		GAME_COUNT = games;
		init();
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

	private int[][] uniqueNumArray2() {
		int[][] uniqueArray = new int[GAME_COUNT][SELECTION_COUNT2];

		for (int i = 0; i < GAME_COUNT; i++)
			uniqueArray[i] = generateUniqueArray2();

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

	private static int[] generateUniqueArray2() {
		int[] array = new int[SELECTION_COUNT2];
		int temp;

		for (int i = 0; i < array.length; i++) {
			temp = Randomizer.generateInt(1, SELECTION_POOL_SIZE2);
			// if the array doesn't have the number, then add the number to the array.
			// can write separate method for this
			while (arrayHasNumber(temp, array)) {
				temp = Randomizer.generateInt(1, SELECTION_POOL_SIZE2);
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
	private void init() {
		lottoArray = Arrays.copyOf(uniqueNumArray(), lottoArray.length);
		lottoArray2 = Arrays.copyOf(uniqueNumArray2(), lottoArray2.length);
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
			System.out.printf("(%2d)   ", i + 1);
			for (int j = 0; j < lottoArray[i].length; j++) {
				System.out.printf(" %02d ", lottoArray[i][j]);
			} // END inner for-loop

			// Only display the second array if there was a second pool selection.
			if (SELECTION_COUNT2 > 0) {
				System.out.print("   ((");
				for (int k = 0; k < lottoArray2[i].length; k++) {
					System.out.printf(" %02d ", lottoArray2[i][k]);
				}
				System.out.print("))");
			}

			System.out.println();
		} // END outer for-loop

		displayFooter();

		return;
	} // END displayTicket

	/**
	 * Displays the heading of the Lotto ticket.
	 */
	protected void displayHeading() {
		System.out.println("--------------------------------------------------");
		System.out.println("----------------- " + GAME_NAME + " -----------------");
		Date today = new Date();
		System.out.printf("         %s%n%n", today);
	} // END displayHeading

	/**
	 * Displays the footer of the lottery ticket which includes the odds of winning.
	 */
	protected void displayFooter() {
		BigInteger odds = new BigInteger("1");
		odds = calculateOdds();

		System.out.println();
		System.out.printf("         Odds of Winning: 1 in %,d\n", odds);
		System.out.println(" --------- (c) " + VENDOR + " --------------");
		System.out.println("--------------------------------------------------");
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
	 * If there is a second pool, you multiply the first pools odds by the second
	 * pools odds to get the final result.
	 * 
	 * @return A BigInteger that is the result of calculating the odds of winning a
	 *         lottery ticket considering both pools.
	 */
	private BigInteger calculateOdds() {
		BigInteger odds = new BigInteger("1");
		odds = factorial(SELECTION_POOL_SIZE).divide( // start of denominator
				factorial(SELECTION_COUNT).multiply
				(factorial(SELECTION_POOL_SIZE - SELECTION_COUNT)) // end denominator
		); // end division of top and bottom

		if (SELECTION_COUNT2 > 0)
			odds = odds.multiply(factorial(SELECTION_POOL_SIZE2).divide( // start of denominator
					factorial(SELECTION_COUNT2).multiply
					(factorial(SELECTION_POOL_SIZE2 - SELECTION_COUNT2)))); // end denom

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
	 * Reads in command line arguments to get a .properties filename for lotto game
	 * specifications.
	 * 
	 * @param args String array of command line arguments
	 * @throws QuickPickerException will throw is there is no prop file name passed
	 * @throws NumberFormatException will throw if second arg is not a number
	 */
	public static void readCmdLineArgs(String[] args) throws QuickPickerException, NumberFormatException {
		if (args.length > 0) {
			propFileName = args[0];
		} else {
			throw new QuickPickerException("Error Code Message: ", 1);
		}
		if (args.length > 1) {
			GAME_COUNT = Integer.parseInt(args[1]);
		}
	}

	/**
	 * @param args String passed from the command line
	 */
	public static void main(String[] args) {

		try {
			readCmdLineArgs(args);
			initFromPropBundle();
		} catch (QuickPickerException e) {
			e.printStackTrace();
			System.exit(-1);
		}

		QuickPicker lotto = new QuickPicker(GAME_COUNT);

		lotto.displayTicket();
	} // END main

} // END LottoQuickPicker class
