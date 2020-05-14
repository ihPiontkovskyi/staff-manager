package ua.knu.staffmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.knu.staffmanager.entity.StaffEntity;

import java.util.Optional;

@Repository
public interface StaffRepository extends JpaRepository<StaffEntity, Integer> {
    Optional<StaffEntity> findByIdentifier(String identifier);
}
