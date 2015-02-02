package instrumenti;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.jfree.date.SerialDate;
import org.jfree.date.SerialDateUtilities;

public class DateDifference {
	public static long getDateDifference(Date d1, Date d2){
		Calendar cal1 = new GregorianCalendar();
		Calendar cal2 = new GregorianCalendar();
		cal1.setTime(d1);
		cal2.setTime(d2);
		long diff = cal2.getTimeInMillis() - cal1.getTimeInMillis();
		/**
		 * diff is the difference in the two dates in milliseconds. We divide it by 1000, to
		 * obtain the time in seconds, then by 60*60 to get the time in hours and then by 24
		 * to get it in days. At the end we add 2, to make the method work with the two dates
		 * including both.
		 */
		return (diff / (1000 * 60 * 60 * 24));
	}
	
	public static long getDateDifference360(Date d1, Date d2){
		SerialDate sd1 = SerialDate.createInstance(d1);
		SerialDate sd2 = SerialDate.createInstance(d2);
		int i = SerialDateUtilities.dayCount30(sd1, sd2);

		Calendar c1 = new GregorianCalendar();
		Calendar c2 = new GregorianCalendar();
		
		c1.setTime(d1);
		c2.setTime(d2);
		
		if(c1.get(Calendar.YEAR)==c2.get(Calendar.YEAR) && c1.get(Calendar.MONTH)==c2.get(Calendar.MONTH)){
			if(i+1>30){
				return 30;
			}
		}
		
		return i+1;
	}
	
}
