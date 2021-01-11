package com.github.ticoyk.teacherhelperb.repositories;

import com.github.ticoyk.teacherhelperb.models.Desk;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoDeskRepository extends MongoRepository<Desk, String>{
    
}
