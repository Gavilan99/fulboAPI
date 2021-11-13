package view;

import java.io.Serializable;

public class RepresentanteView implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8965730219707203636L;
	private Integer legajo;
	private String documento;
	private String nombre;
	private ClubView club;
	
	
	public RepresentanteView(String documento, String nombre, ClubView club) {
		this.documento = documento;
		this.nombre = nombre;
		this.club = club;
	}
	
	public RepresentanteView() {}


	public Integer getLegajo() {
		return legajo;
	}


	public void setLegajo(Integer legajo) {
		this.legajo = legajo;
	}


	public String getDocumento() {
		return documento;
	}


	public void setDocumento(String documento) {
		this.documento = documento;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public ClubView getClub() {
		return club;
	}


	public void setClub(ClubView club) {
		this.club = club;
	}


	@Override
	public String toString() {
		return "RepresentanteView [legajo=" + legajo + ", documento=" + documento + ", nombre=" + nombre + ", club="
				+ club .getNombre()+ "]";
	}
	
	
	
}
