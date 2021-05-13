package DAO;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import Model.Paziente;
import Model.Wrapper;


public class DaoXmlFile<T> implements Dao<T> {
	
	
	public DaoXmlFile() {
		
	}
	
	public boolean salva(File file, T clazz) {
		boolean success=false;
		try {
			JAXBContext context = JAXBContext.newInstance(clazz.getClass());
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			// Marshalling and saving XML to the file.
			m.marshal(clazz, file);
			return (success=true);
		} catch (Exception e) { // catches ANY exception
		  return (success=false);
		}
	}

	public T carica(File file, Class<T> clazz) {
		try {
	        JAXBContext context = JAXBContext
	                .newInstance(clazz);
	        Unmarshaller um = context.createUnmarshaller();
	        T ps=(T) um.unmarshal(file);
	        return ps;
	    } catch (Exception e) { // catches ANY exception
	    	return null;
	    }
	}
}
