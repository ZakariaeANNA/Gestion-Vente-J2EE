package services;

import java.util.List;

import dao.Commandes;
import dao.CommandesDAO;
import dao.CommandesDAOImp;


public class CommandesServicesImp implements CommandesService {
	CommandesDAO cmds = new CommandesDAOImp(); 
	@Override
	public int add(Commandes e) {
		return cmds.add(e);
	}

	@Override
	public List<Commandes> findCommandByUser(String client) {
		return cmds.findCommandByUser(client);
	}

}
