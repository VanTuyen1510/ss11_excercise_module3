package repository;

import model.Category;
import util.Constants;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryRepositoryImpl implements CategoryRepository {
    // Câu lệnh SQL là câu lệnh tĩnh nên khai báo final cho nó:
    public static final String FIND_ALL_CATEGORY = "Select * from category";
    public static final String FIND_CATEGORY_BY_ID = "Select * from category" + "where id = ?";

    @Override
    public Category findById(int id) {
        Category category = null;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(Constants.FIND_CATEGORY_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            // id 1 có hay không nên dùng if ,trả về 1 list dùng while
            if(resultSet.next()){
             // id không cần khai báo nên không cần get ra
                String name = resultSet.getString("name");
                category = new Category(id, name);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }

    @Override
    public List<Category> findAll() {
        // Statement ,Prepare Statement khác nhau là 1 bên bị lỗi SQL ,1 bên thì không ,hàm findAll không truyền gì vào nên dùng statement
        List<Category> categories = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {
            // ResultSet lấy dữ liệu trả về
            ResultSet resultSet = statement.executeQuery(Constants.FIND_ALL_CATEGORY);
            Category category;
            // Dùng while vì chưa biết số lần lặp
            while (resultSet.next()) {
                // điền tên của cột đó
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                category = new Category(id, name);
                categories.add(category);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categories;
    }


//    private static Map<Integer,Category> categoryMap = new HashMap<>();
//    static {
//        categoryMap.put(1, new Category(1, "Laptop"));
//        categoryMap.put(2, new Category(2, "Smartphone"));
//        categoryMap.put(3, new Category(3, "Tivi"));
//        categoryMap.put(4, new Category(4, "Tablet"));
//    }

//    @Override
//    public Category findById(int id) {
//        return categoryMap.get(id);
//    }
//
//    @Override
//    public List<Category> findAll() {
//        return new ArrayList<>(categoryMap.values());
//    }
}
