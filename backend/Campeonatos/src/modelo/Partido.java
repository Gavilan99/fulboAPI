package modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import view.PartidoView;

@Entity
@Table(name = "partidos")
public class Partido {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPartido;
	private Integer nroFecha;
	private Integer nroZona;
	private Integer categoria;
	@ManyToOne()
	@JoinColumn(name = "idClubLocal")
	private Club clubLocal;
	@ManyToOne()
	@JoinColumn(name = "idClubVisitante")
	private Club clubVisitante;
	private Integer golesLocal;
	private Integer golesVisitante;
	@Temporal(value = TemporalType.DATE)
	private Date fechaPartido;
	@Column(name = "validadoLocal")
	private char convalidaLocal;
	@Column(name = "validadoVisitante")
	private char convalidaVisitante;
	@ManyToOne()
	@JoinColumn(name = "idCampeonato")
	private Campeonato campeonato;
	@OneToMany(mappedBy = "partido", cascade = CascadeType.ALL)
	private List<Miembro> jugadoresLocales;
	@OneToMany(mappedBy = "partido", cascade = CascadeType.ALL)
	private List<Miembro> jugadoresVisitantes;

	public Partido(int nroFecha, int nroZona, int categoria, Club clubLocal, Club clubVisitante, 
			       Date fechaPartido, Campeonato campeonato) {
		this.nroFecha = nroFecha;
		this.nroZona = nroZona;
		this.categoria = categoria;
		this.clubLocal = clubLocal;
		this.clubVisitante = clubVisitante;
		this.golesLocal = null;
		this.golesVisitante = null;
		this.fechaPartido = fechaPartido;
		this.convalidaLocal = 'N';
		this.convalidaVisitante = 'N';
		this.campeonato = campeonato;
		jugadoresLocales = new ArrayList<Miembro>();
		jugadoresVisitantes = new ArrayList<Miembro>();
	}
	
	public Partido() {}

	public Integer getIdPartido() {
		return idPartido;
	}

	public int getNroFecha() {
		return nroFecha;
	}

	public int getNroZona() {
		return nroZona;
	}

	public int getCategoria() {
		return categoria;
	}

	public Club getClubLocal() {
		return clubLocal;
	}

	public Club getClubVisitante() {
		return clubVisitante;
	}

	public Integer getGolesLocal() {
		return golesLocal;
	}

	public Integer getGolesVisitante() {
		return golesVisitante;
	}

	public Date getFechaPartido() {
		return fechaPartido;
	}

	public char getConvalidaLocal() {
		return convalidaLocal;
	}

	public char getConvalidaVisitante() {
		return convalidaVisitante;
	}

	public Campeonato getCampeonato() {
		return campeonato;
	}

	public List<Miembro> getJugadoresLocales() {
		return jugadoresLocales;
	}

	public List<Miembro> getJugadoresVisitantes() {
		return jugadoresVisitantes;
	}

	public void setNroFecha(int nroFecha) {
		this.nroFecha = nroFecha;
	}

	public void setNroZona(int nroZona) {
		this.nroZona = nroZona;
	}

	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}

	public void setClubLocal(Club clubLocal) {
		this.clubLocal = clubLocal;
	}

	public void setClubVisitante(Club clubVisitante) {
		this.clubVisitante = clubVisitante;
	}

	public void setFechaPartido(Date fechaPartido) {
		this.fechaPartido = fechaPartido;
	}

	public void setConvalidaLocal(char local) {
		this.convalidaLocal = local;
	}

	public void setConvalidaVisitante(char visitante) {
		this.convalidaVisitante = visitante;
	}
	
	public void incrementarGolLocal() {
		if (this.golesLocal == null) {
			this.golesLocal = 1;
		}
		else {
			this.golesLocal++;
		}
	}
	
	public void incrementarGolVisitante() {
		if (this.golesVisitante == null) {
			this.golesVisitante = 1;
		}
		else {
			this.golesVisitante++;
		}
	}

	public void agregarJugadoresLocales(Miembro miembro) {
		this.jugadoresLocales.add(miembro);
	}

	public void agregarJugadoresVisitantes(Miembro miembro) {
		this.jugadoresVisitantes.add(miembro);
	}
	
	public PartidoView toView() {
		return new PartidoView(idPartido, nroFecha, nroZona, categoria, golesLocal, golesVisitante, fechaPartido, convalidaLocal, convalidaVisitante);
	}
	
}
