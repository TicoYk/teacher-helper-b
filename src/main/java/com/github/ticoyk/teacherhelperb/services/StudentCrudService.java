package com.github.ticoyk.teacherhelperb.services;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.github.ticoyk.teacherhelperb.models.Student;
import com.github.ticoyk.teacherhelperb.repositories.StudentRepository;

import org.springframework.stereotype.Service;

@Service
public class StudentCrudService implements CrudService<Student>{

    private StudentRepository studentRepository;

    StudentCrudService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @Override
    public Student findById(Long id) {
        return this.studentRepository.findById(id).get();
    }

    @Override
    public Set<Student> getData() {
        Iterator<Student> studentIterator= this.studentRepository.findAll().iterator();
        Set<Student> students = new HashSet<Student>();
        studentIterator.forEachRemaining(student -> { students.add(student); });
        return students;
    }

    @Override
    public Student createNewObject(Student object) {
        return this.studentRepository.save(object);
    }
    
    @Override
    public Student updateObject(Long id, Student object) {
        Student student = this.studentRepository.findById(id).get();
        object.setId(student.getId());
        this.studentRepository.save(object);
        return object;
    }

    @Override
    public void deleteById(Long id) {
        this.studentRepository.deleteById(id);
    }
    
}
