package com.mitocode.service.impl;

import com.mitocode.model.Student;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.repo.IStudentRepo;
import com.mitocode.service.IStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Comparator;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl extends CRUDImpl<Student, String> implements IStudentService {
    private final IStudentRepo repo;
    @Override
    protected IGenericRepo<Student, String> getRepo() {
        return repo;
    }

    @Override
    public Flux<Student> sortByAgeAsc() {
        return repo.findAll().sort(Comparator.comparing(Student::getAge));
    }

    @Override
    public Flux<Student> sortByAgeDesc() {
        return repo.findAll().sort(Comparator.comparing(Student::getAge).reversed());
    }
}
