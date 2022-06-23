package com.arham.crud.mapper;

import com.arham.crud.dto.StudentDTO;
import com.arham.crud.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {

//    @Mappings({ @Mapping(target = "id", source = "student.id"), @Mapping(target = "studentName", source = "student.name"), @Mapping(target = "studentClassName", source = "student.className") })
    StudentDTO toStudentDTO(Student student);

    List<StudentDTO> toStudentDTO(List<Student> studentList);

    //    @Mappings({ @Mapping(target = "id", source = "studentDTO.studentId"), @Mapping(target = "name", source = "studentDTO.studentName"), @Mapping(target = "className", source = "studentDTO.studentClassName") })
    Student toStudent(StudentDTO studentDTO);

    List<Student> toStudent(List<StudentDTO> studentDTOList);
}
