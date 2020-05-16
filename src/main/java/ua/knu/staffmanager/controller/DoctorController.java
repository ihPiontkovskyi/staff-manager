package ua.knu.staffmanager.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import ua.knu.staffmanager.service.RequestService;

@Controller
@RequestMapping("/doctor")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DoctorController {

    private final RequestService service;

    @GetMapping
    public String doctorPage(Model model) {
        model.addAttribute("pastRequests", service.findAllPastRequests());
        model.addAttribute("activeRequests", service.findAllActiveRequests());
        return "doctor";
    }

    @PostMapping(value = "/accept/{id}")
    public RedirectView acceptRequest(@PathVariable Integer id) {
        service.accept(id);
        return new RedirectView("/doctor-home");
    }

    @PostMapping(value = "/deny/{id}")
    public String denyRequest(@PathVariable Integer id, @RequestParam(name = "cause") String cause) {
        service.deny(id, cause);
        return "redirect:/doctor";
    }


}
