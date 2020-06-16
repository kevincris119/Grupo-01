package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import entidad.TipoReclamo;
import entidad.Usuario;
import model.TipoReclamoModel;
import model.UsuarioModel;
import util.Validaciones;
import javax.swing.JPasswordField;

public class FrmCrudTipoReclamo extends JFrame implements ActionListener, MouseListener {

	private JPanel contentPane;
	private JTextField txtDescripcion;
	private JTextField txtEstado;
	private JTextField txtFecRegis;
	private JTable table;
	private JButton btnRegistrar;
	private JButton btnEliminar;
	private JButton btnActualizar;

	// Es el id de la fila seleccionado
	int idSeleccionado = -1;

	// ModelCampeonato-->Es la clase donde estan los
	// métodos insert, update, delete, listar en la BD

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
					FrmCrudTipoReclamo frame = new FrmCrudTipoReclamo();
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
	public FrmCrudTipoReclamo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 703, 533);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTipoReclamo = new JLabel("Mantenimiento Tipo de Reclamo");
		lblTipoReclamo.setOpaque(true);
		lblTipoReclamo.setBackground(Color.RED);
		lblTipoReclamo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipoReclamo.setForeground(Color.WHITE);
		lblTipoReclamo.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblTipoReclamo.setBounds(10, 11, 670, 61);
		contentPane.add(lblTipoReclamo);

		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(175, 95, 84, 26);
		contentPane.add(lblDescripcion);

		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setBounds(175, 132, 46, 26);
		contentPane.add(lblEstado);
		
		JLabel lblFechaRegistro = new JLabel("Fecha de Registro");
		lblFechaRegistro.setBounds(175, 169, 94, 26);
		contentPane.add(lblFechaRegistro);

		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(296, 98, 211, 20);
		contentPane.add(txtDescripcion);
		txtDescripcion.setColumns(10);

		txtEstado = new JTextField();
		txtEstado.setBounds(296, 135, 211, 20);
		contentPane.add(txtEstado);
		txtEstado.setColumns(10);
		
		txtFecRegis = new JTextField();
		txtFecRegis.setBounds(296, 172, 211, 20);
		contentPane.add(txtFecRegis);
		txtFecRegis.setColumns(10);

		btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(this);
		btnRegistrar.setIcon(new ImageIcon(FrmCrudTipoReclamo.class.getResource("/iconos/add.gif")));
		btnRegistrar.setBounds(285, 218, 114, 33);
		contentPane.add(btnRegistrar);

		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(this);
		btnActualizar.setIcon(new ImageIcon(FrmCrudTipoReclamo.class.getResource("/iconos/edit.gif")));
		btnActualizar.setBounds(30, 218, 114, 33);
		contentPane.add(btnActualizar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this);
		btnEliminar.setIcon(new ImageIcon(FrmCrudTipoReclamo.class.getResource("/iconos/delete.gif")));
		btnEliminar.setBounds(523, 218, 123, 33);
		contentPane.add(btnEliminar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 273, 667, 210);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(this);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Descripcion", "Estado", "Fecha de Registro"
			}
		));
		table.getColumnModel().getColumn(3).setPreferredWidth(97);
		scrollPane.setViewportView(table);

		// Traer todos los campeonatos de la BD
		listaTipoReclamo();
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnActualizar) {
			do_btnActualizar_actionPerformed(arg0);
		}
		if (arg0.getSource() == btnEliminar) {
			do_btnEliminar_actionPerformed(arg0);
		}
		if (arg0.getSource() == btnRegistrar) {
			do_btnRegistrar_actionPerformed(arg0);
		}
	}

	protected void do_btnRegistrar_actionPerformed(ActionEvent arg0) {
			// 1 Recibir los valores de la GUI
			String des = txtDescripcion.getText();
			String est=txtEstado.getText();
			String fec=txtFecRegis.getText();


			// 2 Validaciones
			if (des.matches(Validaciones.TEXTO) == false) {
				mensaje("El nombre es de 3 a 20 caracteres");
			}else if (est.matches(Validaciones.TEXTO) == false) {
				mensaje("El estado es de 3 a 20 caracteres");
			}else if (fec.matches(Validaciones.FECHA) == false) {
				mensaje("La fecha es YYYY-MM-dd");
			}else {
				// 3 Se crea el objeto tiporeclamo
				TipoReclamo obj = new TipoReclamo();
				obj.setDescripcion(des);
				obj.setEstado(est);
				obj.setFechaRegistro(fec);

				// 4 Se envía al modelo de datos
				TipoReclamoModel model = new TipoReclamoModel();
				int salida = model.insertaTipoReclamo(obj);

				if (salida > 0) {
					mensaje("Se envió correctamente");
					listaTipoReclamo();
					limpiarCajasTexto();
				} else {
					mensaje("Error en el registro");
				}
			}
	}
	protected void do_btnEliminar_actionPerformed(ActionEvent arg0) {
		if (idSeleccionado == -1) {
			mensaje("Se debe seccionar una fila");
		}else {
			TipoReclamoModel m= new TipoReclamoModel();
			int s = m.eliminaTipoReclamo(idSeleccionado);
			if (s > 0) {
				mensaje("Se eliminó correctamente");
				listaTipoReclamo();
				limpiarCajasTexto();
				idSeleccionado = -1;
			} else {
				mensaje("Error en la eliminación");
			}
		}
	}
	protected void do_btnActualizar_actionPerformed(ActionEvent arg0) {
			// 1 Recibir los valores de la GUI
			String des =txtDescripcion.getText();
			String est=txtEstado.getText();
			String fec=txtFecRegis.getText();

	
			// 2 Validaciones
			if (des.matches(Validaciones.TEXTO) == false) {
				mensaje("El nombre es de 3 a 20 caracteres");
			}else if (est.matches(Validaciones.TEXTO) == false) {
				mensaje("El estado es de 3 a 20 caracteres");
			}else if (fec.matches(Validaciones.FECHA) == false) {
				mensaje("La fecha es YYYY-MM-dd");
			}else {
				// 3 Se crea el objeto tiporeclamo
				TipoReclamo obj = new TipoReclamo();
				obj.setDescripcion(des);
				obj.setEstado(est);
				obj.setFechaRegistro(fec);

				// 4 Se envía al modelo de datos
				TipoReclamoModel model = new TipoReclamoModel();
				int salida = model.actualizaTipoReclamo(obj);

				if (salida > 0) {
					mensaje("Se actualizo correctamente");
					listaTipoReclamo();
					limpiarCajasTexto();
					idSeleccionado=-1;
				} else {
					mensaje("Error al actualizar");
				}
			}
	}
	
	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getSource() == table) {
			do_table_mouseClicked(arg0);
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

	protected void do_table_mouseClicked(MouseEvent arg0) {
		// Devuele la fila seleccionada
		int fila = table.getSelectedRow();

		idSeleccionado = Integer.parseInt(table.getValueAt(fila, 0).toString());
		String descripcion = table.getValueAt(fila, 1).toString();
		String estado = table.getValueAt(fila, 2).toString();
		String fecreg = table.getValueAt(fila, 3).toString();

		txtDescripcion.setText(descripcion);
		txtEstado.setText(estado);
		txtFecRegis.setText(fecreg);

	}

	void listaTipoReclamo() {
		TipoReclamoModel m = new TipoReclamoModel();
		List<TipoReclamo> data = m.listatipoTipoReclamos();

		// Se accede a la jtable de la GUI
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		// Se coloca a sero las filas
		dtm.setRowCount(0);

		// Se agregan los tipo de reclamo al jtable
		for (TipoReclamo aux : data) {
			Object[] fila = { aux.getIdTipoReclamo(),
					          aux.getDescripcion(),
					          aux.getEstado(),
					          aux.getFechaRegistro()};
			dtm.addRow(fila);
		}
	}

	void mensaje(String m) {
		JOptionPane.showMessageDialog(this, m);
	}

	void limpiarCajasTexto() {
		txtDescripcion.setText("");
		txtEstado.setText("");
		txtFecRegis.setText("");
		txtDescripcion.requestFocus();
	}
}
