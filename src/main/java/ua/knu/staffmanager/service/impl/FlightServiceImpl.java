package ua.knu.staffmanager.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.knu.staffmanager.entity.Flight;
import ua.knu.staffmanager.repository.FlightRepository;
import ua.knu.staffmanager.service.FlightService;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class FlightServiceImpl implements FlightService {
    private final FlightRepository repository;

    @Override
    public List<Flight> findFlightByTerm(String term) {
        return repository.findAllByIdentifierStartingWith(term);
    }

    @Override
    public List<Flight> findAll() {
        return repository.findAll();
    }

    @Override
    public Flight findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
    }
}
