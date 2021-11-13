package modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import daos.RepresentanteDAO;
import view.RepresentanteView;

@Entity
@Table (name = "representantes")
public class Representante implements Comparable<Representante>{
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	//@Column(name = "idRepresentante")
	private Integer idRepresentante;
	private String documento;
	private String nombre;
	@ManyToOne()
	@JoinColumn(name = "idClub")
	private Club club;
	
	
	public Representante(String documento, String nombre, Club club) {
		this.idRepresentante = null;
		this.documento = documento;
		this.nombre = nombre;
		this.club = club;
	}
	
	public Representante() {}

	public String getDocumento() {
		return documento;
	}

	public String getNombre() {
		return nombre;
	}

	public Club getClub() {
		return club;
	}
	
	public void setDocumento(String documento) {
		if (documento != "") {
			this.documento = documento;
			this.actualizar();
		}
	}
	
	public void setNombre(String nombre) {
		if (nombre != "") {
			this.nombre = nombre;
			this.actualizar();
		}
	}

	public void setClub(Club club) {
		this.club = club;
		this.actualizar();
	}
	
	public RepresentanteView toView() {
		RepresentanteView rv = new RepresentanteView(documento, nombre, club.toView());
		rv.setLegajo(idRepresentante);
		return rv;
	}

	@Override
	public int compareTo(Representante o) {
		return this.documento.compareTo(o.getDocumento());
	}

	public Integer getLegajo() {
		return idRepresentante;
	}
	
	private void actualizar() {
		RepresentanteDAO.getInstancia().actualizar(this);
	}
}
