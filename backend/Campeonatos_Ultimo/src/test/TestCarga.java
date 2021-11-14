package test;

import java.util.Date;

import controlador.Controlador;
import exceptions.CampeonatoException;
import exceptions.ClubException;
import exceptions.JugadorException;
import exceptions.PartidoException;
import exceptions.RepresentanteException;

public class TestCarga {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws ClubException, CampeonatoException, JugadorException, PartidoException, RepresentanteException {
		// TODO Auto-generated method stub
		//Controlador.getInstancia().crearCampeonato("Copa Libertadores",  new Date(58, 6, 2, 0, 0, 0), new Date(58, 6, 29, 0, 0, 0), "activo");
		//Controlador.getInstancia().crearCampeonato("Copa America",  new Date(59, 6, 2, 0, 0, 0), new Date(59, 6, 29, 0, 0, 0), "activo");
		//Controlador.getInstancia().crearCampeonato("Copa Europa",  new Date(60, 6, 2, 0, 0, 0), new Date(60, 6, 29, 0, 0, 0), "activo");
		//Controlador.getInstancia().crearCampeonato("Copa Africa",  new Date(61, 6, 2, 0, 0, 0), new Date(61, 6, 29, 0, 0, 0), "activo");
		
		
		//Controlador.getInstancia().modificarClub("Racing", "Avellaneda2");
		//Controlador.getInstancia().modificarEstadoCampeonato(1, "activo");
		
		
//		Controlador.getInstancia().DesinscribirClubACampeonato(1, 1);
//		Controlador.getInstancia().DesinscribirClubACampeonato(2, 1);
//		
//		Controlador.getInstancia().InscribirClubACampeonato(1, 1);
//		Controlador.getInstancia().InscribirClubACampeonato(2, 1);
		
		
		
		//Controlador.getInstancia().cambioJugadorDeClub(1, 2);
		
		/*Controlador.getInstancia().eliminarJugador(1);
		Controlador.getInstancia().eliminarJugador(2);
		Controlador.getInstancia().eliminarJugador(3);
		Controlador.getInstancia().eliminarJugador(4);*/
		
		/*
		Controlador.getInstancia().agregarJugador("DNI 12345678", "Carlos Suarez", 1, new Date(39, 6, 2, 0, 0, 0), "Calle 123", "cs@gmail.com", "11203210");
		Controlador.getInstancia().agregarJugador("DNI 12345698", "Jose Sanchez", 1, new Date(49, 6, 2, 0, 0, 0), "Calle 123", "cs@gmail.com", "13203210");
		Controlador.getInstancia().agregarJugador("DNI 12345668", "Luis Lopez", 1, new Date(38, 6, 2, 0, 0, 0), "Calle 123", "cs@gmail.com", "11204210");
		Controlador.getInstancia().agregarJugador("DNI 12345628", "Fabricio Gomez", 1, new Date(37, 6, 2, 0, 0, 0), "Calle 123", "cs@gmail.com", "11803210");
		*/
		//Controlador.getInstancia().crearPartido(1, 1, 59, 1, 2, new Date(80, 6, 2, 0, 0, 0), 1);
		
		//Controlador.getInstancia().agregarJugadorEnLista(1, 9, 3);
		
		//Controlador.getInstancia().CargarFalta(9, 1, 1, 2, "amarilla");
		
		//System.out.println(Controlador.getInstancia().ObtenerDatosJugador(1));
		//System.out.println(Controlador.getInstancia().ObtenerClubDelJugador(1));
		//System.out.println(Controlador.getInstancia().ObtenerDatosPartido(1));
		
		//Controlador.getInstancia().habilitarJugador(1, 1, 1);
		//Controlador.getInstancia().CargarGol(1, 9, 4, "a favor");
		Controlador.getInstancia().validarResultado(1, 3, 'S');
		Controlador.getInstancia().validarResultado(1, 2, 'S');
		
	}

}
