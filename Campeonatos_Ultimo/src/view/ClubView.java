package view;

import java.io.Serializable;

public class ClubView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5691014932816081788L;
	private Integer idClub;
	private String nombre;
	private String direccion;
	
	public ClubView(Integer idClub, String nombre, String direccion) {
		this.idClub = idClub;
		this.nombre = nombre;
		this.direccion = direccion;
	}
	
	public ClubView() {}
	
	public Integer getIdClub() {
		return idClub;
	}
	public void setIdClub(Integer idClub) {
		this.idClub = idClub;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@Override
	public String toString() {
		return "ClubView [idClub=" + idClub + ", nombre=" + nombre + ", direccion=" + direccion + "]";
	}
	
	
}
