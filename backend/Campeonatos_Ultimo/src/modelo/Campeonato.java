package modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import view.CampeonatoView;
import view.ClubView;

@Entity
@Table (name = "campeonatos")
public class Campeonato implements Comparable<Campeonato>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCampeonato;
	private String descripcion;
	@Temporal(value = TemporalType.DATE)
	private Date fechaInicio;
	@Temporal(value = TemporalType.DATE)
	private Date fechaFin;
	private String estado;
	@ManyToMany(mappedBy = "participaciones", fetch = FetchType.EAGER)
	private List<Club> inscriptos;
	
	public Campeonato(String descripcion, Date fechaInicio, Date fechaFin, String estado) {
		this.idCampeonato = null;
		this.descripcion = descripcion;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.estado = estado;
	}
	
	public Campeonato() {}

	public Integer getIdCampeonato() {
		return idCampeonato;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public List<Club> getInscriptos(){
		return inscriptos;
	}

	@Override
	public int compareTo(Campeonato o) {
		return this.getIdCampeonato().compareTo(o.getIdCampeonato());
	}
	
	public void inscribirClub(Club club) {
		inscriptos.add(club);
		if(!club.participa(this))
			club.participa(this);
	}
	
	public void desinscribirClub(Club club) {
		inscriptos.remove(club);
	}
	
	public String toString() {
		return descripcion + " : " + estado;
	}
	
	public CampeonatoView toView() {
		List<ClubView> clubesView = new ArrayList<ClubView>();
		for (Club c: inscriptos) {
			clubesView.add(c.toView());
		}
		return new CampeonatoView(idCampeonato, descripcion, fechaInicio, fechaFin, estado, clubesView);
	}
}
