package view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class CampeonatoView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5760207605934261631L;
	private Integer idCampeonato;
	private String descripcion;
	private Date fechaInicio;
	private Date fechaFin;
	private String estado;
	private List<ClubView> inscriptos;
	private Character tieneZonas;
	
	public CampeonatoView(String descripcion, Date fechaInicio, Date fechaFin, String estado, List<ClubView> inscriptos, Character tieneZonas) {
		this.descripcion = descripcion;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.estado = estado;
		this.inscriptos = inscriptos;
		this.tieneZonas = tieneZonas;
	}
	
	public CampeonatoView() {}

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

	public Character getTieneZonas() {
		return tieneZonas;
	}

	public void setTieneZonas(Character tieneZonas) {
		this.tieneZonas = tieneZonas;
	}

	@Override
	public String toString() {
		return "CampeonatoView [idCampeonato=" + idCampeonato + ", descripcion=" + descripcion + ", fechaInicio="
				+ fechaInicio + ", fechaFin=" + fechaFin + ", estado=" + estado + "]";
	}
	
	
}
