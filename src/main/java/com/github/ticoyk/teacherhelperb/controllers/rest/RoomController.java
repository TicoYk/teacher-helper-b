package com.github.ticoyk.teacherhelperb.controllers.rest;

import java.util.Set;

import com.github.ticoyk.teacherhelperb.models.Room;
import com.github.ticoyk.teacherhelperb.services.RoomUserCrudService;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class RoomController {
    
    private RoomUserCrudService roomCrudService;

    RoomController(RoomUserCrudService roomCrudService){
        this.roomCrudService = roomCrudService;
    }

    @GetMapping("/api/rooms")
    public Set<Room> index(@RequestHeader("Authorization") String token){
        return this.roomCrudService.getData(token);
    }

    @GetMapping("/api/rooms/{id}")
    public Room findById(@RequestHeader("Authorization") String token, @PathVariable Long id){
        return this.roomCrudService.findById(id, token);
    }

    @PostMapping("/api/rooms")
    public Room create(@RequestHeader("Authorization") String token, @RequestBody Room room){
        return this.roomCrudService.createNewObject(room, token);
    }

    @PutMapping("/api/rooms/{id}")
    public Room updateRoom(
            @RequestHeader("Authorization") String token,
            @PathVariable(value="id") Long id, 
            @RequestBody Room room
        ){
        return this.roomCrudService.updateObject(id ,room, token);
    }

    @DeleteMapping("/api/rooms/{id}")
    public void deleteById(
            @RequestHeader("Authorization") String token,
            @PathVariable(value="id") Long id
        ){
        try{
            this.roomCrudService.deleteById(id, token);
        } catch(EmptyResultDataAccessException e) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, e.getMessage()
            );
        }
    }
    
}
