package daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import exceptions.FaltaException;
import hibernate.HibernateUtil;

import modelo.Falta;

public class FaltaDAO {

private static FaltaDAO instancia;
	
	private FaltaDAO() {}
	
	public static FaltaDAO getInstancia() {
		if (instancia == null) {
			instancia = new FaltaDAO();
		}
		return instancia;
	}
	
	public void grabar(Falta c) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.save(c);
		s.getTransaction().commit();
		s.close();
	}
	
	public void actualizar(Falta c) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.update(c);
		s.getTransaction().commit();
		s.close();
	}
	
	public void eliminar(Falta c) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.delete(c);
		s.getTransaction().commit();
		s.close();
	}
	
	public Falta ObtenerFaltaPorId(int id) throws FaltaException{
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		Falta aux = (Falta) s.createQuery("from Falta c where c.idFalta = " + id).uniqueResult(); 
		s.getTransaction().commit();
		s.close();
		if (aux != null)
			return aux;
		else
			throw new FaltaException("No existe una falta con id = " + id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Falta> ObtenerFaltas(){
		List<Falta> resultado = new ArrayList<Falta>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		resultado = (List<Falta>) s.createQuery("from Falta").list(); 
		s.getTransaction().commit();
		s.close();
		return resultado;
	}
}
