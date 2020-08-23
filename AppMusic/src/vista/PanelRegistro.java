package vista;


import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import controlador.ControladorAppMusic;

import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class PanelRegistro extends JPanel {

	
	private JLabel warningNombre;
	private JLabel warningApellidos;
	private JLabel warningFechaN;
	private JLabel warningEmail;
	private JLabel warningUsuario;
	private JLabel warningClave;
	private JLabel warningAll;
	private JLabel warningClave2;
	private JLabel warningExiste;
	private JLabel labelNombre;
	private JLabel labelApellidos;
	private JLabel labelFechaN;
	private JLabel labelEmail;
	private JLabel labelUsuario;
	private JLabel labelClave;
	private JLabel labelClave2;
	private JTextField textApellidos;
	private JTextField textNombre;
	private JDateChooser textFechaN;
	private JTextField textEmail;
	private JTextField textUsuario;
	private JPasswordField textClave;
	private JPasswordField textClave2;
	private JLabel warningFormatoFecha;
	private JPanel panel_3;
	private JPanel panelCentral;
	private VentanaPrincipal ventana;
	private PanelMenuNorte menuNorte;



	public PanelRegistro(VentanaPrincipal v) {
		ventana = v;
		menuNorte = new PanelMenuNorte(ventana);
		menuNorte.ocultarBtnRegistrarse();
		menuNorte.ocultarBtnPremium();
		menuNorte.ocultarBtnLogout();
		menuNorte.ocultarLuz();
		menuNorte.ocultarLabel();
		menuNorte.ocultarbtnPdf();
		setSize(Constantes.x_size, Constantes.y_size);
		setLayout(new BorderLayout(0, 0));
		add(menuNorte, BorderLayout.NORTH);
		
		panelCentral = new JPanel();
		add(panelCentral, BorderLayout.CENTER);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{50, 0, 0, 0, 0, 0, 50, 0};
		gbl_panel_1.rowHeights = new int[]{50, 0, 0, 0, 0, 0, 0, 40, 40, 0, 0, 0, 10, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelCentral.setLayout(gbl_panel_1);
		
		labelNombre = new JLabel("Nombre:");
		GridBagConstraints gbc_labelNombre = new GridBagConstraints();
		gbc_labelNombre.anchor = GridBagConstraints.EAST;
		gbc_labelNombre.insets = new Insets(0, 0, 5, 5);
		gbc_labelNombre.gridx = 1;
		gbc_labelNombre.gridy = 1;
		panelCentral.add(labelNombre, gbc_labelNombre);
		
		textNombre = new JTextField();
		GridBagConstraints gbc_textNombre = new GridBagConstraints();
		gbc_textNombre.gridwidth = 3;
		gbc_textNombre.insets = new Insets(0, 0, 5, 5);
		gbc_textNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_textNombre.gridx = 2;
		gbc_textNombre.gridy = 1;
		panelCentral.add(textNombre, gbc_textNombre);
		textNombre.setColumns(10);
		
		warningNombre = new JLabel("*");
		warningNombre.setForeground(Color.RED);
		GridBagConstraints gbc_warningNombre = new GridBagConstraints();
		gbc_warningNombre.anchor = GridBagConstraints.WEST;
		gbc_warningNombre.insets = new Insets(0, 0, 5, 5);
		gbc_warningNombre.gridx = 5;
		gbc_warningNombre.gridy = 1;
		panelCentral.add(warningNombre, gbc_warningNombre);
		
		
		labelApellidos = new JLabel("Apellidos:");
		GridBagConstraints gbc_labelApellidos = new GridBagConstraints();
		gbc_labelApellidos.anchor = GridBagConstraints.EAST;
		gbc_labelApellidos.insets = new Insets(0, 0, 5, 5);
		gbc_labelApellidos.gridx = 1;
		gbc_labelApellidos.gridy = 2;
		panelCentral.add(labelApellidos, gbc_labelApellidos);
		
		textApellidos = new JTextField();
		GridBagConstraints gbc_textApellidos = new GridBagConstraints();
		gbc_textApellidos.gridwidth = 3;
		gbc_textApellidos.insets = new Insets(0, 0, 5, 5);
		gbc_textApellidos.fill = GridBagConstraints.HORIZONTAL;
		gbc_textApellidos.gridx = 2;
		gbc_textApellidos.gridy = 2;
		panelCentral.add(textApellidos, gbc_textApellidos);
		textApellidos.setColumns(10);
		
		warningApellidos = new JLabel("*");
		warningApellidos.setForeground(Color.RED);
		GridBagConstraints gbc_warningApellidos = new GridBagConstraints();
		gbc_warningApellidos.anchor = GridBagConstraints.WEST;
		gbc_warningApellidos.insets = new Insets(0, 0, 5, 5);
		gbc_warningApellidos.gridx = 5;
		gbc_warningApellidos.gridy = 2;
		panelCentral.add(warningApellidos, gbc_warningApellidos);
		
		labelFechaN = new JLabel("Fecha Nacimiento:");
		GridBagConstraints gbc_labelFechaN = new GridBagConstraints();
		gbc_labelFechaN.anchor = GridBagConstraints.EAST;
		gbc_labelFechaN.insets = new Insets(0, 0, 5, 5);
		gbc_labelFechaN.gridx = 1;
		gbc_labelFechaN.gridy = 3;
		panelCentral.add(labelFechaN, gbc_labelFechaN);
		
		textFechaN = new JDateChooser();
		textFechaN.setDateFormatString("dd/MM/yyyy");
		GridBagConstraints gbc_textFechaN = new GridBagConstraints();
		gbc_textFechaN.gridwidth = 2;
		gbc_textFechaN.insets = new Insets(0, 0, 5, 5);
		gbc_textFechaN.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFechaN.gridx = 2;
		gbc_textFechaN.gridy = 3;
		panelCentral.add(textFechaN, gbc_textFechaN);
		
		warningFechaN = new JLabel("*");
		warningFechaN.setForeground(Color.RED);
		GridBagConstraints gbc_warningFechaN = new GridBagConstraints();
		gbc_warningFechaN.anchor = GridBagConstraints.WEST;
		gbc_warningFechaN.insets = new Insets(0, 0, 5, 5);
		gbc_warningFechaN.gridx = 4;
		gbc_warningFechaN.gridy = 3;
		panelCentral.add(warningFechaN, gbc_warningFechaN);
		
		labelEmail = new JLabel("Email:");
		GridBagConstraints gbc_labelEmail = new GridBagConstraints();
		gbc_labelEmail.anchor = GridBagConstraints.EAST;
		gbc_labelEmail.insets = new Insets(0, 0, 5, 5);
		gbc_labelEmail.gridx = 1;
		gbc_labelEmail.gridy = 4;
		panelCentral.add(labelEmail, gbc_labelEmail);
		
		textEmail = new JTextField();
		GridBagConstraints gbc_textEmail = new GridBagConstraints();
		gbc_textEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_textEmail.insets = new Insets(0, 0, 5, 5);
		gbc_textEmail.gridx = 2;
		gbc_textEmail.gridy = 4;
		panelCentral.add(textEmail, gbc_textEmail);
		textEmail.setColumns(100);
		
		warningEmail = new JLabel("*");
		warningEmail.setForeground(Color.RED);
		GridBagConstraints gbc_warningEmail = new GridBagConstraints();
		gbc_warningEmail.anchor = GridBagConstraints.WEST;
		gbc_warningEmail.insets = new Insets(0, 0, 5, 5);
		gbc_warningEmail.gridx = 3;
		gbc_warningEmail.gridy = 4;
		panelCentral.add(warningEmail, gbc_warningEmail);
		
		labelUsuario = new JLabel("Usuario:");
		GridBagConstraints gbc_labelUsuario = new GridBagConstraints();
		gbc_labelUsuario.anchor = GridBagConstraints.EAST;
		gbc_labelUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_labelUsuario.gridx = 1;
		gbc_labelUsuario.gridy = 5;
		panelCentral.add(labelUsuario, gbc_labelUsuario);
		
		textUsuario = new JTextField();
		GridBagConstraints gbc_textUsuario = new GridBagConstraints();
		gbc_textUsuario.fill = GridBagConstraints.HORIZONTAL;
		gbc_textUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_textUsuario.gridx = 2;
		gbc_textUsuario.gridy = 5;
		panelCentral.add(textUsuario, gbc_textUsuario);
		textUsuario.setColumns(10);
		
		warningUsuario = new JLabel("*");
		warningUsuario.setForeground(Color.RED);
		GridBagConstraints gbc_warningUsuario = new GridBagConstraints();
		gbc_warningUsuario.anchor = GridBagConstraints.WEST;
		gbc_warningUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_warningUsuario.gridx = 3;
		gbc_warningUsuario.gridy = 5;
		panelCentral.add(warningUsuario, gbc_warningUsuario);
		
		labelClave = new JLabel("Clave:");
		GridBagConstraints gbc_labelClave = new GridBagConstraints();
		gbc_labelClave.insets = new Insets(0, 0, 5, 5);
		gbc_labelClave.anchor = GridBagConstraints.EAST;
		gbc_labelClave.gridx = 1;
		gbc_labelClave.gridy = 6;
		panelCentral.add(labelClave, gbc_labelClave);
		
		textClave = new JPasswordField();
		GridBagConstraints gbc_textClave = new GridBagConstraints();
		gbc_textClave.fill = GridBagConstraints.HORIZONTAL;
		gbc_textClave.insets = new Insets(0, 0, 5, 5);
		gbc_textClave.gridx = 2;
		gbc_textClave.gridy = 6;
		panelCentral.add(textClave, gbc_textClave);
		textClave.setColumns(10);
		
		labelClave2 = new JLabel("Repite:");
		GridBagConstraints gbc_labelClave2 = new GridBagConstraints();
		gbc_labelClave2.anchor = GridBagConstraints.EAST;
		gbc_labelClave2.insets = new Insets(0, 0, 5, 5);
		gbc_labelClave2.gridx = 3;
		gbc_labelClave2.gridy = 6;
		panelCentral.add(labelClave2, gbc_labelClave2);
		
		textClave2 = new JPasswordField();
		GridBagConstraints gbc_textClave2 = new GridBagConstraints();
		gbc_textClave2.insets = new Insets(0, 0, 5, 5);
		gbc_textClave2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textClave2.gridx = 4;
		gbc_textClave2.gridy = 6;
		panelCentral.add(textClave2, gbc_textClave2);
		textClave2.setColumns(10);
		
		warningClave = new JLabel("*");
		warningClave.setForeground(Color.RED);
		GridBagConstraints gbc_warningClave = new GridBagConstraints();
		gbc_warningClave.anchor = GridBagConstraints.WEST;
		gbc_warningClave.insets = new Insets(0, 0, 5, 5);
		gbc_warningClave.gridx = 5;
		gbc_warningClave.gridy = 6;
		panelCentral.add(warningClave, gbc_warningClave);
		
		panel_3 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_3.getLayout();
		flowLayout_1.setHgap(15);
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.gridwidth = 3;
		gbc_panel_3.insets = new Insets(0, 0, 5, 5);
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 2;
		gbc_panel_3.gridy = 8;
		panelCentral.add(panel_3, gbc_panel_3);
		
		JButton btnRegistrar = new JButton("Registrar");
		panel_3.add(btnRegistrar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana.setToPanelLogin();			
			}
		});
		panel_3.add(btnCancelar);
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (checkFields()){
					Date date = textFechaN.getDate();
					ControladorAppMusic.getUnicaInstancia().registrarUsuario(
									textNombre.getText(),
									textApellidos.getText(),
									textUsuario.getText(),
									new String(textClave.getPassword()),
									date,
									textEmail.getText());
					
						JOptionPane.showMessageDialog(
									ventana,
									"Usuario registrado correctamente.",
									"Registro",
									JOptionPane.INFORMATION_MESSAGE);
						
						String nombreUsuario = textUsuario.getText();
						textNombre.setText("");
						textApellidos.setText("");
						textUsuario.setText("");
						textClave.setText("");
						textClave2.setText("");
						textEmail.setText("");
						textFechaN.setDate(null);
						ventana.setToPanelReciente(nombreUsuario);
						}
				}
				
			
		});
		
		warningAll = new JLabel("* Los campos indicados son obligatorios");
		warningAll.setForeground(Color.RED);
		GridBagConstraints gbc_warningAll = new GridBagConstraints();
		gbc_warningAll.gridwidth = 3;
		gbc_warningAll.insets = new Insets(0, 0, 5, 5);
		gbc_warningAll.gridx = 2;
		gbc_warningAll.gridy = 9;
		panelCentral.add(warningAll, gbc_warningAll);
		
		warningClave2 = new JLabel("* Las dos claves deben coincidir");
		warningClave2.setForeground(Color.RED);
		GridBagConstraints gbc_warningClave2 = new GridBagConstraints();
		gbc_warningClave2.gridwidth = 3;
		gbc_warningClave2.insets = new Insets(0, 0, 5, 5);
		gbc_warningClave2.gridx = 2;
		gbc_warningClave2.gridy = 10;
		panelCentral.add(warningClave2, gbc_warningClave2);
		
		warningExiste = new JLabel("* El usuario ya existe");
		warningExiste.setForeground(Color.RED);
		GridBagConstraints gbc_warningExiste = new GridBagConstraints();
		gbc_warningExiste.gridwidth = 3;
		gbc_warningExiste.insets = new Insets(0, 0, 5, 5);
		gbc_warningExiste.gridx = 2;
		gbc_warningExiste.gridy = 11;
		panelCentral.add(warningExiste, gbc_warningExiste);
		
		warningFormatoFecha = new JLabel("* Introducir fecha con el formato: dd/MM/yyyy");
		warningFormatoFecha.setForeground(Color.RED);
		GridBagConstraints gbc_warningFormatoFecha = new GridBagConstraints();
		gbc_warningFormatoFecha.gridwidth = 3;
		gbc_warningFormatoFecha.insets = new Insets(0, 0, 0, 5);
		gbc_warningFormatoFecha.gridx = 2;
		gbc_warningFormatoFecha.gridy = 12;
		panelCentral.add(warningFormatoFecha, gbc_warningFormatoFecha);
		
		ocultarErrores();
	}
	
	/**
	 * Comprueba que los campos de registro estan bien
	 */
	private boolean checkFields() {
		boolean ok=true;
//		/*borrar todos los errores en pantalla*/
		ocultarErrores();

		if (textNombre.getText().trim().isEmpty()) {
			warningNombre.setVisible(true); 
			ok=false;
		}
		if (textApellidos.getText().trim().isEmpty()) {
			warningApellidos.setVisible(true); 
			ok=false;
		}
		
		if (textFechaN.getDate() == null){
			warningFechaN.setVisible(true);
			ok=false;
		}
		if (textEmail.getText().trim().isEmpty()){
			warningEmail.setVisible(true);
			ok=false;
		}

		if (textUsuario.getText().trim().isEmpty()) {
			warningUsuario.setVisible(true); 
			ok=false;
		}
		
		String password = new String(textClave.getPassword());
		String password2 = new String(textClave2.getPassword());
		
		if (password.equals("")) {
			warningClave.setVisible(true); 
			ok=false;
		}
		
		if (!ok) warningAll.setVisible(true);
		
		if (ok && !password.equals(password2)) {
			warningClave.setVisible(true);
			warningClave2.setVisible(true);
			ok=false;
		}
		//Comprobar el formato de la fecha introducida
		Pattern patron = Pattern.compile("([0-2][0-9]|3[0-1])(\\/)(0[1-9]|1[0-2])(\\/)(\\d{4})");
		String formato = textFechaN.getDateFormatString();
		SimpleDateFormat datef = new SimpleDateFormat(formato);
		String fecha = String.valueOf(datef.format(textFechaN.getDate()));
		Matcher mat = patron.matcher(fecha);
		boolean resultado = mat.matches();
		if (ok && !resultado){
			warningFormatoFecha.setVisible(true);
			ok=false;
		}
		 
		//		/* Comprobar que no exista otro usuario con igual login */
		if (ControladorAppMusic.getUnicaInstancia().isUsuarioRegistrado(textUsuario.getText())) {
			warningExiste.setVisible(true); 
			ok=false;		
		}
		return ok;
	}
	/**
	 * Oculta todos los errores que pueda haber en la pantalla
	 */
	private void ocultarErrores() {
		warningAll.setVisible(false);
		warningApellidos.setVisible(false);
		warningFechaN.setVisible(false);
		warningEmail.setVisible(false);
		warningClave.setVisible(false);
		warningClave2.setVisible(false);
		warningExiste.setVisible(false);
		warningNombre.setVisible(false);
		warningUsuario.setVisible(false);
		warningFormatoFecha.setVisible(false);
	}

}
