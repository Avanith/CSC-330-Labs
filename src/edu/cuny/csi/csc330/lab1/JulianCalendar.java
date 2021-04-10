package edu.cuny.csi.csc330.lab1;

/**
 * Generates a non-leap year perpetual Julian Calendar
 * 
 * @author Daniel Targonski
 *
 */
public class JulianCalendar {

	// Max number of Days in a month
	static private final int MAX_DAY = 31;

	// abbreviated Month names
	static private final String[] MONTH_NAMES = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct",
			"Nov", "Dec" };

	// day length of each month
	static private final int[] MONTH_SIZES = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	/**
	 * display The "DAY" Column Heading
	 */
	static private void displayDayHeading() {
		System.out.printf("%6s", "Day");

	}

	/**
	 * display Month names between Day .... Day
	 */
	static private void displayHeading() {
		displayDayHeading();

		for (int i = 0; i < MONTH_NAMES.length; ++i) {
			System.out.printf("%5s", MONTH_NAMES[i]);
		}

		displayDayHeading();
	}

	static public void display() {
		displayHeading(); // display heading
		System.out.print("\n"); // create a space down from the heading

		int rowCount = MAX_DAY; // 31
		int colCount = MONTH_NAMES.length + 2; // 14
		int dayCounter = 1;
		int monthTracker = 0;
		int[][] displayArr = new int[rowCount][colCount]; // 31 rows, 14 columns

		// We'll cycle through with column-major-order.
		// INNER LOOP WILL BE MAX DAYS, rows
		// OUTER LOOP IS 14 FOR THE 2 OUTER DAYS COLUMNS + 12 MONTHS, columns.
		// we must first populate the displayArr, otherwise we cannot target columns
		// first to fill or display
		// which we must do since we need to cycle through the columns before rows.
		for (int col = 0; col < MONTH_NAMES.length + 2; col++) {
			for (int row = 0; row < MAX_DAY; row++) {
				// The 0th and 13th columns are the Day columns that we fill with 1-31
				if (col == 0 || col == 13) {
					displayArr[row][col] = row + 1;
				}
				// If we're on the 1-12th columns && the row number is <= MONTH_SIZES[col],
				// we fill the column with the dayCounter. col represents the number month we're
				// on.

				else if (row < MONTH_SIZES[monthTracker]) {
					displayArr[row][col] = dayCounter;
					dayCounter++;
				} else {// if dayCounter == MONTH_SIZES[j], do not increment
						// day counter and fill the position with a 000
					displayArr[row][col] = 0;
				}
			} // END INNER FOR LOOP

			// this works because the else-if won't execute if col is at 0 or 13
			// Which means that we don't have to worry about the MONTH_SIZES array going out
			// of bounds
			// We have to do this because the 0th column isn't the start of the months, the
			// 1st is.
			if (col > 0 && col < 12) {
				monthTracker++; // or monthTracker = col - 1;
			}
		} // END OUTER FOR LOOP

		// Display Array
		// displayArr.length gives you the height (rows) of the 2D array.
		// displayArr[row].length gives you the width (columns) of the 2D array.
		for (int row = 0; row < displayArr.length; row++) {
			for (int col = 0; col < displayArr[row].length; col++) {
				if (col == 0 || col == 13)
					System.out.printf("%6d", displayArr[row][col]);
				else
					System.out.printf("  %03d", displayArr[row][col]);

			}
			System.out.println(); // make new line
		} // End outer for-loop
	} // END JULIAN CALENDAR CLASS

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// invoke display method
		JulianCalendar.display();
	}
}