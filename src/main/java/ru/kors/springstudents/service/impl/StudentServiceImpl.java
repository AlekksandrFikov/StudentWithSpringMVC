package ru.kors.springstudents.service.impl;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import ru.kors.springstudents.model.Student;
import ru.kors.springstudents.repository.StudentRepository;
import ru.kors.springstudents.service.StudentService;

@Service
@AllArgsConstructor
@Primary
public class StudentServiceImpl implements StudentService{
	
	private final StudentRepository repository;
	
	@Override
	public List<Student> findAllStudent() {
		
		
		return repository.findAll();
	}

	@Override
	public Student saveStudent(Student student) {
		
		return repository.save(student);
	}

	@Override
	@Transactional
	public Student updateStudent(Student student) {
		return repository.update(student);
		//repository.update21(student);
		//return student;
	}

	@Override
	public Student findByEmail(String email) {
		
		//return repository.findByEmail(email);
		return repository.myFindByEmail(email);
	}

	@Override
	@Transactional
	public void deleteStudent(String email) {

		repository.deleteByEmail(email);
		
	}

}
