package ua.knu.staffmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.knu.staffmanager.entity.CrewEntity;

public interface CrewRepository extends JpaRepository<CrewEntity, Integer> {
}
