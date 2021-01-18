package com.dhruv.apidemo.resource;

import java.util.List;

import com.dhruv.apidemo.model.Student;
import com.dhruv.apidemo.repository.StudentRepository;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("student")
public class StudentResources {
	
	StudentRepository studentRepo = new StudentRepository();
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> getStudent()
	{
		return studentRepo.getStudents();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Student getStudentWithId(@PathParam("id") int id)
	{
		return studentRepo.getStudent(id);
	}
	
	@POST
	@Path("create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Student createStudent(Student s1)
	{
		System.out.println(s1.toString());
		studentRepo.createStudent(s1);
		return s1;
	}
}
