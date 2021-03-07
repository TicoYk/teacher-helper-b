package com.github.ticoyk.teacherhelperb.controllers.rest;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.github.ticoyk.teacherhelperb.models.Desk;
import com.github.ticoyk.teacherhelperb.models.Student;
import com.github.ticoyk.teacherhelperb.services.StudentUserCrudService;

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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class StudentController {
    
    private StudentUserCrudService studentCrudService;

    StudentController(StudentUserCrudService studentCrudService){
        this.studentCrudService = studentCrudService;
    }

    @RequestMapping("/api/students")
    public @ResponseBody Set<Student> index(@RequestHeader("Authorization") String token){
        return this.studentCrudService.getData(token);
    }

    @PostMapping("/api/students")
    public Student create(
            @RequestHeader("Authorization") String token, 
            @RequestBody Student student
        ){
        return this.studentCrudService.createNewObject(student, token);
    }

    @PutMapping("/api/students/{id}")
    public Student updateStudent(
            @RequestHeader("Authorization") String token,
            @PathVariable(value="id") Long id,
            @RequestBody Student student
        ){
        return this.studentCrudService.updateObject(id ,student, token);
    }

    @DeleteMapping("/api/students/{id}")
    public void deleteById(
            @RequestHeader("Authorization") String token,
            @PathVariable(value="id") Long id
        ){
        try {
            this.studentCrudService.deleteById(id, token);
        } catch(EmptyResultDataAccessException e) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, e.getMessage()
            );
        }
    }
    
    @PutMapping("/api/students/{id}/desks")
    public Student addDesk(
            @RequestHeader("Authorization") String token,
            @PathVariable(value="id") Long id, 
            @RequestBody Desk desk
        ){
        Student student = this.studentCrudService.findById(id, token);
        Set<Desk> desks = student.getDesks();
        student.setDesks(desks);
        return this.studentCrudService.updateObject(id ,student, token);
    }
    
    // To Do
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
