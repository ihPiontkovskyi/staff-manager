package ua.knu.staffmanager.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.knu.staffmanager.service.RequestService;

@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class InstructorController {
    private final RequestService service;

    @GetMapping(value = {"/instructor-home"})
    public String doctorPage(Model model) {
        model.addAttribute("pastRequests", service.findAllPastDoctorsRequests());
        model.addAttribute("activeRequests", service.findAllActiveDoctorsRequests());
        return "instructor-home";
    }
}
