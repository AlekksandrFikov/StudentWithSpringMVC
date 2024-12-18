package ru.kors.springstudents.model;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter @Getter @NoArgsConstructor @EqualsAndHashCode
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;
	private String password;	
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable( name="user_authorities",
        joinColumns= @JoinColumn(name="user_id", referencedColumnName="id"),
        inverseJoinColumns=   @JoinColumn(name="authority_id", referencedColumnName="id"))
	private Set<Authority> authorities;
}
