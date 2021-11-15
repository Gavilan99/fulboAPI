package ar.com.campeonatos.demo;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import controlador.Controlador;
import exceptions.CampeonatoException;
import exceptions.ClubException;
import exceptions.JugadorException;
import exceptions.MiembroException;
import exceptions.PartidoException;
import exceptions.RepresentanteException;
import view.CampeonatoView;
import view.ClubView;
import view.FaltaView;
import view.GolView;
import view.JugadorView;
import view.MiembroView;
import view.PartidoView;
import view.RepresentanteView;

@org.springframework.web.bind.annotation.RestController
public class RestController {

	@GetMapping("/getCampeonato")
	@CrossOrigin(origins = "http://localhost:3000") 
	public CampeonatoView getCampeonato(@RequestParam(name="idCampeonato") Integer idCampeonato) { //ANDO
		try {
			return Controlador.getInstancia().ObtenerDatosCampeonato(idCampeonato);
		} catch (CampeonatoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping("/getCampeonatos")
	@CrossOrigin(origins = "http://localhost:3000") 
	public List<CampeonatoView> getCampeonatos() { //ANDO
			return Controlador.getInstancia().ObtenerDatosCampeonatos();
	}

	@GetMapping("/getCampeonatosJugador")
	@CrossOrigin(origins = "http://localhost:3000") 
	public List<CampeonatoView> getCampeonatosJugador(@RequestParam(name="idJugador") Integer idJugador) { //ANDO
		try {
			return Controlador.getInstancia().ObtenerCampeonatosDelJugador(idJugador);
		} catch (JugadorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@PostMapping("/addCampeonato")
	@CrossOrigin(origins = "http://localhost:3000") 
	public void addCampeonato(@RequestBody CampeonatoView campeonato) { //ANDO
		try {
			Controlador.getInstancia().crearCampeonato(campeonato.getDescripcion(), campeonato.getFechaInicio(), campeonato.getFechaFin(), campeonato.getEstado());
		} catch (CampeonatoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@PostMapping("/addJugador")
	@CrossOrigin(origins = "http://localhost:3000") 
	public void addJugador(@RequestBody JugadorView jugador, @RequestParam(name = "idClub") Integer idClub) { //ANDO
		try {
			Controlador.getInstancia().agregarJugador(jugador.getTipoDocumento() + " " + jugador.getNumeroDocumento(), jugador.getNombre() + " " + jugador.getApellido(), idClub, jugador.getFechaNacimiento(), jugador.getDireccion(), jugador.getMail(), jugador.getTelefono());
		} catch (ClubException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@PostMapping("/addJugadorLista")
	@CrossOrigin(origins = "http://localhost:3000") 
	public void addJugadorLista(@RequestParam (name = "idPartido") Integer idPartido, @RequestParam (name = "idJugador") Integer idJugador, @RequestParam (name = "cantAmarillas") Integer cantAmarillas) { //ANDO
		try {
			Controlador.getInstancia().agregarJugadorEnLista(idPartido, idJugador, cantAmarillas);
		} catch (JugadorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PartidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@PutMapping("/cambioJugadorClub")
	@CrossOrigin(origins = "http://localhost:3000") 
	public void cambioJugadorClub(@RequestParam (name = "idJugador") Integer idJugador, @RequestParam (name = "idClubDestino") Integer idClubDestino) { //ANDO
		try {
			Controlador.getInstancia().cambioJugadorDeClub(idJugador, idClubDestino);
		} catch (JugadorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClubException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@PostMapping("/addFalta")
	@CrossOrigin(origins = "http://localhost:3000") 
	public void addFalta(@RequestParam (name = "idJugador") Integer idJugador, @RequestParam (name = "idPartido") Integer idPartido, @RequestParam (name = "idCampeonato") Integer idCampeonato, @RequestBody FaltaView falta) { //ANDO
		try {
			Controlador.getInstancia().CargarFalta(idJugador, idPartido, idCampeonato, falta.getMinuto(), falta.getTipo());
		} catch (PartidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JugadorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CampeonatoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@PostMapping("/addGol")
	@CrossOrigin(origins = "http://localhost:3000") 
	public void addGol(@RequestParam (name = "idJugador") Integer idJugador, @RequestParam (name = "idPartido") Integer idPartido, @RequestBody GolView gol) { //ANDO
		try {
			Controlador.getInstancia().CargarGol(idPartido, idJugador, gol.getMinuto(), gol.getSentido());
		} catch (PartidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JugadorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@PostMapping("/addPartidosElim")
	@CrossOrigin(origins = "http://localhost:3000") 
	public void addPartidosElim(@RequestBody PartidoView partido, @RequestParam (name = "idClubLocal") Integer idClubLocal, @RequestParam (name = "idClubVisitante") Integer idClubVisitante, @RequestParam (name = "idCampeonato") Integer idCampeonato) { //ANDO
		try {
			Controlador.getInstancia().crearPartidoEliminatorias(partido.getCategoria(), idClubLocal, idClubVisitante, partido.getFechaPartido(), idCampeonato);
		} catch (ClubException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CampeonatoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@PostMapping("/addPartidos")
	@CrossOrigin(origins = "http://localhost:3000") 
	public void addPartidos(@RequestParam (name = "idCampeonato") Integer idCampeonato, @RequestParam (name = "categoria") Integer categoria) { //ANDO
		try {
			Controlador.getInstancia().crearPartidos(idCampeonato, categoria);
		} catch (CampeonatoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@PostMapping("/addPartidosZonas")
	@CrossOrigin(origins = "http://localhost:3000") 
	public void addPartidosZonas(@RequestParam (name = "idCampeonato") Integer idCampeonato, @RequestParam (name = "categoria") Integer categoria, @RequestBody ZonaVO clubes) { //ANDO
		try {
			HashMap<Integer, List<Integer>> idClubes = new HashMap<Integer, List<Integer>>();
			for (int i = 1; i <= clubes.getClubes().size(); i++) {
				idClubes.put(i, clubes.getClubes().get(i-1));
			}
			Controlador.getInstancia().crearPartidosZonas(idCampeonato, categoria, idClubes);
		} catch (CampeonatoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClubException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@PutMapping("/deshabilitarJugador")
	@CrossOrigin(origins = "http://localhost:3000") 
	public void deshabilitarJugador(@RequestParam (name = "idJugador") Integer idJugador) { //ANDO
		try {
			Controlador.getInstancia().deshabilitarJugador(idJugador);
		} catch (JugadorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
//	@PutMapping("/desinscribirClub")  NO SE USA
//	public void desinscribirClub(@RequestParam (name = "idClub") Integer idClub, @RequestParam (name = "idCampeonato") Integer idCampeonato) { //ANDO
//		try {
//			Controlador.getInstancia().DesinscribirClubACampeonato(idClub, idCampeonato);
//		} catch (CampeonatoException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ClubException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
//	@DeleteMapping("/deleteCampeonato")  NO SE UTILIZA
//	public void deleteCampeonato(@RequestParam (name = "idCampeonato") Integer idCampeonato) { //ANDO
//		 try {
//			Controlador.getInstancia().eliminarCampeonato(idCampeonato);
//		} catch (CampeonatoException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
//	@DeleteMapping("/deleteJugador")  NO SE USA
//	public void deleteJugador(@RequestParam (name = "idJugador") Integer idJugador) {
//		 try {
//			Controlador.getInstancia().eliminarJugador(idJugador);
//		} catch (JugadorException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	@PutMapping("/habilitarJugador")
	@CrossOrigin(origins = "http://localhost:3000") 
	public void habilitarJugador(@RequestParam (name = "idJugador") Integer idJugador) { //ANDO
		try {
			Controlador.getInstancia().habilitarJugador(idJugador);
		} catch (JugadorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@PutMapping("/inscribirClub")
	@CrossOrigin(origins = "http://localhost:3000") 
	public void inscribirClub(@RequestParam (name = "idClub") Integer idClub, @RequestParam (name = "idCampeonato") Integer idCampeonato) { //ANDO
		try {
			Controlador.getInstancia().InscribirClubACampeonato(idClub, idCampeonato);
		} catch (CampeonatoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClubException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@PutMapping("/updateClub")
	@CrossOrigin(origins = "http://localhost:3000") 
	public void updateClub(@RequestBody ClubView club) { //ANDO
		try {
			Controlador.getInstancia().modificarClub(club.getNombre(), club.getDireccion());
		} catch (ClubException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@PutMapping("/updateCampeonato")
	@CrossOrigin(origins = "http://localhost:3000") 
	public void updateCampeonato(@RequestBody CampeonatoView campeonato) { //ANDO
		try {
			Controlador.getInstancia().modificarEstadoCampeonato(campeonato.getIdCampeonato(), campeonato.getEstado());
		} catch (CampeonatoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@PutMapping("/updateJugador")
	@CrossOrigin(origins = "http://localhost:3000") 
	public void updateJugador(@RequestBody JugadorView jugador) {  //ANDO
		try {
			Controlador.getInstancia().modificarJugador(jugador.getIdJugador(), jugador.getTipoDocumento() + " " + jugador.getNumeroDocumento(), jugador.getNombre() + " " + jugador.getApellido(), jugador.getDireccion(), jugador.getMail(), jugador.getTelefono());
		} catch (JugadorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@PutMapping("/updateRepresentante")
	@CrossOrigin(origins = "http://localhost:3000") 
	public void updateRepresentante(@RequestBody RepresentanteView representante) { //ANDO
		try {
			Controlador.getInstancia().modificarRepresentante(representante.getLegajo(), representante.getDocumento(), representante.getNombre());
		} catch (RepresentanteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@GetMapping("/getClubJugador")
	@CrossOrigin(origins = "http://localhost:3000") 
	public ClubView getClubJugador(@RequestParam (name = "idJugador") Integer idJugador) { //ANDO
		try {
			return Controlador.getInstancia().ObtenerClubDelJugador(idJugador);
		} catch (JugadorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping("/getJugadoresClub")
	@CrossOrigin(origins = "http://localhost:3000") 
	public List<JugadorView> getJugadoresClub(@RequestParam (name = "idClub") Integer idClub) { //ANDO
		try {
			return Controlador.getInstancia().ObtenerJugadoresDelClub(idClub);
		} catch (ClubException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping("/getJugador")
	@CrossOrigin(origins = "http://localhost:3000") 
	public JugadorView getJugador(@RequestParam (name = "idJugador") Integer idJugador) { //ANDO
		try {
			return Controlador.getInstancia().ObtenerDatosJugador(idJugador);
		} catch (JugadorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping("/getPartido")
	@CrossOrigin(origins = "http://localhost:3000") 
	public PartidoView getPartido(@RequestParam (name = "idPartido") Integer idPartido) { //ANDO
		try {
			return Controlador.getInstancia().ObtenerDatosPartido(idPartido);
		} catch (PartidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping("/getPartidosCampeonato")
	@CrossOrigin(origins = "http://localhost:3000") 
	public List<PartidoView> getPartidosCampeonato(@RequestParam (name = "idCampeonato") Integer idCampeonato){ //ANDO
		return Controlador.getInstancia().ObtenerPartidosPorCampeonato(idCampeonato);
	}
	
	@GetMapping("/getRepresentante")
	@CrossOrigin(origins = "http://localhost:3000") 
	public RepresentanteView getRepresentante(@RequestParam (name = "idRepresentante") Integer idRepresentante) { //ANDO
		try {
			return Controlador.getInstancia().ObtenerDatosRepresentante(idRepresentante);
		} catch (RepresentanteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping("/getGanadoresElim")
	@CrossOrigin(origins = "http://localhost:3000") 
	public List<ClubView> getGanadoresElim(@RequestParam (name = "idCampeonato") Integer idCampeonato){ //ANDO
		return Controlador.getInstancia().obtenerGanadoresEliminatorias(idCampeonato);
	}
	
	@GetMapping("/getTablaPos")
	@CrossOrigin(origins = "http://localhost:3000") 
	public List<List<Double>> getTablaPos(@RequestParam (name = "idCampeonato") Integer idCampeonato){ //ANDO
		try {
			return Controlador.getInstancia().ObtenerTablaPosiciones(idCampeonato);
		} catch (CampeonatoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping("/getTablaPorZonas")
	@CrossOrigin(origins = "http://localhost:3000") 
	public List<List<List<Double>>> getTablaPosZonas(@RequestParam (name = "idCampeonato") Integer idCampeonato){ //ANDO
		try {
			return Controlador.getInstancia().ObtenerTablasPosicionesZonas(idCampeonato);
		} catch (CampeonatoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping("/getMiembro")
	@CrossOrigin(origins = "http://localhost:3000") 
	public MiembroView getMiembro(@RequestParam (name = "idLista") Integer idLista) { //ANDO
		try {
			return Controlador.getInstancia().ObtenerDatosMiembro(idLista);
		} catch (MiembroException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping("/getMiembrosLocales")
	@CrossOrigin(origins = "http://localhost:3000") 
	public List<MiembroView> getMiembrosLocales(@RequestParam (name = "idPartido") Integer idPartido) { //ANDO
		try {
			return Controlador.getInstancia().ObtenerLocalesPartido(idPartido);
		}
		 catch (PartidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping("/getMiembrosVisitantes")
	@CrossOrigin(origins = "http://localhost:3000") 
	public List<MiembroView> getMiembrosVisitantes(@RequestParam (name = "idPartido") Integer idPartido) { //ANDO
		try {
			return Controlador.getInstancia().ObtenerVisitantesPartido(idPartido);
		}
		 catch (PartidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
	@PutMapping("/validarResultado")
	@CrossOrigin(origins = "http://localhost:3000") 
	public void validarResultado(@RequestParam (name = "idPartido") Integer idPartido, @RequestParam (name = "idRepresentante") Integer idRepresentante, @RequestParam (name = "validacion") Character validacion) { //ANDO
		try {
			Controlador.getInstancia().validarResultado(idPartido, idRepresentante, validacion);
		} catch (PartidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RepresentanteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CampeonatoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
