package dao;

import java.util.List;

import org.hibernate.Session;

public class ArticlesPrixDAOImp implements ArticlesPrixDAO {

	Session session = HibernateUtil.getSessionFactoryvente().openSession();

	@SuppressWarnings("unchecked")
	@Override
	public List<ArticlesPrix> findAll() {
		return session.createQuery("select o from ArticlesPrix o").list();
	}

	@Override
	public ArticlesPrix findById(int id) {
		return session.get(ArticlesPrix.class, id);
	}

}
