package daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import exceptions.ClubException;
import hibernate.HibernateUtil;
import modelo.Campeonato;
import modelo.Club;

public class ClubDAO {

	private static ClubDAO instancia;
	
	private ClubDAO() {}
	
	public static ClubDAO getInstancia() {
		if (instancia == null) {
			instancia = new ClubDAO();
		}
		return instancia;
	}
	
	/*public void grabar(Club c) {                                NO LO UTILIZA NINGUN USUARIO
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.save(c);
		s.getTransaction().commit();
		s.close();
	} */
	
	public void actualizar(Club c) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.update(c);
		s.getTransaction().commit();
		s.close();
	}
	
	public void eliminar(Club c) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.delete(c);
		s.getTransaction().commit();
		s.close();
	}
	
	public void eliminarInscripcionCampeonato(Club cl, Campeonato ca) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.createSQLQuery("delete from clubesCampeonato where idClub = " + cl.getIdClub() + " and idCampeonato = " + ca.getIdCampeonato()).executeUpdate();
		s.getTransaction().commit();
		s.close();
	}
	
	public Club ObtenerClubPorId(int id) throws ClubException{
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		Club aux = (Club) s.createQuery("from Club c where c.idClub = " + id).uniqueResult(); 
		s.getTransaction().commit();
		s.close();
		if (aux != null)
			return aux;
		else
			throw new ClubException("No existe un club con id = " + id);
	}
	
	public Club ObtenerClubPorNombre(String nombre) throws ClubException{
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		Club aux = (Club) s.createQuery("from Club c where c.nombre = '" + nombre + "'").uniqueResult(); 
		s.getTransaction().commit();
		s.close();
		if (aux != null)
			return aux;
		else
			throw new ClubException("No existe un club con nombre = " + nombre);
	}
	
	@SuppressWarnings("unchecked")
	public List<Club> ObtenerClubes(){
		List<Club> resultado = new ArrayList<Club>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		resultado = (List<Club>) s.createQuery("from Club").list(); 
		s.getTransaction().commit();
		s.close();
		return resultado;
	}
}
