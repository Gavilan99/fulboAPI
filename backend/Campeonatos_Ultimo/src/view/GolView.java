package view;

import java.io.Serializable;

public class GolView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1209130228295967496L;
	private Integer idGol;
	private String jugador;
	private String partido;
	private Integer minuto;
	private String sentido;
	
	public GolView(String jugador, String partido, Integer minuto, String sentido) {
		this.jugador = jugador;
		this.partido = partido;
		this.minuto = minuto;
		this.sentido = sentido;
	}
	
	public GolView() {}
	
	public Integer getIdGol() {
		return idGol;
	}
	public void setIdGol(Integer idGol) {
		this.idGol = idGol;
	}
	public String getJugador() {
		return jugador;
	}
	public void setJugador(String jugador) {
		this.jugador = jugador;
	}
	public String getPartido() {
		return partido;
	}
	public void setPartido(String partido) {
		this.partido = partido;
	}
	public Integer getMinuto() {
		return minuto;
	}
	public void setMinuto(Integer minuto) {
		this.minuto = minuto;
	}
	public String getSentido() {
		return sentido;
	}
	public void setSentido(String sentido) {
		this.sentido = sentido;
	}

	@Override
	public String toString() {
		return "GolView [idGol=" + idGol + "Jugador=" + jugador + ", minuto=" + minuto
				+ ", sentido=" + sentido + "]";
	}
	
	
	
}
