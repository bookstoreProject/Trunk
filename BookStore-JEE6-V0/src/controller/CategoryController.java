package controller;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import entities.Category;
import service.CategoryService;
import service.ClientService;

@Named
@RequestScoped
public class CategoryController {

	@Inject
	private CategoryService category;

	@Produces
	@Named
	private List<Category> categoryList;

	public List<Category> getCategoryList() {
		categoryList = category.findAll();
		return categoryList;
	}

}
