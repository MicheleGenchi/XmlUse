package Model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class GregorianCalendarAdapter extends XmlAdapter<String, GregorianCalendar> {

	@Override
	public GregorianCalendar unmarshal(String v) throws Exception {
		GregorianCalendar gc=new GregorianCalendar();
		DateFormat format = new SimpleDateFormat("EEEE dd/MM/yyyy HH:mm");
		Date date = format.parse(v);
		gc.setTime(date);
		return gc;
	}

	@Override
	public String marshal(GregorianCalendar v) throws Exception {
		// TODO Auto-generated method stub
		return new SimpleDateFormat("EEEE dd/MM/yyyy HH:mm").format(v.getTime());
	}

}
