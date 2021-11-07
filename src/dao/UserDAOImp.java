package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

public class UserDAOImp implements UserDAO {
	Session session = HibernateUtil.getSessionFactoryvente().openSession();
	@Override
	public void add(Users e) {
		session.beginTransaction();
		session.save(e);
		session.getTransaction().commit();
	}

	@Override
	public Users edit(Users e) {
		session.beginTransaction();
		Users user = (Users) session.merge(e);
		session.getTransaction().commit();
		return user;
	}

	@SuppressWarnings("unchecked")
	public boolean check_login(String login,String password)
	{
		boolean userFound = false;
		String SQL_QUERY ="select o from Users as o where o.login=? and o.pass=?";
		Query query = session.createQuery(SQL_QUERY);
		query.setParameter(0,login);
		query.setParameter(1,password);
		List<Users> list = query.list();
		if ((list != null) && (list.size() > 0)) {
			userFound= true;
		}
		return userFound;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean checkUser(String username) {
		boolean userFound = false;
		String SQL_QUERY ="select o from Users as o where o.login=?";
		Query query = session.createQuery(SQL_QUERY);
		query.setParameter(0,username);
		List list = query.list();
		if ((list != null) && (list.size() > 0)) {
			userFound= true;
		}
		return userFound;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int getUserId(String username, String password) {
		int UserId = 0;
		String SQL_QUERY ="select o from Users as o where o.login=? and o.pass=?";
		Query query = session.createQuery(SQL_QUERY);
		query.setParameter(0,username);
		query.setParameter(1,password);
		List<Users> user = query.list();
		if ((user != null) && (user.size() > 0)) {
			UserId= user.get(0).getCodeUser();
		}
		return UserId;
	}

}
