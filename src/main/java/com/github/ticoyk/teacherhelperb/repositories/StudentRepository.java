package com.github.ticoyk.teacherhelperb.repositories;

import com.github.ticoyk.teacherhelperb.models.Student;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface StudentRepository extends CrudRepository<Student, Long>{
    Student findByIdAndApplicationUserId(Long id, Long applicationUserId);
    Iterable<Student> findByApplicationUserId(Long applicationUserId);
    void deleteByIdAndApplicationUserId(Long id, Long applciationUserId);
}
