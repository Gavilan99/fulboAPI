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

import daos.MiembroDAO;
import view.MiembroView;

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
		this.actualizar();
	}

	
	public Club getClub() {
		return club;
	}

	public void setClub(Club club) {
		this.club = club;
		this.actualizar();
	}

	public Partido getPartido() {
		return partido;
	}

	public void setPartido(Partido partido) {
		this.partido = partido;
		this.actualizar();
	}

	public Integer getIngreso() {
		return ingreso;
	}

	public void setIngreso(Integer ingreso) {
		this.ingreso = ingreso;
		this.actualizar();
	}

	public Integer getEgreso() {
		return egreso;
	}

	public void setEgreso(Integer egreso) {
		this.egreso = egreso;
		this.actualizar();
	}

	public Integer getIdLista() {
		return idLista;
	}
	
	public void grabar() {
		MiembroDAO.getInstancia().grabar(this);
	}
	
	private void actualizar() {
		MiembroDAO.getInstancia().actualizar(this);
	}

	@Override
	public String toString() {
		return "Miembro [idLista=" + idLista + ", club=" + club.getNombre() + ", partido=" + partido .getIdPartido()+ ", jugador=" + jugador.getNombre()
				+ ", ingreso=" + ingreso + ", egreso=" + egreso + "]";
	}

	public MiembroView toView() {
		return new MiembroView(idLista, club.toString(), partido.getIdPartido(), jugador.toString(), ingreso, egreso);
	}
	
}
