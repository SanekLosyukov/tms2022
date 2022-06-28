package by.teachmeskills.eshop.commands;

import by.teachmeskills.eshop.entities.Category;
import by.teachmeskills.eshop.entities.User;
import by.teachmeskills.eshop.exceptions.CommandException;
import by.teachmeskills.eshop.repositories.impl.UserRepositoryImpl;
import by.teachmeskills.eshop.services.impl.CategoryServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static by.teachmeskills.eshop.utils.PagesPathEnum.HOME_PAGE;
import static by.teachmeskills.eshop.utils.PagesPathEnum.SIGN_IN_PAGE;
import static by.teachmeskills.eshop.utils.RequestParamsEnum.*;

public class SignInCommandImpl implements BaseCommand {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String name = request.getParameter(NAME.getValue());
        String surname = request.getParameter(SURNAME.getValue());
        String email = request.getParameter(EMAIL.getValue());
        String password = request.getParameter(PASSWORD.getValue());
        String date = request.getParameter(DATE.getValue());

        User validateUser = new User(name, surname, email, password, date);

        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        List<User> usersFromDB = userRepository.read();

        for (User user : usersFromDB) {
            if (user.equals(validateUser)) {
                HttpSession session = request.getSession();
                session.setAttribute(USER.getValue(), validateUser);

                CategoryServiceImpl categoryService = new CategoryServiceImpl();
                List<Category> categories = categoryService.read();
                session.setAttribute(CATEGORIES.getValue(), categories);

                return HOME_PAGE.getPath();
            } else {
                return SIGN_IN_PAGE.getPath();
            }
        }
        return HOME_PAGE.getPath();
    }
}
