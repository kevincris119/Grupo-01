package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
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
		
		setTitle("Mantenimiento Marca\r\n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 428, 565);
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
		lblMantenimientoMarca.setBackground(Color.WHITE);
		lblMantenimientoMarca.setForeground(Color.DARK_GRAY);
		lblMantenimientoMarca.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblMantenimientoMarca.setHorizontalAlignment(SwingConstants.CENTER);
		lblMantenimientoMarca.setBounds(10, 19, 393, 25);
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
		scrollPane.setBounds(10, 233, 393, 291);
		contentPane.add(scrollPane);
		
		tblMarca = new JTable();
		tblMarca.setFillsViewportHeight(true);
		tblMarca.setModel(new DefaultTableModel(new Object[][] {},new String[] {"ID", "Nombre", "Estado"}));
		tblMarca.addMouseListener(this);
		
		scrollPane.setViewportView(tblMarca);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.setIcon(new ImageIcon(FrmCrudMarca.class.getResource("/iconos/edit.gif")));
		btnActualizar.addActionListener(this);
		btnActualizar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnActualizar.setBounds(151, 167, 111, 33);
		contentPane.add(btnActualizar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setIcon(new ImageIcon(FrmCrudMarca.class.getResource("/iconos/delete.gif")));
		btnEliminar.addActionListener(this);
		btnEliminar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEliminar.setBounds(280, 167, 111, 33);
		contentPane.add(btnEliminar);
		
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.setIcon(new ImageIcon(FrmCrudMarca.class.getResource("/iconos/add.gif")));
		btnRegistrar.addActionListener(this);
		btnRegistrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRegistrar.setBounds(20, 167, 111, 33);
		contentPane.add(btnRegistrar);
		listaMarca();
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
				Marca obj = new Marca();
				obj.setIdMarca(idSeleccionado);
				obj.setNombre(nombre);
				obj.setEstado(estado);

				MarcaModel model = new MarcaModel();
				int salida = model.actualizaMarca(obj);

				if (salida > 0) {
					mensaje("Se actualizo correctamente");
					listaMarca();
					limpiarCajasTexto();
					idSeleccionado = -1;
				} else {
					mensaje("Error en la actulización");
				}
			}
		}


	}
	protected void actionPerformedbtnEliminar(ActionEvent arg0) {
		if (idSeleccionado == -1) {
			mensaje("Seleccione una fila");
		} else {
			MarcaModel m = new MarcaModel();
			int s=m.eliminaMarca(idSeleccionado);
			if (s > 0) {
				mensaje("Se elimino correctamente");
				listaMarca();
				limpiarCajasTexto();
				idSeleccionado = -1;
			} else {
				mensaje("Error en la eliminación");
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

	void mensaje(String m) {
		JOptionPane.showMessageDialog(this, m);
	}
	void limpiarCajasTexto() {
		txtNombre.setText("");
		txtEstado.setText("");
		txtNombre.requestFocus();
	}
}
