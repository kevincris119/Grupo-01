package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidad.Usuario;
import util.MySqlDBConexion;

public class UsuarioModel {
	//El metodo que va inserta enb la tabla campeonato
		public int insertaUsuario(Usuario u){
			int salida = -1;
			
			Connection con = null;
			PreparedStatement pstm  = null;
			try{
				//1 Conectar a la base de  datos
				con = MySqlDBConexion.getConexion();
				
				//2 Se prepara el SQL
				String sql = "insert into usuario values(null,?,?,?,?,?)";
				pstm = con.prepareStatement(sql);
				pstm.setString(1, u.getNombre());
				pstm.setString(2, u.getApellido());
				pstm.setString(3, u.getDni());
				pstm.setString(4, u.getLogin());
				pstm.setString(5, u.getPassword());
				
				System.out.println("SQL-->" + pstm);
				
				//3 envia el sql y se recibe la cantidad de registrados
				salida = pstm.executeUpdate();
				
				
			}catch(Exception e1){
				e1.printStackTrace();	
			}finally{
				try {
					if(pstm!= null)pstm.close();
					if(con!= null)con.close();
				} catch (Exception e2) {}
			}
			return salida;
		}
		
		public List<Usuario> listaUsuarios() {
			ArrayList<Usuario> data = new ArrayList<Usuario>();
			Connection con = null;
			PreparedStatement pstm = null;
			ResultSet rs = null; //Trae la data de la BD
			try {
				con = MySqlDBConexion.getConexion();
				String sql ="select * from usuario";
				pstm = con.prepareStatement(sql);
				System.out.println("SQL-->" + pstm);
				
				//En rs se trae los datos de la BD segun el SQL
				rs = pstm.executeQuery();
				
				//Se pasa la data del rs al ArrayList(data)
				Usuario u = null;
				while(rs.next()){
					u = new Usuario();
					
					// Se colocan los campos de la base de datos
					u.setIdusuario(rs.getInt("idusuario"));
					u.setNombre(rs.getString("nombre"));
					u.setApellido(rs.getString("apellido"));
					u.setDni(rs.getString("dni"));
					u.setLogin(rs.getString("login"));
					u.setPassword(rs.getString("password"));
					
					data.add(u);
				}
			
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (pstm != null)pstm.close();
					if (con != null)con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return data;
		}
		
		public int actualizaUsuario(Usuario u) {
			int actualizados = -1;
			Connection con = null;
			PreparedStatement pstm = null;
			try {
				con = MySqlDBConexion.getConexion();
				String sql = "update usuario set nombre=?, apellido=?, dni=?, login=?, password=? where idusuario=?"; 
				pstm = con.prepareStatement(sql);
				pstm.setString(1, u.getNombre());
				pstm.setString(2, u.getApellido());
				pstm.setString(3, u.getDni());
				pstm.setString(4, u.getLogin());
				pstm.setString(5, u.getPassword());
				pstm.setInt(6, u.getIdusuario());
				actualizados = pstm.executeUpdate();
				System.out.println("actualizados :  " + actualizados);
			} catch (Exception e1) {
				e1.printStackTrace();
			} finally {
				try {
					if (pstm != null)pstm.close();
					if (con != null)con.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			return actualizados;
		}
		
		
		public int eliminaUsuario(int idusuario) {
			int eliminados = -1;
			Connection con = null;
			PreparedStatement pstm = null;

			try {
				con = MySqlDBConexion.getConexion();
				String sql ="delete from usuario where idusuario=?";
				pstm = con.prepareStatement(sql);
				pstm.setInt(1, idusuario);
				
				eliminados = pstm.executeUpdate();
				System.out.println("eliminados :  " + eliminados);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (pstm != null)pstm.close();
					if (con != null)con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			return eliminados;
		}
		
		
}
