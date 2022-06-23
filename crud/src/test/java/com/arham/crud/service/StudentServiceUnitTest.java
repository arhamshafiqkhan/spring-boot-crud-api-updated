package com.arham.crud.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.arham.crud.dto.StudentDTO;
import com.arham.crud.mapper.StudentMapper;
import com.arham.crud.model.Student;
import com.arham.crud.repository.StudentRepo;

import com.arham.crud.utils.MockStudent;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@Ignore
@RunWith(MockitoJUnitRunner.Silent.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class StudentServiceUnitTest {
    @Mock
    private StudentRepo studentRepo;
    @Mock
    private StudentMapper mapper;
    @InjectMocks
    private StudentService studentService;

    @Before
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddStudent() {
        List<Student> aMockStudentEntityList = MockStudent.createStudentEntityList();
        List<StudentDTO> aMockStudentDTOList = MockStudent.createStudentDTOList();

        when(studentRepo.saveAll(anyList())).thenReturn(aMockStudentEntityList);
        when(mapper.toStudentDTO(anyList())).thenReturn(aMockStudentDTOList);
        when(mapper.toStudent(anyList())).thenReturn(aMockStudentEntityList);

        List<StudentDTO> newStudentList = studentService.saveStudentsList(aMockStudentDTOList);

//        Assert.assertEquals(201, statusCode.getStatusCodeValue());
//        Assert.assertEquals(ResponseEntity.status(HttpStatus.CREATED.value()), newStudentList.getStatusCode().value());
        Assert.assertEquals(aMockStudentDTOList.get(0).getName(), newStudentList.get(0).getName());
        Assert.assertEquals(aMockStudentDTOList.get(1).getClassName(), newStudentList.get(1).getClassName());
        Assert.assertNotEquals("murtaza", newStudentList.get(1).getName());
    }

    @Test
    public void testGetAllStudents() {
        List<Student> aMockStudentEntityList = MockStudent.createStudentEntityList();
        List<StudentDTO> aMockStudentDTOList = MockStudent.createStudentDTOList();

        when(studentRepo.findAll()).thenReturn(aMockStudentEntityList);
        when(mapper.toStudentDTO(anyList())).thenReturn(aMockStudentDTOList);
        when(mapper.toStudent(anyList())).thenReturn(aMockStudentEntityList);

        List<StudentDTO> newStudentList = studentService.getAllStudents();

        Assert.assertEquals(aMockStudentDTOList.get(0).getName(), newStudentList.get(0).getName());
        Assert.assertEquals(aMockStudentDTOList.get(1).getClassName(), newStudentList.get(1).getClassName());

        Assert.assertNotEquals("Masters", newStudentList.get(0).getClassName());
    }

    @Test
    public void testGetStudentByIdIfFound() {
        List<Student> aMockStudentEntityList = MockStudent.createStudentEntityList();
        List<StudentDTO> aMockStudentDTOList = MockStudent.createStudentDTOList();

        Student aMockStudentEntity = aMockStudentEntityList.get(0);
        StudentDTO aMockStudentDTO = aMockStudentDTOList.get(0);

        long testId = aMockStudentEntity.getId();

        when(studentRepo.findById(testId)).thenReturn(Optional.of(aMockStudentEntity)); // for checking the value
        when(mapper.toStudentDTO(any(Student.class))).thenReturn(aMockStudentDTO);

        StudentDTO newStudent = studentService.getStudentById(testId);
        Assert.assertEquals(aMockStudentDTO.getName(), newStudent.getName()); // for asserting the actual value
    }

    @Test
    public void testGetStudentByIdIfNotFound() {
        List<Student> aMockStudentEntityList = MockStudent.createStudentEntityList();
        List<StudentDTO> aMockStudentDTOList = MockStudent.createStudentDTOList();

        Student aMockStudentEntity = aMockStudentEntityList.get(0);
        StudentDTO aMockStudentDTO = aMockStudentDTOList.get(0);

        long testId = aMockStudentEntity.getId();

        when(studentRepo.findById(testId)).thenReturn(Optional.empty()); // this is for Optional.orElse(null)
        when(mapper.toStudentDTO(any(Student.class))).thenReturn(aMockStudentDTO);

        StudentDTO newStudent = studentService.getStudentById(testId);
//        Assert.assertEquals(404, newStudent.getStatusCodeValue());
        Assert.assertNull(newStudent); // part of Optional.orElse(null)
    }

    @Test
    public void testGetStudentByName() {
        List<Student> aMockStudentEntityList = MockStudent.createStudentEntityList();
        List<StudentDTO> aMockStudentDTOList = MockStudent.createStudentDTOList();

        Student aMockStudentEntity = aMockStudentEntityList.get(0);
        StudentDTO aMockStudentDTO = aMockStudentDTOList.get(0);

        String testName = aMockStudentEntity.getName();

        when(studentRepo.findByName(testName)).thenReturn(aMockStudentEntity);
        when(mapper.toStudentDTO(any(Student.class))).thenReturn(aMockStudentDTO);

        StudentDTO newStudent = studentService.getStudentByName(testName);
        Assert.assertEquals(aMockStudentDTO.getClassName(), newStudent.getClassName());
    }

    @Test
    public void testDeleteStudentIfIdFound() {
        List<Student> aMockStudentEntityList = MockStudent.createStudentEntityList();
        List<StudentDTO> aMockStudentDTOList = MockStudent.createStudentDTOList();

        Student aMockStudentEntity = aMockStudentEntityList.get(0);
        StudentDTO aMockStudentDTO = aMockStudentDTOList.get(0);

        long testId = aMockStudentEntity.getId();

        when(studentRepo.findById(testId)).thenReturn(Optional.of(aMockStudentEntity));
//        when(mapper.toStudentDTO(any(Student.class))).thenReturn(aMockStudentDTO); // mapper was not used in delete

        Boolean isDeleted = studentService.deleteStudent(testId);
//        ResponseEntity<StudentDTO> statusCode = studentService.deleteStudent(testId);

        // verify is used to check if the method was invoked or not, we use verify when there is no return type
        verify(studentRepo).deleteById(aMockStudentEntity.getId()); // for repo, we use entity, not DTO
        Assert.assertTrue(isDeleted);

//        Assert.assertEquals(ResponseEntity.status(HttpStatus.NO_CONTENT), statusCode.getStatusCode());

//        Assert.assertTrue(message.contains(testId + " is successfully deleted!"));
//        Assert.assertFalse(message.contains(testId + " is not successfully deleted!"));
    }

    @Test
    public void testDeleteStudentIfIdNotFound() {
        List<Student> aMockStudentEntityList = MockStudent.createStudentEntityList();
        List<StudentDTO> aMockStudentDTOList = MockStudent.createStudentDTOList();

        Student aMockStudentEntity = aMockStudentEntityList.get(0);
        StudentDTO aMockStudentDTO = aMockStudentDTOList.get(0);

        long testId = 3L;

        when(studentRepo.findById(testId)).thenReturn(Optional.ofNullable(null));

        Boolean isDeleted = studentService.deleteStudent(testId);
        Assert.assertFalse(isDeleted);

//        ResponseEntity<StudentDTO> statusCode = studentService.deleteStudent(testId);
//        Assert.assertEquals(ResponseEntity.status(HttpStatus.NOT_FOUND), statusCode.getStatusCode());
//        Assert.assertTrue(message.contains(testId + " is not present"));
//        Assert.assertFalse(message.contains(testId + "is present"));
    }

    @Test
    public void testUpdateStudentIfIdFound() {
        List<Student> aMockStudentEntityList = MockStudent.createStudentEntityList();
        List<StudentDTO> aMockStudentDTOList = MockStudent.createStudentDTOList();

        Student aMockStudentEntity = aMockStudentEntityList.get(0);
        StudentDTO aMockStudentDTO = aMockStudentDTOList.get(0);

        long testId = aMockStudentEntity.getId();

        when(studentRepo.findById(testId)).thenReturn(Optional.of(aMockStudentEntity));
        when(mapper.toStudentDTO(any(Student.class))).thenReturn(aMockStudentDTO);
        when(mapper.toStudent(any(StudentDTO.class))).thenReturn(aMockStudentEntity);

        StudentDTO newStudent = studentService.updateStudent(testId, aMockStudentDTO);
        // verify is used to check if the method was invoked or not, we use verify when there is no return type
        verify(studentRepo).save(aMockStudentEntity);
        Assert.assertEquals(newStudent.getName(), aMockStudentDTO.getName());

//        Assert.assertEquals(ResponseEntity.status(HttpStatus.OK), statusCode.getStatusCode());
//        Assert.assertTrue(message.contains(testId + " has been updated successfully!"));
//        Assert.assertFalse(message.contains(testId + " has not been updated successfully!"));
    }

    @Test
    public void testUpdateStudentIfIdNotFound() {
        List<Student> aMockStudentEntityList = MockStudent.createStudentEntityList();
        List<StudentDTO> aMockStudentDTOList = MockStudent.createStudentDTOList();

        Student aMockStudentEntity = aMockStudentEntityList.get(0);
        StudentDTO aMockStudentDTO = aMockStudentDTOList.get(0);

        long testId = 4L;

        when(studentRepo.findById(testId)).thenReturn(Optional.ofNullable(null));
        Mockito.doReturn(aMockStudentDTO).when(mapper).toStudentDTO(any(Student.class));

   // when(mapper.toStudentDTO(any(Student.class))).thenReturn(aMockStudentDTO);

        when(mapper.toStudent(any(StudentDTO.class))).thenReturn(aMockStudentEntity);

        StudentDTO newStudent = studentService.updateStudent(testId, aMockStudentDTO);
        Assert.assertNull(newStudent);

//        Assert.assertEquals(ResponseEntity.status(HttpStatus.NOT_FOUND), statusCode.getStatusCode());
//        Assert.assertTrue(message.contains(testId + " is not present."));
//        Assert.assertFalse(message.contains(testId + " is present."));
    }
}
