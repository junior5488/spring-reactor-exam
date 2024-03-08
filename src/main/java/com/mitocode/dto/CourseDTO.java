package com.mitocode.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseDTO {
    private String id;

    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min = 20)
    private String nameCourse;

    @NotNull
    @NotEmpty
    @NotBlank
    @Size(max = 3)
    private String shortnameCourse;

    @NotNull
    private Boolean statusCourse;
}
