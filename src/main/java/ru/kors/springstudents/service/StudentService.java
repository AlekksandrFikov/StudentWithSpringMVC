package ru.kors.springstudents.service;


import java.util.List;

import ru.kors.springstudents.model.Student;


public interface StudentService {
	
    List<Student> findAllStudent();
    
    Student saveStudent(Student student);
    Student updateStudent(Student student);
    Student findByEmail(String email);
    void deleteStudent(String email);
    
}