package com.github.ticoyk.teacherhelperb.repositories;

import com.github.ticoyk.teacherhelperb.models.Student;

import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Long>{
    Student findByIdAndApplicationUserId(Long id, Long applicationUserId);
    Iterable<Student> findByApplicationUserId(Long applicationUserId);
    void deleteByIdAndApplicationUserId(Long id, Long applciationUserId);
}
