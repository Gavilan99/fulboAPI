package modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import view.GolView;

@Entity
@Table(name = "goles")
public class Gol {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idGol;
	@ManyToOne()
	@JoinColumn(name = "idJugador")
	private Jugador jugador;
	@ManyToOne()
	@JoinColumn(name = "idPartido")
	private Partido partido;
	private Integer minuto;
	private String sentido;
		
	public Gol(Jugador jugador, Partido partido, int minuto, String sentido) {
		this.setIdGol(null);
		this.jugador = jugador;
		this.partido = partido;
		this.minuto = minuto;
		this.sentido = sentido;
	}
	
	public Gol() {}
	
	public Jugador getJugador() {
		return jugador;
	}
	public Partido getPartido() {
		return partido;
	}
	public int getMinuto() {
		return minuto;
	}
	public String getTipo() {
		return sentido;
	}

	public Integer getIdGol() {
		return idGol;
	}

	public void setIdGol(Integer idGol) {
		this.idGol = idGol;
	}

	public GolView toView() {
		return new GolView(idGol, jugador.toView(), partido.toView(), minuto, sentido);
	}
}
