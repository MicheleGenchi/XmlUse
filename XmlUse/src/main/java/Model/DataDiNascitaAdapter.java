package Model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DataDiNascitaAdapter extends XmlAdapter<String, GregorianCalendar> {

	@Override
	public GregorianCalendar unmarshal(String v) {
		GregorianCalendar gc=new GregorianCalendar();
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date date;
		try {
			date = format.parse(v);
			gc.setTime(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return gc;
	}

	@Override
	public String marshal(GregorianCalendar v) throws Exception {
		// TODO Auto-generated method stub
		return new SimpleDateFormat("dd/MM/yyyy").format(v.getTime());
	}

}
