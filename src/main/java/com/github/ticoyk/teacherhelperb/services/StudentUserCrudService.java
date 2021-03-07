package com.github.ticoyk.teacherhelperb.services;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.github.ticoyk.teacherhelperb.models.ApplicationUser;
import com.github.ticoyk.teacherhelperb.models.Student;
import com.github.ticoyk.teacherhelperb.repositories.StudentRepository;
import com.github.ticoyk.teacherhelperb.utils.JwtUtil;

import org.springframework.stereotype.Service;

@Service
public class StudentUserCrudService implements UserCrudService<Student>{

    private StudentRepository studentRepository;
    private JwtUtil jwtUtil;
    
    StudentUserCrudService(StudentRepository studentRepository, JwtUtil jwtUtil){
        this.studentRepository = studentRepository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Student findById(Long id, String token) {
        return this.studentRepository.findByIdAndApplicationUserId(
            id, getApplicationUser(token).getId());
    }

    @Override
    public Set<Student> getData(String token) {
        Iterator<Student> studentIterator= this.studentRepository
            .findByApplicationUserId(getApplicationUser(token)
            .getId()).iterator();

        Set<Student> students = new HashSet<Student>();
        studentIterator.forEachRemaining(student -> { students.add(student); });
        return students;
    }

    @Override
    public Student createNewObject(Student student, String token) {
        student.setApplicationUser(getApplicationUser(token));
        return this.studentRepository.save(student);
    }
    
    @Override
    public Student updateObject(Long id, Student newStudent, String token) {
        Student student = findById(id, token);
        if(student == null){
            return null;
        }
        newStudent.setId(id);
        return this.studentRepository.save(newStudent);
    }

    @Override
    public void deleteById(Long id, String token) {
        this.studentRepository.deleteByIdAndApplicationUserId(id, jwtUtil.parseTokenToUser(token).getId());
    }
    
    private ApplicationUser getApplicationUser(String token){
        return this.jwtUtil.parseTokenToUser(token);
    }
}
