package Model;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Elenco")
public class PazientiWrapper implements Wrapper<Paziente> {
	private List<Paziente> lista;

	@Override
	@XmlElement(name="Paziente")
	public List<Paziente> getLista() {
		return lista;
	}

	@Override
	public void setLista(List<Paziente> lista) {
		this.lista=lista;
	}

	public Paziente ricercaPerId(int id) {
		return lista.stream().filter(i->(i.getId()==id)).findFirst().get(); 
	}

}
