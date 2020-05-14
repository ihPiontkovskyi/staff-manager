package ua.knu.staffmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.knu.staffmanager.entity.FlightEntity;

@Repository
public interface FlightRepository extends JpaRepository<FlightEntity, Integer> {
}
