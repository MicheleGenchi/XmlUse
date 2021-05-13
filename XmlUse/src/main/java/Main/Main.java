package Main;

import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.logging.Logger;
import Controller.MioController;
import Model.Appuntamento;
import Model.Paziente;

public class Main {
	private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
	
	public static void main(String...args) {
		MioController controller=new MioController();
  		Paziente[] pazienti= {new Paziente(1,"Michele","Genchi", 
  				new GregorianCalendar(1979,3,13)),
		new Paziente(2,"Michele2","Genchi2", new GregorianCalendar(1979,3,14)),
		new Paziente(3,"Michele3","Genchi3", new GregorianCalendar(1979,3,15))
		};
  		Arrays.stream(pazienti).forEach(p-> controller.scriviPaziente(p));
  		//stampa di tutti i pazienti
  		controller.getPazienti().getLista().forEach(System.out::println);
		
		//EEEE dd/MM/yyyy
		Appuntamento appuntamenti[] = {new Appuntamento(1, 
				new GregorianCalendar(1979,3,13,13,40))};
		Arrays.stream(appuntamenti).forEach(a-> controller.scriviAppuntamento(a));
		//stampa di tutti gli appuntamenti
  		controller.getAppuntamenti().getLista().forEach(a -> {
  			Paziente p=controller.ricercaPerId(a.getIdPaziente());
  			System.out.println(p.getCognome()+" "+p.getNome());
  			System.out.println(a);
  		});
  		System.out.println("Ricerca appuntamenti del paziente "+controller.ricercaPerId(1));
  		System.out.println(controller.appuntamentiDelPaziente(1));
  		try {
  			System.out.println("Ricerca appuntamenti per il giorno "+
  					new GregorianCalendar(1979,3,13).getTime());
			System.out.println(controller.appuntamentiPerData(
					new GregorianCalendar(1979,3,13)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.config("Data scritta in un formato non corretto");
		}
	}
}
	
