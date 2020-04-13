package ua.knu.staffmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.knu.staffmanager.entity.FlightEntity;

public interface FlightRepository extends JpaRepository<FlightEntity, Integer> {
}
