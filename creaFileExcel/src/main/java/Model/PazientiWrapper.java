package Model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Elenco")
public class PazientiWrapper implements Wrapper<Paziente> {
	private List<Paziente> lista;
	private static PazientiWrapper instance;

	private PazientiWrapper() {
		lista=new ArrayList<>();
	}
	
	public static PazientiWrapper getInstance() {
	    if (instance == null) {
	        synchronized (PazientiWrapper.class) {
	            if (instance == null){
	                instance = new PazientiWrapper();
	            }
	        }
	    }
	    return instance;
	}
	
	@Override
	@XmlElement(name="Paziente")
	public List<Paziente> getLista() {
		return lista;
	}

	@Override
	public void setLista(List<Paziente> lista) {
		this.lista=lista;
	}

}
