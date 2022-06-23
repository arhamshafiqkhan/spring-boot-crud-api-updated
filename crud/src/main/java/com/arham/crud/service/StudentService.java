package com.arham.crud.service;

import com.arham.crud.dto.StudentDTO;
import com.arham.crud.mapper.StudentMapper;
import com.arham.crud.model.Student;
import com.arham.crud.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService implements IStudentService {
    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private StudentMapper mapper;

    @Override
    public List<StudentDTO> saveStudentsList(List<StudentDTO> studentDTOList) {
        List<StudentDTO> newStudentList = new ArrayList<>();
        List<StudentDTO> dtoList = new ArrayList<>();
        for (StudentDTO studentDTO : studentDTOList) {
            if (studentRepo.findByName(studentDTO.getName()) == null) {
                newStudentList.add(studentDTO);
            }
        }
        if (newStudentList.size() > 0) {
            List<Student> studentList = mapper.toStudent(newStudentList);
            dtoList = mapper.toStudentDTO(studentRepo.saveAll(studentList));
        }
        return dtoList;

        /*
        try{
            List<StudentDTO> newStudentList = new ArrayList<>();
            int count = 0;
            for (StudentDTO studentDTO : studentDTOList) {
                // in if statement, we should check unique id such as email
                if (studentRepo.findByName(studentDTO.getName()) == null) {
                    newStudentList.add(studentDTO);
                }
                else {
                    count++;
                }
            }
            if (newStudentList.size() <= 0) {
                return new ResponseEntity<>(newStudentList, HttpStatus.BAD_REQUEST);
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
            List<Student> studentList = mapper.toStudent(newStudentList);
            List<StudentDTO> dtoList = mapper.toStudentDTO(studentRepo.saveAll(studentList));
//            return ResponseEntity.status(HttpStatus.CREATED).build(); // returns status only
            return ResponseEntity.created(URI.create("/student"))
                    .header("Students already present", ""+count).body(dtoList);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
     */
    }

    @Override
    public List<StudentDTO> getAllStudents() {

        List<Student> studentList = studentRepo.findAll();
        List<StudentDTO> studentDTOList = mapper.toStudentDTO(studentList);
        return studentDTOList;

//        List<Student> studentList = null;
//        try {
//            studentList = studentRepo.findAll();
//            List<StudentDTO> studentDTOList = mapper.toStudentDTO(studentList);
//            return studentDTOList;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }

    }

    @Override
    public StudentDTO getStudentById(long id) {

        Optional<Student> student = studentRepo.findById(id);
        StudentDTO studentDTO = mapper.toStudentDTO(student.orElse(null));
        return studentDTO;

//       Optional<Student> student;
//        try {
//            student = studentRepo.findById(id);
//            StudentDTO studentDTO = mapper.toStudentDTO(student.get()); // .get() will convert optional to object
//            return ResponseEntity.of(Optional.of(studentDTO));
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
    }

    @Override
    public StudentDTO getStudentByName(String name) {
        Student student = studentRepo.findByName(name);
        StudentDTO studentDTO = mapper.toStudentDTO(student);
        return studentDTO;

//        Student student = null;
//        try{
//            student = studentRepo.findByName(name);
//            StudentDTO studentDTO = mapper.toStudentDTO(student);
//            return ResponseEntity.of(Optional.of(studentDTO));
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }

//        return mapper.toStudentDTO(studentRepo.findByName(name));
    }

    @Override
    public boolean deleteStudent(long id) {
        Boolean isDeleted = false;
        if (studentRepo.findById(id).isPresent()) {
            studentRepo.deleteById(id);
            isDeleted = true;
        }
        return isDeleted;

//        try{
//            studentRepo.findById(id);
//            studentRepo.deleteById(id);
//            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }

//        if (studentRepo.findById(id).isPresent())
//            studentRepo.deleteById(id);
//        else
//            return "Student with id = " + id + " is not present!";
//        return "Student with id = " + id + " is successfully deleted!";
    }

    @Override
    public StudentDTO updateStudent(long id, StudentDTO studentDTO) {
        Student student = studentRepo.findById(id).orElse(null);
        StudentDTO updateStudentDTO = mapper.toStudentDTO(student);
        if (student != null) {
            updateStudentDTO.setName(studentDTO.getName());
            updateStudentDTO.setClassName(studentDTO.getClassName());
            studentRepo.save(mapper.toStudent(updateStudentDTO));
        }
        // this else is to save a new student if it was not present
//        else {
//            updateStudentDTO = mapper.toStudentDTO(studentRepo.save(student));
//        }
        return updateStudentDTO;

/*
        StudentDTO updateStudentDTO = mapper.toStudentDTO(studentRepo.findById(id).orElse(null));
        //instead of using try/catch, we've used if statement
        if (updateStudentDTO != null) {
            updateStudentDTO.setName(studentDTO.getName());
            updateStudentDTO.setClassName(studentDTO.getClassName());
            studentRepo.save(mapper.toStudent(updateStudentDTO));

            // returns the updated student and status OK
            return ResponseEntity.ok().body(updateStudentDTO);

            // returns the updatedStudent as well as default success status 200 (OK)
//            return ResponseEntity.of(Optional.of(updateStudentDTO));

            // just returns the status 200 (OK)
//            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

 */
    }

}
