package ua.knu.staffmanager.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.knu.staffmanager.entity.Role;
import ua.knu.staffmanager.service.CrewService;
import ua.knu.staffmanager.service.FlightService;

@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AdditionalController {
    private final FlightService flightService;
    private final CrewService crewService;

    @GetMapping("/home")
    public String homePage() {
        final Role role = Role.valueOf(SecurityContextHolder.getContext().getAuthentication().getAuthorities().iterator().next().toString());
        switch (role) {
            case ADMINISTRATOR:
                return "redirect:/admin";
            case DOCTOR:
                return "redirect:/doctor";
            case INSTRUCTOR:
                return "redirect:/instructor";
            default:
                throw new IllegalStateException();
        }
    }

    @GetMapping("/flights")
    public String flightsPage(Model model) {
        model.addAttribute("flights", flightService.findAll());
        return "flight";
    }

    @GetMapping("/crews")
    public String crewsPage(Model model) {
        model.addAttribute("crews", crewService.findAll());
        return "crew";
    }
}
