package com.mitocode.service.impl;

import com.mitocode.model.Enrollment;
import com.mitocode.repo.IEnrollmentRepo;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.service.IEnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl extends CRUDImpl<Enrollment, String> implements IEnrollmentService {
    private final IEnrollmentRepo repo;
    @Override
    protected IGenericRepo<Enrollment, String> getRepo() {
        return repo;
    }
}
