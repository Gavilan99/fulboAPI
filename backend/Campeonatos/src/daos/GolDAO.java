package daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import exceptions.GolException;
import hibernate.HibernateUtil;
import modelo.Gol;

public class GolDAO {

	private static GolDAO instancia;
	
	private GolDAO() {}
	
	public static GolDAO getInstancia() {
		if (instancia == null) {
			instancia = new GolDAO();
		}
		return instancia;
	}
	
	public void grabar(Gol c) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.save(c);
		s.getTransaction().commit();
		s.close();
	}
	
	public void actualizar(Gol c) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.update(c);
		s.getTransaction().commit();
		s.close();
	}
	
	public void eliminar(Gol c) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.delete(c);;
		s.getTransaction().commit();
		s.close();
	}
	
	public Gol ObtenerGolPorId(int id) throws GolException{
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		Gol aux = (Gol) s.createQuery("from Gol c where c.idGol = " + id).uniqueResult(); 
		s.getTransaction().commit();
		s.close();
		if (aux != null)
			return aux;
		else
			throw new GolException("No existe un gol con id = " + id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Gol> ObtenerGoles(){
		List<Gol> resultado = new ArrayList<Gol>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		resultado = (List<Gol>) s.createQuery("from Gol").list(); 
		s.getTransaction().commit();
		s.close();
		return resultado;
	}
}
