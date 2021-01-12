package com.github.ticoyk.teacherhelperb.controllers.rest;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

import com.github.ticoyk.teacherhelperb.models.Desk;
import com.github.ticoyk.teacherhelperb.models.Student;
import com.github.ticoyk.teacherhelperb.repositories.StudentRepository;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
    
    private StudentRepository studentRepository;

    StudentController(StudentRepository studentRepository){
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

    @PutMapping("/api/students/{id}/desks")
    public Student addDesk(@PathVariable(value="id") Long id, @RequestBody Desk desk){
        Optional<Student> optStudent = this.studentRepository.findById(id);
        Student student = optStudent.get();
        Set<Desk> desks = student.getDesks();
        student.setDesks(desks);
        return this.studentRepository.save(student);
    }
}
