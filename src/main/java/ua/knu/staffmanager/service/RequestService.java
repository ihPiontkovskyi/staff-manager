package ua.knu.staffmanager.service;

import ua.knu.staffmanager.entity.Request;

import java.util.List;

public interface RequestService {
    List<Request> findAllPastRequests();

    List<Request> findAllActiveRequests();

    void accept(Integer id);

    void deny(Integer id, String cause);

    Request create(Request request);
}
