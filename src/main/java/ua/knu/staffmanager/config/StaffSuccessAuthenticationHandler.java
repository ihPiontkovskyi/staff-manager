package ua.knu.staffmanager.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import ua.knu.staffmanager.entity.Role;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class StaffSuccessAuthenticationHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {
        final Role role = Role.valueOf(authentication.getAuthorities().iterator().next().toString());
        switch (role) {
            case DOCTOR:
                httpServletResponse.sendRedirect("/doctor-home");
                break;
            case INSTRUCTOR:
                httpServletResponse.sendRedirect("/instructor-home");
                break;
            case ADMINISTRATOR:
                httpServletResponse.sendRedirect("/admin-home");
                break;
            default:
                httpServletResponse.sendError(403);
        }
    }
}
