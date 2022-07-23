package by.teachmeskills.eshop.controllers;

import by.teachmeskills.eshop.entities.User;
import by.teachmeskills.eshop.exceptions.AuthorizationException;
import by.teachmeskills.eshop.exceptions.ErrorView;
import by.teachmeskills.eshop.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Objects;

import static by.teachmeskills.eshop.EshopConstants.USER;
import static by.teachmeskills.eshop.PagesPathEnum.SIGN_IN_PAGE;

@RestController
@SessionAttributes({USER})
@RequestMapping("/login")
public class AuthController {
    private final UserService userService;
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ModelAndView openLoginPage() {
        return new ModelAndView(SIGN_IN_PAGE.getPath());
    }

    @ErrorView(value = "error", status = HttpStatus.FORBIDDEN)
    @PostMapping
    public ModelAndView login(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, ModelAndView modelAndView) throws AuthorizationException, AuthorizationException, Exception {
        if (bindingResult.hasErrors()) {
            populateError("email", modelAndView, bindingResult);
            populateError("password", modelAndView, bindingResult);
            modelAndView.setViewName(SIGN_IN_PAGE.getPath());
            return modelAndView;
        }

        return userService.authenticate(user);
    }

    @ModelAttribute(USER)
    public User setUpUserForm() {
        return new User();
    }

    private void populateError(String field, ModelAndView modelAndView, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors(field)) {
            modelAndView.addObject(field + "Error", Objects.requireNonNull(bindingResult.getFieldError(field))
                    .getDefaultMessage());
        }
    }
}
