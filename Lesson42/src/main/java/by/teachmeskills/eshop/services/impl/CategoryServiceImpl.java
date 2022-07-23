package by.teachmeskills.eshop.services.impl;

import by.teachmeskills.eshop.entities.Category;
import by.teachmeskills.eshop.entities.Product;
import by.teachmeskills.eshop.repositories.CategoryRepository;
import by.teachmeskills.eshop.services.CategoryService;
import by.teachmeskills.eshop.services.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

import static by.teachmeskills.eshop.PagesPathEnum.CATEGORY_PAGE;
import static by.teachmeskills.eshop.PagesPathEnum.START_PAGE;
import static by.teachmeskills.eshop.RequestParamsEnum.CATEGORY_PARAM;
import static by.teachmeskills.eshop.RequestParamsEnum.POPULAR_CATEGORIES_LIST_REQ_PARAM;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ProductService productService;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ProductService productService) {
        this.categoryRepository = categoryRepository;
        this.productService = productService;
    }

    @Override
    public Category create(Category entity) {
        return null;
    }

    @Override
    public List<Category> read() throws Exception {
        return categoryRepository.read();
    }

    @Override
    public Category update(Category entity) {
        return null;
    }

    @Override
    public void delete(int id) {
    }

    @Override
    public ModelAndView getCategoryData(int id) throws Exception {
        ModelMap model = new ModelMap();

        Category category = categoryRepository.findById(id);
        if (Optional.ofNullable(category).isPresent()) {
            List<Product> products = productService.getAllForCategory(category.getId());
            category.setProductList(products);
            model.addAttribute(CATEGORY_PARAM.getValue(), category);
        }

        return new ModelAndView(CATEGORY_PAGE.getPath(), model);
    }

    @Override
    public ModelAndView completeAllHomePageData() throws Exception {
        ModelMap model = new ModelMap();

        List<Category> categoriesList = read();

        model.addAttribute(POPULAR_CATEGORIES_LIST_REQ_PARAM.getValue(), categoriesList);

        return new ModelAndView(START_PAGE.getPath(), model);
    }

}
