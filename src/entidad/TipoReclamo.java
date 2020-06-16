package entidad;

import java.sql.Date;

public class TipoReclamo {
	private int idTipoReclamo;
	private String Descripcion;
	private String Estado;
	private Date FechaRegistro;
	
	public int getIdTipoReclamo() {
		return idTipoReclamo;
	}
	public void setIdTipoReclamo(int idTipoReclamo) {
		this.idTipoReclamo = idTipoReclamo;
	}
	public String getDescripcion() {
		return Descripcion;
	}
	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
	public String getEstado() {
		return Estado;
	}
	public void setEstado(String estado) {
		Estado = estado;
	}
	public Date getFechaRegistro() {
		return FechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		FechaRegistro = fechaRegistro;
	}
	
	
	
}
