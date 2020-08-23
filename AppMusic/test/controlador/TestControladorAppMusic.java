package controlador;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

import modelo.Descuento;
import modelo.DescuentoJovenes;
import modelo.NoDescuento;
import modelo.Usuario;

public class TestControladorAppMusic {
	
	private static ControladorAppMusic controlador;
	
	@BeforeClass
	public static void inicializar(){
		controlador = ControladorAppMusic.getUnicaInstancia();
		controlador.registrarUsuario("Jose", "Henarejos", "angerl-ASC", "angerl-ASC", new Date(), "jose.henarejosb@um.es");
	}
	
	@Test
	public void testIsCumpleañosUsuario() {
		assertTrue("Resultado isCumpleañosUsuario", controlador.isCumpleañosUsuario());
	}

	@Test
	public void testActivarDescuento() {
		int resultado = controlador.activarDescuento();
		assertEquals("Resultado activarDescuento 1", 1, resultado);
		Usuario u = controlador.getUsuarioActual();
		Descuento d = u.getDescuento();
		assertTrue("Resultado activarDescuento 2", d instanceof DescuentoJovenes);
	}
	
	@Test
	public void testDesactivarPremium() {
		controlador.desactivarPremium();
		assertTrue("Resultado desactivarPremium 1", !controlador.isUsuarioPremium());
		Usuario u = controlador.getUsuarioActual();
		Descuento d = u.getDescuento();
		assertTrue("Resulado desactivarPremium 2", d instanceof NoDescuento);
	}

	@Test
	public void testLoginUsuario() {
		assertTrue("Resultado login correcto", controlador.loginUsuario("angerl-ASC", "angerl-ASC"));
		assertTrue("Resultado login incorrecto", !controlador.loginUsuario("angerl-ASC", "1234"));
		assertTrue("Resultado login de usuario inexistente", !controlador.loginUsuario("joe", "spike"));
	}
}
