package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidad.TipoReclamo;
import util.MySqlDBConexion;

public class TipoReclamoModel {
	//El metodo que va inserta enb la tabla campeonato
		public int insertaTipoReclamo(TipoReclamo tc){
			int salida = -1;
			
			Connection con = null;
			PreparedStatement pstm  = null;
			try{
				//1 Conectar a la base de  datos
				con = MySqlDBConexion.getConexion();
				
				//2 Se prepara el SQL
				String sql = "insert into tiporeclamo values(null,?,?,?)";
				pstm = con.prepareStatement(sql);
				pstm.setString(1, tc.getDescripcion());
				pstm.setString(2, tc.getEstado());
				pstm.setDate(3, tc.getFechaRegistro());
				
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
		
		public List<TipoReclamo> listatipoTipoReclamos() {
			ArrayList<TipoReclamo> data = new ArrayList<TipoReclamo>();
			Connection con = null;
			PreparedStatement pstm = null;
			ResultSet rs = null; //Trae la data de la BD
			try {
				con = MySqlDBConexion.getConexion();
				String sql ="select * from tiporeclamo";
				pstm = con.prepareStatement(sql);
				System.out.println("SQL-->" + pstm);
				
				//En rs se trae los datos de la BD segun el SQL
				rs = pstm.executeQuery();
				
				//Se pasa la data del rs al ArrayList(data)
				TipoReclamo tc = null;
				while(rs.next()){
					tc = new TipoReclamo();
					
					// Se colocan los campos de la base de datos
					tc.setIdTipoReclamo(rs.getInt("idTipoReclamo"));
					tc.setDescripcion(rs.getString("Descripcion"));
					tc.setEstado(rs.getString("Estado"));
					tc.setFechaRegistro(rs.getDate("FechaRegistro"));
					
					data.add(tc);
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
		
		
		public int actualizaTipoReclamo(TipoReclamo tc) {
			int actualizados = -1;
			Connection con = null;
			PreparedStatement pstm = null;
			try {
				con = MySqlDBConexion.getConexion();
				String sql = "update tiporeclamo set descripcion=?, estado=?, fechaRegistro=? where idtipoReclamo=?"; 
				pstm = con.prepareStatement(sql);
				pstm.setString(1, tc.getDescripcion());
				pstm.setString(2, tc.getEstado());
				pstm.setDate(3, tc.getFechaRegistro());
				pstm.setInt(4, tc.getIdTipoReclamo());
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
		
		
		public int eliminaTipoReclamo(int idtipoReclamo) {
			int eliminados = -1;
			Connection con = null;
			PreparedStatement pstm = null;

			try {
				con = MySqlDBConexion.getConexion();
				String sql ="delete from tiporeclamo where idtipoReclamo=?";
				pstm = con.prepareStatement(sql);
				pstm.setInt(1, idtipoReclamo);
				
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
