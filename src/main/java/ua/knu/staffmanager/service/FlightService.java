package ua.knu.staffmanager.service;

import ua.knu.staffmanager.entity.Flight;

import java.util.List;
import java.util.Optional;

public interface FlightService {
    List<Flight> findFlightByTerm(String term);

    Flight findById(Integer id);
}
