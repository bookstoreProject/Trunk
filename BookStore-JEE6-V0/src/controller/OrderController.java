package controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.sun.org.apache.xpath.internal.operations.Or;

import entities.Book;
import entities.Client;
import entities.Order;
import service.BookService;
import service.OrderService;


@Named
@SessionScoped
public class OrderController implements Serializable{

	@Inject
	private BookService bookService;
	
	@Inject
	private OrderService orderService;
	
	@Inject 
	private ClientController clientController;
	
	@Inject
	private MessageBean messageBean;

	

	public String buyBook(Long id){
		if(clientController.getOrder()==null)
			return "login";
		Book book = bookService.find(id);
		clientController.getOrder().addOne(book);
		messageBean.addMessage("bookBought");
		return "book";
	}
	
	public String addBook(Long id){
		Book book = bookService.find(id);
		clientController.getOrder().addOne(book);		
		return "cart";
	}
	
	public String removeBook(Long id){
		Book book = bookService.find(id);
		clientController.getOrder().removeOne(book);		
		return "cart";
	}

	public String buyOrder(){
		orderService.create(clientController.getOrder());
		messageBean.addMessage("orderBought");
		return "cart";
	}
}