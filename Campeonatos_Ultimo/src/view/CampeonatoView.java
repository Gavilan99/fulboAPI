package view;

import java.util.Date;
import java.util.List;


public class CampeonatoView {

	private Integer idCampeonato;
	private String descripcion;
	private Date fechaInicio;
	private Date fechaFin;
	private String estado;
	private List<ClubView> inscriptos;
	
	public CampeonatoView(Integer idCampeonato, String descripcion, Date fechaInicio, Date fechaFin, String estado,
			List<ClubView> inscriptos) {
		this.idCampeonato = idCampeonato;
		this.descripcion = descripcion;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.estado = estado;
		this.inscriptos = inscriptos;
	}

	public Integer getIdCampeonato() {
		return idCampeonato;
	}

	public void setIdCampeonato(Integer idCampeonato) {
		this.idCampeonato = idCampeonato;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public List<ClubView> getInscriptos() {
		return inscriptos;
	}

	public void setInscriptos(List<ClubView> inscriptos) {
		this.inscriptos = inscriptos;
	}

	@Override
	public String toString() {
		return "CampeonatoView [idCampeonato=" + idCampeonato + ", descripcion=" + descripcion + ", fechaInicio="
				+ fechaInicio + ", fechaFin=" + fechaFin + ", estado=" + estado + "]";
	}
	
	
}
