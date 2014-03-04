package controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import service.BookService;
import service.CategoryService;
import entities.Book;
import entities.Category;

@Named
@SessionScoped
public class BookController implements Serializable{

	@Inject
	private BookService bookService;

	@Produces
	@Named
	private List<Book> bookList;
	
	@Produces
	@Named
	private Book selectedBook;
	
	
	
	public List<Book> getBookList() {
		bookList = bookService.findAll();
		return bookList;
	}

	
	public Book getSelectedBook() {
		return selectedBook;
	}

	public  String selectBook(Long id) {
		selectedBook =  bookService.find(id);
		if (selectedBook == null) {
			return null;
		}
		return "book";	
	}

	
}
