package controller;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.StreamedContent;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.sun.org.apache.xpath.internal.operations.Or;

import entities.Book;
import entities.Client;
import entities.Order;
import service.BookService;
import service.OrderService;
import tools.GeneratePdf;
import tools.Mail;


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
	
	private StreamedContent pdfFile;
	
	public StreamedContent getPdfFile() {
		return pdfFile;
	}

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
		GeneratePdf pdf = new GeneratePdf();
		pdfFile = pdf.GeneratePdfFromOrder(clientController.getOrder());
		Mail message = new Mail();
		message.send();
		messageBean.addMessage("orderBought");
		return "cart";
	}
	
}
