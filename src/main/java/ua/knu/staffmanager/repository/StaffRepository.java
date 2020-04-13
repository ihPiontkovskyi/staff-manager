package ua.knu.staffmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.knu.staffmanager.entity.StaffEntity;

public interface StaffRepository extends JpaRepository<StaffEntity, Integer> {
}
