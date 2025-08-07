package com.example.Students_Service.Student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student markle = new Student(
						"Markle",
						LocalDate.of(2000, Month.JULY, 8), 
						"markle.springer@springboot.com");

            Student alex = new Student(
						"Alex",
						LocalDate.of(2005, Month.JULY, 5), 
						"alexsis.alex@springboot.com");
            repository.saveAll(
                List.of(markle, alex)
            );
        };
    }
}