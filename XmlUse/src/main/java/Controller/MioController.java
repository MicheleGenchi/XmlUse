package Controller;

import java.io.File;
import java.util.Calendar;
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

public class MioController {
	private static final Logger LOGGER = Logger.getLogger(MioController.class.getName());
	private final static String CLOUD_PATH_PAZIENTI="C:\\pazienti.xml";
	private final static String CLOUD_PATH_APPUNTAMENTI="C:\\appuntamenti.xml";
	private PazientiWrapper pazienti;
	private AppuntamentiWrapper appuntamenti;

	public MioController() {
		pazienti=PazientiWrapper.getInstance();
		appuntamenti=AppuntamentiWrapper.getInstance();
		pazienti=leggi_Pazienti()!=null?leggi_Pazienti():pazienti;
		appuntamenti=leggi_Appuntamenti()!=null?leggi_Appuntamenti():appuntamenti;
	}
	
	public void scriviPaziente(Paziente paziente) {
		pazienti.getLista().add(paziente);
		DaoXmlFile<PazientiWrapper> dao=new DaoXmlFile<>();
		File file =new File(CLOUD_PATH_PAZIENTI);
		boolean success=dao.salva(file, pazienti);
		if (success) 
			LOGGER.warning("Scrittura sul file pazienti.xml");
		else
			LOGGER.config("Scrittura sul file pazienti.xml fallita! ");
	}

	public PazientiWrapper leggi_Pazienti() {
		DaoXmlFile<PazientiWrapper> dao=new DaoXmlFile<>();
		File file =new File(CLOUD_PATH_PAZIENTI);
		if (file.exists()) {
			LOGGER.warning("Sono stati recuperati "+dao.carica(file, PazientiWrapper.class).getLista().size()+"  pazienti");
			return dao.carica(file, PazientiWrapper.class);
		}
		LOGGER.config("Problemi nella lettura del file pazienti.xml");
		return null;
	}
	
	public void scriviAppuntamento(Appuntamento appuntamento) {
		appuntamenti.getLista().add(appuntamento);
		DaoXmlFile<AppuntamentiWrapper> dao=new DaoXmlFile<>();
		File file =new File(CLOUD_PATH_APPUNTAMENTI);
		boolean success=dao.salva(file, appuntamenti);
		if (success) 
			LOGGER.warning("Scrittura su file appuntamenti.xml");
		else
			LOGGER.config("Scrittura su file appuntamenti.xml fallita! ");
	}
	
	public AppuntamentiWrapper leggi_Appuntamenti() {
		DaoXmlFile<AppuntamentiWrapper> dao=new DaoXmlFile<>();
		File file =new File(CLOUD_PATH_APPUNTAMENTI);
		if (file.exists()) {
			LOGGER.warning("Sono stati recuperati "+dao.carica(file, AppuntamentiWrapper.class).getLista().size()+" appuntamenti");
			return dao.carica(file, AppuntamentiWrapper.class);
		}
		LOGGER.config("Problemi nella lettura del file appuntamenti.xml");
		return null;
	}

	private boolean confrontoDate(GregorianCalendar data1, GregorianCalendar data2) {
		boolean sameYear = data1.get(Calendar.YEAR) == data2.get(Calendar.YEAR);
        boolean sameMonth = data1.get(Calendar.MONTH) == data2.get(Calendar.MONTH);
        boolean sameDay = data1.get(Calendar.DAY_OF_MONTH) == data2.get(Calendar.DAY_OF_MONTH);
        return (sameDay && sameMonth && sameYear);
	}
	
	
	@XmlSchemaType(name = "data")
	@XmlJavaTypeAdapter(GregorianCalendarAdapter.class) 
	public List<Appuntamento> appuntamentiPerData(GregorianCalendar data) {
		List<Appuntamento> filter=null;
		filter=appuntamenti.getLista().stream().filter(a -> 
			confrontoDate(a.getData(), data)).collect(Collectors.toList());	
		return filter;
	}
	
	public List<Appuntamento> appuntamentiDelPaziente(int id_paziente) {
		List<Appuntamento> filter=appuntamenti.getLista().stream().filter(a->a.getIdPaziente()==id_paziente).collect(Collectors.toList());
		return filter;
	}
	
	public Paziente ricercaPerId(int id) {
		return pazienti.getLista().stream().filter(i->(i.getId()==id)).findFirst().get(); 
	}

	public PazientiWrapper getPazienti() {
		return pazienti;
	}

	public AppuntamentiWrapper getAppuntamenti() {
		return appuntamenti;
	}


}
