package dev.elektronika.meteoradar.controllers;

import dev.elektronika.meteoradar.model.User;
import dev.elektronika.meteoradar.security.details.UserDetailsImpl;
import dev.elektronika.meteoradar.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;
import java.util.Optional;

@Controller
public class ProfileController {
    UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{id}")
    String getUserPage(@PathVariable Long id, Map<String, Object> model, Authentication authentication) {
        Optional<User> profileOwner = userService.findById(id);
        User clientUser = ((UserDetailsImpl) authentication.getPrincipal()).getUser();
        if (profileOwner.isPresent()) {
            model.put("client_user", clientUser);
            model.put("profile_owner", profileOwner.get());
            return "profile";
        } else return "redirect:404";
    }
//
//    @GetMapping("/profile")
//    String getUserPage(Authentication authentication) {
//        User clientUser = ((UserDetailsImpl) authentication.getPrincipal()).getUser();
//        if (clientUser != null) {
//            return "redirect:/user/" + clientUser.getId();
//        }
//        return "redirect:/signIn";
//    }


    @PostMapping("/details/")
    String updateDetails(String text, Authentication authentication) {
        User clientUser = ((UserDetailsImpl) authentication.getPrincipal()).getUser();
        if (clientUser != null) {
            clientUser.setDescription(text);
            userService.add(clientUser);
            return "redirect:/user/" + clientUser.getId();
        }
        return "redirect:/signIn";
    }

}
