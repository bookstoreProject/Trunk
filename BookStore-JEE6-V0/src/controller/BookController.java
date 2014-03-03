package controller;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import service.BookService;
import service.CategoryService;
import entities.Book;
import entities.Category;

@Named
@RequestScoped
public class BookController {

	@Inject
	private BookService book;

	@Produces
	@Named
	private List<Book> bookList;
	
	public List<Book> getBookList() {
		bookList = book.findAll();
		return bookList;
	}
}
