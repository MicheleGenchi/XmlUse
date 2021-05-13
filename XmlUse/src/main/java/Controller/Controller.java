package Controller;

import java.io.File;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import DAO.DaoXmlFile;
import Model.AppuntamentiWrapper;
import Model.Appuntamento;
import Model.GregorianCalendarAdapter;
import Model.Paziente;
import Model.PazientiWrapper;

public class Controller {
	private static final Logger LOGGER = Logger.getLogger(Controller.class.getName());
	private final static String CLOUD_PATH_PAZIENTI="C:\\pazienti.xml";
	private final static String CLOUD_PATH_APPUNTAMENTI="C:\\appuntamenti.xml";

	public void scriviPaziente(Paziente paziente) {
		PazientiWrapper ps=PazientiWrapper.getInstance();
		ps.getLista().add(paziente);
		//ps.setLista(Arrays.asList(p));

		DaoXmlFile<PazientiWrapper> dao=new DaoXmlFile<>();
		File file =new File(CLOUD_PATH_PAZIENTI);
		boolean success=dao.salva(file, ps);
		
		if (success) 
			LOGGER.warning("Scrittura paziente");
		else
			LOGGER.config("Scrittura paziente fallita! ");
	}

	public List<Paziente> leggi_Pazienti() {
		DaoXmlFile<PazientiWrapper> dao=new DaoXmlFile<>();
		File file =new File(CLOUD_PATH_PAZIENTI);
		PazientiWrapper ps=dao.carica(file, PazientiWrapper.class);
		if (ps.getLista().size()<=0)
			LOGGER.config("non ci sono pazienti");	
		return ps.getLista();
	}
	
	public void scriviAppuntamento(Appuntamento appuntamento) {
		AppuntamentiWrapper apps=AppuntamentiWrapper.getInstance();
		apps.getLista().add(appuntamento);
		//ps.setLista(Arrays.asList(p));

		DaoXmlFile<AppuntamentiWrapper> dao=new DaoXmlFile<>();
		File file =new File(CLOUD_PATH_APPUNTAMENTI);
		boolean success=dao.salva(file, apps);
		
		if (success) 
			LOGGER.warning("Scrittura appuntamento");
		else
			LOGGER.config("Scrittura appuntamento fallita! ");
	}
	
	public List<Appuntamento> leggi_Appuntamenti() {
		DaoXmlFile<AppuntamentiWrapper> dao=new DaoXmlFile<>();
		File file =new File(CLOUD_PATH_APPUNTAMENTI);
		AppuntamentiWrapper apps=dao.carica(file, AppuntamentiWrapper.class);
		if (apps.getLista().size()<=0)
			LOGGER.config("non ci sono appuntamenti");	
		return apps.getLista();
	}
	
	@XmlSchemaType(name = "data")
	@XmlJavaTypeAdapter(GregorianCalendarAdapter.class) 
	public List<Appuntamento> appuntamentiPerData(GregorianCalendar data) {
		List<Appuntamento> filter=null;
		filter=leggi_Appuntamenti().stream().filter(a->(a.getData().equals(data))).collect(Collectors.toList());
		return filter;
	}
	
	public List<Appuntamento> appuntamentiDelPaziente(int id_paziente) {
		List<Appuntamento> filter=leggi_Appuntamenti().stream().filter(a->a.getIdPaziente()==id_paziente).collect(Collectors.toList());
		return filter;
	}
	
	public Paziente ricercaPerId(int id) {
		return leggi_Pazienti().stream().filter(i->(i.getId()==id)).findFirst().get(); 
	}


}
