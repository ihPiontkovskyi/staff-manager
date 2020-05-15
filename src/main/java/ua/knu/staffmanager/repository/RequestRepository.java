package ua.knu.staffmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.knu.staffmanager.entity.Request;
import ua.knu.staffmanager.entity.Staff;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Integer> {
    List<Request> findAllByAssignedDoctor(Staff doctor);

    List<Request> findAllByAssignedInstructor(Staff instructor);
}
