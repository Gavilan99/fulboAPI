package test;

import controlador.Controlador;
import exceptions.CampeonatoException;
import exceptions.ClubException;
import exceptions.JugadorException;
import exceptions.PartidoException;
import exceptions.RepresentanteException;

public class TestModificaciones {

	//@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*try {
			Controlador.getInstancia().modificarEstadoCampeonato(5, "activo");
		} catch (CampeonatoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
		Controlador.getInstancia().modificarClub("Racing Club", "Avellaneda 111");
	} catch (ClubException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Controlador.getInstancia().modificarJugador(3, "", "", "", "roberto@roberto.cacho", "+5491124569877");
	} catch (JugadorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Controlador.getInstancia().modificarRepresentante(2, "", "Julian Salomone");
		} catch (RepresentanteException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Controlador.getInstancia().cambioJugadorDeClub(3, 1);
			Controlador.getInstancia().cambioJugadorDeClub(4, 1);
			Controlador.getInstancia().cambioJugadorDeClub(5, 1);
			Controlador.getInstancia().cambioJugadorDeClub(6, 2);
			Controlador.getInstancia().cambioJugadorDeClub(7, 2);
			Controlador.getInstancia().cambioJugadorDeClub(8, 2);
			Controlador.getInstancia().cambioJugadorDeClub(9, 2);
	} catch (JugadorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClubException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Controlador.getInstancia().deshabilitarJugador(4);
		} catch (JugadorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Controlador.getInstancia().habilitarJugador(4);
		} catch (JugadorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		try {
			Controlador.getInstancia().validarResultado(53, 1, 'S');
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
		try {
			Controlador.getInstancia().validarResultado(53, 3, 'S');
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
