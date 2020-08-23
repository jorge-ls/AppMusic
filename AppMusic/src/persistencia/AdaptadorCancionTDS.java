package persistencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import beans.Entidad;
import beans.Propiedad;

import modelo.Cancion;
import modelo.CatalogoEstilos;
import modelo.CatalogoInterpretes;
import modelo.Estilo;
import modelo.Interprete;

public class AdaptadorCancionTDS implements IAdaptadorCancionDAO{

	private static ServicioPersistencia servPersistencia;
	private static AdaptadorCancionTDS unicaInstancia = null;
	
	public static AdaptadorCancionTDS getUnicaInstancia() { // patron singleton
		if (unicaInstancia == null) {
			return new AdaptadorCancionTDS();
		} else
			return unicaInstancia;
	}

	private AdaptadorCancionTDS() { 
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}
	
	/* cuando se registra una cancion se le asigna un identificador unico */
	public void registrarCancion(Cancion cancion) {
		Entidad eCancion = null;
		// Si la entidad está registrada no la registra de nuevo
		boolean existe = true; 
		try {
			eCancion = servPersistencia.recuperarEntidad(cancion.getCodigo());
		} catch (NullPointerException e) {
			existe = false;
		}
		if (existe) return;
		
		// crear entidad cancion
		eCancion = new Entidad();
		eCancion.setNombre("cancion");
		eCancion.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(new Propiedad("titulo", cancion.getTitulo()),
				new Propiedad("interprete", cancion.getInterprete().getNombre()),
				new Propiedad("estilo", cancion.getEstilo().getNombre()),
				new Propiedad("rutaFichero", cancion.getRutaFichero()),
				new Propiedad("numReproduccion", String.valueOf(cancion.getNumReproduccion())))));
		
		// registrar entidad cancion
		eCancion = servPersistencia.registrarEntidad(eCancion);
		// asignar identificador unico
		// Se aprovecha el que genera el servicio de persistencia
		cancion.setCodigo(eCancion.getId());
	}

	public void borrarCancion(Cancion cancion) {
		Entidad eCancion = servPersistencia.recuperarEntidad(cancion.getCodigo());
		servPersistencia.borrarEntidad(eCancion);
	}
	
	public void modificarCancion(Cancion cancion) {
		Entidad eCancion = servPersistencia.recuperarEntidad(cancion.getCodigo());

		servPersistencia.eliminarPropiedadEntidad(eCancion, "titulo");
		servPersistencia.anadirPropiedadEntidad(eCancion, "titulo", cancion.getTitulo());
		servPersistencia.eliminarPropiedadEntidad(eCancion, "interprete");
		servPersistencia.anadirPropiedadEntidad(eCancion, "interprete", cancion.getInterprete().getNombre());
		servPersistencia.eliminarPropiedadEntidad(eCancion, "estilo");
		servPersistencia.anadirPropiedadEntidad(eCancion, "estilo", cancion.getEstilo().getNombre());
		servPersistencia.eliminarPropiedadEntidad(eCancion, "rutaFichero");
		servPersistencia.anadirPropiedadEntidad(eCancion, "rutaFichero", cancion.getRutaFichero());
		servPersistencia.eliminarPropiedadEntidad(eCancion, "numReproduccion");
		servPersistencia.anadirPropiedadEntidad(eCancion, "numReproduccion", String.valueOf(cancion.getNumReproduccion()));
	}

	public Cancion recuperarCancion(int codigo) {
		Entidad eCancion;
		String titulo;
		String nombreInterprete;
		Interprete interprete;
		String nombreEstilo;
		Estilo estilo;
		String rutaFichero;
		int numReproduccion;

		eCancion = servPersistencia.recuperarEntidad(codigo);
		
		// recuperar propiedades que no son objetos
		titulo = servPersistencia.recuperarPropiedadEntidad(eCancion, "titulo");
		
		nombreInterprete = servPersistencia.recuperarPropiedadEntidad(eCancion, "interprete");
		interprete = CatalogoInterpretes.getUnicaInstancia().getInterprete(nombreInterprete);
		if(interprete == null) {
			interprete = new Interprete(nombreInterprete);
			CatalogoInterpretes.getUnicaInstancia().addInterprete(interprete);
		}
		
		nombreEstilo = servPersistencia.recuperarPropiedadEntidad(eCancion, "estilo");
		estilo = CatalogoEstilos.getUnicaInstancia().getEstilo(nombreEstilo);
		if(estilo == null) {
			estilo = new Estilo(nombreEstilo);
			CatalogoEstilos.getUnicaInstancia().addEstilo(estilo);
		}
		
		rutaFichero = servPersistencia.recuperarPropiedadEntidad(eCancion, "rutaFichero");
		numReproduccion = Integer.parseInt(servPersistencia.recuperarPropiedadEntidad(eCancion, "numReproduccion"));

		Cancion cancion = new Cancion(titulo, interprete, estilo, rutaFichero, numReproduccion);
		cancion.setCodigo(codigo);
		
		return cancion;
	}

	public List<Cancion> recuperarTodasCanciones() {
		List<Cancion> canciones = new LinkedList<Cancion>();
		List<Entidad> entidades = servPersistencia.recuperarEntidades("cancion");

		for (Entidad eCancion : entidades) {
			canciones.add(recuperarCancion(eCancion.getId()));
		}
		return canciones;
	}
}
