package dao;

import java.util.List;

public interface ArticlesPrixDAO {

	public List<ArticlesPrix> findAll();
	public ArticlesPrix findById(int id);
}
