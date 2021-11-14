package test;

import java.util.Date;
import java.util.List;

import controlador.Controlador;
import daos.CampeonatoDAO;
import daos.ClubDAO;
import exceptions.CampeonatoException;
import exceptions.ClubException;
import exceptions.JugadorException;
import modelo.Campeonato;
import modelo.Club;

public class Test {

	//@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		try {
			Controlador.getInstancia().DesinscribirClubACampeonato(2, 1);
			
		} catch (CampeonatoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClubException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/*
		Controlador.getInstancia().crearCampeonato("Radicheta", new Date(45, 4, 15, 0, 0, 0), new Date(45, 12, 24, 0, 0, 0), "inactivo");
		Controlador.getInstancia().crearCampeonato("AMOGUS", new Date(58, 6, 2, 0, 0, 0), new Date(58, 6, 29, 0, 0, 0), "activo");
		Controlador.getInstancia().crearCampeonato("BOUJOUNG", new Date(85, 1, 10, 0, 0, 0), new Date(85, 7, 11, 0, 0, 0), "activo");
		*/
		
	/*
		try {
			System.out.println(Controlador.getInstancia().dameCampeonatoId(1).toString());
			System.out.println(Controlador.getInstancia().dameCampeonatoId(3).toString());
			System.out.println(Controlador.getInstancia().dameCampeonatoId(35).toString());
		} catch (CampeonatoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("\n\n\n");
		
		try {
			Controlador.getInstancia().eliminarJugador(1, 2);
		} catch (JugadorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Controlador.getInstancia().eliminarCampeonato(2);
		} catch (CampeonatoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Campeonato> goid = Controlador.getInstancia().dameCampeonatos();
		for (Campeonato c: goid) {
			System.out.println(c.toString());
		} 
		
		try {
			Controlador.getInstancia().InscribirClubACampeonato(2, 1);
		} catch (CampeonatoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClubException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

}
