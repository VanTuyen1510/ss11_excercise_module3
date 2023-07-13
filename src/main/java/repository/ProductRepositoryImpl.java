package repository;

import model.Category;
import model.Product;
import util.Constants;
import util.DatabaseConnection;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class ProductRepositoryImpl implements ProductRepository {
    private CategoryRepository categoryRepository = new CategoryRepositoryImpl();

    @Override
    public void add(Product product) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(Constants.CREATE_PRODUCT);
        ) {
            statement.setInt(1, product.getId());
            statement.setString(2, product.getName());
            statement.setInt(3, product.getQuantity());
            statement.setLong(4, product.getPrice());
            statement.setDate(5, new Date(product.getDateRelease().getTime()));
            statement.setInt(6, product.getCategory().getId());
            statement.executeUpdate(); // thực thi câu lệnh đó chạy câu lệnh đó
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void delete(Product product) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(Constants.DELETE_PRODUCT_BY_ID);
        ) {
            statement.setInt(1, product.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product findById(int id) {
        Product product = null;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(Constants.FIND_PRODUCT_BY_ID);
             //Prepared tốc độ sẽ nhanh hơn
        ) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int quantity = resultSet.getInt("quantity");
                long price = resultSet.getLong("price");
                // Date import được 1 thằng thôi để viết tắt
                // getDate trả SQL date
                Date dateRelease = new Date(resultSet.getDate("date_release").getTime());
                int categoryId = resultSet.getInt("category_id");
                Category category = categoryRepository.findById(categoryId);

                product = new Product(id, name, quantity, price, dateRelease, category);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return product;
    }

    @Override
    public void update(Product product) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(Constants.UPDATE_PRODUCT);
        ) {
            statement.setInt(6, product.getId());
            statement.setString(1, product.getName());
            statement.setInt(2, product.getQuantity());
            statement.setLong(3, product.getPrice());
            statement.setDate(4, new Date(product.getDateRelease().getTime()));
            statement.setInt(5, product.getCategory().getId());


            statement.executeUpdate(); // thực thi câu lệnh đó chạy câu lệnh đó
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(Constants.FIND_ALL_PRODUCT);
             //Prepared tốc độ sẽ nhanh hơn
        ) {
            ResultSet resultSet = statement.executeQuery();
            Product product;
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int quantity = resultSet.getInt("quantity");
                long price = resultSet.getLong("price");
                java.util.Date dateRelease = new java.util.Date(resultSet.getDate("date_release").getTime());
                // vì getDate trả về Date SQL nên phải convert sang java
                int categoryId = resultSet.getInt("category_id");
                Category category = categoryRepository.findById(categoryId);

                product = new Product(id, name, quantity, price, dateRelease, category);
                products.add(product);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

//    @Override
//    public List<Product> searchByName(String name) {
//        return null;
//    }

    @Override
    public List<Product> searchByName(String name) {
        List<Product> products = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constants.SEARCH_NAME_SQL);) {
            System.out.println(preparedStatement);
            preparedStatement.setString(1, "%" + name + "%");
            System.out.println(preparedStatement);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name2 = resultSet.getString("name");
                int quantity = resultSet.getInt("quantity");
                long price = resultSet.getLong("price");
                // Date import được 1 thằng thôi để viết tắt
                // getDate trả SQL date
                Date dateRelease = new Date(resultSet.getDate("date_release").getTime());
                int categoryId = resultSet.getInt("category_id");
                Category category = categoryRepository.findById(categoryId);

              Product product = new Product(id, name2, quantity, price, dateRelease, category);
              products.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return products;
    }



//    private static Map<Integer, Product> productMap;
//
//    static {
//        productMap = new HashMap<>();
//        CategoryRepository categoryRepository = new CategoryRepositoryImpl();
//        productMap.put(1, new Product(1, "Laptop Dell N1440", 2, 12000000, new Date(), categoryRepository.findById(1), "Trung Quốc"));
//        productMap.put(2, new Product(2, "Laptop Asus Zenbook", 4, 13000000, new Date(), categoryRepository.findById(1), "Mỹ"));
//        productMap.put(3, new Product(3, "Iphone 14 pro max", 3, 26000000, new Date(), categoryRepository.findById(2), "Nga"));
//        productMap.put(4, new Product(4, "Samsung ZFlip", 5, 20000000, new Date(), categoryRepository.findById(2), "Trung Quốc"));
//        productMap.put(5, new Product(5, "LG Oled C2 48inch", 2, 20000000, new Date(), categoryRepository.findById(3), "Nga"));
//    }
//
//    @Override
//    public void add(Product product) {
//        productMap.put(product.getId(), product);
//    }
//
//    @Override
//    public void delete(Product product) {
//        productMap.remove(product.getId());
//    }
//
//    @Override
//    public Product findById(int id) {
//        return productMap.get(id);
//    }
//
//    @Override
//    public void update(Product product) {
//        productMap.put(product.getId(), product);
//    }
//
//    @Override
//    public List<Product> findAll() {
//        return new ArrayList<>(productMap.values());
//    }
//
//    @Override
//    public List<Product> searchByName(String name) {
//        List<Product> products = new ArrayList<>();
//        // List trả về 1 list để tìm kiếm những name trong list
//        // Product là trả về 1 sản phẩm
//
//        for (Map.Entry<Integer,Product> entry: productMap.entrySet()) {
//            if(entry.getValue().getName().trim().contains(name)){
//                System.out.println("Sản phẩm bạn muốn tìm là" + productMap.get(entry.getKey()));
//                products.add(entry.getValue());
//            }
//        }
//        return products;
//    }


    }
