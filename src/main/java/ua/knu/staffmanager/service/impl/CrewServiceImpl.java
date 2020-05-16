package ua.knu.staffmanager.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.knu.staffmanager.entity.Crew;
import ua.knu.staffmanager.entity.Flight;
import ua.knu.staffmanager.repository.CrewRepository;
import ua.knu.staffmanager.service.CrewService;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CrewServiceImpl implements CrewService {

    private final CrewRepository repository;

    @Override
    public List<Crew> findFlightByTerm(String term) {
        return repository.findAllByIdentifierStartingWith(term);
    }

    @Override
    public Crew findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
    }
}
