package daos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;

import exceptions.CampeonatoException;
import hibernate.HibernateUtil;
import modelo.Campeonato;
import modelo.Club;
import modelo.Partido;


public class CampeonatoDAO {

	private static CampeonatoDAO instancia;
	
	private CampeonatoDAO() {}
	
	public static CampeonatoDAO getInstancia() {
		if (instancia == null) {
			instancia = new CampeonatoDAO();
		}
		return instancia;
	}
	
	public void grabar(Campeonato c) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.save(c);
		s.createSQLQuery("create table tablaPosiciones" + c.getDescripcion().replace(" ", "") + " (idClub int NOT NULL,\r\n"
				+ "	cantidadJugados int NULL,\r\n"
				+ "	cantidadganados int NULL,\r\n"
				+ "	cantidadempatados int NULL,\r\n"
				+ "	cantidadperdidos int NULL,\r\n"
				+ "	golesFavor int NULL,\r\n"
				+ "	golesContra int NULL,\r\n"
				+ "	diferenciaGoles int NULL,\r\n"
				+ "	puntos int NULL,\r\n"
				+ "	promedio decimal(6, 3) NULL,\r\n"
				+ "	constraint pk_tablaPosiciones" + c.getDescripcion().replace(" ", "") + " primary key (idClub),\r\n"
				+ "	constraint fk_tablaPosiciones" + c.getDescripcion().replace(" ", "") + "_clubes foreign key (idClub) references clubes)").executeUpdate();
		s.getTransaction().commit();
		s.close();
	}
	
	public void generarTablasZonas(Campeonato c, int cantZonas, HashMap<Integer, List<Integer>> idClubes) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		for (int i = 1; i <= cantZonas; i++) {
			s.createSQLQuery("create table tablaPosiciones" + c.getDescripcion().replace(" ", "") + "Zona" + i + " (idClub int NOT NULL,\r\n"
					+ "	cantidadJugados int NULL,\r\n"
					+ "	cantidadganados int NULL,\r\n"
					+ "	cantidadempatados int NULL,\r\n"
					+ "	cantidadperdidos int NULL,\r\n"
					+ "	golesFavor int NULL,\r\n"
					+ "	golesContra int NULL,\r\n"
					+ "	diferenciaGoles int NULL,\r\n"
					+ "	puntos int NULL,\r\n"
					+ "	promedio decimal(6, 3) NULL,\r\n"
					+ "	constraint pk_tablaPosiciones" + c.getDescripcion().replace(" ", "") + "Zona" + i + " primary key (idClub),\r\n"
					+ "	constraint fk_tablaPosiciones" + c.getDescripcion().replace(" ", "") + "Zona" + i + "_clubes foreign key (idClub) references clubes)").executeUpdate();
			for(int j: idClubes.get(i)) {
				s.createSQLQuery("insert into tablaPosiciones"+ c.getDescripcion().replace(" ", "") + "Zona" + i + " values (" + j + ", 0, 0, 0, 0, 0, 0, 0, 0, 0.0)").executeUpdate();
			}
		}
		s.getTransaction().commit();
		s.close();
	}
	
	public void actualizar(Campeonato c) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.update(c);
		s.getTransaction().commit();
		s.close();
	}
	
	public void eliminar(Campeonato c) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.delete(c);
		s.getTransaction().commit();
		s.close();
	}
	
	public void AgregarClubTabla(Campeonato ca, Club cl) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.createSQLQuery("insert into tablaPosiciones" + ca.getDescripcion().replace(" ", "") + " values (" + cl.getIdClub() + ", 0, 0, 0, 0, 0, 0, 0, 0, 0.0)").executeUpdate();
		s.getTransaction().commit();
		s.close();
	}
	
	public void ActualizarClubTabla(Campeonato ca, Club cl, int cantJugados, int cantGanados, int cantEmpatados, int cantPerdidos, int golesFavor, int golesContra, int diferencia, int puntos, double promedio) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.createSQLQuery("update tablaPosiciones" + ca.getDescripcion().replace(" ", "") + " set cantidadJugados = " + cantJugados + ", cantidadganados = " + cantGanados + ", cantidadempatados = " + cantEmpatados + ", cantidadperdidos = " + cantPerdidos + ", golesFavor = " + golesFavor + ", golesContra = " + golesContra + ", diferenciaGoles = " + diferencia + ", puntos = " + puntos + ", promedio = " + promedio + " where idClub = " + cl.getIdClub()).executeUpdate();
		s.getTransaction().commit();
		s.close();
	}
	
//	public void EliminarTablaPosiciones(Campeonato c) { NO SE UTILIZA
//		Session s = HibernateUtil.getSessionFactory().openSession();
//		s.beginTransaction();
//		s.createSQLQuery("drop table tablaPosiciones" + c.getDescripcion().replace(" ", "")).executeUpdate();
//		try {
//			int cont = 1;
//			while (true) {
//				s.createSQLQuery("drop table tablaPosiciones" + c.getDescripcion().replace(" ", "") + "Zona" + cont).executeUpdate();
//			}
//		}
//		catch (Exception e) {
//			System.out.println("Tablas eliminadas");
//		}
//		finally {
//			s.getTransaction().commit();
//			s.close();
//		}
//	}
	
	@SuppressWarnings({ "deprecation" })
	public List<List<Double>> ObtenerTablaPosiciones(Campeonato c){
		List<List<Double>> tabla = new ArrayList<>();
		List<Double> fila;
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		int indice;
		for (Club cl: c.getInscriptos()) {
			indice = 0;
			fila = new ArrayList<Double>();
			fila.add(new Double((int)s.createSQLQuery("select idClub from tablaPosiciones" + c.getDescripcion().replace(" ", "") + " where idClub = " + cl.getIdClub()).uniqueResult()));
			fila.add(new Double((int) s.createSQLQuery("select cantidadJugados from tablaPosiciones" + c.getDescripcion().replace(" ", "") + " where idClub = " + cl.getIdClub()).uniqueResult()));
			fila.add(new Double((int) s.createSQLQuery("select cantidadganados from tablaPosiciones" + c.getDescripcion().replace(" ", "") + " where idClub = " + cl.getIdClub()).uniqueResult()));
			fila.add(new Double((int) s.createSQLQuery("select cantidadempatados from tablaPosiciones" + c.getDescripcion().replace(" ", "") + " where idClub = " + cl.getIdClub()).uniqueResult()));
			fila.add(new Double((int) s.createSQLQuery("select cantidadperdidos from tablaPosiciones" + c.getDescripcion().replace(" ", "") + " where idClub = " + cl.getIdClub()).uniqueResult()));
			fila.add(new Double((int) s.createSQLQuery("select golesFavor from tablaPosiciones" + c.getDescripcion().replace(" ", "") + " where idClub = " + cl.getIdClub()).uniqueResult()));
			fila.add(new Double((int) s.createSQLQuery("select golesContra from tablaPosiciones" + c.getDescripcion().replace(" ", "") + " where idClub = " + cl.getIdClub()).uniqueResult()));
			fila.add(new Double((int) s.createSQLQuery("select diferenciaGoles from tablaPosiciones" + c.getDescripcion().replace(" ", "") + " where idClub = " + cl.getIdClub()).uniqueResult()));
			fila.add(new Double((int) s.createSQLQuery("select puntos from tablaPosiciones" + c.getDescripcion().replace(" ", "") + " where idClub = " + cl.getIdClub()).uniqueResult()));
			fila.add(((BigDecimal)s.createSQLQuery("select promedio from tablaPosiciones" + c.getDescripcion().replace(" ", "") + " where idClub = " + cl.getIdClub()).uniqueResult()).doubleValue());
			for (int i=0; i < tabla.size(); i++) {
				if ((double) fila.get(8) < (double) tabla.get(i).get(8) || ((double) fila.get(8) == (double) tabla.get(i).get(8) && (double) fila.get(7) < (double) tabla.get(i).get(7)) || ((double) fila.get(8) == (double) tabla.get(i).get(8) && (double) fila.get(7) == (double) tabla.get(i).get(7) && (double) fila.get(5) < (double) tabla.get(i).get(5))) {
					indice++;
				}
				else {
					break;
				}
			}
			tabla.add(indice, fila);
		}
		s.getTransaction().commit();
		s.close();
		return tabla;
	}
	
	@SuppressWarnings({ "deprecation" })
	public List<List<List<Double>>> ObtenerTablaPosicionesZonas(Campeonato c, HashMap<Integer, List<Integer>> idClubes){
		List<List<List<Double>>> documento = new ArrayList<>();
		List<List<Double>> tabla;
		List<Double> fila;
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		int indice;
		for (Integer z: idClubes.keySet()) {
			tabla = new ArrayList<>();
			for (Club cl: c.getInscriptos()) {
				indice = 0;
				if (idClubes.get(z).contains(cl.getIdClub())) {
					fila = new ArrayList<Double>();
					fila.add(new Double((int)s.createSQLQuery("select idClub from tablaPosiciones" + c.getDescripcion().replace(" ", "") + "Zona" + z + " where idClub = " + cl.getIdClub()).uniqueResult()));
					fila.add(new Double((int) s.createSQLQuery("select cantidadJugados from tablaPosiciones" + c.getDescripcion().replace(" ", "") + "Zona" + z + " where idClub = " + cl.getIdClub()).uniqueResult()));
					fila.add(new Double((int) s.createSQLQuery("select cantidadganados from tablaPosiciones" + c.getDescripcion().replace(" ", "") + "Zona" + z + " where idClub = " + cl.getIdClub()).uniqueResult()));
					fila.add(new Double((int) s.createSQLQuery("select cantidadempatados from tablaPosiciones" + c.getDescripcion().replace(" ", "") + "Zona" + z + " where idClub = " + cl.getIdClub()).uniqueResult()));
					fila.add(new Double((int) s.createSQLQuery("select cantidadperdidos from tablaPosiciones" + c.getDescripcion().replace(" ", "") + "Zona" + z + " where idClub = " + cl.getIdClub()).uniqueResult()));
					fila.add(new Double((int) s.createSQLQuery("select golesFavor from tablaPosiciones" + c.getDescripcion().replace(" ", "") + "Zona" + z + " where idClub = " + cl.getIdClub()).uniqueResult()));
					fila.add(new Double((int) s.createSQLQuery("select golesContra from tablaPosiciones" + c.getDescripcion().replace(" ", "") + "Zona" + z + " where idClub = " + cl.getIdClub()).uniqueResult()));
					fila.add(new Double((int) s.createSQLQuery("select diferenciaGoles from tablaPosiciones" + c.getDescripcion().replace(" ", "") + "Zona" + z + " where idClub = " + cl.getIdClub()).uniqueResult()));
					fila.add(new Double((int) s.createSQLQuery("select puntos from tablaPosiciones" + c.getDescripcion().replace(" ", "") + "Zona" + z + " where idClub = " + cl.getIdClub()).uniqueResult()));
					fila.add(((BigDecimal)s.createSQLQuery("select promedio from tablaPosiciones" + c.getDescripcion().replace(" ", "") + "Zona" + z + " where idClub = " + cl.getIdClub()).uniqueResult()).doubleValue());
					for (int i=0; i < tabla.size(); i++) {
						if ((double) fila.get(8) < (double) tabla.get(i).get(8) || ((double) fila.get(8) == (double) tabla.get(i).get(8) && (double) fila.get(7) < (double) tabla.get(i).get(7)) || ((double) fila.get(8) == (double) tabla.get(i).get(8) && (double) fila.get(7) == (double) tabla.get(i).get(7) && (double) fila.get(5) < (double) tabla.get(i).get(5))) {
							indice++;
						}
						else {
							break;
						}
					}
					tabla.add(indice, fila);
				}
			}
			documento.add(tabla);
		}
		s.getTransaction().commit();
		s.close();
		return documento;
	}
	
	public Campeonato ObtenerCampeonatoPorId(int id) throws CampeonatoException{
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		Campeonato aux = (Campeonato) s.createQuery("from Campeonato c where c.idCampeonato = " + id).uniqueResult(); 
		s.getTransaction().commit();
		s.close();
		if (aux != null)
			return aux;
		else
			throw new CampeonatoException("No existe un campeonato con id = " + id);
	}
	
	public Campeonato ObtenerCampeonatoPorNombre(String nombre) throws CampeonatoException{
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		Campeonato aux = (Campeonato) s.createQuery("from Campeonato c where c.descripcion = " + nombre).uniqueResult(); 
		s.getTransaction().commit();
		s.close();
		if (aux != null)
			return aux;
		else
			throw new CampeonatoException("No existe un campeonato con descripcion = " + nombre);
	}
	
	@SuppressWarnings("unchecked")
	public List<Campeonato> ObtenerCampeonatos(){
		List<Campeonato> resultado = new ArrayList<Campeonato>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		resultado = (List<Campeonato>) s.createQuery("from Campeonato").list(); 
		s.getTransaction().commit();
		s.close();
		return resultado;
	}
	
	public void CargarPuntosEnTablaGeneral(HashMap<Club, Integer> PuntosEquipos, Campeonato c, Partido p) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		int golesFavor, golesContra;
		for (Club cl: PuntosEquipos.keySet()) {
			if ((int) p.getClubLocal().getIdClub() == (int) cl.getIdClub()) {
				golesFavor = (int) p.getGolesLocal();
				golesContra = (int) p.getGolesVisitante();
			}
			else {
				golesFavor = (int) p.getGolesVisitante();
				golesContra = (int) p.getGolesLocal();
			}
			if (PuntosEquipos.get(cl).intValue() == 3)
				s.createSQLQuery("update tablaPosiciones" + c.getDescripcion().replace(" ", "") + " set puntos = " + PuntosEquipos.get(cl) + ", cantidadjugados += 1, cantidadganados += 1, golesFavor += " + golesFavor + ", golesContra += " + golesContra + ", diferenciaGoles += " + (golesFavor - golesContra) + " where idClub = " + cl.getIdClub()).executeUpdate();
			else if (PuntosEquipos.get(cl).intValue() == 1)
				s.createSQLQuery("update tablaPosiciones" + c.getDescripcion().replace(" ", "") + " set puntos = " + PuntosEquipos.get(cl) + ", cantidadjugados += 1, cantidadempatados += 1, golesFavor += " + golesFavor + ", golesContra += " + golesContra +  ", diferenciaGoles += " + (golesFavor - golesContra) + " where idClub = " + cl.getIdClub()).executeUpdate();
			else if (PuntosEquipos.get(cl).intValue() == 0) 
				s.createSQLQuery("update tablaPosiciones" + c.getDescripcion().replace(" ", "") + " set puntos = " + PuntosEquipos.get(cl) + ", cantidadjugados += 1, cantidadperdidos += 1, golesFavor += " + golesFavor + ", golesContra += " + golesContra +  ", diferenciaGoles += " + (golesFavor - golesContra) + " where idClub = " + cl.getIdClub()).executeUpdate();
			int jugados = (int) s.createSQLQuery("select cantidadjugados from tablaPosiciones" + c.getDescripcion().replace(" ", "") + " where idClub = " + cl.getIdClub()).uniqueResult();
			int puntos = (int) s.createSQLQuery("select puntos from tablaPosiciones" + c.getDescripcion().replace(" ", "") + " where idClub = " + cl.getIdClub()).uniqueResult();
			s.createSQLQuery("update tablaPosiciones" + c.getDescripcion().replace(" ", "") + " set promedio = " + (puntos + 0.0)/jugados + " where idClub = " + cl.getIdClub()).executeUpdate();
		}
		s.getTransaction().commit();
		s.close();
	}
	
	public void CargarPuntosEnTablasZonas(HashMap<Club, Integer> PuntosEquipos, Campeonato c, Partido p) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		int golesFavor, golesContra;
		for (Club cl: PuntosEquipos.keySet()) {
			if ((int) p.getClubLocal().getIdClub() == (int) cl.getIdClub()) {
				golesFavor = (int) p.getGolesLocal();
				golesContra = (int) p.getGolesVisitante();
			}
			else {
				golesFavor = (int) p.getGolesVisitante();
				golesContra = (int) p.getGolesLocal();
			}
			if (PuntosEquipos.get(cl).intValue() == 3)
				s.createSQLQuery("update tablaPosiciones" + c.getDescripcion().replace(" ", "") + "Zona" + p.getNroZona() + " set puntos = " + PuntosEquipos.get(cl) + ", cantidadjugados += 1, cantidadganados += 1, golesFavor += " + golesFavor + ", golesContra += " + golesContra + ", diferenciaGoles += " + (golesFavor - golesContra) + " where idClub = " + cl.getIdClub()).executeUpdate();
			else if (PuntosEquipos.get(cl).intValue() == 1)
				s.createSQLQuery("update tablaPosiciones" + c.getDescripcion().replace(" ", "") + "Zona" + p.getNroZona() + " set puntos = " + PuntosEquipos.get(cl) + ", cantidadjugados += 1, cantidadempatados += 1, golesFavor += " + golesFavor + ", golesContra += " + golesContra +  ", diferenciaGoles += " + (golesFavor - golesContra) + " where idClub = " + cl.getIdClub()).executeUpdate();
			else if (PuntosEquipos.get(cl).intValue() == 0) 
				s.createSQLQuery("update tablaPosiciones" + c.getDescripcion().replace(" ", "") + "Zona" + p.getNroZona() + " set puntos = " + PuntosEquipos.get(cl) + ", cantidadjugados += 1, cantidadperdidos += 1, golesFavor += " + golesFavor + ", golesContra += " + golesContra +  ", diferenciaGoles += " + (golesFavor - golesContra) + " where idClub = " + cl.getIdClub()).executeUpdate();
			int jugados = (int) s.createSQLQuery("select cantidadjugados from tablaPosiciones" + c.getDescripcion().replace(" ", "") + "Zona" + p.getNroZona() + " where idClub = " + cl.getIdClub()).uniqueResult();
			int puntos = (int) s.createSQLQuery("select puntos from tablaPosiciones" + c.getDescripcion().replace(" ", "") + "Zona" + p.getNroZona() + " where idClub = " + cl.getIdClub()).uniqueResult();
			s.createSQLQuery("update tablaPosiciones" + c.getDescripcion().replace(" ", "") + "Zona" + p.getNroZona() + " set promedio = " + (puntos + 0.0)/jugados + " where idClub = " + cl.getIdClub()).executeUpdate();
		}
		s.getTransaction().commit();
		s.close();
	}
	
	
}
