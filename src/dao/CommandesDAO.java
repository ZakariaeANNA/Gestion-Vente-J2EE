package dao;

import java.util.List;

public interface CommandesDAO {
	public int add(Commandes e);		
	public List<Commandes> findCommandByUser(String client);
}
