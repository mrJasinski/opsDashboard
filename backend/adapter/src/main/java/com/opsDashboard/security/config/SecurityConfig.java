package com.opsDashboard.security.config;

import com.opsDashboard.security.filter.CsrfCookieFilter;
import com.opsDashboard.security.filter.JwtAuthFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;

@EnableWebSecurity
@Configuration
class SecurityConfig
{
    private final JwtAuthFilter authFilter;

    SecurityConfig(final JwtAuthFilter authFilter)
    {
        this.authFilter = authFilter;
    }

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception
    {
        var requestHandler = new CsrfTokenRequestAttributeHandler();
        requestHandler.setCsrfRequestAttributeName("_csrf");

        http.securityContext(context -> context.requireExplicitSave(false))
                        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
                                .cors(corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource()
                                {
                                    @Override
                                    public CorsConfiguration getCorsConfiguration(final HttpServletRequest request)
                                    {
                                        var config = new CorsConfiguration();

                                        config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                                        config.setAllowedMethods(Collections.singletonList("*"));
                                        config.setAllowCredentials(true);
                                        config.setAllowedHeaders(Collections.singletonList("*"));
                                        config.setMaxAge(3600L);

                                        return config;
                                    }
                                }))
                .csrf(csrf -> csrf.csrfTokenRequestHandler(requestHandler).ignoringRequestMatchers("/register",
                        "/authenticate", "/dashboard", "/pendingSpecialAccess", "/SAStatusChange**", "/fullRefunds", "/workdays", "/stopWorkday", "/claims"
                        , "/xxx**", "/lowest**", "/claimsByUser**")
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                        .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
                        .addFilterBefore(this.authFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(requests -> requests
                                .requestMatchers("/register", "/authenticate", "/users").permitAll()
                                .requestMatchers("/dashboard", "/pendingSpecialAccess", "/SAStatusChange**", "/fullRefunds", "/workdays"
                                        , "/stopWorkday", "/claims", "/xxx**", "/lowest**", "/claimsByUser**").authenticated())
                        .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
