package ua.knu.staffmanager.config;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import ua.knu.staffmanager.domain.Role;
import ua.knu.staffmanager.service.StaffService;

@Configuration
@EnableWebSecurity
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final StaffService provider;
    private final AuthenticationSuccessHandler handler;

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/static/**", "/login").permitAll()
                .antMatchers("/admin-home").hasAuthority(Role.ADMINISTRATOR.name())
                .antMatchers("/doctor-home").hasAuthority(Role.DOCTOR.name())
                .antMatchers("/instructor-home").hasAuthority(Role.INSTRUCTOR.name())
                .antMatchers("/crew-member-home").hasAuthority(Role.CREW_MEMBER.name())

                .and()
                .formLogin()
                .loginPage("/")
                .loginProcessingUrl("/login")
                .usernameParameter("identifier")
                .passwordParameter("pass")
                .successHandler(handler)

                .and()
                .csrf()
                .disable();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/static/**");
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(provider);
    }
}