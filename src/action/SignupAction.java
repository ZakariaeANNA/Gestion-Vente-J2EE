package action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.Users;
import services.UserService;
import services.UserServiceImp;

@SuppressWarnings("serial")
public class SignupAction extends ActionSupport {
	private String username;
	private String password;
	private String confirmPassword;
	
	
		
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getConfirmPassword() {
		return confirmPassword;
	}


	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	UserService a = new UserServiceImp();
	
	// méthode pour inscrire
	public String signup()
	{
		Map<String,Object> session = ActionContext.getContext().getSession();
		if(username.length()<=20 && password.length()<=20) // verification des donnees saisie par utilisateur 
		{		
			Users user = new Users();
			if(a.checkUser(username)) // si l'utilisateur existe dans BDD
			{
				addActionError("ce nom d'utilisateur est deja existe choisi un autre"); // message d'erreur
				return "input"; // diriger vers page d'inscription
			}else { // si l'utilisateur n'existe pas
				try {
					user.setLogin(username); 
					user.setPass(password);
					a.add(user); // ajouter utilisateur au BDD
					session.put("username",username); // ajouter utilisateur dans session
				}catch(Exception e) {
					System.out.println("problem dans l'ajout de l'objet");
				}
			}
		}else { // si les données ne sont pas bien saisie 
			addActionError("le mot de passe et le nom d'utilisateur ne doit passe depasser 20 chararcters ou chiffre"); // message d'erreur 
			return "input"; // diriger la page d'inscription
		}
		return "success"; // si l'inscription est bien effectue diriger vers l'acceuil
	}
	
	// validation des donnees 
	public void validate()
	{
		if(password.equals(confirmPassword)==false) // verifier les mot de passes
		{
			addActionError("les mots de pass ne sont pas identique");
		}
	}
}
