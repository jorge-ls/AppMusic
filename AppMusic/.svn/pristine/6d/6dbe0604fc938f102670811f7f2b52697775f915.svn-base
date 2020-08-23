package modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import persistencia.DAOException;
import persistencia.FactoriaDAO;
import persistencia.IAdaptadorUsuarioDAO;


public class CatalogoUsuarios {

	private Map<String, Usuario> usuarios;
	private static CatalogoUsuarios unicaInstancia = null;
	
	private FactoriaDAO dao;
	private IAdaptadorUsuarioDAO adaptadorUsuario;
	
	private CatalogoUsuarios() {
		try {
  			dao = FactoriaDAO.getInstancia(FactoriaDAO.DAO_TDS);
  			adaptadorUsuario = dao.getUsuarioDAO();
  			usuarios = new HashMap<String, Usuario>();
  			this.cargarCatalogo();
  		} catch (DAOException eDAO) {
  			eDAO.printStackTrace();
  		}
	}
	public static CatalogoUsuarios getUnicaInstancia(){
		if(unicaInstancia == null)
			unicaInstancia = new CatalogoUsuarios();
		return unicaInstancia;
	}
	
	/*devuelve todos los usuarios*/
	public List<Usuario> getUsuarios(){
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		for (Usuario u : usuarios.values()) 
			lista.add(u);
		return lista;
	}
	
	public Usuario getUsuario(String nombreUsuario) {
		return usuarios.get(nombreUsuario); 
	}
	
	public void addUsuario(Usuario usuario) {
		usuarios.put(usuario.getNombreUsuario(), usuario);
	}
	
	public void removeUsuario (Usuario usuario) {
		usuarios.remove(usuario.getNombreUsuario());

	}
	
	/*Recupera todos los usuarios para trabajar con ellas en memoria*/
	private void cargarCatalogo() throws DAOException {
		 List<Usuario> usuariosBD = adaptadorUsuario.recuperarTodosUsuarios();
		 for (Usuario usuario : usuariosBD) 
			     usuarios.put(usuario.getNombreUsuario(), usuario);
	}

}
