package com.arham.crud.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {

//    @Positive
    private long id;

    @NotEmpty(message = "name can not be empty")
    @Size(min = 3, max = 25, message = "name should contain at least 3 characters and at most 25")
    @Pattern(regexp = "^[a-zA-Z ]*$", message = "Please enter a valid name")
    //    @Pattern(regexp = "^[A-Za-z]*$", message = "Please enter a valid name")
    private String name;

    @NotEmpty(message = "Class Name can not be empty")
    private String className;
}
