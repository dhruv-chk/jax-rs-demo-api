package com.dhruv.apidemo.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.*;

import com.dhruv.apidemo.model.Student;

public class StudentRepository {
	
	Connection con = null;
//	Configuration conn = null;
//	SessionFactory sessionFactory = null;
	
	

	public StudentRepository(){
		
//		conn = new Configuration();
//		sessionFactory = conn.buildSessionFactory();
				
		String url = "jdbc:mysql://localhost:3306/restdemo";
		String username = "root";
		String password = "1990";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
		}
		catch(Exception e)
		{
			System.out.println("Error: " + e.toString());
		}
		
	}
	
	public List<Student> getStudents()
	{				
		List<Student> students = new ArrayList<Student>();
		String query = "Select * from student";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			while(rs.next())
			{
				Student s = new Student();
				s.setId(rs.getInt(1));
				s.setName(rs.getString(2));
				s.setMarks(rs.getInt(3));
				
				students.add(s);
			}
		}
		catch(Exception e)
		{
			System.out.println("Error: " + e.toString());
		}
		return students;
	}
	
	public Student getStudent(int id)
	{
		Student s = new Student();
		String query = "Select * from student where id=" + id;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			if (rs.next())
			{
				s.setId(rs.getInt(1));
				s.setName(rs.getString(2));
				s.setMarks(rs.getInt(3));
			}
		}
		catch(Exception e)
		{
			System.out.println("Error: " + e.toString());
		}
		return s;
	}

	public void createStudent(Student s1) {
		String query= "Insert into student values(?,?,?)";
		try {
			PreparedStatement st = con.prepareStatement(query);
			st.setInt(1, s1.getId());
			st.setString(2, s1.getName());
			st.setInt(3, s1.getMarks());			
			st.executeUpdate();
		}
		catch(Exception e)
		{
			System.out.println("Error: " + e.toString());
		}
	}
}
