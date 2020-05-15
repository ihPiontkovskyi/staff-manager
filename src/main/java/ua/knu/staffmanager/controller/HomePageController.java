package ua.knu.staffmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {

    @GetMapping(value = {"/admin-home"})
    public String adminPage(Model model) {
        return "admin-home";
    }

}
