package com.github.ticoyk.teacherhelperb.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.github.ticoyk.teacherhelperb.models.Room;
import com.github.ticoyk.teacherhelperb.models.ApplicationUser;
import com.github.ticoyk.teacherhelperb.models.Desk;
import com.github.ticoyk.teacherhelperb.repositories.RoomRepository;
import com.github.ticoyk.teacherhelperb.utils.JwtUtil;

import org.springframework.stereotype.Service;

@Service
public class RoomUserCrudService implements UserCrudService<Room> {

    private RoomRepository roomRepository;
    private JwtUtil jwtUtil;

    RoomUserCrudService(RoomRepository roomRepository, JwtUtil jwtUtil){
        this.roomRepository = roomRepository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Room findById(Long id, String token) {
        Room room = this.roomRepository.findByIdAndApplicationUserId(
            id, getApplicationUser(token).getId());
        room.setDesks(ordenateDesksByPos(room));
        return room;
    }

    @Override
    public Set<Room> getData(String token) {
        Set<Room> rooms = new HashSet<Room>();
        Iterator<Room> roomIterator= this.roomRepository
            .findByApplicationUserId(getApplicationUser(token).getId())
            .iterator();
        
        roomIterator.forEachRemaining( room -> {
            room.setDesks(ordenateDesksByPos(room));
            rooms.add(room); 
        });
        return rooms;
    }

    @Override
    public Room createNewObject(Room room, String token) {
        room.setDesks(null);
        room.setApplicationUser(getApplicationUser(token));
        return this.roomRepository.save(room);
    }
    
    @Override
    public Room updateObject(Long id, Room newRoom, String token) {
        Room room = findById(id, token);
        if(room == null){
            return null;
        }
        newRoom.setId(id);
        newRoom.setDesks(removeEmptyDesks(newRoom));
        return this.roomRepository.save(newRoom);
    }

    @Override
    public void deleteById(Long id, String token) {
        this.roomRepository.deleteByIdAndApplicationUserId(id, getApplicationUser(token).getId());
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

    private ApplicationUser getApplicationUser(String token){
        return this.jwtUtil.parseTokenToUser(token);
    }
}
