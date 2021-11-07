package action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.Users;
import services.UserService;
import services.UserServiceImp;

@SuppressWarnings("serial")
public class UpdateAction extends ActionSupport{

	private String actpassword;
	private String nouvpassword;
	private String confnouvpassword;
	

	public String getActpassword() {
		return actpassword;
	}
	public void setActpassword(String actpassword) {
		this.actpassword = actpassword;
	}
	public String getNouvpassword() {
		return nouvpassword;
	}
	public void setNouvpassword(String nouvpassword) {
		this.nouvpassword = nouvpassword;
	}
	public String getConfnouvpassword() {
		return confnouvpassword;
	}
	public void setConfnouvpassword(String confnouvpassword) {
		this.confnouvpassword = confnouvpassword;
	}
	
	UserService user = new UserServiceImp();
	
	// méthode pour modifier profile
	public String update()
	{
		Users a = new Users();
		Map<String,Object> session = ActionContext.getContext().getSession();
		String username = (String) session.get("username"); // le nom d'utilisateur
		if(nouvpassword.equals(confnouvpassword)) // verification des mot de passes
		{
			int Id = user.getUserId(username, actpassword);
			if(Id!=0) // si l'utilisateur existe dans BDD
			{
				a.setCodeUser(Id);
				a.setLogin(username);
				a.setPass(nouvpassword);
				try
				{
					user.edit(a); // modifier profile
				}catch(Exception e)
				{
					System.err.println("une problem dans la modification");
				}
			}else { // sinon
				addActionError("le mot que vous avez saisie est incorrect"); // message d'erreur
				return "input";
			}
		}else { // si les mot de passes ne sont pas identiques
			addActionError("les mots de passe ne sont pas identique"); // message d'erreur
			return "input";
		}
		addActionMessage("le mot de passe a ete modifier avec succes"); // message de succes si les modification est bien effectué
		return "success";
	}
}
