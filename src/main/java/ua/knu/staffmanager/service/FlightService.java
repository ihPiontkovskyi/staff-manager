package ua.knu.staffmanager.service;

import ua.knu.staffmanager.entity.Flight;

import java.util.List;

public interface FlightService {
    List<Flight> findFlightByTerm(String term);

    List<Flight> findAll();

    Flight findById(Integer id);
}
