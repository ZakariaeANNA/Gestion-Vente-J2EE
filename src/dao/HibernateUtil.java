package dao;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {    
    private static SessionFactory sessionFactory;    
    private static SessionFactory sessionFactory1;
    private static SessionFactory buildSessionFactorystock() {
        try {           
        	StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
        	        .configure( "hibernatestock.cfg.xml" )
        	        .build();

        	        Metadata metadata = new MetadataSources( standardRegistry )
        	        .getMetadataBuilder()
        	        .build();

        	        return metadata.getSessionFactoryBuilder().build();
        }
        catch (Throwable ex) {                
            throw new ExceptionInInitializerError(ex);
        }
    }
    private static SessionFactory buildSessionFactoryvente() {
        try {           
        	StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
        	        .configure( "hibernatevente.cfg.xml" )
        	        .build();

        	        Metadata metadata = new MetadataSources( standardRegistry )
        	        .getMetadataBuilder()
        	        .build();

        	        return metadata.getSessionFactoryBuilder().build();
        }
        catch (Throwable ex) {                
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactorystock() {
    	sessionFactory = buildSessionFactorystock();
        return sessionFactory;
    }   
    public static SessionFactory getSessionFactoryvente() {
    	sessionFactory1 = buildSessionFactoryvente();
        return sessionFactory1;
    }   
}