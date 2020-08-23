package vista;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import controlador.ControladorAppMusic;
import modelo.PlayList;

import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;

@SuppressWarnings("serial")
public class PanelMenuOeste extends JPanel {
	private VentanaPrincipal ventana;
	
	private JButton btnExplorar;
	private JButton btnNuevaLista;
	private JButton btnReciente;
	private JButton btnMasEscuchadas;
	private JButton btnMisListas;
	private JList<Object> list;
	private JScrollPane scrollPane;
	
	public PanelMenuOeste(VentanaPrincipal v) {
		ventana = v;
		setSize(Constantes.x_size, Constantes.y_size);
		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		btnExplorar = new JButton("Explorar");
		btnExplorar.setPreferredSize(new Dimension(131, 26));
		btnExplorar.setMinimumSize(new Dimension(131, 26));
		btnExplorar.setMaximumSize(new Dimension(131, 26));
		btnExplorar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana.setToPanelExplorar();
			}
		});
		add(btnExplorar);
		
		btnNuevaLista = new JButton("Nueva Lista");
		btnNuevaLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana.setToPanelNuevaLista();
			}
		});
		btnNuevaLista.setPreferredSize(new Dimension(131, 26));
		btnNuevaLista.setMinimumSize(new Dimension(131, 26));
		btnNuevaLista.setMaximumSize(new Dimension(131, 26));
		add(btnNuevaLista);
		
		btnReciente = new JButton("Reciente");
		btnReciente.setPreferredSize(new Dimension(131, 26));
		btnReciente.setMinimumSize(new Dimension(131, 26));
		btnReciente.setMaximumSize(new Dimension(131, 26));
		btnReciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana.setToPanelReciente(PanelReciente.nombre);
				
			}
		});
		add(btnReciente);
		
		btnMasEscuchadas = new JButton("Mas Escuchadas");
		btnMasEscuchadas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (ControladorAppMusic.getUnicaInstancia().getUsuarioActual().isPremium()){
					ventana.setToPanelMasEscuchadas();
				}
				else{
					JOptionPane.showMessageDialog(
							ventana,
							"Para acceder a este servicio tiene que ser un usuario Premium",
							"Premium",
							JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
		btnMasEscuchadas.setPreferredSize(new Dimension(131, 26));
		btnMasEscuchadas.setMinimumSize(new Dimension(131, 26));
		btnMasEscuchadas.setMaximumSize(new Dimension(131, 26));
		add(btnMasEscuchadas);
		
		btnMisListas = new JButton("Mis Listas");
		btnMisListas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(scrollPane.isVisible())
					scrollPane.setVisible(false);
				else
					scrollPane.setVisible(true);
				revalidate();
			}
		});
		btnMisListas.setPreferredSize(new Dimension(131, 26));
		btnMisListas.setMinimumSize(new Dimension(131, 26));
		btnMisListas.setMaximumSize(new Dimension(131, 26));
		add(btnMisListas);
		
		scrollPane = new JScrollPane();
		scrollPane.setAlignmentX(0.0f);
		add(scrollPane);
		list = new JList<Object>(new MiListModel(ControladorAppMusic.getUnicaInstancia().getPlayListsUsuario()));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent ev) {
				if (ev.getClickCount() == 2){
					MiListModel m = (MiListModel) list.getModel();
					PlayList pl = m.getPlayListAt(list.getSelectedIndex());
					ventana.setToPanelMisListas(pl);
				}
			}
		});
		scrollPane.setViewportView(list);
		scrollPane.setVisible(false);
	}
	
	public void actualizarLista() {
		list.setModel(new MiListModel(ControladorAppMusic.getUnicaInstancia().getPlayListsUsuario()));
	}
	
	class MiListModel extends AbstractListModel<Object> {
		 private List<PlayList> data;
		 
		 public MiListModel(List<PlayList> data) {
			 this.data = data;
		 }

		@Override
		public Object getElementAt(int index) {
			return data.get(index).getNombre();
		}

		@Override
		public int getSize() {
			return data.size();
		}
		
		public PlayList getPlayListAt(int index) {
			return data.get(index);
		}
		 
	}
	
}
