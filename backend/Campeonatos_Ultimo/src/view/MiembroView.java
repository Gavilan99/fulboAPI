package view;

import java.io.Serializable;

public class MiembroView implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6445461797191827012L;
	private Integer idLista; 
	private String club;
	private Integer partido;
	
	
	@Override
	public String toString() {
		return "MiembroView [idLista=" + idLista + ", club=" + club + ", partido=" + partido + ", jugador=" + jugador
				+ ", ingreso=" + ingreso + ", egreso=" + egreso + "]";
	}

	public MiembroView(Integer idLista, String club, Integer partido, String jugador, Integer ingreso, Integer egreso) {
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
	public String getClub() {
		return club;
	}
	public void setClub(String club) {
		this.club = club;
	}
	public Integer getPartido() {
		return partido;
	}
	public void setPartido(Integer partido) {
		this.partido = partido;
	}
	public String getJugador() {
		return jugador;
	}
	public void setJugador(String jugador) {
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
	private String jugador;
	private Integer ingreso;
	private Integer egreso;
}
