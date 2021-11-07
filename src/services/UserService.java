package services;


import dao.Users;

public interface UserService {
	
	public void add(Users e);
	public Users edit(Users e);
	public boolean check_login(String login,String password);
	public boolean checkUser(String username);
	public int getUserId(String username,String password);
}
