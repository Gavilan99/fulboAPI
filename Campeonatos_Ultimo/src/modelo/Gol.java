package modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import daos.GolDAO;
import view.GolView;
import view.JugadorView;

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
		this.idGol = null;
		this.jugador = jugador;
		this.partido = partido;
		this.minuto = minuto;
		this.sentido = sentido;
		GolDAO.getInstancia().grabar(this);
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
		this.actualizar();
	}

	public GolView toView() {
		GolView gv = new GolView(jugador.toString(), Integer.toString(partido.getIdPartido()) , minuto, sentido);
		gv.setIdGol(idGol);
		return gv;
	}
	
	public GolView toViewJugador(JugadorView jv) {
		GolView gv = new GolView(jv.toString(),Integer.toString(partido.getIdPartido()), minuto, sentido);
		gv.setIdGol(idGol);
		return gv;
	}
	
	private void actualizar() {
		GolDAO.getInstancia().actualizar(this);
	}
}
