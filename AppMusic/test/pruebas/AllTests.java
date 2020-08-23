package pruebas;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({controlador.TestControladorAppMusic.class, modelo.TestUsuario.class})
public class AllTests {

}
