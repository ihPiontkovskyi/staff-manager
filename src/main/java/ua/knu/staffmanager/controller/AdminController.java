package ua.knu.staffmanager.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.knu.staffmanager.dto.TransferObject;
import ua.knu.staffmanager.entity.Crew;
import ua.knu.staffmanager.entity.Flight;
import ua.knu.staffmanager.entity.Request;
import ua.knu.staffmanager.entity.RequestStatus;
import ua.knu.staffmanager.entity.Role;
import ua.knu.staffmanager.entity.Staff;
import ua.knu.staffmanager.service.CrewService;
import ua.knu.staffmanager.service.FlightService;
import ua.knu.staffmanager.service.RequestService;
import ua.knu.staffmanager.service.StaffService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AdminController {
    private final RequestService service;
    private final StaffService staffService;
    private final FlightService flightService;
    private final CrewService crewService;

    @GetMapping
    public String adminPage(Model model) {
        model.addAttribute("pastRequests", service.findAllPastRequests());
        model.addAttribute("activeRequests", service.findAllActiveRequests());
        return "admin";
    }

    @GetMapping("/create")
    public String createRequests(Model model) {
        return "create-requests";
    }

    @PostMapping("/create")
    public String createRequest(@RequestParam(name = "doctor-id") Integer doctorId,
                                @RequestParam(name = "instructor-id") Integer instructorId,
                                @RequestParam(name = "crew-id") Integer crewId,
                                @RequestParam(name = "flight-id") Integer flightId) {
        final Staff doctor = staffService.findById(doctorId);
        final Staff instructor = staffService.findById(instructorId);
        final Flight flight = flightService.findById(flightId);
        final Crew crew = crewService.findById(crewId);
        final Request request = Request.builder()
                .assignedDoctor(doctor)
                .assignedInstructor(instructor)
                .crew(crew)
                .examinedByDoctor(RequestStatus.NOT_CONSIDERED)
                .examinedByInstructor(RequestStatus.NOT_CONSIDERED)
                .flight(flight)
                .id(0)
                .build();
        service.create(request);
        return "redirect:/admin";
    }

    @GetMapping("/doctors-by-term")
    @ResponseBody
    public List<TransferObject> getDoctorByTerm(@RequestParam(name = "term") String term) {
        return staffService.findStaffByTerm(term, Role.DOCTOR).stream()
                .map(e -> new TransferObject(e.getId(), e.getFullName()))
                .collect(Collectors.toList());
    }

    @GetMapping("/instructors-by-term")
    @ResponseBody
    public List<TransferObject> getInstructorByTerm(@RequestParam(name = "term") String term) {
        return staffService.findStaffByTerm(term, Role.INSTRUCTOR).stream()
                .map(e -> new TransferObject(e.getId(), e.getFullName()))
                .collect(Collectors.toList());
    }

    @GetMapping("/flights-by-term")
    @ResponseBody
    public List<TransferObject> getFlightByTerm(@RequestParam(name = "term") String term) {
        return flightService.findFlightByTerm(term).stream()
                .map(e -> new TransferObject(e.getId(), e.toString()))
                .collect(Collectors.toList());
    }

    @GetMapping("/crew-by-term")
    @ResponseBody
    public List<TransferObject> getFlightByTerm(@RequestParam(name = "term") String term,
                                                @RequestParam(name = "flightId") Integer flightId) {
        return crewService.findFlightByTerm(term).stream()
                .filter(e -> e.getLocation().equals(flightService.findById(flightId).getFrom()))
                .map(e -> new TransferObject(e.getId(), e.toString()))
                .collect(Collectors.toList());
    }
}
