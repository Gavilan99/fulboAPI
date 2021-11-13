package view;


public class GolView {

	private Integer idGol;
	private JugadorView jugador;
	private PartidoView partido;
	private Integer minuto;
	private String sentido;
	
	public GolView(Integer idGol, JugadorView jugador, PartidoView partido, Integer minuto, String sentido) {
		super();
		this.idGol = idGol;
		this.jugador = jugador;
		this.partido = partido;
		this.minuto = minuto;
		this.sentido = sentido;
	}
	
	public Integer getIdGol() {
		return idGol;
	}
	public void setIdGol(Integer idGol) {
		this.idGol = idGol;
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
		return "GolView [idGol=" + idGol + "Jugador=" + jugador.getApellido() + ", minuto=" + minuto
				+ ", sentido=" + sentido + "]";
	}
	
	
	
}
