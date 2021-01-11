package com.github.ticoyk.teacherhelperb.repositories;

import com.github.ticoyk.teacherhelperb.models.Room;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoRoomRepository extends MongoRepository<Room, String>{
    
}
