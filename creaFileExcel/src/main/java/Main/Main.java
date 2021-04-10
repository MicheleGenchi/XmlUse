package Main;

import java.io.File;
import java.util.Arrays;
import java.util.GregorianCalendar;
import DAO.DaoXmlFile;
import Model.AppuntamentiWrapper;
import Model.Appuntamento;
import Model.Paziente;
import Model.PazientiWrapper;
import Model.Wrapper;

public class Main {
 
	
	public static void main(String...args) {
  		Paziente[] p= {new Paziente(1,"Michele","Genchi", new GregorianCalendar(1979,3,13)),
		new Paziente(2,"Michele2","Genchi2", new GregorianCalendar(1979,3,14)),
		new Paziente(3,"Michele3","Genchi3", new GregorianCalendar(1979,3,15))
		};
		
  		PazientiWrapper ps=new PazientiWrapper();
		ps.setLista(Arrays.asList(p));

		DaoXmlFile<PazientiWrapper> dao=new DaoXmlFile<>();
		File file =new File("c:\\pazienti.xml");
		boolean success=dao.salva(file, ps);
		
		if (success) {
			System.out.println("Scrittura file OK");
			ps=dao.carica(file, PazientiWrapper.class);
			ps.getLista().forEach(System.out::println);
		} else
			System.err.println("Non ci sono dati nel file");
		
		Appuntamento app[] = {new Appuntamento(1, new GregorianCalendar(1979,3,13,13,40))};
		AppuntamentiWrapper apps=new AppuntamentiWrapper();
		apps.setLista(Arrays.asList(app));
		
		DaoXmlFile<AppuntamentiWrapper> dao2=new DaoXmlFile<>();
		file =new File("c:\\appuntamenti.xml");
		success=dao2.salva(file, apps);
		if (success) {
			System.out.println("Scrittura file OK");
			apps=dao2.carica(file, AppuntamentiWrapper.class);
			File file2 =new File("c:\\pazienti.xml");
			apps.getLista().forEach(a -> {
				System.out.println(a.getPaziente(file2, a.getIdPaziente()));
				System.out.println(a);				
			});
		} else
			System.err.println("Non ci sono dati nel file");
		
	}
}
