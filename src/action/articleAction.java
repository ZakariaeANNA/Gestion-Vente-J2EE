package action;
import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import dao.ArticlesPrix;
import dao.ArticlesStock;
import services.ArticlesPrixService;
import services.ArticlesPrixServicesImp;
import services.ArticlesStockServices;
import services.ArticlesStockServicesImp;

public class articleAction extends ActionSupport{
	private static final long serialVersionUID = -4215116840067378030L;
	private List<ArticlesStock> Article= null;
	
	public List<ArticlesStock> getArticle() {
		return Article;
	}


	public void setArticle(List<ArticlesStock> article) {
		Article = article;
	}

	// méthode pour afficher le tableau des articles disponible
	public String execute() {
		setArticle(new ArrayList<ArticlesStock>());
		List<ArticlesPrix> tmp_article;
		ArticlesPrixService avente = new ArticlesPrixServicesImp(); // service articleprix de g_vente
		ArticlesStockServices astock = new ArticlesStockServicesImp(); // service articlestock de g_stock
		tmp_article = avente.findAll(); // selectionner tous les articles de BDD g_vente
		ArticlesStock tmp;
		int qteArt,codeArt;
		for(int i = 0; i < tmp_article.size(); i++) {
			tmp = new ArticlesStock();
			codeArt = tmp_article.get(i).getCodeArt();
			qteArt = astock.findById(codeArt).getQteArt(); // obtenir quantité par code situé au BDD g_stock 
			tmp.setCodeArt(codeArt);
			tmp.setDescArt(tmp_article.get(i).getDescArt());
			tmp.setNomArt(tmp_article.get(i).getNomArt());
			tmp.setPrixArt(tmp_article.get(i).getPrixPdt());
			tmp.setQteArt(qteArt);
			getArticle().add(tmp); // ajouter article au list 
		}
		return "success";
	}
}
