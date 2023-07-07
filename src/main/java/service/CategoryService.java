package service;

import model.Category;

import java.util.List;

public interface CategoryService {
    Category findById(int id);
    List<Category> findAll();
}
