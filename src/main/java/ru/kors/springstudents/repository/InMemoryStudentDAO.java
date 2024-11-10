package ru.kors.springstudents.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import org.springframework.stereotype.*;

import ru.kors.springstudents.model.Student;

@Repository
public class InMemoryStudentDAO {
	
	private final List<Student> STUDENTS = new ArrayList<>();
	

	public List<Student> findAllStudent() {
		return STUDENTS;
	}


	public Student saveStudent(Student student) {
		STUDENTS.add(student);
		return student;
	}


	public Student updateStudent(Student student) {
		int studentIndex = IntStream.range(0, STUDENTS.size())
							.filter(index -> STUDENTS.get(index).getEmail().equals(student.getEmail()))
							.findFirst()
							.orElse(-1);
		
		if (studentIndex>=0) {
			STUDENTS.add(studentIndex, student);
			return student;
		}
		
		return null;
	}
  

	public Student findByEmail(String email) {
		
		return STUDENTS.stream()
				.filter(element -> element.getEmail().equals(email))
				.findFirst()
				.orElse(null);
	}


	public void deleteStudent(String email) {
		var student = findByEmail(email);

		if (student!=null) {
				STUDENTS.remove(student);
}
		
	}
}
  