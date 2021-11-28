package controlador;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import daos.CampeonatoDAO;
import daos.ClubDAO;
import daos.JugadorDAO;
import daos.MiembroDAO;
import daos.PartidoDAO;
import daos.RepresentanteDAO;
import daos.UsuarioDAO;
import exceptions.CampeonatoException;
import exceptions.ClubException;
import exceptions.JugadorException;
import exceptions.MiembroException;
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
import view.MiembroView;
import view.PartidoView;
import view.RepresentanteView;

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
	}
	
	public void agregarJugador(String documento, String nombre, int idClub, Date fechaNacimiento, String direccion, String mail, String telefono) throws ClubException {
		Club aux = ClubDAO.getInstancia().ObtenerClubPorId(idClub);
		Jugador miJugador = new Jugador(documento, nombre, aux, fechaNacimiento, direccion, mail, telefono);
		aux.agregarJugador(miJugador);
	}
	
	public void modificarJugador(int idJugador, String documento, String nombre, String direccion, String mail, String telefono) throws JugadorException {
		Jugador aux = JugadorDAO.getInstancia().ObtenerJugadorPorId(idJugador);
		aux.setDocumento(documento);
		aux.setNombre(nombre);
		aux.setDireccion(direccion);
		aux.setMail(mail);
		aux.setTelefono(telefono);
	}
	
	public void eliminarJugador(int idJugador) throws JugadorException {
		JugadorDAO.getInstancia().ObtenerJugadorPorId(idJugador).eliminar();
	}
	
	public void cambioJugadorDeClub(int idJugador, int idClubDestino) throws JugadorException, ClubException {
		Jugador j = JugadorDAO.getInstancia().ObtenerJugadorPorId(idJugador);
		Club cNuevo = ClubDAO.getInstancia().ObtenerClubPorId(idClubDestino);
		j.getClub().eliminarJugador(j);  
		cNuevo.agregarJugador(j);
		j.setClub(cNuevo);
	}
	
	public void crearCampeonato(String descripcion, Date fechaInicio, Date fechaFin, String estado) throws CampeonatoException {
		new Campeonato(descripcion, fechaInicio, fechaFin, estado);
	}
	
//	public void eliminarCampeonato(int idCampeonato) throws CampeonatoException { NO SE UTILIZA
//		CampeonatoDAO.getInstancia().EliminarTablaPosiciones(CampeonatoDAO.getInstancia().ObtenerCampeonatoPorId(idCampeonato));
//		CampeonatoDAO.getInstancia().ObtenerCampeonatoPorId(idCampeonato).eliminar();
//		
//	}
	
	public void modificarEstadoCampeonato(int idCampeonato, String estado) throws CampeonatoException {
		Campeonato modif = CampeonatoDAO.getInstancia().ObtenerCampeonatoPorId(idCampeonato);
		modif.setEstado(estado);
	}
	
	public void modificarRepresentante(int legajo, String documento, String nombre) throws RepresentanteException {
		Representante aux = RepresentanteDAO.getInstancia().ObtenerRepresentantePorId(legajo);
		aux.setDocumento(documento);
		aux.setNombre(nombre);
	}
	
	public void habilitarJugador(int idJugador) throws JugadorException { 
		Jugador j = JugadorDAO.getInstancia().ObtenerJugadorPorId(idJugador);
		j.setHabilitado("habilitado");
	}
	
	public void deshabilitarJugador(int idJugador) throws JugadorException {
		Jugador j = JugadorDAO.getInstancia().ObtenerJugadorPorId(idJugador);
		j.setHabilitado("deshabilitado");
	}
	
	
	public void agregarJugadorEnLista(int idPartido, int idJugador, int cantAmarillas) throws JugadorException, PartidoException { //No se necesita idClub ya que lo obtengo del jugador
		Jugador j = JugadorDAO.getInstancia().ObtenerJugadorPorId(idJugador);
		Partido p = PartidoDAO.getInstancia().ObtenerPartidoPorId(idPartido);
		if (j.getHabilitado().equalsIgnoreCase("habilitado") && j.getCategoria() >= p.getCategoria()) {
			if (JugadorDAO.getInstancia().ObtenerHabililtacion(p.getCampeonato(), j).equalsIgnoreCase("deshabilitado") || verificarHabilitacionPorFaltas(j, p, cantAmarillas)) {
				Miembro m = new Miembro(j.getClub(), p, j);
				if ((int)p.getClubLocal().getIdClub() == (int)j.getClub().getIdClub() && p.getJugadoresLocales().size() < 17) {
					p.agregarJugadoresLocales(m);
					m.grabar();
				}
				else if (p.getClubVisitante().getIdClub().intValue() == j.getClub().getIdClub().intValue() && p.getJugadoresVisitantes().size() < 17) {
					p.agregarJugadoresVisitantes(m);
					m.grabar(); 
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
		if ((int)p.getClubLocal().getIdClub() == (int)RepresentanteDAO.getInstancia().ObtenerRepresentantePorId(idRepresentante).getClub().getIdClub()) {
			p.setConvalidaLocal(validacion);
		}
		else if ((int)p.getClubVisitante().getIdClub() == (int)RepresentanteDAO.getInstancia().ObtenerRepresentantePorId(idRepresentante).getClub().getIdClub()) {
			p.setConvalidaVisitante(validacion);
		}
		PartidoDAO.getInstancia().actualizar(p);
		this.cargarEstadisticasPartido(idPartido);
	}
	
	public void InscribirClubACampeonato(int idClub, int idCampeonato) throws CampeonatoException, ClubException {
		Club cl = ClubDAO.getInstancia().ObtenerClubPorId(idClub);
		Campeonato ca = CampeonatoDAO.getInstancia().ObtenerCampeonatoPorId(idCampeonato);
		cl.participar(ca);
		ca.inscribirClub(cl);
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
	
	public List<JugadorView> ObtenerJugadoresDelClub(int idClub) throws ClubException {
		List<JugadorView> res = new ArrayList<JugadorView>();
		List<Jugador> aux = ClubDAO.getInstancia().ObtenerClubPorId(idClub).getJugadores();
		for (Jugador j: aux) {
			res.add(j.toView());
		}
		return res;
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
	
	public List<List<List<Double>>> ObtenerTablasPosicionesZonas(int idCampeonato) throws CampeonatoException{
		return CampeonatoDAO.getInstancia().ObtenerTablaPosicionesZonas(CampeonatoDAO.getInstancia().ObtenerCampeonatoPorId(idCampeonato), obtenerClubesPorZona(idCampeonato));
	}
	
	public List<CampeonatoView> ObtenerCampeonatos(){
		List<CampeonatoView> campsView = new ArrayList<CampeonatoView>();
		List<Campeonato> camps = CampeonatoDAO.getInstancia().ObtenerCampeonatos();
		for (Campeonato c: camps) {
			campsView.add(c.toView());
		}
		return campsView;
	}
	
	public List<ClubView> ObtenerClubes() {
		List<ClubView> clubsView = new ArrayList<ClubView>();
		List<Club> clubs = ClubDAO.getInstancia().ObtenerClubes();
		for (Club c: clubs) {
			clubsView.add(c.toView());
		}
		return clubsView;
	}
	
	public List<CampeonatoView> ObtenerCampeonatosDelJugador(int idJugador) throws JugadorException{
		List<CampeonatoView> campsView = new ArrayList<CampeonatoView>();
		List<Campeonato> camps = JugadorDAO.getInstancia().ObtenerJugadorPorId(idJugador).getClub().getParticipaciones();
		for (Campeonato c: camps) {
			campsView.add(c.toView());
		}
		return campsView;
	}
	
	public List<CampeonatoView> ObtenerCampeonatosDelRepresentante(int idRepresentante) throws RepresentanteException{
		List<CampeonatoView> campsView = new ArrayList<CampeonatoView>();
		List<Campeonato> camps = RepresentanteDAO.getInstancia().ObtenerRepresentantePorId(idRepresentante).getClub().getParticipaciones();
		for (Campeonato c: camps) {
			campsView.add(c.toView());
		}
		return campsView;
	}
	
	public void crearPartidoEliminatorias(int categoria, int idClubLocal, int idClubVisitante, Date fechaPartido, int idCampeonato) throws ClubException, CampeonatoException {
		if (this.validarPartido(fechaPartido, idClubLocal, idClubVisitante)) { //valido que un equipo no juegue 2 partidos en la misma fecha
			Partido p = new Partido(categoria, ClubDAO.getInstancia().ObtenerClubPorId(idClubLocal), ClubDAO.getInstancia().ObtenerClubPorId(idClubVisitante), fechaPartido, CampeonatoDAO.getInstancia().ObtenerCampeonatoPorId(idCampeonato));
			p.grabar();
		}
	}
	
	
	private Date sumarSemanas(Date fecha, int cantSemanas) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);
		cal.add(Calendar.DAY_OF_MONTH, 7*cantSemanas);
		Date res = cal.getTime();
		return res;
	}
	
	public void crearPartidos(int idCampeonato, int categoria) throws CampeonatoException {
		Campeonato c = CampeonatoDAO.getInstancia().ObtenerCampeonatoPorId(idCampeonato);
		List<Partido> misPartidos = new ArrayList<Partido>();
		List<Club> auxLocal = new ArrayList<Club>(); 
		List<Club> auxVisitante = new ArrayList<Club>();
		HashMap<Club, Integer> vecesLocal = new HashMap<Club, Integer>();
		HashMap<Club, Integer> vecesVisitante = new HashMap<Club, Integer>();
		for (int i = 0; i < Math.ceil(c.getInscriptos().size() / 2.0); i++) {
			auxLocal.add(c.getInscriptos().get(i));
			if (c.getInscriptos().size() % 2 == 1 && i == c.getInscriptos().size() / 2)
				auxVisitante.add(new Club(-1, "", ""));
			else
				auxVisitante.add(c.getInscriptos().get(c.getInscriptos().size() - i - 1));
		}
		int cantFechas;
		if (c.getInscriptos().size() % 2 == 0) {
			cantFechas = c.getInscriptos().size() - 1;
		}
		else {
			cantFechas = c.getInscriptos().size();
		}
		Club clubLocal, clubVisitante;
		Date fecha = c.getFechaInicio();
		for (int f = 1; f <= cantFechas; f++) {
			for (int i = 0; i < Math.ceil((c.getInscriptos().size() / 2.0)); i++) {
				clubLocal = auxLocal.get(i);
				clubVisitante = auxVisitante.get(i);
				if (clubLocal.getIdClub().intValue() != -1 && clubVisitante.getIdClub().intValue() != -1) {
					if (vecesLocal.getOrDefault(clubLocal, 0) >= (c.getInscriptos().size() / 2) || vecesVisitante.getOrDefault(clubVisitante, 0) >= (c.getInscriptos().size() / 2)) {
						clubLocal = auxVisitante.get(i);  //Invierte la localia de los equipos si uno supera el limite de partidos como local o visitante
						clubVisitante = auxLocal.get(i);
					}
					vecesLocal.put(clubLocal, vecesLocal.getOrDefault(clubLocal, 0) + 1);
					vecesVisitante.put(clubVisitante, vecesVisitante.getOrDefault(clubVisitante, 0) + 1);
					misPartidos.add(new Partido(f, categoria, clubLocal, clubVisitante, fecha, c));
				}
			}
			auxLocal.add(1, auxVisitante.get(0));
			auxVisitante.remove(0);
			auxVisitante.add(auxLocal.get(auxLocal.size() - 1));
			auxLocal.remove(auxLocal.size() - 1);
			fecha = sumarSemanas(fecha, 1);
		}
		List<Partido> partidosVuelta = new ArrayList<Partido>();
		int agregado;
		if (c.getInscriptos().size() % 2 == 0) {
			agregado = c.getInscriptos().size() - 1;
		}
		else {
			agregado = c.getInscriptos().size();
		}
		for (Partido p: misPartidos) {
			partidosVuelta.add(new Partido(p.getNroFecha() + agregado, categoria, p.getClubVisitante(), p.getClubLocal(), sumarSemanas(p.getFechaPartido(), agregado), c));
		}
		for (Partido p: partidosVuelta) {
			misPartidos.add(p);
		}
		for (Partido p: misPartidos) {
			p.grabar();
		}
	}
	
	public void crearPartidosZonas(int idCampeonato, int categoria, HashMap<Integer, List<Integer>> idClubes) throws CampeonatoException, ClubException {
		Campeonato c = CampeonatoDAO.getInstancia().ObtenerCampeonatoPorId(idCampeonato);
		CampeonatoDAO.getInstancia().generarTablasZonas(c, idClubes.size(), idClubes);
		List<Partido> misPartidos = new ArrayList<Partido>();
		List<Club> auxLocal, auxVisitante;
		HashMap<Club, Integer> vecesLocal, vecesVisitante;
		for (int i = 1; i <= idClubes.size(); i++) {
			auxLocal = new ArrayList<Club>();
			auxVisitante = new ArrayList<Club>();
			vecesLocal = new HashMap<Club, Integer>();
			vecesVisitante = new HashMap<Club, Integer>();
			for (int j = 0; j < Math.ceil(idClubes.get(i).size() / 2.0); j++) {
				auxLocal.add(ClubDAO.getInstancia().ObtenerClubPorId(idClubes.get(i).get(j)));
				if (idClubes.get(i).size() % 2 == 1 && j == idClubes.get(i).size() / 2)
					auxVisitante.add(new Club(-1, "", ""));
				else
					auxVisitante.add(ClubDAO.getInstancia().ObtenerClubPorId(idClubes.get(i).get(idClubes.get(i).size() - j - 1)));
			}
			int cantFechas;
			if (idClubes.get(i).size() % 2 == 0) {
				cantFechas = idClubes.get(i).size() - 1;
			}
			else {
				cantFechas = idClubes.get(i).size();
			}
			Club clubLocal, clubVisitante;
			Date fecha = c.getFechaInicio();
			for (int f = 1; f <= cantFechas; f++) {
				for (int j = 0; j < Math.ceil((idClubes.get(i).size() / 2.0)); j++) {
					clubLocal = auxLocal.get(j);
					clubVisitante = auxVisitante.get(j);
					if (clubLocal.getIdClub().intValue() != -1 && clubVisitante.getIdClub().intValue() != -1) {
						if (vecesLocal.getOrDefault(clubLocal, 0) >= (idClubes.get(i).size() / 2) || vecesVisitante.getOrDefault(clubVisitante, 0) >= (idClubes.get(i).size() / 2)) {
							clubLocal = auxVisitante.get(j);  //Invierte la localia de los equipos si uno supera el limite de partidos como local o visitante
							clubVisitante = auxLocal.get(j);
						}
						vecesLocal.put(clubLocal, vecesLocal.getOrDefault(clubLocal, 0) + 1);
						vecesVisitante.put(clubVisitante, vecesVisitante.getOrDefault(clubVisitante, 0) + 1);
						misPartidos.add(new Partido(f, i, categoria, clubLocal, clubVisitante, fecha, c));
					}
				}
				auxLocal.add(1, auxVisitante.get(0));
				auxVisitante.remove(0);
				auxVisitante.add(auxLocal.get(auxLocal.size() - 1));
				auxLocal.remove(auxLocal.size() - 1);
				fecha = sumarSemanas(fecha, 1);
			}
			List<Partido> partidosVuelta = new ArrayList<Partido>();
			int agregado;
			if (idClubes.get(i).size() % 2 == 0) {
				agregado = idClubes.get(i).size() - 1;
			}
			else {
				agregado = idClubes.get(i).size();
			}
			for (Partido p: misPartidos) {
				if (p.getNroZona() == i)
					partidosVuelta.add(new Partido(p.getNroFecha() + agregado, i, categoria, p.getClubVisitante(), p.getClubLocal(), sumarSemanas(p.getFechaPartido(), agregado), c));
			}
			for (Partido p: partidosVuelta) {
				misPartidos.add(p);
			}
		}
		for (Partido p: misPartidos) {
			p.grabar();
		}
		c.setTieneZonas('s');
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
		j.agregarGol(g);
		if (sentido.equalsIgnoreCase("a favor")) {
			if ((int)j.getClub().getIdClub() == (int)p.getClubLocal().getIdClub()) {
				p.incrementarGolLocal();
			}
			else if ((int)j.getClub().getIdClub() == (int)p.getClubVisitante().getIdClub()) {
				p.incrementarGolVisitante();
			}
		}
		else if (sentido.equalsIgnoreCase("en contra")) {
			if ((int)j.getClub().getIdClub() == (int)p.getClubLocal().getIdClub()) {
				p.incrementarGolVisitante();
			}
			else if ((int)j.getClub().getIdClub() == (int)p.getClubVisitante().getIdClub()) {
				p.incrementarGolLocal();
			}
		}
	}
	
	public void CargarFalta(int idJugador, int idPartido, int idCampeonato, int minuto, String tipo) throws PartidoException, JugadorException, CampeonatoException {
		Partido p = PartidoDAO.getInstancia().ObtenerPartidoPorId(idPartido);
		Jugador j = JugadorDAO.getInstancia().ObtenerJugadorPorId(idJugador);
		Campeonato c = CampeonatoDAO.getInstancia().ObtenerCampeonatoPorId(idCampeonato);
		Falta f = new Falta(j, p, c, minuto, tipo);
		j.agregarFalta(f);
	}
	
	public void terminarPartido(Integer idPartido) throws PartidoException {
		Partido p = PartidoDAO.getInstancia().ObtenerPartidoPorId(idPartido.intValue());
		p.setTerminado('S');
	}
	
	private void cargarEstadisticasPartido(int idPartido) throws CampeonatoException, PartidoException {
		Partido p = PartidoDAO.getInstancia().ObtenerPartidoPorId(idPartido);
		HashMap<Club, Integer> PuntosEquipos = new HashMap<Club, Integer>();
		if (p.getConvalidaLocal() == 'S' && p.getConvalidaVisitante() == 'S') {
			if(p.getGolesLocal().intValue() > p.getGolesVisitante().intValue()) { //gana el local
				PuntosEquipos.put(p.getClubLocal(), 3);
				PuntosEquipos.put(p.getClubVisitante(), 0);
			}
			else if(p.getGolesVisitante().intValue() > p.getGolesLocal().intValue()) { //gana el visitante
				PuntosEquipos.put(p.getClubVisitante(), 3);
				PuntosEquipos.put(p.getClubLocal(), 0);
			}
				
			else { //empatan
				PuntosEquipos.put(p.getClubLocal(), 1);
				PuntosEquipos.put(p.getClubVisitante(), 1);
			}
		}
		Campeonato c = CampeonatoDAO.getInstancia().ObtenerCampeonatoPorId(p.getCampeonato().getIdCampeonato());
		CampeonatoDAO.getInstancia().CargarPuntosEnTablaGeneral(PuntosEquipos, c, p);
		if (p.getNroZona() != 0) {
			CampeonatoDAO.getInstancia().CargarPuntosEnTablasZonas(PuntosEquipos, c, p);
		}
	}
	
	public List<ClubView> obtenerGanadoresEliminatorias(int idCampeonato){
		List<Club> clubes = new ArrayList<Club>();
		List<Partido> partidos = PartidoDAO.getInstancia().ObtenerPartidos();
		List<Partido> elim = new ArrayList<Partido>();
		for(Partido p: partidos) {
			if (p.getCampeonato().getIdCampeonato().intValue() != idCampeonato) {
				elim.add(p);
			}
		}
		for (Partido p: elim) {
			partidos.remove(p);
		}
		for (Partido p: partidos) {
			if (p.getNroFecha() == -1 && !clubes.contains(p.getClubLocal())) {
				clubes.add(p.getClubLocal());
			}
			if (p.getNroFecha() == -1 && !clubes.contains(p.getClubVisitante())) {
				clubes.add(p.getClubVisitante());
			}
		}
		for(Partido p: partidos) {
			if(p.getNroFecha() == -1) {
				System.out.println(p.getNroFecha());
				if(p.getGolesLocal().intValue()>p.getGolesVisitante().intValue()) {
					clubes.remove(p.getClubVisitante());
				}
				else if(p.getGolesVisitante().intValue()>p.getGolesLocal().intValue()){
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
	
	public CampeonatoView ObtenerDatosCampeonato(int idCampeonato) throws CampeonatoException {
		return CampeonatoDAO.getInstancia().ObtenerCampeonatoPorId(idCampeonato).toView();
	}
	
	public RepresentanteView ObtenerDatosRepresentante(int legajo) throws RepresentanteException {
		return RepresentanteDAO.getInstancia().ObtenerRepresentantePorId(legajo).toView();
	}
	
	public MiembroView ObtenerDatosMiembro(int idLista) throws MiembroException {
		return MiembroDAO.getInstancia().ObtenerMiembroPorId(idLista).toView();
	}
	
	public List<MiembroView> ObtenerLocalesPartido(int idPartido) throws PartidoException{
		List<MiembroView> res = new ArrayList<MiembroView>();
		List<Miembro> locales = PartidoDAO.getInstancia().ObtenerPartidoPorId(idPartido).getJugadoresLocales();
		for (Miembro m: locales) {
			res.add(m.toView());
		}
		return res;
	}
	
	public List<JugadorView> obtenerJugadoresPartido(Integer idPartido){
        return MiembroDAO.getInstancia().ObtenerJugadoresPartido(idPartido);
    }
	
	public List<JugadorView> obtenerJugadoresClubNoEnPartido(Integer idPartido, Integer idClub) throws ClubException{
        List<JugadorView> jugadoresPartido = this.obtenerJugadoresPartido(idPartido);
        List<JugadorView> jugadoresClub = this.ObtenerJugadoresDelClub(idClub);
        List<JugadorView> res = new ArrayList<JugadorView>();
        for(JugadorView jc : jugadoresClub) {
            for(JugadorView jp : jugadoresPartido) {
                if(jc.getIdJugador().intValue() == jp.getIdJugador().intValue()) {
                    res.add(jc);
                }
            }
        }
        for (JugadorView jj : res) {
            jugadoresClub.remove(jj);
        }
        return jugadoresClub;
    }
	
	public List<MiembroView> ObtenerVisitantesPartido(int idPartido) throws PartidoException{
		List<MiembroView> res = new ArrayList<MiembroView>();
		List<Miembro> visitantes = PartidoDAO.getInstancia().ObtenerPartidoPorId(idPartido).getJugadoresVisitantes();
		for (Miembro m: visitantes) {
			res.add(m.toView());
		}
		return res;
	}
	
	public List<PartidoView> ObtenerPartidosPorCampeonato(int idCampeonato) {
		List<Partido> partidos = PartidoDAO.getInstancia().ObtenerPartidos();
		List<Partido> partCamp = new ArrayList<Partido>();
		List<PartidoView> res = new ArrayList<PartidoView>();
		for (Partido p: partidos) {
			if (p.getCampeonato().getIdCampeonato().intValue() == idCampeonato)
				partCamp.add(p);
		}
		for (Partido p: partCamp) {
			res.add(p.toView());
		}
		return res;
	}
	
	public Integer ObtenerCantidadFechas(List<PartidoView> partidos) {
		Integer maxFecha = 0;
		for (PartidoView pv: partidos) {
			if (pv.getNroFecha() != null && pv.getNroFecha() > maxFecha) {
				maxFecha = pv.getNroFecha();
			}
		}
		return maxFecha;
	}
	
	public List<String> login(String usuario, String contraseña) {
		return UsuarioDAO.getInstancia().login(usuario, contraseña);
	}
	
	public Map<String, List<Integer>> obtenerDatosPartidoJugador(Integer idPartido, Integer idJugador) throws PartidoException, JugadorException {
		Jugador j = JugadorDAO.getInstancia().ObtenerJugadorPorId(idJugador.intValue());
		Map<String, List<Integer>> res = new HashMap<String, List<Integer>>();
		List<Integer> minGoles = new ArrayList<Integer>();
		List<Integer> minAmarillas = new ArrayList<Integer>();
		List<Integer> minRojas = new ArrayList<Integer>();
		for (Gol g: j.getGoles()) {
			if (g.getPartido().getIdPartido().compareTo(idPartido) == 0) {
				minGoles.add(g.getMinuto());
			}
		}
		res.put("Gol", minGoles);
		for (Falta f: j.getFaltas()) {
			if (f.getPartido().getIdPartido().compareTo(idPartido) == 0) {
				if (f.getTipo().equalsIgnoreCase("amarilla")) {
					minAmarillas.add(f.getMinuto());
				}
				else if (f.getTipo().equalsIgnoreCase("roja")) {
					minRojas.add(f.getMinuto());
				}
			}
		}
		res.put("Amarilla", minAmarillas);
		res.put("Roja", minRojas);
		return res;
	}
	
	private HashMap<Integer, List<Integer>> obtenerClubesPorZona(int idCampeonato) {
		HashMap<Integer, List<Integer>> res = new HashMap<Integer, List<Integer>>();
		List<Partido> aux = PartidoDAO.getInstancia().ObtenerPartidos();
		int cantZonas = 0;
		for (Partido p: aux) {
			if (p.getCampeonato().getIdCampeonato().intValue() == idCampeonato && p.getNroZona() > cantZonas) {
				cantZonas = p.getNroZona();
			}
		}
		for (int i = 1; i <= cantZonas; i++) {
			res.put(i, new ArrayList<Integer>());
		}
		for (Partido p: aux) {
			if (p.getCampeonato().getIdCampeonato().intValue() == idCampeonato) {
				List<Integer> clubes = res.get(p.getNroZona());
				clubes.add(p.getClubLocal().getIdClub());
				res.put(p.getNroZona(), clubes);
			}
		}
		return res;
	}
}
