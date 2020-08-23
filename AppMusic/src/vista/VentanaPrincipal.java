package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import modelo.PlayList;


@SuppressWarnings("serial")
public class VentanaPrincipal extends JFrame {
	//private JTextField textLogin;
	//private JPasswordField textPassword;
	
	private JPanel contenedorPrincipal;
	private JPanel panelLogin;
	private JPanel panelRegistro;
	private JPanel panelExplorar;
	private JPanel panelReciente;
	private JPanel panelNuevaLista;
	private JPanel panelMasEscuchadas;
	private JPanel panelMisListas;
	private JPanel panelFelizCumpleaños;
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal window = new VentanaPrincipal();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	* Create the application.
	*/
	public VentanaPrincipal() {
		//panelRegistro = new PanelRegistro(this);
		panelLogin = new PanelLogin(this);
		//panelExplorar = new PanelExplorar(this);
		//panelReciente = new PanelReciente(this);
		//panelNuevaLista = new PanelNuevaLista(this);
		//panelMisListas = new PanelMisListas(this);
		//panelMasEscuchadas = new PanelMasEscuchadas(this);
		contenedorPrincipal = (JPanel) getContentPane();
		initialize();
	}

	/**
	* Initialize the contents of the frame.
	*/
	private void initialize() {
		setSize(Constantes.ventana_x_size, Constantes.ventana_y_size);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(panelLogin);
	}
	
	public JPanel getPanelMisListas() {
		return panelMisListas;
	}
	
	public JPanel getPanelMasEscuchadas() {
		return panelMasEscuchadas;
	}
	
	public JPanel getPanelNuevaLista() {
		return panelNuevaLista;
	}
	
	public JPanel getPanelFelizCumpleaños(){
		return panelFelizCumpleaños;
	}
	
	public JPanel getPanelReciente() {
		return panelReciente;
	}
	
	public JPanel getPanelExplorar() {
		return panelExplorar;
	}
	
	public JPanel getPanelLogin() {
		return panelLogin;
	}
	
	public JPanel getPanelRegistro() {
		return panelRegistro;
	}
	
	public void setToPanelLogin() {
		setContentPane(panelLogin);
		getContentPane().revalidate();
	}
	
	public void setToPanelRegistro() {
		panelRegistro = new PanelRegistro(this);
		setContentPane(panelRegistro);
		getContentPane().revalidate();
	}
	
	public void setToPanelReciente(String nombre) {
		panelReciente = new PanelReciente(this,nombre);
		setContentPane(panelReciente);
		getContentPane().revalidate();
	}
	
	public void setToPanelExplorar() {
		panelExplorar = new PanelExplorar(this);
		setContentPane(panelExplorar);
		getContentPane().revalidate();
	}
	
	public void setToPanelNuevaLista() {
		panelNuevaLista = new PanelNuevaLista(this);
		setContentPane(panelNuevaLista);
		getContentPane().revalidate();
	}
	
	public void setToPanelMisListas(PlayList pl) {
		panelMisListas = new PanelMisListas(this, pl);
		setContentPane(panelMisListas);
		getContentPane().revalidate();
	}
	
	public void setToPanelMasEscuchadas() {
		panelMasEscuchadas = new PanelMasEscuchadas(this);
		setContentPane(panelMasEscuchadas);
		getContentPane().revalidate();
	}
	
	public void setToPanelFelizCumpleaños(String nombre){
		panelFelizCumpleaños = new PanelFelizCumpleaños(this,nombre);
		setContentPane(panelFelizCumpleaños);
		getContentPane().revalidate();
	}
}
