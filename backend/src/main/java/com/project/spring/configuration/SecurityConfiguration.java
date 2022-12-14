package com.project.spring.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.spring.filter.AuthenticationFilter;
import com.project.spring.filter.LoginFilter;
import com.project.spring.model.mapper.ApplicationUserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true )
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final ObjectMapper objectMapper;
    private final ApplicationUserMapper applicationUserMapper;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                    .csrf().disable()
                    .cors()
                .and()
                    .authorizeRequests()
//                        .antMatchers("/**").permitAll()
                        .antMatchers(HttpMethod.GET, "/api/doctor").permitAll()
                        .antMatchers(HttpMethod.POST, "/api/user").permitAll()
                        .antMatchers(HttpMethod.GET, "/api/test/public").permitAll()                // dost??pny dla ka??dego
                        .antMatchers("/api/test/anyone").authenticated()            // dla dowolnej osoby kt??ra jest zalogowana
                        .antMatchers("/api/test/moderator").hasRole("MODERATOR")    // dla dowolnej osoby kt??ra jest zalogowana z rol?? moderator
                        .antMatchers("/api/test/admin").hasRole("ADMIN")            // dla dowolnej osoby kt??ra jest zalogowana z rol?? admin
                        .antMatchers("/api/test/secured").permitAll()               // weryfikacja uprawnie?? odbywa si?? w kontrolerze
                        .anyRequest().authenticated()
                .and()
                    .addFilter(loginFilter(authenticationManager(), applicationUserMapper, objectMapper))       // /login
                    .addFilter(new AuthenticationFilter(authenticationManager()))                               // nie /login
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    private LoginFilter loginFilter(AuthenticationManager authenticationManager,
                                    ApplicationUserMapper applicationUserMapper,
                                    ObjectMapper objectMapper) {
        return new LoginFilter(
                authenticationManager,
                applicationUserMapper,
                objectMapper);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

}
