package service;

import java.util.ArrayList;
import java.util.List;

import model.GenericCRUDService;
import entities.Client;
import entities.Order;

public interface OrderService extends GenericCRUDService<Order>{
	 public List getOrders(Client client);
}

