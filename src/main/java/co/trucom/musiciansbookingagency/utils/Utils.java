package co.trucom.musiciansbookingagency.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

	private Utils() {
	}

	public static Date stringToDate(String dateStr) {
		try {
			return new SimpleDateFormat("dd/MM/yyyy").parse(dateStr);
		} catch (ParseException e) {
			throw new IllegalArgumentException("Couldn't format string to Date: " + e);
		}
	}
	
	public static String dateToString(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
		return dateFormat.format(date);  
	}

}
