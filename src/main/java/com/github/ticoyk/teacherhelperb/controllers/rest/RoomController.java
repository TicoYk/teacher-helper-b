package com.github.ticoyk.teacherhelperb.controllers.rest;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.github.ticoyk.teacherhelperb.models.Room;
import com.github.ticoyk.teacherhelperb.repositories.RoomRepository;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoomController {
    
    private RoomRepository roomRepository;

    RoomController(RoomRepository RoomRepository){
        this.roomRepository = RoomRepository;
    }

    @RequestMapping(value = "/api/rooms", method = RequestMethod.GET)
    public Set<Room> index(){
        Set<Room> rooms = new HashSet<Room>();
        Iterator<Room> iterator = this.roomRepository.findAll().iterator();
        iterator.forEachRemaining(room -> { rooms.add(room);});
        return rooms;
    }

    @RequestMapping(value = "/api/rooms", method = RequestMethod.POST)
    public Room create(@RequestBody Room room){
        return this.roomRepository.save(room);
    }

    @RequestMapping(value = "/api/rooms", method = RequestMethod.PUT)
    public Room put(@RequestBody Room room){
        return this.roomRepository.save(room);
    }
    
}
