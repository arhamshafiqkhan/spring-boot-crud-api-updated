package com.arham.crud.repository;

import com.arham.crud.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {
    Student findByName(String name);
}
