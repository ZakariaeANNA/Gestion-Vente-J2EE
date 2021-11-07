package dao;

import java.util.List;

import org.hibernate.Session;

public class ArticlesStockDAOImp implements ArticlesStockDAO{
	Session session =HibernateUtil.getSessionFactorystock().openSession();
	@Override
	public void add(ArticlesStock e) {
		// TODO Auto-generated method stub
		session.beginTransaction();
		session.save(e);
		session.getTransaction().commit();
	
	}

	@Override
	public ArticlesStock edit(ArticlesStock e) {
		// TODO Auto-generated method stub
		session.beginTransaction();
		ArticlesStock ArtS = (ArticlesStock) session.merge(e);
		session.getTransaction().commit();
		return ArtS;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ArticlesStock> findAll() {
		// TODO Auto-generated method stub
		List<ArticlesStock> list = session.createQuery("select o from ArticlesStock o").list();
		return list;
	}

	@Override
	public ArticlesStock findById(int id) {
		// TODO Auto-generated method stub
		return session.get(ArticlesStock.class, id);
	}

}
