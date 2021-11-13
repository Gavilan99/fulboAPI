package modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import daos.ClubDAO;
import view.ClubView;

import javax.persistence.JoinColumn;

@Entity
@Table (name = "clubes")
public class Club implements Comparable<Club>{

	@Id 
	private Integer idClub;
	private String nombre;
	private String direccion;
	@OneToMany(mappedBy = "club", cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Representante> responsables;
	@OneToMany(mappedBy = "club", cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Jugador> jugadores;
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
	@JoinTable(name = "clubesCampeonato", joinColumns = {@JoinColumn(name = "idClub")}, inverseJoinColumns = {@JoinColumn(name = "idCampeonato")})
	private List<Campeonato> participaciones;
	
	public Club(int idClub, String nombre, String dirección) {
		this.idClub = idClub;
		this.nombre = nombre;
		this.direccion = dirección;
		jugadores = new ArrayList<Jugador>();
		responsables = new ArrayList<Representante>();
		participaciones = new ArrayList<Campeonato>();
	}
	
	public Club() {}
	
	public void asignarResponsable(Representante responsable) {
		responsables.add(responsable);
	}
	
	public void agregarJugador(Jugador jugador) {
		jugadores.add(jugador);
	}
	
	public void eliminarJugador(Jugador jugador) {
		jugadores.remove(jugador);
	}

	public Integer getIdClub() {
		return idClub;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDirección() {
		return direccion;
	}

	public List<Representante> getResponsable() {
		return responsables;
	}

	public List<Jugador> getJugadores() {
		return jugadores;
	}
	
	public List<Campeonato> getParticipaciones(){
		return participaciones;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
		this.actualizar();
	}

	@Override
	public int compareTo(Club o) {
		return this.getIdClub().compareTo(o.getIdClub());
	}
	
	public boolean participa(Campeonato campeonato) {
		return participaciones.contains(campeonato);
	}
	public void participar(Campeonato campeonato) {
		participaciones.add(campeonato);
		this.actualizar();
	}
	
	public void noParticipar(Campeonato campeonato) {
		participaciones.remove(campeonato);
	}
	
	public void agregarJugadoresToListaLocal(Jugador jugador, Partido partido) {
		partido.agregarJugadoresLocales(new Miembro(this, partido, jugador));
	}
	
	public void agregarJugadoresToListaVisitante(Jugador jugador, Partido partido) {
		partido.agregarJugadoresVisitantes(new Miembro(this, partido, jugador));
	}

	@Override
	public String toString() {
		return "Club [nombre=" + nombre + ", direccion=" + direccion + "]";
	}
	
	public ClubView toView() {
		return new ClubView(idClub, nombre, direccion);
	}

	public void setParticipaciones(List<Campeonato> participaciones) {
		this.participaciones = participaciones;
		this.actualizar();
	}
	
	private void actualizar() {
		ClubDAO.getInstancia().actualizar(this);
	}
}
