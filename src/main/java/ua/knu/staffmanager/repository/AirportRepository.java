package ua.knu.staffmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.knu.staffmanager.entity.AirportEntity;

public interface AirportRepository extends JpaRepository<AirportEntity, Integer> {
}
