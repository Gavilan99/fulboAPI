package controlador;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import daos.CampeonatoDAO;
import daos.ClubDAO;
import daos.FaltaDAO;
import daos.GolDAO;
import daos.JugadorDAO;
import daos.MiembroDAO;
import daos.PartidoDAO;
import daos.RepresentanteDAO;
import exceptions.CampeonatoException;
import exceptions.ClubException;
import exceptions.JugadorException;
import exceptions.PartidoException;
import exceptions.RepresentanteException;
import modelo.Campeonato;
import modelo.Club;
import modelo.Falta;
import modelo.Gol;
import modelo.Jugador;
import modelo.Miembro;
import modelo.Partido;
import modelo.Representante;
import view.CampeonatoView;
import view.ClubView;
import view.JugadorView;
import view.PartidoView;

public class Controlador {

	private static Controlador instancia;
	
	private Controlador() { }
	
	public static Controlador getInstancia() {
		if (instancia == null) {
			instancia = new Controlador();
		}
		return instancia;
	}
	
	/*
	 * Notas:
	 * 
	 * -Pueden si lo desean convertir el controlador en un Singleton
	 * 
	 * -Deberan completar los metodos del controlador para que cumplan con los requerimientos
	 *  del trabajo, Recuerden siempre aplicar los patrones GRASP para verificar la correcta 
	 *  asignacion de lasresponsabilidades
	 *  
	 * -En la segunda parate del trabajo deber'an agragar los metodos y controles que 
	 *  considen necesarios. */
	
	public void modificarClub(String nombre, String direccion) throws ClubException { 
		Club aux = ClubDAO.getInstancia().ObtenerClubPorNombre(nombre);
		aux.setDireccion(direccion);
		ClubDAO.getInstancia().actualizar(aux);
	}
	
	public void agregarJugador(String documento, String nombre, int idClub, Date fechaNacimiento, String direccion, String mail, String telefono) throws ClubException {
		Club aux = ClubDAO.getInstancia().ObtenerClubPorId(idClub);
		Jugador miJugador = new Jugador(documento, nombre, aux, fechaNacimiento, direccion, mail, telefono);
		JugadorDAO.getInstancia().grabar(miJugador);
		aux.agregarJugador(miJugador);
	}
	
	public void modificarJugador(int idJugador, String documento, String nombre, String direccion, String mail, String telefono) throws JugadorException {
		Jugador aux = JugadorDAO.getInstancia().ObtenerJugadorPorId(idJugador);
		if (documento != "") {
			aux.setDocumento(documento);
		}
		if (nombre != "") {
			aux.setNombre(nombre);
		}
		if (direccion != "") {
			aux.setDireccion(direccion);
		}
		if (mail != "") {
			aux.setMail(mail);
		}
		if (telefono != "") {
			aux.setTelefono(telefono);
		}
		JugadorDAO.getInstancia().actualizar(aux);
	}
	
	public void eliminarJugador(int idJugador) throws JugadorException {
		JugadorDAO.getInstancia().eliminar(JugadorDAO.getInstancia().ObtenerJugadorPorId(idJugador));
	}
	
	public void cambioJugadorDeClub(int idJugador, int idClubDestino) throws JugadorException, ClubException {
		Jugador j = JugadorDAO.getInstancia().ObtenerJugadorPorId(idJugador);
		Club cNuevo = ClubDAO.getInstancia().ObtenerClubPorId(idClubDestino);
		j.getClub().eliminarJugador(j);  //No se puede sacar. Failed to laizily initialize...
		cNuevo.agregarJugador(j);
		j.setClub(cNuevo);
		JugadorDAO.getInstancia().actualizar(j);
	}
	
	public void crearCampeonato(String descripcion, Date fechaInicio, Date fechaFin, String estado) {
		CampeonatoDAO.getInstancia().grabar(new Campeonato(descripcion, fechaInicio, fechaFin, estado));
	}
	
	public void eliminarCampeonato(int idCampeonato) throws CampeonatoException {
		CampeonatoDAO.getInstancia().eliminar(CampeonatoDAO.getInstancia().ObtenerCampeonatoPorId(idCampeonato));
	}
	
	public void modificarEstadoCampeonato(int idCampeonato, String estado) throws CampeonatoException {
		Campeonato modif = CampeonatoDAO.getInstancia().ObtenerCampeonatoPorId(idCampeonato);
		modif.setEstado(estado);
		CampeonatoDAO.getInstancia().actualizar(modif);
	}
	
	public void modificarRepresentante(int legajo, String documento, String nombre) throws RepresentanteException {
		Representante aux = RepresentanteDAO.getInstancia().ObtenerRepresentantePorId(legajo);
		if (documento != "") {
			aux.setDocumento(documento);
		}
		if (nombre != "") {
			aux.setNombre(nombre);
		}
		RepresentanteDAO.getInstancia().actualizar(aux);
	}
	
	public void habilitarJugador(int idJugador, int idClub, int idCampeonato) {  //DESPUES LO VEMOS
		
	}
	
	/*public void crearListaJugadores() {    No se usa
		
	}*/	
	
	public void agregarJugadorEnLista(int idPartido, int idJugador, int cantAmarillas) throws JugadorException, PartidoException { //No se necesita idClub ya que lo obtengo del jugador
		Jugador j = JugadorDAO.getInstancia().ObtenerJugadorPorId(idJugador);
		Partido p = PartidoDAO.getInstancia().ObtenerPartidoPorId(idPartido);
		if (j.getHabilitado().equalsIgnoreCase("habilitado") && j.getCategoria() <= p.getCategoria()) {
			if (JugadorDAO.getInstancia().ObtenerHabililtacion(p.getCampeonato(), j).equalsIgnoreCase("deshabilitado") || verificarHabilitacionPorFaltas(j, p, cantAmarillas)) {
				Miembro m = new Miembro(j.getClub(), p, j);
				if (p.getClubLocal().equals(j.getClub()) && p.getJugadoresLocales().size() < 17) {
					p.agregarJugadoresLocales(m);
					MiembroDAO.getInstancia().grabar(m);
				}
				else if (p.getClubVisitante().equals(j.getClub()) && p.getJugadoresVisitantes().size() < 17) {
					p.agregarJugadoresVisitantes(m);
					MiembroDAO.getInstancia().grabar(m);
				}
				JugadorDAO.getInstancia().ModificarHabilitacion(p.getCampeonato(), j, "habilitado");
			}
			else {
				JugadorDAO.getInstancia().ModificarHabilitacion(p.getCampeonato(), j, "deshabilitado");
			}
		}
	}
	
	private boolean verificarHabilitacionPorFaltas(Jugador j, Partido p, int cantAmarillas) {
		int totalAmarillas = 0;
		boolean esAmarillaAnterior = false;
		List<Falta> faltas = j.getFaltas();
		for (int i = faltas.size() - 1; i >= 0; i--) {
			if (faltas.get(i).getCampeonato().equals(p.getCampeonato())) {
				if (faltas.get(i).getTipo().equalsIgnoreCase("roja") && faltas.get(i).getPartido().getNroFecha() + 1 == p.getNroFecha()) {
					return false;
				}
				else if (faltas.get(i).getTipo().equalsIgnoreCase("amarilla")) {
					totalAmarillas++;
					if (faltas.get(i).getPartido().getNroFecha() + 1 == p.getNroFecha()) {
						esAmarillaAnterior = true;
					}
				}
			}
		}
		if (esAmarillaAnterior && totalAmarillas % cantAmarillas == 0) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public void validarResultado(int idPartido, int idReprenstante, char validacion) throws PartidoException, RepresentanteException {
		Partido p = PartidoDAO.getInstancia().ObtenerPartidoPorId(idPartido);
		if (p.getClubLocal().equals(RepresentanteDAO.getInstancia().ObtenerRepresentantePorId(idReprenstante).getClub())) {
			p.setConvalidaLocal(validacion);
		}
		else if (p.getClubVisitante().equals(RepresentanteDAO.getInstancia().ObtenerRepresentantePorId(idReprenstante).getClub())) {
			p.setConvalidaVisitante(validacion);
		}
		PartidoDAO.getInstancia().actualizar(p);
	}
	
	public void InscribirClubACampeonato(int idClub, int idCampeonato) throws CampeonatoException, ClubException {
		Club cl = ClubDAO.getInstancia().ObtenerClubPorId(idClub);
		Campeonato ca = CampeonatoDAO.getInstancia().ObtenerCampeonatoPorId(idCampeonato);
		cl.participar(ca);
		ca.inscribirClub(cl);
		ClubDAO.getInstancia().actualizar(cl);
		CampeonatoDAO.getInstancia().AgregarClubTabla(ca, cl);
		for (Jugador j: cl.getJugadores()) {
			JugadorDAO.getInstancia().AgregarHabilitacion(ca, j);
		}
	}
	
	public void DesinscribirClubACampeonato(int idClub, int idCampeonato) throws CampeonatoException, ClubException {
		Club cl = ClubDAO.getInstancia().ObtenerClubPorId(idClub);
		Campeonato ca = CampeonatoDAO.getInstancia().ObtenerCampeonatoPorId(idCampeonato);
		cl.noParticipar(ca);
		ca.desinscribirClub(cl);
		ClubDAO.getInstancia().eliminarInscripcionCampeonato(cl, ca);
		for (Jugador j: cl.getJugadores()) {
			JugadorDAO.getInstancia().EliminarHabilitacion(ca, j);
		}
	}
	
	public ClubView ObtenerClubDelJugador(int idJugador) throws JugadorException {
		return JugadorDAO.getInstancia().ObtenerJugadorPorId(idJugador).getClub().toView();
	}
	
	public JugadorView ObtenerDatosJugador(int idJugador) throws JugadorException {
		return JugadorDAO.getInstancia().ObtenerJugadorPorId(idJugador).toView();
	}
	
	public PartidoView ObtenerDatosPartido(int idPartido) throws PartidoException {
		return PartidoDAO.getInstancia().ObtenerPartidoPorId(idPartido).toView();
	}
	
	public List<List<Double>> ObtenerTablaPosiciones(int idCampeonato) throws CampeonatoException {
		return CampeonatoDAO.getInstancia().ObtenerTablaPosiciones(CampeonatoDAO.getInstancia().ObtenerCampeonatoPorId(idCampeonato));
	}
	
	public List<CampeonatoView> ObtenerCampeonatosDelJugador(int idJugador) throws JugadorException{
		List<CampeonatoView> campsView = new ArrayList<CampeonatoView>();
		List<Campeonato> camps = JugadorDAO.getInstancia().ObtenerJugadorPorId(idJugador).getClub().getParticipaciones();
		for (Campeonato c: camps) {
			campsView.add(c.toView());
		}
		return campsView;
	}
	
	public void crearPartido(int nroFecha, int nroZona, int categoria, int idClubLocal, int idClubVisitante, Date fechaPartido, int idCampeonato) throws ClubException, CampeonatoException {
		PartidoDAO.getInstancia().grabar(new Partido(nroFecha, nroZona, categoria, ClubDAO.getInstancia().ObtenerClubPorId(idClubLocal), ClubDAO.getInstancia().ObtenerClubPorId(idClubVisitante), fechaPartido, CampeonatoDAO.getInstancia().ObtenerCampeonatoPorId(idCampeonato)));
	}
	
	public void CargarGol(int idPartido, int idJugador, int minuto, String sentido) throws PartidoException, JugadorException {
		Partido p = PartidoDAO.getInstancia().ObtenerPartidoPorId(idPartido);
		Jugador j = JugadorDAO.getInstancia().ObtenerJugadorPorId(idJugador);
		Gol g = new Gol(j, p, minuto, sentido);
		GolDAO.getInstancia().grabar(g);
		j.agregarGol(g);
		if (j.getClub().equals(p.getClubLocal())) {
			p.incrementarGolLocal();
		}
		else if (j.getClub().equals(p.getClubVisitante())) {
			p.incrementarGolVisitante();
		}
		PartidoDAO.getInstancia().actualizar(p);
	}
	
	public void CargarFalta(int idJugador, int idPartido, int idCampeonato, int minuto, String tipo) throws PartidoException, JugadorException, CampeonatoException {
		Partido p = PartidoDAO.getInstancia().ObtenerPartidoPorId(idPartido);
		Jugador j = JugadorDAO.getInstancia().ObtenerJugadorPorId(idJugador);
		Campeonato c = CampeonatoDAO.getInstancia().ObtenerCampeonatoPorId(idCampeonato);
		Falta f = new Falta(j, p, c, minuto, tipo);
		FaltaDAO.getInstancia().grabar(f);
		j.agregarFalta(f);
	}
	
}
