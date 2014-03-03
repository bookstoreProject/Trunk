package model;

import service.BookService;
import service.CategoryService;
import entities.Book;
import entities.Category;

public class BookServiceEJB extends GenericCRUDServiceEJB<Book> implements BookService{}
