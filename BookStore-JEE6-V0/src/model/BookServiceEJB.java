package model;

import javax.ejb.Local;
import javax.ejb.Stateless;

import service.BookService;
import service.CategoryService;
import service.ClientService;
import entities.Book;
import entities.Category;

@Stateless
@Local(BookService.class)
public class BookServiceEJB extends GenericCRUDServiceEJB<Book> implements BookService{}
