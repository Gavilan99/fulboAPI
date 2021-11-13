package hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import modelo.Campeonato;
import modelo.Club;
import modelo.Falta;
import modelo.Gol;
import modelo.Jugador;
import modelo.Miembro;
import modelo.Partido;
import modelo.Representante;

public class HibernateUtil
{
    private static SessionFactory sessionFactory;
    private HibernateUtil() {
        try
        {
        	 AnnotationConfiguration config = new AnnotationConfiguration();
        	 config.addAnnotatedClass(Campeonato.class);
        	 config.addAnnotatedClass(Club.class);
        	 config.addAnnotatedClass(Falta.class);
        	 config.addAnnotatedClass(Gol.class);
        	 config.addAnnotatedClass(Jugador.class);
        	 config.addAnnotatedClass(Miembro.class);
        	 config.addAnnotatedClass(Partido.class);
        	 config.addAnnotatedClass(Representante.class);
             sessionFactory = config.buildSessionFactory(); 
        }
        catch (Throwable ex)
        {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
 
    public static SessionFactory getSessionFactory()
    {
    	if (sessionFactory == null) {
    		new HibernateUtil();
    	}
        return sessionFactory;
    }
}
