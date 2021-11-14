package view;

public class FaltaView {

	private Integer idFalta;
	private JugadorView jugador;
	private PartidoView partido;
	private CampeonatoView campeonato;
	private Integer minuto;
	private String tipo;
	
	
	public FaltaView(Integer idFalta, JugadorView jugador, PartidoView partido, CampeonatoView campeonato,
			Integer minuto, String tipo) {
		super();
		this.idFalta = idFalta;
		this.jugador = jugador;
		this.partido = partido;
		this.campeonato = campeonato;
		this.minuto = minuto;
		this.tipo = tipo;
	}


	public Integer getIdFalta() {
		return idFalta;
	}


	public void setIdFalta(Integer idFalta) {
		this.idFalta = idFalta;
	}


	public JugadorView getJugador() {
		return jugador;
	}


	public void setJugador(JugadorView jugador) {
		this.jugador = jugador;
	}


	public PartidoView getPartido() {
		return partido;
	}


	public void setPartido(PartidoView partido) {
		this.partido = partido;
	}


	public CampeonatoView getCampeonato() {
		return campeonato;
	}


	public void setCampeonato(CampeonatoView campeonato) {
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
		return "FaltaView [idFalta=" + idFalta + ", Jugador=" + jugador.getApellido() + ", minuto=" + minuto + ", tipo=" + tipo + "]";
	}
	
	
	
}
