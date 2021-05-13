package Model;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name="Elenco")
public class AppuntamentiWrapper implements Wrapper<Appuntamento> {
	private List<Appuntamento> lista;
	private static AppuntamentiWrapper instance;
	
	private  AppuntamentiWrapper() {
		lista=new ArrayList<>();
	}

	public static AppuntamentiWrapper getInstance() {
	    if (instance == null) {
	        synchronized (AppuntamentiWrapper.class) {
	            if (instance == null){
	                instance = new AppuntamentiWrapper();
	            }
	        }
	    }
	    return instance;
	}
	
	@Override
	@XmlElement(name="Appuntamento")
	public List<Appuntamento> getLista() {
		return lista;
	}

	@Override
	public void setLista(List<Appuntamento> lista) {
		this.lista=lista;
	}

	

}
