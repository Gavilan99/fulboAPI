package daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import exceptions.RepresentanteException;
import hibernate.HibernateUtil;
import modelo.Representante;

public class RepresentanteDAO {

private static RepresentanteDAO instancia;
	
	private RepresentanteDAO() {}
	
	public static RepresentanteDAO getInstancia() {
		if (instancia == null) {
			instancia = new RepresentanteDAO();
		}
		return instancia;
	}
	
	public void grabar(Representante c) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.save(c);
		s.getTransaction().commit();
		s.close();
	}
	
	public void actualizar(Representante c) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.update(c);
		s.getTransaction().commit();
		s.close();
	}
	
	public void eliminar(Representante c) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.delete(c);;
		s.getTransaction().commit();
		s.close();
	}
	
	public Representante ObtenerRepresentantePorId(int id) throws RepresentanteException{
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		Representante aux = (Representante) s.createQuery("from Representante c where c.legajo = " + id).uniqueResult(); 
		s.getTransaction().commit();
		s.close();
		if (aux != null)
			return aux;
		else
			throw new RepresentanteException("No existe un representante con id = " + id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Representante> ObtenerRepresentantes(){
		List<Representante> resultado = new ArrayList<Representante>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		resultado = (List<Representante>) s.createQuery("from Representante").list(); 
		s.getTransaction().commit();
		s.close();
		return resultado;
	}
}
