package view;

import java.io.Serializable;

public class MiembroView implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6445461797191827012L;
	private Integer idLista; 
	private Integer club;
	private Integer partido;
	
	
	@Override
	public String toString() {
		return "MiembroView [idLista=" + idLista + ", club=" + club + ", partido=" + partido + ", jugador=" + jugador
				+ ", ingreso=" + ingreso + ", egreso=" + egreso + "]";
	}

	public MiembroView(Integer idLista, Integer club, Integer partido, Integer jugador, Integer ingreso, Integer egreso) {
		this.idLista = idLista;
		this.club = club;
		this.partido = partido;
		this.jugador = jugador;
		this.ingreso = ingreso;
		this.egreso = egreso;
	}
	
	public MiembroView() {}
	
	public Integer getIdLista() {
		return idLista;
	}
	public void setIdLista(Integer idLista) {
		this.idLista = idLista;
	}
	public Integer getClub() {
		return club;
	}
	public void setClub(Integer club) {
		this.club = club;
	}
	public Integer getPartido() {
		return partido;
	}
	public void setPartido(Integer partido) {
		this.partido = partido;
	}
	public Integer getJugador() {
		return jugador;
	}
	public void setJugador(Integer jugador) {
		this.jugador = jugador;
	}
	public Integer getIngreso() {
		return ingreso;
	}
	public void setIngreso(Integer ingreso) {
		this.ingreso = ingreso;
	}
	public Integer getEgreso() {
		return egreso;
	}
	public void setEgreso(Integer egreso) {
		this.egreso = egreso;
	}
	private Integer jugador;
	private Integer ingreso;
	private Integer egreso;
}
