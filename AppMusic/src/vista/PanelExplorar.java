package vista;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;


import javax.swing.JComboBox;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import javax.swing.table.AbstractTableModel;


import controlador.ControladorAppMusic;
import modelo.Cancion;
import modelo.Estilo;

import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.ListSelectionModel;

@SuppressWarnings("serial")
public class PanelExplorar extends JPanel {

	/**
	 * 
	 */
	private JTextField txtInterprete;
	private JTextField txtTitulo;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton botonPlay;
	private JButton botonPause;
	private JButton botonStop;
	private JButton botonRetroceder;
	private JButton botonAvanzar;
	private JPanel panelCentral;
	
	private PanelMenuNorte menuNorte;
	private PanelMenuOeste menuOeste;
	private VentanaPrincipal ventana;
	
	
	
	class MiTableModel extends AbstractTableModel {
		 private String[] columnNames = {"Titulo", "Interprete"};
		 private List<Cancion> data;
		 
		 public MiTableModel(List<Cancion> data) {
			 this.data = data;
		 }
		 
		 public int getColumnCount() {
			 return columnNames.length; 
		 }
		 
		 public int getRowCount() {
			 return data.size();
		 }
		 
		 public String getColumnName(int col) {
			 return columnNames[col];
		 }
		 
		 public Object getValueAt(int row, int col) {
			 if (col == 0)
				 return data.get(row).getTitulo(); 
			 else
				 return data.get(row).getInterprete().getNombre();
		 }
		 
		 public Cancion getCancionAt(int row) {
			 return data.get(row);
		 }
	}


	public PanelExplorar(VentanaPrincipal v) {
		ventana = v;
		menuNorte = new PanelMenuNorte(ventana);
		menuNorte.ocultarBtnRegistrarse();
		menuNorte.ocultarBtnLogin();
		menuNorte.setTextLabel(PanelReciente.nombre);
		menuOeste = new PanelMenuOeste(ventana);
		setLayout(new BorderLayout(0, 0));
		setSize(Constantes.x_size, Constantes.y_size);
		add(menuNorte, BorderLayout.NORTH);
		add(menuOeste, BorderLayout.WEST);
		
		panelCentral = new JPanel();
		add(panelCentral, BorderLayout.CENTER);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{50, 150, 20, 150, 20, 150, 50, 0};
		gbl_panel_2.rowHeights = new int[]{20, 0, 30, 40, 0, 0, 0, 15, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelCentral.setLayout(gbl_panel_2);
		
		txtInterprete = new JTextField();
		txtInterprete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtInterprete.setText("");
			}
		});
		txtInterprete.setText("Interprete");
		GridBagConstraints gbc_txtInterprete = new GridBagConstraints();
		gbc_txtInterprete.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtInterprete.insets = new Insets(0, 0, 5, 5);
		gbc_txtInterprete.gridx = 1;
		gbc_txtInterprete.gridy = 1;
		panelCentral.add(txtInterprete, gbc_txtInterprete);
		txtInterprete.setColumns(10);
		
		txtTitulo = new JTextField();
		txtTitulo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtTitulo.setText("");
			}
		});
		txtTitulo.setText("Titulo");
		GridBagConstraints gbc_txtTitulo = new GridBagConstraints();
		gbc_txtTitulo.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTitulo.insets = new Insets(0, 0, 5, 5);
		gbc_txtTitulo.gridx = 3;
		gbc_txtTitulo.gridy = 1;
		panelCentral.add(txtTitulo, gbc_txtTitulo);
		txtTitulo.setColumns(10);
		
		JComboBox<String> estilosComboBox = new JComboBox<String>();
		GridBagConstraints Estilo = new GridBagConstraints();
		Estilo.insets = new Insets(0, 0, 5, 5);
		Estilo.fill = GridBagConstraints.HORIZONTAL;
		Estilo.gridx = 5;
		Estilo.gridy = 1;
		estilosComboBox.setEditable(true);
		panelCentral.add(estilosComboBox, Estilo);
		
		List<Estilo> estilos = ControladorAppMusic.getUnicaInstancia().getEstilosMusicales();
		for (Estilo estilo : estilos) {
			estilosComboBox.addItem(estilo.getNombre());
		}
		
		JPanel panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.gridwidth = 5;
		gbc_panel_3.insets = new Insets(0, 0, 5, 5);
		gbc_panel_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_3.gridx = 1;
		gbc_panel_3.gridy = 2;
		panelCentral.add(panel_3, gbc_panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				if(estilosComboBox.getSelectedItem() != null) {
					table.setModel(new MiTableModel(ControladorAppMusic.getUnicaInstancia()
							.buscarCanciones(estilosComboBox.getSelectedItem().toString(),
							txtInterprete.getText(), txtTitulo.getText())));
				}
			}
		});
		panel_3.add(btnBuscar);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridwidth = 5;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 3;
		panelCentral.add(scrollPane, gbc_scrollPane);
		
		table = new JTable(new MiTableModel(ControladorAppMusic.getUnicaInstancia().getCanciones()));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2){
					MiTableModel m = (MiTableModel)table.getModel();
					if(table.getSelectedRow() >= 0) {
						Cancion cancion = m.getCancionAt(table.getSelectedRow());
						if (!ControladorAppMusic.getUnicaInstancia().existeCancionReciente(cancion)){
							ControladorAppMusic.getUnicaInstancia().añadirCancionReciente(cancion);
						}
						else{
							ControladorAppMusic.getUnicaInstancia().setPosicionReciente(cancion);
						}
					
						ControladorAppMusic.getUnicaInstancia().setCancionActual(cancion);
						ControladorAppMusic.getUnicaInstancia().reproducirCancion();
					}
				}
			}
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		table.setPreferredScrollableViewportSize(new Dimension(250, 150));
		scrollPane.setViewportView(table);
		
		JPanel panel_5 = new JPanel();
		GridBagConstraints gbc_panel_5 = new GridBagConstraints();
		gbc_panel_5.gridwidth = 5;
		gbc_panel_5.insets = new Insets(0, 0, 5, 5);
		gbc_panel_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_5.gridx = 1;
		gbc_panel_5.gridy = 4;
		panelCentral.add(panel_5, gbc_panel_5);
		
		botonPlay = new JButton("");
		botonPlay.setIcon(new ImageIcon(PanelExplorar.class.getResource("/vista/play-button.png")));
		botonPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				MiTableModel m = (MiTableModel)table.getModel();
				if (table.getSelectedRow() >= 0) {
					Cancion cancion = m.getCancionAt(table.getSelectedRow());
					if (!ControladorAppMusic.getUnicaInstancia().existeCancionReciente(cancion)){
						ControladorAppMusic.getUnicaInstancia().añadirCancionReciente(cancion);
					}
					else{
						ControladorAppMusic.getUnicaInstancia().setPosicionReciente(cancion);
					}
					ControladorAppMusic.getUnicaInstancia().setCancionActual(cancion);
					ControladorAppMusic.getUnicaInstancia().reproducirCancion();
				}
			}
		});
		panel_5.add(botonPlay);
		
		JPanel panel_4 = new JPanel();
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.gridwidth = 5;
		gbc_panel_4.insets = new Insets(0, 0, 5, 5);
		gbc_panel_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_4.gridx = 1;
		gbc_panel_4.gridy = 6;
		panelCentral.add(panel_4, gbc_panel_4);
		
		botonPause = new JButton("");
		botonPause.setIcon(new ImageIcon(PanelExplorar.class.getResource("/vista/botton_pause.png")));
		botonPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				ControladorAppMusic.getUnicaInstancia().pausarCancion();
			}
		});
		panel_4.add(botonPause);
		
		botonStop = new JButton("");
		botonStop.setIcon(new ImageIcon(PanelExplorar.class.getResource("/vista/boton1.png")));
		botonStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				ControladorAppMusic.getUnicaInstancia().detenerCancion();
			}
		});
		panel_4.add(botonStop);
		
		botonRetroceder = new JButton("");
		botonRetroceder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MiTableModel m = (MiTableModel)table.getModel();
				if (table.getSelectedRow() > 0){
					Cancion cancion = m.getCancionAt(table.getSelectedRow()-1);
					table.changeSelection(table.getSelectedRow()-1,0,false,false);
					if (!ControladorAppMusic.getUnicaInstancia().existeCancionReciente(cancion)){
						ControladorAppMusic.getUnicaInstancia().añadirCancionReciente(cancion);
					}
					else{
						ControladorAppMusic.getUnicaInstancia().setPosicionReciente(cancion);
					}
					ControladorAppMusic.getUnicaInstancia().setCancionActual(cancion);
					ControladorAppMusic.getUnicaInstancia().reproducirCancion();
					
				}
			}
		});
		botonRetroceder.setIcon(new ImageIcon(PanelExplorar.class.getResource("/vista/if_back_rewind_63426.png")));
		panel_4.add(botonRetroceder);
		
		botonAvanzar = new JButton("");
		botonAvanzar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MiTableModel m = (MiTableModel)table.getModel();
				if (table.getSelectedRow() != m.getRowCount()-1){
					Cancion cancion = m.getCancionAt(table.getSelectedRow()+1);
					table.changeSelection(table.getSelectedRow()+1,0,false,false);
					if (!ControladorAppMusic.getUnicaInstancia().existeCancionReciente(cancion)){
						ControladorAppMusic.getUnicaInstancia().añadirCancionReciente(cancion);
					}
					else{
						ControladorAppMusic.getUnicaInstancia().setPosicionReciente(cancion);
					}
					ControladorAppMusic.getUnicaInstancia().setCancionActual(cancion);
					ControladorAppMusic.getUnicaInstancia().reproducirCancion();
				}
				else{
					Cancion cancion = m.getCancionAt(0);
					table.changeSelection(0,0,false,false);
					if (!ControladorAppMusic.getUnicaInstancia().existeCancionReciente(cancion)){
						ControladorAppMusic.getUnicaInstancia().añadirCancionReciente(cancion);
					}
					else{
						ControladorAppMusic.getUnicaInstancia().setPosicionReciente(cancion);
					}
					ControladorAppMusic.getUnicaInstancia().setCancionActual(cancion);
					ControladorAppMusic.getUnicaInstancia().reproducirCancion();
					
				}
			}
		});
		botonAvanzar.setIcon(new ImageIcon(PanelExplorar.class.getResource("/vista/if_forward_arrows_arrow_front_go_1868949.png")));
		panel_4.add(botonAvanzar);
	}
}
