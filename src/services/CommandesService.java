package services;

import java.util.List;

import dao.Commandes;

public interface CommandesService {
	
	public int add(Commandes e);		
	public List<Commandes> findCommandByUser(String client);
}
