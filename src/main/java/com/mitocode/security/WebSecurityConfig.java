package com.mitocode.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

//Clase S7
@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity //@PreAuthorize, sirve para habilitar que ciertos metodos sean para ciertos usuarios
@RequiredArgsConstructor
public class WebSecurityConfig { //extends WebSecurityConfigAdapter: deprecado desde Spring Boot 2.7
    private final AuthenticationManager authenticationManager;
    private final SecurityContextRepository securityContextRepository;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityWebFilterChain SecurityWebFilterChain(ServerHttpSecurity http){
        //indicar el comportamiento del filtro que manja Spring Security, se levanta al inicio de la aplicacion y empieza a
        //validar las rutas que protege o libera. Desde Spring Boot 3.1+
        return http
                .exceptionHandling(Customizer.withDefaults())
                .csrf(ServerHttpSecurity.CsrfSpec::disable) //Cross-site request forgery: proteccion de ataques frente a formularios web
                .formLogin(ServerHttpSecurity.FormLoginSpec::disable)
                .httpBasic(Customizer.withDefaults())
                .authenticationManager(authenticationManager)
                .securityContextRepository(securityContextRepository)
                .authorizeExchange(req -> {
                    req.pathMatchers("/login").permitAll();
                    req.pathMatchers("/v2/login").permitAll();
                    req.anyExchange().authenticated();
                }) //aqui van en expresion lambda las rutas que vas a liberar o proteger
                .build();
    }


}
