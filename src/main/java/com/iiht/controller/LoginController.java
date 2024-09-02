package com.iiht.controller;


import com.iiht.entity.Professor;
import com.iiht.entity.Student;
import com.iiht.service.ProfessorService;
import com.iiht.service.StudentService;
import com.iiht.util.MyJwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController 
{

	@Autowired
	private StudentService studentService;
	
	@Autowired
	private ProfessorService professorService;
	
	@Autowired
    private MyJwtUtil jwtService;
	
	@GetMapping("/")
    public String welcomeMessage()
    {
    	return "Welcome to Elearning Management system !!!";
    }
	
	@PostMapping("/loginstudent")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<Map<String, String>> loginStudent(@RequestBody Student student) throws Exception
	{
		Map<String, String> response = new HashMap<>();
		System.out.println(student);
	        if (studentService.authenticate(student.getUsername(), student.getPassword())) {
//	            String token = jwtService.generateToken(user.getUsername());
	        	String token = jwtService.generateToken(student.getUsername());
	            response.put("token", token); // Include the token in the response
//	            response.put("studentId",Integer.toString(user.getStudentId()));
	            return ResponseEntity.ok(response);
	        } else {
	            response.put("error", "Invalid credentials");
	            return ResponseEntity.status(401).body(response);
	        }
	}
	
	@PostMapping("/loginprofessor")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<Map<String, String>> loginProfessor(@RequestBody Professor professor) throws Exception
	{
//		System.out.println(professor);
//		String currEmail = professor.getEmail();
//		String currPassword = professor.getPassword();
//
//		Professor professorObj = null;
//		if(currEmail != null && currPassword != null)
//		{
//			professorObj = professorService.fetchProfessorByEmailAndPassword(currEmail, currPassword);
//		}
//		if(professorObj == null)
//		{
//			throw new Exception("Professor does not exists!!! Please enter valid credentials...");
//		}
//		return professorObj;

		Map<String, String> response = new HashMap<>();
		System.out.println(professor);
		if (professorService.authenticate(professor.getUsername(), professor.getPassword())) {
//	            String token = jwtService.generateToken(user.getUsername());
			String token = jwtService.generateToken(professor.getUsername());
			response.put("token", token); // Include the token in the response
//	            response.put("studentId",Integer.toString(user.getStudentId()));
			return ResponseEntity.ok(response);
		} else {
			response.put("error", "Invalid credentials");
			return ResponseEntity.status(401).body(response);
		}
	}
}