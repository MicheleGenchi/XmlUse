package Model;

import java.util.List;


public interface Wrapper<T> {
	
	List<T> getLista();
	void setLista(List<T> lista);
}
