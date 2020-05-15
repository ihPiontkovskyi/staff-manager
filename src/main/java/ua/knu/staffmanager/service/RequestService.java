package ua.knu.staffmanager.service;

import ua.knu.staffmanager.entity.Request;

import java.util.List;

public interface RequestService {
    List<Request> findAllPastDoctorsRequests();

    List<Request> findAllActiveDoctorsRequests();
}
