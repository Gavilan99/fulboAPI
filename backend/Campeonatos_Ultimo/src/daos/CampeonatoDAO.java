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
	
	@SuppressWarnings({ "deprecation" })
	public List<List<Double>> ObtenerTablaPosiciones(Campeonato c){
		List<List<Double>> tabla = new ArrayList<>();
		List<Double> fila;
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		for (Club cl: c.getInscriptos()) {
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
			tabla.add(fila);
		}
		s.getTransaction().commit();
		s.close();
		return tabla;
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
	
	public void CargarPuntosEnTabla(HashMap<Club, Integer> PuntosEquipos, Campeonato c) {
		
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		for (Club cl:PuntosEquipos.keySet()) {
			s.createSQLQuery("update tablaPosiciones" + c.getDescripcion().replace(" ", "") + " set puntos = " + PuntosEquipos.get(cl) + " where idClub = " + cl.getIdClub()).executeUpdate();

		}
		s.getTransaction().commit();
		s.close();
		
	}
	
	
	
}
