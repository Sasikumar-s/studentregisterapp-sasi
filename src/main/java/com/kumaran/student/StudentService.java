package com.kumaran.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
	@Autowired
	StudentRepo repo;
	public void save(Student stu) {
		repo.save(stu);
	}
	public List<Student> listAll() {
		return repo.findAll();
	}
	public Student get(long roll) {
		return repo.findById(roll).get();
	}
	
	public void delete(long roll) {
		repo.deleteById(roll);
	}
}
