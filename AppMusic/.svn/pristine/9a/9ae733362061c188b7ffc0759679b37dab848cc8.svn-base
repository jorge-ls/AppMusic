package vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import controlador.ControladorAppMusic;
import modelo.Cancion;

import javax.swing.border.BevelBorder;
import javax.swing.ImageIcon;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.event.ActionEvent;


@SuppressWarnings("serial")
public class PanelReciente extends JPanel {
	
	private JTable table;
	private JPanel panelCentral;
	
	private PanelMenuNorte menuNorte;
	private PanelMenuOeste menuOeste;
	private VentanaPrincipal ventana;
	public static String nombre;
	
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
	 * Initialize the contents of the frame.
	 */
	public PanelReciente(VentanaPrincipal v,String nombre) {
		ventana = v;
		setSize(Constantes.x_size, Constantes.y_size);
		setLayout(new BorderLayout(0, 0));
		PanelReciente.nombre = nombre;
		menuNorte = new PanelMenuNorte(ventana);
		menuNorte.ocultarBtnRegistrarse();
		menuNorte.ocultarBtnLogin();
		menuNorte.setTextLabel(nombre);
		menuOeste = new PanelMenuOeste(ventana);
		add(menuNorte, BorderLayout.NORTH);
		add(menuOeste, BorderLayout.WEST);
		panelCentral = new JPanel();
		add(panelCentral, BorderLayout.CENTER);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{50, 0, 50, 0};
		gbl_panel_2.rowHeights = new int[]{20, 0, 10, 0, 0, 0, 15, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelCentral.setLayout(gbl_panel_2);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 1;
		panelCentral.add(scrollPane, gbc_scrollPane);
		
		table = new JTable(new MiTableModel(ControladorAppMusic.getUnicaInstancia().getCancionesRecientes(10)));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2){
					MiTableModel m = (MiTableModel)table.getModel();
					if(table.getSelectedRow() >= 0) {
						Cancion cancion = m.getCancionAt(table.getSelectedRow());
						ControladorAppMusic.getUnicaInstancia().setCancionActual(cancion);
						ControladorAppMusic.getUnicaInstancia().reproducirCancion();
					}
				}
			}
		});
		table.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		table.setPreferredScrollableViewportSize(new Dimension(250, 150));
		scrollPane.setViewportView(table);
		
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 3;
		panelCentral.add(panel, gbc_panel);
		
		JButton buttonPlay = new JButton("");
		panel.add(buttonPlay);
		buttonPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MiTableModel m = (MiTableModel)table.getModel();
				if(table.getSelectedRow() >= 0) {
					Cancion cancion = m.getCancionAt(table.getSelectedRow());
					ControladorAppMusic.getUnicaInstancia().setCancionActual(cancion);
					ControladorAppMusic.getUnicaInstancia().reproducirCancion();
				}
			}
		});
		buttonPlay.setIcon(new ImageIcon(PanelReciente.class.getResource("/vista/play-button.png")));
		
		JPanel panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.insets = new Insets(0, 0, 5, 5);
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 1;
		gbc_panel_3.gridy = 5;
		panelCentral.add(panel_3, gbc_panel_3);
		
		JButton buttonPause = new JButton("");
		buttonPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControladorAppMusic.getUnicaInstancia().pausarCancion();
			}
		});
		buttonPause.setIcon(new ImageIcon(PanelReciente.class.getResource("/vista/botton_pause.png")));
		panel_3.add(buttonPause);
		
		JButton buttonStop = new JButton("");
		buttonStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControladorAppMusic.getUnicaInstancia().detenerCancion();
			}
		});
		buttonStop.setIcon(new ImageIcon(PanelReciente.class.getResource("/vista/boton1.png")));
		panel_3.add(buttonStop);
		
		JButton buttonRetroceder = new JButton("");
		buttonRetroceder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MiTableModel m = (MiTableModel)table.getModel();
				if (table.getSelectedRow() > 0){
					Cancion cancion = m.getCancionAt(table.getSelectedRow()-1);
					table.changeSelection(table.getSelectedRow()-1,0,false,false);
					ControladorAppMusic.getUnicaInstancia().setCancionActual(cancion);
					ControladorAppMusic.getUnicaInstancia().reproducirCancion();
				}
			}
		
		});
		buttonRetroceder.setIcon(new ImageIcon(PanelReciente.class.getResource("/vista/if_back_rewind_63426.png")));
		panel_3.add(buttonRetroceder);
		
		JButton buttonAvanzar = new JButton("");
		buttonAvanzar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MiTableModel m = (MiTableModel)table.getModel();
				if (table.getSelectedRow() != m.getRowCount()-1){
					Cancion cancion = m.getCancionAt(table.getSelectedRow()+1);
					table.changeSelection(table.getSelectedRow()+1,0,false,false);
					ControladorAppMusic.getUnicaInstancia().setCancionActual(cancion);
					ControladorAppMusic.getUnicaInstancia().reproducirCancion();
				}
				else{
					Cancion cancion = m.getCancionAt(0);
					table.changeSelection(0,0,false,false);
					ControladorAppMusic.getUnicaInstancia().setCancionActual(cancion);
					ControladorAppMusic.getUnicaInstancia().reproducirCancion();
					
				}
			}
		});
		buttonAvanzar.setIcon(new ImageIcon(PanelReciente.class.getResource("/vista/if_forward_arrows_arrow_front_go_1868949.png")));
		panel_3.add(buttonAvanzar);
	}	
}
