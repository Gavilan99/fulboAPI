package modelo;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "listaJugadoresPartido")
public class Miembro {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idLista; 
	@ManyToOne()
	@JoinColumn(name = "idClub")
	private Club club;
	@ManyToOne()
	@JoinColumn(name = "idPartido")
	private Partido partido;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idJugador")
	private Jugador jugador;
	private Integer ingreso;
	private Integer egreso;

	
	public Miembro(Club club, Partido partido, Jugador jugador) {
		this.club = club;
		this.partido = partido;
		this.jugador = jugador;
		this.ingreso = null;
		this.egreso = null;
	}
	
	public Miembro() {}

	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	
	public Club getClub() {
		return club;
	}

	public void setClub(Club club) {
		this.club = club;
	}

	public Partido getPartido() {
		return partido;
	}

	public void setPartido(Partido partido) {
		this.partido = partido;
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

	public Integer getIdLista() {
		return idLista;
	}
}
