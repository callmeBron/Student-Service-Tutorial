package com.example.Students_Service.Student;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;


@RestController // annotates that this class should service rest endpoints
@RequestMapping(path = "api/v1/students") // this will map our endpoint
public class StudentController {
// Student Controller will now be setup for our api

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
// Fetch users
	@GetMapping
	public List<Student> getStudents() {
		return studentService.getStudents();
	}

// Add users
	@PostMapping // used to add new resources to the system
	public void registerNewStudent(@RequestBody Student student) { // @RequestBody will allow us to map into the student object
		studentService.addNewStudent(student);
	}

// Delete users
// i.e http://localhost:8080/api/v1/student/{studentID}
	@DeleteMapping(path = "{studentId}") // used to delete existing users
	public void deleteStudent(@PathVariable("studentId") Long studentId) {
		studentService.deleteStudent(studentId);
	}

// Modify users 
// i.e http://localhost:8080/api/v1/student/{studentID}?{modifiedValue}={newValue}&{modifiedValue}={newValue}
	@PutMapping(path = "{studentId}")
	public void updateStudent(
		@PathVariable("studentId") Long studentID,
		@RequestParam(required = false) String name,
		@RequestParam(required = false) String email
		) {
		studentService.updateStudent(studentID, name, email);
	}
}