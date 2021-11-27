package ar.com.campeonatos.demo;

import java.io.Serializable;

public class UsuarioVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6307533846154380070L;
	private String usuario;
	private String contraseña;
	private String rol;
	private Integer idRol;
	
	public UsuarioVO(String usuario, String contraseña, String rol, Integer idRol) {
		this.usuario = usuario;
		this.contraseña = contraseña;
		this.rol = rol;
		this.idRol = idRol;
	}
	
	public UsuarioVO() {
		this.usuario = "invalido";
	}
	
	public Integer getIdRol() {
		return idRol;
	}
	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	public String getContraseña() {
		return contraseña;
	}
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	
}
