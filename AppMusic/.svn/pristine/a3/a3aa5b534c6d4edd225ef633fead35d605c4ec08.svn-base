package modelo;

import java.util.LinkedList;
import java.util.List;

public class PlayList {
	
	private int codigo;
	private String nombre;
	private List<Cancion> canciones;
	
	
	public PlayList(String nombre){
		codigo = 0;
		this.nombre = nombre;
		this.canciones = new LinkedList<Cancion>();
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public List<Cancion> getCanciones() {
		return canciones;
	}
	
	public void addCancion(Cancion cancion){
		canciones.add(cancion);
	}
	
	public void removeCancion(Cancion cancion){
		canciones.remove(cancion);
	}

	public String showPlayList(){
		String salida="";
		salida+="PlayList: "+this.getNombre()+"\n";
		salida+="\n";
		for (Cancion cancion : canciones) {
			salida+="Título: "+cancion.getTitulo()+". Interprete: "+cancion.getInterprete().getNombre()+". Estilo: "+cancion.getEstilo().getNombre()+"\n";
		}
		return salida;
	}
	
	
}
