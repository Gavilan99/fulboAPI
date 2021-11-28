package daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import exceptions.MiembroException;
import hibernate.HibernateUtil;
import modelo.Miembro;
import view.JugadorView;

public class MiembroDAO {

private static MiembroDAO instancia;
	
	private MiembroDAO() {}
	
	public static MiembroDAO getInstancia() {
		if (instancia == null) {
			instancia = new MiembroDAO();
		}
		return instancia;
	}
	
	public void grabar(Miembro c) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.save(c);
		s.getTransaction().commit();
		s.close();
	}
	
	public void actualizar(Miembro c) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.update(c);
		s.getTransaction().commit();
		s.close();
	}
	
	public void eliminar(Miembro c) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.delete(c);;
		s.getTransaction().commit();
		s.close();
	}
	
	public Miembro ObtenerMiembroPorId(int id) throws MiembroException{
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		Miembro aux = (Miembro) s.createQuery("from Miembro c where c.idLista = " + id).uniqueResult(); 
		s.getTransaction().commit();
		s.close();
		if (aux != null)
			return aux;
		else
			throw new MiembroException("No existe un miembro con id = " + id);
	}
	
	@SuppressWarnings("unchecked")
	public List<JugadorView> ObtenerJugadoresPartido(Integer idPartido){
        List<Miembro> resultado = new ArrayList<Miembro>();
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        resultado = (List<Miembro>) s.createQuery("from Miembro where idPartido = " + idPartido).list(); 
        s.getTransaction().commit();
        s.close();
        List<JugadorView> jugadores = new ArrayList<JugadorView>();
        for(Miembro m : resultado) {
            jugadores.add(m.getJugador().toView());
        }
        return jugadores;
    }
	
	@SuppressWarnings("unchecked")
	public List<Miembro> ObtenerMiembros(){
		List<Miembro> resultado = new ArrayList<Miembro>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		resultado = (List<Miembro>) s.createQuery("from Miembro").list(); 
		s.getTransaction().commit();
		s.close();
		return resultado;
	}
}
