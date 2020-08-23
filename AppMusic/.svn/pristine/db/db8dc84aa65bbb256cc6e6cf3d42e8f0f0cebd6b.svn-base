package persistencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import beans.Entidad;
import beans.Propiedad;

import modelo.PlayList;
import modelo.Cancion;

public class AdaptadorPlayListTDS implements IAdaptadorPlayListDAO {

	private static ServicioPersistencia servPersistencia;
	private static AdaptadorPlayListTDS unicaInstancia;
	
	public static AdaptadorPlayListTDS getUnicaInstancia() { // patron singleton
		if (unicaInstancia == null)
			return new AdaptadorPlayListTDS();
		else
			return unicaInstancia;
	}
	
	private AdaptadorPlayListTDS() { 
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}
	
	/* cuando se registra un playList se le asigna un identificador unico */
	public void registrarPlayList(PlayList playList) {
		Entidad ePlayList;
		// Si la entidad está registrada no la registra de nuevo
		boolean existe = true; 
		try {
			ePlayList = servPersistencia.recuperarEntidad(playList.getCodigo());
		} catch (NullPointerException e) {
			existe = false;
		}
		if (existe) return;

		// registrar primero los atributos que son objetos
		// registrar canciones
		AdaptadorCancionTDS adaptadorC = AdaptadorCancionTDS.getUnicaInstancia();
		for (Cancion c : playList.getCanciones())
			adaptadorC.registrarCancion(c);

		// Crear entidad playList
		ePlayList = new Entidad();

		ePlayList.setNombre("playList");
		ePlayList.setPropiedades(new ArrayList<Propiedad>(
				Arrays.asList(new Propiedad("nombre", playList.getNombre()),
						new Propiedad("canciones", obtenerCodigosCanciones(playList.getCanciones())))));
		// registrar entidad playList
		ePlayList = servPersistencia.registrarEntidad(ePlayList);
		// asignar identificador unico
		// Se aprovecha el que genera el servicio de persistencia
		playList.setCodigo(ePlayList.getId());
	}

	public void borrarPlayList(PlayList playList) {
		Entidad ePlayList;
		AdaptadorCancionTDS adaptadorC = AdaptadorCancionTDS.getUnicaInstancia();

		for (Cancion cancion : playList.getCanciones()) {
			adaptadorC.borrarCancion(cancion);
		}
		ePlayList = servPersistencia.recuperarEntidad(playList.getCodigo());
		servPersistencia.borrarEntidad(ePlayList);
	}

	public void modificarPlayList(PlayList playList) {
		Entidad ePlayList;

		ePlayList = servPersistencia.recuperarEntidad(playList.getCodigo());
		servPersistencia.eliminarPropiedadEntidad(ePlayList, "nombre");
		servPersistencia.anadirPropiedadEntidad(ePlayList, "nombre", playList.getNombre());

		String canciones = obtenerCodigosCanciones(playList.getCanciones());
		servPersistencia.eliminarPropiedadEntidad(ePlayList, "canciones");
		servPersistencia.anadirPropiedadEntidad(ePlayList, "canciones", canciones);
	}
	
	public PlayList recuperarPlayList(int codigo) {
		Entidad ePlayList;
		String nombre;
		List<Cancion> canciones = new LinkedList<Cancion>();
		
		// recuperar entidad
		ePlayList = servPersistencia.recuperarEntidad(codigo);

		// recuperar propiedades que no son objetos
		nombre = servPersistencia.recuperarPropiedadEntidad(ePlayList, "nombre");
		
		PlayList playList = new PlayList(nombre);
		playList.setCodigo(codigo);

		// recuperar propiedades que son objetos llamando a adaptadores
		// canciones
		canciones = obtenerCancionesDesdeCodigos(servPersistencia.recuperarPropiedadEntidad(ePlayList, "canciones"));

		for (Cancion c : canciones)
			playList.addCancion(c);

		// devolver el objeto playList
		return playList;
	}

	public List<PlayList> recuperarTodasPlayLists() {
		List<PlayList> playLists = new LinkedList<PlayList>();
		List<Entidad> ePlayLists = servPersistencia.recuperarEntidades("playList");

		for (Entidad ePlayList : ePlayLists) {
			playLists.add(recuperarPlayList(ePlayList.getId()));
		}
		return playLists;
	}
	
	// -------------------Funciones auxiliares-----------------------------
	private String obtenerCodigosCanciones(List<Cancion> canciones) {
		String aux = "";
		for (Cancion cancion : canciones) {
			aux += cancion.getCodigo() + " ";
		}
		return aux.trim();
	}

	private List<Cancion> obtenerCancionesDesdeCodigos(String canciones) {

		List<Cancion> listaCanciones = new LinkedList<Cancion>();
		StringTokenizer strTok = new StringTokenizer(canciones, " ");
		AdaptadorCancionTDS adaptadorC = AdaptadorCancionTDS.getUnicaInstancia();
		while (strTok.hasMoreTokens()) {
			listaCanciones.add(adaptadorC.recuperarCancion(Integer.valueOf((String) strTok.nextElement())));
		}
		return listaCanciones;
	}
}
