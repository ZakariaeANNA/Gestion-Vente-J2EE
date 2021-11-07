package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

public class CommandesDAOImp implements CommandesDAO {
	
	Session session = HibernateUtil.getSessionFactoryvente().openSession();
	
	
	@Override
	public int add(Commandes e) {
		session.beginTransaction();
		int ID; 
		ID = (Integer)session.save(e);
		session.getTransaction().commit();
		return ID;
	}
	

	
	@SuppressWarnings("unchecked")
	public List<Commandes> findCommandByUser(String client){
		
		String SQL_QUERY ="select o from Commandes as o where o.client=?";
		Query query = session.createQuery(SQL_QUERY);
		query.setParameter(0,client);
		return query.list();	
	}

}
