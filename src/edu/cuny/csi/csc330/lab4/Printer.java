/** A basic printer that will check for needed amount of ink and paper
 * before printing requested task.
 */

package edu.cuny.csi.csc330.lab4;

public class Printer {

	// ------------------------- Encapsulated classes -------------------------
	
	public class InkCartridge {
		
		/** Will model a printer Ink Cartridge
		 * 
		 */
		private InkCartridge() {
			blackInkLeft = 100;
			redInkLeft = 100;
			greenInkLeft = 100;
			blueInkLeft = 100;
		}
		

		private double blackInkLeft;
		private double redInkLeft;
		private double greenInkLeft;
		private double blueInkLeft;
		
		/**
		 * Outputs all the remaining ink levels.
		 */
		private void getInkStatus() {
			System.out.println("Remaining ink levels are: \n"
					+ "Blue Ink:  " + inkCartridge.blueInkLeft + "%\n"
					+ "Green Ink: " + inkCartridge.greenInkLeft + "%\n"
					+ "Red Ink:   " + inkCartridge.redInkLeft + "%\n"
					+ "Black Ink: " + inkCartridge.blackInkLeft + "%");
		}
		
		/**
		 *  Refills inks levels of ink cartridge.
		 */
		private void refillInk() {
			blackInkLeft = 100;
			redInkLeft = 100;
			greenInkLeft = 100;
			blueInkLeft = 100;
			System.out.println("All Ink has been filled to 100%!\n");
		}
		
		@Override
		public String toString() {
			return " [blackInkLeft=" + blackInkLeft + ", redInkLeft=" + redInkLeft + ", greenInkLeft="
					+ greenInkLeft + ", blueInkLeft=" + blueInkLeft + "]";
		}

	} // end class InkCartridge
	
	/** Models a printer paper tray.
	 *
	 */
	public class PaperTray {
		
		private PaperTray() {
			numberOfPapers = maxNumOfPapers;
			isOpen = false;
		}
		

		private boolean isOpen;
		private double numberOfPapers;
		int maxNumOfPapers = 500;
		
		/**
		 * Opens the paper tray.
		 */
		private void openPaperTray () {
			isOpen = true;
			System.out.println("Paper tray is open.");
		}
		
		/**
		 * Closes the paper tray.
		 */
		private void closePaperTray () {
			isOpen = false;
			System.out.println("Paper tray is closed.");
		}
		
		/**
		 * 
		 */
		private void displayNumberOfPapers() {
			System.out.println("Number of pages remaining: " + numberOfPapers);
		}
		private void refillPaper() {
			openPaperTray();
			
			numberOfPapers = maxNumOfPapers;
			System.out.println("Paper has been refilled to " + maxNumOfPapers + " pages.");
			
			closePaperTray();
			
			System.out.println();
		}
		
		@Override
		public String toString() {
			return " [isOpen=" + isOpen + ", numberOfPapers=" + numberOfPapers + ", maxNumOfPapers="
					+ maxNumOfPapers + "]";
		}


	} // end class PaperCartridge
	
	// ------------------------- END encapsulated classes -------------------------
	
	// ------------------------- Constructors -------------------------
	public Printer() {
		isOn = false;
		setPrinterName("Printer");
		System.out.println("New Printer instance created.");
	}
	public Printer(String printerName) {
		isOn = false;
		setPrinterName(printerName);
		System.out.println("New Printer instance created.");
	}
	// ------------------------- END Constructors -------------------------
	
	
	
	// ------------------------- Members/State -------------------------
	private boolean isOn;
	private String printerName;
	private PaperTray paperTray = new PaperTray();
	private InkCartridge inkCartridge = new InkCartridge();
	
	// ------------------------- END Members -------------------------
	
	// ------------------------- Methods -------------------------
	
	/**
	 * getter for the printer references name.
	 * @return printerName
	 */
	public String getPrinterName() {
		return printerName;
	}

	/**
	 * setter for the printer name.
	 * @param printerName:String sets the printerName
	 */
	public void setPrinterName(String printerName) {
		this.printerName = printerName;
	}

	/**
	 * Turns on the printer.
	 */
	public void turnOn() {
		isOn = true;
		System.out.println(this.getPrinterName() + " is now on.\n");
	}
	
	/**
	 * Turns off the printer.
	 */
	public void turnOff() {
		isOn = false;
		System.out.println(this.getPrinterName() + " is now off.\n");
	}
	
	/**
	 * Checks to see if the printer is on.
	 * @return boolean value, true if the printer is on and vice-versa.
	 */
	public boolean isOn() {
		return isOn;
	}
	
	/** Prints color pages.
	 * @param numOfCopies
	 * @return true if print was successful
	 */
	public boolean printColor(int numOfCopies) {
		if (!checkForEnough(numOfCopies, true))
			return false;
		
		System.out.println("Printing " + numOfCopies + " pages in color.");
		
		paperTray.numberOfPapers -= numOfCopies;
		inkCartridge.blueInkLeft -= numOfCopies * 0.2;
		inkCartridge.greenInkLeft -= numOfCopies * 0.2;
		inkCartridge.redInkLeft -= numOfCopies * 0.2;
		
		inkCartridge.getInkStatus();
		paperTray.displayNumberOfPapers();
		
		System.out.println("Color print successful!\n");
		
		return true;
				
	} // end printColor()
	
	/**
	 * Prints black and white pages.
	 * @param numOfCopies:int  the number of copies to be printed.
	 * @return boolean value, true if print was successful.
	 */
	public boolean printBlackandWhite(int numOfCopies) {
		if (!checkForEnough(numOfCopies, false))
			return false;
		
		System.out.println("Printing " + numOfCopies + " pages in black.");
		
		paperTray.numberOfPapers -= numOfCopies;
		inkCartridge.blackInkLeft -= numOfCopies * 0.2;

		
		inkCartridge.getInkStatus();
		paperTray.displayNumberOfPapers();
		
		System.out.println("Black print successful!\n");
		
		return true;
	}
	
	/** Checks the printer to see if it is on, and if it has enough ink and paper for the 
	 * requested printing job.
	 * 
	 * @param numOfCopies:int  the number of copies to check if printer has enough paper and ink for.
	 * @param color:boolean  pass true if you want to check for color printing, false for non-color printing
	 * @return boolean  true if there is enough paper and ink to do the printing job.
	 */
	public boolean checkForEnough(int numOfCopies, boolean color) {
		
		if (!this.isOn()) {
			System.err.println("You requested a print job but " + printerName + " is not on.\n");
			return false;
		}
		
		System.out.println("Checking for required ink and paper for requested job...");

		if ( (paperTray.numberOfPapers - numOfCopies) < 0) {
			System.err.println(printerName + " does not have enough paper to print " + 
				numOfCopies + " pages.");
			
			paperTray.displayNumberOfPapers();
			System.out.println("\n");
			
			return false;
		}
		
		if ( color == false && ((inkCartridge.blackInkLeft - (numOfCopies * 0.2)) < 0)) {
			System.err.println(printerName + " does not have enough black ink left to print " +
		numOfCopies + " pages.\n");
			
			inkCartridge.getInkStatus();
			System.out.println("\n");
			
			return false;
		}
		if ( color == true && ((inkCartridge.blueInkLeft - (numOfCopies * 0.2)) < 0)) {
			System.err.println(printerName + " does not have enough color ink left to print " +
		numOfCopies + " pages.\n");
			
			inkCartridge.getInkStatus();
			System.out.println("\n");
			
			return false;
		}
		System.out.println("Check complete, " + printerName + " has required resources. Initiating print job...\n");
		
		return true;

	} // END checkForEnough()
	
	/** Calls the refillPaper method of the paperCartridge instance.
	 * 
	 */
	public void refillPaper() {
		paperTray.refillPaper();
	}
	
	/** Calls the refillInk method of the inkCartridge instance.
	 * 
	 */
	public void refillInk() {
		inkCartridge.refillInk();
	}

	
	@Override
	public String toString() {
		return "Printer [isOn=" + isOn + ", printerName=" + printerName + ",\npaperTray=" + paperTray
				+ ",\ninkCartridge=" + inkCartridge + "]\n\n";
	}


	// ------------------------- END Methods -------------------------
	
	
	public static void main(String[] args) {
		
		Printer printer = new Printer("Dan's Printer");

		System.out.println(printer.toString());
		
		printer.printColor(500);
		
		printer.turnOn();
		
		printer.printColor(35);
		
		System.out.println(printer.toString());
		
		printer.printColor(465);
		printer.printColor(31);
		printer.refillPaper();
		printer.printColor(5);
		printer.refillInk();
		printer.printColor(153);
		
		System.out.println(printer.toString());
		
		printer.printBlackandWhite(300);
		printer.refillPaper();
		printer.printBlackandWhite(250);
		printer.refillInk();
		printer.printBlackandWhite(250);
		
		printer.turnOff();
		
		System.out.println(printer.toString());
		
	}
}
