package ua.knu.staffmanager.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.knu.staffmanager.entity.Flight;
import ua.knu.staffmanager.entity.Role;
import ua.knu.staffmanager.entity.Staff;
import ua.knu.staffmanager.repository.StaffRepository;
import ua.knu.staffmanager.service.StaffService;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.singletonList;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;
    private final PasswordEncoder encoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String identifier = authentication.getName();
        String password = (String) authentication.getCredentials();

        Staff staffMember = staffRepository.findByIdentifier(identifier).
                orElseThrow(() -> new IllegalArgumentException("user.does.not.exists"));

        if (encoder.matches(password, staffMember.getPassword())) {
            return new UsernamePasswordAuthenticationToken(staffMember.getIdentifier(), staffMember.getPassword(),
                    singletonList(new SimpleGrantedAuthority(staffMember.getRole().name())));
        }
        throw new IllegalArgumentException("incorrect.identifier.or.password");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }

    public List<Staff> findStaffByTerm(String term, Role role) {
        return staffRepository.findAllByFullNameStartingWith(term)
                .stream()
                .filter(e -> e.getRole().equals(role))
                .collect(Collectors.toList());
    }

    @Override
    public Staff findById(Integer id) {
        return staffRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
    }
}
