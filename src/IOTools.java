import java.time.*;
import java.time.format.*;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * ==============================================================================================================
 * Auxiliary class. Methods to control data input
 *
 * @author Abelardo Martínez. Updated by Pau Miñana. Based on and modified from Emiliano and Laia Méndez Martínez
 * @version 26.10b
 * ==============================================================================================================
 */
public class IOTools {
	
	/**
	 * ----------------------------------------
	 * GLOBAL VARIABLES AND CONSTANTS
	 * ---------------------------------------- 
	 */	
	/* We can only use ONE Scanner. When you call sc.close() it closes your underlying stream, which is System.in
	 * Once you close System.in the only way to get it back is to restart your program
	 * We can pass the instance to every method or create this static global
         */
	public static Scanner scKeyboard = new Scanner(System.in);

	/**
	 * ----------------------------------------- 
	 * DATATYPE METHODS
	 * -----------------------------------------
	 */
	/**
	 * ----------------------------------------- 
	 * STRING
	 * -----------------------------------------
	 */
	/* --------------------------------------------------------------------------
	 * Displays a text and waits for other text on the keypad.
	 * --------------------------------------------------------------------------
	 */
	public static String askString(String stQuestion) 
	{
		System.out.print(stQuestion + ": ");
		return scKeyboard.nextLine();
	}

	/* -----------------------------------------------------------------------------
	 * Displays a text and waits for other text on the keypad with a maximum length.
	 * Based on and modified from Sergio Adell Jorge
	 * -----------------------------------------------------------------------------
	 */
	public static String askString(String stQuestion, int iMaxlength) 
	{
		boolean bToolong = false;
		String stReply = "";
		
		do {
			bToolong = false;			
			System.out.print(stQuestion + ": ");
			stReply = scKeyboard.nextLine();
			// We verify if the answer is longest than the maximum length
			if (stReply.length() > iMaxlength) {
				bToolong = true;
				System.out.println("====> ERROR: value too long. Maximum " + iMaxlength + " characters.");
			}
	   } while (bToolong);
		return stReply;
	}

	/*
	 * --------------------------------------------------------------------------------------
	 * Displays a text and waits for other text on the keypad (additional arguments as accepted answers)
	 * --------------------------------------------------------------------------------------
	 */
	public static String askString(String stQuestion, String... stValidAnswers)
	{
	    String stReply = "";
	    boolean bValid;

	    do {
	        System.out.print(stQuestion + ": ");
	        stReply = scKeyboard.nextLine().trim();

	        bValid = false;

	        for (String stAnswer : stValidAnswers) {
	            if (stReply.equalsIgnoreCase(stAnswer)) {
	                bValid = true;
	                break;
	            }
	        }

	        if (!bValid) {
	            System.out.println("====> ERROR: invalid answer. Allowed values: "
	                    + String.join(", ", stValidAnswers) + ".");
	        }

	    } while (!bValid);

	    return stReply;
	}

	/*
	 * --------------------------------------------------------------------------------------
	 * Displays a text and waits for other text on the keypad. (REQUIRED)
	 * --------------------------------------------------------------------------------------
	 */
	public static String askStringRequired(String stQuestion) 
	{
		String stReply = "";
		
		while (stReply.isBlank()) {
			System.out.print(stQuestion + ": ");
			stReply = scKeyboard.nextLine();
			//check if the string is empty or contain only spaces
			if (stReply.isBlank()) {
				System.out.println("====> ERROR: this field can't be empty. Try again.");
			}
		}
		return stReply;
	}

	/**
	 * ----------------------------------------- 
	 * DATE
	 * -----------------------------------------
	 */
	/* -----------------------------------------------------------------------------------------
	 * Displays a text and waits for a date by keyboard. (DEFAULT format yyyy/mm/dd)  20261015
	 * -----------------------------------------------------------------------------------------
	 */
	public static LocalDate askDate(String stQuestion) 
	{
		boolean bIsValid = false;
		LocalDate ldtDate = null;
		
		do {
			String stValue = askString(stQuestion); // read the above method question
			try {
				ldtDate = LocalDate.parse(stValue, DateTimeFormatter.BASIC_ISO_DATE);
				bIsValid = true;
			} catch (DateTimeParseException nfe) {
				System.out.println("====> ERROR: " + stValue + " is not a valid date. EX: yyyymmdd");
			}
		} while (!bIsValid);
		return ldtDate;
	}

	/* -----------------------------------------------------------------------------------------
	 * Displays a text and waits for a date by keyboard. (SPECIFIC user format)
	 * -----------------------------------------------------------------------------------------
	 */
	public static LocalDate askDate(String stQuestion, String stDateFormat) 
	{
		boolean bIsValid = false;
		LocalDate ldtDate = null;
		
		do {
			String stValue = askString(stQuestion); // read the above method question
			try {
				ldtDate = LocalDate.parse(stValue, DateTimeFormatter.ofPattern(stDateFormat));
				bIsValid = true;
			} catch (DateTimeParseException nfe) {
				System.out.println("====> ERROR: " + stValue + " is not a valid date. Required format: " + stDateFormat);
			}
		} while (!bIsValid);
		return ldtDate;
	}
	
	/*
	 * | Fecha escrita | Patrón Java | Cómo definirlo                            |
		| ------------- | ----------- | ----------------------------------------- |
		| 20261015      | uuuuMMdd    | DateTimeFormatter.BASIC_ISO_DATE          |
		| 2026-10-15    | uuuu-MM-dd  | DateTimeFormatter.ISO_LOCAL_DATE          |
		| 15/10/2026    | dd/MM/uuuu  | DateTimeFormatter.ofPattern("dd/MM/uuuu") |
		| 15-10-2026    | dd-MM-uuuu  | DateTimeFormatter.ofPattern("dd-MM-uuuu") |
		| 15.10.2026    | dd.MM.uuuu  | DateTimeFormatter.ofPattern("dd.MM.uuuu") |
		| 10/15/2026    | MM/dd/uuuu  | DateTimeFormatter.ofPattern("MM/dd/uuuu") |
	 */

	/* -----------------------------------------------------------------------------------------
	 * Sets the format passed as a parameter to a date and returns it as a text string.
	 * Based on and modified from Sergio Adell Jorge
	 * -----------------------------------------------------------------------------------------
	 */
	public static String formatDate(LocalDate ldtDate, String stDateFormat) 
	{       	
		return ldtDate.format(DateTimeFormatter.ofPattern(stDateFormat));
	}

	/* -----------------------------------------------------------------------------------------
	 * Return the number of days between two dates 
	 * -----------------------------------------------------------------------------------------
	 */
	public static long daysBetweenDates(LocalDate ldtInitial, LocalDate ldtFinal) 
	{       	
		return ChronoUnit.DAYS.between(ldtInitial, ldtFinal);
	}

	/**
	 * ----------------------------------------- 
	 * DATETIME
	 * -----------------------------------------
	 */
	/* --------------------------------------------------------------------------------------------
	 * Displays a text and waits for a datetime by keyboard. (DEFAULT format yyyy/mm/dd hh:mm:ss)
	 * --------------------------------------------------------------------------------------------
	 */
	public static LocalDateTime askDateTime(String stQuestion) 
	{
		boolean bIsValid = false;
		LocalDateTime ldtDateTime = null;
		
		do {
			String stValue = askString(stQuestion); // read the above method question
			try {
				ldtDateTime = LocalDateTime.parse(stValue, DateTimeFormatter.BASIC_ISO_DATE);
				bIsValid = true;
			} catch (DateTimeParseException nfe) {
				System.out.println("====> ERROR: " + stValue + " is not a valid date");
			}
		} while (!bIsValid);
		return ldtDateTime;
	}

	/* -----------------------------------------------------------------------------------------
	 * Displays a text and waits for a date by keyboard. (SPECIFIC user format)
	 * -----------------------------------------------------------------------------------------
	 */
	public static LocalDateTime askDateTime(String stQuestion, String stDateFormat) 
	{
		boolean bIsValid = false;
		LocalDateTime ldtDateTime = null;
		
		do {
			String stValue = askString(stQuestion); // read the above method question
			try {
				ldtDateTime = LocalDateTime.parse(stValue, DateTimeFormatter.ofPattern(stDateFormat));
				bIsValid = true;
			} catch (DateTimeParseException nfe) {
				System.out.println("====> ERROR: " + stValue + " is not a valid date. Required format: " + stDateFormat);
			}
		} while (!bIsValid);
		return ldtDateTime;
	}

	/* -----------------------------------------------------------------------------------------
	 * Sets the format passed as a parameter to a datetime and returns it as a text string.
	 * Based on and modified from Sergio Adell Jorge
	 * -----------------------------------------------------------------------------------------
	 */
	public static String formatDateTime(LocalDateTime ldtDateTime, String stDateFormat) 
	{       	
		return ldtDateTime.format(DateTimeFormatter.ofPattern(stDateFormat));
	}

	/* -----------------------------------------------------------------------------------------
	 * Return the number of days between two datetimes 
	 * -----------------------------------------------------------------------------------------
	 */
	public static long daysBetweenDatetimes(LocalDateTime ldtInitial, LocalDateTime ldtFinal) 
	{       	
		return ChronoUnit.DAYS.between(ldtInitial, ldtFinal);
	}


	/**
	 * ----------------------------------------- 
	 * INTEGER
	 * -----------------------------------------
	 */
	/*
	 * -----------------------------------------------------------------------------------------
	 * Displays a text and waits for an integer by keyboard.
	 * -----------------------------------------------------------------------------------------
	 */
	public static int askInt(String stQuestion) 
	{
		boolean bRepeat = true;
		int iResult = -1;
		
		do {
			String stValue = askString(stQuestion); // read the above method question
			try {
				iResult = Integer.parseInt(stValue);
				bRepeat = false;
			} catch (NumberFormatException nfe) {
				System.out.println("====> ERROR: " + stValue + " is not a valid integer number");
			}
		} while (bRepeat);
		return iResult;
	}

	/*
	 * -----------------------------------------------------------------------------------------
	 * Displays a text and waits for an integer by keyboard between a minimum and a maximum.
	 * -----------------------------------------------------------------------------------------
	 */
	public static int askInt(String stQuestion, int iMin, int iMax) 
	{
		boolean bRepeat = true;
		int iResult = -1;
		
		do {
			String stValue = askString(stQuestion); // read the above method question
			try {
				iResult = Integer.parseInt(stValue);
				bRepeat = (iResult < iMin) || (iResult > iMax);
				if (bRepeat) {
					System.out.println("====> ERROR: " + iResult + " out of range");
				}
			} catch (NumberFormatException nfe) {
				System.out.println("====> ERROR: " + stValue + " is not a valid integer number");
			}
		} while (bRepeat);
		return iResult;
	}

	/**
	 * ----------------------------------------- 
	 * LONG
	 * -----------------------------------------
	 */
	/*
	 * -------------------------------------------------------------------------------------------
	 * Displays a text and waits for a long by keyboard.
	 * -------------------------------------------------------------------------------------------
	 */
	public static long askLong(String stQuestion) 
	{
		boolean bRepeat = true;
		long lResult = -1;
		
		do {
			String stValue = askString(stQuestion);
			try {
				lResult = Long.parseLong(stValue);
				bRepeat = false;
			} catch (NumberFormatException nfe) {
				System.out.println("====> ERROR: " + stValue + " is not a valid long number");
			}
		} while (bRepeat);
		return lResult;
	}

	/*
	 * -------------------------------------------------------------------------------------------
	 * Displays a text and waits for a long by keyboard between a minimum and a maximum.
	 * -------------------------------------------------------------------------------------------
	 */
	public static long askLong(String stQuestion, long lMin, long lMax) 
	{
		boolean bRepeat = true;
		long lResult = -1;
		
		do {
			String stValue = askString(stQuestion);
			try {
				lResult = Long.parseLong(stValue);
				bRepeat = (lResult < lMin) || (lResult > lMax);
				if (bRepeat) {
					System.out.println("====> ERROR: " + lResult + " out of range");
				}
			} catch (NumberFormatException nfe) {
				System.out.println("====> ERROR: " + stValue + " is not a valid long number");
			}
		} while (bRepeat);
		return lResult;
	}

	/**
	 * ----------------------------------------- 
	 * DOUBLE
	 * -----------------------------------------
	 */
	/*
	 * -------------------------------------------------------------------------------------------
	 * Displays a text and waits for a double by keyboard.
	 * -------------------------------------------------------------------------------------------
	 */
	public static double askDouble(String stQuestion) 
	{	
		boolean bRepeat = true;
		double dResult = -1;
		
		do {
			String stValue = askString(stQuestion);
			try {
				dResult = Double.parseDouble(stValue);
				bRepeat = false;
			} catch (NumberFormatException nfe) {
				System.out.println("====> ERROR: " + stValue + " is not a valid double number");
			}
		} while (bRepeat);
		return dResult;
	}

	/*
	 * -------------------------------------------------------------------------------------------
	 * Displays a text and waits for a double by keyboard between a minimum and a maximum.
	 * -------------------------------------------------------------------------------------------
	 */
	public static double askDouble(String stQuestion, double dMin, double dMax) 
	{
		boolean bRepeat = true;
		double dResult = -1;
		
		do {
			String stValue = askString(stQuestion);
			try {
				dResult = Double.parseDouble(stValue);
				bRepeat = (dResult < dMin) || (dResult > dMax);
				if (bRepeat) {
					System.out.println("====> ERROR: " + dResult + " out of range");
				}
			} catch (NumberFormatException nfe) {
				System.out.println("====> ERROR: " + stValue + " is not a valid double number");
			}
		} while (bRepeat);
		return dResult;
	}

	/**
	 * ----------------------------------------- 
	 * FLOAT
	 * -----------------------------------------
	 */
	/*
	 * -------------------------------------------------------------------------------------------
	 * Displays a text and waits for a float by keyboard.
	 * -------------------------------------------------------------------------------------------
	 */
	public static float askFloat(String stQuestion) 
	{
		boolean bRepeat = true;
		float fResult = -1;
		
		do {
			String stValue = askString(stQuestion);
			try {
				fResult = Float.parseFloat(stValue);
				bRepeat = false;
			} catch (NumberFormatException nfe) {
				System.out.println("====> ERROR: " + stValue + " is not a valid float number");
			}
		} while (bRepeat);
		return fResult;
	}

	/*
	 * -------------------------------------------------------------------------------------------
	 * Displays a text and waits for a float by keyboard between a minimum and a maximum.
	 * -------------------------------------------------------------------------------------------
	 */
	public static float askFloat(String stQuestion, float fMin, float fMax) 
	{
		boolean bRepeat = true;
		float fResult = -1;
		
		do {
			String stValue = askString(stQuestion);
			try {
				fResult = Float.parseFloat(stValue);
				bRepeat = (fResult < fMin) || (fResult > fMax);
				if (bRepeat) {
					System.out.println("====> ERROR: " + fResult + " out of range");
				}
			} catch (NumberFormatException nfe) {
				System.out.println("====> ERROR: " + stValue + " is not a valid float number");
			}
		} while (bRepeat);
		return fResult;
	}

	/**
	 * ----------------------------------------- 
	 * MENU METHODS
	 * -----------------------------------------
	 */
	/*
	 * -----------------------------------------------------------------------------------------
	 *  Displays a text and waits for a number from a list of options.
	 * -----------------------------------------------------------------------------------------
	 */
	public static int askInt(String stQuestion, int... iValids) 
	{
		boolean bAsk = true;
		int iResult;
		
		do {
			iResult = askInt(stQuestion, iValids[0], iValids[iValids.length - 1]);
			bAsk = Arrays.binarySearch(iValids, iResult) < 0;
		} while (bAsk);
		return iResult;
	}

	/*
	 * -----------------------------------------------------------------------------------------
	 *  Displays a menu and waits for the user to choose an option.
	 * -----------------------------------------------------------------------------------------
	 */
	public static int showMenu(String stTitle, String... stOptions) 
	{
		System.out.println();
		System.out.println(stTitle);
		System.out.println("========================================================================");
		int iResult;
		
		if ((stOptions == null) || (stOptions.length == 0)) {
			iResult = -1;
		} else {
			int iNumOptions = 0;
			for (int ii = 0; ii < stOptions.length; ii++) {
				if ((stOptions[ii] == null) || ("".equals(stOptions[ii].trim()))) {
					System.out.println();
				} else {
					System.out.println("  " + iNumOptions + ". " + stOptions[ii]);
					iNumOptions++;
				}
			}
			System.out.println("========================================================================");
			iResult = askInt("    Select an option", 0, iNumOptions-1);
			System.out.println();
		}
		return iResult;
	}

	/*
	 * -------------------------------------------------------------------------------------------
	 * Message showed after doing some action to allow the user to see the result in the console. 
	 * -------------------------------------------------------------------------------------------
	 */
	public static void pressEnterToContinue() 
	{
	    System.out.println(); // Just a separator
	    System.out.println("Press Enter to continue...");
	    scKeyboard.nextLine();
	}
}
