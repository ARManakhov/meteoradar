package dev.elektronika.meteoradar.controllers;

import dev.elektronika.meteoradar.model.User;
import dev.elektronika.meteoradar.services.UserService;
import dev.elektronika.meteoradar.utils.Attributes;
import dev.elektronika.meteoradar.utils.UserValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Objects;

@Controller
@RequestMapping("/signUp")
@Slf4j
public class SignUpController {

    private final UserService userService;
    private final UserValidator userValidator;
    private final RestTemplate restTemplate;

    @Autowired
    public SignUpController(UserService userService, UserValidator userValidator, RestTemplate restTemplate) {
        this.userService = userService;
        this.userValidator = userValidator;
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public String getSignUp(ModelMap map) {
        return "signUp";
    }

    @PostMapping
    public String signUp(User user, BindingResult result, ModelMap model) { //todo make signUp grate again

        String error = "";
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            error += "This mail is already taken! ";
        }
        if (error.isEmpty()) {
            Attributes.addSuccessAttributes(model, "A confirmation letter will come to your mail soon!");
            userService.register(user);
            log.info("User registered: " + user.getEmail());
        }else{
            Attributes.addErrorAttributes(model, error);
        }
        return "/signUp";
    }
}
