package ru.kors.springstudents.repository;
import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityNotFoundException;
import ru.kors.springstudents.model.Student;



@Repository // необязательная анотация
public interface StudentRepository extends JpaRepository<Student,Long> {
	
   void deleteByEmail(String email);
   Student findByEmail(String email);
      
   
   @Query("""
   		
   		SELECT s 
   		FROM Student s 
   		WHERE s.email = ?1
   		
   		""")
   Student myFindByEmail(String email);
   
   
   @Modifying(clearAutomatically = true)
   @Query("""
  			UPDATE Student s 
  			SET 
  				s.ferstname = ?1,
  				s.lastname = ?2,
  				s.dateofberth = ?3,
  				s.email = ?4   				
  			WHERE
  			 	s.id = ?5
  		""")
   
   void update2( String ferstname, 
		   			String lastname,
		   			LocalDate dateofberth, 
		   			String email,
		   			Long id);   
   
   default void update2(Student student) {
	   			update2(student.getFerstname(), 
			   	student.getLastname(),
			   	student.getDateofberth(), 
			   	student.getEmail(),
			   	student.getId());	   
   };

   
   @Modifying(clearAutomatically = true)
   @Query("""
  			UPDATE Student s 
  			SET 
  				s.ferstname = :ferstname,
  				s.lastname = :lastname,
  				s.dateofberth = :dateofberth,
  				s.email = :email
  			WHERE
  			 	s.id = :id
  		""")
   Student update21(
		   			@Param("ferstname") String ferstname, 
		   			@Param("lastname") String lastname,
		   			@Param("dateofberth")LocalDate dateofberth, 
		   			@Param("email")String email,
		   			@Param("id") Long id);
   
   
   default void update21(Student student) {
		update2(student.getFerstname(), 
	   	student.getLastname(),
	   	student.getDateofberth(), 
	   	student.getEmail(),
	   	student.getId());	   
   };

   
 
   default Student update(Student student) {
	   
		var id = student.getId();
		Student stdnt = findById(id).orElseThrow(EntityNotFoundException::new); 
		
		stdnt.setAge(student.getAge());
		stdnt.setDateofberth(student.getDateofberth());
		stdnt.setEmail(student.getEmail());
		stdnt.setFerstname(student.getFerstname());
		stdnt.setLastname(student.getLastname());
		 
		return save(stdnt);
	   
   } ;
  
 }
