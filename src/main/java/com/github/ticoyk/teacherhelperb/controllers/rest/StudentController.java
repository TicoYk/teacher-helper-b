package com.github.ticoyk.teacherhelperb.controllers.rest;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.github.ticoyk.teacherhelperb.models.Desk;
import com.github.ticoyk.teacherhelperb.models.Student;
import com.github.ticoyk.teacherhelperb.services.StudentCrudService;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class StudentController {
    
    private StudentCrudService studentCrudService;

    StudentController(StudentCrudService studentCrudService){
        this.studentCrudService = studentCrudService;
    }

    @RequestMapping("/api/students")
    public @ResponseBody Set<Student> index(){
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
        try {
            this.studentCrudService.deleteById(id);
        } catch(EmptyResultDataAccessException e) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, e.getMessage()
            );
        }
    }
    
    @PutMapping("/api/students/{id}/desks")
    public Student addDesk(@PathVariable(value="id") Long id, @RequestBody Desk desk){
        Student student = this.studentCrudService.findById(id);
        Set<Desk> desks = student.getDesks();
        student.setDesks(desks);
        return this.studentCrudService.updateObject(id ,student);
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
    MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
