package ru.kors.springstudents.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import ch.qos.logback.core.model.Model;
import ru.kors.springstudents.model.Student;
import ru.kors.springstudents.service.StudentService;

@WebMvcTest(StudentControllerHtml.class)
class StudentControllerHtmlTest2  {
	
	@MockBean
	private StudentService service;
	
	@MockBean
	private Model model;
	
//	@InjectMocks
//	private StudentControllerHtml studentControllerHtml;
//	
	@Autowired
	private MockMvc mockMVC;
	

	
	private static  List<Student> students;
	private static  List<Student> students2;
	
	@BeforeAll
	static void globalSetUp() {
		
		students = List.of(	new Student(1L, "ferstname", "lastname", LocalDate.of(1979, 10, 24), "fikov1@gmail.com", 45),
							new Student(2L, "ferstname", "lastname", LocalDate.of(1979, 10, 24), "fiko2v@gmail.com", 45));
		
				
		students2 = List.of(
						students.get(0),
						students.get(1),
						new Student(3L, "ferstname", "lastname", LocalDate.of(1979, 10, 24), "fiko3v@gmail.com", 45));
	
	}
	
//	@BeforeEach
//	void setUp() throws Exception {
//		
//		mockMVC = MockMvcBuilders.standaloneSetup(studentControllerHtml).build();
//		
//	}

	@Test
	void testFindAllStudent() throws Exception {
		when(service.findAllStudent()).thenReturn(students);
		
		mockMVC.perform(get("/api/v2/students"))
				.andExpect(status().isOk())
				.andExpect(model().attribute("students", students))
				.andExpect(view().name("students/allstudents"));
		
		verify(service, times(1)).findAllStudent();
	}
	
	@Test
	void testFindStudentById() throws Exception {
		
		when(service.findByEmail("fikov@list.ru")).thenReturn(students.get(0));
		
		mockMVC.perform(get("/api/v2/students/{email}", "fikov@list.ru"))
			//.andDo(print())
			.andExpect(model().attribute("students", students.get(0)))
			.andExpect(view().name("students/allstudents"))
			.andExpect(status().isOk());
		
		verify(service,times(1)).findByEmail("fikov@list.ru");
	}
	
	@Test
	void testReturnSaveFormt() throws Exception {
		
		mockMVC.perform(get("/api/v2/students/new"))
		//.andDo(print())
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("student"))
		.andExpect(view().name("students/new"));
	
	}

	@Test
	//@Disabled
	void testSaveStudent() throws Exception {
		
		ResultActions resultActions = 
		mockMVC.perform(post("/api/v2/students/new2")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("ferstname", students.get(0).getFerstname())
				.param("lastname", students.get(0).getLastname())
				.param("dateofberth", students.get(0).getDateofberth().toString())
				.param("email", students.get(0).getEmail())
				)
		.andDo(print())
		.andExpect(status().is3xxRedirection())
		.andExpect(status().is(302))
		.andExpect(redirectedUrl("/api/v2/students"))
		.andExpect(view().name("redirect:/api/v2/students"));
	
		var request = resultActions.andReturn().getRequest();
		
		var student =  new Student(
				null,
				request.getParameter("ferstname"),
				request.getParameter("lastname"),
				LocalDate.parse(request.getParameter("dateofberth")),
				request.getParameter("email"),
				0);
		
		verify(service,times(1)).saveStudent(student);
	}	
	
	
	@Test
	void testDeleteForm() {
		//fail("Not yet implemented");
	}

}
