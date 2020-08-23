package modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CatalogoInterpretes {
	private Map<String, Interprete> interpretes;
	private static CatalogoInterpretes unicaInstancia = null;
	
	private CatalogoInterpretes() {
		interpretes = new HashMap<String, Interprete>();
	}
	
	public static CatalogoInterpretes getUnicaInstancia(){
		if(unicaInstancia == null)
			unicaInstancia = new CatalogoInterpretes();
		return unicaInstancia;
	}
	
	/*devuelve todas los interpretes*/
	public List<Interprete> getInterpretes(){
		ArrayList<Interprete> lista = new ArrayList<Interprete>();
		for (Interprete interprete: interpretes.values()) 
			lista.add(interprete);
		return lista;
	}
	
	public Interprete getInterprete(String nombre) {
		return interpretes.get(nombre); 
	}
	
	public void addInterprete(Interprete interprete){
		interpretes.put(interprete.getNombre(), interprete);
	}
	public void removeInterprete (Interprete interprete) {
		interpretes.remove(interprete.getNombre());
	}
	
}
