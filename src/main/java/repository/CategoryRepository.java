package repository;

import model.Category;

import java.util.List;

public interface CategoryRepository {
    Category findById(int id);
    List<Category> findAll();
}
