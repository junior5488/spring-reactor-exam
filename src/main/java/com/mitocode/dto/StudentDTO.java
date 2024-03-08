package com.mitocode.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentDTO {
    private String id;

    @NotNull
    @NotEmpty
    @NotBlank
    @Size(max = 20)
    private String firstnameStudent;

    @NotNull
    @NotEmpty
    @NotBlank
    @Size(max = 20)
    private String lastnameStudent;

    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min = 8, max = 8)
    private String documentStudent;

    @NotNull
    @Min(value = 6)
    @Max(value = 99)
    private Integer ageStudent;
}
