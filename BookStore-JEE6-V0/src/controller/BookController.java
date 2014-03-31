package controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import service.BookService;
import service.CategoryService;
import entities.Book;
import entities.Category;

@Named
@SessionScoped
public class BookController implements Serializable{

	@Inject
	private BookService bookService;

	@Named
	private List<Book> bookList;
	
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
	
	public StreamedContent getStreamedPicture() {
		StreamedContent streamedPicture = null;
		if (streamedPicture == null && selectedBook.getPhoto() != null) {
			try {
				ByteArrayOutputStream os = new ByteArrayOutputStream();
				os.write(selectedBook.getPhoto());
				streamedPicture = new DefaultStreamedContent(new ByteArrayInputStream(os.toByteArray()), "image/png");
				os.close();
			} catch (FileNotFoundException e) {
			} catch (IOException e) {
			}
		}
		return streamedPicture;
	}
	
}
