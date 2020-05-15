package ua.knu.staffmanager.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.knu.staffmanager.service.RequestService;

@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DoctorController {

    private final RequestService service;

    @GetMapping(value = {"/doctor-home"})
    public String doctorPage(Model model) {
        model.addAttribute("pastRequests", service.findAllPastRequests());
        model.addAttribute("activeRequests", service.findAllActiveRequests());
        return "doctor-home";
    }
}
