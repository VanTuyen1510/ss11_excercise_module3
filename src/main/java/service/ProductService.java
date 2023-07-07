package service;

import model.Product;

import java.util.List;

public interface ProductService {
    void add(Product product);
    void delete(Product product);
    Product findById(int id);
    void update(Product product);
    List<Product> findAll();
    List<Product> searchByName(String name);
}
