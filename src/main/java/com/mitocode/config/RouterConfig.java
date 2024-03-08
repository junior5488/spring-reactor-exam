package com.mitocode.config;

import com.mitocode.handler.CourseHandler;
import com.mitocode.handler.EnrollmentHandler;
import com.mitocode.handler.StudentHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterConfig {

    //Functional Endpoint
    @Bean
    public RouterFunction<ServerResponse> studentRoutes(StudentHandler handler){
        return route(GET("/v2/students"), handler::findAll)
                .andRoute(GET("/v2/students/{id}"), handler::findById)
                .andRoute(GET("/v2/sortByAgeAsc"), handler::sortByAgeAsc)
                .andRoute(GET("/v2/students/sortByAgeDesc"), handler::sortByAgeDesc)
                .andRoute(POST("/v2/students"), handler::create)
                .andRoute(PUT("/v2/students/{id}"), handler::update)
                .andRoute(DELETE("/v2/students/{id}"), handler::create)
                ;
    }

    @Bean
    public RouterFunction<ServerResponse> courseRoutes(CourseHandler handler){
        return route(GET("/v2/courses"), handler::findAll)
                .andRoute(GET("/v2/courses/{id}"), handler::findById)
                .andRoute(POST("/v2/courses"), handler::create)
                .andRoute(PUT("/v2/courses/{id}"), handler::update)
                .andRoute(DELETE("/v2/courses/{id}"), handler::create);
    }

    @Bean
    public RouterFunction<ServerResponse> enrollmentRoutes(EnrollmentHandler handler){
        return route(GET("/v2/enrollment"), handler::findAll)
                .andRoute(GET("/v2/enrollment/{id}"), handler::findById)
                .andRoute(POST("/v2/enrollment"), handler::create)
                .andRoute(PUT("/v2/enrollment/{id}"), handler::update)
                .andRoute(DELETE("/v2/enrollment/{id}"), handler::create);
    }
}
