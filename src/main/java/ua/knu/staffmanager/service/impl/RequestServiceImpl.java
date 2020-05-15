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

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RequestServiceImpl {
    private final RequestRepository repository;
    private final StaffRepository staffRepository;

    public List<Request> findAllPastRequests() {
        final Staff current = getCurrentStaff();
        switch (current.getRole()) {
            case DOCTOR:
                return
                        repository.findAllByAssignedDoctor(current)
                                .stream()
                                .filter(e -> !e.getExaminedByDoctor().equals(RequestStatus.NOT_CONSIDERED))
                                .collect(Collectors.toList());
            case INSTRUCTOR:
                return repository.findAllByAssignedInstructor(current)
                        .stream()
                        .filter(e -> !e.getExaminedByDoctor().equals(RequestStatus.NOT_CONSIDERED))
                        .collect(Collectors.toList());
            default:
                return Collections.emptyList();
        }
    }

    public List<Request> findAllActiveRequests() {
        final Staff current = getCurrentStaff();
        switch (current.getRole()) {
            case DOCTOR:
                return
                        repository.findAllByAssignedDoctor(current)
                                .stream()
                                .filter(e -> e.getExaminedByDoctor().equals(RequestStatus.NOT_CONSIDERED))
                                .collect(Collectors.toList());
            case INSTRUCTOR:
                return repository.findAllByAssignedInstructor(current)
                        .stream()
                        .filter(e -> e.getExaminedByDoctor().equals(RequestStatus.NOT_CONSIDERED))
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
