package Model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Elenco")
public class AppuntamentiWrapper implements Wrapper<Appuntamento> {
	private List<Appuntamento> lista;
	
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
