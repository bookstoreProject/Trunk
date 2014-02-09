package service;

import entities.Client;
import model.GenericCRUDService;

public interface ClientService extends GenericCRUDService<Client>{ 
	  public Client login(String login, String password);
}
