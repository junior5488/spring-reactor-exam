package com.mitocode.service;

import com.mitocode.model.Student;
import reactor.core.publisher.Flux;

import java.util.List;

public interface IStudentService extends ICRUD<Student, String>{
    Flux<Student> sortByAgeAsc();
    Flux<Student> sortByAgeDesc();
}
