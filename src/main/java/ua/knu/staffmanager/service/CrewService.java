package ua.knu.staffmanager.service;

import ua.knu.staffmanager.entity.Crew;

import java.util.List;

public interface CrewService {
    List<Crew> findFlightByTerm(String term);

    Crew findById(Integer id);
}
