package leave.system;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public enum SAPublicHoliday {
	
	NEW_YEAR_DAY(holiday("2017-01-01")),
	HUMAN_RIGHTS(holiday("2017-03-21")), 
	GOOD_FRIDAY(holiday("2017-04-14")), 
	FAMILY_DAY(holiday("2017-04-17")),
	FREEDOM_DAY(holiday("2017-04-27")), 
	WORKERS_DAY(holiday("2017-05-01")),
	YOUTH_DAY(holiday("2017-06-16")), 
	NATIONAL_WOMANS_DAY(holiday("2017-08-09")), 
	HERITAGE(holiday("2017-09-24")), 
	DAY_OF_RECONCILIATION(holiday("2017-12-16")), 
	CHRISTMAS_DAY(holiday("2017-12-25")),
	DAY_OF_GOODWILL(holiday("2017-12-26"));

	private int value;

	private static int holiday(String holiday) {
		Date s = null;
		try {
			s = new SimpleDateFormat("yyyy-MM-dd").parse(holiday);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(s);
		if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			return (cal.get(Calendar.DAY_OF_YEAR)) + 1;
		}
		return (cal.get(Calendar.DAY_OF_YEAR));
	}

	private SAPublicHoliday(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}
};
