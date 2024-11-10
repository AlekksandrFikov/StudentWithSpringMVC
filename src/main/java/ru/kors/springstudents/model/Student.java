package ru.kors.springstudents.model;

import java.time.LocalDate;
import java.time.Period;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder 
@Entity
@AllArgsConstructor
public class Student {
	
	public Student() {
		super();
		this.id = -1000000000L;
		this.ferstname = "";
		this.lastname = "";
		this.dateofberth = LocalDate.of(1900, 1, 1);
		this.email = "";
		this.age = 0;
	}
	
	@Id
	@GeneratedValue
	private Long id;
	@Size(min=2, max = 30, message = "Name should be from 2 to 30")
	private String ferstname;
	@Size(min=2, max = 30, message = "Name should be from 2 to 30")
	private String lastname;
	private LocalDate dateofberth;
	@Column(unique = true)
	@Email(message = "Email should be valid")
	private String email; 
	@Transient
	private int age;
	
		
	public int getAge() {
		
		return Period.between(dateofberth, LocalDate.now()).getYears();
	}
	
	
	
}
