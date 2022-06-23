package com.arham.crud.controller;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.io.File;
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@Ignore
public class RestAssuredApi {

//    @BeforeAll
//    public static void setup(){
//        RestAssured.baseURI = "http://localhost:8080/student";
//    }

    private static final String STUDENT_BASE_URI = "http://localhost:8080/student";

    @Test
    public void getAllStudents() {
//        RestAssured.baseURI = "http://localhost:8080/student";
        RestAssured.given().get(STUDENT_BASE_URI).then().statusCode(200).log().all();
    }

    @Test
    public void getStudentById() {
//        RestAssured.baseURI = "http://localhost:8080/student";
        RestAssured.given().get(STUDENT_BASE_URI + "/1").then().statusCode(200).log().all();
    }

    @Test
    public void getStudentByName() {
//        RestAssured.baseURI = "http://localhost:8080/student";
        RestAssured.given()
                .get(STUDENT_BASE_URI + "/4/Murtaza").then().
                statusCode(200).
                log().all();
    }

    @Test
    public void saveStudent() {

        // Correct method
//        RestAssured.baseURI = "http://localhost:8080/student";
        File file = new File("/Users/ashafiq/Documents/postFile.json");
        RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .log().all()
                .body(file).post(STUDENT_BASE_URI)
                .then().assertThat().statusCode(201);

        // Method 2
//        StudentDTO studentDTO = RestAssured.get().as(StudentDTO.class);

//        StudentDTO studentDTO = new StudentDTO();
//        studentDTO.setId(22);
//        studentDTO.setName("Moses");
//        studentDTO.setClassName("MS");
//        RestAssured.given().body(studentDTO)
//                .when().post()
//                .then().statusCode(200);

        //Method 3
//        RestAssured.baseURI = "http://localhost:8080/student";
//        JSONObject request = new JSONObject();
//        request.put("name", "Test3"); // we may also use hash map here for (key, value) pair
//        request.put("className", "MS");
////        request.put("id", "22");
////        request.put("name", "Test 2");
////        request.put("className", "MS");
//
//        RestAssured.given().
//                contentType(ContentType.JSON).
//                body(request.toJSONString()).
////                accept(ContentType.JSON).
////                body(request.toJSONString()).
//                when().post().
//                then().assertThat().statusCode(200);
    }

    @Test
    public void updateStudent() {
//        RestAssured.baseURI = "http://localhost:8080/student";
        JSONObject request = new JSONObject();
        request.put("name", "Testing Name"); // we may also use hash map here for (key, value) pair
        request.put("className", "BS");

        RestAssured.given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toJSONString()).
                when().put(STUDENT_BASE_URI + "/21").
                then().statusCode(200);
    }

    @Test
    public void deleteStudentById() {
//        RestAssured.baseURI = "http://localhost:8080/student";
        RestAssured.given().delete(STUDENT_BASE_URI + "/58").
                then().statusCode(204).
                log().all();
    }
}
