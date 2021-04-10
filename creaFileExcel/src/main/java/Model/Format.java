package Model;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Format  {

	public static String getData(GregorianCalendar data) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("EEEE dd/MM/yyyy");
		return format.format(data.getTime());
	}

	public static String getOra(GregorianCalendar data) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		return format.format(data.getTime());
	}

}
