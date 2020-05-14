package ua.knu.staffmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.knu.staffmanager.entity.AirportEntity;

@Repository
public interface AirportRepository extends JpaRepository<AirportEntity, Integer> {
}
