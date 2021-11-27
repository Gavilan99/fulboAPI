package view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class PartidoView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8841178654941187279L;
	private Integer idPartido;
	private Integer nroFecha;
	private Integer nroZona;
	private Integer categoria;
	private ClubView clubLocal;
	private ClubView clubVisitante;
	private Integer campeonato;
	private Character terminado;
	
	public PartidoView(Integer nroFecha, Integer nroZona, Integer categoria, Integer golesLocal, Integer golesVisitante, Date fechaPartido, Character convalidaLocal, Character convalidaVisitante, ClubView clubLocal, ClubView clubVisitante, List<MiembroView> jugadoresLocales, List<MiembroView> jugadoresVisitantes, Integer campeonato, Character terminado) {
		this.nroFecha = nroFecha;
		this.nroZona = nroZona;
		this.categoria = categoria;
		this.golesLocal = golesLocal;
		this.golesVisitante = golesVisitante;
		this.fechaPartido = fechaPartido;
		this.convalidaLocal = convalidaLocal;
		this.convalidaVisitante = convalidaVisitante;
		this.clubLocal = clubLocal;
		this.clubVisitante = clubVisitante;
		this.jugadoresLocales = jugadoresLocales;
		this.jugadoresVisitantes = jugadoresVisitantes;
		this.campeonato = campeonato;
		this.terminado = terminado;
	}
	
	public PartidoView() {}
	
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
	
	public Integer getCampeonato() {
		return campeonato;
	}

	public void setCampeonato(Integer campeonato) {
		this.campeonato = campeonato;
	}

	public Character getTerminado() {
		return terminado;
	}

	public void setTerminado(Character terminado) {
		this.terminado = terminado;
	}

	public ClubView getClubLocal() {
		return clubLocal;
	}

	public void setClubLocal(ClubView clubLocal) {
		this.clubLocal = clubLocal;
	}
	public ClubView getClubVisitante() {
		return clubVisitante;
	}

	public void setClubVisitante(ClubView clubVisitante) {
		this.clubVisitante = clubVisitante;
	}
	public Character getConvalidaLocal() {
		return convalidaLocal;
	}

	public void setConvalidaLocal(Character convalidaLocal) {
		this.convalidaLocal = convalidaLocal;
	}

	public Character getConvalidaVisitante() {
		return convalidaVisitante;
	}

	public void setConvalidaVisitante(Character convalidaVisitante) {
		this.convalidaVisitante = convalidaVisitante;
	}

	public List<MiembroView> getJugadoresLocales() {
		return jugadoresLocales;
	}

	public void setJugadoresLocales(List<MiembroView> jugadoresLocales) {
		this.jugadoresLocales = jugadoresLocales;
	}

	public List<MiembroView> getJugadoresVisitantes() {
		return jugadoresVisitantes;
	}

	public void setJugadoresVisitantes(List<MiembroView> jugadoresVisitantes) {
		this.jugadoresVisitantes = jugadoresVisitantes;
	}
	private Integer golesLocal;
	private Integer golesVisitante;
	private Date fechaPartido;
	private Character convalidaLocal;
	private Character convalidaVisitante;
	private List<MiembroView> jugadoresLocales;
	private List<MiembroView> jugadoresVisitantes;
}
