package by.teachmeskills.eshop.controllers;

import by.teachmeskills.eshop.services.ProductService;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/search")
public class SearchController {
    private final ProductService productService;

    public SearchController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ModelAndView searchProduct(@ModelAttribute("search") String search) throws Exception {
        return productService.searchProduct(search);
    }
}
