package daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import exceptions.PartidoException;
import hibernate.HibernateUtil;
import modelo.Partido;

public class PartidoDAO {

private static PartidoDAO instancia;
	
	private PartidoDAO() {}
	
	public static PartidoDAO getInstancia() {
		if (instancia == null) {
			instancia = new PartidoDAO();
		}
		return instancia;
	}
	
	public void grabar(Partido c) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.save(c);
		s.getTransaction().commit();
		s.close();
	}
	
	public void actualizar(Partido c) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.update(c);
		s.getTransaction().commit();
		s.close();
	}
	
	public void eliminar(Partido c) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.delete(c);;
		s.getTransaction().commit();
		s.close();
	}
	
	public Partido ObtenerPartidoPorId(int id) throws PartidoException{
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		Partido aux = (Partido) s.createQuery("from Partido c where c.idPartido = " + id).uniqueResult(); 
		s.getTransaction().commit();
		s.close();
		if (aux != null)
			return aux;
		else
			throw new PartidoException("No existe un partido con id = " + id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Partido> ObtenerPartidos(){
		List<Partido> resultado = new ArrayList<Partido>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		resultado = (List<Partido>) s.createQuery("from Partido").list(); 
		s.getTransaction().commit();
		s.close();
		return resultado;
	}
}
