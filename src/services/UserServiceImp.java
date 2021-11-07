package services;


import dao.UserDAO;
import dao.UserDAOImp;
import dao.Users;

public class UserServiceImp implements UserService{

	UserDAO user = new UserDAOImp();
	@Override
	public void add(Users e) {
		user.add(e);
	}

	@Override
	public Users edit(Users e) {
		return user.edit(e);
	}

	public boolean check_login(String login,String password){
		return user.check_login(login, password);
	}

	@Override
	public boolean checkUser(String username) {
		return user.checkUser(username);
	}

	@Override
	public int getUserId(String username, String password) {	
		return user.getUserId(username, password);
	}
}
