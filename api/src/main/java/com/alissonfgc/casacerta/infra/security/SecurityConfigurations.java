package com.alissonfgc.casacerta.infra.security;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    final
    SecurityFilter securityFilter;

    private static final String[] WHITE_LIST_URL = { "docs/api/v1/auth/**", "docs/v2/api-docs/**", "docs/v3/api-docs/**",
            "docs/v3/api-docs/**", "docs/swagger-resources", "docs/swagger-resources/**", "docs/configuration/ui",
            "docs/configuration/security", "docs/swagger-ui/**", "docs/webjars/**", "docs/swagger-ui.html", "/api/auth/**",
            "docs/api/test/**", "/h2-console/**" };

    private static final String[] SECUNDARY_LIST_URL = { "/auth/login", "/auth/register", "/auth/seller/register",
            "/auth/seller/login" };

    private static final String[] CLENT_LIST_URL = { "/users/**" };

    private static final String[] SELLER_LIST_URL = { "/vendors/**" };

    private static final String[] ADMIN_LIST_URL = { "/admins**" };

    public SecurityConfigurations(SecurityFilter securityFilter) {
        this.securityFilter = securityFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, SECUNDARY_LIST_URL).permitAll()
                        .requestMatchers(WHITE_LIST_URL).permitAll()
                        .requestMatchers(CLENT_LIST_URL).hasRole("CLIENT")
                        .requestMatchers(SELLER_LIST_URL).hasRole("SELLER")
                        .requestMatchers(ADMIN_LIST_URL).hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

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
