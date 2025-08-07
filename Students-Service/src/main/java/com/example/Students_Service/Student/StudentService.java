package com.example.Students_Service.Student;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService {
    // business layer
	private final StudentRepository studentRepository;

	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

    public List<Student> getStudents() {
		return studentRepository.findAll();
    }

	public void addNewStudent(Student student) {
		if (!studentExistsByEmail(student.getEmail())) {
			studentRepository.save(student);
		} else {
			throw new IllegalStateException("Email already exists");
		}
	}

	public void deleteStudent(Long studentID) {
		if (studentExistsByID(studentID)){
			studentRepository.deleteById(studentID);
		} else {
			throw new IllegalStateException("There is no student by that ID" + " " + studentID);
		}
	}
	@Transactional // @Transactional annotation with this we wont need to implement jpql query
	public void updateStudent(Long studentID, String name, String email) {
		if (studentExistsByID(studentID)){
			updateStudentName(name, studentID);
			updateStudentEmail(email, studentID);
		} else {
			throw new IllegalStateException("There is no student by that ID" + " " + studentID + "Update failed");
		}
	}

// helper functions (Kinda)
	private Boolean studentExistsByEmail(String studentEmail) {
		return studentRepository.findStudentByEmail(studentEmail).isPresent();
	}

	private Boolean studentExistsByID(Long id) {
		return studentRepository.existsById(id);
	}

	private Student currentStudent(Long id) {
		return studentRepository.findById(id).orElseThrow();
	}

	private Boolean areObjectsTheSame(String valueOne, String valueTwo) {
		return Objects.equals(valueOne, valueTwo); 
	}

	private void updateStudentName(String name, Long id) {
		Student student = currentStudent(id);
		if (name != null && name.length() > 0 && !areObjectsTheSame(student.getName(), name)) {
			student.setName(name);
		}
	}

	private void updateStudentEmail(String email, Long id) {
		Student student = currentStudent(id);
		String studentEmail = student.getEmail();
		
		if (email != null && email.length() > 0 && !areObjectsTheSame(studentEmail, email)) {
			if (!studentExistsByEmail(studentEmail)) {
				student.setEmail(email);
			} else {
				throw new IllegalStateException("Email already exists on db");
			}
		} 
	}
}