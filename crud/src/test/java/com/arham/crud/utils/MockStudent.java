package com.arham.crud.utils;

import com.arham.crud.dto.StudentDTO;
import com.arham.crud.model.Student;

import java.util.ArrayList;
import java.util.List;

public class MockStudent {

    public static Student createMockStudentEntity (long id, String name, String className) {
        Student student = new Student(id, name, className);
        return student;
    }

    public static List<Student> createStudentEntityList() {
        List<Student> studentList = new ArrayList<>();
        studentList.add(createMockStudentEntity(1, "Arham", "BE"));
        studentList.add(createMockStudentEntity(2, "Murtaza", "BS"));
        return studentList;
    }

    public static StudentDTO createMockStudentDTO (long id, String name, String className) {
        StudentDTO studentDTO = new StudentDTO(id, name, className);
        return studentDTO;
    }

    public static List<StudentDTO> createStudentDTOList() {
        List<StudentDTO> studentList = new ArrayList<>();
        studentList.add(createMockStudentDTO(1, "Arham", "BE"));
        studentList.add(createMockStudentDTO(2, "Murtaza", "BS"));
        return studentList;
    }

}
