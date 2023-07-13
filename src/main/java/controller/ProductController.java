package controller;

import model.Category;
import model.Product;
import repository.ProductRepository;
import repository.ProductRepositoryImpl;
import service.CategoryServiceImpl;
import service.ProductServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(urlPatterns = "/product")
public class ProductController extends HttpServlet {
    private String encoding = "utf-8";
    private ProductServiceImpl productService = new ProductServiceImpl();
    private CategoryServiceImpl categoryService = new CategoryServiceImpl();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action"); // tạo biến action làm gì ?
        if (action == null) {
            action = "list";
        }
        switch (action) {
            case "list":
                viewList(req, resp);
                break;
            case "detail":
                viewDetail(req, resp);
                break;
            case "create":
                viewCreate(req, resp);
                break;
            case "edit":
                viewEdit(req, resp);
                break;
            case "delete":
                viewDelete(req, resp);
                break;

            default:
        }
    }

    private void viewDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Product product = productService.findById(id);
        productService.delete(productService.findById(id));
        req.setAttribute("product", product);
        resp.sendRedirect("/product?action=list");
    }

    private void viewEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Product product = productService.findById(id);
        RequestDispatcher dispatcher;
        if (product == null) {
            dispatcher = req.getRequestDispatcher("error-404.jsp");
        } else {
            req.setAttribute("product", product);
            req.setAttribute("categories", categoryService.findAll());
            req.getRequestDispatcher("WEB-INF/views/product/edit.jsp").forward(req, resp);
        }
    }

    private void viewCreate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> categories = categoryService.findAll();
        req.setAttribute("categories", categories);
        req.getRequestDispatcher("/WEB-INF/views/product/create.jsp").forward(req, resp);
        // Vì sao phải tạo 2 cái create ???
    }

    private void viewDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Product products = productService.findById(id);
        req.setAttribute("products", products);
        req.getRequestDispatcher("/WEB-INF/views/product/detail.jsp").forward(req, resp);
//        resp.sendRedirect("/product?action=list");

    }

    private void viewList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = productService.findAll();
        req.setAttribute("products", products);
        req.getRequestDispatcher("/WEB-INF/views/product/list.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            resp.sendRedirect("/product?action=list");
            return;
        }
        switch (action) {
            case "create":
                doCreate(req, resp);
                break;
            case "edit":
                doEdit(req, resp);
                break;
            case "search":
                doSearch(req,resp);
                break;
            default:
        }
    }

    private void doSearch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nameProduct = req.getParameter("name");
        List<Product> products =  productService.searchByName(nameProduct);
        req.setAttribute("products",products);
        req.getRequestDispatcher("/WEB-INF/views/product/search.jsp").forward(req,resp);
        // khi chuyển trang và đi và kèm truyền dữ liệu thì dùng foward
        // khi chuyển trang không đi kèm dữ liệu thì dùng sendRedirect
//        resp.sendRedirect("/product?action=list");
    }


    private void doEdit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //int id, String name, int quantity, long price, Date dateRelease, Category category, String origin
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        int price = Integer.parseInt(req.getParameter("price"));
        String date = req.getParameter("dateRelease");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dateRelease = null;
        try {
            dateRelease = format.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException();
        }
        int categoryId = Integer.parseInt(req.getParameter("categoryId"));
        Category category = categoryService.findById(categoryId); // ???
//        String origin = req.getParameter("origin");
        Product product = new Product(id, name, quantity, price, dateRelease, category);
        productService.update(product);
        resp.sendRedirect("/product?action=list");
    }


    private void doCreate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        int price = Integer.parseInt(req.getParameter("price"));
        String date = req.getParameter("dateRelease");
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date dateRelease = null;
        try {
            dateRelease = format.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException();
        }
        int categoryId = Integer.parseInt(req.getParameter("categoryId"));
        Category category = categoryService.findById(categoryId); // ???
//        String origin = req.getParameter("origin");
        Product product = new Product(id, name, quantity, price, dateRelease, category);
        productService.add(product);
        resp.sendRedirect("/product?action=list");
    }
}
