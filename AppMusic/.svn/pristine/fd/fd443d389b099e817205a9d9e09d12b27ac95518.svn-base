package modelo;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TestUsuario {

	private Usuario u;
	
	@Before
	public void inicializar() {
		u = new Usuario("Spike", "Spiegel", "spike", "spike", new Date(), "spike@gmail.com", false, new NoDescuento());
	}
	
	@Test
	public void testGetAndSetCodigo() {
		u.setCodigo(5);
		int resultadoEsperado = 5;
		assertEquals("Resultado getAndSetCodigo", resultadoEsperado, u.getCodigo());
	}

	@Test
	public void testGetAndSetNombre() {
		u.setNombre("Joe");
		String resultadoEsperado = "Joe";
		assertEquals("Resultado getAndSetNombre", resultadoEsperado, u.getNombre());
	}

	@Test
	public void testGetAndSetApellidos() {
		u.setApellidos("Lopez");
		String resultadoEsperado = "Lopez";
		assertEquals("Resultado getAndSetApellidos", resultadoEsperado, u.getApellidos());
	}

	@Test
	public void testGetAndSetNombreUsuario() {
		u.setNombreUsuario("Joe");
		String resultadoEsperado = "Joe";
		assertEquals("Resultado getAndSetNombreUsuario", resultadoEsperado, u.getNombreUsuario());
	}

	@Test
	public void testGetAndSetContraseña() {
		u.setContraseña("Joe");
		String resultadoEsperado = "Joe";
		assertEquals("Resultado getAndSetContraseña", resultadoEsperado, u.getContraseña());
	}

	@Test
	public void testSetFechaNacimiento() {
		Date date = new Date();
		u.setFechaNacimiento(date);
		assertSame("Resultado getAndSetFechaNacimiento", date, u.getFechaNacimiento());
	}

	@Test
	public void testGetAndSetEmail() {
		u.setEmail("joe@gmail.com");
		String resultadoEsperado = "joe@gmail.com";
		assertEquals("Resultado getAndSetEmail", resultadoEsperado, u.getEmail());
	}

	@Test
	public void testIsAndSetPremium() {
		u.setPremium(true);
		assertTrue("Resultado isAndSetPremium", u.isPremium());
	}
	
	@Test
	public void testGetAndSetDescuento() {
		assertEquals("Resultado getAndSetDescuento 1", 0, u.getDescuento().calcDescuento(), 0);
		u.setDescuento(new DescuentoFijo());
		assertEquals("Resultado getAndSetDescuento 2", 0.2, u.getDescuento().calcDescuento(), 0);
		u.setDescuento(new DescuentoJovenes());
		assertEquals("Resultado getAndSetDescuento 3", 0.5, u.getDescuento().calcDescuento(), 0);
	}

	@Test
	public void testGestionPlayLists() {
		PlayList pl = new PlayList("clasicos");
		PlayList pl2 = new PlayList("exitos");
		u.addPlayList(pl);
		u.addPlayList(pl2);
		PlayList aux = u.getPlayList("clasicos");
		PlayList aux2 = u.getPlayList("exitos");
		assertSame("Resultado addPlayList 1", pl, aux);
		assertSame("Resultado addPlayList 2", pl2, aux2);
		u.removePlayList(pl);
		aux = u.getPlayList("clasicos");
		assertNull(aux);
		u.removePlayList(pl2);
		boolean baux = u.existePlayList("exitos");
		assertTrue(!baux);
		List<PlayList> laux = u.getPlayLists();
		assertTrue(laux.isEmpty());
	}

	@Test
	public void testGetCancionesPlayList() {
		PlayList pl = new PlayList("pasodobles");
		Cancion c = new Cancion("Amparito Roca", null, null, "", 0);
		Cancion c2 = new Cancion("Gato Montes", null, null, "", 0);
		Cancion c3 = new Cancion("Amapola", null, null, "", 0);
		pl.addCancion(c);
		pl.addCancion(c2);
		pl.addCancion(c3);
		u.addPlayList(pl);
		List<Cancion> lista = u.getCancionesPlayList("pasodobles");
		Iterator<Cancion> itlista = lista.iterator();
		Iterator<Cancion> itPlayList = pl.getCanciones().iterator();
		Cancion caux1;
		Cancion caux2;
		while(itlista.hasNext() || itPlayList.hasNext()) {
			caux1 = itlista.next();
			caux2 = itPlayList.next();
			assertSame("Resultado getCancionesPlayList", caux1, caux2);
		}
	}
	
	@Test
	public void testGestionCancionesRecientes() {
		Cancion c1 = new Cancion("Amapola", null, null, "", 0);
		Cancion c2 = new Cancion("Amparito Roca", null, null, "", 0);
		Cancion c3 = new Cancion("Lacrimosa", null, null, "", 0);
		u.addCancionReciente(c1);
		u.addCancionReciente(c2);
		
		List<Cancion> lista = u.getCancionesRecientes();
		assertTrue("Resultado gestionCancionesRecientes 1", lista.contains(c1));
		assertTrue("Resultado gestionCancionesRecientes 2", lista.contains(c2));
		assertTrue("Resultado gestionCancionesRecientes 3", !lista.contains(c3));
		
		assertTrue("Resultado gestionCancionesRecientes 4", u.existeCancionReciente(c1));
		assertTrue("Resultado gestionCancionesRecientes 5", u.existeCancionReciente(c2));
		assertTrue("Resultado gestionCancionesRecientes 6", !u.existeCancionReciente(c3));
	}

	@Test
	public void testSetPosicionReciente() {
		Cancion c1 = new Cancion("Amapola", null, null, "", 0);
		Cancion c2 = new Cancion("Amparito Roca", null, null, "", 0);
		Cancion c3 = new Cancion("Lacrimosa", null, null, "", 0);
		
		u.addCancionReciente(c1);
		u.addCancionReciente(c2);
		u.addCancionReciente(c3);
		
		u.setPosicionReciente(c2);
		
		List<Cancion> lista = u.getCancionesRecientes();
		
		Cancion aux = lista.get(2);
		assertSame("Resultado setPosicionReciente 1", aux, c2);
		assertEquals("Resultado setPosicionReciente 2", 3, lista.size());
	}
}
