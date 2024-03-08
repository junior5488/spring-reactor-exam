package com.mitocode.config;

import com.mitocode.dto.CourseDTO;
import com.mitocode.dto.EnrollmentDTO;
import com.mitocode.model.Course;
import com.mitocode.model.Enrollment;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class MapperConfig {

    @Bean("defaultMapper")
    public ModelMapper defaultMapper(){
        return new ModelMapper();
    }

    @Bean("courseMapper")
    public ModelMapper courseMapper(){
        ModelMapper mapper = new ModelMapper();

        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        //Escritura
        TypeMap<CourseDTO, Course> typeMap1 = mapper.createTypeMap(CourseDTO.class, Course.class);
        typeMap1.addMapping(CourseDTO::getNameCourse, (dest, v) -> dest.setName((String) v));
        typeMap1.addMapping(CourseDTO::getShortnameCourse, (dest, v) -> dest.setShortname((String) v));
        typeMap1.addMapping(CourseDTO::getStatusCourse, (dest, v) -> dest.setStatus((Boolean) v));

        //Lectura
        TypeMap<Course, CourseDTO> typeMap2 = mapper.createTypeMap(Course.class, CourseDTO.class);
        typeMap2.addMapping(Course::getName, (dest, v) -> dest.setNameCourse((String) v));
        typeMap2.addMapping(Course::getShortname, (dest, v) -> dest.setShortnameCourse((String) v));
        typeMap2.addMapping(Course::getStatus, (dest, v) -> dest.setStatusCourse((Boolean) v));

        return mapper;
    }

    @Bean("enrollmentMapper")
    public ModelMapper enrollmentMapper(){
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        //Lectura
        TypeMap<Enrollment, EnrollmentDTO> typeMap1 = mapper.createTypeMap(Enrollment.class, EnrollmentDTO.class);
        typeMap1.addMapping(e -> e.getStudent().getId(), (dest, v) -> dest.getStudent().setId((String) v));
        typeMap1.addMapping(e -> e.getStudent().getFirstname(), (dest, v) -> dest.getStudent().setFirstnameStudent((String) v));
        typeMap1.addMapping(e -> e.getStudent().getLastname(), (dest, v) -> dest.getStudent().setLastnameStudent((String) v));
        typeMap1.addMapping(e -> e.getStudent().getDocument(), (dest, v) -> dest.getStudent().setDocumentStudent((String) v));
        typeMap1.addMapping(e -> e.getStudent().getAge(), (dest, v) -> dest.getStudent().setAgeStudent((Integer) v));

        //Escritura
        TypeMap<EnrollmentDTO, Enrollment> typeMap2 = mapper.createTypeMap(EnrollmentDTO.class, Enrollment.class);
        typeMap2.addMapping(e -> e.getStudent().getId(), (dest, v) -> dest.getStudent().setId((String) v));
        typeMap2.addMapping(e -> e.getStudent().getFirstnameStudent(), (dest, v) -> dest.getStudent().setFirstname((String) v));
        typeMap2.addMapping(e -> e.getStudent().getLastnameStudent(), (dest, v) -> dest.getStudent().setLastname((String) v));
        typeMap2.addMapping(e -> e.getStudent().getDocumentStudent(), (dest, v) -> dest.getStudent().setDocument((String) v));
        typeMap2.addMapping(e -> e.getStudent().getAgeStudent(), (dest, v) -> dest.getStudent().setAge((Integer) v));

        return mapper;
    }

}
