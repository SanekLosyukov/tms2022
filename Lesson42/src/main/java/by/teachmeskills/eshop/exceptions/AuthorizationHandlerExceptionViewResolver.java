package by.teachmeskills.eshop.exceptions;

import org.springframework.ui.ModelMap;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.teachmeskills.eshop.PagesPathEnum.ERROR_PAGE;
import static by.teachmeskills.eshop.RequestParamsEnum.ERROR_PARAM;

public class AuthorizationHandlerExceptionViewResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        if (handler instanceof HandlerMethod hm) {
            ErrorView errorView = hm.getMethodAnnotation(ErrorView.class);
            if (errorView != null) {
                String viewName = errorView.value();
                ModelMap modelMap = new ModelMap();
                modelMap.addAttribute(ERROR_PARAM.getValue(), ex.getMessage());

                ModelAndView model = new ModelAndView(viewName);
                model.setViewName(ERROR_PAGE.getPath());
                model.addAllObjects(modelMap);

                return model;
            }
        }

        //returning null for default processing
        return null;
    }
}
