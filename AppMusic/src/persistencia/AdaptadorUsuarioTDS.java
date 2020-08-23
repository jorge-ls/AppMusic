package persistencia;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import beans.Entidad;
import beans.Propiedad;

import modelo.Usuario;
import modelo.PlayList;
import modelo.Cancion;
import modelo.Descuento;


public class AdaptadorUsuarioTDS implements IAdaptadorUsuarioDAO {
	private static ServicioPersistencia servPersistencia;
	private static AdaptadorUsuarioTDS unicaInstancia = null;
	private SimpleDateFormat dateFormat; // para formatear la fecha de nacimiento en
										 // la base de datos
	
	public static AdaptadorUsuarioTDS getUnicaInstancia() { // patron singleton
		if (unicaInstancia == null)
			return new AdaptadorUsuarioTDS();
		else
			return unicaInstancia;
	}
	
	private AdaptadorUsuarioTDS() { 
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	}
	
	public void registrarUsuario(Usuario usuario) {
		Entidad eUsuario;
		boolean existe = true; 
		
		// Si la entidad está registrada no la registra de nuevo
		try {
			eUsuario = servPersistencia.recuperarEntidad(usuario.getCodigo());
		} catch (NullPointerException e) {
			existe = false;
		}
		if (existe) return;

		// registrar primero los atributos que son objetos
		AdaptadorPlayListTDS adaptadorPlayList = AdaptadorPlayListTDS.getUnicaInstancia();
		for (PlayList pl : usuario.getPlayLists())
			adaptadorPlayList.registrarPlayList(pl);
		
		AdaptadorCancionTDS adaptadorCancion = AdaptadorCancionTDS.getUnicaInstancia();
		for (Cancion c : usuario.getCancionesRecientes())
			adaptadorCancion.registrarCancion(c);

		// crear entidad Usuario
		eUsuario = new Entidad();
		eUsuario.setNombre("usuario");
		eUsuario.setPropiedades(new ArrayList<Propiedad>(
				Arrays.asList(new Propiedad("nombre", usuario.getNombre()),
						new Propiedad("apellidos", usuario.getApellidos()),
						new Propiedad("nombreUsuario", usuario.getNombreUsuario()),
						new Propiedad("contraseña", usuario.getContraseña()),
						new Propiedad("fechaNacimiento", dateFormat.format(usuario.getFechaNacimiento())),
						new Propiedad("email", usuario.getEmail()),
						new Propiedad("premium", String.valueOf(usuario.isPremium())),
						new Propiedad("playLists", obtenerCodigosPlayLists(usuario.getPlayLists())),
						new Propiedad("cancionesRecientes", obtenerCodigosCancionesRecientes(usuario.getCancionesRecientes())),
						new Propiedad("descuento", usuario.getDescuento().getClass().getName()))));
		// registrar entidad cliente
		eUsuario = servPersistencia.registrarEntidad(eUsuario);
		// asignar identificador unico
		// Se aprovecha el que genera el servicio de persistencia
		usuario.setCodigo(eUsuario.getId());	
	}

	public void borrarUsuario(Usuario usuario) {
		Entidad eUsuario = servPersistencia.recuperarEntidad(usuario.getCodigo());
		servPersistencia.borrarEntidad(eUsuario);
	}

	public void modificarUsuario(Usuario usuario) {
		Entidad eCliente = servPersistencia.recuperarEntidad(usuario.getCodigo());

		servPersistencia.eliminarPropiedadEntidad(eCliente, "nombre");
		servPersistencia.anadirPropiedadEntidad(eCliente, "nombre", usuario.getNombre());
		servPersistencia.eliminarPropiedadEntidad(eCliente, "apellidos");
		servPersistencia.anadirPropiedadEntidad(eCliente, "apellidos", usuario.getApellidos());
		servPersistencia.eliminarPropiedadEntidad(eCliente, "nombreUsuario");
		servPersistencia.anadirPropiedadEntidad(eCliente, "nombreUsuario", usuario.getNombreUsuario());
		servPersistencia.eliminarPropiedadEntidad(eCliente, "contraseña");
		servPersistencia.anadirPropiedadEntidad(eCliente, "contraseña", usuario.getContraseña());
		servPersistencia.eliminarPropiedadEntidad(eCliente, "fechaNacimiento");
		servPersistencia.anadirPropiedadEntidad(eCliente, "fechaNacimiento", dateFormat.format(usuario.getFechaNacimiento()));
		servPersistencia.eliminarPropiedadEntidad(eCliente, "email");
		servPersistencia.anadirPropiedadEntidad(eCliente, "email", usuario.getEmail());
		servPersistencia.eliminarPropiedadEntidad(eCliente, "premium");
		servPersistencia.anadirPropiedadEntidad(eCliente, "premium", String.valueOf(usuario.isPremium()));

		String playLists = obtenerCodigosPlayLists(usuario.getPlayLists());
		servPersistencia.eliminarPropiedadEntidad(eCliente, "playLists");
		servPersistencia.anadirPropiedadEntidad(eCliente, "playLists", playLists);
		
		String cancionesRecientes = obtenerCodigosCancionesRecientes(usuario.getCancionesRecientes());
		servPersistencia.eliminarPropiedadEntidad(eCliente, "cancionesRecientes");
		servPersistencia.anadirPropiedadEntidad(eCliente, "cancionesRecientes", cancionesRecientes);
	}

	public Usuario recuperarUsuario(int codigo) {
		Entidad eUsuario;
		String nombre;
		String apellidos;
		String nombreUsuario;
		String contraseña;
		Date fechaNacimiento = null;
		String email;
		boolean premium;
		List<PlayList> playLists = new LinkedList<PlayList>();
		List<Cancion> cancionesRecientes = new LinkedList<Cancion>();
		Descuento descuento = null;

		// recuperar entidad
		eUsuario = servPersistencia.recuperarEntidad(codigo);

		// recuperar propiedades que no son objetos
		nombre = servPersistencia.recuperarPropiedadEntidad(eUsuario, "nombre");
		apellidos = servPersistencia.recuperarPropiedadEntidad(eUsuario, "apellidos");
		nombreUsuario = servPersistencia.recuperarPropiedadEntidad(eUsuario, "nombreUsuario");
		contraseña = servPersistencia.recuperarPropiedadEntidad(eUsuario, "contraseña");
		try {
			fechaNacimiento = dateFormat.parse(servPersistencia.recuperarPropiedadEntidad(eUsuario, "fechaNacimiento"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		email = servPersistencia.recuperarPropiedadEntidad(eUsuario, "email");
		premium = Boolean.parseBoolean(servPersistencia.recuperarPropiedadEntidad(eUsuario, "premium"));
		try {
			descuento = (Descuento) Class.forName(servPersistencia.recuperarPropiedadEntidad(eUsuario, "descuento")).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Usuario usuario = new Usuario(nombre, apellidos, nombreUsuario, contraseña, fechaNacimiento, email, premium, descuento);
		usuario.setCodigo(codigo);

		// recuperar propiedades que son objetos llamando a adaptadores
		// playLists
		playLists = obtenerPlayListsDesdeCodigos(servPersistencia.recuperarPropiedadEntidad(eUsuario, "playLists"));

		for (PlayList pl : playLists)
			usuario.addPlayList(pl);
		
		// cancionesRecientes
		cancionesRecientes = obtenerCancionesRecientesDesdeCodigos(servPersistencia.recuperarPropiedadEntidad(eUsuario, "cancionesRecientes"));

		for (Cancion c : cancionesRecientes)
			usuario.addCancionReciente(c);
		
		return usuario;
	}

	public List<Usuario> recuperarTodosUsuarios() {
		List<Entidad> eUsuarios = servPersistencia.recuperarEntidades("usuario");
		List<Usuario> usuarios = new LinkedList<Usuario>();

		for (Entidad eUsuario : eUsuarios) {
			usuarios.add(recuperarUsuario(eUsuario.getId()));
		}
		return usuarios;
	}
	
	// -------------------Funciones auxiliares-----------------------------
	private String obtenerCodigosPlayLists(List<PlayList> listaPlayLists) {
		String aux = "";
		for (PlayList pl : listaPlayLists) {
			aux += pl.getCodigo() + " ";
		}
		return aux.trim();
	}

	private List<PlayList> obtenerPlayListsDesdeCodigos(String playLists) {

		List<PlayList> listaPlayLists = new LinkedList<PlayList>();
		StringTokenizer strTok = new StringTokenizer(playLists, " ");
		AdaptadorPlayListTDS adaptadorPl = AdaptadorPlayListTDS.getUnicaInstancia();
		while (strTok.hasMoreTokens()) {
			listaPlayLists.add(adaptadorPl.recuperarPlayList(Integer.valueOf((String) strTok.nextElement())));
		}
		return listaPlayLists;
	}
	
	private String obtenerCodigosCancionesRecientes(List<Cancion> listaCanciones) {
		String aux = "";
		for (Cancion c : listaCanciones) {
			aux += c.getCodigo() + " ";
		}
		return aux.trim();
	}

	private List<Cancion> obtenerCancionesRecientesDesdeCodigos(String cancionesRecientes) {

		List<Cancion> listaCanciones = new LinkedList<Cancion>();
		StringTokenizer strTok = new StringTokenizer(cancionesRecientes, " ");
		AdaptadorCancionTDS adaptadorC = AdaptadorCancionTDS.getUnicaInstancia();
		while (strTok.hasMoreTokens()) {
			listaCanciones.add(adaptadorC.recuperarCancion(Integer.valueOf((String) strTok.nextElement())));
		}
		return listaCanciones;
	}

}
