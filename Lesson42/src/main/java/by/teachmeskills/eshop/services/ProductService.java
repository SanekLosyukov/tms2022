package by.teachmeskills.eshop.services;

import by.teachmeskills.eshop.entities.Product;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public interface ProductService extends BaseService<Product> {
    List<Product> getAllForCategory(int categoryId) throws Exception;

    ModelAndView getProductData(int id) throws Exception;

    ModelAndView searchProduct(String search) throws Exception;
}
