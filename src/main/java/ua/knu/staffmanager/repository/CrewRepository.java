package ua.knu.staffmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.knu.staffmanager.entity.Crew;

import java.util.List;

@Repository
public interface CrewRepository extends JpaRepository<Crew, Integer> {
    List<Crew> findAllByIdentifierStartingWith(String term);
}
