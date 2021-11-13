package view;

import java.util.Date;
import java.util.List;

public class JugadorView {

	private Integer idJugador;
	private String tipoDocumento;
	private Integer numeroDocumento;
	private String nombre;
	private String apellido;
	private ClubView club;
	private Date fechaNacimiento;
	private Integer categoria;
	private String habilitado;
	private String direccion;
	private String mail;
	private String telefono;
	private List<GolView> goles;
	private List<FaltaView> faltas;
	
	
	
	public JugadorView(Integer idJugador, String tipoDocumento, Integer numeroDocumento, String nombre, String apellido,
			ClubView club, Date fechaNacimiento, Integer categoria, String habilitado, String direccion, String mail,
			String telefono, List<GolView> goles, List<FaltaView> faltas) {
		this.idJugador = idJugador;
		this.tipoDocumento = tipoDocumento;
		this.numeroDocumento = numeroDocumento;
		this.nombre = nombre;
		this.apellido = apellido;
		this.club = club;
		this.fechaNacimiento = fechaNacimiento;
		this.categoria = categoria;
		this.habilitado = habilitado;
		this.direccion = direccion;
		this.mail = mail;
		this.telefono = telefono;
		this.goles = goles;
		this.faltas = faltas;
	}
	public ClubView getClub() {
		return club;
	}
	public void setClub(ClubView club) {
		this.club = club;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public Integer getCategoria() {
		return categoria;
	}
	public void setCategoria(Integer categoria) {
		this.categoria = categoria;
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
	public List<GolView> getGoles() {
		return goles;
	}
	public void setGoles(List<GolView> goles) {
		this.goles = goles;
	}
	public List<FaltaView> getFaltas() {
		return faltas;
	}
	public void setFaltas(List<FaltaView> faltas) {
		this.faltas = faltas;
	}
	
	
	public Integer getIdJugador() {
		return idJugador;
	}
	public void setIdJugador(Integer idJugador) {
		this.idJugador = idJugador;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public Integer getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(Integer numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	@Override
	public String toString() {
		return "JugadorView [idJugador=" + idJugador + ", tipoDocumento=" + tipoDocumento + ", numeroDocumento="
				+ numeroDocumento + ", nombre=" + nombre + ", apellido=" + apellido + "]";
	}
}
