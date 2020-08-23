package vista;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import java.awt.Component;

@SuppressWarnings("serial")
public class PanelFelizCumpleaños extends JPanel {
	private VentanaPrincipal ventana;
	public PanelFelizCumpleaños(VentanaPrincipal v,String nombre) {
		ventana = v;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel lblfelizCumpleaos = new JLabel("\u00A1Feliz Cumplea\u00F1os!");
		lblfelizCumpleaos.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblfelizCumpleaos.setFont(new Font("Tahoma 11",0,50));
		add(lblfelizCumpleaos);
		
		JLabel labelUsuario = new JLabel(nombre);
		labelUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
		labelUsuario.setFont(new Font("Tahoma 11",0,50));
		add(labelUsuario);
		
		JButton btnSalir = new JButton("Continuar");
		btnSalir.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ventana.setToPanelReciente(nombre);
			}
		});
		add(btnSalir);
	}

}
