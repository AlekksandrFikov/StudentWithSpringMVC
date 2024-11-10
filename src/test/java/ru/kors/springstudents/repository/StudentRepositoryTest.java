package ru.kors.springstudents.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import ru.kors.springstudents.model.Student;

@DataJpaTest
@ActiveProfiles("test")
@Sql(scripts = {"/newstudents.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_CLASS)
@Sql(scripts = {"/truncstudents.sql"}, executionPhase = ExecutionPhase.AFTER_TEST_CLASS)
class StudentRepositoryTest {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    TestEntityManager entityManager;

    
	@BeforeEach
	public void setUp() {
	    Student student = new Student(10L, "ferstname", "lastname", LocalDate.of(1979, 10, 24), "StudentRepositoryTest@gmail.com", 45);
	    entityManager.merge(student);
	}
    
    
    @Test
	void testDeleteByEmail() {
    	studentRepository.deleteByEmail("StudentRepositoryTest@gmail.com");
    	var student = studentRepository.findByEmail("StudentRepositoryTest@gmail.com");
		assertThat(student).isNull();
		
	}

	@Test
	void testFindByEmail() {
		var student = studentRepository.findByEmail("StudentRepositoryTest@gmail.com");
		assertThat(student.getEmail()).isEqualTo("StudentRepositoryTest@gmail.com");
	}

	@Test
	void testMyFindByEmail() {
		var student = studentRepository.findByEmail("StudentRepositoryTest@gmail.com");
		assertThat(student.getEmail()).isEqualTo("StudentRepositoryTest@gmail.com");
	}

	
	@Test
	void testUpdate() {
		var student = studentRepository.findById(1L);
		
		student.ifPresentOrElse(
				(sdnt) 	-> 
						{sdnt.setEmail("update@gmail.com");
						entityManager.merge(sdnt);
						var updeteStudent = studentRepository.findByEmail("update@gmail.com");						
						assertThat(updeteStudent).isNotNull();},
				() -> 
						{fail("Put student with id=1L in test data base");}
				);
		
		
	}

}
