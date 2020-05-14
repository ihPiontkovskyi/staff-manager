package ua.knu.staffmanager.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.knu.staffmanager.entity.AirportEntity;
import ua.knu.staffmanager.entity.RoleEntity;
import ua.knu.staffmanager.entity.StaffEntity;
import ua.knu.staffmanager.repository.AirportRepository;
import ua.knu.staffmanager.repository.StaffRepository;
import ua.knu.staffmanager.service.StaffService;
import ua.knu.staffmanager.service.exception.LoginFailedException;
import ua.knu.staffmanager.service.mapper.StaffMapper;

import static java.util.Collections.singletonList;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class StaffServiceImpl implements StaffService {

    private static final String INCORRECT_DATA = "incorrect.data";
    private final StaffRepository staffRepository;
    private final AirportRepository airportRepository;
    private final StaffMapper staffMapper;
    private final PasswordEncoder encoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String identifier = authentication.getName();
        String password = (String) authentication.getCredentials();
        StaffEntity staffMember = staffRepository.findByIdentifier(identifier).
                orElseThrow(() -> new LoginFailedException("user.does.not.exists"));

        if (encoder.matches(password, staffMember.getPassword())) {
            return new UsernamePasswordAuthenticationToken(staffMember.getIdentifier(), staffMember.getPassword(), singletonList(new SimpleGrantedAuthority(staffMember.getRoleEntity().name())));
        }
        throw new LoginFailedException("incorrect.identifier.or.password");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
