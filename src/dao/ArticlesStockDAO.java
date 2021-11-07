package dao;

import java.util.List;

public interface ArticlesStockDAO {

	public void add(ArticlesStock e);
	public ArticlesStock edit(ArticlesStock e);
	public List<ArticlesStock> findAll();
	public ArticlesStock findById(int id); 
	
	
}
