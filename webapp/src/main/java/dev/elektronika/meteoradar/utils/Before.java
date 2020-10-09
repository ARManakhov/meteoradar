package dev.elektronika.meteoradar.utils;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpSession;

public class Before {

    public static void startPage(ModelMap modelMap, HttpSession session, Model model){
        Attributes.addSuccessAttributes(modelMap,"Successfully visited the page");
        String nickname = (String) session.getAttribute("nickname");
        model.addAttribute("nickname", nickname);
    }
}
