package modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import view.FaltaView;

@Entity
@Table (name = "faltas")
public class Falta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idFalta;
	@ManyToOne()
	@JoinColumn(name = "idJugador")
	private Jugador jugador;
	@ManyToOne()
	@JoinColumn(name = "idPartido")
	private Partido partido;
	@ManyToOne()
	@JoinColumn(name = "idCampeonato")
	private Campeonato campeonato;
	private Integer minuto;
	private String tipo;
	
	public Falta(Jugador jugador, Partido partido, Campeonato campeonato, int minuto, String tipo) {
		this.idFalta = null;
		this.jugador = jugador;
		this.partido = partido;
		this.campeonato = campeonato;
		this.minuto = minuto;
		this.tipo = tipo;
	}
	
	public Falta() {}

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
		return tipo;
	}

	public Integer getIdFalta() {
		return idFalta;
	}

	public void setIdFalta(Integer idFalta) {
		this.idFalta = idFalta;
	}

	public Campeonato getCampeonato() {
		return campeonato;
	}

	public void setCampeonato(Campeonato campeonato) {
		this.campeonato = campeonato;
	}
	
	public FaltaView toView() {
		return new FaltaView(idFalta, jugador.toView(), partido.toView(), campeonato.toView(), minuto, tipo);
	}
	
}
