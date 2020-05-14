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

    @GetMapping(value = {"/instructor-home"})
    public String instructorPage(Model model) {
        return "instructor-home";
    }

    @GetMapping(value = {"/crew-member-home"})
    public String crewPage(Model model) {
        return "crew-member-home";
    }

    @GetMapping(value = {"/doctor-home"})
    public String doctorPage(Model model) {
        return "doctor-home";
    }
}
