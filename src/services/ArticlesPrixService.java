package services;

import java.util.List;

import dao.ArticlesPrix;

public interface ArticlesPrixService {
	
	public List<ArticlesPrix> findAll();
	public ArticlesPrix findById(int id);
	
}
