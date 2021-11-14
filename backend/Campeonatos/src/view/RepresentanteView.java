package view;

public class RepresentanteView {

	private Integer legajo;
	private String documento;
	private String nombre;
	private ClubView club;
	
	
	public RepresentanteView(Integer legajo, String documento, String nombre, ClubView club) {
		this.legajo = legajo;
		this.documento = documento;
		this.nombre = nombre;
		this.club = club;
	}


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
