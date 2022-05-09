package by.teachmeskills.register.utils;

import by.teachmeskills.register.exceptions.RequestParamNullException;

import java.util.Optional;

public class HttpRequestParamValidator {
    private static final String REQUEST_PARAMETER_IS_NULL_ERROR = "Request parameter is not initialized!";

    public static void validateParamNotNull(String param) throws RequestParamNullException {
        if (!Optional.ofNullable(param).isPresent()) {
            throw new RequestParamNullException(REQUEST_PARAMETER_IS_NULL_ERROR);
        }
    }
}
