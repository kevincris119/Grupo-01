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

import entidad.Usuario;
import model.UsuarioModel;
import util.Validaciones;

public class FrmCrudUsuario extends JFrame implements ActionListener, MouseListener {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtDni;
	private JTextField txtLogin;
	private JTextField txtPassword;
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
					FrmCrudUsuario frame = new FrmCrudUsuario();
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
	public FrmCrudUsuario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 466);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setOpaque(true);
		lblUsuario.setBackground(Color.RED);
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuario.setForeground(Color.WHITE);
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblUsuario.setBounds(10, 11, 414, 59);
		contentPane.add(lblUsuario);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(105, 95, 84, 26);
		contentPane.add(lblNombre);

		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setBounds(105, 142, 46, 26);
		contentPane.add(lblApellidos);
		
		JLabel lblDni = new JLabel("Dni");
		lblDni.setBounds(105, 189, 46, 26);
		contentPane.add(lblDni);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setBounds(105, 236, 46, 26);
		contentPane.add(lblLogin);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(105, 283, 46, 26);
		contentPane.add(lblPassword);

		txtNombre = new JTextField();
		txtNombre.setBounds(184, 98, 211, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);

		txtApellido = new JTextField();
		txtApellido.setBounds(184, 145, 86, 20);
		contentPane.add(txtApellido);
		txtApellido.setColumns(10);
		
		txtDni = new JTextField();
		txtDni.setBounds(184, 192, 86, 20);
		contentPane.add(txtDni);
		txtDni.setColumns(10);
		
		txtLogin = new JTextField();
		txtLogin.setBounds(184, 239, 86, 20);
		contentPane.add(txtLogin);
		txtLogin.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setBounds(184, 293, 86, 20);
		contentPane.add(txtPassword);
		txtPassword.setColumns(10);

		btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(this);
		btnRegistrar.setIcon(new ImageIcon(FrmCrudUsuario.class.getResource("/iconos/add.gif")));
		btnRegistrar.setBounds(10, 179, 114, 33);
		contentPane.add(btnRegistrar);

		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(this);
		btnActualizar.setIcon(new ImageIcon(FrmCrudUsuario.class.getResource("/iconos/edit.gif")));
		btnActualizar.setBounds(310, 179, 114, 33);
		contentPane.add(btnActualizar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this);
		btnEliminar.setIcon(new ImageIcon(FrmCrudUsuario.class.getResource("/iconos/delete.gif")));
		btnEliminar.setBounds(158, 179, 114, 33);
		contentPane.add(btnEliminar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 233, 414, 184);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(this);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Id", "Nombre", "Apellido", "Dni", "Login", "Password" }));
		scrollPane.setViewportView(table);

		// Traer todos los campeonatos de la BD
		listaUsuarios();
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
			String nom = txtNombre.getText();
			String ape=txtApellido.getText();
			String dni=txtDni.getText();
			String log=txtLogin.getText();
			String pwd=txtPassword.getText();

			// 2 Validaciones
			if (nom.matches(Validaciones.TEXTO) == false) {
				mensaje("El nombre es de 3 a 20 caracteres");
			}else if (ape.matches(Validaciones.TEXTO) == false) {
				mensaje("El pais es de 3 a 20 caracteres");
			}else if (dni.matches(Validaciones.DNI) == false) {
				mensaje("El dni es de 8 digitos");
			}else if (log.matches(Validaciones.TEXTO) == false) {
				mensaje("El login es de texto y digitos");
			}else if (pwd.matches(Validaciones.TEXTO) == false) {
				mensaje("El password es de texto y digitos");
			}else {
				// 3 Se crea el objeto jugador
				Usuario obj = new Usuario();
				obj.setNombre(nom);
				obj.setApellido(ape);
				obj.setDni(dni);
				obj.setLogin(log);
				obj.setPassword(pwd);

				// 4 Se envía al modelo de datos
				UsuarioModel model = new UsuarioModel();
				int salida = model.insertaUsuario(obj);

				if (salida > 0) {
					mensaje("Se envió correctamente");
					listaUsuarios();
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
			UsuarioModel m= new UsuarioModel();
			int s = m.eliminaUsuario(idSeleccionado);
			if (s > 0) {
				mensaje("Se eliminó correctamente");
				listaUsuarios();
				limpiarCajasTexto();
				idSeleccionado = -1;
			} else {
				mensaje("Error en la eliminación");
			}
		}
	}
	protected void do_btnActualizar_actionPerformed(ActionEvent arg0) {
			// 1 Recibir los valores de la GUI
			String nom = txtNombre.getText();
			String ape=txtApellido.getText();
			String dni=txtDni.getText();
			String log=txtLogin.getText();
			String pwd=txtPassword.getText();
	
			// 2 Validaciones
			if (nom.matches(Validaciones.TEXTO) == false) {
				mensaje("El nombre es de 3 a 20 caracteres");
			}else if (ape.matches(Validaciones.TEXTO) == false) {
				mensaje("El pais es de 3 a 20 caracteres");
			}else if (dni.matches(Validaciones.DNI) == false) {
				mensaje("El dni es de 8 digitos");
			}else if (log.matches(Validaciones.TEXTO) == false) {
				mensaje("El login es de texto y digitos");
			}else if (pwd.matches(Validaciones.TEXTO) == false) {
				mensaje("El password es de texto y digitos");
			}else{
				// 3 Se crea el objeto jugador
				Usuario obj = new Usuario();
				obj.setIdusuario(idSeleccionado);
				obj.setNombre(nom);
				obj.setApellido(ape);
				obj.setDni(dni);
				obj.setLogin(log);
				obj.setPassword(pwd);

				// 4 Se envía al modelo de datos
				UsuarioModel model = new UsuarioModel();
				int salida = model.actualizaUsuario(obj);

				if (salida > 0) {
					mensaje("Se envió correctamente");
					listaUsuarios();
					limpiarCajasTexto();
					idSeleccionado=-1;
				} else {
					mensaje("Error en el registro");
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
		String nombre = table.getValueAt(fila, 1).toString();
		String apellido = table.getValueAt(fila, 2).toString();
		String dni = table.getValueAt(fila, 3).toString();
		String login = table.getValueAt(fila, 4).toString();
		String password = table.getValueAt(fila, 5).toString();

		txtNombre.setText(nombre);
		txtApellido.setText(apellido);
		txtDni.setText(dni);
		txtLogin.setText(login);
		txtPassword.setText(password);
	}

	void listaUsuarios() {
		UsuarioModel m = new UsuarioModel();
		List<Usuario> data = m.listaUsuarios();

		// Se accede a la jtable de la GUI
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		// Se coloca a sero las filas
		dtm.setRowCount(0);

		// Se agregan los campeonatos al jtable
		for (Usuario aux : data) {
			Object[] fila = { aux.getIdusuario(),
					          aux.getNombre(),
					          aux.getApellido(),
					          aux.getDni(),
					          aux.getLogin(),
					          aux.getPassword()};
			dtm.addRow(fila);
		}
	}

	void mensaje(String m) {
		JOptionPane.showMessageDialog(this, m);
	}

	void limpiarCajasTexto() {
		txtNombre.setText("");
		txtApellido.setText("");
		txtDni.setText("");
		txtLogin.setText("");
		txtPassword.setText("");
		txtNombre.requestFocus();
	}

}
