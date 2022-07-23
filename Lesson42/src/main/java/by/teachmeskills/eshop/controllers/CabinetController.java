package by.teachmeskills.eshop.controllers;

import by.teachmeskills.eshop.entities.Order;
import by.teachmeskills.eshop.services.OrderService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import static by.teachmeskills.eshop.EshopConstants.ORDER;
import static by.teachmeskills.eshop.EshopConstants.PRODUCT_ID_PARAM;
import static by.teachmeskills.eshop.PagesPathEnum.CABINET_PAGE;

@RestController
@SessionAttributes({ORDER})
@RequestMapping("/cabinet")
public class CabinetController {
    private static final String ORDER = "order";

    private final OrderService orderService;

    public CabinetController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/add")
    public ModelAndView addProductToOrder(@RequestParam(PRODUCT_ID_PARAM) String id, @ModelAttribute(ORDER) Order order) throws Exception {
        int productId = Integer.parseInt(id);
        return orderService.addProductToOrder(productId, order);
    }

    @GetMapping("/open")
    public ModelAndView redirectToCabinet() {
        return new ModelAndView(CABINET_PAGE.getPath());
    }

    @ModelAttribute(ORDER)
    public Order order() {
        return new Order();
    }
}
