package util;

public class Constants {
    public static final String FIND_ALL_CATEGORY = "Select * from category";
    public static final String FIND_CATEGORY_BY_ID = "Select * from category where id = ?";
    public static final String FIND_ALL_PRODUCT = "select * from product";
    public static final String FIND_PRODUCT_BY_ID = "select * from product where id = ?";
    public static final String CREATE_PRODUCT = "insert into product values(?,?,?,?,?,?)";
    public static final String UPDATE_PRODUCT = "Update product set name = ? , quantity = ? , price = ? , date_release = ? , category_id = ? where id = ?";
    // truyền id vào update sẽ nằm cuối
    public static final String DELETE_PRODUCT_BY_ID = "Delete from product where id = ?";
    public static final String SEARCH_NAME_SQL = "select * from product where name like ?";
}
