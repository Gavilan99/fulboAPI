package daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import exceptions.JugadorException;
import hibernate.HibernateUtil;
import modelo.Campeonato;
import modelo.Jugador;

public class JugadorDAO {

	private static JugadorDAO instancia;
	
	private JugadorDAO() {}
	
	public static JugadorDAO getInstancia() {
		if (instancia == null) {
			instancia = new JugadorDAO();
		}
		return instancia;
	}
	
	public void grabar(Jugador c) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.save(c);
		s.getTransaction().commit();
		s.close();
	}
	
	public void actualizar(Jugador c) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.update(c);
		s.getTransaction().commit();
		s.close();
	}
	
	public void eliminar(Jugador c) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.delete(c);
		s.getTransaction().commit();
		s.close();
	}
	
	public void AgregarHabilitacion(Campeonato c, Jugador j) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.createSQLQuery("insert into habilitaciones values (" + j.getIdJugador() + ", " + c.getIdCampeonato() + ", 'habilitado')").executeUpdate();
		s.getTransaction().commit();
		s.close();
	}
	
	public void ModificarHabilitacion(Campeonato c, Jugador j, String habilitacion) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.createSQLQuery("update habilitaciones set idJugador = " + j.getIdJugador() + ", idCampeonato = " + c.getIdCampeonato() + ", habilitado = '" + habilitacion + "' where idJugador = " + j.getIdJugador() + " and idCampeonato = " + c.getIdCampeonato()).executeUpdate();
		s.getTransaction().commit();
		s.close();
	}
	
	public void EliminarHabilitacion(Campeonato c, Jugador j) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.createSQLQuery("delete from habilitaciones where idJugador = " + j.getIdJugador() + " and idCampeonato = " + c.getIdCampeonato()).executeUpdate();
		s.getTransaction().commit();
		s.close();
	}
	
	public String ObtenerHabililtacion(Campeonato c, Jugador j) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		String resultado = (String) s.createSQLQuery("select habilitado from habilitaciones where idJugador = " + j.getIdJugador() + " and idCampeonato = " + c.getIdCampeonato()).uniqueResult();
		s.getTransaction().commit();
		s.close();
		return resultado;
	}
	
	public Jugador ObtenerJugadorPorId(int id) throws JugadorException{
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		Jugador aux = (Jugador) s.createQuery("from Jugador c where c.idJugador = " + id).uniqueResult(); 
		s.getTransaction().commit();
		s.close();
		if (aux != null)
			return aux;
		else
			throw new JugadorException("No existe un jugador con id = " + id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Jugador> ObtenerJugadores(){
		List<Jugador> resultado = new ArrayList<Jugador>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		resultado = (List<Jugador>) s.createQuery("from Jugador").list(); 
		s.getTransaction().commit();
		s.close();
		return resultado;
	}
	
}
