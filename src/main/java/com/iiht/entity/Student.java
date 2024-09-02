package com.iiht.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student
{
	@Id
	private Integer studentId;

	@ElementCollection
	@CollectionTable(name = "student_courses", joinColumns = @JoinColumn(name = "student_id"))
	@Column(name = "course_id")
	private List<Integer> courseIds;

	private String email;
	private String username;
	private String studentName;
//	private String userid;
	private String mobile;
	private String gender;

	private String address;
	private String password;


//	private String role;


}

//package com.application.model;
//
//import javax.persistence.*;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.util.List;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//public class User
//{
//	@Id
//	private Integer studentId;
//	@ElementCollection
//	@CollectionTable(name = "user_courses", joinColumns = @JoinColumn(name = "user_id"))
//	@Column(name = "course_id")
//	private List<Integer> courseIds;
//
////	private List<Integer> courseIds;
////	private List<Integer> courseIds;
//	private String email;
//	private String username;
//	private String studentName;
//	private String userid;
//	private String mobile;
//	private String gender;
//	private String profession;
//	private String address;
//	private String password;
//}