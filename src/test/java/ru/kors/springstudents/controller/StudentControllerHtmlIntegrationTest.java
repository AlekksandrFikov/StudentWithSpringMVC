package ru.kors.springstudents.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;

import ru.kors.springstudents.model.Student;
import ru.kors.springstudents.service.StudentService;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")

@Sql(scripts = {"/newstudents.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_CLASS)
@Sql(scripts = {"/truncstudents.sql"}, executionPhase = ExecutionPhase.AFTER_TEST_CLASS)
public class StudentControllerHtmlIntegrationTest {
	
	@SpyBean
	private StudentService service;
	
	@Autowired
	private MockMvc mockMVC;
	
	@Autowired
	private StudentControllerHtml controller;
	
	@BeforeAll
	static void globalSetUp() {
	}
	
	@BeforeEach
	void setUp() throws Exception {
	}

	
	@Test
	void contextLoads() throws Exception {
		assertThat(controller)
			.isNotNull()
			.isEqualTo(controller);
	}
	
	
	@Test
	void testFindAllStudent() throws Exception {
	
		mockMVC.perform(get("/api/v2/students"))
//				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(view().name("students/allstudents"))
				.andExpect(model().attributeExists("students"))
				.andExpect(model().size(2))
				.andExpect(content().string(containsString("[All Students]")))
				.andExpect(content().string(containsString("fikov3@list.ru")))
				;
		
		verify(service, times(1)).findAllStudent();
	}
	

	@Test
	void testFindStudentById() throws Exception {
	
		mockMVC.perform(get("/api/v2/students/{email}", "fikov3@list.ru"))
			//.andDo(print())
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("students"))
			.andExpect(model().attribute("students", service.findByEmail("fikov3@list.ru")))
			.andExpect(model().size(2))
			.andExpect(view().name("students/allstudents"))
			.andExpect(content().string(containsString("[All Students]")))
			.andExpect(content().string(containsString("fikov3@list.ru")))
			;
		
		verify(service,times(2)).findByEmail("fikov3@list.ru");
	}


	
	@Test
	void testReturnSaveForm() throws Exception {
			
		mockMVC.perform(get("/api/v2/students/new"))
		//.andDo(print())
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("student"))
		.andExpect(model().attribute("student", new Student()))
	
		.andExpect(view().name("students/new"));
	
	}
	
	


	@Test
	@Sql(scripts = {"/newstudents.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	void testSaveStudent() throws Exception {
		
		var student = new Student(51L, "ferstname", "lastname", LocalDate.of(1979, 10, 24), "fikov@gmail.com", 45);
 
		mockMVC.perform(post("/api/v2/students/new2")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("id", student.getId().toString())
				.param("ferstname", student.getFerstname())
				.param("lastname", student.getLastname())
				.param("dateofberth", student.getDateofberth().toString())
				.param("email", student.getEmail())
				)
		//.andDo(print())
		.andExpect(status().is3xxRedirection())
		.andExpect(status().is(302))
		.andExpect(view().name("redirect:/api/v2/students"))
		.andExpect(redirectedUrl("/api/v2/students"));
	
		var student2 = service.findByEmail("fikov@gmail.com"); 		
		verify(service,times(1)).saveStudent(student2);
		assertEquals(student, student2);
	}	
	
	
	@Test
	void testDeleteForm() {
		//fail("Not yet implemented");
	}


}
