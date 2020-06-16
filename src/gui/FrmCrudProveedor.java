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

import entidad.Proveedor;
import model.ProveedorModel;
import util.Validaciones;
import java.awt.SystemColor;

@SuppressWarnings("serial")
public class FrmCrudProveedor extends JFrame implements ActionListener, MouseListener {
	

	private JPanel contentPane;
	private JTextField txtRazSoc;
	private JTable tblProveedor;
	private JButton btnRegistrar;
	private JButton btnEliminar;
	private JButton btnActualizar;

	// Es el id de la fila seleccionado
	int idSeleccionado = -1;
	private JTextField txtRuc;
	private JTextField txtDirec;
	private JTextField txtTelef;
	private JTextField txtCel;
	private JTextField txtContac;
	private JTextField txtEstado;

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
					FrmCrudProveedor frame = new FrmCrudProveedor();
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
	public FrmCrudProveedor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 648, 503);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblMantenimientoCampeonato = new JLabel("MANTENIMIENTO PROVEEDOR");
		lblMantenimientoCampeonato.setOpaque(true);
		lblMantenimientoCampeonato.setBackground(SystemColor.activeCaption);
		lblMantenimientoCampeonato.setHorizontalAlignment(SwingConstants.CENTER);
		lblMantenimientoCampeonato.setForeground(Color.WHITE);
		lblMantenimientoCampeonato.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblMantenimientoCampeonato.setBounds(10, 11, 613, 59);
		contentPane.add(lblMantenimientoCampeonato);

		JLabel lblNombre = new JLabel("Razon Social");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNombre.setBounds(10, 81, 84, 26);
		contentPane.add(lblNombre);

		txtRazSoc = new JTextField();
		txtRazSoc.setBounds(101, 84, 199, 20);
		contentPane.add(txtRazSoc);
		txtRazSoc.setColumns(10);

		btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(this);
		btnRegistrar.setIcon(new ImageIcon(FrmCrudProveedor.class.getResource("/iconos/add.gif")));
		btnRegistrar.setBounds(90, 225, 114, 33);
		contentPane.add(btnRegistrar);

		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(this);
		btnActualizar.setIcon(new ImageIcon(FrmCrudProveedor.class.getResource("/iconos/edit.gif")));
		btnActualizar.setBounds(390, 225, 114, 33);
		contentPane.add(btnActualizar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this);
		btnEliminar.setIcon(new ImageIcon(FrmCrudProveedor.class.getResource("/iconos/delete.gif")));
		btnEliminar.setBounds(238, 225, 114, 33);
		contentPane.add(btnEliminar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 269, 613, 184);
		contentPane.add(scrollPane);

		tblProveedor = new JTable();
		tblProveedor.addMouseListener(this);
		tblProveedor.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Razon Social", "Ruc", "Direccion", "Telefono", "Celular", "Contacto", "Estado"
			}
		));
		scrollPane.setViewportView(tblProveedor);
		
		JLabel lblRuc = new JLabel("Ruc");
		lblRuc.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblRuc.setBounds(10, 118, 84, 26);
		contentPane.add(lblRuc);
		
		txtRuc = new JTextField();
		txtRuc.setColumns(10);
		txtRuc.setBounds(101, 121, 199, 20);
		contentPane.add(txtRuc);
		
		JLabel lblDireccion = new JLabel("Direccion");
		lblDireccion.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDireccion.setBounds(10, 155, 84, 26);
		contentPane.add(lblDireccion);
		
		txtDirec = new JTextField();
		txtDirec.setColumns(10);
		txtDirec.setBounds(101, 158, 199, 20);
		contentPane.add(txtDirec);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTelefono.setBounds(10, 188, 84, 26);
		contentPane.add(lblTelefono);
		
		txtTelef = new JTextField();
		txtTelef.setColumns(10);
		txtTelef.setBounds(101, 191, 199, 20);
		contentPane.add(txtTelef);
		
		JLabel lblCelular = new JLabel("Celular");
		lblCelular.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCelular.setBounds(321, 81, 84, 26);
		contentPane.add(lblCelular);
		
		txtCel = new JTextField();
		txtCel.setColumns(10);
		txtCel.setBounds(400, 84, 211, 20);
		contentPane.add(txtCel);
		
		JLabel lblContacto = new JLabel("Contacto");
		lblContacto.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblContacto.setBounds(321, 118, 84, 26);
		contentPane.add(lblContacto);
		
		txtContac = new JTextField();
		txtContac.setColumns(10);
		txtContac.setBounds(400, 121, 211, 20);
		contentPane.add(txtContac);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEstado.setBounds(321, 155, 84, 26);
		contentPane.add(lblEstado);
		
		txtEstado = new JTextField();
		txtEstado.setColumns(10);
		txtEstado.setBounds(400, 158, 211, 20);
		contentPane.add(txtEstado);

		// Traer todos los campeonatos de la BD
		listaProveedor();
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
		String razSoc = txtRazSoc.getText().trim();
		String ruc=txtRuc.getText().trim();
		
		
		String dir = txtDirec.getText().trim();
		String tel = txtTelef.getText().trim();
	

		if (razSoc.matches(Validaciones.TEXTO) == false) {
			mensaje("La Razon social es de 2 a 20 caracteres");
		} else if (ruc.matches(Validaciones.RUC) == false) {
			mensaje("El Ruc es de 11 dígitos");
		} else {
			Proveedor obj = new Proveedor();
			obj.setRazonSocial(razSoc);
			obj.setRuc(ruc);

			ProveedorModel model = new ProveedorModel();
			int salida = model.insertaProveedor(obj);

			if (salida > 0) {
				mensaje("Se envió correctamente");
			} else {
				mensaje("Error en el registro");
			}
		}
		listaProveedor();
		limpiarCajasTexto();
	}


	protected void do_btnEliminar_actionPerformed(ActionEvent arg0) {
		if (idSeleccionado == -1) {
			mensaje("Seleccione una fila");
		} else {
			ProveedorModel m= new ProveedorModel();
			int s = m.eliminaProveedor(idSeleccionado);
			if (s > 0) {
				mensaje("Se eliminó correctamente");
				listaProveedor();
				limpiarCajasTexto();
				idSeleccionado = -1;
			} else {
				mensaje("Error en la eliminación");
			}
		}
	}

	protected void do_btnActualizar_actionPerformed(ActionEvent arg0) {
		if (idSeleccionado == -1) {
			mensaje("Se debe seleccionar una fila");
		} else {
			String razSoc = txtRazSoc.getText().trim();
			String ruc=txtRuc.getText().trim();

			if (razSoc.matches(Validaciones.TEXTO) == false) {
				mensaje("La Razon social es de 2 a 20 caracteres");
			} else if (ruc.matches(Validaciones.RUC) == false) {
				mensaje("El Ruc es de 11 dígitos");
			} else {
				Proveedor obj = new Proveedor();
				obj.setRazonSocial(razSoc);
				obj.setRuc(ruc);

				ProveedorModel model = new ProveedorModel();
				int salida = model.insertaProveedor(obj);

				if (salida > 0) {
					mensaje("Se envió correctamente");
					listaProveedor();
					limpiarCajasTexto();
					idSeleccionado = -1;
				} else {
					mensaje("Error en la actulización");
				}
			}
		}
	}

	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getSource() == tblProveedor) {
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
		int fila = tblProveedor.getSelectedRow();

		idSeleccionado = Integer.parseInt(tblProveedor.getValueAt(fila, 0).toString());
		String razSoc = tblProveedor.getValueAt(fila, 1).toString();
		String ruc = tblProveedor.getValueAt(fila, 2).toString();
		String dir = tblProveedor.getValueAt(fila, 3).toString();
		String tel = tblProveedor.getValueAt(fila, 4).toString();
		String cel = tblProveedor.getValueAt(fila, 5).toString();
		String cont = tblProveedor.getValueAt(fila, 6).toString();
		String est = tblProveedor.getValueAt(fila, 7).toString();

		txtRazSoc.setText(razSoc);
		txtRuc.setText(ruc);
		txtDirec.setText(dir);
		txtTelef.setText(tel);
		txtCel.setText(cel);
		txtContac.setText(cont);
		txtEstado.setText(est);
		
	}

	void listaProveedor() {
		ProveedorModel m = new ProveedorModel();
		List<Proveedor> data = m.listaProveedor();

		// Se accede a la jtable de la GUI
		DefaultTableModel dtm = (DefaultTableModel) tblProveedor.getModel();
		// Se coloca a sero las filas
		dtm.setRowCount(0);

		// Se agregan los campeonatos al jtable
		for (Proveedor aux : data) {
			Object[] fila = { aux.getIdproveedor(), aux.getRazonSocial(), aux.getRuc() };
			dtm.addRow(fila);
		}
	}

	void mensaje(String m) {
		JOptionPane.showMessageDialog(this, m);
	}

	void limpiarCajasTexto() {
		txtRazSoc.setText("");
		txtRuc.setText("");
		txtDirec.setText("");
		txtTelef.setText("");
		txtCel.setText("");
		txtContac.setText("");
		txtEstado.setText("");
		txtRazSoc.requestFocus();
	}
}
