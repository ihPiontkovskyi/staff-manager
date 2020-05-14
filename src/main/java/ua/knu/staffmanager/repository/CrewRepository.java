package ua.knu.staffmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.knu.staffmanager.entity.CrewEntity;

@Repository
public interface CrewRepository extends JpaRepository<CrewEntity, Integer> {
}
