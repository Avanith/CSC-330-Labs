package edu.cuny.csi.csc330.lab1;

/**
 * Generates a unicode textual chessboard 
 * Reference:  https://en.wikipedia.org/wiki/Chess_symbols_in_Unicode 
 * @author Daniel Targonski
 *
 */
public class ChessBoard {
	
	// Array of pieces values Rook, Knight, Bishop, King, Queen, Bishop, Knight, Rook 
	static private final  char [] WHITE_PIECES = {
		'\u2656', '\u2658', '\u2657', '\u2655', '\u2654', '\u2657', '\u2658', '\u2656'
	};
	
	// qUEEN 
	static private final  char [] BLACK_PIECES = {
			'\u2656', '\u2658', '\u2657', '\u2654', '\u2655', '\u2657', '\u2658', '\u2656'
		};
	
	// unicode value of a pawn 
	static private  final char PAWN = '\u2659'; 
	// our choice for "empty square" - medium rectangle ... 
	static private  final char EMPTY = '\u25AD'; 
	
	private static final int ROWS = 8; 
	
	private static final String HEADING = 
			"-----------------------------------------\n"
			+ "        Unicode Symbols Chessboard\n" 
			+ "-----------------------------------------\n";
	
	// Black piece values are equiv white pieces + 6
	private static final int BLACK_PIECE_DISPLACEMENT = 6;
	
	static private void displayHeading() {
		System.out.println(HEADING.toUpperCase());
	}
	
	private static void displayBoard() {
		for(int row = ROWS ; row >= 1  ; --row ) {
			System.out.printf("%-5d", row);
			
			/* After the row number gets printed out, if we are on the 8th, 7th, 2nd, or 1st ranks,
			 * we want to show pieces. If we are in-between the 2nd and 7th rows, we show the empty square.
			 *  */
			for (int file = 0; file < WHITE_PIECES.length ; file++) {
				if (row == 8) { // PRINT BLACK pieces
					System.out.printf("%3c", BLACK_PIECES[file] + BLACK_PIECE_DISPLACEMENT);
				}
				else if(row == 7) { // PRINT BLACK PAWNS
					System.out.printf("%3c", PAWN + BLACK_PIECE_DISPLACEMENT);
				}
				else if(row == 2) { // print white pawns
					System.out.printf("%3c", PAWN);
				}
				else if(row == 1) { // print out whites pieces
					System.out.printf("%3c", WHITE_PIECES[file]);
				}
				else { // print out squares
					System.out.printf("%3c", EMPTY);
				} // END IF-ELSE
				
			} // END INNER FOR-LOOP
			// New line
			System.out.println("");
			
		} // END OUTER FOR-LOOP
		
	} // END DISPLAYBOARD
	
	// Only Public Method Used Directly By main() 
	static public void display() {
		displayHeading(); // display heading 
		displayBoard(); 
	
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// invoke display method 
		ChessBoard.display(); 
	}
	
}