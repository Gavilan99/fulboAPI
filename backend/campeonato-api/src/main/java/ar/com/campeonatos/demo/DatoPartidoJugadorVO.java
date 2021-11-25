package ar.com.campeonatos.demo;

public class DatoPartidoJugadorVO {

	private String apellido;
	private Integer minuto;
	private String tipo;
	
	
	public DatoPartidoJugadorVO(String apellido, Integer minuto, String tipo) {
		this.apellido = apellido;
		this.minuto = minuto;
		this.tipo = tipo;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public Integer getMinuto() {
		return minuto;
	}


	public void setMinuto(Integer minuto) {
		this.minuto = minuto;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
}
