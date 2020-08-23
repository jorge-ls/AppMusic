package vista;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import java.awt.GridBagLayout;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.table.AbstractTableModel;

import controlador.ControladorAppMusic;
import modelo.Cancion;
import modelo.PlayList;

import java.awt.GridBagConstraints;
import javax.swing.JScrollPane;
import java.awt.Insets;
import java.util.List;

import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class PanelMisListas extends JPanel {
	private JTable table;
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
	
	public PanelMisListas(VentanaPrincipal v, PlayList pl) {
		
		ventana = v;
		menuNorte = new PanelMenuNorte(ventana);
		menuNorte.ocultarBtnRegistrarse();
		menuNorte.ocultarBtnLogin();
		menuNorte.setTextLabel(PanelReciente.nombre);
		menuOeste = new PanelMenuOeste(ventana);
		setSize(Constantes.x_size, Constantes.y_size);
		setLayout(new BorderLayout(0, 0));
		
		add(menuNorte, BorderLayout.NORTH);
		add(menuOeste, BorderLayout.WEST);
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{50, 0, 50, 0};
		gbl_panel.rowHeights = new int[]{20, 0, 10, 0, 0, 0, 15, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 1;
		panel.add(scrollPane, gbc_scrollPane);
		
		table = new JTable(new MiTableModel(pl.getCanciones()));
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
		table.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		table.setPreferredScrollableViewportSize(new Dimension(250, 150));
		scrollPane.setViewportView(table);
		
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 3;
		panel.add(panel_2, gbc_panel_2);
		
		JButton btnPlay = new JButton("");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
			
		});
		panel_2.add(btnPlay);
		btnPlay.setIcon(new ImageIcon(PanelMisListas.class.getResource("/vista/play-button.png")));
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 5;
		panel.add(panel_1, gbc_panel_1);
		
		JButton btnStop = new JButton("");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControladorAppMusic.getUnicaInstancia().pausarCancion();
			}
		});
		btnStop.setIcon(new ImageIcon(PanelMisListas.class.getResource("/vista/botton_pause.png")));
		panel_1.add(btnStop);
		
		JButton btnDetener = new JButton("");
		btnDetener.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControladorAppMusic.getUnicaInstancia().detenerCancion();
			}
		});
		btnDetener.setIcon(new ImageIcon(PanelMisListas.class.getResource("/vista/boton1.png")));
		panel_1.add(btnDetener);
		
		JButton buttonRetroceder = new JButton("");
		buttonRetroceder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		buttonRetroceder.setIcon(new ImageIcon(PanelMisListas.class.getResource("/vista/if_back_rewind_63426.png")));
		panel_1.add(buttonRetroceder);
		
		JButton buttonAvanzar = new JButton("");
		buttonAvanzar.addActionListener(new ActionListener() {
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
		buttonAvanzar.setIcon(new ImageIcon(PanelMisListas.class.getResource("/vista/if_forward_arrows_arrow_front_go_1868949.png")));
		panel_1.add(buttonAvanzar);
		
	}
}
