package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidad.Proveedor;
import util.MySqlDBConexion;


public class ProveedorModel {

	//El metodo que va inserta enb la tabla proveedor
		public int insertaProveedor(Proveedor p){
			int salida = -1;
			
			Connection con = null;
			PreparedStatement pstm  = null;
			try{
				//1 Conectar a la base de  datos
				con = MySqlDBConexion.getConexion();
				
				//2 Se prepara el SQL
				String sql = "insert into proveedor values(null,?,?,?,?,?,?,?)";
				pstm = con.prepareStatement(sql);
				pstm.setString(1, p.getRazonSocial());
				pstm.setString(2, p.getRuc());
				pstm.setString(3, p.getDireccion());
				pstm.setString(4, p.getTelefono());
				pstm.setString(5, p.getCelular());
				pstm.setString(6, p.getContacto());
				pstm.setString(7, p.getEstado());		
				
				System.out.println("SQL-->" + pstm);
				
				//3 envia el sql y se recibe la cantidad de registrados
				salida = pstm.executeUpdate();
				
				
			}catch(Exception e){
				e.printStackTrace();	
			}finally{
				try {
					if(pstm!= null)pstm.close();
					if(con!= null)con.close();
				} catch (Exception e2) {}
			}
			return salida;
		}
		
		public List<Proveedor> listaProveedor() {
			ArrayList<Proveedor> data = new ArrayList<Proveedor>();
			Connection con = null;
			PreparedStatement pstm = null;
			ResultSet rs = null; //Trae la data de la BD
			try {
				con = MySqlDBConexion.getConexion();
				String sql ="select * from Proveedor";
				pstm = con.prepareStatement(sql);
				System.out.println("SQL-->" + pstm);
				
				//En rs se trae los datos de la BD segun el SQL
				rs = pstm.executeQuery();
				
				//Se pasa la data del rs al ArrayList(data)
				Proveedor c = null;
				while(rs.next()){
					c = new Proveedor();
					
					// Se colocan los campos de la base de datos
					c.setIdproveedor(rs.getInt("idproveedor"));
					c.setRazonSocial(rs.getString("razonsocial"));
					c.setRuc(rs.getString("ruc"));
					c.setDireccion(rs.getString("direccion"));
					c.setTelefono(rs.getString("telefono"));
					c.setCelular(rs.getString("celular"));
					c.setContacto(rs.getString("contacto"));
					c.setEstado(rs.getString("estado"));
					
					data.add(c);
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
		
		public int actualizaProveedor(Proveedor p) {
			int actualizados = -1;
			Connection con = null;
			PreparedStatement pstm = null;
			try {
				con = MySqlDBConexion.getConexion();
				String sql = "update proveedor set razonsocial=?, ruc=?,direccion=?,telefono=?,celular=?,"
						+ "contacto=?,estado=? where idproveedor=?"; 
				pstm = con.prepareStatement(sql);
				pstm.setString(1, p.getRazonSocial());
				pstm.setString(2, p.getRuc());
				pstm.setString(3, p.getDireccion());
				pstm.setString(4, p.getTelefono());
				pstm.setString(5, p.getCelular());
				pstm.setString(6, p.getContacto());
				pstm.setString(7, p.getEstado());
				pstm.setInt(8, p.getIdproveedor());
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
		
		
		public int eliminaProveedor(int idproveedor) {
			int eliminados = -1;
			Connection con = null;
			PreparedStatement pstm = null;

			try {
				con = MySqlDBConexion.getConexion();
				String sql ="delete from proveedor where idproveedor=?";
				pstm = con.prepareStatement(sql);
				pstm.setInt(1, idproveedor);
				
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


