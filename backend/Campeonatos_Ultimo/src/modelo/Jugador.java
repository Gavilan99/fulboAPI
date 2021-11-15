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

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import daos.JugadorDAO;
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
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Gol> goles;
	@OneToMany(mappedBy = "jugador", cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
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
        this.categoria = auxCategoria % 100;
        this.goles = new ArrayList<Gol>();
        this.faltas = new ArrayList<Falta>();
        JugadorDAO.getInstancia().grabar(this);
	}
	
	public Jugador() {}

	public Integer getIdJugador() {
		return idJugador;
	}


	public String getNombre() {
		return nombre + " " + apellido;
	}
	
	public void setNombre(String nombre) {
		if (!nombre.equals(" ")) {
			String[] nom = nombre.split(" ");
			this.nombre = nom[0];
			this.apellido = nom[1];
			this.actualizar();
		}
		
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	
	public String getDocumento() {
		return this.tipoDocumento + " " + this.numeroDocumento;
	}
	
	public void setDocumento(String documento) {
		if (documento != " ") {
			String[] doc = documento.split(" ");
			this.tipoDocumento = doc[0];
			this.numeroDocumento = Integer.parseInt(doc[1]);
			this.actualizar();
		}
	}

	public String getHabilitado() {
		return habilitado;
	}

	public void setHabilitado(String habilitado) {
		this.habilitado = habilitado;
		this.actualizar();
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		if (direccion != "") {
			this.direccion = direccion;
			this.actualizar();
		}
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		if (mail != "") {
			this.mail = mail;
			this.actualizar();
		}
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		if (telefono != "") {
			this.telefono = telefono;
			this.actualizar();
		}
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
		this.actualizar();
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
		JugadorView jv = new JugadorView(tipoDocumento, numeroDocumento, nombre, apellido, club.toView(), fechaNacimiento, categoria, habilitado, direccion, mail, telefono);
		jv.setIdJugador(idJugador);
		List<GolView> golesView = new ArrayList<GolView>();
		List<FaltaView> faltasView = new ArrayList<FaltaView>();
		for (Gol g: goles) {
			golesView.add(g.toViewJugador(jv));
		}
		for (Falta f: faltas) {
			faltasView.add(f.toViewJugador(jv));
		}
		jv.setGoles(golesView);
		jv.setFaltas(faltasView);
		return jv;
	}
	
	private void actualizar() {
		JugadorDAO.getInstancia().actualizar(this);
	}
	
	public void eliminar() {
		JugadorDAO.getInstancia().eliminar(this);
	}
	
}
