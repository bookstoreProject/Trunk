package controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import entities.Category;
import service.CategoryService;
import service.ClientService;

@Named
@SessionScoped
public class CategoryController implements Serializable {

	@Inject
	private CategoryService category;

	@Produces
	@Named
	private List<Category> categoryList;
	
	@Produces
	@Named
	private Category selectedCategory;

	public List<Category> getCategoryList() {
		categoryList = category.findAll();
		return categoryList;
	}
	
	public Category getSelectedCategory() {
		return selectedCategory;
	}

	public String selectCategory(Long id) {
		selectedCategory =  category.find(id);
		if (selectedCategory == null) {
			return null;
		}
		return "category";	
	}

}
