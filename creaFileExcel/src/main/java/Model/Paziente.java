package Model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@XmlType(propOrder={"id","cognome", "nome", "dataDiNascita"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Paziente {
	private int id;
	private String nome;
	private String cognome;
	
	@XmlJavaTypeAdapter(GregorianCalendarAdapter.class)
	private GregorianCalendar dataDiNascita;
	
	public Paziente() {
		
	}
	
	public Paziente(int id, String nome, String cognome, GregorianCalendar dataDiNascita) {
		super();
		this.id=id;
		this.nome = nome;
		this.cognome = cognome;
		this.dataDiNascita = dataDiNascita;
	}
	
	public Paziente(int id) {
		this.id=id;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public GregorianCalendar getDataDiNascita() {
		return dataDiNascita;
	}
	public void setDataDiNascita(GregorianCalendar dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		try {
			builder.append("Paziente [id=").append(id).append(", nome=").append(nome).append(", cognome=").append(cognome).append(", dataDiNascita=")
					.append(new GregorianCalendarAdapter().marshal(dataDiNascita)).append("]");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return builder.toString();
	}



}
