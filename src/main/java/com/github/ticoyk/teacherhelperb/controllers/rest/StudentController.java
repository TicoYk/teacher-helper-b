package com.github.ticoyk.teacherhelperb.controllers.rest;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

import com.github.ticoyk.teacherhelperb.models.Desk;
import com.github.ticoyk.teacherhelperb.models.Student;
import com.github.ticoyk.teacherhelperb.repositories.StudentRepository;
import com.github.ticoyk.teacherhelperb.services.StudentCrudService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
    
    private StudentCrudService studentCrudService;

    StudentController(StudentCrudService studentCrudService){
        this.studentCrudService = studentCrudService;
    }

    @RequestMapping("/api/students")
    public Set<Student> index(){
        return this.studentCrudService.getData();
    }

    @PostMapping("/api/students")
    public Student create(@RequestBody Student student){
        return this.studentCrudService.createNewObject(student);
    }

    @PutMapping("/api/students/{id}")
    public Student updateStudent(@PathVariable(value="id") Long id, @RequestBody Student student){
        return this.studentCrudService.updateObject(id ,student);
    }

    @DeleteMapping("/api/students/{id}")
    public void deleteById(@PathVariable(value="id") Long id){
        this.studentCrudService.deleteById(id);
    }
    
    @PutMapping("/api/students/{id}/desks")
    public Student addDesk(@PathVariable(value="id") Long id, @RequestBody Desk desk){
        Student student = this.studentCrudService.findById(id);
        Set<Desk> desks = student.getDesks();
        student.setDesks(desks);
        return this.studentCrudService.updateObject(id ,student);
    }
    
}
