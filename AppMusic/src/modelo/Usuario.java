package modelo;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Usuario {
	
	private int codigo;
	private String nombre;
	private String apellidos;
	private String nombreUsuario;
	private String contraseña;
	private Date fechaNacimiento;
	private String email;
	private boolean premium;
	private Descuento descuento;
	private List<PlayList> playLists;
	private List<Cancion> cancionesRecientes;
	
	public Usuario(String nombre, String apellidos, String nombreUsuario, String contraseña, Date fechaNacimiento, String email, boolean premium, Descuento descuento){
		codigo = 0;
		this.nombre = nombre;
		this.apellidos= apellidos;
		this.nombreUsuario = nombreUsuario;
		this.contraseña = contraseña;
		this.fechaNacimiento = fechaNacimiento;
		this.email = email;
		this.premium = premium;
		this.playLists = new LinkedList<PlayList>();
		this.cancionesRecientes = new LinkedList<Cancion>();
		this.descuento = descuento;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getCodigo() {
		return codigo;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
	public String getApellidos() {
		return apellidos;
	}
	
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	
	public String getContraseña() {
		return contraseña;
	}
	
	public void setFechaNacimiento(Date fecha) {
		this.fechaNacimiento = fecha;
	}
	
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setPremium(boolean valor) {
		premium = valor;
	}
	
	public boolean isPremium(){
    	return premium;
    }
	
	public void setDescuento(Descuento descuento) {
    	this.descuento = descuento;
    }
    
    public Descuento getDescuento() {
    	return descuento;
    }
    
    public void quitarDescuento() {
    	descuento = new NoDescuento();
    }
	
	public void addPlayList(PlayList pl) {
		playLists.add(pl);
	}
	
	public PlayList addPlayList(String nombre) {
		PlayList pl = new PlayList(nombre);
		playLists.add(pl);
		return pl;
	}
	
	public PlayList addPlayList(String nombre, List<Cancion> list) {
		PlayList pl = new PlayList(nombre);
		for(Cancion c : list) {
			pl.addCancion(c);
		}
		playLists.add(pl);
		return pl;
	}
	
	public void removePlayList(PlayList pl){
		playLists.remove(pl);
	}
	
    public List<PlayList> getPlayLists() {
		return playLists;
	}
    
    public PlayList getPlayList(String nombre){
    	for (PlayList playList : playLists) {
			if (playList.getNombre().equals(nombre)){
				return playList;
			}
		}
    	return null;
    }
    
    public boolean existePlayList(String nombre){
    	return this.getPlayList(nombre) != null;
    }
    
    public List<Cancion> getCancionesPlayList(String nombre) {
    	PlayList pl = getPlayList(nombre);
    	if (pl != null) {
    		return pl.getCanciones();
    	} else {
    		return null;
    	}
    }
    
    public String showPlayLists(){
    	String salida="";
    	salida+="Usuario: "+this.getNombreUsuario()+"\n";
    	salida+="\n";
    	for (PlayList playList : playLists) {
			salida+= playList.showPlayList();
			salida+="\n";
		}
    	return salida;
    }
    
    public void addCancionReciente(Cancion c) {
		cancionesRecientes.add(c);
	}
    
    public List<Cancion> getCancionesRecientes() {
		return cancionesRecientes;
	}
    
    public boolean existeCancionReciente(Cancion cancionR){
    	for (Cancion cancion : cancionesRecientes) {
			if (cancion.getTitulo().equals(cancionR.getTitulo())){
				return true;
			}
		}
    	return false;
    }
    
    public void setPosicionReciente(Cancion cancion){
    	cancionesRecientes.remove(cancion);
    	cancionesRecientes.add(cancion);
    }
}
