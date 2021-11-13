package test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import controlador.Controlador;
import exceptions.CampeonatoException;
import exceptions.ClubException;
import exceptions.JugadorException;
import exceptions.PartidoException;
import exceptions.RepresentanteException;

public class TestCreacionEliminacion {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws ClubException, CampeonatoException, JugadorException, PartidoException, RepresentanteException {
		// TODO Auto-generated method stub
		/*Controlador.getInstancia().crearCampeonato("Copa Libertadores",  new Date(58, 6, 2, 0, 0, 0), new Date(60, 6, 29, 0, 0, 0), "activo");
		Controlador.getInstancia().crearCampeonato("Copa America",  new Date(59, 6, 2, 0, 0, 0), new Date(61, 6, 29, 0, 0, 0), "activo");
		Controlador.getInstancia().crearCampeonato("Copa Europa",  new Date(60, 6, 2, 0, 0, 0), new Date(62, 6, 29, 0, 0, 0), "activo");
		Controlador.getInstancia().crearCampeonato("Copa Africa",  new Date(61, 6, 2, 0, 0, 0), new Date(61, 6, 29, 0, 0, 0), "activo");
		
		Controlador.getInstancia().eliminarCampeonato(2);
		
		
		Controlador.getInstancia().InscribirClubACampeonato(1, 1);
		Controlador.getInstancia().InscribirClubACampeonato(2, 1);
		Controlador.getInstancia().InscribirClubACampeonato(1, 5);
		Controlador.getInstancia().InscribirClubACampeonato(2, 5);
		Controlador.getInstancia().InscribirClubACampeonato(3, 5);
		Controlador.getInstancia().InscribirClubACampeonato(4, 5);
		Controlador.getInstancia().InscribirClubACampeonato(5, 5);
		Controlador.getInstancia().InscribirClubACampeonato(6, 5);
		Controlador.getInstancia().InscribirClubACampeonato(7, 5);
		Controlador.getInstancia().InscribirClubACampeonato(8, 5);
		Controlador.getInstancia().DesinscribirClubACampeonato(1, 1);
		Controlador.getInstancia().DesinscribirClubACampeonato(2, 1);
		
		
		
		
		Controlador.getInstancia().agregarJugador("DNI 12345678", "Carlos Suarez", 1, new Date(39, 6, 2, 0, 0, 0), "Calle 123", "cs@gmail.com", "11203210");
		Controlador.getInstancia().agregarJugador("DNI 12345698", "Jose Sanchez", 1, new Date(49, 6, 2, 0, 0, 0), "Calle 123", "cs@gmail.com", "13203210");
		Controlador.getInstancia().agregarJugador("DNI 12345668", "Luis Lopez", 1, new Date(38, 6, 2, 0, 0, 0), "Calle 123", "cs@gmail.com", "11204210");
		Controlador.getInstancia().agregarJugador("DNI 12345628", "Fabricio Gomez", 1, new Date(37, 6, 2, 0, 0, 0), "Calle 123", "cs@gmail.com", "11803210");
		
		Controlador.getInstancia().eliminarJugador(3);
		Controlador.getInstancia().eliminarJugador(4);
		*//*
		try {
			Controlador.getInstancia().crearPartidosFixture(5, 20);  
			HashMap<Integer, List<Integer>> zonaClubes = new HashMap<Integer, List<Integer>>();
			List<Integer> clubes1 = new ArrayList<Integer>();
			List<Integer> clubes2 = new ArrayList<Integer>();
			clubes1.add(1);
			clubes1.add(2);
			clubes1.add(3);
			clubes1.add(4);
			clubes2.add(5);
			clubes2.add(6);
			clubes2.add(7);
			clubes2.add(8);
			zonaClubes.put(1, clubes1);
			zonaClubes.put(2, clubes2);
			Controlador.getInstancia().crearPartidosZonas(5, 28, 2, zonaClubes);
		} catch (CampeonatoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClubException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		/*
		Controlador.getInstancia().agregarJugadorEnLista(1, 2, 3);
	
		Controlador.getInstancia().CargarFalta(2, 1, 3, 2, "amarilla");	
		Controlador.getInstancia().agregarJugador("DNI 25884775", "Alberto Ramonescu", 4, new Date(1988, 4, 12, 0, 0), "Almafuerte 524", "albertescu05@coldmail.com", "+5491128741536");*/
		/*Controlador.getInstancia().CargarGol(53, 30, 28, "a favor");
		Controlador.getInstancia().CargarGol(53, 30, 58, "a favor");
		Controlador.getInstancia().CargarGol(53, 30, 17, "a favor");
		Controlador.getInstancia().CargarGol(53, 30, 28, "a favor");
		Controlador.getInstancia().CargarGol(53, 4, 91, "a favor");*/
		
		HashMap<Integer, List<Integer>> idClubes = new HashMap<Integer, List<Integer>>();
		List<Integer> zona1 = new ArrayList<Integer>();
		List<Integer> zona2 = new ArrayList<Integer>();
		List<Integer> zona3 = new ArrayList<Integer>();
		zona1.add(1);
		zona1.add(3);
		zona1.add(6);
		zona3.add(7);
		zona3.add(2);
		zona3.add(4);
		zona2.add(5);
		zona2.add(8);
		zona2.add(9);
		idClubes.put(1, zona1);
		idClubes.put(2, zona2);
		idClubes.put(3, zona3);
		Controlador.getInstancia().crearPartidosZonas(5, 83, idClubes); 
		
//		
//		
//		
	}

}
