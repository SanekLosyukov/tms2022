package by.teachmeskills.eshop.controllers;

import by.teachmeskills.eshop.services.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/home")
public class HomeController {
    private final CategoryService categoryService;

    public HomeController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    public ModelAndView getHomePage() throws Exception {
        return categoryService.completeAllHomePageData();
    }

}
