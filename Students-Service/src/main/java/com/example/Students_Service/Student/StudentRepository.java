package com.example.Students_Service.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
// responsible for data access
public interface StudentRepository 
       extends JpaRepository<Student, Long> {

        // custom function to find user 
        @Query("SELECT s FROM Student s WHERE s.email = ?1") // JPQL
        Optional<Student> findStudentByEmail(String email); // Trasform to SQL query 'SELECT * FROM student WHERE email = <value>'

        @Query("SELECT t FROM Student t WHERE t.id = ?1")
        Optional<Student> findStudentByID(Long id);
}
