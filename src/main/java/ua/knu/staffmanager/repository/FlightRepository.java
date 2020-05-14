package ua.knu.staffmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.knu.staffmanager.entity.Request;

@Repository
public interface FlightRepository extends JpaRepository<Request, Integer> {
}
