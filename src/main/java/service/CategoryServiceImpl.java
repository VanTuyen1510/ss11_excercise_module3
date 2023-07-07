package service;

import model.Category;
import repository.CategoryRepositoryImpl;

import java.util.List;

public class CategoryServiceImpl implements CategoryService{
    private CategoryRepositoryImpl repository = new CategoryRepositoryImpl();
    @Override
    public Category findById(int id) {
        return repository.findById(id);
    }

    @Override
    public List<Category> findAll() {
        return repository.findAll();
    }
}
