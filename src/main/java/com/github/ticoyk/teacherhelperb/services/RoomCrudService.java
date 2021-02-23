package com.github.ticoyk.teacherhelperb.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.github.ticoyk.teacherhelperb.models.Room;
import com.github.ticoyk.teacherhelperb.models.Desk;
import com.github.ticoyk.teacherhelperb.repositories.RoomRepository;

import org.springframework.stereotype.Service;

@Service
public class RoomCrudService implements CrudService<Room> {

    private RoomRepository roomRepository;

    RoomCrudService(RoomRepository roomRepository){
        this.roomRepository = roomRepository;
    }

    @Override
    public Room findById(Long id) {
        Room room = this.roomRepository.findById(id).get();
        room.setDesks(ordenateDesksByPos(room));
        return room;
    }

    @Override
    public Set<Room> getData() {
        Set<Room> rooms = new HashSet<Room>();
        Iterator<Room> roomIterator= this.roomRepository
            .findAll()
            .iterator();
        
        roomIterator.forEachRemaining( room -> {
            room.setDesks(ordenateDesksByPos(room));
            rooms.add(room); 
        });
        return rooms;
    }

    @Override
    public Room createNewObject(Room room) {
        room.setDesks(null);
        return this.roomRepository.save(room);
    }
    
    @Override
    public Room updateObject(Long id, Room newRoom) {
        newRoom.setId(id);
        newRoom.setDesks(removeEmptyDesks(newRoom));
        
        return this.roomRepository.save(newRoom);
    }

    

    @Override
    public void deleteById(Long id) {
        this.roomRepository.deleteById(id);
    }

    private List<Desk> ordenateDesksByPos(Room room){
        List<Desk> desks = room.getDesks();
        Collections.sort(desks, new Comparator<Desk>() {
            @Override
            public int compare(Desk desk1, Desk desk2) {
                if( desk1.getPosY() < desk2.getPosY() ){
                    return -1;
                }
                return 1;
            }
        });
        Collections.sort(desks, new Comparator<Desk>() {
            @Override
            public int compare(Desk desk1, Desk desk2) {
                if( desk1.getPosY().equals(desk2.getPosY()) ){
                    if( desk1.getPosX() < desk2.getPosX() ){
                        return -1;
                    }
                }
                return 1;
            }
        });
        return desks;
    }

    private List<Desk> removeEmptyDesks(Room room){
        Iterator<Desk> desks = room.getDesks().iterator();
        List<Desk> nonEmptyDesks = new ArrayList<Desk>();
        while(desks.hasNext()){
            Desk desk = desks.next();
            if(desk.getStudent() != null) nonEmptyDesks.add(desk);
        }
        return nonEmptyDesks;
    }
}
