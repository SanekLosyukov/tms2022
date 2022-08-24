package by.teachmeskills.eshop.controllers;

import by.teachmeskills.eshop.config.JwtProvider;
import by.teachmeskills.eshop.dto.AuthResponse;
import by.teachmeskills.eshop.dto.UserCredentialsRequest;
import by.teachmeskills.eshop.entities.User;
import by.teachmeskills.eshop.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthController {
    private final UserService userService;
    private final JwtProvider jwtProvider;

    public AuthController(UserService userService, JwtProvider jwtProvider) {
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody @Valid UserCredentialsRequest registrationRequest) {
        User user = User.builder()
                .login(registrationRequest.getLogin())
                .password(registrationRequest.getPassword())
                .build();
        userService.saveUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/auth")
    public AuthResponse auth(@RequestBody UserCredentialsRequest request) {
        User user = userService.findByLoginAndPassword(request.getLogin(), request.getPassword());
        String token = jwtProvider.generateToken(user.getLogin());
        return new AuthResponse(token);
    }
}
