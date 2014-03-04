package model;

import javax.ejb.Local;
import javax.ejb.Stateless;

import entities.Book;
import entities.Order;
import service.BookService;
import service.OrderService;

@Stateless
@Local(OrderService.class)
public class OrderServiceEJB extends GenericCRUDServiceEJB<Order> implements OrderService{

}
