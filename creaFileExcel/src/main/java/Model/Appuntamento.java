package Model;

import java.io.File;
import java.util.GregorianCalendar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import DAO.DaoXmlFile;

@XmlType(propOrder={"idPaziente","data"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Appuntamento {
	
	private int idPaziente;
	
	@XmlJavaTypeAdapter(AppuntamentoAdapter.class)                         
	private GregorianCalendar data;
	
	public Appuntamento() {
		
	}

	
	public Appuntamento(int idPaziente, GregorianCalendar data) {
		super();
		this.idPaziente = idPaziente;
		this.data = data;
	}


	public int getIdPaziente() {
		return idPaziente;
	}

	public void setIdPaziente(int idPaziente) {
		this.idPaziente = idPaziente;
	}

	public GregorianCalendar getData() {
		return data;
	}

	public void setData(GregorianCalendar data) {
		this.data = data;
	}
	
	public Paziente getPaziente(File filePazienti, int id) {
		DaoXmlFile<PazientiWrapper> dao=new DaoXmlFile<>();
		PazientiWrapper ps = dao.carica(filePazienti, PazientiWrapper.class);
		return ps.ricercaPerId(id);
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		try {
			builder.append("Appuntamento [").append("data=")
			.append(Format.getData(data)).append(", ora=").append(Format.getOra(data)).append("]");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return builder.toString();
	}
	
	
}
