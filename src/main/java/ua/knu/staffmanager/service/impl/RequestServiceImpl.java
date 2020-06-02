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
import java.util.NoSuchElementException;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static ua.knu.staffmanager.entity.RequestStatus.ACCEPTED;
import static ua.knu.staffmanager.entity.RequestStatus.DENIED;
import static ua.knu.staffmanager.entity.RequestStatus.NOT_CONSIDERED;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RequestServiceImpl implements RequestService {
    private final RequestRepository repository;
    private final StaffRepository staffRepository;

    @Override
    public List<Request> findAllPastRequests() {
        return getRequests(
                e -> !e.getExaminedByInstructor().equals(NOT_CONSIDERED) || e.getExaminedByDoctor().equals(DENIED),
                e -> !e.getExaminedByDoctor().equals(NOT_CONSIDERED),
                e -> !e.getExaminedByDoctor().equals(NOT_CONSIDERED) || (e.getExaminedByDoctor().equals(ACCEPTED) && !e.getExaminedByInstructor().equals(NOT_CONSIDERED)));
    }

    @Override
    public List<Request> findAllActiveRequests() {
        return getRequests(
                e -> e.getExaminedByInstructor().equals(NOT_CONSIDERED) && e.getExaminedByDoctor().equals(ACCEPTED),
                e -> e.getExaminedByDoctor().equals(NOT_CONSIDERED),
                e -> e.getExaminedByDoctor().equals(NOT_CONSIDERED) || (e.getExaminedByDoctor().equals(ACCEPTED) && e.getExaminedByInstructor().equals(NOT_CONSIDERED)));
    }

    @Override
    public void accept(Integer id) {
        final Staff current = getCurrentStaff();
        Request request = repository.findById(id).orElseThrow(NoSuchElementException::new);
        switch (current.getRole()) {
            case INSTRUCTOR:
                request.setExaminedByInstructor(RequestStatus.ACCEPTED);
                repository.save(request);
                break;
            case DOCTOR:
                request.setExaminedByDoctor(RequestStatus.ACCEPTED);
                repository.save(request);
                break;
            default:
                throw new IllegalStateException();
        }
    }

    @Override
    public void deny(Integer id, String cause) {
        final Staff current = getCurrentStaff();
        Request request = repository.findById(id)
                .orElseThrow(NoSuchElementException::new);
        switch (current.getRole()) {
            case INSTRUCTOR:
                request.setExaminedByInstructor(RequestStatus.DENIED);
                request.setRejectedCause(cause);
                repository.save(request);
                break;
            case DOCTOR:
                request.setExaminedByDoctor(RequestStatus.DENIED);
                request.setRejectedCause(cause);
                repository.save(request);
                break;
            default:
                throw new IllegalStateException();
        }
    }

    @Override
    public Request create(Request request) {
        return repository.save(request);
    }

    private List<Request> getRequests(Predicate<Request> instructorCase, Predicate<Request> doctorCase,
                                      Predicate<Request> adminCase) {
        final Staff current = getCurrentStaff();
        switch (current.getRole()) {
            case DOCTOR:
                return repository.findAllByAssignedDoctor(current)
                        .stream()
                        .filter(doctorCase)
                        .collect(Collectors.toList());
            case INSTRUCTOR:
                return repository.findAllByAssignedInstructor(current)
                        .stream()
                        .filter(instructorCase)
                        .collect(Collectors.toList());
            case ADMINISTRATOR:
                return repository.findAll()
                        .stream()
                        .filter(adminCase)
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
