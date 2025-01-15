package com.org.application.CRUD.angular.config;

import com.org.application.CRUD.angular.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    Logger logger = LoggerFactory.getLogger(ApplicationConfig.class);

    private final UsuarioRepository usuarioRepository;

    @Bean
    public UserDetailsService userDetailsService(){
        logger.info("[ApplicationConfig] - userDetailsService - SOBREESCRITURA DE UserDetailsService");
        return username -> usuarioRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("No se encontro al usuario en la BD"));
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        logger.info("[ApplicationConfig] - authenticationProvider Dao");
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;

    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        logger.info("[ApplicationConfig] - authenticationProvider configuration");
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        logger.info("[ApplicationConfig] - passwordEncoder");
        return new BCryptPasswordEncoder();
    }
}
