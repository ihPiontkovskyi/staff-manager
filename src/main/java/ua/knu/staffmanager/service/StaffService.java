package ua.knu.staffmanager.service;

import org.springframework.security.authentication.AuthenticationProvider;
import ua.knu.staffmanager.entity.Role;
import ua.knu.staffmanager.entity.Staff;

import java.util.List;

public interface StaffService extends AuthenticationProvider {
    List<Staff> findStaffByTerm(String term, Role role);

    Staff findById(Integer id);
}
