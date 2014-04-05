package model;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

import entities.Book;
import entities.Client;
import entities.Order;
import service.BookService;
import service.OrderService;

@Stateless
@Local(OrderService.class)
public class OrderServiceEJB extends GenericCRUDServiceEJB<Order> implements OrderService{

	public List getOrders(Client client) {
	    List orders = null;
	    try {
	    	orders =  (List) em.createQuery("select o from Order o where o.client=:client ").setParameter("client", client).getResultList();
	    } catch (NoResultException e) {
	    }
	    return orders;
	  }
}
