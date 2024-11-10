package ru.kors.springstudents.service.impl;

import java.util.List;
import ru.kors.springstudents.model.Student;
import ru.kors.springstudents.repository.InMemoryStudentDAO;
import  ru.kors.springstudents.service.StudentService;
import org.springframework.stereotype.*;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class InMemoryStudentService implements StudentService {
	
	private final InMemoryStudentDAO repositiory;
	
	@Override
	public List<Student> findAllStudent() {
		
		return repositiory.findAllStudent();
	}

	@Override
	public Student saveStudent(Student student) {
		
		return repositiory.saveStudent(student);
	}

	@Override
	public Student updateStudent(Student student) {
		
		return repositiory.updateStudent(student);
	}

	@Override
	public Student findByEmail(String email) {
	
		return repositiory.findByEmail(email);
	}

	
	@Override
	public void deleteStudent(String email) {
		
		repositiory.deleteStudent(email);
		
	}

}
