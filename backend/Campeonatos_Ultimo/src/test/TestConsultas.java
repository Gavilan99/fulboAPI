package test;

import java.util.List;

import controlador.Controlador;
import exceptions.CampeonatoException;
import exceptions.JugadorException;
import exceptions.PartidoException;
import exceptions.RepresentanteException;
import view.CampeonatoView;
import view.ClubView;

public class TestConsultas {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		try {
			List<CampeonatoView> camps = Controlador.getInstancia().ObtenerCampeonatosDelJugador(3);
			for (CampeonatoView cv: camps) {
				System.out.println(cv);
			}
		} catch (JugadorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			System.out.println(Controlador.getInstancia().ObtenerDatosJugador(3));
		} catch (JugadorException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			System.out.println(Controlador.getInstancia().ObtenerClubDelJugador(3));
		} catch (JugadorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			System.out.println(Controlador.getInstancia().ObtenerDatosPartido(1));
		} catch (PartidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<ClubView> clubesv = Controlador.getInstancia().obtenerGanadoresEliminatorias(2); //Cuando se juegan partidos tipo octavos, cuartos, semis, etc, obtiene loc clubes ganadores de dichos partidos.
		for(ClubView cl: clubesv) {
			System.out.println(cl);
		}
		*/
		try {
			Controlador.getInstancia().CargarGol(542, 33, 78, "a favor");
			Controlador.getInstancia().validarResultado(542, 3, 'S');
			Controlador.getInstancia().validarResultado(542, 1, 'S');
		} catch (PartidoException | JugadorException e) {
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
