package com.arham.crud.mapper;

import com.arham.crud.dto.StudentDTO;
import com.arham.crud.model.Student;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-23T22:27:27+0500",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_333 (Oracle Corporation)"
)
@Component
public class StudentMapperImpl implements StudentMapper {

    @Override
    public StudentDTO toStudentDTO(Student student) {
        if ( student == null ) {
            return null;
        }

        StudentDTO studentDTO = new StudentDTO();

        studentDTO.setId( student.getId() );
        studentDTO.setName( student.getName() );
        studentDTO.setClassName( student.getClassName() );

        return studentDTO;
    }

    @Override
    public List<StudentDTO> toStudentDTO(List<Student> studentList) {
        if ( studentList == null ) {
            return null;
        }

        List<StudentDTO> list = new ArrayList<StudentDTO>( studentList.size() );
        for ( Student student : studentList ) {
            list.add( toStudentDTO( student ) );
        }

        return list;
    }

    @Override
    public Student toStudent(StudentDTO studentDTO) {
        if ( studentDTO == null ) {
            return null;
        }

        Student student = new Student();

        student.setId( studentDTO.getId() );
        student.setName( studentDTO.getName() );
        student.setClassName( studentDTO.getClassName() );

        return student;
    }

    @Override
    public List<Student> toStudent(List<StudentDTO> studentDTOList) {
        if ( studentDTOList == null ) {
            return null;
        }

        List<Student> list = new ArrayList<Student>( studentDTOList.size() );
        for ( StudentDTO studentDTO : studentDTOList ) {
            list.add( toStudent( studentDTO ) );
        }

        return list;
    }
}
