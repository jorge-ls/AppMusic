package vista;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


import controlador.ControladorAppMusic;
import javax.swing.JLabel;

import pulsador.IEncendidoListener;
import pulsador.Luz;

import java.io.File;
import java.util.EventObject;

@SuppressWarnings("serial")
public class PanelMenuNorte extends JPanel {
	private VentanaPrincipal ventana;
	
	private JButton btnRegistrarse;
	private JButton bPremium;
	private JButton btnLogin;
	private JButton btnLogout;
	private JLabel lblNewLabel;
	private JButton bPdf;
	private Luz luz;
	
	
	public PanelMenuNorte(VentanaPrincipal v) {
		ventana = v;
		FlowLayout flowLayout = (FlowLayout) getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		flowLayout.setVgap(15);
		
		btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ventana.setToPanelRegistro();
			}
		});
		
		luz = new Luz();
		luz.addEncendidoListener(new IEncendidoListener(){
			public void enteradoCambioEncendido (EventObject e){
				JFileChooser chooser = new JFileChooser();
				chooser.showOpenDialog(ventana);
				File fichero = chooser.getSelectedFile();
				if (fichero != null) {
				ControladorAppMusic.getUnicaInstancia().buscarCanciones(fichero.getAbsolutePath());
				}
				
			}
			 
		});
		
		luz.setColor(Color.YELLOW);
		
		add(luz);
		
		lblNewLabel = new JLabel("New label");
		add(lblNewLabel);
		add(btnRegistrarse);
		
		bPremium = new JButton("");
		bPremium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!ControladorAppMusic.getUnicaInstancia().isUsuarioPremium()){
					int res =JOptionPane.showConfirmDialog(ventana, 
							 "¿Desea activar los servicios premium?", 
							  "Premium", JOptionPane.YES_NO_OPTION);
					if (res == JOptionPane.YES_OPTION){
						ControladorAppMusic.getUnicaInstancia().activarPremium();
						JOptionPane.showMessageDialog(
								ventana,
								"Los servicios premium han sido activados",
								"Premium",
								JOptionPane.INFORMATION_MESSAGE);
						if (ControladorAppMusic.getUnicaInstancia().activarDescuento()==0){
							JOptionPane.showMessageDialog(
									ventana,
									"Se ha aplicado un descuento fijo",
									"Premium",
									JOptionPane.INFORMATION_MESSAGE);
						
						}
						else{
						JOptionPane.showMessageDialog(
								ventana,
								"Se ha aplicado un descuento para jovenes",
								"Premium",
								JOptionPane.INFORMATION_MESSAGE);
						
					    }
					}
				}
				else{
					int res1 =JOptionPane.showConfirmDialog(ventana, 
												 "¿Desea desactivar los servicios premium?", 
												  "Premium", JOptionPane.YES_NO_OPTION);
					if (res1 == JOptionPane.YES_OPTION){
						ControladorAppMusic.getUnicaInstancia().desactivarPremium();
						JOptionPane.showMessageDialog(
								ventana,
								"Los servicios premium han sido desactivados",
								"Premium",
								JOptionPane.INFORMATION_MESSAGE);
						
					}
				
				
				}
			}
		});
		bPremium.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/vista/1486504777-crown-optimization-royal-princes-winner-premium-service_81383.png")));
		add(bPremium);
		
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ventana.setToPanelLogin();
			}
		});
		
		bPdf = new JButton("");
		bPdf.setIcon(new ImageIcon(PanelMenuNorte.class.getResource("/vista/if_ACP_PDF 2_file_document_51955.png")));
		bPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ControladorAppMusic.getUnicaInstancia().imprimirPlayListsUsuario();
			}
		});
		add(bPdf);
		add(btnLogin);
		
		btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ControladorAppMusic.getUnicaInstancia().detenerCancion();
				ventana.setToPanelLogin();
			}
		});
		add(btnLogout);
	}
	
	public void ocultarBtnRegistrarse(){
		btnRegistrarse.setVisible(false);
	}
	
	public void ocultarBtnPremium(){
		bPremium.setVisible(false);
	}
	
	public void ocultarBtnLogin(){
		btnLogin.setVisible(false);
	}
	
	public void ocultarBtnLogout(){
		btnLogout.setVisible(false);
	}
	
	public void ocultarLabel(){
		lblNewLabel.setVisible(false);
	}
	
	public void ocultarLuz(){
		luz.setVisible(false);
	}
	
	public void ocultarbtnPdf(){
		bPdf.setVisible(false);
	}
	public void setTextLabel(String nombre){
		lblNewLabel.setText("Hola "+nombre);
	}
	
	
}
