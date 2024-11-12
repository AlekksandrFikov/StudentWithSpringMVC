package ru.kors.springstudents.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ru.kors.springstudents.model.User;


public interface UserRepository extends JpaRepository<User,Integer> {
   
	@Query("""
	   		SELECT u 
	   		FROM User u 
	   		WHERE u.username = :username
	   		""")
	Optional<User> findByUsername (String username);
    
 }
