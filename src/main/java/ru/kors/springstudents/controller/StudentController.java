package ru.kors.springstudents.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import ru.kors.springstudents.model.Student;
import ru.kors.springstudents.service.StudentService;

@RestController
@RequestMapping("/api/v1/students")
@AllArgsConstructor
public class StudentController {
		
	private final StudentService service;
	
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Student> findAllStudent() {
    
    	return service.findAllStudent();
    }
    
    
    @PostMapping("save_student.html")
	public Student saveStudent(@RequestBody Student student) {
		
		return service.saveStudent(student);
	}

	@PutMapping("update_student")
	public Student updateStudent(@RequestBody Student student) {
		
		return service.updateStudent(student);
		
	}


	@GetMapping("/{email}")
	public Student findByEmail(@PathVariable String email) {
	
		return service.findByEmail(email);
	}

	
	@DeleteMapping("delete_student/{email}")
	public void deleteStudent(@PathVariable String email) {
		
		service.deleteStudent(email);
		
	}
    
    
}
