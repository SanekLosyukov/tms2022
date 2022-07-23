package by.teachmeskills.eshop.services;

import by.teachmeskills.eshop.entities.Order;
import by.teachmeskills.eshop.entities.Product;
import by.teachmeskills.eshop.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import static by.teachmeskills.eshop.EshopConstants.ORDER;
import static by.teachmeskills.eshop.PagesPathEnum.CABINET_PAGE;
import static by.teachmeskills.eshop.RequestParamsEnum.PRODUCT;

@Service
public class OrderService {
    private final ProductRepository productRepository;

    public OrderService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ModelAndView addProductToOrder(int productId, Order order) throws Exception {
        ModelMap modelOrder = new ModelMap();

        Product product = productRepository.findById(productId);
        order.addProduct(product);
        modelOrder.addAttribute(PRODUCT.getValue(), product);
        modelOrder.addAttribute(ORDER, order);

        return new ModelAndView(CABINET_PAGE.getPath(), modelOrder);
    }
}
