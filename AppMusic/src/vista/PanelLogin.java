package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controlador.ControladorAppMusic;

@SuppressWarnings("serial")
public class PanelLogin extends JPanel {
	private VentanaPrincipal ventana;
	private PanelMenuNorte menuNorte;
	private JPanel panelCentral;
	private JTextField textLogin;
	private JPasswordField textPassword;
	
	public PanelLogin(VentanaPrincipal v) {
		ventana = v;
		menuNorte = new PanelMenuNorte(ventana);
		menuNorte.ocultarBtnPremium();
		menuNorte.ocultarBtnLogout();
		menuNorte.ocultarBtnLogin();
		menuNorte.ocultarLabel();
		menuNorte.ocultarLuz();
		menuNorte.ocultarbtnPdf();
		setSize(Constantes.x_size, Constantes.y_size);
		setLayout(new BorderLayout(0, 0));
		add(menuNorte, BorderLayout.NORTH);
		
		panelCentral = new JPanel();
		add(panelCentral, BorderLayout.CENTER);
		GridBagLayout gbl_panel_center = new GridBagLayout();
		gbl_panel_center.columnWidths = new int[]{50, 0, 0, 50, 0};
		gbl_panel_center.rowHeights = new int[]{50, 0, 0, 30, 0, 50, 0};
		gbl_panel_center.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_center.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelCentral.setLayout(gbl_panel_center);
			
		JLabel lblUsuario = new JLabel("Usuario : ");
		GridBagConstraints gbc_lblUsuario = new GridBagConstraints();
		gbc_lblUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsuario.anchor = GridBagConstraints.EAST;
		gbc_lblUsuario.gridx = 1;
		gbc_lblUsuario.gridy = 1;
		panelCentral.add(lblUsuario, gbc_lblUsuario);
			
		textLogin = new JTextField();
		GridBagConstraints gbc_textLogin = new GridBagConstraints();
		gbc_textLogin.insets = new Insets(0, 0, 5, 5);
		gbc_textLogin.fill = GridBagConstraints.HORIZONTAL;
		gbc_textLogin.gridx = 2;
		gbc_textLogin.gridy = 1;
		panelCentral.add(textLogin, gbc_textLogin);
		textLogin.setColumns(10);
			
		JLabel lblClave = new JLabel("Clave : ");
		GridBagConstraints gbc_lblClave = new GridBagConstraints();
		gbc_lblClave.anchor = GridBagConstraints.EAST;
		gbc_lblClave.insets = new Insets(0, 0, 5, 5);
		gbc_lblClave.gridx = 1;
		gbc_lblClave.gridy = 2;
		panelCentral.add(lblClave, gbc_lblClave);
			
		textPassword = new JPasswordField();
		GridBagConstraints gbc_textPassword = new GridBagConstraints();
		gbc_textPassword.insets = new Insets(0, 0, 5, 5);
		gbc_textPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_textPassword.gridx = 2;
		gbc_textPassword.gridy = 2;
		panelCentral.add(textPassword, gbc_textPassword);
		
		//PANEL BOTONES ACEPTAR/CANCELAR
		JPanel panel_acept_cancel = new JPanel();
		FlowLayout fl_panel_acept_cancel = (FlowLayout) panel_acept_cancel.getLayout();
		fl_panel_acept_cancel.setHgap(15);
		GridBagConstraints gbc_panel_acept_cancel = new GridBagConstraints();
		gbc_panel_acept_cancel.insets = new Insets(0, 0, 5, 5);
		gbc_panel_acept_cancel.fill = GridBagConstraints.BOTH;
		gbc_panel_acept_cancel.gridx = 2;
		gbc_panel_acept_cancel.gridy = 4;
		panelCentral.add(panel_acept_cancel, gbc_panel_acept_cancel);
			
		JButton btnLogin = new JButton("Aceptar");  
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean login;
				login = ControladorAppMusic.getUnicaInstancia().loginUsuario(textLogin.getText(), new String(textPassword.getPassword()));
				if (login){
					String nombreUsuario = textLogin.getText();
					if (ControladorAppMusic.getUnicaInstancia().isCumpleañosUsuario()){
						textLogin.setText("");
						textPassword.setText("");
						ventana.setToPanelFelizCumpleaños(nombreUsuario);
					}
					else{
						textLogin.setText("");
						textPassword.setText("");
						ventana.setToPanelReciente(nombreUsuario);
					}
					
				}
				else {
					JOptionPane.showMessageDialog(ventana, "Nombre de usuario o contraseña erroneo", "Error", JOptionPane.ERROR_MESSAGE);
					textLogin.setText("");
					textPassword.setText("");
				}
				//add(menuOeste, BorderLayout.WEST);
				//ventana.setToPanelReciente();
			}
		});
		panel_acept_cancel.add(btnLogin);
			
		JButton btnRegistro = new JButton("Cancelar");
		btnRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana.dispose();
			}
		});
		panel_acept_cancel.add(btnRegistro);
	}
}
