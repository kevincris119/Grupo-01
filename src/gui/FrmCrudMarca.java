package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.Cursor;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;

import entidad.Marca;
import model.MarcaModel;
import util.Validaciones;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class FrmCrudMarca extends JFrame implements ActionListener, MouseListener  {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtEstado;
	private JTable tblMarca;
	private JLabel lblNombre;
	private JLabel lblEstado;
	private JLabel lblMantenimientoMarca;
	private JScrollPane scrollPane;
	private JButton btnActualizar;
	private JButton btnEliminar;
	private JButton btnRegistrar;
	int idSeleccionado = -1;
	private JButton btnCancelar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmCrudMarca frame = new FrmCrudMarca();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmCrudMarca() {
		setResizable(false);
		cerrar();
		setTitle("Mantenimiento Marca\r\n");
		setBounds(100, 100, 855, 399);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(31, 76, 45, 13);
		contentPane.add(lblNombre);
		
		lblEstado = new JLabel("Estado");
		lblEstado.setBounds(31, 109, 45, 13);
		contentPane.add(lblEstado);
		
		lblMantenimientoMarca = new JLabel("Mantenimiento Marca");
		lblMantenimientoMarca.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblMantenimientoMarca.setHorizontalAlignment(SwingConstants.CENTER);
		lblMantenimientoMarca.setBounds(10, 19, 816, 25);
		contentPane.add(lblMantenimientoMarca);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(109, 73, 178, 19);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtEstado = new JTextField();
		txtEstado.setBounds(109, 106, 178, 19);
		contentPane.add(txtEstado);
		txtEstado.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		scrollPane.setBounds(333, 65, 493, 284);
		contentPane.add(scrollPane);
		
		tblMarca = new JTable();
		tblMarca.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tblMarca.setSelectionForeground(Color.WHITE);
		tblMarca.setSurrendersFocusOnKeystroke(true);
		tblMarca.setFillsViewportHeight(true);
		tblMarca.setModel(new DefaultTableModel(new Object[][] {},new String[] {"ID", "Nombre", "Estado"}));
		tblMarca.addMouseListener(this);
		
		scrollPane.setViewportView(tblMarca);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.setEnabled(false);
		btnActualizar.setIcon(new ImageIcon(FrmCrudMarca.class.getResource("/iconos/edit.gif")));
		btnActualizar.addActionListener(this);
		btnActualizar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnActualizar.setBounds(151, 167, 111, 33);
		contentPane.add(btnActualizar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setEnabled(false);
		btnEliminar.setIcon(new ImageIcon(FrmCrudMarca.class.getResource("/iconos/delete.gif")));
		btnEliminar.addActionListener(this);
		btnEliminar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEliminar.setBounds(20, 232, 111, 33);
		contentPane.add(btnEliminar);
		
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.setIcon(new ImageIcon(FrmCrudMarca.class.getResource("/iconos/add.gif")));
		btnRegistrar.addActionListener(this);
		btnRegistrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRegistrar.setBounds(20, 167, 111, 33);
		contentPane.add(btnRegistrar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(FrmCrudMarca.class.getResource("/iconos/cancelP.png")));
		btnCancelar.setEnabled(false);
		btnCancelar.addActionListener(this);
		btnCancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCancelar.setBounds(151, 232, 111, 33);
		contentPane.add(btnCancelar);
		
		listaMarca();
		seleccionarCursor();
		
	}
	public void cerrar() {
		try {
			this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					confirmarSalida();
				}
			});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void confirmarSalida() {
		int valor =  JOptionPane.showConfirmDialog(this, "¿Esta seguro que desea salir?", "Advertencia", JOptionPane.YES_NO_OPTION);
		if(valor == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnRegistrar) {
			actionPerformedbtnRegistrar(arg0);
		}
		if (arg0.getSource() == btnActualizar) {
			actionPerformedbtnActualizar(arg0);
		}
		if (arg0.getSource() == btnEliminar) {
			actionPerformedbtnEliminar(arg0);
		}
		if (arg0.getSource() == btnCancelar) {
			actionPerformedbtnCancelar(arg0);
		}
	}
	protected void actionPerformedbtnCancelar(ActionEvent arg0) {
		botones(false);
	}
	protected void actionPerformedbtnRegistrar(ActionEvent arg0) {
		String nombre = txtNombre.getText().trim();
		String estado = txtEstado.getText().trim();
		
		if (nombre.matches(Validaciones.TEXTO) == false) {
			mensaje("Nombre es de 4 a 40 caracteres");
		} else if (estado.matches(Validaciones.TEXTO) == false) {
			mensaje("Estado es de 4 a 40 caracteres");
		} else {
			Marca obj = new Marca();
			obj.setNombre(nombre);
			obj.setEstado((estado));
			MarcaModel model = new MarcaModel();
			int salida = model.insertaMarca(obj);
			if (salida > 0) {
				mensaje("Se envió correctamente");
				listaMarca();
				seleccionarCursor();
				limpiarCajasTexto();
			} else {
				mensaje("Error en el registro");
			}
		}
		
	}
	protected void actionPerformedbtnActualizar(ActionEvent arg0) {
		if (idSeleccionado == -1) {
			mensaje("Se debe seleccionar una fila");
		} else {
			String nombre = txtNombre.getText().trim();
			String estado = txtEstado.getText().trim();

			if (nombre.matches(Validaciones.TEXTO) == false) {
				mensaje("Nombre es de 2 a 20 caracteres");
			} else if (estado.matches(Validaciones.TEXTO) == false) {
				mensaje("Pais es de 2 a 20 caracteres");
			} else {
				int valor = JOptionPane.showConfirmDialog(this, "¿Esta seguro de que desea modificar el registro?", "Advertencia", JOptionPane.YES_NO_OPTION);
				if(valor==JOptionPane.YES_OPTION) {
					Marca obj = new Marca();
					obj.setIdMarca(idSeleccionado);
					obj.setNombre(nombre);
					obj.setEstado(estado);

					MarcaModel model = new MarcaModel();
					int salida = model.actualizaMarca(obj);

					if (salida > 0) {
						mensaje("Se actualizo correctamente");
						listaMarca();
						
						botones(false);
						limpiarCajasTexto();
						idSeleccionado = -1;
					} else {
						mensaje("Error en la actulización");
					}
				}
			}
		}


	}
	protected void actionPerformedbtnEliminar(ActionEvent arg0) {
		if (idSeleccionado == -1) {
			mensaje("Seleccione una fila");
		} else {
			int valor = JOptionPane.showConfirmDialog(this, "¿Esta seguro de que desea eliminar el registro?", "Advertencia", JOptionPane.YES_NO_OPTION);
			if(valor==JOptionPane.YES_OPTION) {
				MarcaModel m = new MarcaModel();
				int s=m.eliminaMarca(idSeleccionado);
				if (s > 0) {
					mensaje("Se elimino correctamente");
					listaMarca();
					limpiarCajasTexto();
					idSeleccionado = -1;
					botones(false);
					seleccionarCursor();
				} else {
					mensaje("Error en la eliminación");
				}
			
			}
		}

	}
	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getSource() == tblMarca) {
			mouseClickedtblMarca(arg0);
		}
	}

	public void mouseEntered(MouseEvent arg0) {
	}

	public void mouseExited(MouseEvent arg0) {
	}

	public void mousePressed(MouseEvent arg0) {
	}

	public void mouseReleased(MouseEvent arg0) {
	}
	protected void mouseClickedtblMarca(MouseEvent arg0) {
		try {
			int fila = tblMarca.getSelectedRow();
			
			idSeleccionado = Integer.parseInt(tblMarca.getValueAt(fila, 0).toString());
		
			String nombre = tblMarca.getValueAt(fila, 1).toString();
			String estado = tblMarca.getValueAt(fila, 2).toString();
			
			txtNombre.setText(nombre);
			txtEstado.setText(estado);
			
			
			btnRegistrar.setEnabled(false);
			btnActualizar.setEnabled(true);
			btnEliminar.setEnabled(true);
			btnCancelar.setEnabled(true);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	void listaMarca() {
		MarcaModel m = new MarcaModel();
		List<Marca> data = m.listaMarca();
		DefaultTableModel dtm = (DefaultTableModel) tblMarca.getModel();
		dtm.setRowCount(0);
		for (Marca aux : data) {
			Object[] fila = { aux.getIdMarca(), aux.getNombre(), aux.getEstado() };
			dtm.addRow(fila);
		}
	}
	void seleccionarCursor() {
		int x = tblMarca.getRowCount();
		if(x==0) {
			tblMarca.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}else {
			tblMarca.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}
		
	}
	void mensaje(String m) {
		JOptionPane.showMessageDialog(this, m);
	}
	void botones(boolean x) {
		btnRegistrar.setEnabled(true);
		btnEliminar.setEnabled(x);
		btnActualizar.setEnabled(x);
	}

	void limpiarCajasTexto() {
		txtNombre.setText("");
		txtEstado.setText("");
		txtNombre.requestFocus();
	}
}
