package edu.cuny.csi.csc330.lab6;

public class QuickPickerException extends Exception {

	// static publicly defined error codes
	public static int UNSET = 0;
	public static int INVALID_FILENAME = 1;

	protected int code;

	/**
	 * Returns the error code number
	 * 
	 * @return int of the error code number
	 */
	public int getCode() {
		return code;
	}

	// static publicly defined exception messages
	public static String[] MESSAGE = {
			"Code Unspecified",
			"No .properties file name was passed",
			".properties file of passed name was not found.",
			"A required property is missing from the specified .properties file.",
			"The \"GameName\" is missing from the specified .properties file.",
			"The \"Pool1UniqueCount\" Property is missing from the specified .properties file.",
			"The \"Pool1TotalSize\" Property is missing from the specified .properties file.",
			"The \"Pool2UniqueCount\" Property is missing from the specified .properties file.",
			"The \"Pool2TotalSize\" Property is missing from the specified .properties file.",
			"The \"Vendor\" Property is missing from the specified .properties file."
			};

	/**
	 * Default constructor
	 */
	public QuickPickerException() {
	}

	/**Constructor with string error message param
	 * 
	 * @param msg String This will be the error message.
	 */
	public QuickPickerException(String msg) {
		super(msg);
	}

	/** Constructor with string error message param and
	 * an int error code
	 * 
	 * @param msg  This will be the error message.
	 * @param code The error code.
	 */
	public QuickPickerException(String msg, int code) {
		super(msg);
		this.code = code;
	}

	@Override
	public String toString() {
		return "QuickPickerException [code=" + code + ", toString()=" + super.toString() + "]\n" + MESSAGE[code];
	}

	public static void main(String[] args) {
		QuickPickerException exc = new QuickPickerException("QuickPicker Exception message is...");
		System.out.println(exc);
		
		QuickPickerException exc2 = new QuickPickerException("QuickPicker Exception message is...", 2);
		System.out.println(exc2);
		
		QuickPickerException exc3 = new QuickPickerException("QuickPicker Exception message is...", 3);
		System.out.println(exc3);
	}

}
