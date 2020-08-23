package vista;


import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.border.BevelBorder;
import javax.swing.table.AbstractTableModel;

import controlador.ControladorAppMusic;
import modelo.Cancion;
import modelo.Estilo;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.BoxLayout;
import javax.swing.border.EtchedBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class PanelNuevaLista extends JPanel {

	private JPanel panelCentral;
	
	private PanelMenuNorte menuNorte;
	private PanelMenuOeste menuOeste;
	private VentanaPrincipal ventana;
	private JPanel panel;
	private JPanel panel_1;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JPanel panel_5;
	private JPanel panel_6;
	private JPanel panel_7;
	private JTable table;
	private JScrollPane scrollPane;
	private JTable table_1;
	private JScrollPane scrollPane_1;
	private JPanel panel_2;
	private JButton btnAadir;
	private JButton btnQuitar;
	private JPanel panel_3;
	private JPanel panel_4;
	private JTextField textTitulo;
	private JButton btnVer;
	private JButton btnEliminar;
	private JTextField textInterprete;
	private JButton btnBuscar;
	private JComboBox<String> comboBox;
	private String nomList;
	private List<Cancion> auxList;
	private JComboBox<String> textField;


	
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
	/**
	 * Create the application.
	 */
	public PanelNuevaLista(VentanaPrincipal v) {
		ventana = v;
		initialize();
	}

	
	private void initialize() {
		
		menuNorte = new PanelMenuNorte(ventana);
		menuNorte.ocultarBtnRegistrarse();
		menuNorte.ocultarBtnLogin();
		menuNorte.setTextLabel(PanelReciente.nombre);
		menuOeste = new PanelMenuOeste(ventana);
		setSize(Constantes.x_size, Constantes.y_size);
		setLayout(new BorderLayout(0, 0));

		add(menuNorte, BorderLayout.NORTH);
		add(menuOeste, BorderLayout.WEST);
		
		panelCentral = new JPanel();
		add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		panelCentral.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		panel_3 = new JPanel();
		panel.add(panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{40, 0, 50, 0, 20, 0, 40, 0};
		gbl_panel_3.rowHeights = new int[]{0, 5, 0};
		gbl_panel_3.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		
		btnVer = new JButton("Ver");
		btnVer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (textField.getSelectedItem() == null || textField.getSelectedItem().toString().equals("PlayList") || textField.getSelectedItem().toString().equals("")){
					JOptionPane.showMessageDialog(
							ventana,
							"Introduce un playList",
							"PlayList",
							JOptionPane.INFORMATION_MESSAGE);
				}
				else if (!ControladorAppMusic.getUnicaInstancia().existePlayList(textField.getSelectedItem().toString())){
					int res =JOptionPane.showConfirmDialog(ventana, 
							 "¿Deseas crear una nueva lista?", 
							  "Nueva Lista", JOptionPane.YES_NO_OPTION);
					if (res == JOptionPane.YES_OPTION){
						ControladorAppMusic.getUnicaInstancia().añadirPlayList(textField.getSelectedItem().toString());
						auxList = new ArrayList<Cancion>();
						table_1.setModel(new MiTableModel(auxList));
					}
				}
				else {
					auxList = new ArrayList<Cancion>(ControladorAppMusic.getUnicaInstancia().getPlayListUsuario(textField.getSelectedItem().toString()));
					table_1.setModel(new MiTableModel(auxList));
				}
				if (textField.getSelectedItem() != null) {
					nomList = textField.getSelectedItem().toString();
				}
		}
		});
		
		textField = new JComboBox<String>();
		textField.setEditable(true);
		for(String pl : ControladorAppMusic.getUnicaInstancia().getPlayListsUsuarioString()) {
			textField.addItem(pl);
		}
		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_1.gridx = 1;
		gbc_comboBox_1.gridy = 0;
		panel_3.add(textField, gbc_comboBox_1);
	
		GridBagConstraints gbc_btnVer = new GridBagConstraints();
		gbc_btnVer.insets = new Insets(0, 0, 5, 5);
		gbc_btnVer.gridx = 3;
		gbc_btnVer.gridy = 0;
		panel_3.add(btnVer, gbc_btnVer);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getSelectedItem() != null) {
					if (ControladorAppMusic.getUnicaInstancia().existePlayList(textField.getSelectedItem().toString())){
						int res =JOptionPane.showConfirmDialog(ventana, 
								"¿Deseas eliminar la playList "+textField.getSelectedItem().toString()+" ?" , 
								"Eliminar Lista", JOptionPane.YES_NO_OPTION);
						if (res == JOptionPane.YES_OPTION){
							ControladorAppMusic.getUnicaInstancia().eliminarPlayList(textField.getSelectedItem().toString());
							menuOeste.actualizarLista();
							auxList = new ArrayList<Cancion>();
							table_1.setModel(new MiTableModel(auxList));
						}
					}
					else{
						JOptionPane.showMessageDialog(ventana,"La playList "+textField.getSelectedItem().toString()+" no existe","Eliminar lista",JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		GridBagConstraints gbc_btnEliminar = new GridBagConstraints();
		gbc_btnEliminar.insets = new Insets(0, 0, 5, 5);
		gbc_btnEliminar.gridx = 5;
		gbc_btnEliminar.gridy = 0;
		panel_3.add(btnEliminar, gbc_btnEliminar);
		
		panel_4 = new JPanel();
		panel.add(panel_4);
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[]{20, 150, 20, 150, 20, 150, 20, 0, 20, 0};
		gbl_panel_4.rowHeights = new int[]{0, 5, 0};
		gbl_panel_4.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_4.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_4.setLayout(gbl_panel_4);
		
		textTitulo = new JTextField();
		textTitulo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textTitulo.setText("");
			}
		});
		GridBagConstraints gbc_textTitulo = new GridBagConstraints();
		gbc_textTitulo.insets = new Insets(0, 0, 5, 5);
		gbc_textTitulo.fill = GridBagConstraints.HORIZONTAL;
		gbc_textTitulo.gridx = 1;
		gbc_textTitulo.gridy = 0;
		textTitulo.setText("Titulo");
		panel_4.add(textTitulo, gbc_textTitulo);
		textTitulo.setColumns(10);
		
		textInterprete = new JTextField();
		textInterprete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textInterprete.setText("");
			}
		});
		GridBagConstraints gbc_textInterprete = new GridBagConstraints();
		gbc_textInterprete.insets = new Insets(0, 0, 5, 5);
		gbc_textInterprete.fill = GridBagConstraints.HORIZONTAL;
		gbc_textInterprete.gridx = 3;
		gbc_textInterprete.gridy = 0;
		textInterprete.setText("Interprete");
		panel_4.add(textInterprete, gbc_textInterprete);
		textInterprete.setColumns(10);
		
		comboBox = new JComboBox<String>();
		comboBox.setEditable(true);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 5;
		gbc_comboBox.gridy = 0;
		panel_4.add(comboBox, gbc_comboBox);
		
		List<Estilo> estilos = ControladorAppMusic.getUnicaInstancia().getEstilosMusicales();
		for (Estilo estilo : estilos) {
			comboBox.addItem(estilo.getNombre());
		}
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox.getSelectedItem() != null) {
					table.setModel(new MiTableModel(ControladorAppMusic.getUnicaInstancia()
							.buscarCanciones(comboBox.getSelectedItem().toString(),
							textInterprete.getText(), textTitulo.getText())));
				}
			}
		});
		GridBagConstraints gbc_btnBuscar = new GridBagConstraints();
		gbc_btnBuscar.insets = new Insets(0, 0, 5, 5);
		gbc_btnBuscar.gridx = 7;
		gbc_btnBuscar.gridy = 0;
		panel_4.add(btnBuscar, gbc_btnBuscar);
		
		panel_1 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
		flowLayout_1.setHgap(20);
		flowLayout_1.setVgap(10);
		panelCentral.add(panel_1, BorderLayout.SOUTH);
		
		btnNewButton = new JButton("Aceptar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(nomList != null) {
					if(ControladorAppMusic.getUnicaInstancia().existePlayList(nomList)) {
						ControladorAppMusic.getUnicaInstancia().eliminarPlayList(nomList);
					}
					ControladorAppMusic.getUnicaInstancia().añadirPlayList(nomList, auxList);
					menuOeste.actualizarLista();
					textField.removeAllItems();
					for(String pl : ControladorAppMusic.getUnicaInstancia().getPlayListsUsuarioString()) {
						textField.addItem(pl);
					}
					table_1.setModel(new MiTableModel(new ArrayList<Cancion>()));
				} else {
					auxList = new ArrayList<Cancion>();
					table_1.setModel(new MiTableModel(auxList));
				}
			}
		});
		panel_1.add(btnNewButton);
		
		btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				auxList = new ArrayList<Cancion>();
				table_1.setModel(new MiTableModel(auxList));	
			}
		});
		panel_1.add(btnNewButton_1);
		
		panel_5 = new JPanel();
		panelCentral.add(panel_5, BorderLayout.CENTER);
		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.X_AXIS));
		
		panel_6 = new JPanel();
		panel_5.add(panel_6);
		GridBagLayout gbl_panel_6 = new GridBagLayout();
		gbl_panel_6.columnWidths = new int[]{20, 0, 20, 0};
		gbl_panel_6.rowHeights = new int[]{0, 0};
		gbl_panel_6.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_6.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_6.setLayout(gbl_panel_6);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 0;
		panel_6.add(scrollPane, gbc_scrollPane);
		
		table = new JTable(new MiTableModel(ControladorAppMusic.getUnicaInstancia().getCanciones()));
		table.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		table.setPreferredScrollableViewportSize(new Dimension(250, 150));
		scrollPane.setViewportView(table);
		
		panel_7 = new JPanel();
		panel_7.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Lista", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_5.add(panel_7);
		GridBagLayout gbl_panel_7 = new GridBagLayout();
		gbl_panel_7.columnWidths = new int[]{20, 0, 20, 0};
		gbl_panel_7.rowHeights = new int[]{0, 0, 0};
		gbl_panel_7.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_7.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		panel_7.setLayout(gbl_panel_7);
		
		scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 1;
		gbc_scrollPane_1.gridy = 1;
		panel_7.add(scrollPane_1, gbc_scrollPane_1);
		
		table_1 = new JTable(new MiTableModel(new LinkedList<Cancion>()));
		table_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		table_1.setPreferredScrollableViewportSize(new Dimension(250, 150));
		scrollPane_1.setViewportView(table_1);
		
		
		panel_2 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setHgap(10);
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 0;
		panel_7.add(panel_2, gbc_panel_2);
		
		btnAadir = new JButton("Añadir");
		btnAadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  int row = table.getSelectedRow();
				  if(row >= 0) {
					  MiTableModel modelo = (MiTableModel)table.getModel();
					  Cancion cancion = modelo.getCancionAt(row);
					  if (!auxList.contains(cancion)){
						  auxList.add(cancion);
						  table_1.setModel(new MiTableModel(auxList)); 
					  }
				  }
			}
		});
		panel_2.add(btnAadir);
		
		btnQuitar = new JButton("Quitar");
		btnQuitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table_1.getSelectedRow();
				if(row >= 0) {
					MiTableModel modelo = (MiTableModel) table_1.getModel();
					Cancion cancion = modelo.getCancionAt(row);
					auxList.remove(cancion);
					table_1.setModel(new MiTableModel(auxList));
				}
			}
		});
		panel_2.add(btnQuitar);
		
		auxList = new LinkedList<Cancion>();
		table_1 = new JTable(new MiTableModel(auxList));
		table_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		table_1.setPreferredScrollableViewportSize(new Dimension(250, 150));
		scrollPane_1.setViewportView(table_1);
	}
}
