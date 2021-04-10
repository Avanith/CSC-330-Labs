/* NumericAnalyzer.java
 *  This program will accept a list of 1 or more numbers as command line
 *  arguments. It will display information such as the number of arguments passed,
 *  the minimum, max, range, sum, mean, median, variance, and standard deviation
 *  of the integer array.
 */

package edu.cuny.csi.csc330.lab2;

import java.util.Arrays;

public class NumericAnalyzer {

	// ------------------- ATTRIBUTES -------------------
	private int[] numbers;
	private int count, min, max, range, sum;
	private double mean, median, variance, standardDeviation;

	// ------------------- END ATTRIBUTES -------------------

	// ------------------- CONSTRUCTOR ------------------- 
	/*
	 * The constructor will accept a integer array that will come from the command
	 * line string "args" array. It will call the setNumbers method.
	 */
	public NumericAnalyzer(int[] numbers) {
		this.numbers = numbers;
		Arrays.sort(this.numbers);
	} // ------------------- END CONSTRUCTOR ------------------- 

	// ------------------- METHODS -------------------

	/**
	 * @return the numbers
	 */
	public int[] getNumbers() {
		return numbers;
	}

	/**
	 * Setter for private integer array "numbers"
	 * 
	 * @param numbers the numbers to set
	 */
	public void setNumbers(int[] numbers) {
		this.numbers = numbers;
	}

	/*
	 * This method will parse the string array to check for numeric values and give
	 * an error if a non-numeric value is detected.
	 */
	public static boolean isNumeric(String data) {
		// test string for numeric value
		try { // parseInt will throw a NFE exception if the value is not an integer
			int i = (Integer.parseInt(data));
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	} // END ISNUMERIC

	// Will sort the numbers array.
	private void sortArray() {
		if (numbers.length > 1)
			Arrays.sort(numbers);
	} // End sortArray

	// Stores the length of the numbers array in the count variable.
	private void deriveCount() {
		this.count = numbers.length;
	} // END COUNT

	// MUST BE RUN AFTER SORT
	// gets the smallest value in the numbers array.
	private void deriveMin() {
		this.min = numbers[0];
	} // END MIN

	// assigns the highest number in the numbers array to the max var.
	private void deriveMax() {
		this.max = numbers[numbers.length - 1];
	} // END MAX

	// calculates the range of the numbers array and assigns it to the range var.
	private void deriveRange() {
		this.range = numbers[numbers.length - 1] - numbers[0];
	} // END RANGE

	private void deriveSum() {
		this.sum = 0;
		for (int num : this.numbers) {
			this.sum += num;
		}
	} // END SUM

	/*
	 * MUST RUN SUM BEFORE MEAN Calculates the mean of the numbers array and stores
	 * result in sum variable.
	 */
	private void deriveMean() {
		this.mean = this.sum / this.numbers.length;
	} // END MEAN

	// Calculates the median of the numbers array.
	private void deriveMedian() {
		// If odd number of values, assign median to middle value
		if (numbers.length % 2 != 0) {
			this.median = this.numbers[(this.numbers.length - 1) / 2];
		} else { // even number of values
			double leftOfMid, rightOfMid;
			leftOfMid = numbers[(numbers.length - 1) / 2];
			rightOfMid = numbers[numbers.length / 2];
			this.median = leftOfMid + rightOfMid / 2;
		}
	} // END MEDIAN

	/*
	 * MUST RUN MEAN BEFORE VARIANCE Subtract the mean from each value in the list.
	 * This gives you a measure of the distance of each value from the mean. Square
	 * each of these distances (and they’ll all be positive values), add all of the
	 * squares together, and divide that sum by the number of values (that is, take
	 * the average of these squared values).
	 */
	private void deriveVariance() {
		// Making a copy of our numbers array so we don't modify the original array.
		int[] tempArr = Arrays.copyOf(this.numbers, this.numbers.length);

		int sumOfSquares = 0;
		for (int i = 0; i < tempArr.length; i++) {
			tempArr[i] -= mean;
			tempArr[i] = (int) Math.pow(tempArr[i], 2);
			sumOfSquares += tempArr[i];
		}
		this.variance = sumOfSquares / tempArr.length;
	} // END VARIANCE

	// Calculates the standard deviation of the number array.
	private void deriveStandardDeviation() {
		this.standardDeviation = Math.sqrt(this.variance);
	} // END STANDARDDEVIATION

	/*
	 * The analyze method will call all the methods that will analyze the numbers
	 * array.
	 */
	private void analyze() {
		deriveCount();
		deriveMin();
		deriveMax();
		deriveRange();
		deriveSum();
		deriveMean();
		deriveMedian();
		deriveVariance();
		deriveStandardDeviation();
	} // END ANALYZE

	// Displays the numbers array and the analysis done on it.
	public void display() {
		String frmtString = "%-15s %,15d\n";
		
		for (int i = 0; i < numbers.length; i++) {
			System.out.printf(" %3d ", this.numbers[i]);
		} // END FOR LOOP
		System.out.println();
		System.out.println();

		System.out.printf(frmtString, "Count:", this.count);
		System.out.printf(frmtString, "Min:", this.min);
		System.out.printf(frmtString, "Max:", this.max);
		System.out.printf(frmtString, "Range:", this.range);
		System.out.printf(frmtString, "Sum:", this.sum);
		System.out.printf("Mean:               %,10.0f%n", this.mean);
		System.out.printf("Median:             %,10.0f%n", this.median);
		System.out.printf("Variance:           %,10.0f%n", this.variance);
		System.out.printf("Standard Deviation: %,10.0f%n", this.standardDeviation);

	} // END DISPLAY

	// ------------------- END METHODS -------------------

	public static void main(String[] args) {

		// If nothing is passed from the command line, exit program.
		if (args.length == 0) {
			System.err.println("No arguments passed!");
			System.exit(1);
		} // END IF STATEMENT

		// Create the integer array that will be passed into a Numeric Analyzer Object.
		int[] numbers = new int[args.length];
		for (int i = 0; i < args.length; i++) {

			// Test for numeric value
			if (!NumericAnalyzer.isNumeric(args[i])) {
				System.err.println("Expecting Numeric Data: " + args[i]);
				System.exit(2); // exit code for invalid data
			} // END IF STATEMENT

			numbers[i] = Integer.parseInt(args[i]);
		} // END FOR LOOP

		NumericAnalyzer analyzer = new NumericAnalyzer(numbers);
//		analyzer.sortArray();
		analyzer.analyze();
		// Display calls the helper methods, sortArray and analyze.
		analyzer.display();
		System.exit(0);

	} // END MAIN
} // END NUMERIC ANALYZER CLASS
