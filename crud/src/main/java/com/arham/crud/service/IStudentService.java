package com.arham.crud.service;
import com.arham.crud.dto.StudentDTO;

import java.util.List;

public interface IStudentService {
    List<StudentDTO> saveStudentsList(List<StudentDTO> studentDTOList);
    List<StudentDTO> getAllStudents();
    StudentDTO getStudentById(long id);
    StudentDTO getStudentByName(String name);
    boolean deleteStudent(long id);
    StudentDTO updateStudent(long id, StudentDTO studentDTO);
}
