package view;

import java.util.Date;

public class PartidoView {

	private Integer idPartido;
	private Integer nroFecha;
	private Integer nroZona;
	private Integer categoria;
	
	
	public PartidoView(Integer idPartido, Integer nroFecha, Integer nroZona, Integer categoria, Integer golesLocal,
			Integer golesVisitante, Date fechaPartido, char convalidaLocal, char convalidaVisitante) {
		this.idPartido = idPartido;
		this.nroFecha = nroFecha;
		this.nroZona = nroZona;
		this.categoria = categoria;
		this.golesLocal = golesLocal;
		this.golesVisitante = golesVisitante;
		this.fechaPartido = fechaPartido;
		this.convalidaLocal = convalidaLocal;
		this.convalidaVisitante = convalidaVisitante;
	}
	@Override
	public String toString() {
		return "PartidoView [idPartido=" + idPartido + ", nroFecha=" + nroFecha + ", nroZona=" + nroZona
				+ ", categoria=" + categoria + ", golesLocal=" + golesLocal + ", golesVisitante=" + golesVisitante
				+ ", fechaPartido=" + fechaPartido + ", convalidaLocal=" + convalidaLocal + ", convalidaVisitante="
				+ convalidaVisitante + "]";
	}
	public Integer getIdPartido() {
		return idPartido;
	}
	public void setIdPartido(Integer idPartido) {
		this.idPartido = idPartido;
	}
	public Integer getNroFecha() {
		return nroFecha;
	}
	public void setNroFecha(Integer nroFecha) {
		this.nroFecha = nroFecha;
	}
	public Integer getNroZona() {
		return nroZona;
	}
	public void setNroZona(Integer nroZona) {
		this.nroZona = nroZona;
	}
	public Integer getCategoria() {
		return categoria;
	}
	public void setCategoria(Integer categoria) {
		this.categoria = categoria;
	}
	public Integer getGolesLocal() {
		return golesLocal;
	}
	public void setGolesLocal(Integer golesLocal) {
		this.golesLocal = golesLocal;
	}
	public Integer getGolesVisitante() {
		return golesVisitante;
	}
	public void setGolesVisitante(Integer golesVisitante) {
		this.golesVisitante = golesVisitante;
	}
	public Date getFechaPartido() {
		return fechaPartido;
	}
	public void setFechaPartido(Date fechaPartido) {
		this.fechaPartido = fechaPartido;
	}
	public char getConvalidaLocal() {
		return convalidaLocal;
	}
	public void setConvalidaLocal(char convalidaLocal) {
		this.convalidaLocal = convalidaLocal;
	}
	public char getConvalidaVisitante() {
		return convalidaVisitante;
	}
	public void setConvalidaVisitante(char convalidaVisitante) {
		this.convalidaVisitante = convalidaVisitante;
	}
	private Integer golesLocal;
	private Integer golesVisitante;
	private Date fechaPartido;
	private char convalidaLocal;
	private char convalidaVisitante;
}
