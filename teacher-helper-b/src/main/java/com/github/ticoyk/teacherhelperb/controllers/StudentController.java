package com.github.ticoyk.teacherhelperb.controllers;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.github.ticoyk.teacherhelperb.models.Student;
import com.github.ticoyk.teacherhelperb.repositories.StudentRepository;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
    
    private StudentRepository studentRepository;

    StudentController(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping("/api/students")
    public Set<Student> index(){
        Iterator<Student> iterator = this.studentRepository.findAll().iterator();
        Set<Student> students = new HashSet<Student>();
        iterator.forEachRemaining((student) -> students.add(student));
        return students;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/api/students")
    public Student create(@RequestBody Student student){
        return this.studentRepository.save(student);
    }
}
