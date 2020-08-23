package controlador;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import modelo.Cancion;
import modelo.CatalogoCanciones;
import modelo.CatalogoEstilos;
import modelo.CatalogoInterpretes;
import modelo.CatalogoUsuarios;
import modelo.Descuento;
import modelo.DescuentoFijo;
import modelo.DescuentoJovenes;
import modelo.Estilo;
import modelo.Interprete;
import modelo.NoDescuento;
import modelo.PlayList;
import modelo.Usuario;
import persistencia.DAOException;
import persistencia.FactoriaDAO;
import persistencia.IAdaptadorCancionDAO;
import persistencia.IAdaptadorPlayListDAO;
import persistencia.IAdaptadorUsuarioDAO;
import umu.tds.componente.BuscadorCanciones;
import umu.tds.componente.Canciones;
import umu.tds.componente.CancionesEvent;
import umu.tds.componente.CancionesListener;
import umu.tds.componente.IBuscadorCanciones;

public class ControladorAppMusic implements CancionesListener{

	private static ControladorAppMusic unicaInstancia;
	
	/**
	 * Ruta (relativa al proyecto)
	 * donde se generará el archivo PDF con las listas de canciones del usuario
	 */
	private static final String RUTA_PDF = "playList.pdf";
	
	private IAdaptadorCancionDAO adaptadorCancion;
	private IAdaptadorPlayListDAO adaptadorPlayList;
	private IAdaptadorUsuarioDAO adaptadorUsuario;
	
	private CatalogoUsuarios catalogoUsuarios;
	private CatalogoCanciones catalogoCanciones;
	
	private MediaPlayer mediaplayer;
	
	private Usuario usuarioActual;
	private Cancion cancionActual;
	
	private IBuscadorCanciones buscador;
	
	private ControladorAppMusic() {
		inicializarAdaptadores(); 
		inicializarCatalogos();
		inicializarReproductor();
		usuarioActual = null;
		cancionActual = null;
		buscador = new BuscadorCanciones();
		buscador.addListener(this);
	}
	
	private void inicializarReproductor() {
		try {
			com.sun.javafx.application.PlatformImpl.startup(()->{});
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Exception " + ex.getMessage());
		}
		mediaplayer = null;
	}
	
	public static ControladorAppMusic getUnicaInstancia() {
		 if (unicaInstancia == null)
			 unicaInstancia = new ControladorAppMusic();
		 return unicaInstancia;
	}
	
	private void inicializarAdaptadores() {
		 FactoriaDAO factoria = null;
		 try {
			 factoria = FactoriaDAO.getInstancia(FactoriaDAO.DAO_TDS);
		 } catch (DAOException e) {
			 e.printStackTrace();
		 }
		 adaptadorCancion = factoria.getCancionDAO();
		 adaptadorPlayList = factoria.getPlayListDAO();
		 adaptadorUsuario = factoria.getUsuarioDAO();
	}
  
	private void inicializarCatalogos() {
		catalogoCanciones = CatalogoCanciones.getUnicaInstancia();
		catalogoUsuarios = CatalogoUsuarios.getUnicaInstancia();
	}
	
	/**
	 * Obtiene el usuario actual
	 * @return El usuario actual
	 */
	public Usuario getUsuarioActual(){
		return usuarioActual;
	}
	
	/**
	 * Indica si un usuario es premium
	 * @return true si el usuario es premium, false en caso contrario
	 */
	public boolean isUsuarioPremium(){
		return usuarioActual.isPremium();
	}
	
	/**
	 * Se activa un descuento en base a la edad del usuario, si el usuario es mayor de edad se activa un descuento fijo
	 * en caso contrario se activa un descuento para jovenes.
	 * @return 0 si se aplica un descuento fijo, 1 si se aplica un descuento para jovenes
	 */
	public int activarDescuento(){
		Date fechaN = usuarioActual.getFechaNacimiento();
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		SimpleDateFormat datef = new SimpleDateFormat("dd/MM/yyyy");
		String fecha = String.valueOf(datef.format(fechaN));
		LocalDate fechaNac = LocalDate.parse(fecha, fmt);
		LocalDate ahora = LocalDate.now();
		 
		Period periodo = Period.between(fechaNac, ahora);
		if (periodo.getYears() >= 18){
			Descuento descuento = new DescuentoFijo();
			usuarioActual.setDescuento(descuento);
			return 0;
			
		}
		else{
			Descuento descuento1 = new DescuentoJovenes();
			usuarioActual.setDescuento(descuento1);
			return 1;
		}
	}
	
	/**
	 * Comprueba si es el cumpleaños de un usuario
	 * @return true si es el cumpleaños del usuario, false en caso contrario
	 */
	public boolean isCumpleañosUsuario(){
		Calendar calendar = Calendar.getInstance();
		Calendar calendar1 = Calendar.getInstance();
		Date date = usuarioActual.getFechaNacimiento();
		calendar1.setTime(date);
		int dia = calendar.get(Calendar.DAY_OF_MONTH);
		int mes = calendar.get(Calendar.MONTH);
		int diaUsuario = calendar1.get(Calendar.DAY_OF_MONTH);
		int mesUsuario = calendar1.get(Calendar.MONTH);
		return (dia == diaUsuario && mes == mesUsuario);
	}
	
	/**
	 * Registra a un usuario
	 * @param nombre El nombre del usuario
	 * @param apellidos Los apellidos del usuario
	 * @param nombreUsuario El nick del usuario
	 * @param contraseña La contraseña del usuario
	 * @param fecha La fecha de nacimiento del usuario
	 * @param email El email del usuario
	 */
	public void registrarUsuario(String nombre,String apellidos, String nombreUsuario, String contraseña, Date fecha, String email) {
		 usuarioActual = new Usuario(nombre, apellidos, nombreUsuario, contraseña, fecha, email, false, new NoDescuento());
		 adaptadorUsuario.registrarUsuario(usuarioActual);
		 catalogoUsuarios.addUsuario(usuarioActual);
	}
	
	/**
	 * Añade una nueva lista de reproduccion al usuario actual
	 * @param nombre El nombre de la lista de reproduccion
	 */
	public void añadirPlayList(String nombre){
		PlayList pl = usuarioActual.addPlayList(nombre);
		adaptadorPlayList.registrarPlayList(pl);
		adaptadorUsuario.modificarUsuario(usuarioActual);
	}
	
	public void añadirPlayList(String nombre, List<Cancion> list){
		PlayList pl = usuarioActual.addPlayList(nombre, list);
		adaptadorPlayList.registrarPlayList(pl);
		adaptadorUsuario.modificarUsuario(usuarioActual);
	}
	
	/**
	 * Elimina una lista de reproduccion del usuario actual
	 * @param playList La lista de reproduccion a eliminar
	 */
	public void eliminarPlayList(String nombre){
		PlayList playList = usuarioActual.getPlayList(nombre);
		usuarioActual.removePlayList(playList);
		adaptadorPlayList.borrarPlayList(playList);
		adaptadorUsuario.modificarUsuario(usuarioActual);
	}
	
	/**
	 * Comprueba si existe una lista de reproduccion
	 * @param nombre El nombre de la lista de reproduccion a buscar
	 * @return true si existe la lista, false en caso contrario
	 */
	public boolean existePlayList(String nombre){
		return usuarioActual.existePlayList(nombre);
	}
	
	public List<PlayList> getPlayListsUsuario() {
		return usuarioActual.getPlayLists();
	}
	
	public List<String> getPlayListsUsuarioString(){
		List<String> playListsNombres = new LinkedList<String>();
		List<PlayList> playLists = getPlayListsUsuario();
		for(PlayList pl : playLists) {
			playListsNombres.add(pl.getNombre());
		}
		return playListsNombres;
	}
	
	/**
	 * Añade una cancion a la lista de canciones recientes
	 * @param cancion La cancion
	 */
	public void añadirCancionReciente(Cancion cancion){
		usuarioActual.addCancionReciente(cancion);
		adaptadorUsuario.modificarUsuario(usuarioActual);
	}
	
	/**
	 * Comprueba si existe una cancion en la lista de canciones recientes
	 * @param cancion La cancion
	 * @return true si existe, false en caso contrario
	 */
	public boolean existeCancionReciente(Cancion cancion){
		return usuarioActual.existeCancionReciente(cancion);
	}
	
	/**
	 * Cambia la posicion de la cancion reciente a la primera posicion
	 * @param cancion La cancion
	 */
	public void setPosicionReciente(Cancion cancion){
		usuarioActual.setPosicionReciente(cancion);
	}
	
	/**
	 * Incrementa en 1 el numero de reproducciones de una cancion
	 * @param cancion La cancion
	 */
	public void incrementarReproduccion(Cancion cancion){
		cancion.addNumReproduccion();
		adaptadorCancion.modificarCancion(cancion);
	}
	
	/**
	 * Metodo para obtener todos los estilos musicales
	 * @return Los estilos
	 */
	public List<Estilo> getEstilosMusicales(){
		return CatalogoEstilos.getUnicaInstancia().getEstilos();
	}
	
	/**
	 * Devuelve las canciones del catalogo
	 * @return Las canciones
	 */
	public List<Cancion> getCanciones(){
		return catalogoCanciones.getCanciones();
	}
	
	/**
	 * Obtiene las canciones mas escuchadas
	 * @param valor El numero de canciones
	 * @return La lista de canciones mas escuchadas
	 */
	public List<Cancion> getMasEscuchadas(int valor){
		LinkedList<Cancion> canciones = new LinkedList<Cancion>(this.getCanciones());
		LinkedList<Cancion> masEscuchadas = new LinkedList<Cancion>();
		Iterator<Cancion> it = canciones.iterator();
		while (it.hasNext()){
			Cancion cancion = it.next();
			if (cancion.getNumReproduccion() == 0){
				it.remove();
			}
		}
		Collections.sort(canciones, new Comparator<Cancion>() {
			public int compare(Cancion cancion1, Cancion cancion2) {
				Integer rep1 = new Integer(cancion1.getNumReproduccion());
				Integer rep2 = new Integer(cancion2.getNumReproduccion());
		        return rep1.compareTo(rep2);
			}
		});
		for (int i = 0;i < valor && i < canciones.size();i++) {
			Cancion cancion = canciones.get(canciones.size()-1-i);
			masEscuchadas.add(cancion);
		}
		return masEscuchadas;
	}
	
	/**
	 * Metodo de login de un usuario
	 * @param nombreUsuario Nombre de usuario
	 * @param contraseña Contraseña asociada al usuario
	 * @return true si el login tiene exito, false en caso contrario
	 */
	public boolean loginUsuario(String nombreUsuario, String contraseña){
		Usuario usuario = catalogoUsuarios.getUsuario(nombreUsuario);
		if (usuario != null && usuario.getContraseña().equals(contraseña)){
			usuarioActual = usuario;	// El nuevo usuario actual es el logeado
			return true;
		}
		return false;
	}
	
	/**
	 * Indica si existe un usuario registrado con el nombre nombreUsuario
	 * @param nombreUsuario El nombre del usuario
	 * @return true si el usuario esta registrado, false en caso contrario
	 */
	public boolean isUsuarioRegistrado(String nombreUsuario){
		Usuario usuario = catalogoUsuarios.getUsuario(nombreUsuario);
		return usuario != null;
	}
	
	
	
	/**
	 * Metodo para buscar canciones aplicando los filtros pasados como parametro
	 * @param estilo El estilo
	 * @param interprete El interprete
	 * @param titulo El titulo
	 * @return Lista de canciones
	 */
	public List<Cancion> buscarCanciones(String estilo, String interprete, String titulo){
		estilo = estilo.toLowerCase();
		interprete = interprete.toLowerCase();
		titulo = titulo.toLowerCase();
		List<Cancion> filtrado = catalogoCanciones.getCanciones();
		Cancion auxiliar;
		if (interprete.equals("Interprete")){
			interprete = "";
		}
		if (titulo.equals("Titulo")){
			titulo = "";
		}
		if(!estilo.equals("")) {
			Iterator<Cancion> it = filtrado.iterator();
			while(it.hasNext()) {
				auxiliar = it.next();
				if(!auxiliar.getEstilo().getNombre().toLowerCase().equals(estilo)) {
					it.remove();
				}
			}
		}
		if(!interprete.equals("")) {
			Iterator<Cancion> it = filtrado.iterator();
			while(it.hasNext()) {
				auxiliar = it.next();
				String nomInterprete = auxiliar.getInterprete().getNombre();
				String[] nomInterpreteSplit = nomInterprete.split("&");
				boolean existe = false;
				for(String interpreteIndividual : nomInterpreteSplit) {
					interpreteIndividual = interpreteIndividual.trim().toLowerCase();
					if(interpreteIndividual.equals(interprete))
						existe = true;
				}
				if(!existe) {
					it.remove();
				}
			}
		}
		if(!titulo.equals("")) {
			Iterator<Cancion> it = filtrado.iterator();
			while(it.hasNext()) {
				auxiliar = it.next();
				if(!auxiliar.getTitulo().toLowerCase().equals(titulo)) {
					it.remove();
				}
			}
		}
		
		return filtrado;
	}
	
	/**
	 * Obtiene las canciones de una playList de un usuario
	 * @param nombre Nombre de la playList
	 * @return La playList
	 */
	public List<Cancion> getPlayListUsuario(String nombre) {
		List<Cancion> pl = usuarioActual.getCancionesPlayList(nombre);
		return pl;
	}
	
	/**
	 * Muestra en un archivo PDF las playList de un usuario
	 */
	public void imprimirPlayListsUsuario(){
		String salida= usuarioActual.showPlayLists();
		FileOutputStream archivo;
		try {
			archivo = new FileOutputStream(RUTA_PDF);
			Document documento = new Document();
			PdfWriter.getInstance(documento, archivo);
			documento.open();
			documento.add(new Paragraph(salida));
			documento.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Convierte al usuario en premium
	 */
	public void activarPremium() {
		usuarioActual.setPremium(true);
		adaptadorUsuario.modificarUsuario(usuarioActual);
	}
	
	/**
	 * Convierte a un usuario premium en un usuario normal
	 */
	public void desactivarPremium() {
		usuarioActual.setPremium(false);
		usuarioActual.quitarDescuento();
		adaptadorUsuario.modificarUsuario(usuarioActual);
	}
	
	/**
	 * Se obtienen las ultimas canciones reproducidas por el usuario
	 * @param valor El numero de canciones obtenidas
	 * @return Las ultimas canciones reproducidas
	 */
	public List<Cancion> getCancionesRecientes(int valor){
		List<Cancion> recientes = usuarioActual.getCancionesRecientes();
		List<Cancion> ult_recientes = new LinkedList<Cancion>();
		for (int i = 0; i < valor && i < recientes.size(); i++) {
			Cancion cancion = recientes.get(recientes.size()-1-i);
			ult_recientes.add(cancion);
		}
		return ult_recientes;
	}
	
	public void setCancionActual(Cancion c) {
		if(c != cancionActual) {
			if(mediaplayer != null) {
				mediaplayer.stop();
			}
			cancionActual = c;
			this.incrementarReproduccion(c);
			adaptadorCancion.modificarCancion(c);
			System.out.println(c.getRutaFichero());
			if (c.getRutaFichero().startsWith("http")){
				Media hit = new Media(c.getRutaFichero());
				mediaplayer = new MediaPlayer(hit);
			}
			else{
				File f = new File(c.getRutaFichero());
				Media hit = new Media(f.toURI().toString());
				mediaplayer = new MediaPlayer(hit);
			}
			mediaplayer.setOnEndOfMedia(()->{
				mediaplayer.stop();
			});
			
		}
	}
	
	/**
	 * Reproduce una cancion
	 */
	public void reproducirCancion() {
		if(mediaplayer != null) {
			mediaplayer.play();
		}
		
	}
    /**
     * Pausa una cancion
     */
	public void pausarCancion() {
		if(mediaplayer != null) {
			mediaplayer.pause();
		}
	}
	/**
	 * Detiene una cancion
	 */
	public void detenerCancion() {
		if(mediaplayer != null) {
			mediaplayer.stop();
		}
	}
	/**
	 * Realiza la busqueda de canciones a partir de un archivo xml con una lista de canciones
	 * @param nuevasCanciones Fichero xml con la lista de canciones
	 */
	public void buscarCanciones(String nuevasCanciones){
		buscador.buscarCanciones(nuevasCanciones);
	}

	/**
	 * Se almacenan las nuevas canciones obtenidas y se actualizan los catalogos
	 * @param eventoCanciones Evento que contiene todas las nuevas canciones
	 */
	@Override
	public void nuevasCanciones(CancionesEvent eventoCanciones) {
		boolean existe;
		Cancion cancionActual;
		Canciones canciones = eventoCanciones.getCanciones();
		List<umu.tds.componente.Cancion> nuevasCanciones = canciones.getCancion();
		for (umu.tds.componente.Cancion cancion : nuevasCanciones) {
			existe = false;
			if (catalogoCanciones.getCancion(cancion.getURL()) != null){
				existe = true;
			}
			if (!existe){
				System.out.println("Nueva Cancion: " + cancion.getEstilo() + " - " + cancion.getInterprete() + " - " + cancion.getTitulo());
				Interprete in = CatalogoInterpretes.getUnicaInstancia().getInterprete(cancion.getInterprete());
				if(in == null) {
					in = new Interprete(cancion.getInterprete());
					CatalogoInterpretes.getUnicaInstancia().addInterprete(in);
				}
				Estilo est = CatalogoEstilos.getUnicaInstancia().getEstilo(cancion.getEstilo());
				if(est == null) {
					est = new Estilo(cancion.getEstilo());
					CatalogoEstilos.getUnicaInstancia().addEstilo(est);
				}
				cancionActual = new Cancion(cancion.getTitulo(), in, est,cancion.getURL(), 0);
				catalogoCanciones.addCancion(cancionActual);
				adaptadorCancion.registrarCancion(cancionActual);
			}
		}
		
	}

}
