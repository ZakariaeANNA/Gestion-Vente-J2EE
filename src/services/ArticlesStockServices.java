package services;

import java.util.List;

import dao.ArticlesStock;

public interface ArticlesStockServices {
	
	public void add(ArticlesStock e);
	public ArticlesStock edit(ArticlesStock e);
	public List<ArticlesStock> findAll();
	public ArticlesStock findById(int id); 
}
