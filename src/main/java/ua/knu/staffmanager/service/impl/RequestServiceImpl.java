package ua.knu.staffmanager.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ua.knu.staffmanager.entity.Request;
import ua.knu.staffmanager.entity.RequestStatus;
import ua.knu.staffmanager.entity.Staff;
import ua.knu.staffmanager.repository.RequestRepository;
import ua.knu.staffmanager.repository.StaffRepository;
import ua.knu.staffmanager.service.RequestService;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RequestServiceImpl implements RequestService {
    private final RequestRepository repository;
    private final StaffRepository staffRepository;

    @Override
    public List<Request> findAllPastDoctorsRequests() {
        return getRequests(e -> !e.getExaminedByInstructor().equals(RequestStatus.NOT_CONSIDERED)
                , e -> !e.getExaminedByDoctor().equals(RequestStatus.NOT_CONSIDERED));
    }

    @Override
    public List<Request> findAllActiveDoctorsRequests() {
        return getRequests(e -> e.getExaminedByInstructor().equals(RequestStatus.NOT_CONSIDERED)
                , e -> e.getExaminedByDoctor().equals(RequestStatus.NOT_CONSIDERED));
    }

    private List<Request> getRequests(Predicate<Request> instructorCase, Predicate<Request> doctorCase) {
        final Staff current = getCurrentStaff();
        switch (current.getRole()) {
            case DOCTOR:
                return
                        repository.findAllByAssignedDoctor(current)
                                .stream()
                                .filter(doctorCase)
                                .collect(Collectors.toList());
            case INSTRUCTOR:
                return repository.findAllByAssignedInstructor(current)
                        .stream()
                        .filter(instructorCase)
                        .collect(Collectors.toList());
            default:
                return Collections.emptyList();
        }
    }

    private Staff getCurrentStaff() {
        final String identifier = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        return staffRepository.findByIdentifier(identifier).orElseThrow(IllegalStateException::new);
    }
}
