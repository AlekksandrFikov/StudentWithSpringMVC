package ru.kors.springstudents.controller;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import ru.kors.springstudents.model.Student;
import ru.kors.springstudents.service.StudentService;

@Controller
@RequestMapping("/api/v2/students")
@AllArgsConstructor
public class StudentControllerHtml {
		
	private final StudentService service;
	
    @GetMapping
    public String findAllStudent(Model model) {
    	
    	model.addAttribute("students", service.findAllStudent());
    	return "students/allstudents";
    }
    
    @GetMapping("/{email}")
    public String findStudentById(
    		//@PathVariable("email") String email,
    		@PathVariable String email,
    		Model model) { 
    	
    	model.addAttribute("students", service.findByEmail(email));
    	return "students/allstudents";
    }
    
    @GetMapping("/new")
	public String returnSaveFormt(
			@ModelAttribute Student student, // Будет создан пустой объект, т.к. параметров его еще нет 
			Model model) { 

    	return "students/new";
	}
    
    
    
    @PostMapping("/new")
	public String saveStudent(
			@ModelAttribute @Valid Student student, // автоматически заполнит объект
			BindingResult bindingResult,
			Model model) {
    	
    		if (bindingResult.hasErrors()) return "students/new";
    				
    	    service.saveStudent(student);
    	
		return "redirect:/api/v2/students";//  + "/" + student.getEmail() ; 
	}   
    
    
    @GetMapping("/new2")
	public String returnSaveFormt2(
			@ModelAttribute Student student, // Будет создан пустой объект, т.к. параметров его еще нет 
			Model model) { 
		    	
    	model.addAttribute("students", student);
    	return "students/new2";
	}
    
   
    
    @PostMapping("/new2")
	public String saveStudent2(
			@RequestParam String ferstname,
			@RequestParam String lastname,
			@RequestParam String dateofberth,
			@RequestParam String email) {
    	
    	var student = new Student(null,ferstname,lastname,LocalDate.parse(dateofberth),email,0);
    	
    	service.saveStudent(student);
    	
    	return "redirect:/api/v2/students";
	}
    
    
    
	@GetMapping("/{email}/edit")
	public String editForm(			
			@PathVariable String email,
			@ModelAttribute Student student,
    		Model model) throws Exception {
	
		student = service.findByEmail(email);
				
		//if (true) throw new Exception("throw new Exception......");
		
		model.addAttribute("student", student);
	 
    	return "students/edit";
	} 
    

	@PatchMapping("/{email}")
	public String updateStudent(
			@PathVariable String email,
			@ModelAttribute @Valid Student student,
			BindingResult bindingResult,
    		Model model) {
		
		if (bindingResult.hasErrors()) return "students/edit";
		
		service.updateStudent(student);
		
		return "redirect:/api/v2/students";
		
	}



	@GetMapping("/find")
	public String grantFormFindByEmail() {
	 
    	return "student/find";
	} 

	@PostMapping("/find")
	public String findByEmail(@RequestParam String email, Model model) {
	 		
		model.addAttribute("students", service.findByEmail(email));
		
    	return "student/allstudents";
	} 

	
	
	@GetMapping("/{email}/delete")
	public String deleteForm(			
			@PathVariable String email,
			@ModelAttribute Student student,
    		Model model) {
	
		student = service.findByEmail(email);
		model.addAttribute("student", student);
	 
    	return "students/delete";
	} 
    

	@DeleteMapping("/{email}")
	public String deleteStudent(
			@PathVariable String email,
			@ModelAttribute Student student,
    		Model model) {
		
		service.deleteStudent(email);
		
		return "redirect:/api/v2/students";
		
	}
	
	@ExceptionHandler
	 public String handleException(Exception e, Model model) {

		model.addAttribute("Exception", e.getMessage() + " : [Controller lavel]");
		
	 return "students/exception";
    }
	
    
	@ModelAttribute("PersistentAttribute")
	public String addPersistentAttribute() {
		return "Text or Object";
	}
    
}
