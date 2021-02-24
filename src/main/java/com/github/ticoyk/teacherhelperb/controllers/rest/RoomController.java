package com.github.ticoyk.teacherhelperb.controllers.rest;

import java.util.Set;

import com.github.ticoyk.teacherhelperb.models.Room;
import com.github.ticoyk.teacherhelperb.services.RoomCrudService;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class RoomController {
    
    private RoomCrudService roomCrudService;

    RoomController(RoomCrudService roomCrudService){
        this.roomCrudService = roomCrudService;
    }

    @GetMapping("/api/rooms")
    public Set<Room> index(){
        return this.roomCrudService.getData();
    }

    @GetMapping("/api/rooms/{id}")
    public Room findById(@PathVariable Long id){
        return this.roomCrudService.findById(id);
    }

    @PostMapping("/api/rooms")
    public Room create(@RequestBody Room room){
        return this.roomCrudService.createNewObject(room);
    }

    @PutMapping("/api/rooms/{id}")
    public Room updateRoom(@PathVariable(value="id") Long id, @RequestBody Room room){
        return this.roomCrudService.updateObject(id ,room);
    }

    @DeleteMapping("/api/rooms/{id}")
    public void deleteById(@PathVariable(value="id") Long id){
        try{
            this.roomCrudService.deleteById(id);
        } catch(EmptyResultDataAccessException e) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, e.getMessage()
            );
        }
    }
    
}
