package com.arham.crud.controller;

import com.arham.crud.dto.StudentDTO;
import com.arham.crud.model.Student;
import com.arham.crud.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/student")
@Validated
public class StudentController {
    @Autowired
    private StudentService service;

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        List<StudentDTO> studentDTOList = service.getAllStudents();
        ResponseEntity responseEntity;
        if (studentDTOList.size() <= 0) {
            responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else {
            responseEntity = ResponseEntity.ok(studentDTOList);
//            responseEntity = ResponseEntity.of(Optional.of(studentDTOList));
        }
        return responseEntity;
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable @Positive long id) {
        StudentDTO studentDTO = service.getStudentById(id);
        ResponseEntity responseEntity;
        if (studentDTO == null) {
            responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else {
            responseEntity = ResponseEntity.ok(studentDTO);
        }
        return responseEntity;
    }

    @GetMapping("/{id}/{name}")
    public ResponseEntity<StudentDTO> getStudentByName(@PathVariable @NotEmpty @Pattern(regexp = "^[a-zA-Z ]*$") String name) {
        StudentDTO studentDTO = service.getStudentByName(name);
        ResponseEntity responseEntity;
        if (studentDTO == null) {
            responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else {
            responseEntity = ResponseEntity.ok(studentDTO);
        }
        return responseEntity;
    }

    @PostMapping
    public ResponseEntity<List<StudentDTO>> addStudentsList(@Valid @RequestBody List<StudentDTO> studentDTOList) {
        List<StudentDTO> dtoList = service.saveStudentsList(studentDTOList);
        ResponseEntity responseEntity;
        if (dtoList.size() <= 0) {
            responseEntity = ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        else {
            responseEntity = ResponseEntity.created(URI.create("/student")).body(dtoList);
        }
        return responseEntity;
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable @Positive long id, @Valid @RequestBody StudentDTO studentDTO) {
        StudentDTO studDTO = service.updateStudent(id, studentDTO);
        ResponseEntity responseEntity;
        if (studDTO != null) {
            responseEntity = ResponseEntity.ok(studDTO);
        }
        else {
            responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return responseEntity;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StudentDTO> deleteStudent(@PathVariable @Positive long id) {
        Boolean isDeleted = service.deleteStudent(id);
        ResponseEntity responseEntity;
        if (isDeleted) {
            responseEntity = ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        else {
            responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return responseEntity;
    }
}
