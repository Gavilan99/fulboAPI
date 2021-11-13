package modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name = "representantes")
public class Representante implements Comparable<Representante>{
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idRepresentante")
	private Integer legajo;
	private String documento;
	private String nombre;
	@ManyToOne()
	@JoinColumn(name = "idClub")
	private Club club;
	
	
	public Representante(String documento, String nombre, Club club) {
		this.legajo = null;
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
		this.documento = documento;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setClub(Club club) {
		this.club = club;
	}

	@Override
	public int compareTo(Representante o) {
		return this.documento.compareTo(o.getDocumento());
	}

	public Integer getLegajo() {
		return legajo;
	}
}
