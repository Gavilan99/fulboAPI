package daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import hibernate.HibernateUtil;

public class UsuarioDAO {

	private static UsuarioDAO instancia;

	private UsuarioDAO() {}
	
	public static UsuarioDAO getInstancia() {
		if (instancia == null) {
			instancia = new UsuarioDAO();
		}
		return instancia;
	}
	
	public List<String> login(String usuario, String contrase�a) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		List<String> res = new ArrayList<String>();
		String rol = (String) s.createSQLQuery("select rol from usuarios where usuario = '" + usuario + "' and contrase�a = '" + contrase�a + "'").uniqueResult();
		if (rol != null) {
			Integer idRol = (Integer) s.createSQLQuery("select idRol from usuarios where usuario = '" + usuario + "' and contrase�a = '" + contrase�a + "'").uniqueResult();
			res.add(rol);
			res.add(Integer.toString(idRol));
		}
		s.getTransaction().commit();
		s.close();
		System.out.println(res.size());
		return res;
	}
}
