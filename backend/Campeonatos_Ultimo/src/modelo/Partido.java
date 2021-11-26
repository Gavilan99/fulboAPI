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

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import daos.PartidoDAO;
import view.MiembroView;
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
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name = "idCampeonato")
	private Campeonato campeonato;
	@OneToMany(mappedBy = "partido", cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Miembro> jugadoresLocales;
	@OneToMany(mappedBy = "partido", cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Miembro> jugadoresVisitantes;

	public Partido(int nroFecha, int nroZona, int categoria, Club clubLocal, Club clubVisitante, 
			       Date fechaPartido, Campeonato campeonato) {
		this.nroFecha = nroFecha;
		this.nroZona = nroZona;
		this.categoria = categoria;
		this.clubLocal = clubLocal;
		this.clubVisitante = clubVisitante;
		this.golesLocal = 0;
		this.golesVisitante = 0;
		this.fechaPartido = fechaPartido;
		this.convalidaLocal = 'N';
		this.convalidaVisitante = 'N';
		this.campeonato = campeonato;
		jugadoresLocales = new ArrayList<Miembro>();
		jugadoresVisitantes = new ArrayList<Miembro>();
	}
	
	public Partido(int nroFecha, int categoria, Club clubLocal, Club clubVisitante, 
		       Date fechaPartido, Campeonato campeonato) {
	this.nroFecha = nroFecha;
	this.nroZona = null;
	this.categoria = categoria;
	this.clubLocal = clubLocal;
	this.clubVisitante = clubVisitante;
	this.golesLocal = 0;
	this.golesVisitante = 0;
	this.fechaPartido = fechaPartido;
	this.convalidaLocal = 'N';
	this.convalidaVisitante = 'N';
	this.campeonato = campeonato;
	jugadoresLocales = new ArrayList<Miembro>();
	jugadoresVisitantes = new ArrayList<Miembro>();
}
	
	public Partido(int categoria, Club clubLocal, Club clubVisitante, 
		       Date fechaPartido, Campeonato campeonato) {
	this.nroFecha = null;
	this.nroZona = null;
	this.categoria = categoria;
	this.clubLocal = clubLocal;
	this.clubVisitante = clubVisitante;
	this.golesLocal = 0;
	this.golesVisitante = 0;
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
		try {
			return nroFecha;
		}
		catch (Exception e) {
			return -1;
		}
	}

	public int getNroZona() {
		try {
			return nroZona;
		}
		catch (Exception e) {
			return 0;
		}
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
	
	

	public void setGolesLocal(Integer golesLocal) {
		this.golesLocal = golesLocal;
	}

	public void setGolesVisitante(Integer golesVisitante) {
		this.golesVisitante = golesVisitante;
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
		this.actualizar();
	}

	public void setNroZona(int nroZona) {
		this.nroZona = nroZona;
		this.actualizar();
	}

	public void setCategoria(int categoria) {
		this.categoria = categoria;
		this.actualizar();
	}

	public void setClubLocal(Club clubLocal) {
		this.clubLocal = clubLocal;
		this.actualizar();
	}

	public void setClubVisitante(Club clubVisitante) {
		this.clubVisitante = clubVisitante;
		this.actualizar();
	}

	public void setFechaPartido(Date fechaPartido) {
		this.fechaPartido = fechaPartido;
		this.actualizar();
	}

	public void setConvalidaLocal(char local) {
		this.convalidaLocal = local;
		this.actualizar();
	}

	public void setConvalidaVisitante(char visitante) {
		this.convalidaVisitante = visitante;
		this.actualizar();
	}
	
	public void setJugadoresLocales(List<Miembro> jugadores) {
		this.jugadoresLocales = jugadores;
		this.actualizar();
	}
	
	public void setJugadoresVisitantes(List<Miembro> jugadores) {
		this.jugadoresVisitantes = jugadores;
		this.actualizar();
	}
	
	public void incrementarGolLocal() {
		try {
			this.golesLocal++;
		}
		catch (NullPointerException e) {
			this.golesLocal = 1;
		}
		this.actualizar();
	}
	
	public void incrementarGolVisitante() {
		try {
			this.golesVisitante++;
		}
		catch (NullPointerException e) {
			this.golesVisitante = 1;
		}
		this.actualizar();
	}

	public void agregarJugadoresLocales(Miembro miembro) {
		this.jugadoresLocales.add(miembro);
	}

	public void agregarJugadoresVisitantes(Miembro miembro) {
		this.jugadoresVisitantes.add(miembro);
	}
	
	public PartidoView toView() {
		List<MiembroView> jlv = new ArrayList<MiembroView>();
		List<MiembroView> jvv = new ArrayList<MiembroView>();
		for (Miembro jl: jugadoresLocales) {
			jlv.add(jl.toView());
		}
		for (Miembro jv: jugadoresVisitantes) {
			jvv.add(jv.toView());
		}
		PartidoView pv = new PartidoView(nroFecha, nroZona, categoria, golesLocal, golesVisitante, fechaPartido, convalidaLocal, convalidaVisitante, clubLocal.toView(), clubVisitante.toView(), jlv, jvv, campeonato.getIdCampeonato());
		pv.setIdPartido(idPartido);
		return pv;
	}
	
	public void grabar() {
		PartidoDAO.getInstancia().grabar(this);
	}
	
	private void actualizar() {
		PartidoDAO.getInstancia().actualizar(this);
	}
	
}
