package action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;

import com.itextpdf.text.pdf.PdfWriter;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.ArticlesPrix;
import dao.ArticlesStock;
import dao.Commandes;
import services.ArticlesPrixService;
import services.ArticlesPrixServicesImp;
import services.ArticlesStockServices;
import services.ArticlesStockServicesImp;
import services.CommandesService;
import services.CommandesServicesImp;
import util.InvoiceGenerator;
public class addCommande extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String codeArt;
	private String dateCMD;
	private String qteArt;
	private List<Commandes> mescommandes=null;
	private InputStream inputStream;
	private Map<String,Object> session = ActionContext.getContext().getSession();

	public String getCodeArt() {
		return codeArt;
	}
	public void setCodeArt(String codeArt) {
		this.codeArt = codeArt;
	}
	public String getDateCMD() {
		return dateCMD;
	}
	public void setDateCMD(String dateCMD) {
		this.dateCMD = dateCMD;
	}
	public String getQteArt() {
		return qteArt;
	}
	public void setQteArt(String qteArt) {
		this.qteArt = qteArt;
	}
	public List<Commandes> getMescommandes() {
		return mescommandes;
	}
	public void setMescommandes(List<Commandes> mescommandes) {
		this.mescommandes = mescommandes;
	}
	
	
	// méthode pour faire une commande
    public String execute(){
    	String user = (String) session.get("username"); // le nom d'utilisateur
    	session.remove("commande");
    	int code = Integer.parseInt(codeArt);
		int qte = Integer.parseInt(qteArt);
		ArticlesPrixService article = new ArticlesPrixServicesImp(); // service articleprix de g_vente
		CommandesService a = new CommandesServicesImp(); // service commande de g_vente;
		ArticlesStockServices stock = new ArticlesStockServicesImp(); // service articlestock de g_stock
		ArticlesStock art;
		Commandes commande = new Commandes();
		// par l'exception NullPointerException on va détecter si l'article est existe dans la base de données c_à_d l'objet est non nulle.
		try {
			article.findById(code); // chercher l'article dans g_vente
			commande.setCodeArt(code);
			commande.setDateCmd(new SimpleDateFormat("dd/MM/yyyy").parse(dateCMD));
			commande.setQteCmd(qte);
			commande.setClient(user);
			art = stock.findById(code); // chercher l'article dans g_stock
			if(art.getQteArt() >= qte) {
				int codeCMD = a.add(commande);
				if(codeCMD > 0) {
					commande.setCodeCmd(codeCMD);
					session.put("commande", commande); // ajouter commande au session
					int nv_qte = art.getQteArt() - qte; // soustraction du quantité disponible de g_stock 
					art.setQteArt(nv_qte); // ajouter la nouvelle quantité 
					stock.edit(art); // modifier les données
				}
				
			}else {
				addActionError("La quantité demandé n'existe pas dans le stock"); // message d'erreur au page si la quantité demandé n'existe pas 
				return ERROR;
			}
			
			
		}catch(Exception e) {
			addActionError("L'article demandé n'existe pas"); // message d'erreur si l'article n'existe pas
			return ERROR;
		}
		addActionMessage("L'article est ajouté avec succes. Cliquez dans Telecharger pour telecharger la facture de la commande."); // message de succes si la commande est bien enregistré
		return SUCCESS;
	}
	
    // méthode pour télécharger la facture odf du commande
    public String telecharger() {
    	String user = (String) session.get("username"); // le nom d'utilisateur
        Commandes commande = new Commandes();
        commande = (Commandes) session.get("commande"); 
        InvoiceGenerator pdf = new InvoiceGenerator(); // instancier l'objet pour génerer pdf
        inputStream = pdf.createPDF(commande,user); // generer pdf à partir de données commande et nom d'utilisateur 
    	return SUCCESS;
    }

	
	public InputStream getInputStream() {
        return inputStream;
    }
	
	// méthode pour afficher le tableau des commandes
	public String getcommande() {
		String user = (String) session.get("username");
		CommandesService a = new CommandesServicesImp(); // service commande
		setMescommandes(a.findCommandByUser(user)); // selectionner tous les commandes d'utilisateur
		return "success";
	}
	
}
