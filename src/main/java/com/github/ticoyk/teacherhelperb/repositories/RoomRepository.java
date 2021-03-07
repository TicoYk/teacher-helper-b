package com.github.ticoyk.teacherhelperb.repositories;

import com.github.ticoyk.teacherhelperb.models.Room;

import org.springframework.data.repository.CrudRepository;

public interface RoomRepository extends CrudRepository<Room, Long>{
    Room findByIdAndApplicationUserId(Long id, Long applicationUserId);
    Iterable<Room> findByApplicationUserId(Long applicationUserId);
    void deleteByIdAndApplicationUserId(Long id, Long applciationUserId);
}
