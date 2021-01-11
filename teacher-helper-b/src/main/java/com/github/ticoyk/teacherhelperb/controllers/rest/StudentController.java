package com.github.ticoyk.teacherhelperb.controllers.rest;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.github.ticoyk.teacherhelperb.models.Student;
import com.github.ticoyk.teacherhelperb.repositories.MongoStudentRepository;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
    
    private MongoStudentRepository studentRepository;

    StudentController(MongoStudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @RequestMapping("/api/students")
    public Set<Student> index(){
        Iterator<Student> iterator = this.studentRepository.findAll().iterator();
        Set<Student> students = new HashSet<Student>();
        iterator.forEachRemaining((student) -> students.add(student));
        return students;
    }

    @PostMapping("/api/students")
    public Student create(@RequestBody Student student){
        return this.studentRepository.save(student);
    }
}
