package modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CatalogoEstilos {

	private Map<String, Estilo> estilos;
	private static CatalogoEstilos unicaInstancia = null;
	
	private CatalogoEstilos() {
		estilos = new HashMap<String, Estilo>();
	}
	
	public static CatalogoEstilos getUnicaInstancia(){
		if(unicaInstancia == null)
			unicaInstancia = new CatalogoEstilos();
		return unicaInstancia;
	}
	
	/*devuelve todas los estilos*/
	public List<Estilo> getEstilos(){
		ArrayList<Estilo> lista = new ArrayList<Estilo>();
		for (Estilo estilo: estilos.values()) 
			lista.add(estilo);
		return lista;
	}
	
	public Estilo getEstilo(String nombre) {
		return estilos.get(nombre); 
	}
	
	public void addEstilo(Estilo estilo){
		estilos.put(estilo.getNombre(), estilo);
	}
	public void removeEstilo (Estilo estilo) {
		estilos.remove(estilo.getNombre());
	}
	
}
