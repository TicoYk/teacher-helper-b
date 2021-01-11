package com.github.ticoyk.teacherhelperb.controllers.rest;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.github.ticoyk.teacherhelperb.models.Room;
import com.github.ticoyk.teacherhelperb.repositories.MongoRoomRepository;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoomController {
    
    private MongoRoomRepository mongoRoomRepository;

    RoomController(MongoRoomRepository mongoRoomRepository){
        this.mongoRoomRepository = mongoRoomRepository;
    }

    @RequestMapping(value = "/api/rooms", method = RequestMethod.GET)
    public Set<Room> index(){
        Set<Room> rooms = new HashSet<Room>();
        Iterator<Room> iterator = this.mongoRoomRepository.findAll().iterator();
        iterator.forEachRemaining(room -> { rooms.add(room);});
        return rooms;
    }

    @RequestMapping(value = "/api/rooms", method = RequestMethod.POST)
    public Room create(@RequestBody Room room){
        return this.mongoRoomRepository.save(room);
    }
}
