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
import javax.swing.border.BevelBorder;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class FrmCrudProveedor extends JFrame implements ActionListener, MouseListener, KeyListener {


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
	private JButton btnCancelar;
	private JLabel lblAlertCel;
	private JLabel lblAlertRuc;
	private JLabel lblAlertTelef;
	private JLabel lblAlertRazS;
	private JLabel lblAlertCont;


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
		setTitle("PROVEEDOR");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 826, 551);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblMantenimientoProveedor = new JLabel("MANTENIMIENTO PROVEEDOR");
		lblMantenimientoProveedor.setOpaque(true);
		lblMantenimientoProveedor.setBackground(SystemColor.controlDkShadow);
		lblMantenimientoProveedor.setHorizontalAlignment(SwingConstants.CENTER);
		lblMantenimientoProveedor.setForeground(Color.WHITE);
		lblMantenimientoProveedor.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblMantenimientoProveedor.setBounds(10, 11, 790, 59);
		contentPane.add(lblMantenimientoProveedor);

		btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(this);
		btnRegistrar.setIcon(new ImageIcon(FrmCrudProveedor.class.getResource("/iconos/addP.png")));
		btnRegistrar.setBounds(62, 245, 114, 33);
		contentPane.add(btnRegistrar);

		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(this);
		btnActualizar.setIcon(new ImageIcon(FrmCrudProveedor.class.getResource("/iconos/updateP.png")));
		btnActualizar.setBounds(520, 245, 114, 33);
		contentPane.add(btnActualizar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this);
		btnEliminar.setIcon(new ImageIcon(FrmCrudProveedor.class.getResource("/iconos/deleteP.png")));
		btnEliminar.setBounds(238, 245, 114, 33);
		contentPane.add(btnEliminar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 291, 794, 210);
		contentPane.add(scrollPane);

		tblProveedor = new JTable();
		tblProveedor.setSurrendersFocusOnKeystroke(true);
		tblProveedor.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tblProveedor.addMouseListener(this);
		tblProveedor.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"ID", "Razon Social", "Ruc", "Direccion", "Telefono", "Celular", "Contacto", "Estado"
				}
				) {
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblProveedor.getColumnModel().getColumn(0).setResizable(false);
		tblProveedor.getColumnModel().getColumn(0).setPreferredWidth(30);
		tblProveedor.getColumnModel().getColumn(1).setResizable(false);
		tblProveedor.getColumnModel().getColumn(1).setPreferredWidth(91);
		tblProveedor.getColumnModel().getColumn(2).setResizable(false);
		tblProveedor.getColumnModel().getColumn(2).setPreferredWidth(79);
		tblProveedor.getColumnModel().getColumn(3).setPreferredWidth(114);
		tblProveedor.getColumnModel().getColumn(4).setResizable(false);
		tblProveedor.getColumnModel().getColumn(5).setResizable(false);
		tblProveedor.getColumnModel().getColumn(7).setResizable(false);
		scrollPane.setViewportView(tblProveedor);


		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(FrmCrudProveedor.class.getResource("/iconos/cancelP.png")));
		btnCancelar.addActionListener(this);
		btnCancelar.setEnabled(false);
		btnCancelar.setBounds(667, 245, 114, 33);
		contentPane.add(btnCancelar);

		listaProveedor();
		btnActualizar.setEnabled(false);
		btnCancelar.setEnabled(false);
		btnEliminar.setEnabled(false);

		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(20, 81, 780, 153);
		contentPane.add(panel);
		panel.setLayout(null);

		txtTelef = new JTextField();
		txtTelef.addKeyListener(this);
		txtTelef.setBounds(132, 121, 199, 20);
		panel.add(txtTelef);
		txtTelef.setColumns(10);

		txtDirec = new JTextField();
		txtDirec.setBounds(132, 88, 199, 20);
		panel.add(txtDirec);
		txtDirec.setColumns(10);

		txtRuc = new JTextField();
		txtRuc.addKeyListener(this);
		txtRuc.setBounds(132, 51, 199, 20);
		panel.add(txtRuc);
		txtRuc.setColumns(10);

		txtRazSoc = new JTextField();
		txtRazSoc.addKeyListener(this);
		txtRazSoc.setBounds(132, 14, 199, 20);
		panel.add(txtRazSoc);
		txtRazSoc.setColumns(10);

		JLabel lblNombre = new JLabel("Razon Social");
		lblNombre.setBounds(41, 11, 84, 26);
		panel.add(lblNombre);
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 12));

		JLabel lblRuc = new JLabel("Ruc");
		lblRuc.setBounds(41, 48, 84, 26);
		panel.add(lblRuc);
		lblRuc.setFont(new Font("Tahoma", Font.BOLD, 12));

		JLabel lblDireccion = new JLabel("Direccion");
		lblDireccion.setBounds(41, 85, 84, 26);
		panel.add(lblDireccion);
		lblDireccion.setFont(new Font("Tahoma", Font.BOLD, 12));

		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(41, 118, 84, 26);
		panel.add(lblTelefono);
		lblTelefono.setFont(new Font("Tahoma", Font.BOLD, 12));

		JLabel lblCelular = new JLabel("Celular");
		lblCelular.setBounds(480, 8, 84, 26);
		panel.add(lblCelular);
		lblCelular.setFont(new Font("Tahoma", Font.BOLD, 12));

		txtCel = new JTextField();
		txtCel.addKeyListener(this);
		txtCel.setBounds(559, 11, 211, 20);
		panel.add(txtCel);
		txtCel.setColumns(10);

		txtContac = new JTextField();
		txtContac.addKeyListener(this);
		txtContac.setBounds(559, 48, 211, 20);
		panel.add(txtContac);
		txtContac.setColumns(10);

		JLabel lblContacto = new JLabel("Contacto");
		lblContacto.setBounds(480, 45, 84, 26);
		panel.add(lblContacto);
		lblContacto.setFont(new Font("Tahoma", Font.BOLD, 12));

		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setBounds(480, 82, 84, 26);
		panel.add(lblEstado);
		lblEstado.setFont(new Font("Tahoma", Font.BOLD, 12));

		txtEstado = new JTextField();
		txtEstado.setBounds(559, 85, 211, 20);
		panel.add(txtEstado);
		txtEstado.setColumns(10);

		lblAlertCel = new JLabel("");
		lblAlertCel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAlertCel.setForeground(Color.RED);
		lblAlertCel.setBounds(559, 31, 211, 14);
		panel.add(lblAlertCel);

		lblAlertRuc = new JLabel("");
		lblAlertRuc.setForeground(Color.RED);
		lblAlertRuc.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAlertRuc.setBounds(132, 69, 211, 14);
		panel.add(lblAlertRuc);

		lblAlertTelef = new JLabel("");
		lblAlertTelef.setForeground(Color.RED);
		lblAlertTelef.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAlertTelef.setBounds(132, 139, 211, 14);
		panel.add(lblAlertTelef);

		lblAlertRazS = new JLabel("");
		lblAlertRazS.setForeground(Color.RED);
		lblAlertRazS.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAlertRazS.setBounds(132, 31, 211, 14);
		panel.add(lblAlertRazS);
		
		lblAlertCont = new JLabel("");
		lblAlertCont.setForeground(Color.RED);
		lblAlertCont.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAlertCont.setBounds(559, 69, 211, 14);
		panel.add(lblAlertCont);
		setLocationRelativeTo(this);
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnCancelar) {
			actionPerformedBtnCancelarJButton(arg0);
		}
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
		String cel = txtCel.getText().trim();
		String cont = txtContac.getText().trim();
		String est = txtEstado.getText().trim();		
		String dir = txtDirec.getText().trim();
		String tel = txtTelef.getText().trim();

		if (razSoc.matches(Validaciones.TEXTO) == false) {
			mensaje("La Razon social es de 4 a 40 caracteres");
		}else if (ruc.matches(Validaciones.RUC) == false) {
			mensaje("El Ruc es de 11 dígitos");
		} else if (cel.matches(Validaciones.CELULAR) == false) {
			mensaje("El Celular es de 9 dígitos");
		} else if (cont.matches(Validaciones.TEXTO) == false) {
			mensaje("El Contacto es de 4 a 40 caracteres");
		} else if (est.matches(Validaciones.TEXTO) == false) {
			mensaje("El estado es de 4 a 40 caracteres");
		} else if (dir.matches(Validaciones.DIRECCION) == false) {
			mensaje("La direccion es de 4 a 40 caracteres");
		} else if (tel.matches(Validaciones.TELEFONO) == false) {
			mensaje("El Telefono tiene formato XXXXXXX");
		} else{
			Proveedor obj = new Proveedor();
			obj.setRazonSocial(razSoc);
			obj.setRuc(ruc);
			obj.setCelular(cel);
			obj.setContacto(cont);
			obj.setEstado(est);
			obj.setDireccion(dir);
			obj.setTelefono(tel);

			ProveedorModel model = new ProveedorModel();
			int salida = model.insertaProveedor(obj);

			if (salida > 0) {
				limpiarCajasTexto();
				listaProveedor();
				mensaje("Se Registro correctamente");

			} else {
				mensaje("Error en el registro");
			}
		}


	}


	protected void do_btnEliminar_actionPerformed(ActionEvent arg0) {
		if (idSeleccionado == -1) {
			mensaje("Seleccione una fila");
		} else {
			ProveedorModel m= new ProveedorModel();
			int s = m.eliminaProveedor(idSeleccionado);
			if (s > 0) {
				listaProveedor();
				limpiarCajasTexto();


				idSeleccionado = -1;
				btnEliminar.setEnabled(false);
				btnActualizar.setEnabled(false);
				btnRegistrar.setEnabled(true);
				btnCancelar.setEnabled(false);
				mensaje("Se eliminó correctamente");
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
			String cel = txtCel.getText().trim();
			String cont = txtContac.getText().trim();
			String est = txtEstado.getText().trim();		
			String dir = txtDirec.getText().trim();
			String tel = txtTelef.getText().trim();

			if (razSoc.matches(Validaciones.TEXTO) == false) {
				mensaje("La Razon social es de 4 a 40 caracteres");
			}else if (ruc.matches(Validaciones.RUC) == false) {
				mensaje("El Ruc es de 11 dígitos");
			} else if (cel.matches(Validaciones.CELULAR) == false) {
				mensaje("El Celular es de 9 dígitos");
			} else if (cont.matches(Validaciones.TEXTO) == false) {
				mensaje("El Contacto es de 4 a 40 caracteres");
			} else if (est.matches(Validaciones.TEXTO) == false) {
				mensaje("El estado es de 4 a 40 caracteres");
			} else if (dir.matches(Validaciones.DIRECCION) == false) {
				mensaje("La direccion es de 4 a 40 caracteres");
			} else if (tel.matches(Validaciones.TELEFONO) == false) {
				mensaje("El Telefono tiene formato XXXXXXX");
			} else{
				Proveedor obj = new Proveedor();
				obj.setIdproveedor(idSeleccionado);
				obj.setRazonSocial(razSoc);
				obj.setRazonSocial(razSoc);
				obj.setRuc(ruc);
				obj.setCelular(cel);
				obj.setContacto(cont);
				obj.setEstado(est);
				obj.setDireccion(dir);
				obj.setTelefono(tel);

				ProveedorModel model = new ProveedorModel();
				int salida = model.actualizaProveedor(obj);

				if (salida > 0) {
					listaProveedor();
					limpiarCajasTexto();
					idSeleccionado = -1;
					btnActualizar.setEnabled(false);
					btnRegistrar.setEnabled(true);
					btnCancelar.setEnabled(false);
					mensaje("Se actualizo correctamente");

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

		btnActualizar.setEnabled(true);
		btnRegistrar.setEnabled(false);
		btnCancelar.setEnabled(true);
		btnEliminar.setEnabled(true);



	}

	void listaProveedor() {
		ProveedorModel m = new ProveedorModel();
		List<Proveedor> data = m.listaProveedor();

		// Se accede a la jtable de la GUI
		DefaultTableModel dtm = (DefaultTableModel) tblProveedor.getModel();
		// Se coloca a sero las filas
		dtm.setRowCount(0);

		// Se agregan los datos al jtable
		for (Proveedor aux : data) {
			Object[] fila = { aux.getIdproveedor(), aux.getRazonSocial(), aux.getRuc(),aux.getDireccion(),aux.getTelefono(),aux.getCelular(),
					aux.getContacto(),aux.getEstado()};
			dtm.addRow(fila);			
		}
		if (dtm.getRowCount()==0) {
			m.reiniciarAutoincremente();
			m.reiniciarAutoincremente();
		}
	}

	void mensaje(String m) {
		JOptionPane.showMessageDialog(this, m);
	}

	void limpiarCajasTexto() {
		lblAlertCel.setText("");
		lblAlertRuc.setText("");
		lblAlertTelef.setText("");
		lblAlertRazS.setText("");

		txtRazSoc.setText("");
		txtRuc.setText("");
		txtDirec.setText("");
		txtTelef.setText("");
		txtCel.setText("");
		txtContac.setText("");
		txtEstado.setText("");
		txtRazSoc.requestFocus();
	}
	protected void actionPerformedBtnCancelarJButton(ActionEvent arg0) {
		btnActualizar.setEnabled(false);
		btnRegistrar.setEnabled(true);
		btnEliminar.setEnabled(false);
		btnCancelar.setEnabled(false);
		limpiarCajasTexto();

	}
	public void keyPressed(KeyEvent e) {
	}
	public void keyReleased(KeyEvent e) {
	}
	public void keyTyped(KeyEvent e) {
		if (e.getSource() == txtContac) {
			keyTypedTxtContacJTextField(e);
		}
		if (e.getSource() == txtRazSoc) {
			keyTypedTxtRazSocJTextField(e);
		}
		if (e.getSource() == txtTelef) {
			keyTypedTxtTelefJTextField(e);
		}
		if (e.getSource() == txtRuc) {
			keyTypedTxtRucJTextField(e);
		}
		if (e.getSource() == txtCel) {
			keyTypedTxtCelJTextField(e);
		}
	}
	//Validaciones con EVENTOS
	protected void keyTypedTxtCelJTextField(KeyEvent e) {
		if (txtCel.getText().length()<9) {
			if (!(e.getKeyChar()>47 && e.getKeyChar()<58)) {
				e.consume();
				lblAlertCel.setText("< Ingresar Solo Numeros >");
			}else {lblAlertCel.setText("");}
		}else {
			e.consume();
			lblAlertCel.setText("< 9 Digitos Como Maximo >");
		}

	}
	protected void keyTypedTxtRucJTextField(KeyEvent e) {
		if (txtRuc.getText().length()<11) {
			if (!(e.getKeyChar()>47 && e.getKeyChar()<58)) {
				e.consume();
				lblAlertRuc.setText("< Ingresar Solo Numeros >");
			}else {lblAlertRuc.setText("");}
		}else {
			e.consume();
			lblAlertRuc.setText("< 11 Digitos Como Maximo >");
		}
	}
	protected void keyTypedTxtTelefJTextField(KeyEvent e) {
		if (txtTelef.getText().length()<7) {
			if (!(e.getKeyChar()>47 && e.getKeyChar()<58)) {
				e.consume();
				lblAlertTelef.setText("< Ingresar Solo Numeros >");
			}else {lblAlertTelef.setText("");}
		}else {
			e.consume();
			lblAlertTelef.setText("< 7 Digitos Como Maximo >");
		}
	}
	protected void keyTypedTxtRazSocJTextField(KeyEvent e) {
		if (txtRazSoc.getText().length()<40) {
			lblAlertRazS.setText("");
			if (txtRazSoc.getText().length()<3) 
				lblAlertRazS.setText("< Se Requiere mas de 3 Letras >");
		}else{
			e.consume();
			lblAlertRazS.setText("< 40 Caracteres como Maximo >");
		}
	}	
	protected void keyTypedTxtContacJTextField(KeyEvent e) {
		if (txtContac.getText().length()<40) {
			lblAlertCont.setText("");
			if (txtContac.getText().length()<3) 
				lblAlertCont.setText("< Se Requiere mas de 3 Letras >");
		}else{
			e.consume();
			lblAlertCont.setText("< 40 Caracteres como Maximo >");
		}
	}
}
