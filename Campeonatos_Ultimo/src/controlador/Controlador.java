package controlador;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
		j.getClub().eliminarJugador(j);  
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
	
	public void habilitarJugador(int idJugador) throws JugadorException { 
		Jugador j = JugadorDAO.getInstancia().ObtenerJugadorPorId(idJugador);
		j.setHabilitado("habilitado");
		JugadorDAO.getInstancia().actualizar(j);
	}
	
	
	public void agregarJugadorEnLista(int idPartido, int idJugador, int cantAmarillas) throws JugadorException, PartidoException { //No se necesita idClub ya que lo obtengo del jugador
		Jugador j = JugadorDAO.getInstancia().ObtenerJugadorPorId(idJugador);
		Partido p = PartidoDAO.getInstancia().ObtenerPartidoPorId(idPartido);
		System.out.println("rimordial");
		if (j.getHabilitado().equalsIgnoreCase("habilitado") && j.getCategoria() >= p.getCategoria()) {
			System.out.println("holaPrimordial0");
			if (JugadorDAO.getInstancia().ObtenerHabililtacion(p.getCampeonato(), j).equalsIgnoreCase("deshabilitado") || verificarHabilitacionPorFaltas(j, p, cantAmarillas)) {
				Miembro m = new Miembro(j.getClub(), p, j);
				if ((int)p.getClubLocal().getIdClub() == (int)j.getClub().getIdClub() && p.getJugadoresLocales().size() < 17) {
					
					p.agregarJugadoresLocales(m);
					System.out.println("hola");
					MiembroDAO.getInstancia().grabar(m);
				}
				else if (p.getClubVisitante().equals(j.getClub()) && p.getJugadoresVisitantes().size() < 17) {
					p.agregarJugadoresVisitantes(m);
					MiembroDAO.getInstancia().grabar(m);
					System.out.println("hola visitatw"); 
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
	
	public void validarResultado(int idPartido, int idRepresentante, char validacion) throws PartidoException, RepresentanteException, CampeonatoException {
		Partido p = PartidoDAO.getInstancia().ObtenerPartidoPorId(idPartido);
		if (p.getClubLocal().equals(RepresentanteDAO.getInstancia().ObtenerRepresentantePorId(idRepresentante).getClub())) {
			p.setConvalidaLocal(validacion);
		}
		else if (p.getClubVisitante().equals(RepresentanteDAO.getInstancia().ObtenerRepresentantePorId(idRepresentante).getClub())) {
			p.setConvalidaVisitante(validacion);
		}
		PartidoDAO.getInstancia().actualizar(p);
		this.calcularPuntosCampeonato(p.getCampeonato().getIdCampeonato());
		
		
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
		if(this.validarPartido(fechaPartido, idClubLocal, idClubVisitante)) { //valido que un equipo no juegue 2 partidos en la misma fecha
		PartidoDAO.getInstancia().grabar(new Partido(nroFecha, nroZona, categoria, ClubDAO.getInstancia().ObtenerClubPorId(idClubLocal), ClubDAO.getInstancia().ObtenerClubPorId(idClubVisitante), fechaPartido, CampeonatoDAO.getInstancia().ObtenerCampeonatoPorId(idCampeonato)));
		}
	}
	
	private boolean validarPartido(Date fechaPartido, int idClubLocal, int idCLubVisitante) {
		List<Partido> partidos = PartidoDAO.getInstancia().ObtenerPartidos();
		for (Partido p:partidos) {
			
			if (p.getFechaPartido().compareTo(fechaPartido) == 0 && (p.getClubVisitante().getIdClub() == idCLubVisitante || p.getClubLocal().getIdClub() == idClubLocal || p.getClubVisitante().getIdClub() == idClubLocal || p.getClubLocal().getIdClub() == idCLubVisitante)) {
				return false;
			}
		}
		return true;
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
	
	public void calcularPuntosCampeonato(int idCampeonato) throws CampeonatoException {
		HashMap<Club,Integer> PuntosEquipos = new HashMap<Club,Integer>(); 
		List <Partido> partidos = PartidoDAO.getInstancia().ObtenerPartidos();
		for(Partido p: partidos) {
			if(idCampeonato == p.getCampeonato().getIdCampeonato() && p.getConvalidaLocal() == 'S' && p.getConvalidaVisitante() == 'S') {
				/*PuntosEquipos.putIfAbsent(p.getClubLocal(), 0);
				PuntosEquipos.putIfAbsent(p.getClubVisitante(), 0);*/
				
				if(p.getGolesLocal() > p.getGolesVisitante()) { //gana el local
					PuntosEquipos.put(p.getClubLocal(), PuntosEquipos.getOrDefault(p.getClubLocal(), 0) + 3);
				}
				else if(p.getGolesVisitante() > p.getGolesLocal()) { //gana el visitante
					PuntosEquipos.put(p.getClubVisitante(), PuntosEquipos.getOrDefault(p.getClubVisitante(), 0) + 3);
				}
				
				else { //empatan
					PuntosEquipos.put(p.getClubLocal(), PuntosEquipos.getOrDefault(p.getClubLocal(), 0) + 1);
					PuntosEquipos.put(p.getClubVisitante(), PuntosEquipos.getOrDefault(p.getClubVisitante(), 0) + 1);
				}
			}
			
		}
		
		Campeonato c = CampeonatoDAO.getInstancia().ObtenerCampeonatoPorId(idCampeonato);
		CampeonatoDAO.getInstancia().CargarPuntosEnTabla(PuntosEquipos, c);

	}
	
	public List<ClubView> obtenerGanadores(int idCampeonato){
		List<Club> clubes = ClubDAO.getInstancia().ObtenerClubes();
		List<Partido> partidos = PartidoDAO.getInstancia().ObtenerPartidos();
		
		for(Partido p: partidos) {
			if(p.getCampeonato().getIdCampeonato() == idCampeonato) {
				if(p.getGolesLocal()>p.getGolesVisitante()) {
					clubes.remove(p.getClubVisitante());
				}
				else if(p.getGolesVisitante()>p.getGolesLocal()){
					clubes.remove(p.getClubLocal());
				}
			}
		}	
		List <ClubView> clviews = new ArrayList <ClubView>();	
		for(Club c:clubes) {
			clviews.add(c.toView());
		}
		return clviews;
		}
	
	/*public void generarPartidosTodosContraTodos(int idCampeonato) throws CampeonatoException, ClubException {
		Campeonato ca = CampeonatoDAO.getInstancia().ObtenerCampeonatoPorId(idCampeonato);
		List<Club> clubes = ca.getInscriptos();
		for (int i=0; i<clubes.size()-1;i++) {
			for (int j=i+1;j<clubes.size();j++) {
				this.crearPartido(i+1, -1, categoria, clubes.indexOf(i), clubes.indexOf(j), new Date(), idCampeonato);
				this.crearPartido(, -1, categoria, clubes.indexOf(i), clubes.indexOf(j), new Date(), idCampeonato);
			}
		}
		
	}*/
	
	
	
	
	
}
