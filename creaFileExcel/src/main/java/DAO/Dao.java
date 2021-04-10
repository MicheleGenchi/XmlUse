package DAO;

import java.io.File;

public interface Dao<T> {

	boolean salva(File file, T clazz);
	T carica(File file, Class<T> clazz);
}
