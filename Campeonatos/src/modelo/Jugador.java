package modelo;

import java.text.SimpleDateFormat;
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

import view.FaltaView;
import view.GolView;
import view.JugadorView;

@Entity
@Table(name = "jugadores")
public class Jugador implements Comparable<Jugador>{

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idJugador;
	private String tipoDocumento;
	private Integer numeroDocumento;
	private String nombre;
	private String apellido;
	@ManyToOne()
	@JoinColumn(name = "idClub")
	private Club club;
	@Column(name = "fechaNac")
	@Temporal(value = TemporalType.DATE)
	private Date fechaNacimiento;
	private Integer categoria;
	private String habilitado;
	private String direccion;
	private String mail;
	private String telefono;
	@OneToMany(mappedBy = "jugador", cascade = CascadeType.ALL)
	private List<Gol> goles;
	@OneToMany(mappedBy = "jugador", cascade = CascadeType.ALL)
	private List<Falta> faltas;
	
	public Jugador(String documento, String nombre, Club club, Date fechaNacimiento, String direccion, String mail, String telefono) {  
		this.idJugador = null;
		String[] doc = documento.split(" ");
		this.tipoDocumento = doc[0];
		this.numeroDocumento = Integer.parseInt(doc[1]);
		String[] nom = nombre.split(" ");
		this.nombre = nom[0];
		this.apellido = nom[1];
		this.club = club;
		this.fechaNacimiento = fechaNacimiento;
		this.direccion = direccion;
		this.mail = mail;
		this.telefono = telefono;
		this.habilitado = "habilitado";
		SimpleDateFormat getYearFormat = new SimpleDateFormat("yyyy");
		int auxCategoria = Integer.parseInt(getYearFormat.format(this.fechaNacimiento)) + 1900;
        if(auxCategoria > 1999)
        	this.categoria = auxCategoria - 1900;
        else
        	this.categoria = auxCategoria - 2000;
        this.goles = new ArrayList<Gol>();
        this.faltas = new ArrayList<Falta>();
	}
	
	public Jugador() {}

	public Integer getIdJugador() {
		return idJugador;
	}


	public String getNombre() {
		return nombre + " " + apellido;
	}
	
	public void setNombre(String nombre) {
		String[] nom = nombre.split(" ");
		this.nombre = nom[0];
		this.apellido = nom[1];
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	
	public String getDocumento() {
		return this.tipoDocumento + " " + this.numeroDocumento;
	}
	
	public void setDocumento(String documento) {
		String[] doc = documento.split(" ");
		this.tipoDocumento = doc[0];
		this.numeroDocumento = Integer.parseInt(doc[1]);
	}

	public String getHabilitado() {
		return habilitado;
	}

	public void setHabilitado(String habilitado) {
		this.habilitado = habilitado;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public int getCategoria() {
		return categoria;
	}

	public List<Gol> getGoles() {
		return goles;
	}

	public void agregarGol(Gol gol) {
		goles.add(gol);
	}
	
	public List<Falta> getFaltas() {
		return faltas;
	}
	
	public void agregarFalta(Falta falta) {
		faltas.add(falta);
	}

	
	public Club getClub() {
		return club;
	}

	public void setClub(Club club) {
		this.club = club;
	}
	
	

	@Override
	public int compareTo(Jugador o) {
		return this.getDocumento().compareTo(o.getDocumento());
	}

	@Override
	public String toString() {
		return "Jugador [idJugador=" + idJugador + ", tipoDocumento=" + tipoDocumento + ", numeroDocumento="
				+ numeroDocumento + ", nombre=" + nombre + ", apellido=" + apellido + "]";
	}
	
	public JugadorView toView() {
		List<GolView> golesView = new ArrayList<GolView>();
		List<FaltaView> faltasView = new ArrayList<FaltaView>();
		for (Gol g: goles) {
			golesView.add(g.toView());
		}
		for (Falta f: faltas) {
			faltasView.add(f.toView());
		}
		return new JugadorView(idJugador, tipoDocumento, numeroDocumento, nombre, apellido, club.toView(), fechaNacimiento, categoria, habilitado, direccion, mail, telefono, golesView, faltasView);
	}
	
}
