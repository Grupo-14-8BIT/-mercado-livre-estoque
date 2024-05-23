package com.stock.stock.Security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthConverter jwtAuthConverter;


    @Bean
    public SecurityFilterChain securtyFilterChain (HttpSecurity http) throws Exception   {

        http
                .csrf()
                .disable()
                .authorizeHttpRequests().requestMatchers(HttpMethod.POST, "/token/login").permitAll() // Exclude "/login" from security filter chain
                .anyRequest()
                .authenticated();



        http
                .oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(jwtAuthConverter);

        http
                .sessionManagement()
                .sessionCreationPolicy(STATELESS);



//       http.csrf(csrf -> csrf.disable()).oauth2ResourceServer(oauth2 -> oauth2
//               .jwt(jwt -> jwt.jwtAuthenticationConverter(new JWTConverter())) );
//

//       http.authorizeHttpRequests(authorizeConfig -> {authorizeConfig.anyRequest().authenticated();
//                   authorizeConfig.requestMatchers("/login").permitAll();});

        return http.build();
    }
}