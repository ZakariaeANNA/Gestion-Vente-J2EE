package action;


import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;



import services.UserService;
import services.UserServiceImp;

@SuppressWarnings("serial")
public class LoginAction extends ActionSupport {
	private String username;
	private String password;
	UserService a = new UserServiceImp();

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

	
	
	// méthode d'authentification
	public String login()
	{
		Map<String,Object> session = ActionContext.getContext().getSession();
		boolean userFound = false;
		try {
			userFound= a.check_login(username, password); // verifier si le nom d'utilisateur et le mot de passe existe dans la base de données
		}catch(Exception e)
			{
				System.err.println("une problem dans la recheche");
			}
		if(userFound) // si utilisateur existe
		{
			session.put("username", username); // enregistrer l'utilisateur dans la session
			return "success";
		}else {
			addActionError("le nom d'utilisateur ou le mot de est incorrect"); // message d'erreur au utilisateur indiquant que les informations ne sont pa correctes
			return "input";
		}
	}
	
	//méthode pour deconnexion
	public String logout()
	{
		Map<String,Object> session = ActionContext.getContext().getSession();
		session.clear(); // vider session
		return "failure"; // diriger utilisateur vers la page d'authentification
	}

}
