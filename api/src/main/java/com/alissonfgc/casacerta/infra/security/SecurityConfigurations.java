package com.alissonfgc.casacerta.infra.security;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    private static final String[] WHITE_LIST_URL = { "docs/api/v1/auth/**", "docs/v2/api-docs/**", "docs/v3/api-docs/**",
            "docs/v3/api-docs/**", "docs/swagger-resources", "docs/swagger-resources/**", "docs/configuration/ui",
            "docs/configuration/security", "docs/swagger-ui/**", "docs/webjars/**", "docs/swagger-ui.html", "/api/auth/**",
            "docs/api/test/**", "/h2-console/**" };

    private static final String[] SECUNDARY_LIST_URL = { "/auth/login", "/auth/register", "/auth/seller/register",
            "/auth/seller/login" };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(WHITE_LIST_URL).permitAll()
                        .requestMatchers(SECUNDARY_LIST_URL).permitAll() //HttpMethod.OPTIONS,
                        .anyRequest().authenticated()
                )
                .build();
    }git

    @Bean
    WebSecurityCustomizer ignoringCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/h2-console/**", "/swagger-ui/**", "/v3/api-docs/**");
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
