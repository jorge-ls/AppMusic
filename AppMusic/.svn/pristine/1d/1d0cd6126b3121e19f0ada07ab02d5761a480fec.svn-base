package modelo;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import persistencia.DAOException;
import persistencia.FactoriaDAO;
import persistencia.IAdaptadorCancionDAO;

public class CatalogoCanciones {
	private Map<String, Cancion> canciones;
	private static CatalogoCanciones unicaInstancia = null;
	
	/**
	 * Carpeta con las canciones (ruta relativa al proyecto)
	 */
    private static final String RUTA_CANCIONES = "Canciones";
  
	private FactoriaDAO dao;
	private IAdaptadorCancionDAO adaptadorCancion;
	
	private CatalogoCanciones() {
		try {
  			dao = FactoriaDAO.getInstancia(FactoriaDAO.DAO_TDS);
  			adaptadorCancion = dao.getCancionDAO();
  			canciones = new HashMap<String, Cancion>();
  			this.cargarCatalogo();
  		} catch (DAOException eDAO) {
  			eDAO.printStackTrace();
  		}
	}
	public static CatalogoCanciones getUnicaInstancia(){
		if(unicaInstancia == null)
			unicaInstancia = new CatalogoCanciones();
		return unicaInstancia;
	}
	
	/*devuelve todas las canciones*/
	public List<Cancion> getCanciones(){
		ArrayList<Cancion> lista = new ArrayList<Cancion>();
		for (Cancion cancion: canciones.values()) 
			lista.add(cancion);
		return lista;
	}
	
	public Cancion getCancion(String rutaFichero) {
		return canciones.get(rutaFichero); 
	}
	
	
	public void addCancion(Cancion cancion){
		canciones.put(cancion.getRutaFichero(),cancion);
	}
	public void removeCancion (Cancion cancion) {
		canciones.remove(cancion.getRutaFichero());
	}
	
	// Carga las canciones del directorio que no existan en la base de datos
	private void cargarDelDirectorio() {
		File dir = new File(RUTA_CANCIONES);
		File[] estilos = dir.listFiles();
		File[] cancionesDir;
		String estiloActual;
		if(estilos != null) {
			for(int i = 0; i < estilos.length; i++) {
				estiloActual = estilos[i].getName();
				cancionesDir = estilos[i].listFiles();
				for(int j = 0; j < cancionesDir.length; j++) {
					if(getCancion(cancionesDir[j].getPath()) == null) {	// La canción no existe y hay que crearla
						String nombreCancion = cancionesDir[j].getName();	// nombre de la cancion: interprete - titulo
						String[] nombreCancionSplit = nombreCancion.split("-", 2);
						String interpreteActual = nombreCancionSplit[0].trim();	// Extraemos el interprete (trim para quitar espacios innecesarios)
						
						//Extraemos el titulo sin la extensión del archivo
						String tituloActual = nombreCancionSplit[1].substring(0,nombreCancionSplit[1].lastIndexOf(".")).trim();
						System.out.println("Nueva Cancion: " + estiloActual + " - " + interpreteActual + " - " + tituloActual);
						// Obtenemos el interprete, si no existe se crea uno nuevo
						Interprete in = CatalogoInterpretes.getUnicaInstancia().getInterprete(interpreteActual);
						if(in == null) {
							in = new Interprete(interpreteActual);
							CatalogoInterpretes.getUnicaInstancia().addInterprete(in);
						}
						
						// Obtenemos el estilo, si no existe se crea uno nuevo
						Estilo est = CatalogoEstilos.getUnicaInstancia().getEstilo(estiloActual);
						if(est == null) {
							est = new Estilo(estiloActual);
							CatalogoEstilos.getUnicaInstancia().addEstilo(est);
						}
						
						Cancion cancionActual = new Cancion(tituloActual, in, est, cancionesDir[j].getPath(), 0);
						adaptadorCancion.registrarCancion(cancionActual);
						addCancion(cancionActual);
					}
				}
			}
		}
	}
	
	/*Recupera todas las canciones para trabajar con ellas en memoria*/
	private void cargarCatalogo() throws DAOException {
		 List<Cancion> cancionesBD = adaptadorCancion.recuperarTodasCanciones();
		 for (Cancion cancion : cancionesBD) 
			     addCancion(cancion);
		 cargarDelDirectorio();
	}


}

