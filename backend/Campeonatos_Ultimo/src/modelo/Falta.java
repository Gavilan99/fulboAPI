package modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import daos.FaltaDAO;
import view.FaltaView;
import view.JugadorView;

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
		FaltaDAO.getInstancia().grabar(this);
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
		this.actualizar();
	}

	public Campeonato getCampeonato() {
		return campeonato;
	}

	public void setCampeonato(Campeonato campeonato) {
		this.campeonato = campeonato;
		this.actualizar();
	}
	
	public FaltaView toView() {
		FaltaView fv = new FaltaView(jugador.toString(), Integer.toString(partido.getIdPartido()), campeonato.toString(), minuto, tipo);
		fv.setIdFalta(idFalta);
		return fv;
	}
	
	public FaltaView toViewJugador(JugadorView jv) {
		FaltaView fv = new FaltaView(jv.toString(), Integer.toString(partido.getIdPartido()), campeonato.toString(), minuto, tipo);
		fv.setIdFalta(idFalta);
		return fv;
	}
	
	private void actualizar() {
		FaltaDAO.getInstancia().actualizar(this);
	}
}
