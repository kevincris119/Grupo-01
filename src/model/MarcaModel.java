package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidad.Marca;
import util.MySqlDBConexion;


public class MarcaModel {
	public int insertaMarca(Marca m) {
		int salida = - 1;
		
		Connection conn=null;
		PreparedStatement pstm = null;
		
		try {
			conn = MySqlDBConexion.getConexion();
			String sql = "insert into marca values(null,?,?)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, m.getNombre());
			pstm.setString(2, m.getEstado());
			
			System.out.println("SQL-->" + pstm);
			salida = pstm.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstm!= null)pstm.close();
				if(conn!= null)conn.close();
			} catch (Exception e2) {}
		}
		return salida;
	}
	public List<Marca> listaMarca() {
		ArrayList<Marca> data = new ArrayList<Marca>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			conn = MySqlDBConexion.getConexion();
			String sql ="select * from marca";
			pstm = conn.prepareStatement(sql);
			System.out.println("SQL-->" + pstm);
			rs = pstm.executeQuery();
			Marca c = null;
			while(rs.next()){
				c = new Marca();
				c.setIdMarca(rs.getInt("idMarca"));
				c.setNombre(rs.getString("nombre"));
				c.setEstado(rs.getString("estado"));
				
				data.add(c);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (pstm != null)pstm.close();
				if (conn != null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		
		return data;
	}
	public int actualizaMarca(Marca m) {
		int actualizados = -1;
		Connection con = null;
		PreparedStatement pstm = null;
		try {
			con = MySqlDBConexion.getConexion();
			String sql = "update marca set nombre=?, estado=? where idmarca=?"; 
			pstm = con.prepareStatement(sql);
			pstm.setString(1, m.getNombre());
			pstm.setString(2, m.getEstado());
			pstm.setInt(3, m.getIdMarca());
			actualizados = pstm.executeUpdate();
			System.out.println("actualizados :  " + actualizados);
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
		return actualizados;
	}

	
	public int eliminaMarca(int idMarca) {
		int eliminados = -1;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = MySqlDBConexion.getConexion();
			String sql ="delete from marca where idmarca=?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, idMarca);
			eliminados = pstm.executeUpdate();
			System.out.println("eliminados :  " + eliminados);
			System.out.println("SQL-->" + pstm);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null)pstm.close();
				if (conn != null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return eliminados;
	}
	
}
