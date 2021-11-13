package view;

import java.io.Serializable;

public class FaltaView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7090275411522154873L;
	private Integer idFalta;
	private String jugador;
	private String partido;
	private String campeonato;
	private Integer minuto;
	private String tipo;
	
	
	public FaltaView(String jugador, String partido, String campeonato, Integer minuto, String tipo) {
		this.jugador = jugador;
		this.partido = partido;
		this.campeonato = campeonato;
		this.minuto = minuto;
		this.tipo = tipo;
	}
	
	public FaltaView() {}


	public Integer getIdFalta() {
		return idFalta;
	}


	public void setIdFalta(Integer idFalta) {
		this.idFalta = idFalta;
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


	public String getCampeonato() {
		return campeonato;
	}


	public void setCampeonato(String campeonato) {
		this.campeonato = campeonato;
	}


	public Integer getMinuto() {
		return minuto;
	}


	public void setMinuto(Integer minuto) {
		this.minuto = minuto;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	@Override
	public String toString() {
		return "FaltaView [idFalta=" + idFalta + ", Jugador=" + jugador + ", minuto=" + minuto + ", tipo=" + tipo + "]";
	}
	
	
	
}
